package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import utils.ConfigReader;
import utils.JsonReader;

public class HomePage extends CommonPage{

    public HomePage(WebDriver driver) {
        super(driver);
    }

    public String getHeaderText(String pageName,String elementName) {
        String headerXpath = JsonReader.getLocator(pageName, elementName); // get xpath in page json file
        WebElement header = driver.findElement(By.xpath(headerXpath));
        return header.getText();
    }

    public void navigateToHomePage() {
        driver.get(ConfigReader.getProperty("baseUrl"));
    }
    }


