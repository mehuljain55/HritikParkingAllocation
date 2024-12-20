package com.ParkingAllocation.Controller.AdminController;

import com.ParkingAllocation.DaoImpl.ParkingDaoImpl;
import com.ParkingAllocation.Entity.ParkingModel;
import com.ParkingAllocation.Entity.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/admin/addParking")
public class AddParking extends HttpServlet {

    ParkingDaoImpl parkingDaoImpl;

    public AddParking() {
        this.parkingDaoImpl = new ParkingDaoImpl();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String parkingType = request.getParameter("parkingType");
        String status=parkingDaoImpl.addParking(parkingType);
        request.getRequestDispatcher("Admin/Dashboard.jsp").forward(request, response);

    }

}
