package pageObjects;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import utils.CommonActions;

public class FormPage extends CommonActions {

    AndroidDriver driver;

    public FormPage(AndroidDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);

    }

    @AndroidFindBy(id = "com.androidsample.generalstore:id/spinnerCountry")
    private WebElement countryDropDown;

    @AndroidFindBy(id = "com.androidsample.generalstore:id/nameField")
    private WebElement nameTextField;

    @AndroidFindBy(id = "com.androidsample.generalstore:id/radioMale")
    private WebElement maleRadioBtn;

    @AndroidFindBy(id = "com.androidsample.generalstore:id/radioFemale")
    private WebElement femaleRadioBtn;

    @AndroidFindBy(id = "com.androidsample.generalstore:id/btnLetsShop")
    private WebElement letsShopBtn;

    @AndroidFindBy(xpath = "//android.widget.Toast")
    private WebElement toastMessage;


    public FormPage clickOnCountryDropDown(){
        countryDropDown.click();
        return this;
    }
    public FormPage selectCountry(String country) {
        driver.findElement(By.xpath("//android.widget.TextView[@resource-id='android:id/text1' and @text='"+country+"']")).click();
        return this;
    }

    public FormPage typeName(String name){
        nameTextField.sendKeys(name);
        hideKeyboard();
        return this;
    }

    public FormPage selectGender(String gender){
        if (gender.equalsIgnoreCase("Male")){
            maleRadioBtn.click();
        }else if (gender.equalsIgnoreCase("Female")){
            femaleRadioBtn.click();
        }
        return this;
    }

    public ProductPage clickOnLetsShopBtn(){
        letsShopBtn.click();
        return new ProductPage(driver);
    }

    public FormPage scrollUntilTextIsVisible(String text){
        scrollUntilVisibilityOfText(text);
        return this;
    }

    public String getToastMessage(){
        return toastMessage.getAttribute("name");

    }

    public void setUpActivity(){
        startActivity("com.androidsample.generalstore/com.androidsample.generalstore.MainActivity");
    }




}
