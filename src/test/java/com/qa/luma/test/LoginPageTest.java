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
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.net.MalformedURLException;

@Listeners(com.qa.luma.listeners.Listeners.class)
public class LoginPageTest {

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

    @Test
    public void loginTest(){
        String email = "dimagadjilla@gmail.com";
        String password = "3036057Dr";
        mainPage = new MainPage(driver);
        loginPage = mainPage.clickOnSignIn();
        loginPage.login(email, password);
        Assert.assertTrue(loginPage.loggedIn());
    }

    @Test
    public void createAccount(){
        faker = new Faker();
        String firstName = faker.name().firstName();
        String lastName = faker.name().lastName();
        String email = faker.internet().emailAddress();
        String password = LoginPage.generatePassword();
        mainPage = new MainPage(driver);
        loginPage = mainPage.clickOnCreateAccount();
        loginPage.createAccount(firstName, lastName, email, password);
        Assert.assertTrue(loginPage.successUp());
    }
}
