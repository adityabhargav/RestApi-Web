package com.core.caw.driverSetup;

import com.core.caw.helper.FileHelper;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;

import java.io.IOException;

public class Driver {
    public WebDriver driver = null;

    public WebDriver driverInit(String browser) throws IOException {
        if (browser.equals("chrome")) {
            System.setProperty("webdriver.chrome.driver", FileHelper.getPropFile("path.properties", "ChromeDriverPath"));
            driver = new ChromeDriver();
        } else if (browser.equals("edge")) {
            System.setProperty("webdriver.edge.driver", FileHelper.getPropFile("path.properties", "EdgeDriverPath"));
            driver = new EdgeDriver();
        }
        driver.manage().window().maximize();
        driver.manage().deleteAllCookies();
        this.setDriver(driver);
        return driver;
    }

    private void setDriver(WebDriver driver) {
        this.driver = driver;
    }

    public WebDriver getDriver() {
        return driver;
    }
}
