package com.core.caw.pages.web;

import com.core.caw.commons.BasePage;
import com.core.caw.commons.ElementLocators;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.awt.*;

public class ContactPage extends BasePage {

    WebDriver driver;

    public ContactPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
    }

    @FindBy(css = ElementLocators.ContactPage.CONTACT_US)
    private WebElement contactUsBtn;

    @FindBy(css = ElementLocators.ContactPage.CHOOSE_FILE)
    private WebElement contactFileBtn;

    public void navToContactPg() throws AWTException, InterruptedException {
        contactUsBtn.click();
        super.waitForPageLoad(contactFileBtn);
        contactFileBtn.click();
        Thread.sleep(3500);
        super.uploadFile();
    }
}
