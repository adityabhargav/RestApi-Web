package com.runner.caw.Listeners;

import com.aventstack.extentreports.Status;
import com.core.caw.reports.ExtentTestManager;
import com.core.caw.reports.ReportManager;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.util.Arrays;
import java.util.Iterator;

public class Listener implements ITestListener {

    @Override
    public void onTestStart(ITestResult result) {
        System.out.println(result.getMethod().getMethodName());
        ExtentTestManager.startTest(result.getMethod().getMethodName());
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        System.out.println("Success of test cases and its details are : " + result.getName());
        ExtentTestManager.getTest().log(Status.PASS, result.getName() + " :: Test passed");
    }

    @Override
    public void onTestFailure(ITestResult result) {
        System.out.println("Failure of test cases and its details are : " + result.getName());
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        System.out.println("Skip of test cases and its details are : " + result.getName());
        ExtentTestManager.getTest().log(Status.SKIP, "Test Skipped");
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
    }

    @Override
    public void onStart(ITestContext context) {

    }

    @Override
    public void onFinish(ITestContext context) {
        ExtentTestManager.endTest();
        ReportManager.getInstance().flush();
    }
}