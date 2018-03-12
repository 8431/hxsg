package com.hxsg.CommonUtil;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanInitializationException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import java.security.SecureRandom;
import java.util.Properties;

/**
 * Created by Administrator on 2017/7/26 0026.
 */
public class EncryptablePropertyPlaceholderConfigurer extends PropertyPlaceholderConfigurer {
    private static final String DESKEY = "GZ6C058DLFZLPCJGWWWJWWSFF";
    @Override
    protected void processProperties(ConfigurableListableBeanFactory beanFactory, Properties props) throws BeansException {
        try {
            String username = props.getProperty("username");
            if (username != null) {
                props.setProperty("username",doWork(username,DESKEY,1) );

            }

            String password = props.getProperty("password");
            if (password != null) {
                props.setProperty("password", doWork(password,DESKEY,1) );
            }

            String url = props.getProperty("url");
            if (url != null) {
                props.setProperty("url", doWork(url,DESKEY,1));
            }

            String driver= props.getProperty("driver");
            if(driver != null){
                props.setProperty("driver",doWork(driver,DESKEY,1));
                System.out.println(doWork(driver,DESKEY,1)+"------0000---------");
            }

            super.processProperties(beanFactory, props);
        } catch (Exception e) {
            e.printStackTrace();
            throw new BeanInitializationException(e.getMessage());
        }
    }
    private static String doWork(String data, String password,int flag) {
        try {
            SecureRandom random = new SecureRandom();
            DESKeySpec desKey = new DESKeySpec(password.getBytes());
            SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
            SecretKey securekey = keyFactory.generateSecret(desKey);
            Cipher cipher = Cipher.getInstance("DES");
            //
            if(flag == 0){
                BASE64Encoder base64encoder = new BASE64Encoder();
                cipher.init(Cipher.ENCRYPT_MODE, securekey, random);
                return base64encoder.encode(cipher.doFinal(data.getBytes("UTF-8")));
            }else{
                BASE64Decoder base64decoder = new BASE64Decoder();
                byte[] encodeByte = base64decoder.decodeBuffer(data);
                cipher.init(Cipher.DECRYPT_MODE, securekey, random);
                byte[] decoder = cipher.doFinal(encodeByte);
                return new String(decoder,"UTF-8");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * test
     * @param args
     */
    public static void main(String[] args) {
        try {
            // 明文
            String str = "Eru43wPo";
            String desc = doWork(str, DESKEY,0);
            System.out.println("密文：" + desc);
            // 解密
            str = doWork(desc, DESKEY,1);
            System.out.println("明文：" +str);
        } catch (Exception e1) {
            e1.printStackTrace();
        }

    }
}
