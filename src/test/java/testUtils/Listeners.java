package testUtils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import io.appium.java_client.AppiumDriver;
import org.apache.commons.io.FileUtils;
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

        /*try {
            driver = (AppiumDriver) result.getTestClass().getRealClass().getField("driver").get(result.getInstance());
        } catch (Exception e) {
           e.printStackTrace();
        }

        try {
            test.addScreenCaptureFromPath(getScreenshotPath(result.getMethod().getMethodName(), driver), result.getMethod().getMethodName());
        } catch (IOException e) {
            e.printStackTrace();
        }*/

        /*try {
            driver = (AppiumDriver) result.getTestClass().getRealClass().getField("driver").get(result.getInstance());
        } catch (IllegalAccessException | NoSuchFieldException e) {
            throw new RuntimeException(e);
        }
        File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        String screenshotPath = System.getProperty("user.dir")
                + pathSeparator + "reports" + pathSeparator +
                result.getMethod().getMethodName() + ".png";
        try {
            FileUtils.copyFile(scrFile, new File(screenshotPath));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
       test.fail("reports", MediaEntityBuilder.createScreenCaptureFromPath("reports/" +
               result.getMethod().getMethodName() + ".png").build());*/

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
