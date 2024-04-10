package pageObjects;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import utils.CommonActions;

import java.util.List;

public class ProductPage extends CommonActions {

    AndroidDriver driver;

    public ProductPage(AndroidDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);

    }

    @AndroidFindBy(id = "com.androidsample.generalstore:id/productName")
    private List<WebElement> products;

    @AndroidFindBy(id = "com.androidsample.generalstore:id/productAddCart")
    private List<WebElement> addToCartButtons;

    @AndroidFindBy(id = "com.androidsample.generalstore:id/appbar_btn_cart")
    private WebElement cartBtn;

    @AndroidFindBy(id = "com.androidsample.generalstore:id/toolbar_title")
    private WebElement pageTitle;

    @AndroidFindBy(xpath = "//android.widget.TextView[@text = 'ADD TO CART']")
    private List<WebElement> listOfAddToCart;


    public ProductPage addProductToCart(String productName) {
        int productCount = products.size();
        for (int i = 0; i < productCount; i++) {
            String product = products.get(i).getText();
            if (product.equalsIgnoreCase(productName)) {
                addToCartButtons.get(i).click();
            }
        }
        return this;
    }

    public CartPage clickOnCartButton() {
        cartBtn.click();
        return new CartPage(driver);
    }

    public ProductPage scrollUntilTextIsVisible(String visibleText) {
        scrollUntilVisibilityOfText(visibleText);
        return this;

    }

    public ProductPage addProductByIndex(int index) {
        listOfAddToCart.get(index).click();
        return this;

    }

    public String getPageTitle() {
        return pageTitle.getText();

    }

    public ProductPage waitUntilPageIsLoaded() {
        waitUntilVisibilityOfElement(By.id("com.androidsample.generalstore:id/toolbar_title"));
        return this;

    }

}
