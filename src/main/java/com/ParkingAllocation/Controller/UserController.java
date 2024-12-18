package com.ParkingAllocation.Controller;

import com.ParkingAllocation.Entity.User;
import com.ParkingAllocation.JDBCUtils.JdbcUtils;
import com.ParkingAllocation.Service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/user/login")
public class UserController extends HttpServlet {

    private UserService userService;

    public UserController() {
        this.userService = new UserService(new JdbcUtils());
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int userId = Integer.parseInt(request.getParameter("userId"));
        String password = request.getParameter("password");
        User user = userService.validateUser(userId, password);

        if (user != null) {
            request.setAttribute("user", user);
            request.getRequestDispatcher("/view/Dashboard").forward(request, response);
        } else {
            response.sendRedirect("/view/Login");
        }
    }
}
