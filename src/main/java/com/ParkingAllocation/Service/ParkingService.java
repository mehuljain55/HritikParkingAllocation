package com.ParkingAllocation.Service;


import com.ParkingAllocation.Entity.ParkingHistory;
import com.ParkingAllocation.Entity.ParkingModel;
import com.ParkingAllocation.Entity.User;
import com.ParkingAllocation.JDBCUtils.JdbcUtils;

import java.sql.*;
import java.time.LocalTime;
import java.util.Date;

public class ParkingService {

    private JdbcUtils jdbcUtils;


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

    public User getUserInformation(int userId){
        try{
            User user=new User();
            Connection con=jdbcUtils.establishConnection();
            PreparedStatement statement = con.prepareStatement("SELECT * FROM User WHERE userId = ?");
            statement.setInt(1, userId);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {

                user.setUserId(resultSet.getInt("userId"));
                user.setName(resultSet.getString("userName"));
            }
            con.close();
            return user;
        } catch (Exception e) {
            e.printStackTrace();
            return  null;
        }
    }

    public String checkIn(int userId,int parkingId) throws SQLException {
        ParkingModel parkingModel=getParkingInformation(parkingId);
        User user=getUserInformation(userId);
        if (parkingModel.getStatus().equals("Free"))
        {
            parkingModel.setUserId(userId);
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

            String sql = "INSERT INTO ParkingHistory (employeeId, employeeName, date, startTime) " +
                    "VALUES (?, ?, ?, ?)";

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
                "UPDATE Parking SET  status = 'Free', userId = ?, userName = ?, parkingHistoryId=? WHERE parkingId = ?");
        PreparedStatement updateHistoryStatement = con.prepareStatement(
                "UPDATE ParkingHistory SET endTime = ? WHERE parkingHistoryId = ?");

               // Update Parking table
               updateParkingStatement.setInt(1, 0);
               updateParkingStatement.setString(2, "");
               updateParkingStatement.setInt(3, 0);
               updateParkingStatement.setInt(4, parkingId);
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




}



