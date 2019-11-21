package com.helloworld.servlet;
	 
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.helloworld.dao.UserDao;
import com.helloworld.encrypt.Md5;;

	public class RegistServlet extends HttpServlet {
		
		private static final long serialVersionUID = 1L;

		protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			request.setCharacterEncoding("UTF-8");
			response.setCharacterEncoding("UTF-8");
			response.setContentType("text/html;charset=UTF-8");
			String username = request.getParameter("username");
			String password = request.getParameter("password");
			Md5 md5 = new Md5();
			String md5Psw = md5.encrypt(password);
			String phoneNum = request.getParameter("phoneNum");
			UserDao u = new UserDao();
			
			if(u.isRegistered(username)==true) {
				response.getWriter().print("<script> alert(\"\");</script>");
				request.getRequestDispatcher("register.jsp").include(request, response);
			} else if (u.newPhoneNum(phoneNum)==false) {
				response.getWriter().print("<script> alert(\"\");</script>");
				request.getRequestDispatcher("register.jsp").include(request, response);
			} else {
			int id=0;
			u.addUser(id,username,md5Psw,phoneNum);
			request.getRequestDispatcher("success.jsp").forward(request, response);
		}
		}
	}