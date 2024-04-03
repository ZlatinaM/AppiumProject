package testUtils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import io.appium.java_client.AppiumDriver;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;

import java.io.File;
import java.io.IOException;

public class Reporter {

    public static ExtentReports extent;
    AppiumDriver driver;

    public static ExtentReports getExtentReportObject() {
        String path = System.getProperty("user.dir") + "//reports//ExtendReport.html";
        ExtentSparkReporter report = new ExtentSparkReporter(path);
        extent = new ExtentReports();
        report.config().setTheme(Theme.DARK);
        report.config().setReportName("EndToEnd test results");
        report.config().setDocumentTitle("Test results");

        extent.attachReporter(report);
        extent.setSystemInfo("Tester", "Harry Potter");
        return extent;

    }

    /*public String getScreenshotPath(String testCaseName, AppiumDriver driver) throws IOException {
        File source = driver.getScreenshotAs(OutputType.FILE);
        String destinationFile = "reports\\" + testCaseName + ".png";
        FileUtils.copyFile(source, new File(destinationFile));
        return destinationFile;

    }*/


}




