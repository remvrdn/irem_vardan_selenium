package tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.CommonPage;
import utils.ConfigReader;
import utils.DriverManager;
import io.qameta.allure.Step;
import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;

import static utils.DriverManager.takeScreenshot;

@Feature("Homepage")
public class HomePageTest {
    WebDriver driver;
    String baseUrl=ConfigReader.getProperty("baseUrl");
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
        Assert.assertEquals(actualHeader, expectedHeader, "Header text doesn't match!");
    }

    @Test(testName = "Verify Company Menu Navigation")
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
