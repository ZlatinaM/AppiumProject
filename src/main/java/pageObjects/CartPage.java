package pageObjects;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.CommonActions;

import java.time.Duration;
import java.util.List;

public class CartPage extends CommonActions {
    AndroidDriver driver;

    public CartPage(AndroidDriver driver){
        super(driver);
        this.driver = driver;
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    @AndroidFindBy(id = "com.androidsample.generalstore:id/termsButton")
    private WebElement termOfConditionsBtn;

    @AndroidFindBy(className = "android.widget.CheckBox")
    private WebElement sendMeEmailCheckBox;

    @AndroidFindBy(id = "com.androidsample.generalstore:id/btnProceed")
    private WebElement visitTheWebSiteBth;

    @AndroidFindBy(id = "android:id/button1")
    private WebElement closeTermOfConditionsBtn;

    @AndroidFindBy(xpath = "//android.widget.TextView[@resource-id='com.androidsample.generalstore:id/productPrice']")
    private List<WebElement> listOfPrices;

    @AndroidFindBy(id = "com.androidsample.generalstore:id/totalAmountLbl")
    private WebElement totalPurchaseAmount;

    @AndroidFindBy(id = "com.androidsample.generalstore:id/alertTitle")
    private WebElement termsPopUpTitle;

    @AndroidFindBy(id = "com.androidsample.generalstore:id/productName")
    private WebElement productName;


    public CartPage selectCheckBox(){
        sendMeEmailCheckBox.click();
        return this;
    }

    public CartPage readTermsOfConditions(){
        longPressAction(termOfConditionsBtn);
        return this;
    }

    public CartPage closeTermsOfCondition(){
        closeTermOfConditionsBtn.click();
        return this;
    }

    public String verifyTermsOfConditionsPopUpTitle(){
        return termsPopUpTitle.getText();
    }

    public GooglePage visitTheWebsiteToCompletePurchase(){
        visitTheWebSiteBth.click();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3));
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("com.androidsample.generalstore:id/btnProceed")));
        return new GooglePage(driver);
    }

    public boolean theTotalPurchaseAmountIsCalculatedCorrectly(){
        boolean isCalculatedCorrectly;

        int count = listOfPrices.size();
        double sum = 0;
        for (int i=0; i<count; i++){
            String price = listOfPrices.get(i).getText();

            Double formatted = formatToDouble(price);
            System.out.println(formatted);

            sum = sum+formatted;
        }
        System.out.println(sum);

        String totalAmount = totalPurchaseAmount.getText();
        Double displayedSum = formatToDouble(totalAmount);

        if (sum == displayedSum){
            isCalculatedCorrectly = true;

        }else{
            isCalculatedCorrectly = false;
        }

        return isCalculatedCorrectly;
    }

    public String getProductNameDisplayed(){
        return productName.getText();
    }

    public CartPage waitUntilPageIsLoaded(){
        waitUntilVisibilityOfElement(By.xpath("//android.widget.TextView[@text='Total Purchase Amount:  ']"));
        return this;
    }

}
