package cn.wilton.framework.file.common.util;

/**
 * @Description
 * @Author: Ranger
 * @Date: 2021/2/4 10:03
 * @Email: wilton.icp@gmail.com
 */
public class ByteHapper {
    //原始数组
    byte[] bytes;
    //记录当前写入到多少位
    int index;

    private ByteHapper(int capacity){
        bytes = new byte[capacity];
        index = 0;
    }

    public static ByteHapper CreateBytes(int capacity){
        ByteHapper byteHapper = new ByteHapper(capacity);
        return byteHapper;
    }

    //向数组中追加内容
    public ByteHapper AppendNumber(long val){
        byte[] arr = Number2byte(val);
        AppendBytes(arr);
        return this;
    }
    public ByteHapper AppendNumber(int val){
        byte[] arr = Number2byte(val);
        AppendBytes(arr);
        return this;
    }
    public ByteHapper AppendNumber(short val){
        byte[] arr = Number2byte(val);
        AppendBytes(arr);
        return this;
    }
    public ByteHapper AppendNumber(byte val){
        byte[] arr = new byte[]{val};
        AppendBytes(arr);
        return this;
    }

    /**
     * 获取数据的总和
     * @return
     */
    public int GetSum(){
        int ret = 0;
        for(int i = 0 ; i < bytes.length ; i ++){
            ret += bytes[i];
        }
        return ret;
    }

    //追加byte数组
    public ByteHapper AppendBytes(byte[] arr){

        for(byte i = 0 ; i < arr.length ; i ++){
            bytes[index + i] = arr[i];
        }

        index += arr.length;
        return this;
    }

    /**
     * 将数字转换为byte数组
     */
    public static byte[] Number2byte(long val) {

        byte[] arr = new byte[]{
                (byte) ((val >> 56) & 0xFF),
                (byte) ((val >> 48) & 0xFF),
                (byte) ((val >> 40) & 0xFF),
                (byte) ((val >> 32) & 0xFF),
                (byte) ((val >> 24) & 0xFF),
                (byte) ((val >> 16) & 0xFF),
                (byte) ((val >> 8) & 0xFF),
                (byte) (val & 0xFF)
        };

        return arr;
    }
    public static byte[] Number2byte(int val) {

        byte[] arr = new byte[]{
                (byte) ((val >> 24) & 0xFF),
                (byte) ((val >> 16) & 0xFF),
                (byte) ((val >> 8) & 0xFF),
                (byte) (val & 0xFF)
        };

        return arr;
    }
    public static byte[] Number2byte(short val) {

        byte[] arr = new byte[]{
                (byte) ((val >> 8) & 0xFF),
                (byte) (val & 0xFF)
        };

        return arr;
    }
}
