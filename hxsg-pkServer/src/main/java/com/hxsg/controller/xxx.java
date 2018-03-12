//package com.hxsg.controller;
//
//public class xxx
//{
//    public static void main(String[] args) {
//
//
//        System.out.println(60.0F + 30.0F * 18000 / 30000.0F - 0.0F);
//    }
//
//    public static String getCurSkillEffect(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5)
//    {
//        String str = "";
//        int i = 0;
//        float f1 = 0.0F;
//
//        float f2=0.0F;
//        label316:
//        switch (paramInt1)
//        {
//            case 1:
//                int j = paramInt3 / 3;
//                if (paramInt4 != EFFECTTYPE_COMMAND)
//                    if (paramInt4 != EFFECTTYPE_HUAJING)
//                        break label316;
//                switch (paramInt2)
//                {
//                    default:
//                        break;
//                    case 1:
//                        i = Tools.floatToint(18000.0F + paramInt3 * 7000 / 20000.0F);
//                        break;
//                    case 2:
//                        i = Tools.floatToint(25000.0F + paramInt3 * 7000 / 20000.0F);
//                        break;
//                    case 3:
//                        i = Tools.floatToint(32000.0F + paramInt3 * 7000 / 20000.0F);
//                        break label316:
//                    switch (paramInt2)
//                    {
//                        case 1:
//                            i = Tools.floatToint(j + 600);
//                            break;
//                        case 2:
//                            i = Tools.floatToint(j + 1600);
//                            break;
//                        case 3:
//                            i = Tools.floatToint(j + 2800);
//                            break;
//                        case 4:
//                            i = Tools.floatToint(j + 4200);
//                            break;
//                        case 5:
//                            i = Tools.floatToint(j + 6000);
//                    }
//                }
//                str = String.valueOf(i);
//                break;
//            case 3:
//                if (paramInt4 != EFFECTTYPE_COMMAND)
//                    if (paramInt4 != EFFECTTYPE_HUAJING)
//                        break label565;
//                switch (paramInt2)
//                {
//                    default:
//                        break;
//                    case 1:
//                        i = Tools.floatToint(6000.0F + paramInt3 * 3000 / 20000.0F);
//                        break;
//                    case 2:
//                        i = Tools.floatToint(9000.0F + paramInt3 * 3000 / 20000.0F);
//                        break;
//                    case 3:
//                        i = Tools.floatToint(12000.0F + paramInt3 * 3000 / 20000.0F);
//                        break label565:
//                    switch (paramInt2)
//                    {
//                        case 1:
//                            i = Tools.floatToint(400.0F + paramInt3 * 2500 / 30000.0F);
//                            break;
//                        case 2:
//                            i = Tools.floatToint(900.0F + paramInt3 * 2500 / 30000.0F);
//                            break;
//                        case 3:
//                            i = Tools.floatToint(1100.0F + paramInt3 * 2500 / 30000.0F);
//                            break;
//                        case 4:
//                            i = Tools.floatToint(2000.0F + paramInt3 * 2500 / 30000.0F);
//                            break;
//                        case 5:
//                            i = Tools.floatToint(2500.0F + paramInt3 * 2500 / 30000.0F);
//                    }
//                }
//                str = String.valueOf(i);
//                break;
//            case 5:
//                if (paramInt4 != EFFECTTYPE_COMMAND)
//                    if (paramInt4 != EFFECTTYPE_HUAJING)
//                        break label827;
//                switch (paramInt2)
//                {
//                    default:
//                        break;
//                    case 1:
//                        i = Tools.floatToint(100.0F + 10.0F * paramInt3 / 20000.0F - 0.0F);
//                        break;
//                    case 2:
//                        i = Tools.floatToint(110.0F + 10.0F * paramInt3 / 20000.0F - 0.0F);
//                        break;
//                    case 3:
//                        i = Tools.floatToint(120.0F + 10.0F * paramInt3 / 20000.0F - 0.0F);
//                        break label827:
//                    switch (paramInt2)
//                    {
//                        case 1:
//                            i = Tools.floatToint(60.0F + 30.0F * paramInt3 / 30000.0F - 0.0F);
//                            break;
//                        case 2:
//                            i = Tools.floatToint(60.0F + 30.0F * paramInt3 / 30000.0F - 0.0F);
//                            break;
//                        case 3:
//                            i = Tools.floatToint(60.0F + 30.0F * paramInt3 / 30000.0F - 0.0F);
//                            break;
//                        case 4:
//                            i = Tools.floatToint(60.0F + 30.0F * paramInt3 / 30000.0F - 0.0F);
//                            break;
//                        case 5:
//                            i = Tools.floatToint(60.0F + 30.0F * paramInt3 / 30000.0F - 0.0F);
//                    }
//                }
//                str = String.valueOf(i);
//                break;
//            case 8:
//                if (paramInt4 != EFFECTTYPE_COMMAND)
//                    if (paramInt4 != EFFECTTYPE_HUAJING)
//                        break label1077;
//                switch (paramInt2)
//                {
//                    default:
//                        break;
//                    case 1:
//                        i = Tools.floatToint(6000.0F + paramInt3 * 3000 / 20000.0F);
//                        break;
//                    case 2:
//                        i = Tools.floatToint(9000.0F + paramInt3 * 3000 / 20000.0F);
//                        break;
//                    case 3:
//                        i = Tools.floatToint(12000.0F + paramInt3 * 3000 / 20000.0F);
//                        break label1077:
//                    switch (paramInt2)
//                    {
//                        case 1:
//                            i = Tools.floatToint(400.0F + paramInt3 * 2500 / 30000.0F);
//                            break;
//                        case 2:
//                            i = Tools.floatToint(900.0F + paramInt3 * 2500 / 30000.0F);
//                            break;
//                        case 3:
//                            i = Tools.floatToint(1100.0F + paramInt3 * 2500 / 30000.0F);
//                            break;
//                        case 4:
//                            i = Tools.floatToint(2000.0F + paramInt3 * 2500 / 30000.0F);
//                            break;
//                        case 5:
//                            i = Tools.floatToint(2500.0F + paramInt3 * 2500 / 30000.0F);
//                    }
//                }
//                str = String.valueOf(i);
//                break;
//            case 9:
//                if (paramInt4 != EFFECTTYPE_COMMAND)
//                    if (paramInt4 != EFFECTTYPE_HUAJING)
//                        break label1293;
//                switch (paramInt2)
//                {
//                    default:
//                        break;
//                    case 1:
//                        f2 = 0.47F + 0.03F * paramInt3 / 20000.0F;
//                        break;
//                    case 2:
//                        f2 = 0.5F + 0.04F * paramInt3 / 20000.0F;
//                        break;
//                    case 3:
//                        f2 = 0.54F + 0.04F * paramInt3 / 20000.0F;
//                        break label1293:
//                    switch (paramInt2)
//                    {
//                        case 1:
//                            f2 = 0.17F + 0.2F * paramInt3 / 30000.0F;
//                            break;
//                        case 2:
//                            f2 = 0.19F + 0.2F * paramInt3 / 30000.0F;
//                            break;
//                        case 3:
//                            f2 = 0.21F + 0.2F * paramInt3 / 30000.0F;
//                            break;
//                        case 4:
//                            f2 = 0.23F + 0.2F * paramInt3 / 30000.0F;
//                            break;
//                        case 5:
//                            f2 = 0.25F + 0.2F * paramInt3 / 30000.0F;
//                    }
//                }
//                str = String.valueOf((int)(100.0F * f2)) + "%";
//                break;
//            case 10:
//                if (paramInt4 != EFFECTTYPE_COMMAND)
//                    if (paramInt4 != EFFECTTYPE_HUAJING)
//                        break label1533;
//                switch (paramInt2)
//                {
//                    default:
//                        break;
//                    case 1:
//                        f2 = 0.31F + 0.02F * paramInt3 / 20000.0F;
//                        break;
//                    case 2:
//                        f2 = 0.33F + 0.02F * paramInt3 / 20000.0F;
//                        break;
//                    case 3:
//                        f2 = 0.35F + 0.02F * paramInt3 / 20000.0F;
//                        break label1533:
//                    switch (paramInt2)
//                    {
//                        case 1:
//                            f2 = 0.11F + 0.15F * paramInt3 / 30000.0F;
//                            break;
//                        case 2:
//                            f2 = 0.12F + 0.15F * paramInt3 / 30000.0F;
//                            break;
//                        case 3:
//                            f2 = 0.13F + 0.15F * paramInt3 / 30000.0F;
//                            break;
//                        case 4:
//                            f2 = 0.14F + 0.15F * paramInt3 / 30000.0F;
//                            break;
//                        case 5:
//                            f2 = 0.15F + 0.15F * paramInt3 / 30000.0F;
//                    }
//                }
//                str = String.valueOf((int)(100.0F * f2)) + "%";
//                break;
//            case 11:
//                if (paramInt4 != EFFECTTYPE_COMMAND)
//                    if (paramInt4 != EFFECTTYPE_HUAJING)
//                        break label1828;
//                switch (paramInt2)
//                {
//                    default:
//                        break;
//                    case 1:
//                        f2 = Tools.floatToint(100.0F + 20.0F * paramInt3 / 20000.0F - 0.0F);
//                        break;
//                    case 2:
//                        f2 = Tools.floatToint(120.0F + 10.0F * paramInt3 / 20000.0F - 0.0F);
//                        break;
//                    case 3:
//                        f2 = Tools.floatToint(130.0F + 10.0F * paramInt3 / 20000.0F - 0.0F);
//                        break label1828:
//                    switch (paramInt2)
//                    {
//                        case 1:
//                            f2 = Tools.floatToint(60.0F + 30.0F * paramInt3 / 30000.0F - 0.0F);
//                            break;
//                        case 2:
//                            f2 = Tools.floatToint(60.0F + 30.0F * paramInt3 / 30000.0F - 0.0F);
//                            break;
//                        case 3:
//                            f2 = Tools.floatToint(60.0F + 30.0F * paramInt3 / 30000.0F - 0.0F);
//                            break;
//                        case 4:
//                            f2 = Tools.floatToint(60.0F + 30.0F * paramInt3 / 30000.0F - 0.0F);
//                            break;
//                        case 5:
//                            f2 = Tools.floatToint(60.0F + 30.0F * paramInt3 / 30000.0F - 0.0F);
//                    }
//                }
//                str = (int)f2 + "%";
//                break;
//            case 12:
//                if (paramInt4 != EFFECTTYPE_COMMAND)
//                    if (paramInt4 != EFFECTTYPE_HUAJING)
//                        break label2116;
//                switch (paramInt2)
//                {
//                    default:
//                        break;
//                    case 1:
//                        f2 = Tools.floatToint(100.0F + 20.0F * paramInt3 / 20000.0F - 0.0F);
//                        break;
//                    case 2:
//                        f2 = Tools.floatToint(120.0F + 10.0F * paramInt3 / 20000.0F - 0.0F);
//                        break;
//                    case 3:
//                        f2 = Tools.floatToint(130.0F + 10.0F * paramInt3 / 20000.0F - 0.0F);
//                        break label2116:
//                    switch (paramInt2)
//                    {
//                        case 1:
//                            f2 = Tools.floatToint(60.0F + 30.0F * paramInt3 / 30000.0F - 0.0F);
//                            break;
//                        case 2:
//                            f2 = Tools.floatToint(60.0F + 30.0F * paramInt3 / 30000.0F - 0.0F);
//                            break;
//                        case 3:
//                            f2 = Tools.floatToint(60.0F + 30.0F * paramInt3 / 30000.0F - 0.0F);
//                            break;
//                        case 4:
//                            f2 = Tools.floatToint(60.0F + 30.0F * paramInt3 / 30000.0F - 0.0F);
//                            break;
//                        case 5:
//                            f2 = Tools.floatToint(60.0F + 30.0F * paramInt3 / 30000.0F - 0.0F);
//                    }
//                }
//                str = (int)f2 + "%";
//                break;
//            case 13:
//                if (paramInt4 != EFFECTTYPE_COMMAND)
//                    if (paramInt4 != EFFECTTYPE_HUAJING)
//                        break label2381;
//                switch (paramInt2)
//                {
//                    default:
//                        break;
//                    case 1:
//                        i = Tools.floatToint(14000.0F + paramInt3 * 6000 / 20000.0F);
//                        break;
//                    case 2:
//                        i = Tools.floatToint(20000.0F + paramInt3 * 6000 / 20000.0F);
//                        break;
//                    case 3:
//                        i = Tools.floatToint(26000.0F + paramInt3 * 6000 / 20000.0F);
//                        break label2381:
//                    switch (paramInt2)
//                    {
//                        case 1:
//                            i = Tools.floatToint(600.0F + paramInt3 * 6000 / 30000.0F);
//                            break;
//                        case 2:
//                            i = Tools.floatToint(1600.0F + paramInt3 * 6000 / 30000.0F);
//                            break;
//                        case 3:
//                            i = Tools.floatToint(2800.0F + paramInt3 * 6000 / 30000.0F);
//                            break;
//                        case 4:
//                            i = Tools.floatToint(4200.0F + paramInt3 * 6000 / 30000.0F);
//                            break;
//                        case 5:
//                            i = Tools.floatToint(6000.0F + paramInt3 * 6000 / 30000.0F);
//                    }
//                }
//                str = String.valueOf(i);
//                break;
//            case 14:
//                if (paramInt4 != EFFECTTYPE_COMMAND)
//                    if (paramInt4 != EFFECTTYPE_HUAJING)
//                        break label2629;
//                switch (paramInt2)
//                {
//                    default:
//                        break;
//                    case 1:
//                        i = Tools.floatToint(3000.0F + paramInt3 * 1500 / 30000.0F);
//                        break;
//                    case 2:
//                        i = Tools.floatToint(4500.0F + paramInt3 * 1500 / 30000.0F);
//                        break;
//                    case 3:
//                        i = Tools.floatToint(6000.0F + paramInt3 * 1500 / 30000.0F);
//                        break label2629:
//                    switch (paramInt2)
//                    {
//                        case 1:
//                            i = Tools.floatToint(200.0F + paramInt3 * 1250 / 30000.0F);
//                            break;
//                        case 2:
//                            i = Tools.floatToint(450.0F + paramInt3 * 1250 / 30000.0F);
//                            break;
//                        case 3:
//                            i = Tools.floatToint(550.0F + paramInt3 * 1250 / 30000.0F);
//                            break;
//                        case 4:
//                            i = Tools.floatToint(1000.0F + paramInt3 * 1250 / 30000.0F);
//                            break;
//                        case 5:
//                            i = Tools.floatToint(1250.0F + paramInt3 * 1250 / 30000.0F);
//                    }
//                }
//                str = String.valueOf(i);
//                break;
//            case 15:
//                if (paramInt4 != EFFECTTYPE_COMMAND)
//                    if (paramInt4 != EFFECTTYPE_HUAJING)
//                        break label2862;
//                switch (paramInt2)
//                {
//                    default:
//                        break;
//                    case 1:
//                        f2 = Tools.floatToint(15.0F + 5.0F * paramInt3 / 20000.0F);
//                        break;
//                    case 2:
//                        f2 = Tools.floatToint(20.0F + 5.0F * paramInt3 / 20000.0F);
//                        break;
//                    case 3:
//                        f2 = Tools.floatToint(25.0F + 5.0F * paramInt3 / 20000.0F);
//                        break label2862:
//                    switch (paramInt2)
//                    {
//                        case 1:
//                            f2 = Tools.floatToint(15.0F * paramInt3 / 30000.0F);
//                            break;
//                        case 2:
//                            f2 = Tools.floatToint(15.0F * paramInt3 / 30000.0F);
//                            break;
//                        case 3:
//                            f2 = Tools.floatToint(15.0F * paramInt3 / 30000.0F);
//                            break;
//                        case 4:
//                            f2 = Tools.floatToint(15.0F * paramInt3 / 30000.0F);
//                            break;
//                        case 5:
//                            f2 = Tools.floatToint(15.0F * paramInt3 / 30000.0F);
//                    }
//                }
//                str = (int)f2 + "%";
//                break;
//            case 16:
//                if (paramInt4 != EFFECTTYPE_COMMAND)
//                    if (paramInt4 != EFFECTTYPE_HUAJING)
//                        break label3101;
//                switch (paramInt2)
//                {
//                    default:
//                        break;
//                    case 1:
//                        f2 = Tools.floatToint(65.0F + 5.0F * paramInt3 / 20000.0F);
//                        break;
//                    case 2:
//                        f2 = Tools.floatToint(70.0F + 5.0F * paramInt3 / 20000.0F);
//                        break label3101:
//                    switch (paramInt2)
//                    {
//                        case 1:
//                            f2 = Tools.floatToint(35.0F + 25.0F * paramInt3 / 30000.0F);
//                            break;
//                        case 2:
//                            f2 = Tools.floatToint(35.0F + 25.0F * paramInt3 / 30000.0F);
//                            break;
//                        case 3:
//                            f2 = Tools.floatToint(35.0F + 25.0F * paramInt3 / 30000.0F);
//                            break;
//                        case 4:
//                            f2 = Tools.floatToint(35.0F + 25.0F * paramInt3 / 30000.0F);
//                            break;
//                        case 5:
//                            f2 = Tools.floatToint(35.0F + 25.0F * paramInt3 / 30000.0F);
//                    }
//                }
//                str = (int)f2 + "%";
//                break;
//            case 17:
//                label565: label827: label2116: label2381: if (paramInt4 != EFFECTTYPE_COMMAND)
//                    label1533: if (paramInt4 != EFFECTTYPE_HUAJING)
//                        break label3365;
//                switch (paramInt2)
//                {
//                    default:
//                        break;
//                    case 1:
//                        i = Tools.floatToint(14000.0F + paramInt3 * 6000 / 20000.0F);
//                        break;
//                    case 2:
//                        i = Tools.floatToint(20000.0F + paramInt3 * 6000 / 20000.0F);
//                        break;
//                    case 3:
//                        label1077: label2629: i = Tools.floatToint(26000.0F + paramInt3 * 6000 / 20000.0F);
//                        label1293: label1828: label2862: break label3365:
//                        switch (paramInt2)
//                        {
//                            case 1:
//                                i = Tools.floatToint(600.0F + paramInt3 * 6000 / 30000.0F);
//                                break;
//                            case 2:
//                                i = Tools.floatToint(1600.0F + paramInt3 * 6000 / 30000.0F);
//                                break;
//                            case 3:
//                                i = Tools.floatToint(2800.0F + paramInt3 * 6000 / 30000.0F);
//                                break;
//                            case 4:
//                                i = Tools.floatToint(4200.0F + paramInt3 * 6000 / 30000.0F);
//                                break;
//                            case 5:
//                                label3101: i = Tools.floatToint(6000.0F + paramInt3 * 6000 / 30000.0F);
//                        }
//                }
//                label3365: str = String.valueOf(i);
//            case 2:
//            case 4:
//            case 6:
//            case 7:
//        }
//        return str;
//    }
//}