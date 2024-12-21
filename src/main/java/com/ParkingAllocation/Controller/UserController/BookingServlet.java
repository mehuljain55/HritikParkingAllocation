package com.ParkingAllocation.Controller.UserController;

import com.ParkingAllocation.Entity.ParkingModel;
import com.ParkingAllocation.Entity.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/user/booking")
public class BookingServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        User user = (User) request.getSession().getAttribute("user");
        String vechileNo=request.getParameter("vechileNo");

        if (user != null) {

        } else {
            response.sendRedirect("/Login.jsp");
        }
    }
}

