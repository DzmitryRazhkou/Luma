package com.qa.pages;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
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

    public String searchValidator() {
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

    public String getProductSorted() {
        By productSortedLocator = By.cssSelector("div[class='products wrapper grid products-grid']:nth-child(2) li:nth-of-type(2) div div div span span span span");
        wait.until(ExpectedConditions.presenceOfElementLocated(productSortedLocator));
        String productPrice = driver.findElement(productSortedLocator).getText();
        System.out.println(" =====> " +productPrice+ " <===== ");
        return productPrice;
    }
}
