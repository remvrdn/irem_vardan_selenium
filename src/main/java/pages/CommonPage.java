package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import utils.ConfigReader;

import java.time.Duration;


public class CommonPage {
    protected WebDriver driver;
    protected WebDriverWait wait;
    private static final Logger logger = LoggerFactory.getLogger(CommonPage.class);

    public CommonPage(WebDriver driver) {
        this.driver = driver;

        // Timeout değerini config'den al
        Duration timeout = Duration.ofSeconds(Long.parseLong(ConfigReader.getProperty("explicitWaitTimeout")));
        this.wait = new WebDriverWait(driver, timeout);
        logger.info("BasePage initialized with timeout: " + timeout + " seconds");
    }

    // Dinamik bir element beklemek için
    public void waitForElementToBeVisible(WebElement element) {
        try {
            wait.until(ExpectedConditions.visibilityOf(element));
            logger.info("Element görünür oldu: " + element);
        } catch (Exception e) {
            logger.error("Element görünür olmadı: " + element, e);
            throw new RuntimeException("Element görünür olmadı: " + element, e);
        }
    }

    // Sayfa başlığını kontrol etmek için
    public String getPageTitle() {
        String title = driver.getTitle();
        logger.info("Sayfa başlığı alındı: " + title);
        return title;
    }

    // Ortak bir elementin metnini almak için
    public String getElementText(WebElement element) {
        try {
            waitForElementToBeVisible(element);
            String text = element.getText();
            logger.info("Element metni alındı: " + text);
            return text;
        } catch (Exception e) {
            logger.error("Element metni alınamadı: " + element, e);
            throw new RuntimeException("Element metni alınamadı: " + element, e);
        }
    }
}
