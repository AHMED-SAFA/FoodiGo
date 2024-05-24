//package com.example.android.foodiego;
//
//public class reg_class {
//
//    public String name,email,address,mobile,pass,profileImageUrl;
//    public reg_class(String name, String email, String address, String mobile, String pass,String profileImageUrl) {
//        this.name = name;
//        this.email = email;
//        this.address = address;
//        this.mobile = mobile;
//        this.pass = pass;
//        this.profileImageUrl = profileImageUrl;
//    }
//
//    public String getName() {
//        return name;
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    }
//
//    public String getEmail() {
//        return email;
//    }
//
//    public void setEmail(String email) {
//        this.email = email;
//    }
//
//    public String getAddress() {
//        return address;
//    }
//
//    public void setAddress(String address) {
//        this.address = address;
//    }
//
//    public String getMobile() {
//        return mobile;
//    }
//
//    public void setMobile(String mobile) {
//        this.mobile = mobile;
//    }
//
//    public String getPass() {
//        return pass;
//    }
//    public void setPass(String pass) {
//        this.pass = pass;
//    }
//}


package com.example.android.foodiego;

public class reg_class {
    public String name, email, address, mobile,password, profileImageUrl;

    public reg_class(String name, String email, String address, String mobile,String password, String profileImageUrl) {
        this.name = name;
        this.email = email;
        this.address = address;
        this.mobile = mobile;
        this.password = password;
        this.profileImageUrl = profileImageUrl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public String getMobile() {
        return mobile;
    }
    public String getPassword() {
        return password;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getProfileImageUrl() {
        return profileImageUrl;
    }

    public void setProfileImageUrl(String profileImageUrl) {
        this.profileImageUrl = profileImageUrl;
}
}
