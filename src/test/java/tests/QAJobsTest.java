package tests;

import io.qameta.allure.Feature;
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

@Feature("QA Jobs")
public class QAJobsTest {
    WebDriver driver;
    String baseUrl= ConfigReader.getProperty("baseUrl");
    QAPage qaPage;
    CommonPage commonPage;

    @BeforeMethod
    public void setup() {
<<<<<<< HEAD
        driver = DriverManager.getDriver(ConfigReader.getProperty("browser"));
        commonPage = new CommonPage(driver);
        qaPage = new QAPage(driver);
        driver.get(baseUrl);
=======
        driver = DriverManager.createNewDriver();
        commonPage = new CommonPage(driver);
        qaPage = new QAPage(driver);
        driver.get(baseUrl);
        commonPage.clickElement("HomePage", "cookieAcceptAll");
>>>>>>> 2ef7341 (update project for website)
    }
    @Test(testName = "Go to Quality Assurance Page")
    public void testgoQualityAssurancePage() {
        commonPage.clickElement("HomePage","companySectioninDropdownList");
        commonPage.clickElement("HomePage","careersinCompanySection");

        commonPage.getCurrentUrl("CareersPage", "careersPagePath");
<<<<<<< HEAD
        commonPage.clickElement("CareersPage","declineAllButton");
=======
>>>>>>> 2ef7341 (update project for website)
        commonPage.findElementByXpath("CareersPage", "careersPageTitle");
        commonPage.findElementByXpath("CareersPage","ourLocationTitle");
        commonPage.isElementTextPresent("CareersPage","ourLocationTextArea");
        commonPage.findElementByXpath("CareersPage","ourLocationList");
        commonPage.findElementByXpath("CareersPage","lifeAtTitle");
        commonPage.isElementTextPresent("CareersPage","lifeAtTextArea");
        commonPage.findElementByXpath("CareersPage","findYourCallingTitle");
<<<<<<< HEAD
        commonPage.scrollAndClickElement("CareersPage","seeAllTeamsButton");
        commonPage.scrollToElement("CareersPage","QATeamTitle");
        commonPage.getCurrentUrl("qualityAssurancePage","qualityAssurancePagePath");
        commonPage.scrollToElement("qualityAssurancePage","seeAllQAJobsButton");
=======
        commonPage.scrollAndClickElementwithJS("CareersPage","seeAllTeamsButton");
        commonPage.scrollAndClickElementwithJS("CareersPage","QATeamTitle");
        commonPage.getCurrentUrl("qualityAssurancePage","qualityAssurancePagePath");
        commonPage.scrollAndClickElementwithJS("qualityAssurancePage","seeAllQAJobsButton");
>>>>>>> 2ef7341 (update project for website)
        commonPage.findElementByXpath("qualityAssurancePage","openAllPositionsTitle");
        commonPage.doubleClickElement("qualityAssurancePage","filterbyLocationDropbox");
        commonPage.clickElementWithWait("qualityAssurancePage","filterIstanbulLocation");
        qaPage.ifIsNotSelectedQADepartment();
        commonPage.clickElementWithWait("qualityAssurancePage","filterbyLocationDropbox");
        commonPage.clickElementWithWait("qualityAssurancePage","filterIstanbulLocation");
        qaPage.ifIsNotSelectedQADepartment();
        commonPage.waitforTenSeconds();
<<<<<<< HEAD
        commonPage.scrollToElement("qualityAssurancePage","softwareQATesterPositionTitle");
=======
        commonPage.scrollAndClickElementwithJS("qualityAssurancePage","softwareQATesterPositionTitle");
>>>>>>> 2ef7341 (update project for website)


    }

    @Test(testName = "Check QA Jobs")
    public void checkQAJobs(){
        commonPage.clickElement("HomePage","companySectioninDropdownList");
        commonPage.clickElement("HomePage","careersinCompanySection");

        commonPage.getCurrentUrl("CareersPage", "careersPagePath");
<<<<<<< HEAD
        commonPage.clickElement("CareersPage","declineAllButton");
=======
>>>>>>> 2ef7341 (update project for website)
        commonPage.findElementByXpath("CareersPage", "careersPageTitle");
        commonPage.findElementByXpath("CareersPage","ourLocationTitle");
        commonPage.isElementTextPresent("CareersPage","ourLocationTextArea");
        commonPage.findElementByXpath("CareersPage","ourLocationList");
        commonPage.findElementByXpath("CareersPage","lifeAtTitle");
        commonPage.isElementTextPresent("CareersPage","lifeAtTextArea");
        commonPage.findElementByXpath("CareersPage","findYourCallingTitle");
<<<<<<< HEAD
        commonPage.scrollAndClickElement("CareersPage","seeAllTeamsButton");
        commonPage.scrollToElement("CareersPage","QATeamTitle");
        commonPage.getCurrentUrl("qualityAssurancePage","qualityAssurancePagePath");
        commonPage.scrollToElement("qualityAssurancePage","seeAllQAJobsButton");
=======
        commonPage.scrollAndClickElementwithJS("CareersPage","seeAllTeamsButton");
        commonPage.scrollAndClickElementwithJS("CareersPage","QATeamTitle");
        commonPage.getCurrentUrl("qualityAssurancePage","qualityAssurancePagePath");
        commonPage.scrollAndClickElementwithJS("qualityAssurancePage","seeAllQAJobsButton");
>>>>>>> 2ef7341 (update project for website)
        commonPage.findElementByXpath("qualityAssurancePage","openAllPositionsTitle");
        commonPage.doubleClickElement("qualityAssurancePage","filterbyLocationDropbox");
        commonPage.clickElementWithWait("qualityAssurancePage","filterIstanbulLocation");
        qaPage.ifIsNotSelectedQADepartment();
        commonPage.clickElementWithWait("qualityAssurancePage","filterbyLocationDropbox");
        commonPage.clickElementWithWait("qualityAssurancePage","filterIstanbulLocation");
        qaPage.ifIsNotSelectedQADepartment();
        commonPage.waitforTenSeconds();
<<<<<<< HEAD
        commonPage.scrollToElement("qualityAssurancePage","softwareQATesterPositionTitle");
=======
        commonPage.scrollAndClickElementwithJS("qualityAssurancePage","softwareQATesterPositionTitle");
>>>>>>> 2ef7341 (update project for website)
        qaPage.isTextIstanbulTurkey("qualityAssurancePage","positonLocationinJobCard");
        qaPage.isTextQAorQualityAssurance("qualityAssurancePage","departTitleinJobCard");
    }

    @Test(testName = "go to role view")
    public void checkRoleView(){
        commonPage.clickElement("HomePage","companySectioninDropdownList");
        commonPage.clickElement("HomePage","careersinCompanySection");

        commonPage.getCurrentUrl("CareersPage", "careersPagePath");
<<<<<<< HEAD
        commonPage.clickElement("CareersPage","declineAllButton");
=======
>>>>>>> 2ef7341 (update project for website)
        commonPage.findElementByXpath("CareersPage", "careersPageTitle");
        commonPage.findElementByXpath("CareersPage","ourLocationTitle");
        commonPage.isElementTextPresent("CareersPage","ourLocationTextArea");
        commonPage.findElementByXpath("CareersPage","ourLocationList");
        commonPage.findElementByXpath("CareersPage","lifeAtTitle");
        commonPage.isElementTextPresent("CareersPage","lifeAtTextArea");
        commonPage.findElementByXpath("CareersPage","findYourCallingTitle");
<<<<<<< HEAD
        commonPage.scrollAndClickElement("CareersPage","seeAllTeamsButton");
        commonPage.scrollToElement("CareersPage","QATeamTitle");
        commonPage.getCurrentUrl("qualityAssurancePage","qualityAssurancePagePath");
        commonPage.scrollToElement("qualityAssurancePage","seeAllQAJobsButton");
=======
        commonPage.scrollAndClickElementwithJS("CareersPage","seeAllTeamsButton");
        commonPage.scrollToElement("CareersPage","QATeamTitle");
        commonPage.getCurrentUrl("qualityAssurancePage","qualityAssurancePagePath");
        commonPage.scrollAndClickElementwithJS("qualityAssurancePage","seeAllQAJobsButton");
>>>>>>> 2ef7341 (update project for website)
        commonPage.findElementByXpath("qualityAssurancePage","openAllPositionsTitle");
        commonPage.doubleClickElement("qualityAssurancePage","filterbyLocationDropbox");
        commonPage.clickElementWithWait("qualityAssurancePage","filterIstanbulLocation");
        qaPage.ifIsNotSelectedQADepartment();
        commonPage.clickElementWithWait("qualityAssurancePage","filterbyLocationDropbox");
        commonPage.clickElementWithWait("qualityAssurancePage","filterIstanbulLocation");
        qaPage.ifIsNotSelectedQADepartment();
        commonPage.waitforTenSeconds();
<<<<<<< HEAD
        commonPage.scrollToElement("qualityAssurancePage","softwareQATesterPositionTitle");
        qaPage.isTextIstanbulTurkey("qualityAssurancePage","positonLocationinJobCard");
        qaPage.isTextQAorQualityAssurance("qualityAssurancePage","departTitleinJobCard");
=======
        commonPage.scrollAndClickElementwithJS("qualityAssurancePage","softwareQATesterPositionTitle");
        qaPage.isTextIstanbulTurkey("qualityAssurancePage","positonLocationinJobCard");
        qaPage.isTextQAorQualityAssurance("qualityAssurancePage","departTitleinJobCard");
        commonPage.scrollAndClickElementwithJS("qualityAssurancePage","viewRoleButton");
>>>>>>> 2ef7341 (update project for website)
        commonPage.hoverAndClickButton("qualityAssurancePage","firstQAJobCard","viewRoleButton");
        commonPage.switchToNewTab("jobApplyUrl");
        commonPage.findElementByXpath("jobsLever","applyThisJobButton");
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
