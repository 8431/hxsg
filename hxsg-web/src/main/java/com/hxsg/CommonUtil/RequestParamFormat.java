package com.hxsg.CommonUtil;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

/**
 *  HTTP请求参数格式化
 * @description
 * @author        jiangzhenjian  2016-3-18 上午10:09:52 
 * @version       V1.0
 */
public class RequestParamFormat {

	/**
	 * 
	 *  把HTTP请求参数转换成Map对象
	 * @description
	 * 
	 * @title  formatMap
	 * @param  @param request
	 * @param  @return
	 * @return Map<String,Object>
	 */
	public static Map<String, Object> requestParamToMap(HttpServletRequest request) {
		@SuppressWarnings("unchecked")
		Map<String,String[]> map=request.getParameterMap();
		Map<String, Object> newMap=new HashMap<String, Object>();
		for(String key:map.keySet()){
			String value=map.get(key)[0];
			if(!StringUtil.isEmpty(value)){
				newMap.put(key, map.get(key)[0]);
			}
		}
		return newMap;
	}
    /**
     * 封装二维数组
     * @param request
     * @param name
     * @return
     * @throws Exception
     */
    public static String[][] requestParamToArrays(HttpServletRequest request,String name) throws Exception{
        @SuppressWarnings("unchecked")
        Map<String,String[]> map=request.getParameterMap();
        Map<String,Object> map2=new HashMap<String, Object>();
        String[][] ot=null;
        Map<String,String[]> tree=new TreeMap<String, String[]>();
        for(String key:map.keySet()) {
            if(key.startsWith(name)){
                tree.put(key,map.get(key));
            }
        }
        ot=new  String[tree.size()][];
        int index=0;
        for(String key:tree.keySet()) {
            ot[index]=tree.get(key);
            index++;
        }
        return ot;
    }
}
