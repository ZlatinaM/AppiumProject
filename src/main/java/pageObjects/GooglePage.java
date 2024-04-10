package pageObjects;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utils.CommonActions;

import java.util.Set;

public class GooglePage extends CommonActions {

    AndroidDriver driver;

    public GooglePage(AndroidDriver driver){
        super(driver);
        this.driver = driver;
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);

    }

    @FindBy(xpath = "//button[@id='L2AGLb']")
    private WebElement acceptCookiesBtn;

    @FindBy(name = "q")
    private WebElement googleTextField;

    public GooglePage switchContext() {
        Set<String> contexts = driver.getContextHandles();
        for (String context : contexts) {
            if (context.contains("WEBVIEW")) {
                driver.context(context);
                break;
            }

        }
        return this;
    }

    public GooglePage scrollInWeb(){
        scrollInWebContext();
        return this;
    }

    public GooglePage acceptCookies(){
        clickToElement(acceptCookiesBtn);
        return this;
    }

    public GooglePage searchInTheField(String text){
        googleTextField.sendKeys(text);
        return this;
    }

}
