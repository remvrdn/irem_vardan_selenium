package tests;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.CareersPage;
import pages.CommonPage;
import pages.QAPage;
import utils.ConfigReader;
import utils.DriverManager;

import static utils.DriverManager.takeScreenshot;

public class QAJobsTest {
    WebDriver driver;
    String baseUrl= ConfigReader.getProperty("baseUrl");
    QAPage qaPage;
    CommonPage commonPage;

    @BeforeMethod
    public void setup() {
        driver = DriverManager.getDriver(ConfigReader.getProperty("browser"));
        commonPage = new CommonPage(driver);
        qaPage = new QAPage(driver);
        driver.get(baseUrl);
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
        commonPage.scrollToElement("CareersPage","QATeamTitle");
        commonPage.getCurrentUrl("qualityAssurancePage","qualityAssurancePagePath");
        commonPage.scrollToElement("qualityAssurancePage","seeAllQAJobsButton");
        commonPage.findElementByXpath("qualityAssurancePage","openAllPositionsTitle");
        commonPage.doubleClickElement("qualityAssurancePage","filterbyLocationDropbox");
        commonPage.clickElementWithWait("qualityAssurancePage","filterIstanbulLocation");
        qaPage.ifIsNotSelectedQADepartment();
        commonPage.clickElementWithWait("qualityAssurancePage","filterbyLocationDropbox");
        commonPage.clickElementWithWait("qualityAssurancePage","filterIstanbulLocation");
        qaPage.ifIsNotSelectedQADepartment();
        commonPage.waitforTenSeconds();
        commonPage.scrollToElement("qualityAssurancePage","softwareQATesterPositionTitle");


    }



    @Test(testName = "Check QA Jobs")
    public void checkQAJobs(){
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
        commonPage.scrollToElement("CareersPage","QATeamTitle");
        commonPage.getCurrentUrl("qualityAssurancePage","qualityAssurancePagePath");
        commonPage.scrollToElement("qualityAssurancePage","seeAllQAJobsButton");
        commonPage.findElementByXpath("qualityAssurancePage","openAllPositionsTitle");
        commonPage.doubleClickElement("qualityAssurancePage","filterbyLocationDropbox");
        commonPage.clickElementWithWait("qualityAssurancePage","filterIstanbulLocation");
        qaPage.ifIsNotSelectedQADepartment();
        commonPage.clickElementWithWait("qualityAssurancePage","filterbyLocationDropbox");
        commonPage.clickElementWithWait("qualityAssurancePage","filterIstanbulLocation");
        qaPage.ifIsNotSelectedQADepartment();
        commonPage.waitforTenSeconds();
        commonPage.scrollToElement("qualityAssurancePage","softwareQATesterPositionTitle");
        qaPage.isTextIstanbulTurkey("qualityAssurancePage","positonLocationinJobCard");
        qaPage.isTextQAorQualityAssurance("qualityAssurancePage","departTitleinJobCard");
    }

    @Test(testName = "go to role view")
    public void checkRoleView(){
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
        commonPage.scrollToElement("CareersPage","QATeamTitle");
        commonPage.getCurrentUrl("qualityAssurancePage","qualityAssurancePagePath");
        commonPage.scrollToElement("qualityAssurancePage","seeAllQAJobsButton");
        commonPage.findElementByXpath("qualityAssurancePage","openAllPositionsTitle");
        commonPage.doubleClickElement("qualityAssurancePage","filterbyLocationDropbox");
        commonPage.clickElementWithWait("qualityAssurancePage","filterIstanbulLocation");
        qaPage.ifIsNotSelectedQADepartment();
        commonPage.clickElementWithWait("qualityAssurancePage","filterbyLocationDropbox");
        commonPage.clickElementWithWait("qualityAssurancePage","filterIstanbulLocation");
        qaPage.ifIsNotSelectedQADepartment();
        commonPage.waitforTenSeconds();
        commonPage.scrollToElement("qualityAssurancePage","softwareQATesterPositionTitle");
        qaPage.isTextIstanbulTurkey("qualityAssurancePage","positonLocationinJobCard");
        qaPage.isTextQAorQualityAssurance("qualityAssurancePage","departTitleinJobCard");
        commonPage.hoverAndClickButton("qualityAssurancePage","firstQAJobCard","viewRoleButton");
        commonPage.switchToNewTab("jobApplyUrl");
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
