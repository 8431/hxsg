//package com.hxsg.util;
//
//import android.graphics.Bitmap;
//import android.graphics.BitmapFactory;
//import sanguo.obj.Point;
//import util.Cache;
//import util.HashList;
//import util.Resources;
//
//import javax.microedition.lcdui.Image;
//import java.io.*;
//import java.util.Hashtable;
//import java.util.Vector;
//
///**
// * Created by dlf on 2018/2/1 0001.
// * Email 1429264916@qq.com
// */
//public class Test {
//    private static  byte[] end;
//    private static String[] fightSceneInPackage;
//    private static  byte[] hand;
//    private static String[] mapDataInPackage;
//    private static Cache mapElementImageCache;
//    public static String[][] mapImgNameListInRms;
//    public static String[] mapImgRecordKey;
//    public static String[][] mapImgSort;
//    private static String[] mapPicInPackage;
//    private static Cache mapStageImageCahce;
//    private static Hashtable menuResources;
//    public static Hashtable needMapImgTable;
//    public static Hashtable needSpriteBinTable;
//    public static Hashtable needSpriteImgTable;
//    public static Vector netGetBinName;
//    public static Vector netGetImageName;
//    public static Hashtable netMapImgTable;
//    public static Hashtable netSpriteBinTable;
//    public static Hashtable netSpriteImgTable;
//    public static HashList onlyNetSpriteBinNameTable;
//    public static HashList onlyNetSpriteImageNameTable;
//    private static InputStream pointInputStream;
//    private static Cache rHeadImageCache;
//    private static Cache realHeadHasCache;
//    private static String[] realPicInPackage;
////    private static com.hxsg.util.Resources res;
//    private static String[] spriteBinInPackage;
//    public static String[][] spriteBinNameListInRms;
//    private static Cache spriteImageCache;
//    public static String[][] spriteImgNameListInRms;
//    private static Cache spriteMatrixCache;
//    private static String[] spritePicInPackage;
//    public static String[] spriteRecordKey;
//    public static String[][] spriteSort;
//    private static Cache stageTempImageCahce;
//    private static Hashtable stringResources;
//    public static int version = 0;
//    private static InputStream warPointInputStream;
//    private String cid;
//    private Point currentPoint;
//    private Point currentWarPoint;
//    private String exitUrl;
//    public static void main(String[] args) {
//
//        byte[]  bt= getFileToByte(new File("C:\\Users\\Administrator\\Desktop\\lwsg\\assets\\res\\r\\fh.hf"));
//        try {
//            String srt2=new String(bt,"UTF-8");
//            System.out.println(srt2);
//        } catch (UnsupportedEncodingException e) {
//            e.printStackTrace();
//        }
//    }
//    public static byte[] getFileToByte(File file) {
//        byte[] by = new byte[(int) file.length()];
//        try {
//            InputStream is = new FileInputStream(file);
//            ByteArrayOutputStream bytestream = new ByteArrayOutputStream();
//            byte[] bb = new byte[2048];
//            int ch;
//            ch = is.read(bb);
//            while (ch != -1) {
//                bytestream.write(bb, 0, ch);
//                ch = is.read(bb);
//            }
//            by = bytestream.toByteArray();
//        } catch (Exception ex) {
//            ex.printStackTrace();
//        }
//        return by;
//    }
//    public static int saveToImgByBytes(File imgFile,String imgPath,String imgName){
//
//        int stateInt = 1;
//        if(imgFile.length() > 0){
//            try {
//                File file=new File(imgPath,imgName);//可以是任何图片格式.jpg,.png等
//                FileOutputStream fos=new FileOutputStream(file);
//
//                FileInputStream fis = new FileInputStream(imgFile);
//
//                byte[] b = new byte[1024];
//                int nRead = 0;
//                while ((nRead = fis.read(b)) != -1) {
//                    fos.write(b, 0, nRead);
//                }
//                fos.flush();
//                fos.close();
//                fis.close();
//
//            } catch (Exception e) {
//                stateInt = 0;
//                e.printStackTrace();
//            } finally {
//            }
//        }
//        return stateInt;
//    }
//        public  static boolean  saveBitmap2file(Bitmap bmp,String filename){
//            Bitmap.CompressFormat format= Bitmap.CompressFormat.JPEG;
//            int quality = 100;
//            OutputStream stream = null;
//            try {
//                stream = new FileOutputStream("C:\\Users\\Administrator\\Desktop\\lwsg\\assets" + filename);
//            } catch (FileNotFoundException e) {
//                // TODO Auto-generated catch block
//                e.printStackTrace();
//            }
//            return bmp.compress(format, quality, stream);
//        }
//    public static Image readImageCode(String paramString) {
//        Image localImage = null;
//        InputStream localInputStream = null;
//        try {
//            System.out.println(paramString);
//             localInputStream=new FileInputStream(paramString);
//            byte[] arrayOfByte1 = new byte[localInputStream.available()];
//            localInputStream.read(arrayOfByte1);
//            int i = arrayOfByte1[(-1 + arrayOfByte1.length)];
//            System.out.println("i="+i);
//            byte[] arrayOfByte2 = new byte[-1 + 20 + arrayOfByte1.length];
//            if (i == 0) {
//                System.arraycopy(hand, 0, arrayOfByte2, 0, 8);
//                System.arraycopy(arrayOfByte1, 0, arrayOfByte2, 8, -1 + arrayOfByte1.length);
//                System.arraycopy(end, 0, arrayOfByte2, -1 + 8 + arrayOfByte1.length, 12);
//            }
//            label191:
//            do {
//                localImage = Image.createImage(arrayOfByte2, 0, arrayOfByte2.length);
//                System.out.println("localImage"+localImage);
//                break label191;
//            }
//            while (i != 1);
//            System.arraycopy(hand, 0, arrayOfByte2, 0, 8);
//            System.arraycopy(arrayOfByte1, -188 + -1 + arrayOfByte1.length, arrayOfByte2, 8, 188);
//            System.arraycopy(arrayOfByte1, 0, arrayOfByte2, 196, -188 + -1 + arrayOfByte1.length);
//            System.arraycopy(end, 0, arrayOfByte2, -1 + 8 + arrayOfByte1.length, 12);
//        } catch (Exception localException) {
//
//        }
//        finally {
//            try {
//                // 关闭输出流
//                localInputStream.close();
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        }
//        System.out.println(localImage);
//        return localImage;
//    }
//
//}
