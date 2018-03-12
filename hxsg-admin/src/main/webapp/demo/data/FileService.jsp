<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.*,Test.*,java.lang.reflect.*"%>
<% 		
	request.setCharacterEncoding("UTF-8");
	response.setCharacterEncoding("UTF-8");	
	 
    String methodName = request.getParameter("method");
    
       
    Class[] argsClass = new Class[2]; 
    argsClass[0] = HttpServletRequest.class;
    argsClass[1] = HttpServletResponse.class;
    
    Class cls = this.getClass();   
    Method method = cls.getMethod(methodName, argsClass);   
    
    Object[] args = new Object[2];
    args[0] = request;
    args[1] = response;   
    method.invoke(this, args);     
   	
%>
<%!
public void LoadFolders(HttpServletRequest request, HttpServletResponse response) throws Exception
{ 	
    //获取提交的数据
    String id = request.getParameter("id");
    if(StringUtil.isNullOrEmpty(id)) id = "-1";

    //获取下一级节点
    String sql = "select * from plus_file where folder = 1 and pid = '" + id + "' order by updatedate";
    ArrayList folders = new Test.TestDB().DBSelect(sql);
    
    //判断节点，是否有子节点。如果有，则处理isLeaf和expanded。
    for (int i = 0, l = folders.size(); i < l; i++)
    {
        HashMap node = (HashMap)folders.get(i);
        String nodeId = node.get("id").toString();

        node.put("isLeaf", false);
        node.put("expanded", false);
    }
    
    //返回处理结果
    String json = Test.JSON.Encode(folders);
    response.getWriter().write(json);    
}
public void LoadFiles(HttpServletRequest request, HttpServletResponse response) throws Exception
{ 	
    //获取提交的数据
    String folderId = request.getParameter("folderId");   

    //获取下一级节点
    String sql = "select * from plus_file where pid = " + folderId + " and folder = 0 order by updatedate";
    ArrayList files = new Test.TestDB().DBSelect(sql);    
    
    //返回处理结果
    String json = Test.JSON.Encode(files);
    response.getWriter().write(json);    
}
%> 