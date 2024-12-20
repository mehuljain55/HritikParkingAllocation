package com.ParkingAllocation.Controller.AdminController;

import com.ParkingAllocation.DaoImpl.ParkingDaoImpl;
import com.ParkingAllocation.Entity.ParkingHistory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class ViewParkingHistory extends HttpServlet {

   private ParkingDaoImpl parkingDaoImpl;

    public ViewParkingHistory() {
        this.parkingDaoImpl = new ParkingDaoImpl();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String startDateParam = request.getParameter("startDate");
        String endDateParam = request.getParameter("endDate");

        // Parse date strings to Date objects
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date startDate = null;
        Date endDate = null;

        List<ParkingHistory> parkingHistoryList=parkingDaoImpl.viewParkingHistory(startDate,endDate);
        request.setAttribute("parkingHistory", parkingHistoryList);
        request.getRequestDispatcher("User/ParkingHistory.jsp").forward(request, response);
    }

}
