package com.sandhya.firebasedatabasesendandreceive;

public class ContactModel {
    String name,mobile,email,address,branch,college,rollNo;

    public ContactModel() {
    }

    public ContactModel(String name, String mobile, String email, String address, String branch, String college, String rollNo) {
        this.name = name;
        this.mobile = mobile;
        this.email = email;
        this.address = address;
        this.branch = branch;
        this.college = college;
        this.rollNo = rollNo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getBranch() {
        return branch;
    }

    public void setBranch(String branch) {
        this.branch = branch;
    }

    public String getCollege() {
        return college;
    }

    public void setCollege(String college) {
        this.college = college;
    }

    public String getRollNo() {
        return rollNo;
    }

    public void setRollNo(String rollNo) {
        this.rollNo = rollNo;
    }
}
