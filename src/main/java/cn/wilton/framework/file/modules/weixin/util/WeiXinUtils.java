package cn.wilton.framework.file.modules.weixin.util;

import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.servlet.http.HttpServletResponse;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>自定义微信API接口工具类
 * @author Ranger
 * @email wilton.icp@gmail.com
 * @since 2021/4/9
 */
public class WeiXinUtils  {

    /**
     * 微信扫码状态记录
     * key = scene_str;value = openId
     */
    private static Map<String,String > USER_OPENID = new HashMap<>();

    /**
     * 微信扫码用户昵称记录
     * key = scene_str;value = nickname
     */
    private static Map<String,String > USER_NICKNAME = new HashMap<>();



    public static Map<String, String> getUserOpenid() {
        return USER_OPENID;
    }

    public static void setUserOpenid(Map<String, String> userOpenid) {
        USER_OPENID = userOpenid;
    }

    public static Map<String, String> getUserNickname() {
        return USER_NICKNAME;
    }

    public static void setUserNickname(Map<String, String> userNickname) {
        USER_NICKNAME = userNickname;
    }

    /**
     * XML格式字符串转换为Map
     *
     * @param strXML XML字符串
     * @return XML数据转换后的Map
     * @throws Exception
     */
    public static Map<String, String> xmlToMap(String strXML) throws Exception {
        try {
            Map<String, String> data = new HashMap<String, String>();
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = dbf.newDocumentBuilder();
            InputStream stream = new ByteArrayInputStream(strXML.getBytes("UTF-8"));
            org.w3c.dom.Document doc = documentBuilder.parse(stream);
            doc.getDocumentElement().normalize();
            NodeList nodeList = doc.getDocumentElement().getChildNodes();
            for (int idx = 0; idx < nodeList.getLength(); ++idx) {
                Node node = nodeList.item(idx);
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    org.w3c.dom.Element element = (org.w3c.dom.Element) node;
                    data.put(element.getNodeName(), element.getTextContent());
                }
            }
            try {
                stream.close();
            } catch (Exception ex) {
                // do nothing
            }
            return data;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw ex;
        }

    }


    /**
     * 返回信息给微信 商户已经接收到回调
     *
     * @param response
     * @param content  内容
     * @throws Exception
     */
    public static void responsePrint(HttpServletResponse response, String content) throws Exception {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/xml");
        response.getWriter().print(content);
        response.getWriter().flush();
        response.getWriter().close();
    }

}
