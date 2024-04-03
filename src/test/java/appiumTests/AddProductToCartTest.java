package appiumTests;

import pageObjects.CartPage;
import pageObjects.ProductPage;
import testUtils.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;

public class AddProductToCartTest extends BaseTest {

    CartPage cartPage;
    ProductPage productPage;

    @Test
    public void addProductToCart() {
        formPage.clickOnCountryDropDown()
                .selectCountry("Albania")
                .typeName("abc");
        productPage = formPage.clickOnLetsShopBtn()
                .waitUntilPageIsLoaded()
                .scrollUntilTextIsVisible("Converse All Star")
                .addProductToCart("Converse All Star");
        cartPage = productPage.clickOnCartButton()
                .waitUntilPageIsLoaded();

        Assert.assertEquals(cartPage.getProductNameDisplayed(), "Converse All Star");
    }
}
