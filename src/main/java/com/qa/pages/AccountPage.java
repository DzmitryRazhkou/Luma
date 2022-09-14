package com.qa.pages;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class AccountPage {
    private WebDriver driver;
    private WebDriverWait wait;
    private Logger log;

    public AccountPage(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        log = Logger.getLogger(MainPage.class);
    }

    private WebElement getAccountValidation(){
        By accountValidationLocator = By.cssSelector("div[class='box box-information'] div:nth-child(2) p");
        wait.until(ExpectedConditions.presenceOfElementLocated(accountValidationLocator));
        return driver.findElement(accountValidationLocator);
    }

    public boolean accountValidator() {
        try{
            System.out.println(" =====> " +getAccountValidation().getText()+" <===== ");
            return getAccountValidation().isDisplayed();
        } catch (TimeoutException y){
            System.out.println(" =====> Provide Another Locator <===== ");
            return false;
        }
    }


//    div[class='content block-collapsible-nav-content'] ul li

}
