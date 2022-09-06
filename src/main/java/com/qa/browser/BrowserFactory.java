package com.qa.browser;

import com.qa.enums.Browsers;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;

import java.net.MalformedURLException;
import java.net.URL;

public class BrowserFactory {

    public static String url;

    public static WebDriver getBrowser(Browsers browser) throws MalformedURLException {
        switch (browser) {
//            Google Chrome:
            case CHROME:
                return getChromeDriver();
            case CHROME_HEADLESS:
                return getChromeHeadlessDriver();
            case CHROME_GRID:
                return getChromeGridDriver();
//            Marionette Firefox:
            case FIREFOX:
                return getFirefoxDriver();
            case FIREFOX_HEADLESS:
                return getFirefoxHeadlessDriver();
            case FIREFOX_GRID:
                return getFirefoxGridDriver();
//                Apple Safari:
            default:
                return getSafariDriver();
        }
    }

//    Google Chrome:

    private static WebDriver getChromeDriver() {
        WebDriverManager.chromedriver().setup();
        System.out.println(" =====> Selenium Chrome <===== ");
        return new ChromeDriver();
    }

    private static ChromeDriver getChromeHeadlessDriver() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless");
        options.addArguments("window-size=1400,1000");
        WebDriverManager.chromedriver().setup();
        System.out.println(" =====> Selenium Chrome Headless <===== ");
        return new ChromeDriver(options);
    }

    private static WebDriver getChromeGridDriver() throws MalformedURLException {
        ChromeOptions chromeOptions = new ChromeOptions();
        WebDriverManager.chromedriver().setup();
        System.out.println(" =====> Selenium Chrome Grid <===== ");
        return new RemoteWebDriver(new URL(url), chromeOptions);
    }

//    Marionette Firefox:

    private static WebDriver getFirefoxDriver() {
        WebDriverManager.firefoxdriver().setup();
        System.out.println(" =====> Selenium Firefox <===== ");
        return new FirefoxDriver();
    }

    private static WebDriver getFirefoxHeadlessDriver() {
        FirefoxOptions options = new FirefoxOptions();
        options.addArguments("--headless");
        options.addArguments("window-size=1400,1000");
        WebDriverManager.firefoxdriver().setup();
        System.out.println(" =====> Selenium Firefox Headless <===== ");
        return new FirefoxDriver(options);
    }

    private static WebDriver getFirefoxGridDriver() throws MalformedURLException {
        FirefoxOptions firefoxOptionsOptions = new FirefoxOptions();
        WebDriverManager.firefoxdriver().setup();
        System.out.println(" =====> Selenium Firefox Grid <===== ");
        return new RemoteWebDriver(new URL(url), firefoxOptionsOptions);
    }

    //    Apple Safari:

    private static WebDriver getSafariDriver() {
        WebDriverManager.safaridriver().setup();
        System.out.println(" =====> Selenium Safari <===== ");
        return new SafariDriver();
    }


}
