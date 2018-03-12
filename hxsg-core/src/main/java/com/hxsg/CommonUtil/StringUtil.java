package com.hxsg.CommonUtil;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * 字符串处理工具类
 *
 * @author jiangzhenjian  2016-3-18 下午09:37:15
 * @version V1.0
 * @description
 */
public class StringUtil {

    /**
     * 判断字符串集合是否为空
     *
     * @param @param  strings
     * @param @return
     * @return boolean
     * @description
     * @title isEmpty
     */
    public static boolean isEmpty(String... strings) {
        for (String s : strings) {
            if (s == null || s.trim().length() == 0) {
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) throws IOException {
//    try {
//        Runtime rt = Runtime.getRuntime();
//        Process pr=rt.exec("cmd /c D:&& cd kettle/xml/img/&&dcmj2pnm.exe +obt -v 1.dcm 3.jpg"); // cmd /c calc
//        BufferedReader input = new BufferedReader(new InputStreamReader(pr.getInputStream(), "GBK"));
//
//        String line = null;
//
//        while ((line = input.readLine()) != null) {
//            System.out.println(line);
//        }
//
//        int exitVal = pr.waitFor();
//        System.out.println("Exited with error code " + exitVal);
//
//    } catch (Exception e) {
//        System.out.println(e.toString());
//        e.printStackTrace();
//    }


//    URL url=new URL("http://localhost:8088/1.dcm");
//    HttpURLConnection urlcon = (HttpURLConnection)url.openConnection();
//    urlcon.connect();         //获取连接
//    InputStream is = urlcon.getInputStream();
//    File file=new File("D:/a.jpg");
//    new FileOutputStream()
        //BufferedReader buffer = new BufferedReader(new InputStreamReader(is));
//    StringBuffer bs = new StringBuffer();
//    String l = null;
//    while((l=buffer.readLine())!=null){
//        bs.append(l).append("/n");
//    }
//    System.out.println(bs.toString());
//
//
//    String str = "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";
//
//    BASE64Decoder decoder=new BASE64Decoder();
//
//    byte[] bytes= new byte[0];
//        bytes = decoder.decodeBuffer(str);
//
//
//    File file=new File("D:/a.jpg");
//
//    FileOutputStream fos=new FileOutputStream(file);
//
//    fos.write(bytes);
//
//    fos.flush();
//
//    fos.close();

    }


}

class test {
    /**
     * 从网络Url中下载文件
     *
     * @param urlStr
     * @param fileName
     * @param savePath
     * @throws java.io.IOException
     */
    public static void downLoadFromUrl(String urlStr, String fileName, String savePath) {
        URL url = null;
        FileOutputStream fos = null;
        InputStream inputStream = null;
        ByteArrayOutputStream bos = null;

        try {
            url = new URL("http://127.0.0.1:8088/1.dcm");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            //设置超时间为3秒
            conn.setConnectTimeout(10 * 1000);
            //防止屏蔽程序抓取而返回403错误
            conn.setRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 5.0; Windows NT; DigExt)");
            //得到输入流
            inputStream = conn.getInputStream();
            //获取自己数组
            byte[] buffer = new byte[1024];
            int len = 0;
            bos = new ByteArrayOutputStream();
            while ((len = inputStream.read(buffer)) != -1) {
                bos.write(buffer, 0, len);
            }
            byte[] getData = bos.toByteArray();
            //文件保存位置
            File saveDir = new File(savePath);
            if (!saveDir.exists()) {
                saveDir.mkdir();
            }
            File file = new File(saveDir + File.separator + fileName);
            fos = new FileOutputStream(file);
            fos.write(getData);


        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (inputStream != null) {

                    inputStream.close();

                }
                if (bos != null) {
                    bos.close();
                }
                if (fos != null) {

                    fos.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


    }


    /**
     * 从输入流中获取字节数组
     *
     * @param inputStream
     * @return
     * @throws java.io.IOException
     */
    public static byte[] readInputStream(InputStream inputStream) throws IOException {
        byte[] buffer = new byte[1024];
        int len = 0;
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        while ((len = inputStream.read(buffer)) != -1) {
            bos.write(buffer, 0, len);
        }
        bos.close();
        return bos.toByteArray();
    }

    public static void main(String[] args) {
        try {
            downLoadFromUrl("http://127.0.0.1:8088/1.dcm",
                    "4.dcm", "D:/kettle/xml/img/");
        } catch (Exception e) {
            e.printStackTrace();
            // TODO: handle exception
        }
    }


}