package com.runner.caw;

import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.core.caw.driverSetup.Driver;
import com.core.caw.helper.FileHelper;
import com.core.caw.reports.ExtentTestManager;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestResult;
import org.testng.annotations.*;

import java.io.File;
import java.io.IOException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;


public class BaseTest extends Driver {

    @Parameters({"browser"})
    @BeforeTest
    public void beforeMtd(String browser) throws IOException {
        if (!browser.equals("api")) {
            super.driverInit(browser);
            super.getDriver().get(FileHelper.getPropFile("url.properties", "WebBaseUrl"));
        }
    }

    @Parameters({"browser"})
    @AfterMethod
    public void aftMtd(ITestResult result, String browser) {
        if (!result.isSuccess() && !browser.equals("api")) {
            try {
                SimpleDateFormat sdf3 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                Timestamp timestamp = new Timestamp(System.currentTimeMillis());
                String targetLocation = null;
                File screenshotFile = null;
                String testClassName = result.getInstanceName().trim();
                String timeStamp = timestamp.toString();
                String testMethodName = result.getName().toString().trim();
                String screenShotName = testMethodName + timeStamp + ".png";
                String fileSeperator = System.getProperty("file.separator");
                String reportsPath = System.getProperty("user.dir") + fileSeperator + "TestReport" + fileSeperator + "screenshots";
                System.out.println("Screen shots reports path - " + reportsPath);
                try {
                    File file = new File(reportsPath + fileSeperator + testClassName);
                    if (!file.exists()) {
                        if (file.mkdirs()) {
                            System.out.println("Directory: " + file.getAbsolutePath() + " is created!");
                        } else {
                            System.out.println("Failed to create directory: " + file.getAbsolutePath());
                        }
                    }
                    screenshotFile = ((TakesScreenshot) super.getDriver()).getScreenshotAs(OutputType.FILE);
                    targetLocation = reportsPath + fileSeperator + testClassName + fileSeperator + screenShotName;// define
                    // location
                    File targetFile = new File(targetLocation);
                    System.out.println("Screen shot file location - " + screenshotFile.getAbsolutePath());
                    System.out.println("Target File location - " + targetFile.getAbsolutePath());
//                    FileHandler.copy(screenshotFile, targetFile);
                }
//                catch (FileNotFoundException e) {
//                    System.out.println("File not found exception occurred while taking screenshot " + e.getMessage());
//                }
                catch (Exception e) {
                    System.out.println("An exception occurred while taking screenshot " + e.getCause());
                }
                try {
                    ExtentTestManager.getTest().fail("Screenshot", MediaEntityBuilder.createScreenCaptureFromPath(screenshotFile.getAbsolutePath()).build());
                } catch (IOException e) {
                    System.out.println("An exception occured while taking screenshot " + e.getCause());
                }
                ExtentTestManager.getTest().log(Status.FAIL, "Test Failed :: " + result.getThrowable().getMessage());
            } catch (Exception e) {

            }
            super.getDriver().close();
        }
    }

    @AfterTest
    public void aftTst() {
        super.getDriver().quit();
    }
}
