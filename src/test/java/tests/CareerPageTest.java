package tests;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.CareersPage;
import pages.CommonPage;
import utils.ConfigReader;
import utils.DriverManager;

import static utils.DriverManager.takeScreenshot;

public class CareerPageTest {
    WebDriver driver;
    String baseUrl= ConfigReader.getProperty("baseUrl");
    CareersPage careersPage;
    CommonPage commonPage;

    @BeforeMethod
    public void setup() {
        driver = DriverManager.getDriver(ConfigReader.getProperty("browser"));
        careersPage = new CareersPage(driver);
        commonPage = new CommonPage(driver);
        driver.get(baseUrl);
    }
    @Test(testName = "Check Careers Page Attributes")
    public void testCareersPage() {
        commonPage.clickElement("HomePage","companySectioninDropdownList");
        commonPage.clickElement("HomePage","careersinCompanySection");
        commonPage.getCurrentUrl("CareersPage","careersPagePath");
        commonPage.findElementByXpath("CareersPage","careersPageTitle");
        commonPage.getCurrentUrl("CareersPage", "careersPagePath");
        commonPage.findElementByXpath("CareersPage", "careersPageTitle");
        commonPage.findElementByXpath("CareersPage","ourLocationTitle");
        commonPage.isElementTextPresent("CareersPage","ourLocationTextArea");
        commonPage.findElementByXpath("CareersPage","ourLocationList");
        commonPage.findElementByXpath("CareersPage","lifeAtTitle");
        commonPage.isElementTextPresent("CareersPage","lifeAtTextArea");
        commonPage.findElementByXpath("CareersPage","findYourCallingTitle");
        commonPage.findElementByXpath("CareersPage","seeAllTeamsButton");
    }

    @Test(testName = "Go to Quality Assurance Page")
    public void testgoQualityAssurancePage() {
        commonPage.clickElement("HomePage","companySectioninDropdownList");
        commonPage.clickElement("HomePage","careersinCompanySection");
        commonPage.getCurrentUrl("CareersPage", "careersPagePath");
        commonPage.clickElement("CareersPage","declineAllButton");
        commonPage.findElementByXpath("CareersPage", "careersPageTitle");
        commonPage.findElementByXpath("CareersPage","ourLocationTitle");
        commonPage.isElementTextPresent("CareersPage","ourLocationTextArea");
        commonPage.findElementByXpath("CareersPage","ourLocationList");
        commonPage.findElementByXpath("CareersPage","lifeAtTitle");
        commonPage.isElementTextPresent("CareersPage","lifeAtTextArea");
        commonPage.findElementByXpath("CareersPage","findYourCallingTitle");
        commonPage.scrollAndClickElement("CareersPage","seeAllTeamsButton");
        commonPage.getCurrentUrl("qualityAssurancePage","qualityAssurancePagePath");}

    @AfterMethod
    public void tearDown(ITestContext context) {
        if (driver != null) {
            String testName = context.getName();
            takeScreenshot(driver,testName); // call screenshot method
            driver.quit(); //safetly quit browser
        }
    }
}
