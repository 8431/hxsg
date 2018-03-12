//package com.hxsg.util;
//
///**
// * Created by dlf on 2018/2/1 0001.
// * Email 1429264916@qq.com
// */
//
//import android.content.res.AssetManager;
//import android.graphics.Bitmap;
//import android.graphics.Matrix;
//import connect.RequestListener;
//import game.MyLayer;
//
//import java.io.ByteArrayInputStream;
//import java.io.ByteArrayOutputStream;
//import java.io.InputStream;
//import java.io.PrintStream;
//import java.util.Hashtable;
//import java.util.Vector;
//import javax.microedition.lcdui.Graphics;
//import javax.microedition.lcdui.Image;
//
//import main.MainMidlet;
//import sanguo.*;
//
//import sanguo.obj.Point;
//import sanguo.sprite.CortegeSprite;
//import sanguo.sprite.HorseSprite;
//import util.*;
//
//public class Resources {
//
//    private static final byte[] end;
//    private static String[] fightSceneInPackage;
//    private static final byte[] hand;
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
//    private static Resources res;
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
//
//    static {
//        Object localObject = new String[2][];
//        String[] arrayOfString = new String[2];
//        arrayOfString[0] = "role/n";
//        arrayOfString[1] = "role/h";
//        localObject[0] = arrayOfString;
//        arrayOfString = new String[2];
//        arrayOfString[0] = "role/gn";
//        arrayOfString[1] = "role/jn";
//        localObject[1] = arrayOfString;
//        spriteSort = (String) localObject;
//        localObject = new String[3];
//        localObject[0] = "roleSort0";
//        localObject[1] = "roleSort1";
//        localObject[2] = "roleSort";
//        spriteRecordKey = (String) localObject;
//        localObject = new String[3][];
//        arrayOfString = new String[3];
//        arrayOfString[0] = "chibi";
//        arrayOfString[1] = "battile";
//        arrayOfString[2] = "battle";
//        localObject[0] = arrayOfString;
//        arrayOfString = new String[3];
//        arrayOfString[0] = "chengdu";
//        arrayOfString[1] = "hole";
//        arrayOfString[2] = "hulao";
//        localObject[1] = arrayOfString;
//        arrayOfString = new String[3];
//        arrayOfString[0] = "mian";
//        arrayOfString[1] = "novi";
//        arrayOfString[2] = "wu";
//        localObject[2] = arrayOfString;
//        mapImgSort = (String) localObject;
//        localObject = new String[4];
//        localObject[0] = "mapImgSort0";
//        localObject[1] = "mapImgSort1";
//        localObject[2] = "mapImgSort2";
//        localObject[3] = "mapImgSort";
//        mapImgRecordKey = (String) localObject;
//        spriteImageCache = new Cache(500, true);
//        spriteMatrixCache = new Cache(500, true);
//        mapStageImageCahce = new Cache(100, false);
//        rHeadImageCache = new Cache(6, true);
//        stageTempImageCahce = new Cache(100, false);
//        realHeadHasCache = new Cache(50, true);
//        mapElementImageCache = new Cache(100, false);
//        localObject = new byte[8];
//        localObject[0] = -119;
//        localObject[1] = 80;
//        localObject[2] = 78;
//        localObject[3] = 71;
//        localObject[4] = 13;
//        localObject[5] = 10;
//        localObject[6] = 26;
//        localObject[7] = 10;
//        hand = (B) localObject;
//        localObject = new byte[12];
//        localObject[4] = 73;
//        localObject[5] = 69;
//        localObject[6] = 78;
//        localObject[7] = 68;
//        localObject[8] = -82;
//        localObject[9] = 66;
//        localObject[10] = 96;
//        localObject[11] = -126;
//        end = (B) localObject;
//    }
//
//    public static void addIconImageCache(String paramString, Image paramImage) {
//        stageTempImageCahce.add(paramString, paramImage);
//    }
//
//    public static void addMapElementImageCache(String paramString, Image paramImage) {
//        mapElementImageCache.add(paramString, paramImage);
//    }
//
//    public static String[][] addNameList(String[][] paramArrayOfString, int paramInt, String[] paramArrayOfString1) {
//        if (paramArrayOfString1 != null)
//            if (paramArrayOfString[paramInt] != null) {
//                String[] arrayOfString = new String[paramArrayOfString[paramInt].length + paramArrayOfString1.length];
//                for (int i = 0; i < arrayOfString.length; ++i)
//                    if (i >= paramArrayOfString[paramInt].length)
//                        arrayOfString[i] = paramArrayOfString1[(i - paramArrayOfString[paramInt].length)];
//                    else
//                        arrayOfString[i] = paramArrayOfString[paramInt][i];
//                paramArrayOfString[paramInt] = arrayOfString;
//            } else {
//                paramArrayOfString[paramInt] = paramArrayOfString1;
//            }
//        return paramArrayOfString;
//    }
//
//    public static void addRHeadImageCache(String paramString, Image paramImage) {
//        rHeadImageCache.add(paramString, paramImage);
//    }
//
//    public static void addSpriteImageCache(String paramString, Image paramImage) {
//        spriteImageCache.add(paramString, paramImage);
//    }
//
//    public static void addSpriteImageTempCache(String paramString, Image paramImage) {
//        spriteImageCache.addTemp(paramString, paramImage);
//    }
//
//    public static void addStageTempImageCache(String paramString, Image paramImage) {
//        stageTempImageCahce.add(paramString, paramImage);
//    }
//
//    public static int byteToInt(int paramInt1, int paramInt2) {
//        if (paramInt2 < 0)
//            paramInt2 += 256;
//        return (paramInt2 + (paramInt1 << 8));
//    }
//
//    public static String cacheState() {
//        StringBuffer localStringBuffer = new StringBuffer();
//        localStringBuffer.append("spriteImage:");
//        localStringBuffer.append(spriteImageCache.getSize());
//        localStringBuffer.append(StringUtils.strret);
//        localStringBuffer.append("spriteMatrix:");
//        localStringBuffer.append(spriteMatrixCache.getSize());
//        localStringBuffer.append(StringUtils.strret);
//        localStringBuffer.append("mapStageImage:");
//        localStringBuffer.append(mapStageImageCahce.getSize());
//        localStringBuffer.append(StringUtils.strret);
//        localStringBuffer.append("rHeadImage:");
//        localStringBuffer.append(rHeadImageCache.getSize());
//        localStringBuffer.append(StringUtils.strret);
//        localStringBuffer.append("stageTempImage:");
//        localStringBuffer.append(stageTempImageCahce.getSize());
//        localStringBuffer.append(StringUtils.strret);
//        localStringBuffer.append("realHeadHasCache:");
//        localStringBuffer.append(realHeadHasCache.getSize());
//        localStringBuffer.append(StringUtils.strret);
//        localStringBuffer.append("mapElementImageCache:");
//        localStringBuffer.append(mapElementImageCache.getSize());
//        return localStringBuffer.toString();
//    }
//
//    public static void checkCortegeSpriteLikeRoleSpriteRes(CortegeSprite paramCortegeSprite, boolean paramBoolean, int paramInt) {
//        Object localObject = StringUtils.getPngName(paramCortegeSprite.getSex(), paramCortegeSprite.getRoletype(), paramCortegeSprite.getLvl());
//        String[] arrayOfString;
//        if (!(paramBoolean)) {
//            arrayOfString = new String[3];
//            arrayOfString[0] = paramCortegeSprite.getSmallHeader();
//            arrayOfString[1] = localObject + "_b.hf";
//            arrayOfString[2] = "role/" + paramCortegeSprite.getBaseRoletype() + "_" + paramCortegeSprite.getArmLevel() + "_arm.hf";
//            localObject = new String[2];
//            localObject[0] = StringUtils.getDataName(paramCortegeSprite.getSex(), paramCortegeSprite.getBaseRoletype(), GameLogic.actionName[0]);
//            localObject[1] = StringUtils.getDataName(paramCortegeSprite.getSex(), paramCortegeSprite.getBaseRoletype(), GameLogic.actionName[1]);
//        } else {
//            if (paramCortegeSprite.getBaseRoletype() != 3) {
//                arrayOfString = new String[4];
//                arrayOfString[0] = paramCortegeSprite.getSmallHeader();
//                arrayOfString[1] = localObject + "_b.hf";
//                arrayOfString[2] = "role/" + paramCortegeSprite.getBaseRoletype() + "_" + paramCortegeSprite.getArmLevel() + "_arm.hf";
//                arrayOfString[3] = localObject + "_att.hf";
//            } else {
//                arrayOfString = new String[3];
//                arrayOfString[0] = paramCortegeSprite.getSmallHeader();
//                arrayOfString[1] = localObject + "_b.hf";
//                arrayOfString[2] = "role/" + paramCortegeSprite.getBaseRoletype() + "_" + paramCortegeSprite.getArmLevel() + "_arm.hf";
//            }
//            if (paramCortegeSprite.getBaseRoletype() != 2) {
//                localObject = new String[7];
//                localObject[0] = StringUtils.getDataName(paramCortegeSprite.getSex(), paramCortegeSprite.getBaseRoletype(), GameLogic.actionName[0]);
//                localObject[1] = StringUtils.getDataName(paramCortegeSprite.getSex(), paramCortegeSprite.getBaseRoletype(), GameLogic.actionName[1]);
//                localObject[2] = StringUtils.getDataName(paramCortegeSprite.getSex(), paramCortegeSprite.getBaseRoletype(), GameLogic.actionName[2]);
//                localObject[3] = StringUtils.getDataName(paramCortegeSprite.getSex(), paramCortegeSprite.getBaseRoletype(), GameLogic.actionName[3]);
//                localObject[4] = StringUtils.getDataName(paramCortegeSprite.getSex(), paramCortegeSprite.getBaseRoletype(), GameLogic.actionName[4]);
//                localObject[5] = StringUtils.getDataName(paramCortegeSprite.getSex(), paramCortegeSprite.getBaseRoletype(), GameLogic.actionName[5]);
//                localObject[6] = StringUtils.getDataName(paramCortegeSprite.getSex(), paramCortegeSprite.getBaseRoletype(), GameLogic.actionName[7]);
//            } else {
//                localObject = new String[8];
//                localObject[0] = StringUtils.getDataName(paramCortegeSprite.getSex(), paramCortegeSprite.getBaseRoletype(), GameLogic.actionName[0]);
//                localObject[1] = StringUtils.getDataName(paramCortegeSprite.getSex(), paramCortegeSprite.getBaseRoletype(), GameLogic.actionName[1]);
//                localObject[2] = StringUtils.getDataName(paramCortegeSprite.getSex(), paramCortegeSprite.getBaseRoletype(), GameLogic.actionName[2]);
//                localObject[3] = StringUtils.getDataName(paramCortegeSprite.getSex(), paramCortegeSprite.getBaseRoletype(), GameLogic.actionName[3]);
//                localObject[4] = StringUtils.getDataName(paramCortegeSprite.getSex(), paramCortegeSprite.getBaseRoletype(), GameLogic.actionName[4]);
//                localObject[5] = StringUtils.getDataName(paramCortegeSprite.getSex(), paramCortegeSprite.getBaseRoletype(), GameLogic.actionName[5]);
//                localObject[6] = StringUtils.getDataName(paramCortegeSprite.getSex(), paramCortegeSprite.getBaseRoletype(), GameLogic.actionName[7]);
//                localObject[7] = StringUtils.getDataName(paramCortegeSprite.getSex(), paramCortegeSprite.getBaseRoletype(), GameLogic.actionName[8]);
//            }
//        }
//        doCheck(arrayOfString, localObject, paramInt);
//    }
//
//    public static void checkHorseSpriteRes(HorseSprite paramHorseSprite, int paramInt) {
//        String[] arrayOfString1 = new String[1];
//        arrayOfString1[0] = "role/h_" + paramHorseSprite.getShowType() + ".hf";
//        String[] arrayOfString2 = new String[1];
//        arrayOfString2[0] = "role/h_1_s";
//        if ((paramHorseSprite.getType() > 1) && (paramHorseSprite.getType() < 6)) {
//            arrayOfString2 = new String[1];
//            arrayOfString2[0] = "role/h_2_s";
//        }
//        doCheck(arrayOfString1, arrayOfString2, paramInt);
//    }
//
//    private static String checkHumanBin(String paramString, int paramInt) {
//        Object localObject;
//        if ((getSpriteCellMatrix(paramString.substring(1 + paramString.indexOf("/")) + ".bin") == null) || (getSpriteIndexMatrix(paramString.substring(1 + paramString.indexOf("/")) + "_i.bin") == null))
//            if (!(checkIn(spriteBinInPackage, paramString.substring(1 + paramString.indexOf("/")) + ".bin"))) {
//                int i = checkIn(spriteBinNameListInRms, paramString.substring(1 + paramString.indexOf("/")));
//                if (i <= -1) {
//                    for (int j = 0; j < spriteSort.length; ++j) {
//                        for (i = 0; ; ++i) {
//                            if (i >= spriteSort[j].length)
//                                break label227;
//                            if (paramString.indexOf(spriteSort[j][i]) >= 0)
//                                break;
//                        }
//                        if ((paramInt != 3) && (paramInt != 2)) {
//                            localObject = "net_" + spriteRecordKey[j];
//                            break label409:
//                        }
//                        localObject = "netCache";
//                        label227:
//                        break label409:
//                    }
//                    if ((paramInt != 3) && (paramInt != 2))
//                        localObject = "net_" + spriteRecordKey[(-1 + spriteRecordKey.length)];
//                    else
//                        localObject = "netCache";
//                } else if ((paramInt != 3) && (paramInt != 2)) {
//                    if (paramInt != 1)
//                        localObject = "rms_" + spriteRecordKey[localObject];
//                    else
//                        localObject = "delay_rms_" + spriteRecordKey[localObject];
//                } else {
//                    localObject = "netCache";
//                }
//            } else {
//                localObject = getResources().readByteData(paramString + ".bin");
//                getSpriteBin(paramString.substring(1 + paramString.indexOf("/")), localObject);
//                localObject = "local";
//            }
//        else
//            localObject = "cache";
//        label409:
//        return ((String) localObject);
//    }
//
//    private static String checkHumanImage(String paramString, int paramInt) {
//        Object localObject;
//        if ((Image) spriteImageCache.get(paramString) == null)
//            if (!(checkIn(spritePicInPackage, paramString.substring(1 + paramString.indexOf("/"))))) {
//                int i = checkIn(spriteImgNameListInRms, paramString.substring(1 + paramString.indexOf("/")));
//                if (i <= -1) {
//                    for (int j = 0; j < spriteSort.length; ++j) {
//                        for (i = 0; ; ++i) {
//                            if (i >= spriteSort[j].length)
//                                break label145;
//                            if (paramString.indexOf(spriteSort[j][i]) >= 0)
//                                break;
//                        }
//                        if ((paramInt != 3) && (paramInt != 2)) {
//                            localObject = "net_" + spriteRecordKey[j];
//                            break label302:
//                        }
//                        localObject = "netCache";
//                        label145:
//                        break label302:
//                    }
//                    if ((paramInt != 3) && (paramInt != 2))
//                        localObject = "net_" + spriteRecordKey[(-1 + spriteRecordKey.length)];
//                    else
//                        localObject = "netCache";
//                } else if ((paramInt != 3) && (paramInt != 2)) {
//                    if (paramInt != 1)
//                        localObject = "rms_" + spriteRecordKey[localObject];
//                    else
//                        localObject = "delay_rms_" + spriteRecordKey[localObject];
//                } else {
//                    localObject = "netCache";
//                }
//            } else {
//                localObject = zoomImage(loadImageCode(paramString));
//                if (localObject != null)
//                    spriteImageCache.add(paramString, localObject);
//                localObject = "local";
//            }
//        else
//            localObject = "cache";
//        label302:
//        return ((String) localObject);
//    }
//
//    private static int checkIn(String[][] paramArrayOfString, String paramString) {
//        int j;
//        if (paramString != null) {
//            j = 0;
//            if (j < paramArrayOfString.length) {
//                if (paramArrayOfString[j] != null) ;
//                for (int i = 0; ; ++i) {
//                    if (i >= paramArrayOfString[j].length)
//                        ++j;
//                    if ((paramArrayOfString[j][i] != null) && (paramArrayOfString[j][i].trim().equals(paramString.trim())))
//                        break;
//                }
//            } else {
//                j = -1;
//            }
//        } else {
//            j = -1;
//        }
//        return j;
//    }
//
//    private static boolean checkIn(String[] paramArrayOfString, String paramString) {
//        int i = 0;
//        if (paramString != null) {
//            for (int j = 0; ; ++j) {
//                if (j >= paramArrayOfString.length)
//                    break label44;
//                if ((paramArrayOfString[j] != null) && (paramArrayOfString[j].trim().equals(paramString.trim())))
//                    break;
//            }
//            i = 1;
//        }
//        label44:
//        return i;
//    }
//
//    public static boolean checkMapImage(String[] paramArrayOfString) {
//        if (paramArrayOfString != null) {
//            j = 0;
//            for (int i = 0; ; ++i) {
//                if (i >= paramArrayOfString.length)
//                    break label130;
//                String str = checkMapImg(paramArrayOfString[i]);
//                if ((str.equals("cache")) || (str.equals("local")))
//                    continue;
//                if ((j == 0) && (str.startsWith("net_")))
//                    j = 1;
//                Vector localVector = (Vector) needMapImgTable.get(str);
//                if (localVector != null) {
//                    if (!(localVector.contains(paramArrayOfString[i])))
//                        localVector.addElement(paramArrayOfString[i]);
//                } else {
//                    localVector = new Vector(4, 4);
//                    localVector.addElement(paramArrayOfString[i]);
//                }
//                needMapImgTable.put(str, localVector);
//            }
//        }
//        int j = 0;
//        label130:
//        return j;
//    }
//
//    public static boolean checkMapImageComplete(int paramInt, byte[] paramArrayOfByte) {
//        int l = 2 + 1;
//        int j = paramArrayOfByte[2];
//        int i = l + 1;
//        l = byteToInt(j, paramArrayOfByte[l]);
//        Object localObject = new byte[l];
//        System.arraycopy(paramArrayOfByte, i, localObject, 0, l);
//        (l + 4);
//        localObject = StringUtils.split(StringUtils.BYTEtoUTF(localObject), ",");
//        Vector localVector = new Vector(localObject.length, 4);
//        for (l = 0; l < localObject.length; ++l)
//            localVector.addElement(localObject[l]);
//        if (paramInt != 38) {
//            if (paramInt != 25)
//                break label344;
//            for (k = 0; ; ++k) {
//                if (k >= AliveBuildingSprite.qiImageG.length)
//                    break label344;
//                for (l = 0; l < AliveBuildingSprite.qiImageG[k].length; ++l)
//                    localVector.addElement(AliveBuildingSprite.qiImageG[k][l]);
//            }
//        }
//        for (l = 0; l < AliveBuildingSprite.menImageG.length; ++l)
//            for (k = 0; k < AliveBuildingSprite.menImageG[l].length; ++k)
//                localVector.addElement(AliveBuildingSprite.menImageG[l][k]);
//        for (int k = 0; k < AliveBuildingSprite.menDestroyImageG.length; ++k)
//            for (l = 0; l < AliveBuildingSprite.menDestroyImageG[k].length; ++l)
//                localVector.addElement(AliveBuildingSprite.menDestroyImageG[k][l]);
//        for (l = 0; l < AliveBuildingSprite.qiImageG.length; ++l)
//            for (k = 0; k < AliveBuildingSprite.qiImageG[l].length; ++k)
//                localVector.addElement(AliveBuildingSprite.qiImageG[l][k]);
//        for (l = 0; l < MapNpcSprite.qiangImageG.length; ++l)
//            for (k = 0; k < MapNpcSprite.qiangImageG[l].length; ++k)
//                localVector.addElement(MapNpcSprite.qiangImageG[l][k]);
//        label344:
//        return checkMapImage(StringUtils.vectorToString(localVector));
//    }
//
//    private static String checkMapImg(String paramString) {
//        Object localObject;
//        if (mapElementImageCache.get(paramString) == null)
//            if (!(checkIn(mapPicInPackage, paramString.substring(1 + paramString.indexOf("/")) + ".hf"))) {
//                int i = checkIn(mapImgNameListInRms, paramString.substring(1 + paramString.indexOf("/")));
//                if (i <= -1) {
//                    for (int j = 0; j < mapImgSort.length; ++j) {
//                        for (i = 0; ; ++i) {
//                            if (i >= mapImgSort[j].length)
//                                break label144;
//                            if (paramString.indexOf(mapImgSort[j][i]) >= 0)
//                                break;
//                        }
//                        localObject = "net_" + mapImgRecordKey[j];
//                        label144:
//                        break label256:
//                    }
//                    localObject = "net_" + mapImgRecordKey[(-1 + mapImgRecordKey.length)];
//                } else {
//                    localObject = "rms_" + mapImgRecordKey[localObject];
//                }
//            } else {
//                localObject = zoomImage(loadImageCode(paramString + ".hf"));
//                if (localObject != null)
//                    mapElementImageCache.add(paramString, localObject);
//                localObject = "local";
//            }
//        else
//            localObject = "cache";
//        label256:
//        return ((String) localObject);
//    }
//
//    public static void checkRoleConfig(String[] paramArrayOfString1, String[] paramArrayOfString2, boolean paramBoolean, int paramInt) {
//        Vector localVector1 = new Vector(4, 4);
//        Vector localVector2 = new Vector(4, 4);
//        for (int i = 0; ; ++i) {
//            if ((i >= paramArrayOfString1.length) || ((!(paramBoolean)) && (i > 3))) {
//                doCheck(StringUtils.vectorToString(localVector1), StringUtils.vectorToString(localVector2), paramInt);
//                return;
//            }
//            if (paramArrayOfString1[i].equals(""))
//                continue;
//            if ((i != 2) && (i != 6) && (i != 8)) {
//                localVector1.addElement("role/" + paramArrayOfString1[i] + ".hf");
//            } else if (i != 2) {
//                if (i != 6) {
//                    if ((i != 8) || (!(hasAction(paramArrayOfString2, GameLogic.actionName[6]))))
//                        continue;
//                    localVector2.addElement("role/" + paramArrayOfString1[2] + "_" + GameLogic.actionName[6]);
//                } else {
//                    if (hasAction(paramArrayOfString2, GameLogic.actionName[2]))
//                        localVector2.addElement("role/" + paramArrayOfString1[2] + "_" + GameLogic.actionName[2]);
//                    if (hasAction(paramArrayOfString2, GameLogic.actionName[3]))
//                        localVector2.addElement("role/" + paramArrayOfString1[2] + "_" + GameLogic.actionName[3]);
//                    if (hasAction(paramArrayOfString2, GameLogic.actionName[4]))
//                        localVector2.addElement("role/" + paramArrayOfString1[2] + "_" + GameLogic.actionName[4]);
//                    if (hasAction(paramArrayOfString2, GameLogic.actionName[5]))
//                        localVector2.addElement("role/" + paramArrayOfString1[2] + "_" + GameLogic.actionName[5]);
//                    if (!(hasAction(paramArrayOfString2, GameLogic.actionName[7])))
//                        continue;
//                    localVector2.addElement("role/" + paramArrayOfString1[2] + "_" + GameLogic.actionName[7]);
//                }
//            } else {
//                if (hasAction(paramArrayOfString2, GameLogic.actionName[0]))
//                    localVector2.addElement("role/" + paramArrayOfString1[2] + "_" + GameLogic.actionName[0]);
//                if (!(hasAction(paramArrayOfString2, GameLogic.actionName[1])))
//                    continue;
//                localVector2.addElement("role/" + paramArrayOfString1[2] + "_" + GameLogic.actionName[1]);
//            }
//        }
//    }
//
//    public static void checkRoleSpriteRes(RoleSprite paramRoleSprite, boolean paramBoolean, int paramInt) {
//        String str = StringUtils.getPngName(paramRoleSprite.getSex(), paramRoleSprite.getRoletype(), paramRoleSprite.getLvl());
//        int i = paramRoleSprite.getHorse();
//        String[] arrayOfString2 = null;
//        String[] arrayOfString1 = null;
//        if (!(paramBoolean)) {
//            if ((i != 0) && (!(paramRoleSprite.isNotShowHorse()))) {
//                if ((i != 1) && (i != 11) && (i != 10)) {
//                    if ((i <= 1) || (i >= 6)) {
//                        if (i > 5) {
//                            arrayOfString2 = new String[4];
//                            arrayOfString2[0] = paramRoleSprite.getSmallHeader();
//                            arrayOfString2[1] = str + "_b.hf";
//                            arrayOfString2[2] = "role/" + paramRoleSprite.getBaseRoletype() + "_" + paramRoleSprite.getArmingLevel() + "_arm.hf";
//                            arrayOfString2[3] = "role/h_" + paramRoleSprite.getShowHorse() + ".hf";
//                            arrayOfString1 = new String[2];
//                            arrayOfString1[0] = StringUtils.getDataName(paramRoleSprite.getSex(), paramRoleSprite.getBaseRoletype(), GameLogic.actionName[0]) + "_h2";
//                            arrayOfString1[1] = StringUtils.getDataName(paramRoleSprite.getSex(), paramRoleSprite.getBaseRoletype(), GameLogic.actionName[1]) + "_h2";
//                        }
//                    } else {
//                        arrayOfString2 = new String[4];
//                        arrayOfString2[0] = paramRoleSprite.getSmallHeader();
//                        arrayOfString2[1] = str + "_b.hf";
//                        arrayOfString2[2] = "role/" + paramRoleSprite.getBaseRoletype() + "_" + paramRoleSprite.getArmingLevel() + "_arm.hf";
//                        arrayOfString2[3] = "role/h_" + paramRoleSprite.getShowHorse() + ".hf";
//                        arrayOfString1 = new String[2];
//                        arrayOfString1[0] = StringUtils.getDataName(paramRoleSprite.getSex(), paramRoleSprite.getBaseRoletype(), GameLogic.actionName[0]) + "_h1";
//                        arrayOfString1[1] = StringUtils.getDataName(paramRoleSprite.getSex(), paramRoleSprite.getBaseRoletype(), GameLogic.actionName[1]) + "_h1";
//                    }
//                } else {
//                    arrayOfString2 = new String[4];
//                    arrayOfString2[0] = paramRoleSprite.getSmallHeader();
//                    arrayOfString2[1] = str + "_b.hf";
//                    arrayOfString2[2] = "role/" + paramRoleSprite.getBaseRoletype() + "_" + paramRoleSprite.getArmingLevel() + "_arm.hf";
//                    arrayOfString2[3] = "role/h_" + paramRoleSprite.getShowHorse() + ".hf";
//                    arrayOfString1 = new String[2];
//                    arrayOfString1[0] = StringUtils.getDataName(paramRoleSprite.getSex(), paramRoleSprite.getBaseRoletype(), GameLogic.actionName[0]) + "_h";
//                    arrayOfString1[1] = StringUtils.getDataName(paramRoleSprite.getSex(), paramRoleSprite.getBaseRoletype(), GameLogic.actionName[1]) + "_h";
//                }
//            } else {
//                arrayOfString2 = new String[3];
//                arrayOfString2[0] = paramRoleSprite.getSmallHeader();
//                arrayOfString2[1] = str + "_b.hf";
//                arrayOfString2[2] = "role/" + paramRoleSprite.getBaseRoletype() + "_" + paramRoleSprite.getArmingLevel() + "_arm.hf";
//                arrayOfString1 = new String[2];
//                arrayOfString1[0] = StringUtils.getDataName(paramRoleSprite.getSex(), paramRoleSprite.getBaseRoletype(), GameLogic.actionName[0]);
//                arrayOfString1[1] = StringUtils.getDataName(paramRoleSprite.getSex(), paramRoleSprite.getBaseRoletype(), GameLogic.actionName[1]);
//            }
//        } else if ((i != 0) && (!(paramRoleSprite.isNotShowHorse()))) {
//            if ((i != 1) && (i != 11) && (i != 10)) {
//                if ((i <= 1) || (i >= 6)) {
//                    if (i > 5) {
//                        if (paramRoleSprite.getBaseRoletype() != 3) {
//                            arrayOfString2 = new String[5];
//                            arrayOfString2[0] = paramRoleSprite.getSmallHeader();
//                            arrayOfString2[1] = str + "_b.hf";
//                            arrayOfString2[2] = "role/" + paramRoleSprite.getBaseRoletype() + "_" + paramRoleSprite.getArmingLevel() + "_arm.hf";
//                            arrayOfString2[3] = "role/h_" + paramRoleSprite.getShowHorse() + ".hf";
//                            arrayOfString2[4] = str + "_att.hf";
//                        } else {
//                            arrayOfString2 = new String[4];
//                            arrayOfString2[0] = paramRoleSprite.getSmallHeader();
//                            arrayOfString2[1] = str + "_b.hf";
//                            arrayOfString2[2] = "role/" + paramRoleSprite.getBaseRoletype() + "_" + paramRoleSprite.getArmingLevel() + "_arm.hf";
//                            arrayOfString2[3] = "role/h_" + paramRoleSprite.getShowHorse() + ".hf";
//                        }
//                        if (paramRoleSprite.getBaseRoletype() != 2) {
//                            arrayOfString1 = new String[7];
//                            arrayOfString1[0] = StringUtils.getDataName(paramRoleSprite.getSex(), paramRoleSprite.getBaseRoletype(), GameLogic.actionName[0]) + "_h2";
//                            arrayOfString1[1] = StringUtils.getDataName(paramRoleSprite.getSex(), paramRoleSprite.getBaseRoletype(), GameLogic.actionName[1]) + "_h2";
//                            arrayOfString1[2] = StringUtils.getDataName(paramRoleSprite.getSex(), paramRoleSprite.getBaseRoletype(), GameLogic.actionName[2]);
//                            arrayOfString1[3] = StringUtils.getDataName(paramRoleSprite.getSex(), paramRoleSprite.getBaseRoletype(), GameLogic.actionName[3]);
//                            arrayOfString1[4] = StringUtils.getDataName(paramRoleSprite.getSex(), paramRoleSprite.getBaseRoletype(), GameLogic.actionName[4]);
//                            arrayOfString1[5] = StringUtils.getDataName(paramRoleSprite.getSex(), paramRoleSprite.getBaseRoletype(), GameLogic.actionName[5]);
//                            arrayOfString1[6] = StringUtils.getDataName(paramRoleSprite.getSex(), paramRoleSprite.getBaseRoletype(), GameLogic.actionName[7]);
//                        } else {
//                            arrayOfString1 = new String[8];
//                            arrayOfString1[0] = StringUtils.getDataName(paramRoleSprite.getSex(), paramRoleSprite.getBaseRoletype(), GameLogic.actionName[0]) + "_h2";
//                            arrayOfString1[1] = StringUtils.getDataName(paramRoleSprite.getSex(), paramRoleSprite.getBaseRoletype(), GameLogic.actionName[1]) + "_h2";
//                            arrayOfString1[2] = StringUtils.getDataName(paramRoleSprite.getSex(), paramRoleSprite.getBaseRoletype(), GameLogic.actionName[2]);
//                            arrayOfString1[3] = StringUtils.getDataName(paramRoleSprite.getSex(), paramRoleSprite.getBaseRoletype(), GameLogic.actionName[3]);
//                            arrayOfString1[4] = StringUtils.getDataName(paramRoleSprite.getSex(), paramRoleSprite.getBaseRoletype(), GameLogic.actionName[4]);
//                            arrayOfString1[5] = StringUtils.getDataName(paramRoleSprite.getSex(), paramRoleSprite.getBaseRoletype(), GameLogic.actionName[5]);
//                            arrayOfString1[6] = StringUtils.getDataName(paramRoleSprite.getSex(), paramRoleSprite.getBaseRoletype(), GameLogic.actionName[7]);
//                            arrayOfString1[7] = StringUtils.getDataName(paramRoleSprite.getSex(), paramRoleSprite.getBaseRoletype(), GameLogic.actionName[8]);
//                        }
//                    }
//                } else {
//                    if (paramRoleSprite.getBaseRoletype() != 3) {
//                        arrayOfString2 = new String[5];
//                        arrayOfString2[0] = paramRoleSprite.getSmallHeader();
//                        arrayOfString2[1] = str + "_b.hf";
//                        arrayOfString2[2] = "role/" + paramRoleSprite.getBaseRoletype() + "_" + paramRoleSprite.getArmingLevel() + "_arm.hf";
//                        arrayOfString2[3] = "role/h_" + paramRoleSprite.getShowHorse() + ".hf";
//                        arrayOfString2[4] = str + "_att.hf";
//                    } else {
//                        arrayOfString2 = new String[4];
//                        arrayOfString2[0] = paramRoleSprite.getSmallHeader();
//                        arrayOfString2[1] = str + "_b.hf";
//                        arrayOfString2[2] = "role/" + paramRoleSprite.getBaseRoletype() + "_" + paramRoleSprite.getArmingLevel() + "_arm.hf";
//                        arrayOfString2[3] = "role/h_" + paramRoleSprite.getShowHorse() + ".hf";
//                    }
//                    if (paramRoleSprite.getBaseRoletype() != 2) {
//                        arrayOfString1 = new String[7];
//                        arrayOfString1[0] = StringUtils.getDataName(paramRoleSprite.getSex(), paramRoleSprite.getBaseRoletype(), GameLogic.actionName[0]) + "_h1";
//                        arrayOfString1[1] = StringUtils.getDataName(paramRoleSprite.getSex(), paramRoleSprite.getBaseRoletype(), GameLogic.actionName[1]) + "_h1";
//                        arrayOfString1[2] = StringUtils.getDataName(paramRoleSprite.getSex(), paramRoleSprite.getBaseRoletype(), GameLogic.actionName[2]);
//                        arrayOfString1[3] = StringUtils.getDataName(paramRoleSprite.getSex(), paramRoleSprite.getBaseRoletype(), GameLogic.actionName[3]);
//                        arrayOfString1[4] = StringUtils.getDataName(paramRoleSprite.getSex(), paramRoleSprite.getBaseRoletype(), GameLogic.actionName[4]);
//                        arrayOfString1[5] = StringUtils.getDataName(paramRoleSprite.getSex(), paramRoleSprite.getBaseRoletype(), GameLogic.actionName[5]);
//                        arrayOfString1[6] = StringUtils.getDataName(paramRoleSprite.getSex(), paramRoleSprite.getBaseRoletype(), GameLogic.actionName[7]);
//                    } else {
//                        arrayOfString1 = new String[8];
//                        arrayOfString1[0] = StringUtils.getDataName(paramRoleSprite.getSex(), paramRoleSprite.getBaseRoletype(), GameLogic.actionName[0]) + "_h1";
//                        arrayOfString1[1] = StringUtils.getDataName(paramRoleSprite.getSex(), paramRoleSprite.getBaseRoletype(), GameLogic.actionName[1]) + "_h1";
//                        arrayOfString1[2] = StringUtils.getDataName(paramRoleSprite.getSex(), paramRoleSprite.getBaseRoletype(), GameLogic.actionName[2]);
//                        arrayOfString1[3] = StringUtils.getDataName(paramRoleSprite.getSex(), paramRoleSprite.getBaseRoletype(), GameLogic.actionName[3]);
//                        arrayOfString1[4] = StringUtils.getDataName(paramRoleSprite.getSex(), paramRoleSprite.getBaseRoletype(), GameLogic.actionName[4]);
//                        arrayOfString1[5] = StringUtils.getDataName(paramRoleSprite.getSex(), paramRoleSprite.getBaseRoletype(), GameLogic.actionName[5]);
//                        arrayOfString1[6] = StringUtils.getDataName(paramRoleSprite.getSex(), paramRoleSprite.getBaseRoletype(), GameLogic.actionName[7]);
//                        arrayOfString1[7] = StringUtils.getDataName(paramRoleSprite.getSex(), paramRoleSprite.getBaseRoletype(), GameLogic.actionName[8]);
//                    }
//                }
//            } else {
//                if (paramRoleSprite.getBaseRoletype() != 3) {
//                    arrayOfString2 = new String[5];
//                    arrayOfString2[0] = paramRoleSprite.getSmallHeader();
//                    arrayOfString2[1] = str + "_b.hf";
//                    arrayOfString2[2] = "role/" + paramRoleSprite.getBaseRoletype() + "_" + paramRoleSprite.getArmingLevel() + "_arm.hf";
//                    arrayOfString2[3] = "role/h_" + paramRoleSprite.getShowHorse() + ".hf";
//                    arrayOfString2[4] = str + "_att.hf";
//                } else {
//                    arrayOfString2 = new String[4];
//                    arrayOfString2[0] = paramRoleSprite.getSmallHeader();
//                    arrayOfString2[1] = str + "_b.hf";
//                    arrayOfString2[2] = "role/" + paramRoleSprite.getBaseRoletype() + "_" + paramRoleSprite.getArmingLevel() + "_arm.hf";
//                    arrayOfString2[3] = "role/h_" + paramRoleSprite.getShowHorse() + ".hf";
//                }
//                if (paramRoleSprite.getBaseRoletype() != 2) {
//                    arrayOfString1 = new String[7];
//                    arrayOfString1[0] = StringUtils.getDataName(paramRoleSprite.getSex(), paramRoleSprite.getBaseRoletype(), GameLogic.actionName[0]) + "_h";
//                    arrayOfString1[1] = StringUtils.getDataName(paramRoleSprite.getSex(), paramRoleSprite.getBaseRoletype(), GameLogic.actionName[1]) + "_h";
//                    arrayOfString1[2] = StringUtils.getDataName(paramRoleSprite.getSex(), paramRoleSprite.getBaseRoletype(), GameLogic.actionName[2]);
//                    arrayOfString1[3] = StringUtils.getDataName(paramRoleSprite.getSex(), paramRoleSprite.getBaseRoletype(), GameLogic.actionName[3]);
//                    arrayOfString1[4] = StringUtils.getDataName(paramRoleSprite.getSex(), paramRoleSprite.getBaseRoletype(), GameLogic.actionName[4]);
//                    arrayOfString1[5] = StringUtils.getDataName(paramRoleSprite.getSex(), paramRoleSprite.getBaseRoletype(), GameLogic.actionName[5]);
//                    arrayOfString1[6] = StringUtils.getDataName(paramRoleSprite.getSex(), paramRoleSprite.getBaseRoletype(), GameLogic.actionName[7]);
//                } else {
//                    arrayOfString1 = new String[8];
//                    arrayOfString1[0] = StringUtils.getDataName(paramRoleSprite.getSex(), paramRoleSprite.getBaseRoletype(), GameLogic.actionName[0]) + "_h";
//                    arrayOfString1[1] = StringUtils.getDataName(paramRoleSprite.getSex(), paramRoleSprite.getBaseRoletype(), GameLogic.actionName[1]) + "_h";
//                    arrayOfString1[2] = StringUtils.getDataName(paramRoleSprite.getSex(), paramRoleSprite.getBaseRoletype(), GameLogic.actionName[2]);
//                    arrayOfString1[3] = StringUtils.getDataName(paramRoleSprite.getSex(), paramRoleSprite.getBaseRoletype(), GameLogic.actionName[3]);
//                    arrayOfString1[4] = StringUtils.getDataName(paramRoleSprite.getSex(), paramRoleSprite.getBaseRoletype(), GameLogic.actionName[4]);
//                    arrayOfString1[5] = StringUtils.getDataName(paramRoleSprite.getSex(), paramRoleSprite.getBaseRoletype(), GameLogic.actionName[5]);
//                    arrayOfString1[6] = StringUtils.getDataName(paramRoleSprite.getSex(), paramRoleSprite.getBaseRoletype(), GameLogic.actionName[7]);
//                    arrayOfString1[7] = StringUtils.getDataName(paramRoleSprite.getSex(), paramRoleSprite.getBaseRoletype(), GameLogic.actionName[8]);
//                }
//            }
//        } else {
//            if (paramRoleSprite.getBaseRoletype() != 3) {
//                arrayOfString2 = new String[4];
//                arrayOfString2[0] = paramRoleSprite.getSmallHeader();
//                arrayOfString2[1] = str + "_b.hf";
//                arrayOfString2[2] = "role/" + paramRoleSprite.getBaseRoletype() + "_" + paramRoleSprite.getArmingLevel() + "_arm.hf";
//                arrayOfString2[3] = str + "_att.hf";
//            } else {
//                arrayOfString2 = new String[3];
//                arrayOfString2[0] = paramRoleSprite.getSmallHeader();
//                arrayOfString2[1] = str + "_b.hf";
//                arrayOfString2[2] = "role/" + paramRoleSprite.getBaseRoletype() + "_" + paramRoleSprite.getArmingLevel() + "_arm.hf";
//            }
//            if (paramRoleSprite.getBaseRoletype() != 2) {
//                arrayOfString1 = new String[7];
//                arrayOfString1[0] = StringUtils.getDataName(paramRoleSprite.getSex(), paramRoleSprite.getBaseRoletype(), GameLogic.actionName[0]);
//                arrayOfString1[1] = StringUtils.getDataName(paramRoleSprite.getSex(), paramRoleSprite.getBaseRoletype(), GameLogic.actionName[1]);
//                arrayOfString1[2] = StringUtils.getDataName(paramRoleSprite.getSex(), paramRoleSprite.getBaseRoletype(), GameLogic.actionName[2]);
//                arrayOfString1[3] = StringUtils.getDataName(paramRoleSprite.getSex(), paramRoleSprite.getBaseRoletype(), GameLogic.actionName[3]);
//                arrayOfString1[4] = StringUtils.getDataName(paramRoleSprite.getSex(), paramRoleSprite.getBaseRoletype(), GameLogic.actionName[4]);
//                arrayOfString1[5] = StringUtils.getDataName(paramRoleSprite.getSex(), paramRoleSprite.getBaseRoletype(), GameLogic.actionName[5]);
//                arrayOfString1[6] = StringUtils.getDataName(paramRoleSprite.getSex(), paramRoleSprite.getBaseRoletype(), GameLogic.actionName[7]);
//            } else {
//                arrayOfString1 = new String[8];
//                arrayOfString1[0] = StringUtils.getDataName(paramRoleSprite.getSex(), paramRoleSprite.getBaseRoletype(), GameLogic.actionName[0]);
//                arrayOfString1[1] = StringUtils.getDataName(paramRoleSprite.getSex(), paramRoleSprite.getBaseRoletype(), GameLogic.actionName[1]);
//                arrayOfString1[2] = StringUtils.getDataName(paramRoleSprite.getSex(), paramRoleSprite.getBaseRoletype(), GameLogic.actionName[2]);
//                arrayOfString1[3] = StringUtils.getDataName(paramRoleSprite.getSex(), paramRoleSprite.getBaseRoletype(), GameLogic.actionName[3]);
//                arrayOfString1[4] = StringUtils.getDataName(paramRoleSprite.getSex(), paramRoleSprite.getBaseRoletype(), GameLogic.actionName[4]);
//                arrayOfString1[5] = StringUtils.getDataName(paramRoleSprite.getSex(), paramRoleSprite.getBaseRoletype(), GameLogic.actionName[5]);
//                arrayOfString1[6] = StringUtils.getDataName(paramRoleSprite.getSex(), paramRoleSprite.getBaseRoletype(), GameLogic.actionName[7]);
//                arrayOfString1[7] = StringUtils.getDataName(paramRoleSprite.getSex(), paramRoleSprite.getBaseRoletype(), GameLogic.actionName[8]);
//            }
//        }
//        doCheck(arrayOfString2, arrayOfString1, paramInt);
//    }
//
//    public static void checkSmallHeadRes(String[] paramArrayOfString) {
//        doCheck(paramArrayOfString, null, 2);
//    }
//
//    public static void cleanMatrixCache() {
//        spriteMatrixCache.clear();
//    }
//
//    public static void cleanMatrixCache(String paramString) {
//        spriteMatrixCache.remove(paramString);
//    }
//
//    public static void cleanMatrixTempCache() {
//        spriteMatrixCache.clearTemp();
//    }
//
//    public static void cleanSpriteImageCache() {
//        spriteImageCache.clear();
//    }
//
//    public static void cleanSpriteImageCache(String paramString) {
//        spriteImageCache.remove(paramString);
//    }
//
//    public static void cleanSpriteImageTempCache() {
//        spriteImageCache.clearTemp();
//    }
//
//    public static void cleanStageTempImageCache(String paramString) {
//        stageTempImageCahce.remove(paramString);
//    }
//
//    public static void clearMapElementImage() {
//        mapElementImageCache.clear();
//    }
//
//    public static void clearMapStageImage() {
//        mapStageImageCahce.clear();
//    }
//
//    public static void clearRHeadImage() {
//        rHeadImageCache.clear();
//    }
//
//    public static void clearStageTempImage() {
//        stageTempImageCahce.clear();
//    }
//
//    private static void doCheck(String[] paramArrayOfString1, String[] paramArrayOfString2, int paramInt) {
//        Object localObject1;
//        Object localObject2;
//        if (paramArrayOfString1 != null)
//            for (i = 0; i < paramArrayOfString1.length; ++i) {
//                localObject1 = checkHumanImage(paramArrayOfString1[i], paramInt);
//                if ((((String) localObject1).equals("cache")) || (((String) localObject1).equals("local")))
//                    continue;
//                if (paramInt != 3) {
//                    if (paramInt != 2) {
//                        if (((String) localObject1).startsWith("net_"))
//                            sanguo.stage.WaitStage.waitGetImage = true;
//                        localObject2 = (Vector) needSpriteImgTable.get(localObject1);
//                        if (localObject2 == null) {
//                            localObject2 = new Vector(4, 4);
//                            needSpriteImgTable.put(localObject1, localObject2);
//                        }
//                        if (((Vector) localObject2).contains(paramArrayOfString1[i]))
//                            continue;
//                        ((Vector) localObject2).addElement(paramArrayOfString1[i]);
//                        needSpriteImgTable.put(localObject1, localObject2);
//                    } else {
//                        localObject1 = paramArrayOfString1[i].substring(1 + paramArrayOfString1[i].indexOf("/"));
//                        onlyNetSpriteImageNameTable.add(localObject1, "cache");
//                        netGetImageName.addElement(localObject1);
//                    }
//                } else {
//                    localObject1 = paramArrayOfString1[i].substring(1 + paramArrayOfString1[i].indexOf("/"));
//                    onlyNetSpriteImageNameTable.add(localObject1, "tempCache");
//                    netGetImageName.addElement(localObject1);
//                }
//            }
//        if (paramArrayOfString2 == null)
//            return;
//        for (int i = 0; i < paramArrayOfString2.length; ++i) {
//            localObject2 = checkHumanBin(paramArrayOfString2[i], paramInt);
//            if ((((String) localObject2).equals("cache")) || (((String) localObject2).equals("local")))
//                continue;
//            if (paramInt != 3) {
//                if (paramInt != 2) {
//                    if (((String) localObject2).startsWith("net_"))
//                        sanguo.stage.WaitStage.waitGetBin = true;
//                    localObject1 = (Vector) needSpriteBinTable.get(localObject2);
//                    if (localObject1 == null) {
//                        localObject1 = new Vector(4, 4);
//                        needSpriteBinTable.put(localObject2, localObject1);
//                    }
//                    if (((Vector) localObject1).contains(paramArrayOfString2[i]))
//                        continue;
//                    ((Vector) localObject1).addElement(paramArrayOfString2[i]);
//                    needSpriteBinTable.put(localObject2, localObject1);
//                } else {
//                    localObject1 = paramArrayOfString2[i].substring(1 + paramArrayOfString2[i].indexOf("/"));
//                    onlyNetSpriteBinNameTable.add(localObject1, "cache");
//                    netGetBinName.addElement(localObject1);
//                }
//            } else {
//                localObject1 = paramArrayOfString2[i].substring(1 + paramArrayOfString2[i].indexOf("/"));
//                onlyNetSpriteBinNameTable.add(localObject1, "tempCache");
//                netGetBinName.addElement(localObject1);
//            }
//        }
//    }
//
//    public static void doHasRealHeadInRmsCache(String paramString) {
//        realHeadHasCache.add(paramString, "2");
//    }
//
//    public static final void drawBlock(Graphics paramGraphics, int paramInt1, int paramInt2, int paramInt3, int paramInt4, String paramString) {
//        drawBlock(paramGraphics, paramInt1, paramInt2, paramInt3, paramInt4, paramString, false);
//    }
//
//    public static final void drawBlock(Graphics paramGraphics, int paramInt1, int paramInt2, int paramInt3, int paramInt4, String paramString, boolean paramBoolean) {
//        Image localImage = getStageTempImage("r/" + paramString + ".hf");
//        if ((paramInt3 != localImage.getWidth()) || (paramInt4 != localImage.getHeight())) {
//            int i;
//            if (paramInt3 % localImage.getWidth() != 0)
//                i = 1 + paramInt3 / localImage.getWidth();
//            else
//                i = paramInt3 / localImage.getWidth();
//            if (paramInt4 % localImage.getHeight() != 0)
//                j = 1 + paramInt4 / localImage.getHeight();
//            else
//                j = paramInt4 / localImage.getHeight();
//            int[] arrayOfInt = new int[4];
//            arrayOfInt[0] = paramGraphics.getClipX();
//            arrayOfInt[1] = paramGraphics.getClipY();
//            arrayOfInt[2] = paramGraphics.getClipWidth();
//            arrayOfInt[3] = paramGraphics.getClipHeight();
//            paramGraphics.setClip(paramInt1, paramInt2, paramInt3, paramInt4);
//            if (!(paramBoolean))
//                for (int l = 0; ; ++l) {
//                    if (l >= j)
//                        break label263;
//                    for (int k = 0; k < i; ++k)
//                        paramGraphics.drawImage(localImage, paramInt1 + k * localImage.getWidth(), paramInt2 + l * localImage.getHeight(), 20);
//                }
//            for (int j = 0; j < i; ++j)
//                paramGraphics.drawImage(localImage, paramInt1 + j * localImage.getWidth(), paramInt2, 20);
//            label263:
//            paramGraphics.setClip(arrayOfInt[0], arrayOfInt[1], arrayOfInt[2], arrayOfInt[3]);
//        } else {
//            paramGraphics.drawImage(localImage, paramInt1, paramInt2, 20);
//        }
//    }
//
//    public static final byte[] enCode(byte[] paramArrayOfByte) {
//        int i = paramArrayOfByte.length;
//        byte[] arrayOfByte = new byte[i];
//        for (int j = 0; j < i; ++j)
//            arrayOfByte[j] = (byte) (-1 + -1 * paramArrayOfByte[(i - 1 - j)]);
//        for (int k = 0; k + 10 < i; k += 10)
//            for (int l = 0; l < 5; ++l) {
//                j = arrayOfByte[(k + l)];
//                arrayOfByte[(k + l)] = arrayOfByte[(l + k + 5)];
//                arrayOfByte[(l + k + 5)] = j;
//            }
//        return arrayOfByte;
//    }
//
//    public static String[] getArrayText(String paramString) {
//        return ((String[]) stringResources.get(paramString));
//    }
//
//    public static int getBinSize() {
//        return spriteMatrixCache.getSize();
//    }
//
//    public static Image getIconImage(String paramString) {
//        Object localObject = (Image) stageTempImageCahce.get(paramString);
//        if (localObject != null)
//            localObject = localObject;
//        while (true) {
//            return localObject;
//            new StringBuilder(String.valueOf(paramString)).append(".hf").toString();
//            if (RmsUtils.isContainsIconHead(paramString)) {
//                localObject = RmsUtils.findIconHeadByKey(paramString);
//                if (localObject == null) ;
//            }
//            try {
//                localObject = zoomImage(Image.createImage(localObject, 0, localObject.length));
//                localObject = localObject;
//                label69:
//                if (localObject == null)
//                    break label92;
//                stageTempImageCahce.add(paramString, localObject);
//                label92:
//                localObject = localObject;
//            } catch (Exception localException) {
//                localObject = null;
//                break label69:
//                localObject = (Image) stageTempImageCahce.get("atdefault");
//                if (localObject == null) {
//                    localObject = zoomImage(loadImageCode("icon/atdefault.hf"));
//                    stageTempImageCahce.add("atdefault", localObject);
//                }
//                stageTempImageCahce.add(paramString, localObject);
//                GameLogic.shortIconList.add(paramString, "hf");
//                localObject = localObject;
//            }
//        }
//    }
//
//    public static int getImageSize() {
//        return spriteImageCache.getSize();
//    }
//
//    public static byte[] getMapBin(String paramString) {
//        byte[] arrayOfByte;
//        if (!(checkIn(mapDataInPackage, paramString + ".bin")))
//            arrayOfByte = RmsUtils.findMapBinByKey(paramString);
//        else
//            arrayOfByte = getResources().readByteData(paramString + ".bin");
//        return arrayOfByte;
//    }
//
//    public static Image getMapElementImageInCache(String paramString) {
//        return ((Image) mapElementImageCache.get(paramString));
//    }
//
//    public static int getMapImageSize() {
//        return mapElementImageCache.getSize();
//    }
//
//    public static Image getMapSpriteElementImage(String paramString) {
//        Image localImage = (Image) mapElementImageCache.get(paramString);
//        if (localImage == null) {
//            if (checkIn(mapPicInPackage, paramString + ".hf")) {
//                localImage = zoomImage(loadImageCode(paramString + ".hf"));
//                if (localImage != null)
//                    break label80;
//            }
//            localImage = localImage;
//            break label95:
//            label80:
//            mapElementImageCache.add(paramString, localImage);
//            localImage = localImage;
//        } else {
//            localImage = localImage;
//        }
//        label95:
//        return localImage;
//    }
//
//    public static Image getMapStageImage(String paramString) {
//        return getMapStageImage(paramString, false);
//    }
//
//    public static Image getMapStageImage(String paramString, boolean paramBoolean) {
//        Image localImage = (Image) mapStageImageCahce.get(paramString);
//        if (localImage == null) {
//            localImage = loadImageCode(paramString);
//            if (localImage != null) {
//                if (paramBoolean)
//                    localImage = zoomImage(localImage);
//                mapStageImageCahce.add(paramString, localImage);
//                localImage = localImage;
//            } else {
//                localImage = null;
//            }
//        } else {
//            localImage = localImage;
//        }
//        return localImage;
//    }
//
//    public static String getMenuName(String paramString) {
//        return ((String) menuResources.get(paramString));
//    }
//
//    public static Image getParagraphShowImage(String paramString) {
//        Image localImage;
//        Object localObject;
//        if (paramString.indexOf("role/") < 0) {
//            if (paramString.indexOf("icon/") < 0)
//                localImage = getStageTempImage(paramString, true);
//            else
//                localObject = null;
//        } else {
//            localImage = getSpriteImage(paramString);
//            if (localImage == null) {
//                localImage = zoomImage(loadImageCode("role/d_h.hf"));
//                addSpriteImageCache(paramString, localImage);
//                localObject = paramString.substring(1 + paramString.indexOf("/"));
//                onlyNetSpriteImageNameTable.add(localObject, "cache");
//                netGetImageName.addElement(localObject);
//            }
//            localObject = localImage;
//        }
//        return ((Image) localObject);
//    }
//
//    private ByteArrayOutputStream getPointNextStream()
//            throws Exception {
//        ByteArrayOutputStream localByteArrayOutputStream;
//        if (pointInputStream != null) {
//            localByteArrayOutputStream = new ByteArrayOutputStream();
//            new Vector();
//            while (true) {
//                int i = pointInputStream.read();
//                if (i == -1)
//                    break;
//                if (i == StringUtils.strret)
//                    break label65;
//                localByteArrayOutputStream.write(i);
//            }
//            pointInputStream.close();
//            pointInputStream = null;
//        } else {
//            localByteArrayOutputStream = null;
//        }
//        label65:
//        return localByteArrayOutputStream;
//    }
//
//    public static Resources getResources() {
//        if (res == null)
//            res = new Resources();
//        return res;
//    }
//
//    public static boolean getSpriteBin(String paramString) {
//        boolean bool;
//        if ((getSpriteCellMatrix(paramString + ".bin") == null) || (getSpriteIndexMatrix(paramString + "_i.bin") == null))
//            if (!(paramString.startsWith("role/")))
//                if (!(paramString.startsWith("fightscene/")))
//                    if (!(paramString.startsWith("fs/")))
//                        if (!(paramString.startsWith("ff/")))
//                            bool = getSpriteBin(paramString, getResources().readByteData(paramString + ".bin"));
//                        else
//                            bool = getSpriteBin(paramString, getResources().readByteData(paramString + ".bin"));
//                    else
//                        bool = getSpriteBin(paramString, getResources().readByteData(paramString + ".bin"));
//                else if (!(checkIn(fightSceneInPackage, paramString.substring(1 + paramString.indexOf("/")) + ".bin")))
//                    if (RmsUtils.isContainsFightSceneBin(paramString + ".bin"))
//                        bool = getSpriteBin(paramString, RmsUtils.findFightSceneByKey(paramString + ".bin"));
//                    else
//                        bool = getSpriteBin(paramString, getResources().readByteData(paramString + ".bin"));
//                else if (!(checkIn(spriteBinInPackage, paramString.substring(1 + paramString.indexOf("/")) + ".bin")))
//                    bool = getSpriteBin(paramString, null);
//                else
//                    bool = getSpriteBin(paramString, getResources().readByteData(paramString + ".bin"));
//            else
//                bool = true;
//        return bool;
//    }
//
//    public static boolean getSpriteBin(String paramString, byte[] paramArrayOfByte) {
//        int j;
//        if (paramArrayOfByte != null) {
//            int l = 0 + 1;
//            int i = paramArrayOfByte[0];
//            int k = l + 1;
//            l = byteToInt(i, paramArrayOfByte[l]);
//            byte[] arrayOfByte1 = new byte[l];
//            System.arraycopy(paramArrayOfByte, k, arrayOfByte1, 0, l);
//            k = l + 2;
//            int[] arrayOfInt = new int[l];
//            for (int i2 = 0; i2 < arrayOfByte1.length; i2 += 6) {
//                arrayOfInt[i2] = arrayOfByte1[i2];
//                int i5 = i2 + 1;
//                if (arrayOfByte1[(i2 + 1)] >= 0)
//                    i4 = arrayOfByte1[(i2 + 1)];
//                else
//                    i4 = 256 + arrayOfByte1[(i2 + 1)];
//                arrayOfInt[i5] = (i4 * MyLayer.getZoom());
//                i5 = i2 + 2;
//                if (arrayOfByte1[(i2 + 2)] >= 0)
//                    i4 = arrayOfByte1[(i2 + 2)];
//                else
//                    i4 = 256 + arrayOfByte1[(i2 + 2)];
//                arrayOfInt[i5] = (i4 * MyLayer.getZoom());
//                int i4 = i2 + 3;
//                if (arrayOfByte1[(i2 + 3)] >= 0)
//                    i5 = arrayOfByte1[(i2 + 3)];
//                else
//                    i5 = 256 + arrayOfByte1[(i2 + 3)];
//                arrayOfInt[i4] = (i5 * MyLayer.getZoom());
//                i4 = i2 + 4;
//                if (arrayOfByte1[(i2 + 4)] >= 0)
//                    i5 = arrayOfByte1[(i2 + 4)];
//                else
//                    i5 = 256 + arrayOfByte1[(i2 + 4)];
//                arrayOfInt[i4] = (i5 * MyLayer.getZoom());
//                arrayOfInt[(i2 + 5)] = arrayOfByte1[(i2 + 5)];
//            }
//            spriteMatrixCache.add(paramString + "_i.bin", arrayOfInt);
//            int i1 = k + 1;
//            i2 = paramArrayOfByte[k];
//            k = i1 + 1;
//            i1 = byteToInt(i2, paramArrayOfByte[i1]);
//            byte[] arrayOfByte2 = new byte[i1];
//            System.arraycopy(paramArrayOfByte, k, arrayOfByte2, 0, i1);
//            short[][] arrayOfShort = readSpriteMatrix(arrayOfByte2);
//            for (k = 0; k < arrayOfShort.length; ++k)
//                for (int i3 = 0; i3 < arrayOfShort[k].length; i3 += 3) {
//                    arrayOfShort[k][(i3 + 1)] = (short) (arrayOfShort[k][(i3 + 1)] * MyLayer.getZoom());
//                    arrayOfShort[k][(i3 + 2)] = (short) (arrayOfShort[k][(i3 + 2)] * MyLayer.getZoom());
//                }
//            spriteMatrixCache.add(paramString + ".bin", arrayOfShort);
//            if ((arrayOfShort != null) && (arrayOfByte1 != null))
//                j = 1;
//            else
//                j = 0;
//        } else {
//            j = 0;
//        }
//        return j;
//    }
//
//    public static boolean getSpriteBinTemp(String paramString, byte[] paramArrayOfByte) {
//        int j;
//        if (paramArrayOfByte != null) {
//            int l = 0 + 1;
//            int k = paramArrayOfByte[0];
//            int i = l + 1;
//            int i2 = byteToInt(k, paramArrayOfByte[l]);
//            byte[] arrayOfByte1 = new byte[i2];
//            System.arraycopy(paramArrayOfByte, i, arrayOfByte1, 0, i2);
//            k = i2 + 2;
//            int[] arrayOfInt = new int[i2];
//            for (i2 = 0; i2 < arrayOfByte1.length; i2 += 6) {
//                arrayOfInt[i2] = arrayOfByte1[i2];
//                int i4 = i2 + 1;
//                if (arrayOfByte1[(i2 + 1)] >= 0)
//                    i5 = arrayOfByte1[(i2 + 1)];
//                else
//                    i5 = 256 + arrayOfByte1[(i2 + 1)];
//                arrayOfInt[i4] = (i5 * MyLayer.getZoom());
//                int i5 = i2 + 2;
//                if (arrayOfByte1[(i2 + 2)] >= 0)
//                    i4 = arrayOfByte1[(i2 + 2)];
//                else
//                    i4 = 256 + arrayOfByte1[(i2 + 2)];
//                arrayOfInt[i5] = (i4 * MyLayer.getZoom());
//                i5 = i2 + 3;
//                if (arrayOfByte1[(i2 + 3)] >= 0)
//                    i4 = arrayOfByte1[(i2 + 3)];
//                else
//                    i4 = 256 + arrayOfByte1[(i2 + 3)];
//                arrayOfInt[i5] = (i4 * MyLayer.getZoom());
//                i4 = i2 + 4;
//                if (arrayOfByte1[(i2 + 4)] >= 0)
//                    i5 = arrayOfByte1[(i2 + 4)];
//                else
//                    i5 = 256 + arrayOfByte1[(i2 + 4)];
//                arrayOfInt[i4] = (i5 * MyLayer.getZoom());
//                arrayOfInt[(i2 + 5)] = arrayOfByte1[(i2 + 5)];
//            }
//            spriteMatrixCache.addTemp(paramString + "_i.bin", arrayOfInt);
//            int i1 = k + 1;
//            i2 = paramArrayOfByte[k];
//            k = i1 + 1;
//            i1 = byteToInt(i2, paramArrayOfByte[i1]);
//            byte[] arrayOfByte2 = new byte[i1];
//            System.arraycopy(paramArrayOfByte, k, arrayOfByte2, 0, i1);
//            short[][] arrayOfShort = readSpriteMatrix(arrayOfByte2);
//            for (int i3 = 0; i3 < arrayOfShort.length; ++i3)
//                for (i1 = 0; i1 < arrayOfShort[i3].length; i1 += 3) {
//                    arrayOfShort[i3][(i1 + 1)] = (short) (arrayOfShort[i3][(i1 + 1)] * MyLayer.getZoom());
//                    arrayOfShort[i3][(i1 + 2)] = (short) (arrayOfShort[i3][(i1 + 2)] * MyLayer.getZoom());
//                }
//            spriteMatrixCache.addTemp(paramString + ".bin", arrayOfShort);
//            if ((arrayOfShort != null) && (arrayOfInt != null))
//                j = 1;
//            else
//                j = 0;
//        } else {
//            j = 0;
//        }
//        return j;
//    }
//
//    public static short[][] getSpriteCellMatrix(String paramString) {
//        short[][] arrayOfShort;
//        try {
//            arrayOfShort = (short[][]) spriteMatrixCache.get(paramString);
//            return arrayOfShort;
//        } catch (NullPointerException localNullPointerException) {
//            arrayOfShort = null;
//        }
//    }
//
//    public static Image getSpriteImage(String paramString) {
//        Object localObject2;
//        if ((paramString == null) || (paramString.equals("role/.hf")))
//            localObject2 = null;
//        while (true) {
//            return localObject2;
//            localObject2 = (Image) spriteImageCache.get(paramString);
//            if (localObject2 != null)
//                continue;
//            if (paramString.startsWith("role/")) {
//                if (!(checkIn(spritePicInPackage, paramString.substring(1 + paramString.indexOf("/")))))
//                    continue;
//                localObject2 = zoomImage(loadImageCode(paramString));
//                if (localObject2 == null)
//                    continue;
//                spriteImageCache.add(paramString, localObject2);
//            }
//            Object localObject1;
//            if (paramString.startsWith("fightscene/")) {
//                if (checkIn(fightSceneInPackage, paramString.substring(1 + paramString.indexOf("/")))) {
//                    localObject2 = zoomImage(loadImageCode(paramString));
//                    if (localObject2 == null)
//                        continue;
//                    spriteImageCache.add(paramString, localObject2);
//                }
//                if (!(RmsUtils.isContainsFightSceneBin(paramString)))
//                    continue;
//                localObject1 = RmsUtils.findFightSceneByKey(paramString);
//                if (localObject1 == null)
//                    continue;
//            }
//            try {
//                localObject1 = Image.createImage(localObject1, 0, localObject1.length);
//                localObject2 = localObject1;
//                label168:
//                if (localObject2 != null) ;
//                spriteImageCache.add(paramString, localObject2);
//            } catch (Exception localException) {
//                localObject2 = null;
//                break label168:
//                if (paramString.startsWith("fs/")) {
//                    localObject2 = zoomImage(loadImageCode(paramString));
//                    if (localObject2 != null) ;
//                    spriteImageCache.add(paramString, localObject2);
//                }
//                if (paramString.startsWith("ff/")) {
//                    localObject2 = zoomImage(loadImageCode(paramString));
//                    if (localObject2 != null) ;
//                    spriteImageCache.add(paramString, localObject2);
//                }
//                localObject2 = zoomImage(loadImageCode(paramString));
//            }
//            if (localObject2 == null)
//                continue;
//            spriteImageCache.add(paramString, localObject2);
//        }
//    }
//
//    public static Image getSpriteImageInCache(String paramString) {
//        return ((Image) spriteImageCache.get(paramString));
//    }
//
//    public static int[] getSpriteIndexMatrix(String paramString) {
//        int[] arrayOfInt;
//        try {
//            arrayOfInt = (int[]) spriteMatrixCache.get(paramString);
//            return arrayOfInt;
//        } catch (NullPointerException localNullPointerException) {
//            arrayOfInt = null;
//        }
//    }
//
//    public static Image getStageTempImage(String paramString) {
//        return getStageTempImage(paramString, false);
//    }
//
//    public static Image getStageTempImage(String paramString, boolean paramBoolean) {
//        Object localObject2 = (Image) stageTempImageCahce.get(paramString);
//        if (localObject2 != null) ;
//        for (Object localObject1 = localObject2; ; localObject1 = localObject2) {
//            label17:
//            return localObject1;
//            if (paramString.startsWith("fightscene/"))
//                if (!(checkIn(fightSceneInPackage, paramString.substring(1 + paramString.indexOf("/"))))) ;
//            for (localObject2 = zoomImage(loadImageCode(paramString)); localObject2 == null; localObject2 = zoomImage((Image) localObject2))
//                do {
//                    while (true) {
//                        localObject1 = null;
//                        break label17:
//                        if (!(RmsUtils.isContainsFightSceneBin(paramString)))
//                            continue;
//                        localObject1 = RmsUtils.findFightSceneByKey(paramString);
//                        if (localObject1 == null)
//                            continue;
//                        try {
//                            localObject1 = zoomImage(Image.createImage(localObject1, 0, localObject1.length));
//                            localObject2 = localObject1;
//                        } catch (Exception localException) {
//                            localObject2 = null;
//                        }
//                        continue;
//                        if (paramString.startsWith("fs/")) {
//                            localObject2 = zoomImage(loadImageCode(paramString));
//                            if (localObject2 == null)
//                                continue;
//                            spriteImageCache.add(paramString, localObject2);
//                            localObject1 = localObject2;
//                        }
//                        if (paramString.startsWith("ff/")) {
//                            localObject2 = zoomImage(loadImageCode(paramString));
//                            if (localObject2 == null)
//                                continue;
//                            spriteImageCache.add(paramString, localObject2);
//                            localObject1 = localObject2;
//                        }
//                        if (!(paramString.startsWith("icon/")))
//                            break;
//                        localObject2 = getIconImage(paramString.substring(5));
//                        if (!(paramBoolean))
//                            continue;
//                        localObject2 = zoomImage((Image) localObject2);
//                    }
//                    getResources();
//                    localObject2 = loadImageCode(paramString);
//                }
//                while (!(paramBoolean));
//            stageTempImageCahce.add(paramString, localObject2);
//        }
//    }
//
//    public static String getText(String paramString) {
//        return ((String) stringResources.get(paramString));
//    }
//
//    private ByteArrayOutputStream getWarPointNextStream()
//            throws Exception {
//        ByteArrayOutputStream localByteArrayOutputStream;
//        if (warPointInputStream != null) {
//            localByteArrayOutputStream = new ByteArrayOutputStream();
//            new Vector();
//            while (true) {
//                int i = warPointInputStream.read();
//                if (i == -1)
//                    break;
//                if (i == StringUtils.strret)
//                    break label65;
//                localByteArrayOutputStream.write(i);
//            }
//            warPointInputStream.close();
//            warPointInputStream = null;
//        } else {
//            localByteArrayOutputStream = null;
//        }
//        label65:
//        return localByteArrayOutputStream;
//    }
//
//    private static boolean hasAction(String[] paramArrayOfString, String paramString) {
//        for (int i = 0; ; ++i) {
//            if (i >= paramArrayOfString.length)
//                break label29;
//            if (paramArrayOfString[i].equals(paramString))
//                break;
//        }
//        i = 1;
//        label29:
//        i = 0;
//        return i;
//    }
//
//    public static Image loadImageCode(String paramString) {
//        Image localImage;
//        if (MyLayer.getZoom() == 4) {
//            localImage = readImageCode(StringUtils.replaceAll(paramString, ".hf", "@4.hf"));
//            if (localImage != null)
//                break label104;
//        }
//        if (MyLayer.getZoom() <= 1) {
//            localImage = readImageCode(paramString);
//        } else {
//            localImage = readImageCode(StringUtils.replaceAll(paramString, ".hf", "@2.hf"));
//            if (localImage != null) {
//                if (MyLayer.getZoom() != 2)
//                    localImage.setZoomNum(MyLayer.getZoom() / 2);
//                else
//                    localImage.setIsZoom(true);
//            } else {
//                localImage = readImageCode(paramString);
//                if (localImage != null) {
//                    localImage.setZoomNum(MyLayer.getZoom());
//                    break label109:
//                    label104:
//                    localImage.setIsZoom(true);
//                }
//            }
//        }
//        label109:
//        return localImage;
//    }
//
//    public static Image readImageCode(String paramString) {
//        Image localImage = null;
//        try {
//            InputStream localInputStream = getResources().getRes(paramString);
//            if (localInputStream == null)
//                break label191;
//            byte[] arrayOfByte1 = new byte[localInputStream.available()];
//            localInputStream.read(arrayOfByte1);
//            int i = arrayOfByte1[(-1 + arrayOfByte1.length)];
//            byte[] arrayOfByte2 = new byte[-1 + 20 + arrayOfByte1.length];
//            if (i == 0) {
//                System.arraycopy(hand, 0, arrayOfByte2, 0, 8);
//                System.arraycopy(arrayOfByte1, 0, arrayOfByte2, 8, -1 + arrayOfByte1.length);
//                System.arraycopy(end, 0, arrayOfByte2, -1 + 8 + arrayOfByte1.length, 12);
//            }
//            do {
//                localImage = Image.createImage(arrayOfByte2, 0, arrayOfByte2.length);
//                break label191:
//            }
//            while (i != 1);
//            System.arraycopy(hand, 0, arrayOfByte2, 0, 8);
//            System.arraycopy(arrayOfByte1, -188 + -1 + arrayOfByte1.length, arrayOfByte2, 8, 188);
//            System.arraycopy(arrayOfByte1, 0, arrayOfByte2, 196, -188 + -1 + arrayOfByte1.length);
//            System.arraycopy(end, 0, arrayOfByte2, -1 + 8 + arrayOfByte1.length, 12);
//        } catch (Exception localException) {
//        }
//        label191:
//        return localImage;
//    }
//
//    private String[] readPackage(String paramString) {
//        Object localObject = readByteData(paramString);
//        try {
//            localObject = StringUtils.split(new String(localObject, "UTF-8"), String.valueOf(StringUtils.strret));
//            localObject = localObject;
//            return localObject;
//        } catch (Exception localException) {
//            localObject = null;
//        }
//    }
//
//    private static short[][] readSpriteMatrix(byte[] paramArrayOfByte) {
//        short[][] arrayOfShort = null;
//        int k = 0;
//        while (true) {
//            int j;
//            int i;
//            int l;
//            int i1;
//            try {
//                j = paramArrayOfByte[k];
//                arrayOfShort = new short[j][];
//                i = 0;
//                break label105:
//                k += 1;
//                l = paramArrayOfByte[k];
//                k += 1;
//                i1 = paramArrayOfByte[k];
//                if (i1 < 0)
//                    i1 += 256;
//                l = i1 + (l << 8);
//                arrayOfShort[i] = new short[l];
//                i1 = 0;
//                break label112:
//        [S local[ S = arrayOfShort[i];
//                local[S[i1] = paramArrayOfByte[(++k)];
//                label105:
//                ++i1;
//            } catch (Exception localException) {
//                if (i >= j) ;
//                return arrayOfShort;
//            }
//            label112:
//            if (i1 < l)
//                continue;
//            ++i;
//        }
//    }
//
//    // ERROR //
//    private Hashtable readStrRes(String paramString) {
//        // Byte code:
//        //   0: aload_0
//        //   1: aload_1
//        //   2: invokevirtual 339	util/Resources:readByteData	(Ljava/lang/String;)[B
//        //   5: astore_2
//        //   6: new 65	java/lang/String
//        //   9: dup
//        //   10: aload_2
//        //   11: ldc_w 729
//        //   14: invokespecial 732	java/lang/String:<init>	([BLjava/lang/String;)V
//        //   17: getstatic 187	util/StringUtils:strret	C
//        //   20: invokestatic 735	java/lang/String:valueOf	(C)Ljava/lang/String;
//        //   23: invokestatic 424	util/StringUtils:split	(Ljava/lang/String;Ljava/lang/String;)[Ljava/lang/String;
//        //   26: astore_3
//        //   27: new 387	java/util/Hashtable
//        //   30: dup
//        //   31: aload_3
//        //   32: arraylength
//        //   33: invokespecial 741	java/util/Hashtable:<init>	(I)V
//        //   36: astore 4
//        //   38: iconst_0
//        //   39: istore 5
//        //   41: iload 5
//        //   43: aload_3
//        //   44: arraylength
//        //   45: if_icmplt +9 -> 54
//        //   48: aload 4
//        //   50: pop
//        //   51: goto +108 -> 159
//        //   54: aload_3
//        //   55: iload 5
//        //   57: aaload
//        //   58: astore 7
//        //   60: aload 7
//        //   62: ldc_w 743
//        //   65: invokevirtual 296	java/lang/String:indexOf	(Ljava/lang/String;)I
//        //   68: istore 6
//        //   70: iload 6
//        //   72: bipush 255
//        //   74: if_icmpne +6 -> 80
//        //   77: goto +85 -> 162
//        //   80: aload 7
//        //   82: iconst_0
//        //   83: iload 6
//        //   85: invokevirtual 746	java/lang/String:substring	(II)Ljava/lang/String;
//        //   88: astore_2
//        //   89: aload 7
//        //   91: iload 6
//        //   93: iconst_1
//        //   94: iadd
//        //   95: invokevirtual 300	java/lang/String:substring	(I)Ljava/lang/String;
//        //   98: ldc_w 748
//        //   101: getstatic 187	util/StringUtils:strret	C
//        //   104: invokestatic 735	java/lang/String:valueOf	(C)Ljava/lang/String;
//        //   107: invokestatic 704	util/StringUtils:replaceAll	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;
//        //   110: astore 6
//        //   112: aload 6
//        //   114: ldc_w 750
//        //   117: invokevirtual 296	java/lang/String:indexOf	(Ljava/lang/String;)I
//        //   120: bipush 255
//        //   122: if_icmpne +15 -> 137
//        //   125: aload 4
//        //   127: aload_2
//        //   128: aload 6
//        //   130: invokevirtual 404	java/util/Hashtable:put	(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
//        //   133: pop
//        //   134: goto +28 -> 162
//        //   137: aload 4
//        //   139: aload_2
//        //   140: aload 6
//        //   142: ldc_w 750
//        //   145: invokestatic 424	util/StringUtils:split	(Ljava/lang/String;Ljava/lang/String;)[Ljava/lang/String;
//        //   148: invokevirtual 404	java/util/Hashtable:put	(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
//        //   151: pop
//        //   152: goto +10 -> 162
//        //   155: pop
//        //   156: goto +16 -> 172
//        //   159: aload 4
//        //   161: areturn
//        //   162: iinc 5 1
//        //   165: goto -124 -> 41
//        //   168: pop
//        //   169: aload 4
//        //   171: pop
//        //   172: aconst_null
//        //   173: astore 4
//        //   175: goto -16 -> 159
//        //
//        // Exception table:
//        //   from	to	target	type
//        //   6	38	155	Exception
//        //   41	152	168	Exception
//    }
//
//    public static Image zoomImage(Image paramImage) {
//        if (paramImage != null)
//            if (!(paramImage.getIsZoom())) {
//                if (paramImage.getZoomNum() == 0)
//                    paramImage.setZoomNum(MyLayer.getZoom());
//                if (MyLayer.getZoom() > 1) {
//                    Matrix localMatrix = new Matrix();
//                    localMatrix.postScale(paramImage.getZoomNum(), paramImage.getZoomNum());
//                    paramImage.setBitmap(Bitmap.createBitmap(paramImage.getBitmap(), 0, 0, paramImage.getBitmap().getWidth(), paramImage.getBitmap().getHeight(), localMatrix, true));
//                }
//            } else
//                paramImage = null;
//        return paramImage;
//    }
//
//    public static Image zoomImage(Image paramImage, float paramFloat) {
//        if (paramFloat > 1.0F) {
//            Matrix localMatrix = new Matrix();
//            localMatrix.postScale(paramFloat, paramFloat);
//            paramImage.setBitmap(Bitmap.createBitmap(paramImage.getBitmap(), 0, 0, paramImage.getBitmap().getWidth(), paramImage.getBitmap().getHeight(), localMatrix, true));
//        }
//        return paramImage;
//    }
//
//    public String getCid() {
//        if (this.cid == null) ;
//        label70:
//        String str;
//        try {
//            Object localObject = MainMidlet.instance.getAssets().open("cid.bin");
//            byte[] arrayOfByte = new byte[((InputStream) localObject).available()];
//            ((InputStream) localObject).read(arrayOfByte);
//            ((InputStream) localObject).close();
//            localObject = new String(arrayOfByte);
//            int i = ((String) localObject).indexOf("==");
//            if (i == -1) {
//                this.cid = ((String) localObject);
//                this.exitUrl = null;
//                System.out.println(this.cid);
//                localObject = System.out;
//                if (this.exitUrl != null)
//                    break label145;
//                str = "null";
//                label95:
//                ((PrintStream) localObject).println(str);
//                return this.cid;
//            }
//            this.cid = ((String) localObject).substring(0, str);
//            label145:
//            this.exitUrl = ((String) localObject).substring(str + 2);
//        } catch (Exception localException) {
//            this.cid = "5000";
//            this.exitUrl = null;
//            break label70:
//            str = this.exitUrl;
//            break label95:
//        }
//    }
//
//    public Point getCurrentPoint() {
//        return this.currentPoint;
//    }
//
//    public Point getCurrentWarPoint() {
//        return this.currentWarPoint;
//    }
//
//    public String getExitUrl() {
//        if (this.exitUrl == null) ;
//        Object localObject;
//        try {
//            InputStream localInputStream = MainMidlet.instance.getAssets().open("cid.bin");
//            localObject = new byte[localInputStream.available()];
//            localInputStream.read(localObject);
//            localInputStream.close();
//            localObject = new String(localObject);
//            int i = ((String) localObject).indexOf("==");
//            label70:
//            PrintStream localPrintStream;
//            if (i == -1) {
//                this.cid = ((String) localObject);
//                this.exitUrl = null;
//                System.out.println(this.cid);
//                localPrintStream = System.out;
//                if (this.exitUrl != null)
//                    break label145;
//                localObject = "null";
//                label95:
//                localPrintStream.println((String) localObject);
//                return this.exitUrl;
//            }
//            this.cid = ((String) localObject).substring(0, localPrintStream);
//            label145:
//            this.exitUrl = ((String) localObject).substring(localPrintStream + 2);
//        } catch (Exception localException) {
//            this.cid = "5000";
//            this.exitUrl = null;
//            break label70:
//            localObject = this.exitUrl;
//            break label95:
//        }
//    }
//
//    public Image getRHeadImage(String paramString) {
//        Object localObject;
//        if (paramString != null) {
//            localObject = (String) realHeadHasCache.get(paramString);
//            if ((localObject == null) || (!(((String) localObject).equals("1")))) {
//                localObject = (Image) rHeadImageCache.get(paramString);
//                if (localObject == null) {
//                    realHeadHasCache.add(paramString, "0");
//                    localObject = paramString + ".hf";
//                    if (checkIn(realPicInPackage, (String) localObject)) {
//                        localObject = zoomImage(loadImageCode("head/" + ((String) localObject)));
//                        if (localObject != null)
//                            break label216;
//                    }
//                    if (RmsUtils.isContainsRealHead(paramString)) {
//                        localObject = RmsUtils.findRealHeadByKey(paramString);
//                        if (localObject != null) {
//                            localObject = zoomImage(Image.createImage(localObject, 0, localObject.length));
//                            if (localObject != null)
//                                break label195;
//                        }
//                    }
//                    if (!(((String) realHeadHasCache.get(paramString)).equals("0"))) {
//                        localObject = null;
//                    } else {
//                        realHeadHasCache.add(paramString, "1");
//                        GameLogic.getRequestListener().insertContent(500, paramString);
//                        localObject = null;
//                        break label244:
//                        label195:
//                        rHeadImageCache.add(paramString, localObject);
//                        realHeadHasCache.add(paramString, "2");
//                        break label244:
//                        label216:
//                        rHeadImageCache.add(paramString, localObject);
//                        realHeadHasCache.add(paramString, "2");
//                    }
//                }
//            } else {
//                localObject = null;
//            }
//        } else {
//            localObject = null;
//        }
//        label244:
//        return ((Image) localObject);
//    }
//
//    public InputStream getRes(String paramString) {
//        InputStream localInputStream = null;
//        try {
//            localInputStream = MainMidlet.instance.getAssets().open("res/" + paramString);
//            localInputStream = localInputStream;
//            return localInputStream;
//        } catch (Exception localException) {
//        }
//    }
//
//    public void initRes() {
//        RmsUtils.clearOldData();
//        mapPicInPackage = readPackage("pi.config");
//        mapDataInPackage = readPackage("di.config");
//        spritePicInPackage = readPackage("role/rpi.config");
//        spriteBinInPackage = readPackage("role/rdi.config");
//        realPicInPackage = readPackage("head/rpi.config");
//        spriteImgNameListInRms = RmsUtils.loadNameList("roleImg");
//        spriteBinNameListInRms = RmsUtils.loadNameList("roleBin");
//        mapImgNameListInRms = RmsUtils.loadNameList("mapImg_new");
//        needSpriteImgTable = new Hashtable();
//        needSpriteBinTable = new Hashtable();
//        needMapImgTable = new Hashtable();
//        netSpriteImgTable = new Hashtable();
//        netSpriteBinTable = new Hashtable();
//        netMapImgTable = new Hashtable();
//        onlyNetSpriteImageNameTable = new HashList(4, 4);
//        onlyNetSpriteBinNameTable = new HashList(4, 4);
//        netGetImageName = new Vector(4, 4);
//        netGetBinName = new Vector(4, 4);
//    }
//
//    public void initStrRes() {
//        stringResources = readStrRes("data/intr.bin");
//    }
//
//    public void newPointStrean(String paramString) {
//        try {
//            pointInputStream = new ByteArrayInputStream(paramString.getBytes("UTF-8"));
//            toPointNext();
//            return;
//        } catch (Exception localException) {
//            pointInputStream = null;
//        }
//    }
//
//    public void newWarPointStrean(String paramString) {
//        try {
//            warPointInputStream = new ByteArrayInputStream(paramString.getBytes("UTF-8"));
//            toWarPointNext();
//            return;
//        } catch (Exception localException) {
//            warPointInputStream = null;
//        }
//    }
//
//    public byte[] readByteData(String paramString) {
//        byte[] arrayOfByte;
//        try {
//            InputStream localInputStream = getRes(paramString);
//            arrayOfByte = new byte[localInputStream.available()];
//            localInputStream.read(arrayOfByte);
//            localInputStream.close();
//            arrayOfByte = arrayOfByte;
//            return arrayOfByte;
//        } catch (Exception localException) {
//            arrayOfByte = null;
//        }
//    }
//
//    public void readPointInputStream(String paramString) {
//        pointInputStream = getRes(paramString);
//    }
//
//    public void readWarPointInputStream(String paramString) {
//        warPointInputStream = getRes(paramString);
//    }
//
//    public void toPointNext() {
//        try {
//            ByteArrayOutputStream localByteArrayOutputStream = getPointNextStream();
//            if (localByteArrayOutputStream == null) {
//                this.currentPoint = null;
//                return;
//            }
//            this.currentPoint = new Point(StringUtils.split(new String(localByteArrayOutputStream.toByteArray(), "UTF-8"), ","));
//        } catch (Exception localException) {
//            this.currentPoint = null;
//        }
//    }
//
//    public void toWarPointNext() {
//        try {
//            ByteArrayOutputStream localByteArrayOutputStream = getWarPointNextStream();
//            if (localByteArrayOutputStream == null) {
//                this.currentWarPoint = null;
//                return;
//            }
//            this.currentWarPoint = new Point(StringUtils.split(new String(localByteArrayOutputStream.toByteArray(), "UTF-8"), ","));
//        } catch (Exception localException) {
//            this.currentWarPoint = null;
//        }
//    }
//}
