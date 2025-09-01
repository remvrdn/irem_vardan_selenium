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
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DriverManager {
<<<<<<< HEAD
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
=======
    private static ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    public static WebDriver getDriver() {
        if(driver.get() == null){
            String browser = ConfigReader.getProperty("browser").toLowerCase();
            switch(browser){
                case "chrome" -> driver.set(new ChromeDriver());
                case "firefox" -> driver.set(new FirefoxDriver());
                default -> throw new IllegalArgumentException("Invalid browser: "+browser);
            }
            driver.get().manage().window().maximize();
        }
        return driver.get();
    }

    // creates a new WebDriver instance each time it is called
    public static WebDriver createNewDriver() {
        WebDriver newDriver = initDriver();
        driver.set(newDriver);
        return newDriver;
    }
    private static WebDriver initDriver() {
        String browser = ConfigReader.getProperty("browser").toLowerCase();
        WebDriver webDriver;
        switch (browser) {
            case "chrome" -> webDriver = new ChromeDriver();
            case "firefox" -> webDriver = new FirefoxDriver();
            default -> throw new IllegalArgumentException("Invalid browser: " + browser);
        }
        webDriver.manage().window().maximize();
        return webDriver;
    }

    public static void quitDriver() {
        if(driver.get() != null){
            driver.get().quit();
            driver.remove();
>>>>>>> 2ef7341 (update project for website)
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
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss");
        String timestamp = LocalDateTime.now().format(formatter);
        String fileName = testName + "_" + timestamp + ".png";
        try {
            Files.copy(srcFile.toPath(), screenshotsDir.resolve(fileName));
            System.out.println("Screenshot saved as: " + fileName);
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("An error while taking a screenshot");

        }
    }
}
