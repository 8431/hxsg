
<%@page import="java.io.OutputStream"%>
<%@page import="jxl.Workbook"%>
<%@page import="jxl.write.Label"%>
<%@page import="jxl.write.WritableSheet"%>
<%@page import="jxl.write.WritableWorkbook"%>
<%@page import="javax.print.attribute.standard.Finishings"%><%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.*,Test.*,java.lang.reflect.*"%>
<% 		
	request.setCharacterEncoding("UTF-8");
	response.setCharacterEncoding("UTF-8");	
    
    excelWrite(request, response);
    
%>
<%!

public HashMap SearchEmployees(HttpServletRequest request, HttpServletResponse response) throws Exception
{ 	
    //查询条件
    String key = request.getParameter("key");
    //字段排序
    String sortField = request.getParameter("sortField");
    String sortOrder = request.getParameter("sortOrder");
    HashMap result = new Test.TestDB().SearchEmployees(key, 0, 10000, sortField, sortOrder);
    return result;
}

public void excelWrite(HttpServletRequest request, HttpServletResponse response) throws Exception
{	
	OutputStream out=response.getOutputStream();
	String fname = "grid";
    response.reset();//清空输出流
    
    response.setCharacterEncoding("UTF-8");//设置相应内容的编码格式
    fname = java.net.URLEncoder.encode(fname,"UTF-8");
    response.setHeader("Content-Disposition","attachment;filename="+new String(fname.getBytes("UTF-8"),"GBK")+".xls");
    response.setContentType("application/ms-excel");//定义输出类型
	String json = request.getParameter("columns");
	ArrayList rows = (ArrayList)Test.JSON.Decode(json);
	HashMap data = SearchEmployees(request, response);
       try {     
            // 获得开始时间     
           // long start = System.currentTimeMillis();             
            // 创建Excel工作薄     
            WritableWorkbook workbook = Workbook.createWorkbook(out);      
           // 添加第一个工作表并设置第一个Sheet的名字     
          WritableSheet sheet = workbook.createSheet("grid1", 0);     
            Label label;  
          //写出列名
            for(int i=0;i<rows.size();i++){      
            	HashMap hm = (HashMap)rows.get(i);
            	Iterator iterator = hm.keySet().iterator();                
                label = new Label(i,0,hm.get("header").toString());
                sheet.addCell(label);     
           }   
          //写出数据
            ArrayList list = (ArrayList)data.get("data");  
            	for (int i = 1; i < list.size(); i++) {
	            	HashMap hm1 = (HashMap)list.get(i);
	            	for (int k = 1; k < hm1.size(); k++) {
	            		for(int j=0;j<rows.size();j++){     
	    	            	HashMap hm = (HashMap)rows.get(j);
	    	            	String key =hm.get("field").toString();
	            			String value=String.valueOf(hm1.get(key));
	            			label = new Label(j,i,value);
			            	sheet.addCell(label); 
	            		}
					}	
				}       
            	
          // 写入数据     
            	workbook.write();     
           // 关闭文件     
            	workbook.close();  
            	out.close();
          // long end = System.currentTimeMillis();     
          //System.out.println("----完成该操作共用的时间是:"+(end-start)/1000);     
       } catch (Exception e) {     
          // System.out.println("---出现异常---");     
           e.printStackTrace();     
       }     
}

%> 