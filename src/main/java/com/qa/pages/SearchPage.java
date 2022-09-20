package com.qa.pages;

import org.apache.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class SearchPage {
    private WebDriver driver;
    private WebDriverWait wait;
    private Logger log;

    public SearchPage(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        log = Logger.getLogger(MainPage.class);
    }

    private WebElement getSearchProductValidation() {
        By searchProductValidationLocator = By.cssSelector("h1[class='page-title'] span");
        wait.until(ExpectedConditions.presenceOfElementLocated(searchProductValidationLocator));
        return driver.findElement(searchProductValidationLocator);
    }

    public String searchValidator() throws InterruptedException {
        try {
            log.info("Product of the Search");
            System.out.println(getSearchProductValidation().getText());
            return getSearchProductValidation().getText();
        } catch (TimeoutException y) {
            System.out.println(" =====> Provide Another Locator <===== ");
            return null;
        }
    }

    private List<WebElement> getSorter() {
        By sorterLocator = By.xpath("(//*[@id='sorter'])[1]/option");
        wait.until(ExpectedConditions.presenceOfElementLocated(sorterLocator));
        return driver.findElements(sorterLocator);
    }

    public void getSortText(String sort) {
        log.warn("User wants to get all list of sorters. ");
        List<WebElement> listSorter = getSorter();
        for (WebElement s : listSorter) {
            if (s.getText().equals(sort)) {
                log.info(" =====> " +sort+ " <===== ");
                s.click();
                break;
            }
        }
    }

    public String getProductSorted() throws InterruptedException {
        By productSortedLocator = By.cssSelector("div[class='products wrapper grid products-grid']:nth-child(2) li:nth-of-type(2) div div div span span span span");
        wait.until(ExpectedConditions.presenceOfElementLocated(productSortedLocator));
        String productPrice = driver.findElement(productSortedLocator).getText();
        System.out.println(" =====> " +productPrice+ " <===== ");
        return productPrice;
    }

    private WebElement getSize(){
        By sizeLocator = By.cssSelector("div[class='products wrapper grid products-grid']:nth-child(2) li:nth-of-type(2) div div[class='swatch-attribute size'] div:nth-of-type(4)");
        wait.until(ExpectedConditions.presenceOfElementLocated(sizeLocator));
        return driver.findElement(sizeLocator);
    }

    private WebElement getColor(){
        By colorLocator = By.cssSelector("div[class='products wrapper grid products-grid']:nth-child(2) li:nth-of-type(2) div div[class='swatch-attribute color'] div:nth-of-type(2)");
        wait.until(ExpectedConditions.presenceOfElementLocated(colorLocator));
        return driver.findElement(colorLocator);
    }

    private WebElement approachToAddToCartButton() {
        By fullElementLocator = By.cssSelector("div[class='products wrapper grid products-grid']:nth-child(2) li:nth-of-type(2)");
        wait.until(ExpectedConditions.presenceOfElementLocated(fullElementLocator));
        return driver.findElement(fullElementLocator);
    }

    private WebElement getAddToCartButton() {
        By addToCartLocator = By.cssSelector("div[class='products wrapper grid products-grid']:nth-child(2) li:nth-of-type(2) div div button span");
        wait.until(ExpectedConditions.presenceOfElementLocated(addToCartLocator));
        return driver.findElement(addToCartLocator);
    }

    public void doAddToCart() {
        log.info("User selects size of the product. ");
        getSize().click();
        log.info("User selects color of the product. ");
        getColor().click();

        Actions act = new Actions(driver);
        try {
            log.info("User makes mouse over to `Add To Cart` button. ");
            act.moveToElement(approachToAddToCartButton()).build().perform();
        } catch (ElementNotInteractableException y){
            System.out.println(y);
        }
        log.info("User clicks on the `Add To Cart` button. ");
        getAddToCartButton().click();
    }

    private WebElement getCart() {
        By cartLocator = By.cssSelector("a[class='action showcart']");
        wait.until(ExpectedConditions.presenceOfElementLocated(cartLocator));
        return driver.findElement(cartLocator);
    }

    public String getProductPriceInTheCart() {
        By cartLocator = By.cssSelector("div[class='price-container'] span span span span");
        wait.until(ExpectedConditions.presenceOfElementLocated(cartLocator));
        log.info("User validates price from product in the cart");
        String txt = driver.findElement(cartLocator).getText();
        System.out.println("=====> " +txt+ " <===== ");
        return txt;
    }

    public void doClickOnTheCart(){
        log.info("User clicks on the cart");
        getCart().click();
    }

    private WebElement getProceedToCheckOut() {
        By proceedToCheckOutLocator = By.cssSelector("#top-cart-btn-checkout");
        wait.until(ExpectedConditions.presenceOfElementLocated(proceedToCheckOutLocator));
        return driver.findElement(proceedToCheckOutLocator);
    }

    public ShippingAddressPage doClickCartAndProceed() {
        log.info("User clicks on the cart");
        getCart().click();
        log.info("User clicks on the proceed to checkout");
        getProceedToCheckOut().click();
        return new ShippingAddressPage(driver);
    }

}
