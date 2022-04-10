package com.core.caw.pages.web;

import com.core.caw.commons.BasePage;
import com.core.caw.commons.ElementLocators;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class SearchItemPage extends BasePage {
    WebDriver driver;

    public SearchItemPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
    }

    @FindBy(css = ElementLocators.SearchItemPage.SEARCH_LABEL)
    private WebElement searchLabelTxt;

    @FindBy(xpath = ElementLocators.SearchItemPage.TSHIRT_TAB)
    private WebElement tshirtTab;

    @FindBy(css = ElementLocators.SearchItemPage.ITEM_SIZE_L)
    private WebElement largeSize;

    @FindBy(css = ElementLocators.SearchItemPage.ITEM_SIZE_M)
    private WebElement mediumSize;

    @FindBy(css = ElementLocators.SearchItemPage.ITEM_SIZE_S)
    private WebElement smallSize;

    @FindBy(xpath = ElementLocators.SearchItemPage.TSHIRT_BAR)
    private List<WebElement> shirtTab;

    public boolean isSearchOccured(String search) {
        super.waitForPageLoad(searchLabelTxt);
        boolean flag = false;
        try {
            if (searchLabelTxt.getText().replaceAll("[^a-z[A-Z][0-9]]", "").toLowerCase().equals(search.toLowerCase())) {
                flag = true;
            }
        } catch (Exception e) {
            System.out.println("Search Creiteria not matched");
        }
        return flag;
    }

    private void navTshirtTab() {
        tshirtTab.click();
    }

    private void clickOnItemSize() {
        navTshirtTab();
        super.waitForPageLoad(shirtTab.get(3));
        largeSize.click();
    }

    public boolean chkIfLargeSizeClk() {
        boolean flag = false;
        this.clickOnItemSize();
        try {
            flag = largeSize.isSelected();
        } catch (Exception e) {
        }
        return flag;
    }
}
