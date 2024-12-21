package com.ParkingAllocation.Controller.UserController;

import com.ParkingAllocation.DaoImpl.ParkingDaoImpl;
import com.ParkingAllocation.Entity.User;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;


@WebServlet("/user/checkIn")
public class CheckIn extends HttpServlet {


    private ParkingDaoImpl parkingDaoImpl;

    public CheckIn() {
        this.parkingDaoImpl = new ParkingDaoImpl();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("Parking controller");
        String vehicleNo=request.getParameter("vehicleNo");
        int parkingId=Integer.parseInt(request.getParameter("parkingId"));

        User user = (User) request.getSession().getAttribute("user");

        if (user != null) {
            try {
                String status=parkingDaoImpl.checkIn(user.getUserId(),parkingId,vehicleNo);
                request.setAttribute("status",status);
                response.sendRedirect("/user/dashboard");

            } catch (SQLException e) {
                throw new RuntimeException(e);

            }
        } else {
            response.sendRedirect("/Login.jsp");
        }
    }
}

