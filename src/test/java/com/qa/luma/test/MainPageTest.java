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
import org.testng.annotations.*;

import java.net.MalformedURLException;
import java.util.List;

@Listeners(com.qa.luma.listeners.Listeners.class)
public class MainPageTest {
    private WebDriver driver;
    private LoginPage loginPage;

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

    @Test
    public void pageTitleTest(){
        MainPage mainPage = new MainPage(driver);
        Assert.assertTrue(mainPage.logoIsDisplayed());
    }

    @Test
    public void clickOnSignInTest(){
        MainPage mainPage = new MainPage(driver);
        loginPage = mainPage.clickOnSignIn();
        Assert.assertTrue(loginPage.customerLoginValidate());
    }

    @Test
    public void topSellers(){
        MainPage mainPage = new MainPage(driver);
        int act = mainPage.topSellers();
        int exp = 6;
        Assert.assertEquals(act, exp);
    }
}
