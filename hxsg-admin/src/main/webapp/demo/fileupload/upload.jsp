<%@page import="java.io.File"%>
<%@page import="java.util.Enumeration"%>
<%@page import="com.oreilly.servlet.multipart.DefaultFileRenamePolicy"%>
<%@page import="com.oreilly.servlet.MultipartRequest"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.*,Test.*"%>

<% 	
	
	String uploadPath = "demo\\fileUpload\\upload";
	String saveDirectory = session.getServletContext().getRealPath(uploadPath);
	MultipartRequest multi = new MultipartRequest(request,saveDirectory,
		100 * 1024 * 1024, "UTF-8");
	 
	//如果有上传文件, 则保存到数据内
	Enumeration files = multi.getFileNames();
	while (files.hasMoreElements()) {
		String name = (String)files.nextElement();
		File f = multi.getFile(name);
		if(f!=null){
			//读取上传后的项目文件, 导入保存到数据中
			String fileName = multi.getFilesystemName(name);
			response.getWriter().write(fileName +"("+new Date()+")");    //可以返回一个JSON字符串, 在客户端做更多处理					
		}
	}
%>
