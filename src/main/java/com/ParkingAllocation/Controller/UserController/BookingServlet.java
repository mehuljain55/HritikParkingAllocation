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


@WebServlet("/user/booking")
public class BookingServlet extends HttpServlet {


    private ParkingDaoImpl parkingDaoImpl;

    public BookingServlet() {
        this.parkingDaoImpl = new ParkingDaoImpl();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String vechileNo=request.getParameter("vechileNo");
        int parkingId=Integer.parseInt(request.getParameter("parkingId"));

        User user = (User) request.getSession().getAttribute("user");

        if (user != null) {
            try {
                String status=parkingDaoImpl.checkIn(user.getUserId(),parkingId,vechileNo);
                request.setAttribute("status",status);
                request.getRequestDispatcher("/user/dashboard").forward(request, response);

            } catch (SQLException e) {
                throw new RuntimeException(e);

            }
        } else {
            response.sendRedirect("/Login.jsp");
        }
    }
}

