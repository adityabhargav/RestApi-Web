package com.runner.caw;

import com.core.caw.pages.web.ContactPage;
import com.core.caw.pages.web.HomePage;
import com.core.caw.pages.web.SearchItemPage;
import com.runner.caw.Listeners.Listener;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.awt.*;

@Listeners(Listener.class)
public class WebRunner extends BaseTest {

    @Test(priority = 0, description = "Validate whether the search suggestion is not given to the user until 3 characters are populated")
    public void InputBoxTest() throws InterruptedException {
        HomePage homePage = new HomePage(super.driver);
        Assert.assertTrue(homePage.enterLessThanThreeSearch("dr"), "search suggestion is populated on user until 3 characters");
        Assert.assertTrue(homePage.enterMoreThanThreeSearch("dre"), "search suggestion is not populated to the user after 3 characters");
    }

    @Test(priority = 1, description = "Validate results are displayed according to the search made by the user")
    public void SearchResultOccur() {
        HomePage homePage = new HomePage(super.driver);
        Assert.assertTrue(homePage.isSearchMatchedOnOccur("Summer"), "results are not displaying according to the search made by the user");
    }

    @Test(priority = 2, description = "Validate results are displayed according to the search made by the user")
    public void selectLarge() {
        SearchItemPage searchItemPage = new SearchItemPage(super.driver);
        Assert.assertTrue(searchItemPage.chkIfLargeSizeClk(), "Large opion not selected");
    }

    @Test(priority = 3, description = "Validate whether the user is able to upload a file on the contact us page")
    public void UploadFile() throws AWTException, InterruptedException {
        ContactPage countPg = new ContactPage(super.driver);
        countPg.navToContactPg();
    }
}
