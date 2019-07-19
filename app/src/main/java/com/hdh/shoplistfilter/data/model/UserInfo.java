package com.hdh.shoplistfilter.data.model;

public class UserInfo {
    private String userID;
    private String userName;
    private String userAddressNumber;
    private String userAddress;
    private String userRestAddress;
    private String userFirstHomeNumber;
    private String userMidHomeNumber;
    private String userLastHomeNumber;
    private String userFirstPhoneNumber;
    private String userMidPhoneNumber;
    private String userLastPhoneNumber;
    private String userFirstEmail;
    private String userLastEmail;
    private String userBirthdayYear;
    private String userBirthdayMonth;
    private String userBirthdayDay;
    private String userArea;
    private int userPasswordConfirmationQuestion;
    private String userPasswordConfirmationAnswer;

    private int userFirstHomePosition;
    private int userFirstPhoneNumberPosition;
    private int userAreaPosition;

    /**
     * DB 에서 불러오지 않으므로 초기 설정
     * DB 연결 후 삭제요망
     */
    public UserInfo() {
        userID = "kjg123kg";
        userName = "황다현";
        userAddressNumber = "01799";
        userAddress = "서울 노원구 노원로58 (우방아파트)";
        userRestAddress = "404동 202호";
        userFirstHomeNumber = "02";
        userMidHomeNumber = "979";
        userLastHomeNumber = "3339";
        userFirstPhoneNumber = "010";
        userMidPhoneNumber = "7600";
        userLastPhoneNumber = "1318";
        userFirstEmail = "kjg123kg";
        userLastEmail = "gmail.com";
        userBirthdayYear = "1994";
        userBirthdayMonth = "6";
        userBirthdayDay = "18";
        userArea = "서울";
        userAreaPosition = 2;
        userFirstHomePosition = 1;
        userFirstPhoneNumberPosition = 1;
        userPasswordConfirmationQuestion = 3;
        userPasswordConfirmationAnswer = "가족";
    }

    public void setUserInfo(String userID,
                            String userName,
                            String userAddressNumber, String userAddress, String userRestAddress,
                            String userFirstHomeNumber, String userMidHomeNumber, String userLastHomeNumber,
                            String userFirstPhoneNumber, String userMidPhoneNumber, String userLastPhoneNumber,
                            String userFirstEmail, String userLastEmail,
                            String userBirthdayYear, String userBirthdayMonth, String userBirthdayDay,
                            String userArea,
                            int userPasswordConfirmationQuestion, String userPasswordConfirmationAnswer ,
                            int userFirstHomePosition,
                            int userFirstPhoneNumberPosition,
                            int userAreaPosition) {
        this.userID = userID;
        this.userName = userName;
        this.userAddressNumber = userAddressNumber;
        this.userAddress = userAddress;
        this.userRestAddress = userRestAddress;
        this.userFirstHomeNumber = userFirstHomeNumber;
        this.userMidHomeNumber = userMidHomeNumber;
        this.userLastHomeNumber = userLastHomeNumber;
        this.userFirstPhoneNumber = userFirstPhoneNumber;
        this.userMidPhoneNumber = userMidPhoneNumber;
        this.userLastPhoneNumber = userLastPhoneNumber;
        this.userFirstEmail = userFirstEmail;
        this.userLastEmail = userLastEmail;
        this.userBirthdayYear = userBirthdayYear;
        this.userBirthdayMonth = userBirthdayMonth;
        this.userBirthdayDay = userBirthdayDay;
        this.userArea = userArea;
        this.userPasswordConfirmationQuestion = userPasswordConfirmationQuestion;
        this.userPasswordConfirmationAnswer = userPasswordConfirmationAnswer;
        this.userFirstHomePosition = userFirstHomePosition;
        this.userFirstPhoneNumberPosition = userFirstPhoneNumberPosition;
        this.userAreaPosition = userAreaPosition;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserAddressNumber() {
        return userAddressNumber;
    }

    public void setUserAddressNumber(String userAddressNumber) {
        this.userAddressNumber = userAddressNumber;
    }

    public String getUserAddress() {
        return userAddress;
    }

    public void setUserAddress(String userAddress) {
        this.userAddress = userAddress;
    }

    public String getUserRestAddress() {
        return userRestAddress;
    }

    public void setUserRestAddress(String userRestAddress) {
        this.userRestAddress = userRestAddress;
    }

    public String getUserFirstHomeNumber() {
        return userFirstHomeNumber;
    }

    public void setUserFirstHomeNumber(String userFirstHomeNumber) {
        this.userFirstHomeNumber = userFirstHomeNumber;
    }

    public String getUserMidHomeNumber() {
        return userMidHomeNumber;
    }

    public void setUserMidHomeNumber(String userMidHomeNumber) {
        this.userMidHomeNumber = userMidHomeNumber;
    }

    public String getUserLastHomeNumber() {
        return userLastHomeNumber;
    }

    public void setUserLastHomeNumber(String userLastHomeNumber) {
        this.userLastHomeNumber = userLastHomeNumber;
    }

    public String getUserFirstPhoneNumber() {
        return userFirstPhoneNumber;
    }

    public void setUserFirstPhoneNumber(String userFirstPhoneNumber) {
        this.userFirstPhoneNumber = userFirstPhoneNumber;
    }

    public String getUserMidPhoneNumber() {
        return userMidPhoneNumber;
    }

    public void setUserMidPhoneNumber(String userMidPhoneNumber) {
        this.userMidPhoneNumber = userMidPhoneNumber;
    }

    public String getUserLastPhoneNumber() {
        return userLastPhoneNumber;
    }

    public void setUserLastPhoneNumber(String userLastPhoneNumber) {
        this.userLastPhoneNumber = userLastPhoneNumber;
    }

    public String getUserFirstEmail() {
        return userFirstEmail;
    }

    public void setUserFirstEmail(String userFirstEmail) {
        this.userFirstEmail = userFirstEmail;
    }

    public String getUserLastEmail() {
        return userLastEmail;
    }

    public void setUserLastEmail(String userLastEmail) {
        this.userLastEmail = userLastEmail;
    }


    public String getUserBirthdayYear() {
        return userBirthdayYear;
    }

    public void setUserBirthdayYear(String userBirthdayYear) {
        this.userBirthdayYear = userBirthdayYear;
    }

    public String getUserBirthdayMonth() {
        return userBirthdayMonth;
    }

    public void setUserBirthdayMonth(String userBirthdayMonth) {
        this.userBirthdayMonth = userBirthdayMonth;
    }

    public String getUserBirthdayDay() {
        return userBirthdayDay;
    }

    public void setUserBirthdayDay(String userBirthdayDay) {
        this.userBirthdayDay = userBirthdayDay;
    }

    public String getUserArea() {
        return userArea;
    }

    public void setUserArea(String userArea) {
        this.userArea = userArea;
    }

    public int getUserPasswordConfirmationQuestion() {
        return userPasswordConfirmationQuestion;
    }

    public void setUserPasswordConfirmationQuestion(int userPasswordConfirmationQuestion) {
        this.userPasswordConfirmationQuestion = userPasswordConfirmationQuestion;
    }

    public String getUserPasswordConfirmationAnswer() {
        return userPasswordConfirmationAnswer;
    }

    public void setUserPasswordConfirmationAnswer(String userPasswordConfirmationAnswer) {
        this.userPasswordConfirmationAnswer = userPasswordConfirmationAnswer;
    }

    public int getUserFirstHomePosition() {
        return userFirstHomePosition;
    }

    public void setUserFirstHomePosition(int userFirstHomePosition) {
        this.userFirstHomePosition = userFirstHomePosition;
    }

    public int getUserFirstPhoneNumberPosition() {
        return userFirstPhoneNumberPosition;
    }

    public void setUserFirstPhoneNumberPosition(int userFirstPhoneNumberPosition) {
        this.userFirstPhoneNumberPosition = userFirstPhoneNumberPosition;
    }

    public int getUserAreaPosition() {
        return userAreaPosition;
    }

    public void setUserAreaPosition(int userAreaPosition) {
        this.userAreaPosition = userAreaPosition;
    }

    @Override
    public String toString() {
        return "UserInfo{" +
                "userID='" + userID + '\'' +
                ", userName='" + userName + '\'' +
                ", userAddressNumber='" + userAddressNumber + '\'' +
                ", userAddress='" + userAddress + '\'' +
                ", userRestAddress='" + userRestAddress + '\'' +
                ", userFirstHomeNumber='" + userFirstHomeNumber + '\'' +
                ", userMidHomeNumber='" + userMidHomeNumber + '\'' +
                ", userLastHomeNumber='" + userLastHomeNumber + '\'' +
                ", userFirstPhoneNumber='" + userFirstPhoneNumber + '\'' +
                ", userMidPhoneNumber='" + userMidPhoneNumber + '\'' +
                ", userLastPhoneNumber='" + userLastPhoneNumber + '\'' +
                ", userFirstEmail='" + userFirstEmail + '\'' +
                ", userLastEmail='" + userLastEmail + '\'' +
                ", userBirthdayYear='" + userBirthdayYear + '\'' +
                ", userBirthdayMonth='" + userBirthdayMonth + '\'' +
                ", userBirthdayDay='" + userBirthdayDay + '\'' +
                ", userArea='" + userArea + '\'' +
                ", userPasswordConfirmationQuestion=" + userPasswordConfirmationQuestion +
                ", userPasswordConfirmationAnswer='" + userPasswordConfirmationAnswer + '\'' +
                ", userFirstHomePosition=" + userFirstHomePosition +
                ", userFirstPhoneNumberPosition=" + userFirstPhoneNumberPosition +
                ", userAreaPosition=" + userAreaPosition +
                '}';
    }
}