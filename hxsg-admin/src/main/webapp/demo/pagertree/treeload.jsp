<%@page import="Test.TreeUtil"%><%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.*,Test.*,java.lang.reflect.*"%>
<% 		
	request.setCharacterEncoding("UTF-8");
	response.setCharacterEncoding("UTF-8");	
	 
    //获取列表数据（树）
    ArrayList treelist = FromDataBase(request, response);

    //使用PagerTree服务端分页功能
    CreatePagerTree(treelist, request, response);    
   	
%>
<%!
public void CreatePagerTree(ArrayList treelist, HttpServletRequest request, HttpServletResponse response) throws Exception
{
    String key = request.getParameter("name");
    int pageIndex = request.getParameter("pageIndex") == null ? 0 : Integer.parseInt(request.getParameter("pageIndex"));
    int pageSize = request.getParameter("pageSize") == null ? 20 : Integer.parseInt(request.getParameter("pageSize"));

    //加载数据
    DataTree tree = new DataTree("UID", "ParentTaskUID", "children");
    tree.LoadList(treelist);

    //处理折叠
    tree.SetRequest(request);

    //处理过滤
    if (!StringUtil.isNullOrEmpty(key))
    {
        ArrayList nodes = SearchNodes(key, treelist);
        tree.SetFiltered(nodes);
    }

    //处理分页
    HashMap result = new HashMap();
    result.put("total", tree.GetTotalCount());
    result.put("data", tree.GetPagedData(pageIndex, pageSize));

        //返回所有父节点
        ArrayList allIds = new ArrayList();
            for (int i = 0,l=treelist.size(); i<l; i++)
            {
                HashMap node = (HashMap)treelist.get(i);
                if (node.get("children") != null)
                {
                    allIds.add(node.get("UID"));
                }
            }
        result.put("allIds", allIds);

    //返回JSON
    String json = Test.JSON.Encode(result);
    response.getWriter().write(json);
}
public ArrayList FromDataBase(HttpServletRequest request, HttpServletResponse response) throws Exception
{
    String id = request.getParameter("id");
    
    //示例从本地文件读取，实际应该从数据库获取树型数据    
    String saveDirectory = request.getSession().getServletContext().getRealPath("demo/pagertree");    
    String path = saveDirectory +"/tasks.txt";
    String json = Test.File.read(path);
    ArrayList tree = (ArrayList)Test.JSON.Decode(json);
    return tree;
}
public ArrayList SearchNodes(String key, ArrayList nodeList) throws Exception
{
    key = key.toLowerCase();
    ArrayList filters = new ArrayList();

    for (int i = 0, l = nodeList.size(); i < l; i++)
    {
        HashMap node = (HashMap)nodeList.get(i);
        String taskName = node.get("Name")!= null ? node.get("Name").toString().toLowerCase() : "";
        if (taskName.indexOf(key) != -1)
        {
            filters.add(node);
        }
    }

    return filters;
}

%> 