package testUtils;

import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import pageObjects.FormPage;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.time.Duration;
import java.util.Properties;

public class BaseTest {

    public AndroidDriver driver;
    public AppiumDriverLocalService service;
    public FormPage formPage;
    Properties prop = new Properties();



    @BeforeSuite
    public void startService() throws IOException {
        FileInputStream fis = new FileInputStream(System.getProperty("user.dir") + "\\src\\main\\java\\resources\\data.properties");
        prop.load(fis);
        String ipAddress = prop.getProperty("ipAddress");
        int port = Integer.parseInt(prop.getProperty("port"));

        String appiumFilePath = System.getProperty("appiumFile") != null ? System.getProperty("appiumFile") : prop.getProperty("appiumFile");

        service = new AppiumServiceBuilder().withAppiumJS(new File(appiumFilePath))
                .withIPAddress(ipAddress).usingPort(port).build();
        service.start();
    }

    @BeforeMethod
    public void openDriver() throws URISyntaxException, MalformedURLException {
        String device = System.getProperty("androidDeviceName") != null ? System.getProperty("androidDeviceName") : prop.getProperty("androidDeviceName");
        UiAutomator2Options options = new UiAutomator2Options();
        options.setDeviceName(device);
        options.setChromedriverExecutable(System.getProperty("user.dir") + "\\src\\test\\resources\\chrome_version_123\\chromedriver.exe");
        options.setApp(System.getProperty("user.dir") + "\\src\\test\\resources\\General-Store.apk");
        int port = Integer.parseInt(prop.getProperty("port"));
        driver = new AndroidDriver(new URI("http://127.0.0.1:"+port+"/").toURL(), options);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        formPage = new FormPage(driver);
    }

    @AfterMethod
    public void closeDriver() {
        driver.quit();
    }

    @AfterSuite
    public void stopService() {
        service.stop();
    }
}
