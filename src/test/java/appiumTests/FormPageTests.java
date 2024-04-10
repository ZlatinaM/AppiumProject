package appiumTests;

import testData.DataProviderClass;
import pageObjects.ProductPage;
import testUtils.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;

public class FormPageTests extends BaseTest {

    ProductPage productPage;

    @Test(dataProvider = "getData", dataProviderClass = DataProviderClass.class)
    public void validateSuccessfulFillingForm(String text, String country, String name, String gender) {
        formPage.clickOnCountryDropDown()
                .scrollUntilTextIsVisible(text)
                .selectCountry(country)
                .typeName(name)
                .selectGender(gender);
        productPage = formPage.clickOnLetsShopBtn()
                .waitUntilPageIsLoaded();

        Assert.assertEquals(productPage.getPageTitle(), "Products");
    }

    @Test
    public void verifyToastMessageWhenNoNameIsGiven() {
        formPage.clickOnCountryDropDown()
                .selectCountry("Albania")
                .clickOnLetsShopBtn();

        Assert.assertEquals(formPage.getToastMessage(), "Please enter your name");
    }
}
