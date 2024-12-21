package com.ParkingAllocation.DaoImpl;


import com.ParkingAllocation.Entity.ParkingHistory;
import com.ParkingAllocation.Entity.ParkingModel;
import com.ParkingAllocation.Entity.User;
import com.ParkingAllocation.JDBCUtils.JdbcUtils;

import java.sql.*;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ParkingDaoImpl {

    private JdbcUtils jdbcUtils;
    private UserDaoImpl userDao;

    public ParkingDaoImpl() {
        this.jdbcUtils = new JdbcUtils();
        this.userDao = new UserDaoImpl();
    }

    public String addParking(String parkingType)
    {
        try{
            Connection con=jdbcUtils.establishConnection();
            int maxParkingId=1;
            Statement statement=con.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT MAX(parkingId) FROM Parking");
            if (resultSet.next()) {
                maxParkingId = resultSet.getInt(1) + 1;
            }

            String query = "INSERT INTO Parking (parkingId, parkingType, status) " +
                    "VALUES (?, ?, ?)";
            PreparedStatement preparedStatement=con.prepareStatement(query);
            preparedStatement.setInt(1, maxParkingId);
            preparedStatement.setString(2, parkingType);
            preparedStatement.setString(3, "Free");
            preparedStatement.executeUpdate();
            con.close();
            return "Success";
        }catch (Exception e)
        {
            e.printStackTrace();
            return "Failed";
        }

    }

    public ParkingModel getParkingInformation(int parkingId){
        try{
       ParkingModel parkingModel=new ParkingModel();
       Connection con=jdbcUtils.establishConnection();
       PreparedStatement statement = con.prepareStatement("SELECT * FROM Parking WHERE parkingId = ?");
       statement.setInt(1, parkingId);
       ResultSet resultSet = statement.executeQuery();

        if (resultSet.next()) {

            parkingModel.setParkingId(resultSet.getInt("parkingId"));
            parkingModel.setStatus(resultSet.getString("status"));
            parkingModel.setParkingHistoryId(resultSet.getInt("parkingHistoryId"));
            parkingModel.setUserId(resultSet.getInt("userId"));
        }
       return parkingModel;
    } catch (Exception e) {
        e.printStackTrace();
        return  null;
    }
    }


    public String checkIn(int userId,int parkingId,String vechineNo) throws SQLException {
        ParkingModel parkingModel=getParkingInformation(parkingId);
        User user=userDao.getUserInformation(userId);
        if (parkingModel.getStatus().equals("Free"))
        {
            parkingModel.setUserId(userId);
            parkingModel.setVechileNo(vechineNo);
            Connection con=jdbcUtils.establishConnection();
            PreparedStatement updateStatement;
            int maxParkingHistoryId=0;
            Statement statement = con.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT MAX(sno) FROM ParkingHistory");
            if (resultSet.next()) {
                maxParkingHistoryId = resultSet.getInt(1) + 1;
            }

            ParkingHistory parkingHistory=new ParkingHistory();
            parkingHistory.setSno(maxParkingHistoryId);
            parkingHistory.setDate(new Date());
            parkingHistory.setEmployeeId(userId);
            parkingHistory.setEmployeeName(user.getName());
            parkingHistory.setStartTime(LocalTime.now());
            parkingHistory.setParkingSlot(parkingId);

            String sql = "INSERT INTO ParkingHistory (employeeId, employeeName, date, startTime,vechileNo) " +
                    "VALUES (?, ?, ?, ?, ?)";

            updateStatement = con.prepareStatement(sql);
            updateStatement.setInt(1, parkingHistory.getEmployeeId());
            updateStatement.setString(2, parkingHistory.getEmployeeName());
            updateStatement.setDate(3, new java.sql.Date(parkingHistory.getDate().getTime()));
            updateStatement.setTime(4, java.sql.Time.valueOf(parkingHistory.getStartTime()));
            updateStatement.executeUpdate();
            updateStatement = con.prepareStatement("UPDATE Parking SET userId = ?, status = 'Occupied',userName=?, parkingHistoryId=? WHERE parkingId = ?");
            updateStatement.setInt(1, userId);
            updateStatement.setString(2,user.getName());
            updateStatement.setInt(3, parkingId);
            updateStatement.setInt(4,maxParkingHistoryId);
            updateStatement.setString(5,vechineNo);
            return "success";
        }
        else {
            return "failed";
        }
    }

    public String checkOut(int userId,int parkingId,int parkingHistoryId) throws SQLException {
       try{
           Connection con=jdbcUtils.establishConnection();
        PreparedStatement updateParkingStatement = con.prepareStatement(
                "UPDATE Parking SET  status = 'Free', userId = ?, userName = ?, parkingHistoryId=?, vechileNo=? WHERE parkingId = ?");
        PreparedStatement updateHistoryStatement = con.prepareStatement(
                "UPDATE ParkingHistory SET endTime = ? WHERE parkingHistoryId = ?");

               // Update Parking table
               updateParkingStatement.setInt(1, 0);
               updateParkingStatement.setString(2, "");
               updateParkingStatement.setInt(3, 0);
               updateParkingStatement.setInt(4, parkingId);
           updateParkingStatement.setString(5, "");
               updateParkingStatement.executeUpdate();

               // Update ParkingHistory table
               updateHistoryStatement.setTime(1, java.sql.Time.valueOf(LocalTime.now()));
               updateHistoryStatement.setInt(2, parkingHistoryId);
               updateHistoryStatement.executeUpdate();
               con.close();
               return "success";
       }catch (Exception e)
       {
           e.printStackTrace();
           return "Failed";
       }

        }
    public List<ParkingHistory> viewParkingHistory(Date startDate,Date endDate)
    {
        List<ParkingHistory> parkingHistoryList=new ArrayList<>();
        try {
            Connection conn=jdbcUtils.establishConnection();

            String query = "SELECT * FROM parking_history WHERE date BETWEEN ? AND ?";

         PreparedStatement preparedStatement = conn.prepareStatement(query);
         preparedStatement.setDate(1, new java.sql.Date(startDate.getTime()));
         preparedStatement.setDate(2, new java.sql.Date(endDate.getTime()));

             ResultSet resultSet = preparedStatement.executeQuery();
                while (resultSet.next()) {
                    ParkingHistory parkingHistory = new ParkingHistory();

                    parkingHistory.setSno(resultSet.getInt("sno"));
                    parkingHistory.setParkingSlot(resultSet.getInt("parking_slot"));
                    parkingHistory.setEmployeeId(resultSet.getInt("employee_id"));
                    parkingHistory.setEmployeeName(resultSet.getString("employee_name"));
                    parkingHistory.setDate(resultSet.getDate("date"));
                    parkingHistory.setStartTime(resultSet.getTime("start_time").toLocalTime());
                    parkingHistory.setEndTime(resultSet.getTime("end_time").toLocalTime());
                    parkingHistoryList.add(parkingHistory);
                }
                    return parkingHistoryList;


        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<ParkingModel> getAllParkingSlot() {
        List<ParkingModel> parkingList = new ArrayList<>();


        try{
             Connection con=jdbcUtils.establishConnection();
             String query = "SELECT parkingId, userId, parkingHistoryId, userName, status, parkingType FROM Parking";
             Statement statement = con.createStatement();
             ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                ParkingModel parking = new ParkingModel();
                parking.setParkingId(resultSet.getInt("parkingId"));
                parking.setUserId(resultSet.getInt("userId"));
                parking.setParkingHistoryId(resultSet.getInt("parkingHistoryId"));
                parking.setUserName(resultSet.getString("userName"));
                parking.setStatus(resultSet.getString("status"));
                parking.setParkingType(resultSet.getString("parkingType"));
                parkingList.add(parking);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return parkingList;
    }


}



