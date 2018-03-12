
<%@page import="org.omg.CORBA.Request"%>
<%@page import="Test.TreeUtil"%><%@ page language="java" contentType="text/html; charset=UTF-8"
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
public void LoadTree(HttpServletRequest request, HttpServletResponse response) throws Exception
{ 	
    
    String sql = "select * from plus_file order by num";
    ArrayList folders = new Test.TestDB().DBSelect(sql);
    
    String json = Test.JSON.Encode(folders);
    response.getWriter().write(json);    
}
public void LoadNodes(HttpServletRequest request, HttpServletResponse response) throws Exception
{ 	
    //获取提交的数据
    String id = request.getParameter("id");
    if(StringUtil.isNullOrEmpty(id)) id = "-1";

    //获取下一级节点
    String sql = "select * from plus_file where pid = '" + id + "' order by num";
    ArrayList folders = new Test.TestDB().DBSelect(sql);
    
    //判断节点，是否有子节点。如果有，则处理isLeaf和expanded。
    for (int i = 0, l = folders.size(); i < l; i++)
    {
        HashMap node = (HashMap)folders.get(i);
        String nodeId = node.get("id").toString();

        String sql2 = "select * from plus_file where pid = '" + nodeId + "' order by num";
        ArrayList nodes = new Test.TestDB().DBSelect(sql2);
		
        if (nodes.size() > 0)
        {
            node.put("isLeaf", false);
            node.put("expanded", false);
        }
    }
    
    //返回处理结果
    String json = Test.JSON.Encode(folders);
    response.getWriter().write(json);    
}

public void SaveTree(HttpServletRequest request, HttpServletResponse response)throws Exception
{
    String dataJSON = request.getParameter("data");
    String removedJSON = request.getParameter("removed");
    ArrayList tree = (ArrayList)Test.JSON.Decode(dataJSON);
    ArrayList removed = (ArrayList)Test.JSON.Decode(removedJSON);

    //树形转换为列表
    ArrayList list = TreeUtil.ToList(tree, "-1", "children", "id", "pid");
    
    //生成id和num
    for(int i = 0,l = list.size();i<l;i++){
        HashMap node = (HashMap)list.get(i);
        if(node.get("id") == null || node.get("id").toString().equals("")){
            String UID = UUID.randomUUID().toString();
            node.put("id",UID);
        }
        node.put("num",i);
    }
    
    //生成pid
    list = TreeUtil.ToList(tree, "-1", "children", "id", "pid");
    
    // Add/Update/Move Node
    for(int i=0,l=list.size();i<l;i++){
           HashMap node = (HashMap)list.get(i);           
           String state = node.get("_state") == null ? "" : node.get("_state").toString();           
           if(state .equals("added"))
           {
                new Test.TestDB().InsertNode(node);
           }else{
                new Test.TestDB().UpdateTreeNode(node);   
           }
    }
    // Remove Node
    if(removed != null)
    {
           for(int j =0 ,len = removed.size();j<len;j++)
           {
                HashMap removedNode = (HashMap)removed.get(j);
                new Test.TestDB().RemoveNode(removedNode);
           }
    }
}



public void FilterTree(HttpServletRequest request, HttpServletResponse response)throws Exception
{
	ArrayList data = new ArrayList();
    
	//获取查询参数
    String text = request.getParameter("name").toLowerCase();
    
    //获取整个树数据
    String sql = "select * from plus_file";
    ArrayList nodes = new Test.TestDB().DBSelect(sql);
    
    //找出符合查询条件的节点
    for(int i=0;i<nodes.size();i++){
        HashMap node = (HashMap)nodes.get(i);
	
	String name = node.get("name") == null ? "" : node.get("name").toString().toLowerCase();

        if(name.indexOf(text) > -1){
        	data.add(node);
            
        	//加入父级所有节点
            String pid = node.get("pid").toString();
            if(!pid.equals("-1")){
                ArrayList data2 = SearchParentNode(pid,nodes);
                data.addAll(data2);
            }
        }
    }
    
    //去除重复节点
    HashMap idMaps = new HashMap();
    for(int i= data.size()-1;i>=0;i--){
        HashMap node = (HashMap)data.get(i);  
        String id = node.get("id").toString();
        if(idMaps.get(id) == null){
            idMaps.put(id,node);
        }else{
        	data.remove(i);
        }
    }
    
  //返回JSON
    String dataJson = Test.JSON.Encode(data);
    response.getWriter().write(dataJson);    
}

public ArrayList SearchParentNode(String pid,ArrayList nodes)throws Exception
{
    ArrayList data = new ArrayList();
    for(int i=0;i<nodes.size();i++){
        HashMap node = (HashMap)nodes.get(i);
        if(node.get("id").toString().equals(pid)){
            data.add(node);
            if(!node.get("pid").toString().equals( "-1")){
                ArrayList data2 = SearchParentNode(node.get("pid").toString(),nodes);
                data.addAll(data2);
            }
        }
    }
    return data;
}
%> 