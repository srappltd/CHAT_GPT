package com.sandhya.firebasedatabasesendandreceive;

public class UsersModel {
    String email, name, password, date,image;

    public UsersModel(String email, String name, String password, String date, String image) {
        this.email = email;
        this.name = name;
        this.password = password;
        this.date = date;
        this.image = image;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}