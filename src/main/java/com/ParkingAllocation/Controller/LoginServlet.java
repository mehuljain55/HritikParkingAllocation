package com.ParkingAllocation.Controller;

import com.ParkingAllocation.Entity.User;
import com.ParkingAllocation.JDBCUtils.JdbcUtils;
import com.ParkingAllocation.DaoImpl.UserDaoImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/validateCredential")
public class LoginServlet extends HttpServlet {

    private UserDaoImpl userDaoImpl;

    public LoginServlet() {
        this.userDaoImpl = new UserDaoImpl();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("Validate Login");
        int userId = Integer.parseInt(request.getParameter("userId"));
        String password = request.getParameter("password");
        User user = userDaoImpl.validateUser(userId, password);
        request.getSession().setAttribute("user", user);
        if (user != null && user.getRole().equals("user") ) {
            response.sendRedirect("/user/dashboard");
        }else if ( user != null && user.getRole().equals("admin") ) {
            response.sendRedirect("/admin/dashboard");


        } else {
            response.sendRedirect("/Login.jsp");
        }
    }
}
