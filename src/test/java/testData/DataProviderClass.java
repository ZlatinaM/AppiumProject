package testData;

import org.testng.annotations.DataProvider;

public class DataProviderClass {

    @DataProvider
    public static Object[][] getData() {
        return new Object[][]{{"Bulgaria", "Bulgaria", "abc", "Female"}};
    }

}
