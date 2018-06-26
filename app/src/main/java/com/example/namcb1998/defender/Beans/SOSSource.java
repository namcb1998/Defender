package com.example.namcb1998.defender.Beans;

public class SOSSource {
    private String token;
    private boolean isEnded;
    private double latitude;
    private double longitude;
    private boolean someOneIsComing;

    public SOSSource() {
    }

    public SOSSource(String token, boolean isEnded, double latitude, double longitude, boolean someOneIsComing) {
        this.token = token;
        this.isEnded = isEnded;
        this.latitude = latitude;
        this.longitude = longitude;
        this.someOneIsComing = someOneIsComing;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public boolean isEnded() {
        return isEnded;
    }

    public void setEnded(boolean ended) {
        isEnded = ended;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public boolean isSomeOneIsComing() {
        return someOneIsComing;
    }

    public void setSomeOneIsComing(boolean someOneIsComing) {
        this.someOneIsComing = someOneIsComing;
    }
}
