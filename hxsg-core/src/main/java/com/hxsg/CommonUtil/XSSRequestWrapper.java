package com.hxsg.CommonUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.util.regex.Pattern;

public class XSSRequestWrapper extends HttpServletRequestWrapper {
    public XSSRequestWrapper(HttpServletRequest request) {
        super(request);  
    }  
  
    @Override  
    public String[] getParameterValues(String parameter) {  
        String[] values = super.getParameterValues(parameter);  
        if (values == null) {  
            return null;  
        }  
        int count = values.length;  
        String[] encodedValues = new String[count];  
        for (int i = 0; i < count; i++) {  
            encodedValues[i] = stripXSS(values[i]);  
        }  
        return encodedValues;  
    }  
  
    @Override  
    public String getParameter(String parameter) {  
        String value = super.getParameter(parameter);  
        return stripXSS(value);  
    }  
  
    @Override  
    public String getHeader(String name) {  
        String value = super.getHeader(name);  
        //return stripXSS(value);  
        return value;  
    }  
      
    public String getQueryString() {    
        String value = super.getQueryString();    
        if (value != null) {    
            value = stripXSS(value);    
        }    
        return value;    
    }    
  
    private String stripXSS(String value) {
        if (value != null) {  
            // Avoid anything between script tags  
            Pattern scriptPattern = Pattern.compile("<script>(.*?)</script>", Pattern.CASE_INSENSITIVE);  
            value = scriptPattern.matcher(value).replaceAll("");  
            // Avoid anything in a  
            // e­xpression  
            scriptPattern = Pattern.compile("src[\r\n]*=[\r\n]*\\\'(.*?)\\\'",  
                    Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL);  
            value = scriptPattern.matcher(value).replaceAll("");  
            scriptPattern = Pattern.compile("src[\r\n]*=[\r\n]*\\\"(.*?)\\\"",  
                    Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL);  
            value = scriptPattern.matcher(value).replaceAll("");  
            // Remove any lonesome </script> tag  
            scriptPattern = Pattern.compile("</script>", Pattern.CASE_INSENSITIVE);  
            value = scriptPattern.matcher(value).replaceAll("");  
            // Remove any lonesome <script ...> tag  
            scriptPattern = Pattern.compile("<script(.*?)>",  
                    Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL);  
            value = scriptPattern.matcher(value).replaceAll("");  
            // Avoid eval(...) e­xpressions  
            scriptPattern = Pattern.compile("eval\\((.*?)\\)",  
                    Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL);  
            value = scriptPattern.matcher(value).replaceAll("");  
            // Avoid e­xpression(...) e­xpressions  
            scriptPattern = Pattern.compile("e­xpression\\((.*?)\\)",  
                    Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL);  
            value = scriptPattern.matcher(value).replaceAll("");  
            // Avoid javascript:... e­xpressions  
            scriptPattern = Pattern.compile("javascript:", Pattern.CASE_INSENSITIVE);  
            value = scriptPattern.matcher(value).replaceAll("");  
            // Avoid vbscript:... e­xpressions  
            scriptPattern = Pattern.compile("vbscript:", Pattern.CASE_INSENSITIVE);  
            value = scriptPattern.matcher(value).replaceAll("");  
            // Avoid onload= e­xpressions  
            scriptPattern = Pattern.compile("onload(.*?)=",  
                    Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL);  
            value = scriptPattern.matcher(value).replaceAll("");  
  
        }  
        return value;  
    }  
}
