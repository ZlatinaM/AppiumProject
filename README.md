The framework is built with Appium/Java/TestNG. The Page Object Model design pattern is used toghether with Page factory for initialization of the web elements. POM is enhanced with Fluent Page Object Model in which there is a chaning between the methods of a given page.
The common actions are located in a separate class and that class is extended by the page classes.
There is a property file that allows some commonly used variables to be easily maintained.
The testng.xml file is the runner file.
In some tests @DataProvider option is used just for exercise this approach.
ExtentReports is the reporting tool that is used in the framework. The TestNG listener interafce is used to log the statuses of the tests and to attach screenshot to the report.
The framework has integration with maven, so the tests can be run via the terminal given the profile described in the testng.xml file (mvn test -PRegression), not only via the IDE.
