package com.core.caw.pages.web;

import com.core.caw.commons.BasePage;
import com.core.caw.commons.Constants;
import com.core.caw.commons.ElementLocators;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends BasePage {

    WebDriver driver;

    public HomePage(WebDriver driver) {
        super(driver);
        this.driver = driver;
    }

    @FindBy(css = ElementLocators.HomePage.INPUT_SEARCHBOX)
    private WebElement inputSearchBox;

    @FindBy(css = ElementLocators.HomePage.INPUT_SEARCHBOX_SUBMIT)
    private WebElement inputSearchBoxSubmit;

    @FindBy(css = ElementLocators.HomePage.INPUT_SEARCHBOX_DrpDown)
    private WebElement inputSearchBoxDrpDwn;

    @FindBy(css = ElementLocators.Common.LOGO)
    private WebElement logo;

    private boolean isSearchPopedInEntry(String input) {
        boolean flag = false;
        super.waitForPageLoad(logo);
        inputSearchBox.click();
        inputSearchBox.clear();
        inputSearchBox.sendKeys(input);
        if (super.isDisplayed(inputSearchBoxDrpDwn, Constants.LOW_WAIT)) {
            flag = true;
        }
        return flag;
    }

    public boolean enterLessThanThreeSearch(String input) throws InterruptedException {
        if (!isSearchPopedInEntry(input)) {
            return true;
        }
        return false;
    }

    public boolean enterMoreThanThreeSearch(String input) {
        return isSearchPopedInEntry(input);
    }

    private void enterSearch(String input) {
        super.waitForElement(inputSearchBox, Constants.MEDIUM_WAIT);
        inputSearchBox.click();
        inputSearchBox.clear();
        inputSearchBox.sendKeys(input);
        inputSearchBoxSubmit.click();
    }

    public boolean isSearchMatchedOnOccur(String input) {
        this.enterSearch(input);
        SearchItemPage searchItemPage = new SearchItemPage(driver);
        return searchItemPage.isSearchOccured(input);
    }
}
