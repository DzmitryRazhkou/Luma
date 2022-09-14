package com.qa.luma.test;

import com.qa.browser.BrowserFactory;
import com.qa.enums.Browsers;
import com.qa.pages.MainPage;
import com.qa.pages.SearchPage;
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
public class SearchPageTest {
    private WebDriver driver;

    @BeforeMethod
    public void startUp() throws MalformedURLException {
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

    @Test(priority = 1)
    public void searchTest(){
        String product = "Hoodie";
        String expSearchResult = "Search results for: 'Hoodie'";

        MainPage mainPage = new MainPage(driver);
        SearchPage searchPage = mainPage.searchProduct(product);
        String actSearchResult = searchPage.searchValidator();
        Assert.assertEquals(expSearchResult, actSearchResult);
    }

    @Test(priority = 2)
    public void validateProductSortTest() {
        String product = "Hoodie";
        String price = "Price";
        MainPage mainPage = new MainPage(driver);
        SearchPage searchPage = mainPage.searchProduct(product);
        searchPage.getSortText(price);
        String actRes = searchPage.getProductSorted();
        String expRes = "$70.00";
        Assert.assertEquals(expRes, actRes);
    }
}
