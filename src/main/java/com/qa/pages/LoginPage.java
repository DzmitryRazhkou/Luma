package com.qa.pages;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoginPage {

    private WebDriver driver;
    private WebDriverWait wait;
    private Logger log;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        log = Logger.getLogger(MainPage.class);
    }

    private WebElement getCustomerLogin(){
        By customerLoginLocator = By.cssSelector("span[data-ui-id='page-title-wrapper']");
        wait.until(ExpectedConditions.presenceOfElementLocated(customerLoginLocator));
        return driver.findElement(customerLoginLocator);
    }

    public boolean customerLoginValidate(){
        String text = getCustomerLogin().getText();
        System.out.println(" =====> " +text+ " <===== ");
        return getCustomerLogin().isDisplayed();
    }


}
