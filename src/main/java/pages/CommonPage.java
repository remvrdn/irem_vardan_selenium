package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import utils.ConfigReader;
import utils.JsonReader;

import java.time.Duration;
import java.util.Set;


public class CommonPage {
    protected WebDriver driver;
    protected WebDriverWait wait;
    private static final Logger logger = LoggerFactory.getLogger(CommonPage.class);

    public CommonPage(WebDriver driver) {
        this.driver = driver;

        // get timeout value in config
        Duration timeout = Duration.ofSeconds(Long.parseLong(ConfigReader.getProperty("explicitWaitTimeout")));
        this.wait = new WebDriverWait(driver, timeout);
        logger.info("BasePage initialized with timeout: " + timeout + " seconds");
    }

    // waiting element for visible
    public void waitForElementToBeVisible(WebElement element){
        try {
            wait.until(ExpectedConditions.visibilityOf(element));
            logger.info("Found element " + element);
        } catch (Exception e) {
            logger.error("Not found element " + element, e);
            throw new RuntimeException("Not visible element " + element, e);
        }
    }
    // page redirection and page url control
    public void goToUrl(String url) {
        driver.get(url);
    }

    public void scrollToElement(String pageName, String elementName) {
        String elementPath = JsonReader.getLocator(pageName, elementName);
        int scrollAttempts = 0;
        int maxScrollAttempts = 40;

        while (scrollAttempts < maxScrollAttempts) {
            try {
                // find the element and check if it is clickable
                WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
                WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(elementPath)));

                // scrolling process
                JavascriptExecutor js = (JavascriptExecutor) driver;
                js.executeScript("arguments[0].scrollIntoView({block: 'center', inline: 'nearest'});", element);
                System.out.println("Element found and scrolled: " + elementName);

                // clickability control
                wait.until(ExpectedConditions.elementToBeClickable(element));
                element.click(); // Elemente tıklanıyor
                System.out.println("The element is clickable and was clicked: " + elementName);
                return;

            } catch (org.openqa.selenium.StaleElementReferenceException staleException) {
                System.out.println("Element recreated in DOM, searching again...");
            } catch (Exception e) {
                // scroll and increase the number of attempts
                JavascriptExecutor js = (JavascriptExecutor) driver;
                js.executeScript("window.scrollBy(0, 400);");
                scrollAttempts++;
                System.out.println("Scrolling... Test: " + scrollAttempts);
            }
        }

        System.err.println("Maximum scroll attempts reached. Could not make element clickable: " + elementName);
    }


    public void scrollAndClickElement(String pageName, String elementName) {
        String elementPath = JsonReader.getLocator(pageName, elementName);
        int maxScrollAttempts = 10; // maximum scroll attempts
        int scrollCount = 0; // scroll counter
        boolean isElementClickable = false;

        while (!isElementClickable && scrollCount < maxScrollAttempts) {
            try {
                // find the element and redefine it every loop
                WebElement element = driver.findElement(By.xpath(elementPath));

                // scroll to make element visible
                Actions actions = new Actions(driver);
                actions.moveToElement(element).perform();
                System.out.println("Element found and scrolled: " + elementName);

                // verify that it is clickable and click
                if (element.isDisplayed() && element.isEnabled()) {
                    isElementClickable = true; // clickable, end loop
                    element.click();
                    System.out.println("The element is clickable and was clicked: " + elementName);
                } else {
                    actions.moveToElement(element).perform();
                    element.click();
                    System.out.println("The element is visible but not clickable: " + elementName);
                }
            } catch (Exception e) {
                // if the element is not found or clickable, scroll the page manually
                try {

                    JavascriptExecutor js = (JavascriptExecutor) driver;
                    js.executeScript("window.scrollBy(0, 250);");
                    scrollCount++;
                    System.out.println("Scrolling... Test: " + scrollCount);
                } catch (Exception jsException) {
                    System.err.println("Scrolling operation failed: " + jsException.getMessage());
                    break; // Eğer scroll yapılamıyorsa döngüyü durdur
                }
            }
        }

        // Maksimum scroll denemesinden sonra element bulunamazsa hata mesajı
        if (!isElementClickable) {
            System.err.println("Could not make element clickable: " + elementName);
        }
    }

    public String getCurrentUrl(String pageName, String path) {
            String urlPath = JsonReader.getLocator(pageName, path);
        String currentUrl = driver.getCurrentUrl();
        try {
            currentUrl.contains(urlPath);
            logger.info("Current Url: " + currentUrl);
            return currentUrl;
        } catch (Exception e) {
            throw new RuntimeException("Not found path in current url: " + urlPath, e);
        }
    }

    // check redirect url
    public void verifyPageUrl(String expectedUrl) {
        wait.until(ExpectedConditions.urlToBe(expectedUrl));
    }

    // find element by xpath
    public WebElement findElementByXpath(String pageName, String elementName) {
        String elementPath = JsonReader.getLocator(pageName, elementName);
        return wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(elementPath)));
    }

    // check title
    public String getPageTitle() {
        String title = driver.getTitle();
        logger.info("Found page title: " + title);
        return title;
    }

    // get element text
    public String getElementText(String pageName, String elementName) {
        String elementPath = JsonReader.getLocator(pageName, elementName);
        WebElement element = driver.findElement(By.xpath(elementPath));
        try {
            waitForElementToBeVisible(element);
            String text = element.getText();
            logger.info("Element text " + text);
            return text;
        } catch (Exception e) {
            logger.error("Not found element text: " + element, e);
            throw new RuntimeException("Not found element text: " + element, e);
        }
    }

    public void clickElement(String pageName, String elementName) {
        String elementPath = JsonReader.getLocator(pageName, elementName);
        WebElement element = driver.findElement(By.xpath(elementPath));
        try{
            waitForElementToBeVisible(element);
            element.click();
        }
        catch (Exception e) {
        logger.error("Not found element: " + element, e);
        throw new RuntimeException("Not found element: " + element, e);
        }
    }
    public boolean isElementTextPresent(String pageName, String elementName) {
        String elementPath = JsonReader.getLocator(pageName, elementName);
        WebElement element = driver.findElement(By.xpath(elementPath));
        String elementText = element.getText();

        // check text in element
        return elementText != null && !elementText.trim().isEmpty();
    }
    public void scrollToElement2(String pageName, String elementName) {
        String elementPath = JsonReader.getLocator(pageName, elementName);
        boolean isElementClickable = false;
        int scrollAttempts = 0;
        int maxScrollAttempts = 10;

        while (!isElementClickable && scrollAttempts < maxScrollAttempts) {
            try {
                // try to find the element
                WebElement element = driver.findElement(By.xpath(elementPath));

                // make the element visible and scroll
                JavascriptExecutor js = (JavascriptExecutor) driver;
                js.executeScript("arguments[0].scrollIntoView(true);", element);
                System.out.println("Element found and scrolled: " + elementName);

                // verify that it is clickable
                WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
                wait.until(ExpectedConditions.elementToBeClickable(element));

                if (element.isDisplayed() && element.isEnabled()) {
                    isElementClickable = true; // Tıklanabilir, döngüyü bitir
                    element.click(); // Tıkla
                    System.out.println("The element is clickable and was clicked: " + elementName);
                } else {
                    System.out.println("The element is visible but not yet clickable: " + elementName);
                }
            } catch (Exception e) {
                // if the element is not found or is clicked, manually scroll the page a small amount
                try {
                    JavascriptExecutor js = (JavascriptExecutor) driver;
                    js.executeScript("window.scrollBy(0, 250);");
                    scrollAttempts++;
                    System.out.println("Element not found or clickable, scrolling down... Try: " + scrollAttempts);
                } catch (Exception jsException) {
                    System.err.println("Scrolling operation failed: " + jsException.getMessage());
                    break; // Eğer scroll yapılamıyorsa döngüyü durdur
                }
            }
        }

        if (!isElementClickable) {
            System.err.println("Maximum scroll attempts reached. Could not make element clickable: " + elementName);
        }
    }

    public void clickElementWithWait(String pageName, String elementName) {
        String elementPath = JsonReader.getLocator(pageName, elementName);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        try {
            WebElement element = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(elementPath)));
            element.click();
            System.out.println("The element is clickable and was clicked: " + elementName);
        } catch (Exception e) {
            System.err.println("Element could not be clicked: " + elementName + ". Failed: " + e.getMessage());
        }
    }
    public void waitforTenSeconds(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }
    public void doubleClickElement(String pageName, String elementName) {
        String elementPath = JsonReader.getLocator(pageName, elementName);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        try {
            WebElement element = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(elementPath)));
            Actions actions = new Actions(driver);
            actions.doubleClick(element).perform();
            System.out.println("Double click on the element: " + elementName);
        } catch (Exception e) {
            System.err.println("Could not double-click element: " + elementName + ". Failed: " + e.getMessage());
        }
    }
    public void hoverAndClickButton(String pageName,String firstElementName, String SecondElementName) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        String cardXPath = JsonReader.getLocator(pageName, firstElementName);
        String buttonXPath = JsonReader.getLocator(pageName, SecondElementName);
        try {
            // hover the card
            WebElement cardElement = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(cardXPath)));
            Actions actions = new Actions(driver);
            actions.moveToElement(cardElement).perform();
            System.out.println("Hovered the card: " + cardXPath);

            // wait until button visible and clickable and click
            WebElement buttonElement = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(buttonXPath)));
            buttonElement.click();
            System.out.println("Click button: " + buttonXPath);
        } catch (Exception e) {
            System.err.println("Failed : " + e.getMessage());
        }
    }
    public void switchToNewTab(String expectedURL) {
        try {
            // keep valid win
            String currentWindow = driver.getWindowHandle();
            Set<String> allWindows = driver.getWindowHandles();

            // access new tab
            for (String window : allWindows) {
                if (!window.equals(currentWindow)) {
                    driver.switchTo().window(window);
                    System.out.println("open new tab");
                    String currentURL = driver.getCurrentUrl();
                    if (currentURL.equals(expectedURL)) {
                        System.out.println("Correct url: " + currentURL);
                    } else {
                        System.err.println("excepted url: " + expectedURL + ", current url: " + currentURL);
                    }
                    return;

                }
            }
        }catch (Exception e){
            System.err.println("An error occurred while switching tabs or checking URLs: " + e.getMessage());

        }
        }
<<<<<<< HEAD
    }
=======

    public void scrollAndClickElementwithJS(String pageName, String elementName) {
        String elementPath = JsonReader.getLocator(pageName, elementName);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        try {
            WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(elementPath)));

            // scroll into view
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({behavior: 'smooth', block: 'center'});", element);

            // JS click to avoid overlay issues
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);

            System.out.println("Element clicked via JS: " + elementName);
        } catch (Exception e) {
            System.err.println("Failed to scroll and click element: " + elementName + " - " + e.getMessage());
        }
    }

}
>>>>>>> 2ef7341 (update project for website)
