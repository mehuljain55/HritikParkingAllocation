package com.ParkingAllocation.Dao;

import com.ParkingAllocation.Entity.ParkingModel;
import com.ParkingAllocation.Entity.User;

public interface UserDao {
    public String addUser(User user);
    public String addParkingSlot(ParkingModel parkingModel);
    public User validateUser(int userId, String password);
}
