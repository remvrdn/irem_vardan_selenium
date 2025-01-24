package tests;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.HomePage;
import utils.ConfigReader;
import utils.DriverManager;

public class HomePageTest {
    WebDriver driver;
    String baseUrl=ConfigReader.getProperty("baseUrl");
    @BeforeMethod
    public void setup() {
        driver = DriverManager.getDriver(ConfigReader.getProperty("browser"));
        driver.get(baseUrl);
    }

    @Test
    public void verifyHeaderText() {
        HomePage homePage = new HomePage(driver);
        String actualHeader = homePage.getHeaderText();
        String expectedHeader = "#1 AI-native\n" +"Omnichannel Experience\n" +"and Customer Engagement Platform";
        Assert.assertEquals(actualHeader, expectedHeader, "Header text doesn't match!");
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }
}
