package com.sandhya.firebasedatabasesendandreceive.data;

public class DataModel {
    String name,age,mobile,Name,Mobile,Age,image;

    public DataModel() {
    }

    public DataModel(String name, String age, String mobile, String name1, String mobile1, String age1, String image) {
        this.name = name;
        this.age = age;
        this.mobile = mobile;
        Name = name1;
        Mobile = mobile1;
        Age = age1;
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }
}
