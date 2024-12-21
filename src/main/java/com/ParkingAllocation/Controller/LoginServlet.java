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

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int userId = Integer.parseInt(request.getParameter("userId"));
        String password = request.getParameter("password");
        User user = userDaoImpl.validateUser(userId, password);

        if (user != null && user.getRole().equals("user") ) {
            request.getSession().setAttribute("user", user);
            request.getRequestDispatcher("User/Dashboard.jsp").forward(request, response);
        }else if ( user != null && user.getRole().equals("admin") ) {
            request.setAttribute("user", user);
            request.getRequestDispatcher("Admin/Dashboard.jsp").forward(request, response);


        } else {
            response.sendRedirect("/Login.jsp");
        }
    }
}
