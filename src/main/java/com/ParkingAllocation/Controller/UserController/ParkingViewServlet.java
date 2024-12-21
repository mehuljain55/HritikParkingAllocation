package com.ParkingAllocation.Controller.UserController;

import com.ParkingAllocation.DaoImpl.ParkingDaoImpl;
import com.ParkingAllocation.Entity.ParkingModel;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/user/viewParkingSlot")
public class ParkingViewServlet extends HttpServlet {

    private ParkingDaoImpl parkingDaoImpl;

    public ParkingViewServlet() {
        this.parkingDaoImpl = new ParkingDaoImpl();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        List<ParkingModel> parkingModelList=parkingDaoImpl.getAllParkingSlot();
        System.out.println("Parking Size:"+parkingModelList.size());
        request.setAttribute("parking",parkingModelList);
        request.getRequestDispatcher("/ParkingView.jsp").forward(request, response);

    }

}
