package com.qa.pages;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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

    public boolean customerLoginValidate() {
        String text = getCustomerLogin().getText();
        System.out.println(" =====> " + text + " <===== ");
        return getCustomerLogin().isDisplayed();
    }

//    Login Customer:

    private WebElement getEmail() {
        By emailLocator = By.id("email");
        wait.until(ExpectedConditions.presenceOfElementLocated(emailLocator));
        return driver.findElement(emailLocator);
    }

    private WebElement getPassword() {
        By passwordLocator = By.id("pass");
        wait.until(ExpectedConditions.presenceOfElementLocated(passwordLocator));
        return driver.findElement(passwordLocator);
    }

    private WebElement getSignIn() {
        By signInLocator = By.xpath("(//*[contains(text(),'Sign In')])[3]");
        wait.until(ExpectedConditions.presenceOfElementLocated(signInLocator));
        return driver.findElement(signInLocator);
    }

//    Validate:

    private WebElement getLoggedIn() {
        By loggedInLocator = By.xpath("(//*[@class='logged-in'])[1]");
        wait.until(ExpectedConditions.presenceOfElementLocated(loggedInLocator));
        return driver.findElement(loggedInLocator);
    }

    private WebElement getSuccess() {
        By successLocator = By.cssSelector("div[role='alert']");
        wait.until(ExpectedConditions.presenceOfElementLocated(successLocator));
        return driver.findElement(successLocator);
    }

    public void login(String email, String password) {
        getEmail().clear();
        getEmail().sendKeys(email);
        getPassword().clear();
        getPassword().sendKeys(password);
        getSignIn().click();
    }

    public boolean loggedIn() {
        if (getLoggedIn().isDisplayed()) {
            System.out.println(" =====> The Web Element is Displayed <===== ");
            return true;
        } else {
            System.out.println(" =====> The Web Element is not Displayed <===== ");
            return false;
        }
    }

//    Create New Customer Account

    private WebElement getFirstName() {
        By firstNameLocator = By.id("firstname");
        wait.until(ExpectedConditions.presenceOfElementLocated(firstNameLocator));
        return driver.findElement(firstNameLocator);
    }

    private WebElement getLastName() {
        By lastNameLocator = By.id("lastname");
        wait.until(ExpectedConditions.presenceOfElementLocated(lastNameLocator));
        return driver.findElement(lastNameLocator);
    }

    private WebElement getEmailCustomer() {
        By emailCustomerLocator = By.id("email_address");
        wait.until(ExpectedConditions.presenceOfElementLocated(emailCustomerLocator));
        return driver.findElement(emailCustomerLocator);
    }

    private WebElement getPasswordCustomer() {
        By passwordCustomerLocator = By.id("password");
        wait.until(ExpectedConditions.presenceOfElementLocated(passwordCustomerLocator));
        return driver.findElement(passwordCustomerLocator);
    }

    private WebElement getConfirmPasswordCustomer() {
        By passwordConfirmCustomerLocator = By.id("password-confirmation");
        wait.until(ExpectedConditions.presenceOfElementLocated(passwordConfirmCustomerLocator));
        return driver.findElement(passwordConfirmCustomerLocator);
    }

    private WebElement getCreateAccount() {
        By createAccountLocator = By.xpath("(//*[contains(text(),'Create an Account')])[3]");
        wait.until(ExpectedConditions.presenceOfElementLocated(createAccountLocator));
        return driver.findElement(createAccountLocator);
    }

    public void createAccount(String firstName, String lastName, String email, String password) {
        getFirstName().clear();
        getFirstName().sendKeys(firstName);

        getLastName().clear();
        getLastName().sendKeys(lastName);

        getEmailCustomer().clear();
        getEmailCustomer().sendKeys(email);

        getPasswordCustomer().clear();
        getPasswordCustomer().sendKeys(password);

        getConfirmPasswordCustomer().clear();
        getConfirmPasswordCustomer().sendKeys(password);

        getCreateAccount().click();
    }

    public static String generatePassword() {
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789@!#$%&";
        String password = RandomStringUtils.random( 8, characters );

        String regex = "^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@!#$%&])(?=\\S+$).{8,}$";
        Pattern pattern = Pattern.compile( regex );
        Matcher matcher = pattern.matcher( password );

        if (matcher.matches()) {
            return password;
        } else {
            return generatePassword(); // recursion
        }
    }

    public boolean successUp() {
        if (getSuccess().isDisplayed()) {
            System.out.println(" =====> The Web Element is Displayed <===== ");
            return true;
        } else {
            System.out.println(" =====> The Web Element is not Displayed <===== ");
            return false;
        }
    }
}
