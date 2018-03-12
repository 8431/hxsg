package com.hxsg.CommonUtil.controller;

import javax.servlet.http.HttpServlet;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ServletDownload
 */

    /**
 * Created by dlf on 2016/4/1.
 */
    @WebServlet(asyncSupported = true, urlPatterns = { "/ServletDownload" })

    public class ServletDownload extends HttpServlet {
        private static final long serialVersionUID = 1L;

        /**
         * @see HttpServlet#HttpServlet()
         */
        public ServletDownload() {
            super();
            // TODO Auto-generated constructor stub
        }

        /**
         * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
         */
        protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            // TODO Auto-generated method stub

            //获得请求文件名
            String filename = request.getParameter("filename");
            System.out.println(filename);

            //设置文件MIME类型
            response.setContentType(getServletContext().getMimeType(filename));
            //设置Content-Disposition
            response.setHeader("Content-Disposition", "attachment;filename="+filename);
            //读取目标文件，通过response将目标文件写到客户端
            //获取目标文件的绝对路径
            String fullFileName = getServletContext().getRealPath("/download/" + filename);
            //System.out.println(fullFileName);
            //读取文件
            InputStream in = new FileInputStream(fullFileName);
            OutputStream out = response.getOutputStream();

            //写文件
            int b;
            while((b=in.read())!= -1)
            {
                out.write(b);
            }

            in.close();
            out.close();
        }

        /**
         * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
         */
        protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            // TODO Auto-generated method stub
        }




    }
