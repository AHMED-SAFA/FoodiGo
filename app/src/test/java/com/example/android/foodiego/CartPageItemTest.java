package com.example.android.foodiego;

import org.junit.Before;
import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import static org.junit.Assert.*;

public class CartPageItemTest {

    private CartPageItem cartPageItem;

    @Before
    public void setUp() {
        cartPageItem = new CartPageItem("10.00", 2, 20);
    }

    @Test
    public void testConstructor() {
        assertEquals("10.00", cartPageItem.getPrice());
        assertEquals(2, cartPageItem.getQuantity());
        assertEquals(20, cartPageItem.getTotalPrice());
    }

    @Test
    public void testGetCurrentDate() {
        String currentDate = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
        assertEquals(currentDate, cartPageItem.getCurrentDate());
    }

    @Test
    public void testGetCurrentTime() {
        String currentTime = new SimpleDateFormat("HH:mm:ss").format(new Date());
        assertEquals(currentTime, cartPageItem.getCurrentTime());
    }

    @Test
    public void testSetPrice() {
        cartPageItem.setPrice("15.00");
        assertEquals("15.00", cartPageItem.getPrice());
    }

    @Test
    public void testSetQuantity() {
        cartPageItem.setQuantity(3);
        assertEquals(3, cartPageItem.getQuantity());
    }

    @Test
    public void testSetCurrentDate() {
        String newDate = "2024-05-17";
        cartPageItem.setCurrentDate(newDate);
        assertEquals(newDate, cartPageItem.getCurrentDateDatabase());
    }

    @Test
    public void testSetCurrentTime() {
        String newTime = "12:34:56";
        cartPageItem.setCurrentTime(newTime);
        assertEquals(newTime, cartPageItem.getCurrentTimeDatabase());
    }

    @Test
    public void testToMap() {
        Map<String, Object> map = cartPageItem.toMap();
        assertEquals("10.00", map.get("price"));
        assertEquals(2, map.get("quantity"));
        assertEquals(20, map.get("totalPrice"));
    }
}
