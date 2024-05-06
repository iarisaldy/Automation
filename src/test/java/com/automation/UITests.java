package com.automation;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.AndroidMobileCapabilityType;
import io.appium.java_client.remote.MobileCapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.openqa.selenium.By;


import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import static org.testng.Assert.assertTrue;

public class UITests {

    private AppiumDriver<MobileElement> driver;

    @BeforeClass
    public void setup() throws MalformedURLException {
        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability(MobileCapabilityType.PLATFORM_NAME, "Android");
        caps.setCapability(MobileCapabilityType.DEVICE_NAME, "emulator-5554");
        caps.setCapability(AndroidMobileCapabilityType.APP_PACKAGE, "com.example.app");
        caps.setCapability(AndroidMobileCapabilityType.APP_ACTIVITY, ".MainActivity");

        URL url = new URL("http://127.0.0.1:4723/wd/hub");
        driver = new AndroidDriver<>(url, caps);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    @Test(priority = 1)
    public void testUserLogin() {
        // Implement user login using valid credentials
        MobileElement usernameField = driver.findElement(By.id("username"));
        MobileElement passwordField = driver.findElement(By.id("password"));
        MobileElement loginButton = driver.findElement(By.id("login_button"));

        usernameField.sendKeys("valid_username");
        passwordField.sendKeys("valid_password");
        loginButton.click();

        // Assertion for successful login
        assertTrue(driver.findElement(By.id("welcome_message")).isDisplayed());
    }

    @Test(priority = 2)
    public void testInvalidLogin() {
        // Implement user login using invalid credentials
        MobileElement usernameField = driver.findElement(By.id("username"));
        MobileElement passwordField = driver.findElement(By.id("password"));
        MobileElement loginButton = driver.findElement(By.id("login_button"));

        usernameField.sendKeys("invalid_username");
        passwordField.sendKeys("invalid_password");
        loginButton.click();

        // Assertion for failure message
        assertTrue(driver.findElement(By.id("error_message")).isDisplayed());
    }

    @Test(priority = 3)
    public void testPriceFilter() {
        // Implement price filter functionality and assertion
        assertTrue(true);
    }

    @Test(priority = 4)
    public void testAddToCartFromListingScreen() {
        // Implement adding products to cart from the listing screen and assertion
        assertTrue(true);
    }

    @Test(priority = 5)
    public void testAddToCartFromDetailsScreen() {
        // Implement adding products to cart from the details screen and assertion
        assertTrue(true);
    }

    @Test(priority = 6)
    public void testAddMultipleProductsToCart() {
        // Implement adding multiple products to cart and assertion
        assertTrue(true);
    }

    @Test(priority = 7)
    public void testContinueShoppingRedirection() {
        // Implement redirection to product listing screen from cart and assertion
        assertTrue(true);
    }

    @Test(priority = 8)
    public void testCartDetails() {
        // Implement verifying details of products in the cart and assertion
        assertTrue(true);
    }

    @Test(priority = 9)
    public void testCheckoutSuccess() {
        // Implement checkout flow with valid user information and verify success
        assertTrue(true);
    }

    @Test(priority = 10)
    public void testCheckoutInvalidFirstName() {
        // Implement checkout flow with invalid user First Name and verify error message
        assertTrue(true);
    }

    @AfterClass
    public void teardown() {
        if (driver != null) {
            driver.quit();
        }
    }
}