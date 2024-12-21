package com.ParkingAllocation.Entity;

import java.time.LocalTime;
import java.util.Date;

public class ParkingHistory {
    private int sno;
    private int parkingSlot;
    private int employeeId;
    private String employeeName;
    private Date date;
    private String vechineNo;
    private LocalTime startTime;
    private LocalTime endTime;

    public ParkingHistory(int sno, int employeeId, String employeeName, Date date, LocalTime startTime, LocalTime endTime) {
        this.sno = sno;
        this.employeeId = employeeId;
        this.employeeName = employeeName;
        this.date = date;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public ParkingHistory() {
    }

    public int getSno() {
        return sno;
    }

    public void setSno(int sno) {
        this.sno = sno;
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalTime startTime) {
        this.startTime = startTime;
    }

    public LocalTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalTime endTime) {
        this.endTime = endTime;
    }

    public int getParkingSlot() {
        return parkingSlot;
    }

    public void setParkingSlot(int parkingSlot) {
        this.parkingSlot = parkingSlot;
    }

    public String getVechineNo() {
        return vechineNo;
    }

    public void setVechineNo(String vechineNo) {
        this.vechineNo = vechineNo;
    }
}
