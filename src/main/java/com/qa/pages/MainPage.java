package com.qa.pages;

import org.apache.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class MainPage {

    private final WebDriver driver;
    private final WebDriverWait wait;
    private final Logger log;

    public MainPage(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        log = Logger.getLogger(MainPage.class);
    }

//    Logo
    private WebElement getLogo() {
        By logoLocator = By.cssSelector("a.logo");
        return driver.findElement(logoLocator);
    }

    public boolean logoIsDisplayed() {
        log.info("The main page has been validated. ");
        System.out.println(" =====> " + getLogo().getText() + " <===== ");
        return getLogo().isDisplayed();
    }

    //    Search:
    private WebElement getSearchField() {
        By searchFieldLocator = By.id("search");
        wait.until(ExpectedConditions.visibilityOfElementLocated(searchFieldLocator));
        return driver.findElement(searchFieldLocator);
    }

    public SearchPage searchProduct(String product){
        log.info("User types a product name on the search field and presses the ENTER/RETURN button for the search.");
        getSearchField().sendKeys(product, Keys.RETURN);
        log.info("User navigates on the search page. ");
        return new SearchPage(driver);
    }

//    Login/ Create an Account:

    private WebElement getLoginBtn() {
        By loginBtnLocator = By.cssSelector("ul[class='header links']:nth-child(2) li[class='authorization-link']");
        wait.until(ExpectedConditions.visibilityOfElementLocated(loginBtnLocator));
        return driver.findElement(loginBtnLocator);
    }

    private WebElement getCreateAccountBtn() {
        By createAccountBtnLocator = By.cssSelector("ul[class='header links']:nth-child(2) li:nth-of-type(3)");
        wait.until(ExpectedConditions.visibilityOfElementLocated(createAccountBtnLocator));
        return driver.findElement(createAccountBtnLocator);
    }

    public LoginPage clickOnSignIn() {
        log.info("User clicks on the login page.");
        getLoginBtn().click();
        log.info("User navigates on the login page.");
        return new LoginPage(driver);
    }

    public LoginPage clickOnCreateAccount() {
        log.info("User clicks on the login page.");
        getCreateAccountBtn().click();
        log.info("User navigates on the login page.");
        return new LoginPage(driver);
    }

//    Top Sellers:

    private int getTopSellers() {
        By topSellersLocator = By.cssSelector("ol[class='product-items widget-product-grid'] li");
        wait.until(ExpectedConditions.presenceOfElementLocated(topSellersLocator));
        List<WebElement> list = driver.findElements(topSellersLocator);
        List<String> listProduct = new ArrayList<>();

        for (WebElement webElement : list) {
            System.out.println(webElement.getText());
            listProduct.add(webElement.getText());
        }
        System.out.println(" =====> The Amount Of Top Sellers Product is: " +listProduct.size()+ " <===== ");
        return listProduct.size();
    }

    public int topSellers() {
        By topSellersLocator = By.cssSelector("ol[class='product-items widget-product-grid'] li");
        WebElement playlist = driver.findElement(topSellersLocator);

        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView();", playlist);
        log.warn("JS scrolls down to the web element.");
        return getTopSellers();
    }
}
