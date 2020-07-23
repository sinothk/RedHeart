package com.sinothk.redheart.domain;






public class UserVo {

    private Long account;
    private String email;
    private String userPwd;
    //
    private Double loginLat;
    private Double loginLon;

    private double lat;
    private double lng;
    //
    private String imei;

    public Long getAccount() {
        return account;
    }

    public void setAccount(Long account) {
        this.account = account;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUserPwd() {
        return userPwd;
    }

    public void setUserPwd(String userPwd) {
        this.userPwd = userPwd;
    }

    public Double getLoginLat() {
        return loginLat;
    }

    public void setLoginLat(Double loginLat) {
        this.loginLat = loginLat;
    }

    public Double getLoginLon() {
        return loginLon;
    }

    public void setLoginLon(Double loginLon) {
        this.loginLon = loginLon;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLng() {
        return lng;
    }

    public void setLng(double lng) {
        this.lng = lng;
    }

    public String getImei() {
        return imei;
    }

    public void setImei(String imei) {
        this.imei = imei;
    }
}
