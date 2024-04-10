package appiumTests;

import pageObjects.CartPage;
import pageObjects.GooglePage;
import pageObjects.ProductPage;
import testUtils.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;

public class CartPageTests extends BaseTest {

    ProductPage productPage;
    CartPage cartPage;
    GooglePage googlePage;


    @Test
    public void verifyTotalPurchaseAmountIsCalculatedCorrectly() {
        formPage.clickOnCountryDropDown().
                selectCountry("Albania")
                .typeName("abc");
        productPage = formPage.clickOnLetsShopBtn()
                .waitUntilPageIsLoaded()
                .addProductByIndex(0)
                .addProductByIndex(0);
        cartPage = productPage.clickOnCartButton()
                .waitUntilPageIsLoaded();

        Assert.assertTrue(cartPage.theTotalPurchaseAmountIsCalculatedCorrectly());
    }

    @Test
    public void verifyTermsOfConditionsPopUp() {
        formPage.clickOnCountryDropDown()
                .selectCountry("Albania")
                .typeName("abc");
        productPage = formPage.clickOnLetsShopBtn()
                .waitUntilPageIsLoaded()
                .scrollUntilTextIsVisible("Converse All Star")
                .addProductToCart("Converse All Star");
        cartPage = productPage.clickOnCartButton()
                .waitUntilPageIsLoaded()
                .readTermsOfConditions();

        Assert.assertEquals(cartPage.verifyTermsOfConditionsPopUpTitle(), "Terms Of Conditions");
        cartPage.closeTermsOfCondition();
    }

    @Test
    public void verifyPurchaseButtonLeadsToGooglePage() {
        formPage.clickOnCountryDropDown()
                .selectCountry("Albania")
                .typeName("abc");
        productPage = formPage.clickOnLetsShopBtn()
                .waitUntilPageIsLoaded()
                .scrollUntilTextIsVisible("Converse All Star")
                .addProductToCart("Converse All Star");
        cartPage = productPage.clickOnCartButton()
                .waitUntilPageIsLoaded()
                .selectCheckBox();
        googlePage = cartPage.visitTheWebsiteToCompletePurchase()
                .switchContext()
                .scrollInWeb()
                .acceptCookies()
                .searchInTheField("rahul shetty academy");
    }
}
