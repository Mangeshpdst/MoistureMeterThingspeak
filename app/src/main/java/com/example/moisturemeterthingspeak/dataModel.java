package com.example.moisturemeterthingspeak;

public class dataModel {
    String Moisture_Value, Time, Date, Location, Lot, Bail;

    public dataModel(String moisture_Value, String time, String date, String location, String lot, String bail) {
        Moisture_Value = moisture_Value;
        Time = time;
        Date = date;
        Location = location;
        Lot = lot;
        Bail = bail;
    }

    public String getMoisture_Value() {
        return Moisture_Value;
    }

    public void setMoisture_Value(String moisture_Value) {
        Moisture_Value = moisture_Value;
    }

    public String getTime() {
        return Time;
    }

    public void setTime(String time) {
        Time = time;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }

    public String getLocation() {
        return Location;
    }

    public void setLocation(String location) {
        Location = location;
    }

    public String getLot() {
        return Lot;
    }

    public void setLot(String lot) {
        Lot = lot;
    }

    public String getBail() {
        return Bail;
    }

    public void setBail(String bail) {
        Bail = bail;
    }
}
