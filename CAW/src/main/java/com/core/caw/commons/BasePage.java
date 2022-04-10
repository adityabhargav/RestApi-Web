package com.core.caw.commons;

import com.core.caw.driverSetup.Driver;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.awt.*;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class BasePage extends Driver {

    protected WebDriver driver;

    public static enum ElementAttribute {text, name, selected, enabled, clickable, checkable, scrollable, focusable, checked}

    public BasePage(WebDriver driver) {
//        super();
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public boolean isDisplayed(WebElement webElement) {
        return this.waitForElement(webElement, 60);
    }

    public boolean isDisplayed(WebElement webElement, int timeout) {
        int time = timeout;
        boolean flag = false;
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(time));
            wait.until(ExpectedConditions.visibilityOf(webElement));
            flag = true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return flag;
    }

    public String getAttribute(WebElement we, ElementAttribute attr) {
        if (this.isDisplayed(we)) {
            switch (attr) {
                case text:
                    return we.getAttribute(attr.toString());
                case name:
                    return we.getAttribute(attr.toString());
                case selected:
                    return we.getAttribute(attr.toString());
                case clickable:
                    return we.getAttribute(attr.toString());
                case checkable:
                    return we.getAttribute(attr.toString());
                case scrollable:
                    return we.getAttribute(attr.toString());
                case focusable:
                    return we.getAttribute(attr.toString());
                case checked:
                    return we.getAttribute(attr.toString());
                case enabled:
                    return we.getAttribute(attr.toString());
                default:
                    break;
            }
        } else {

            return null;
        }
        return null;
    }

    public boolean waitForElement(WebElement webElement, int timeOut) {
        boolean flag = false;
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
            wait.until(ExpectedConditions.visibilityOf(webElement));
            flag = true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return flag;
    }

    public boolean verifyElementEnabled(WebElement webElement) {
        if (this.getAttribute(webElement, ElementAttribute.enabled).equalsIgnoreCase("true")) return true;
        return false;
    }

    public boolean verifyElementDisabled(WebElement ele) {
        if (this.getAttribute(ele, ElementAttribute.enabled).equalsIgnoreCase("false"))
            return true;
        return false;
    }

    public List<String> getListItems(List<WebElement> webElementList) {
        List<String> itemList = new ArrayList<String>();
        try {
            for (WebElement listElement : webElementList) {
                itemList.add(listElement.getText());
            }

            return itemList;
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Empty list found, returning the same");
        }
        return null;

    }

    public void waitForPageLoad(WebElement element) {
        this.waitForElement(element, 10);
    }

    public void uploadFile() throws AWTException, InterruptedException {
        Robot rb = new Robot();
        StringSelection str = new StringSelection("C:\\CAW\\src\\main\\resources\\uitls\\abrakadabra.txt");
        Toolkit.getDefaultToolkit().getSystemClipboard().setContents(str, null);
        rb.keyPress(KeyEvent.VK_CONTROL);
        rb.keyPress(KeyEvent.VK_V);

        rb.keyRelease(KeyEvent.VK_CONTROL);
        rb.keyRelease(KeyEvent.VK_V);

        rb.keyPress(KeyEvent.VK_ENTER);
        rb.keyRelease(KeyEvent.VK_ENTER);
        Thread.sleep(5000);
    }
}
