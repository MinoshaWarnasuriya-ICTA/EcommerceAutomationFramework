package TestComponents;

import com.aventstack.extentreports.*;
import com.aventstack.extentreports.reporter.*;
import org.openqa.selenium.*;
import org.testng.*;
import resources.*;

import java.io.*;

public class Listenners extends BaseTest implements ITestListener {
    ExtentTest test;
    ExtentReports extent = extentReports.getExtentObject();
    public  void onTestStart(ITestResult result) {

        test =  extent.createTest(result.getMethod().getMethodName());

    }

    public void onTestSuccess(ITestResult result) {
    }

    public void onTestFailure(ITestResult result) {

        test.log(Status.FAIL,result.getThrowable() );
        WebDriver driver = null;
        try {
            driver = (WebDriver) result.getTestClass().getRealClass().getField("driver").get(result.getInstance());
        } catch (IllegalAccessException | NoSuchFieldException e) {
            throw new RuntimeException(e);
        }
        String path = null;
        try {
            path = getScreenShot(result.getMethod().getMethodName(),driver);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        test.addScreenCaptureFromPath(path,result.getMethod().getMethodName()+".png");
    }

    public void onTestSkipped(ITestResult result) {
    }

    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
    }

    public void onTestFailedWithTimeout(ITestResult result) {
        this.onTestFailure(result);
    }

    public void onStart(ITestContext context) {
    }

    public void onFinish(ITestContext context) {
        extent.flush();
    }
}
