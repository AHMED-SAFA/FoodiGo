package com.example.android.foodiego;

import static org.junit.Assert.*;

import org.junit.Test;


public class RegClassTest {

    @Test
    public void testConstructorAndGetters() {
        String testName = "John Doe";
        String testEmail = "johndoe@example.com";
        String testAddress = "123 Main St";
        String testMobile = "123-456-7890";
        String testPassword = "securepassword";
        String testProfileImageUrl = "https://example.com/profile.jpg";

        reg_class user = new reg_class(testName, testEmail, testAddress, testMobile, testPassword, testProfileImageUrl);

        assertEquals(testName, user.getName());
        assertEquals(testEmail, user.getEmail());
        assertEquals(testAddress, user.getAddress());
        assertEquals(testMobile, user.getMobile());
        assertEquals(testPassword, user.getPassword());
        assertEquals(testProfileImageUrl, user.getProfileImageUrl());
    }

    @Test
    public void testSetters() {
        reg_class user = new reg_class("", "", "", "", "", "");

        String newName = "Jane Doe";
        String newEmail = "janedoe@example.com";
        String newAddress = "456 Elm St";
        String newMobile = "098-765-4321";
        String newPassword = "verysecure";
        String newProfileImageUrl = "https://example.com/newprofile.jpg";

        user.setName(newName);
        user.setEmail(newEmail);
        user.setAddress(newAddress);
        user.setMobile(newMobile);
        user.setPassword(newPassword); // Updated to setPassword()
        user.setProfileImageUrl(newProfileImageUrl);

        assertEquals(newName, user.getName());
        assertEquals(newEmail, user.getEmail());
        assertEquals(newAddress, user.getAddress());
        assertEquals(newMobile, user.getMobile());
        assertEquals(newPassword, user.getPassword()); // Updated to getPassword()
        assertEquals(newProfileImageUrl, user.getProfileImageUrl());
    }
}
