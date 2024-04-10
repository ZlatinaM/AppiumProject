package testUtils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class Listeners extends Reporter implements ITestListener {
    ExtentTest test;
    ExtentReports extent = Reporter.getExtentReportObject();
    AppiumDriver driver;

    @Override
    public void onTestStart(ITestResult result) {
        test = extent.createTest(result.getMethod().getMethodName());
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        test.log(Status.PASS, "Test passed");
    }

    @Override
    public void onTestFailure(ITestResult result) {

        test.fail(result.getThrowable());
        try {
            captureScreenshot(result);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public void onFinish(ITestContext context) {
        extent.flush();
    }

    public void captureScreenshot(ITestResult result) throws NoSuchFieldException, IllegalAccessException {
        driver = (AppiumDriver) result.getTestClass().getRealClass().getField("driver").get(result.getInstance());
        if (driver != null) {
            TakesScreenshot screenshotDriver = (TakesScreenshot) driver;
            String base64String = "data:image/png;base64," + screenshotDriver.getScreenshotAs(OutputType.BASE64);
            test.addScreenCaptureFromBase64String(base64String);
        }
    }
}
