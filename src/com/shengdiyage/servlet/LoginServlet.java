package com.shengdiyage.servlet;

import com.shengdiyage.entity.Admin;
import com.shengdiyage.service.AdminService;
import com.shengdiyage.service.serviceImplement.AdminServiceImpl;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLEncoder;

/**
 * Created by Akari on 2017/7/10.
 */
public class LoginServlet extends HttpServlet {
    public LoginServlet() {
        super();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        req.setCharacterEncoding("utf-8");
        String adminName = req.getParameter("userName");
        String adminPassword = req.getParameter("userPassword");
        Admin admin = new Admin(adminName, adminPassword);
        AdminService adminService = new AdminServiceImpl();

        if (adminService.verifyAdmin(admin)) {
            HttpSession session = req.getSession();
            admin = adminService.queryAdminByName(adminName);
            session.setAttribute("admin",admin);
            String codeAdminName = URLEncoder.encode(admin.getAdminName(),"utf-8");
            Cookie cookieAdminName = new Cookie("admin",codeAdminName);
            cookieAdminName.setMaxAge(60*60*24*7);
            cookieAdminName.setPath("/");
            resp.addCookie(cookieAdminName);
            resp.sendRedirect("/admin/main.jsp");
        } else {
            PrintWriter out = resp.getWriter();
            out.print("<script>alert('登陆失败，请检查用户名和密码！');history.back();</script>");
        }
    }

    @Override
    public void destroy() {
        super.destroy();
    }

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
    }

    @Override
    public void init() throws ServletException {
        super.init();
    }
}
