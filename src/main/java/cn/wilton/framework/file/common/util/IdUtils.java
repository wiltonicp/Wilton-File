package cn.wilton.framework.file.common.util;

import cn.hutool.core.util.IdUtil;
import org.apache.commons.lang3.StringUtils;

import java.nio.ByteBuffer;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * <p>
 * @author Ranger
 * @email wilton.icp@gmail.com
 * @since 2021/3/31
 */
public class IdUtils extends IdUtil{

    /**
     * 兑换码长度
     */
    private static final Integer CODE_LENGTH = 16;
    private static String BASE32_STRATEGY = "abcdefghijkmnpqrstuvwxyz23456789";
    private static final String PASSWORD = "dak3le2";
    /**
     * 从byte转为字符表索引所需要的位数
     */
    private static final int convertByteCount = 5;

    /**
     * 生成 id
     * @return
     */
    public static String getId(){
        String uuid = IdUtil.simpleUUID();
        String nowStr = "-" + DateUtil.formatFullTime(LocalDateTime.now(), DateUtil.FULLS_TIME_PATTERN);
        return uuid + nowStr + "-";
    }

    /**
     * 生成随机4位取件码
     * @return
     */
    public static String getCode(){
        String uuid = new String();
        for(int i = 0;i < 4;i++){
            char ch = BASE32_STRATEGY.charAt(new Random().nextInt(BASE32_STRATEGY.length()));
            uuid += ch;
        }
        return uuid;
    }

    /**
     * 生成一个唯一码
     * @param batchId
     * @return
     */
    public static String createdOne(Long batchId) {
        byte[] bytes = longToBytes(batchId);
        List<String> codes = created(bytes[0], 1, CODE_LENGTH, PASSWORD);
        return codes.get(0);
    }

    /**
     * 批量生成
     * @param batchId 批次 id
     * @param codeNum 生成数量
     * @return
     */
    public static List<String> created(Long batchId,int codeNum) {
        byte[] bytes = longToBytes(batchId);
        List<String> codes = created(bytes[0], codeNum, CODE_LENGTH, PASSWORD);
        return codes;
    }

    /**
     * 生成兑换码
     *
     * [类型(1)，id(4)，随机码(n),校验码(1)]
     * 类型为每次生成兑换码的批次id，这里只用了1个byte来存储，可以根据需要增加。
     * id为每次生成的每个兑换码的唯一id。
     * 随机码为每个兑换码的随机数。
     * 校验码用来在验证兑换码时进行校验。
     * @param batchId 批次 id
     * @param codeNum 生成数量
     * @param codeLength 兑换码长度
     * @param password 校验码
     * @return
     */
    public static List<String> created(byte batchId, int codeNum, int codeLength, String password) {
        //8位的数据总长度
        int fullCodeLength = codeLength * convertByteCount / 8;
        //随机码对时间和id同时做异或处理
        //类型1，id4，随机码n,校验码1
        //随机码有多少个
        int randCount = fullCodeLength - 6;

        //如果随机码小于0 不生成
        if(randCount <= 0 ) {
            return null;
        }
        List<String> result = new ArrayList<>();
        for(int i = 0 ; i < codeNum ; i ++) {
            //这里使用i作为code的id
            //生成n位随机码
            byte[] randBytes = new byte[randCount];
            for(int j = 0 ; j  < randCount ; j ++) {
                randBytes[j] = (byte)(Math.random() * Byte.MAX_VALUE);
            }

            //存储所有数据
            ByteHapper byteHapper = ByteHapper.CreateBytes(fullCodeLength);
            byteHapper.AppendNumber(batchId).AppendNumber(i).AppendBytes(randBytes);

            //计算校验码 这里使用所有数据相加的总和与byte.max 取余
            byte verify = (byte) (byteHapper.GetSum() % Byte.MAX_VALUE);
            byteHapper.AppendNumber(verify);

            //使用随机码与时间和ID进行异或
            for(int j = 0 ; j < 5 ; j ++) {
                byteHapper.bytes[j] = (byte) (byteHapper.bytes[j] ^ (byteHapper.bytes[5 + j % randCount]));
            }

            //使用密码与所有数据进行异或来加密数据
            byte[] passwordbytes = password.getBytes();
            for(int j = 0 ; j < byteHapper.bytes.length ; j++){
                byteHapper.bytes[j] = (byte) (byteHapper.bytes[j] ^ passwordbytes[j % passwordbytes.length]);
            }

            //这里存储最终的数据
            byte[] bytes = new byte[codeLength];

            //按6位一组复制给最终数组
            for(int j = 0 ; j < byteHapper.bytes.length ; j ++) {
                for(int k = 0 ; k < 8 ; k ++) {
                    int sourceIndex = j*8+k;
                    int targetIndex_x = sourceIndex / convertByteCount;
                    int targetIndex_y = sourceIndex % convertByteCount;
                    byte placeval = (byte)Math.pow(2, k);
                    byte val = (byte)((byteHapper.bytes[j] & placeval) == placeval ? 1:0);
                    //复制每一个bit
                    bytes[targetIndex_x] = (byte)(bytes[targetIndex_x] | (val << targetIndex_y));
                }
            }
            StringBuilder strBuilder = new StringBuilder();
            //编辑最终数组生成字符串
            for(int j = 0 ; j < bytes.length ; j ++) {
                strBuilder.append(BASE32_STRATEGY.charAt(bytes[j]));
            }
            result.add(strBuilder.toString());
        }
        return result;
    }

    /**
     * 验证兑换码
     * @param code
     */
    public static Boolean verifyCode(String code){
        if(!StringUtils.isNotBlank(code) || code.length() != 16){
            return false;
        }
        byte[] bytes = new byte[code.length()];

        //首先遍历字符串从字符表中获取相应的二进制数据
        for(int i=0;i<code.length();i++){
            byte index = (byte) BASE32_STRATEGY.indexOf(code.charAt(i));
            bytes[i] = index;
        }

        //还原数组
        int fullCodeLength = code.length() * convertByteCount / 8;
        //随机码有多少个
        int randCount = fullCodeLength - 6;

        byte[] fullBytes = new byte[fullCodeLength];
        for(int j = 0 ; j < fullBytes.length ; j ++) {
            for(int k = 0 ; k < 8 ; k ++) {
                int sourceIndex = j*8+k;
                int targetIndex_x = sourceIndex / convertByteCount;
                int targetIndex_y = sourceIndex % convertByteCount;
                byte placeval = (byte)Math.pow(2, targetIndex_y);
                byte val = (byte)((bytes[targetIndex_x] & placeval) == placeval ? 1:0);
                fullBytes[j] = (byte) (fullBytes[j] | (val << k));
            }
        }
        //解密，使用密码与所有数据进行异或来加密数据
        byte[] passwordBytes = PASSWORD.getBytes();
        for(int j = 0 ; j < fullBytes.length ; j++){
            fullBytes[j] = (byte) (fullBytes[j] ^ passwordBytes[j % passwordBytes.length]);
        }
        //使用随机码与时间和ID进行异或
        for(int j = 0 ; j < 5 ; j ++) {
            fullBytes[j] = (byte) (fullBytes[j] ^ (fullBytes[5 + j % randCount]));
        }
        //获取校验码 计算除校验码位以外所有位的总和
        int sum = 0;
        for(int i = 0 ;i < fullBytes.length - 1; i ++){
            sum += fullBytes[i];
        }
        byte verify = (byte) (sum % Byte.MAX_VALUE);
        //校验
        if(verify == fullBytes[fullBytes.length - 1]){
            return true;
        }else {
            return false;
        }
    }

    public static byte[] longToBytes(Long x) {
        final ByteBuffer buffer = ByteBuffer.allocate(Long.BYTES);
        buffer.putLong(x);
        return buffer.array();
    }

    public static long bytesToLong(byte[] bytes) {
        final ByteBuffer buffer = ByteBuffer.allocate(Long.BYTES);
        buffer.put(bytes);
        buffer.flip();
        return buffer.getLong();
    }

    public static void main(String[] args) {
        System.out.println(fastUUID());
    }

}
