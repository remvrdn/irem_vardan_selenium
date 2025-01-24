package utils;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class DriverManager {
    private static WebDriver driver;

    public static WebDriver getDriver(String browserType) {
        if (driver == null) {
            switch (browserType.toLowerCase()) {
                case "chrome":
                    WebDriverManager.chromedriver().setup();
                    driver = new ChromeDriver();
                    break;

                case "firefox":
                    WebDriverManager.firefoxdriver().setup();
                    driver = new FirefoxDriver();
                    break;

                case "edge":
                    WebDriverManager.edgedriver().setup();
                    driver = new EdgeDriver();
                    break;

                case "yandex":
                    WebDriverManager.chromedriver().setup(); // Yandex browser uses chromedriver
                    driver = new ChromeDriver();
                    break;

                default:
                    throw new IllegalArgumentException("Invalid browser: " + browserType);
            }
            driver.manage().window().maximize();
        }
        return driver;
    }

    public static void quitDriver() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }
    public static void takeScreenshot(WebDriver driver, String testName) {
        File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        Path screenshotsDir = Paths.get("screenshots");

        // if not found screenshotsDir path file create new file
        if (!Files.exists(screenshotsDir)) {
            try {
                Files.createDirectories(screenshotsDir); // Klasörü oluştur
            } catch (IOException e) {
                e.printStackTrace();
                System.out.println("Error while creating screenshots file");
                return;
            }
        }
        try {
            Files.copy(srcFile.toPath(), Paths.get("screenshots/" + testName + ".png"));
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("An error while taking a screenshot");

        }
    }
}
