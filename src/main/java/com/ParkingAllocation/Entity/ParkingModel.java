package com.ParkingAllocation.Entity;

public class ParkingModel {
   private int parkingId;
   private int userId;
   private int parkingHistoryId;
   private String userName;
   private String status;

   public ParkingModel(int parkingId, int userId, String userName, String status) {
      this.parkingId = parkingId;
      this.userId = userId;
      this.userName = userName;
      this.status = status;
   }

   public ParkingModel() {
   }

   public int getParkingId() {
      return parkingId;
   }

   public void setParkingId(int parkingId) {
      this.parkingId = parkingId;
   }

   public int getUserId() {
      return userId;
   }

   public void setUserId(int userId) {
      this.userId = userId;
   }

   public String getUserName() {
      return userName;
   }

   public void setUserName(String userName) {
      this.userName = userName;
   }

   public String getStatus() {
      return status;
   }

   public void setStatus(String status) {
      this.status = status;
   }

   public int getParkingHistoryId() {
      return parkingHistoryId;
   }

   public void setParkingHistoryId(int parkingHistoryId) {
      this.parkingHistoryId = parkingHistoryId;
   }
}
