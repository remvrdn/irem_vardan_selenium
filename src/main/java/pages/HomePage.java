package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utils.ConfigReader;
import utils.JsonReader;

public class HomePage extends CommonPage{

    public HomePage(WebDriver driver) {
        super(driver);
    }

    public String getHeaderText() {
        String headerXpath = JsonReader.getLocator("HomePage", "homePageHeader"); // get xpath in page json file
        WebElement header = driver.findElement(By.xpath(headerXpath));
        return header.getText();
    }

    public void clickStartButton() {
        String startButtonXpath = JsonReader.getLocator("HomePage", "startButton");
        WebElement startButton = driver.findElement(By.xpath(startButtonXpath));
        startButton.click();
    }

    public void navigateToHomePage() {
        driver.get(ConfigReader.getProperty("baseUrl"));
    }
    }


