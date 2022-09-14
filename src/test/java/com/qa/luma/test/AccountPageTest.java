package com.qa.luma.test;

import com.github.javafaker.Faker;
import com.qa.browser.BrowserFactory;
import com.qa.enums.Browsers;
import com.qa.pages.LoginPage;
import com.qa.pages.MainPage;
import com.qa.utils.ScreenShot;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.net.MalformedURLException;

public class AccountPageTest {


    private WebDriver driver;
    private MainPage mainPage;
    private LoginPage loginPage;


    private Faker faker;

    @BeforeMethod
    public void startUp() throws MalformedURLException {
        Faker faker = new Faker();
        driver = BrowserFactory.getBrowser(Browsers.CHROME);
        driver.get("https://magento.softwaretestingboard.com/");
    }

    @AfterMethod
    public void tearDown(ITestResult result) {
        if (result.FAILURE == result.getStatus()) {
            ScreenShot.takeScreenshot(driver, result.getTestName());
        }
        driver.quit();
    }



}
