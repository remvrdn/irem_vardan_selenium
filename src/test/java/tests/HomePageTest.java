package tests;

<<<<<<< HEAD
import org.openqa.selenium.By;
=======
>>>>>>> 2ef7341 (update project for website)
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.HomePage;
<<<<<<< HEAD
import pages.CommonPage;
=======
>>>>>>> 2ef7341 (update project for website)
import utils.ConfigReader;
import utils.DriverManager;
import io.qameta.allure.Step;
import io.qameta.allure.Description;
import io.qameta.allure.Feature;
<<<<<<< HEAD
import io.qameta.allure.Story;
=======
>>>>>>> 2ef7341 (update project for website)

import static utils.DriverManager.takeScreenshot;

@Feature("Homepage")
public class HomePageTest {
    WebDriver driver;
    String baseUrl=ConfigReader.getProperty("baseUrl");
<<<<<<< HEAD
    String expectedHeader = "#1 AI-native\n" +"Omnichannel Experience\n" +"and Customer Engagement Platform";
    HomePage homePage;

    @BeforeMethod
    public void setup() {
        driver = DriverManager.getDriver(ConfigReader.getProperty("browser"));
        homePage = new HomePage(driver);
        driver.get(baseUrl);
    }


    @Test(testName = "Verify Website")
    public void verifyHeaderText() {
        String actualHeader = homePage.getHeaderText("HomePage",expectedHeader);
=======
    String expectedHeader = "#1 AI-native\n" +
            "Omnichannel Experience\n" +
            "and Customer Engagement Platform";
    HomePage homePage;

    @BeforeMethod
    @Step("Initialize browser and navigate to base URL")
    public void setup() {
        driver = DriverManager.createNewDriver();
        homePage = new HomePage(driver);
        driver.get(baseUrl);
        homePage.clickElement("HomePage", "cookieAcceptAll");
    }

    @Test(testName = "Verify Website Header Text")
    @Description("Verify that the homepage header text is correct")
    @Step("Verify homepage header")
    public void verifyHeaderText() {
        String actualHeader = homePage.getHeaderText("HomePage", "homePageHeader");
>>>>>>> 2ef7341 (update project for website)
        Assert.assertEquals(actualHeader, expectedHeader, "Header text doesn't match!");
    }

    @Test(testName = "Verify Company Menu Navigation")
<<<<<<< HEAD
    public void testCompanyMenu() {
        String actualHeader = homePage.getHeaderText("HomePage","homePageHeader");
        Assert.assertEquals(actualHeader, expectedHeader, "Header text doesn't match!");
        homePage.clickElement("HomePage","companySectioninDropdownList");
    }
    @Test(testName = "Verify Careers Page")
    public void testCareersMenu() {
        String actualHeader = homePage.getHeaderText("HomePage","homePageHeader");
        Assert.assertEquals(actualHeader, expectedHeader, "Header text doesn't match!");
        homePage.clickElement("HomePage","companySectioninDropdownList");
        homePage.clickElement("HomePage","careersinCompanySection");
        homePage.getCurrentUrl("CareersPage","careersPagePath");
        homePage.findElementByXpath("CareersPage","careersPageTitle");
    }

    @AfterMethod
    public void tearDown(ITestContext context) {
        if (driver != null) {
            String testName = context.getName();
            takeScreenshot(driver,testName); // call screenshot method
            driver.quit(); //safetly quit browser
        }
    }
}
=======
    @Description("Verify navigation through the Company menu")
    @Step("Navigate to Company section and verify")
    public void testCompanyMenu() {
        String actualHeader = homePage.getHeaderText("HomePage", "homePageHeader");
        Assert.assertEquals(actualHeader, expectedHeader, "Header text doesn't match!");
        homePage.clickElement("HomePage", "companySectioninDropdownList");
    }

    @Test(testName = "Verify Careers Page")
    @Description("Verify Careers page navigation from Company menu")
    @Step("Navigate to Careers page and verify elements")
    public void testCareersMenu() {
        String actualHeader = homePage.getHeaderText("HomePage", "homePageHeader");
        Assert.assertEquals(actualHeader, expectedHeader, "Header text doesn't match!");
        homePage.clickElement("HomePage", "companySectioninDropdownList");
        homePage.clickElement("HomePage", "careersinCompanySection");
        homePage.getCurrentUrl("CareersPage", "careersPagePath");
        homePage.findElementByXpath("CareersPage", "careersPageTitle");
    }

    @AfterMethod
    @Step("Take screenshot and quit browser")
    public void tearDown(ITestContext context) {
        if (driver != null) {
            String testName = context.getName();
            takeScreenshot(driver, testName);
            driver.quit();
        }
    }
}
>>>>>>> 2ef7341 (update project for website)
