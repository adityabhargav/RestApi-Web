package com.runner.caw;

import com.core.caw.helper.DataHelper;
import com.core.caw.helper.ExcelHelper;
import com.core.caw.helper.FileHelper;
import com.core.caw.pages.api.ContactPage;
import com.core.caw.pages.api.UserPage;
import org.testng.Assert;
import org.testng.annotations.*;
import org.testng.asserts.SoftAssert;

@Listeners(com.runner.caw.Listeners.ApiListener.class)
public class ApiRunner {

    private UserPage userPage = new UserPage();
    private ContactPage contactPage = new ContactPage();
    private DataHelper dataHelper = new DataHelper();
    private ExcelHelper excelHelper = new ExcelHelper();
    private String contactId = null;

    @Test(priority = 0, description = "Adding user")
    public void AddUser() throws Exception {
        String usrData = String.valueOf(excelHelper.userData("AddUser")[0][0]);
        SoftAssert softAssert = new SoftAssert();
        String[][] result = userPage.addUser(usrData);
        //Validations....
        Assert.assertTrue(result[0][0].equals("201"), "Obtained response is " + result[0][0] + " is not matching with_expected response 201-Created");
        String userData = dataHelper.getValFromObject("user", result[0][1]);
        softAssert.assertTrue(dataHelper.getValFromObject("firstName", userData).equals(dataHelper.getValFromObject("firstName", usrData)), "first name is not matching with the given input");
        softAssert.assertTrue(dataHelper.getValFromObject("lastName", userData).equals(dataHelper.getValFromObject("lastName", usrData)), "last name is not matching with the given input");
        softAssert.assertTrue(dataHelper.getValFromObject("email", userData).equals(dataHelper.getValFromObject("email", usrData)), "email is not matching with the given input");
        softAssert.assertAll();
        FileHelper.getPropFile("url.properties", "ApiBaseUrl");
    }

    @Test(priority = 1, description = "Getting details from user")
    public void FetchUserInvalidToken() throws Exception {
        String accFirstName = FileHelper.getPropFile("login.properties", "firstName");
        String accLastName = FileHelper.getPropFile("login.properties", "lastName");
        String[][] result = userPage.fetchUserInvalidToken();
        //Validations....
        Assert.assertTrue(result[0][0].equals("401"), "Obtained response is " + result[0][0] + " is not matching with expected response 401-Unauthorized");
    }

    @Test(priority = 2, description = "Getting details from user")
    public void FetchUser() throws Exception {
        SoftAssert softAssert = new SoftAssert();
        String accFirstName = FileHelper.getPropFile("login.properties", "firstName");
        String accLastName = FileHelper.getPropFile("login.properties", "lastName");
        String[][] result = userPage.fetchUser();
        //Validations....
        Assert.assertTrue(result[0][0].equals("200"), "Obtained response is " + result[0][0] + " is not matching with expected response 200-OK");
        softAssert.assertTrue(dataHelper.getValFromObject("firstName", result[0][1]).equals(accFirstName), "first name is not matching with the given input");
        softAssert.assertTrue(dataHelper.getValFromObject("lastName", result[0][1]).equals(accLastName), "last name is not matching with the given input");
        softAssert.assertAll();
    }

    @Test(priority = 3, description = "Adding Contact to the user")
    public void AddContact() throws Exception {
        String usrData = String.valueOf(excelHelper.userData("AddContact")[0][0]);
        SoftAssert softAssert = new SoftAssert();
        String[][] result = contactPage.addContact(usrData);
        //Validations....
        Assert.assertTrue(result[0][0].equals("201"), "Contact is not created");
        contactId = dataHelper.getValFromObject("_id", result[0][1]);
        String[] params = {"firstName", "lastName", "email", "birthdate", "phone", "street1", "street2", "city", "stateProvince", "postalCode", "country"};
        for (String param : params) {
            softAssert.assertTrue(dataHelper.getValFromObject(param, result[0][1]).equals(dataHelper.getValFromObject(param, usrData)), param + " is not matching with the given input");
        }
        softAssert.assertAll();
    }

    @Test(priority = 4, description = "update the given contact")
    public void UpdateContact() throws Exception {
        String usrData = String.valueOf(excelHelper.userData("UpdateContact")[0][0]);
        if (contactId != null) {
            String result[][] = contactPage.contactOperations("updateContact", usrData, contactId);
            Assert.assertTrue(result[0][0].equals("200"), "Upadtion details failure Expected 200 OK is not retrieved ., Actual status code " + result[0][0]);
        } else {
            Assert.fail("Contact Id is not retrieved");
        }
    }

    @Test(priority = 5, description = "Delete the given contact")
    public void DeleteContact() throws Exception {
        if (contactId != null) {
            String result[][] = contactPage.contactOperations("deleteContact", "", contactId);
            Assert.assertTrue(result[0][0].equals("200"), "Deletion contact failure Expected 200 OK is not retrieved ., Actual status code " + result[0][0]);
        } else {
            Assert.fail("Contact Id is not retrieved");
        }
    }

    @Test(priority = 6, description = "Get the given contact after deletion")
    public void GetContact() throws Exception {
        System.out.println(contactId);
        if (contactId != null) {
            String result[][] = contactPage.contactOperations("retrieveContact", "", contactId);
            Assert.assertTrue(result[0][0].equals("404"), "Expected 404 Not found is not retrieved ., Actual status code " + result[0][0]);
        } else {
            Assert.fail("Contact Id is not retrieved");
        }
    }


}