package com.ParkingAllocation.DaoImpl;

import com.ParkingAllocation.Dao.UserDao;
import com.ParkingAllocation.Entity.ParkingModel;
import com.ParkingAllocation.Entity.User;
import com.ParkingAllocation.JDBCUtils.JdbcUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class UserDaoImpl  implements UserDao {

    private JdbcUtils jdbcUtils;

    public UserDaoImpl() {
        this.jdbcUtils =new JdbcUtils();
    }

    public String addUser(User user)
    {
        try {

            Connection con=jdbcUtils.establishConnection();
            Statement statement = con.createStatement();
            String query = "INSERT INTO User (userId, name, role, password) " +
                    "VALUES (" + user.getUserId() + ", '" + user.getName() + "', '" + user.getRole() + "', '" + user.getPassword() + "')";
            PreparedStatement ps = con.prepareStatement(query);
            ps.executeUpdate();
            con.close();
            return "User added";
        }catch (Exception e)
        {
            e.printStackTrace();
            return "Failed to add user";
        }
    }

    public String addParkingSlot(ParkingModel parkingModel)
    {
        try {
            int maxParkingId=0;
            Connection con=jdbcUtils.establishConnection();
            Statement statement = con.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT MAX(parkingId) FROM Parking");
            if (resultSet.next()) {
                maxParkingId = resultSet.getInt(1) + 1;
            }
            parkingModel.setParkingId(maxParkingId);
            String query = "INSERT INTO Parking (parkingId, userAllocationId, status) " +
                    "VALUES (" + parkingModel.getParkingId() + ", NULL, 'Free')";
            PreparedStatement ps = con.prepareStatement(query);
            ps.executeUpdate();
            con.close();
            return "User updated";
        }catch (Exception e)
        {
            e.printStackTrace();
            return "Failed to add user";
        }
    }

    public User validateUser(int userId,String password)
    {
        User user=new User();
        try {

        Connection con=jdbcUtils.establishConnection();
        String query = "SELECT userId, name, role, password FROM user WHERE userId = ?";

        PreparedStatement stmt = con.prepareStatement(query);
        stmt.setInt(1, userId);

        ResultSet rs = stmt.executeQuery();

        // Process result set
        if (rs.next()) {
            user.setUserId(rs.getInt("userId"));
            user.setName(rs.getString("name"));
            user.setRole(rs.getString("role"));
            user.setPassword(rs.getString("password"));
        }


        if(user.getPassword().equals(password))
        {
            return user;
        }
        else {
            return null;
        }


        }catch (Exception e)
        {
            e.printStackTrace();
            return null;
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

        }


