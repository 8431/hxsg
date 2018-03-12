package ParseMiuiXml;

import org.dom4j.Document;
import org.dom4j.Node;
import org.dom4j.io.SAXReader;
import org.springframework.util.StringUtils;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/6/9 0009.
 */
public class ParseXmlUtil {
    //private static Log logger = LogFactory.getLog(ParseXmlUtil.class);
    public static String getText(List<Node> nodeLi) throws Exception {
        return nodeLi.size() > 0 ? nodeLi.get(0).getText() : null;

    }
  public static void  createQuery00000Quest(String start){
      Document document = null;
      SAXReader reader = null;

      try {
          reader = new SAXReader();
          reader.setValidation(false);
          reader.setFeature("http://apache.org/xml/features/nonvalidating/load-external-dtd", false);
          String filePath = ParseXmlUtil.class.getClassLoader().getResource("1.xml").getPath();
          File f = new File(filePath);
          document = reader.read(f);
          //query0000request
          String cxtjPd = "//cxtj/@pd";
          String cxtjWhere = "//cxtj/@where";
          String property = "//cxtj/property";
          String pdName = getText(document.selectNodes(cxtjPd));
          String cxjgWh = getText(document.selectNodes(cxtjWhere));
          List<Node> node = document.selectNodes(property);
          createQueryVo(pdName, node,start,"Request");
          //query0000response
          String cxjgPd = "//cxjg/@pd";
          String propertyJg = "//cxjg/property";
          String pdNameJg = getText(document.selectNodes(cxjgPd));
          List<Node> nodeJg = document.selectNodes(propertyJg);
          createQueryVo(pdNameJg, nodeJg,start,"Response");



      } catch (Exception e) {
          e.printStackTrace();
      } finally {
          if (reader != null) {
              reader.resetHandlers();
          }
      }

  }

    private static void createQueryVo(String pdName, List<Node> node,String classNameStart,String classNameEnd) throws Exception {
        StringBuffer sbuff = new StringBuffer();
        sbuff.append("package ParseMiuiXml;\n");
        sbuff.append("package com.wondersgroup.shzzjb.shbx.sqyyfw.shywsb.S" + pdName + ";\n");
        sbuff.append("import com.wondersgroup.shzzjb.framework.model.mapping.annotation.CommAreaField;\n");
        sbuff.append("import com.wondersgroup.shzzjb.framework.model.mapping.annotation.NormBeanSupport;\n");
        sbuff.append("import com.wondersgroup.shzzjb.framework.model.mapping.annotation.NormItemField;\n");
        sbuff.append("@CommAreaSupport(pdname = \"" + pdName + "\")\n");
        sbuff.append(" public class "+classNameStart + pdName + classNameEnd+" {\n");
        int num = 30;
        List<String> nameLi=new ArrayList<String>();
        for (Node n : node) {
            //需增加自定义HTml页面
            String name = n.getText();

            String length = getText(n.selectNodes("@length"));
            //默认长度10
            if(StringUtils.isEmpty(length)) length="10";
            System.out.println(length);
            if (StringUtils.isEmpty(name)) {
                throw new RuntimeException("property标签值为空");

            } else {

                sbuff.append(" @CommAreaField(order = " + num + ")\n");
                sbuff.append("@NormItemField(name = \"" + name + "\", length =" + length + ", required = false)\n");
                String var = ChineseCharToEn.getAllFirstLetter(name);
                nameLi.add(var);
                sbuff.append("private String " + var + ";\n");
            }
            num += 10;
        }
        for(String var:nameLi){
            String getVar = change(var);
            sbuff.append("private   String get" + getVar + "(){ \n  return " + var + ";\n}\n");
            sbuff.append("private   void String set" + getVar + "(String " + var + "){\n this." + var + " = " + var + ";\n}\n");
        }


        createFile(classNameStart+ pdName +classNameEnd, sbuff.toString());
    }


    public static void main(String[] args) {
        createQuery00000Quest("quest");



    }

    public static boolean createFile(String fileName, String filecontent) {
        Boolean bool = false;
        String filenameTemp = "E://" + fileName + ".java";//文件路径+名称+文件类型
        File file = new File(filenameTemp);
        try {
            //如果文件不存在，则创建新的文件
            if (!file.exists()) {
                file.createNewFile();
                bool = true;
                System.out.println("success create file,the file is " + filenameTemp);
                //创建文件成功后，写入内容到文件里
                writeFileContent(filenameTemp, filecontent);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return bool;
    }

    public static boolean writeFileContent(String filepath, String newstr) throws IOException {
        Boolean bool = false;
        String filein = newstr + "\r\n";//新写入的行，换行
        String temp = "";

        FileInputStream fis = null;
        InputStreamReader isr = null;
        BufferedReader br = null;
        FileOutputStream fos = null;
        PrintWriter pw = null;
        try {
            File file = new File(filepath);//文件路径(包括文件名称)
            //将文件读入输入流
            fis = new FileInputStream(file);
            isr = new InputStreamReader(fis);
            br = new BufferedReader(isr);
            StringBuffer buffer = new StringBuffer();

            //文件原有内容
            for (int i = 0; (temp = br.readLine()) != null; i++) {
                buffer.append(temp);
                // 行与行之间的分隔符 相当于“\n”
                buffer = buffer.append(System.getProperty("line.separator"));
            }
            buffer.append(filein);

            fos = new FileOutputStream(file);
            pw = new PrintWriter(fos);
            pw.write(buffer.toString().toCharArray());
            pw.flush();
            bool = true;
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        } finally {
            //不要忘记关闭
            if (pw != null) {
                pw.close();
            }
            if (fos != null) {
                fos.close();
            }
            if (br != null) {
                br.close();
            }
            if (isr != null) {
                isr.close();
            }
            if (fis != null) {
                fis.close();
            }
        }
        return bool;
    }


    private static String change(String name) {
        char[] cs = name.toCharArray();
        cs[0] -= 32;
        return String.valueOf(cs);
    }
}
