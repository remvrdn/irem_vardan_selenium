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
    // Sayfa yönlendirme ve sayfa url kontrolü
    public void goToUrl(String url) {
        driver.get(url);
    }

    public void scrollToElement(String pageName, String elementName) {
        String elementPath = JsonReader.getLocator(pageName, elementName);
        int scrollAttempts = 0;
        int maxScrollAttempts = 40;

        while (scrollAttempts < maxScrollAttempts) {
            try {
                // Elementi bul ve tıklanabilir olup olmadığını kontrol et
                WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
                WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(elementPath)));

                // Scroll işlemi
                JavascriptExecutor js = (JavascriptExecutor) driver;
                js.executeScript("arguments[0].scrollIntoView({block: 'center', inline: 'nearest'});", element);
                System.out.println("Element bulundu ve scroll yapıldı: " + elementName);

                // Tıklanabilirlik kontrolü
                wait.until(ExpectedConditions.elementToBeClickable(element));
                element.click(); // Elemente tıklanıyor
                System.out.println("Element tıklanabilir durumda ve tıklandı: " + elementName);
                return; // Metodu tamamla

            } catch (org.openqa.selenium.StaleElementReferenceException staleException) {
                System.out.println("Element DOM'da yeniden oluşturuldu, tekrar aranıyor...");
            } catch (Exception e) {
                // Scroll yap ve deneme sayısını artır
                JavascriptExecutor js = (JavascriptExecutor) driver;
                js.executeScript("window.scrollBy(0, 400);");
                scrollAttempts++;
                System.out.println("Scroll yapılıyor... Deneme: " + scrollAttempts);
            }
        }

        System.err.println("Maksimum scroll denemesine ulaşıldı. Element tıklanabilir hale getirilemedi: " + elementName);
    }


    public void scrollAndClickElement(String pageName, String elementName) {
        String elementPath = JsonReader.getLocator(pageName, elementName);
        int maxScrollAttempts = 10; // Maksimum scroll denemesi
        int scrollCount = 0; // Scroll sayacı
        boolean isElementClickable = false;

        while (!isElementClickable && scrollCount < maxScrollAttempts) {
            try {
                // Elementi bul ve her döngüde yeniden tanımla
                WebElement element = driver.findElement(By.xpath(elementPath));

                // Elementi görünür hale getirmek için scroll yap
                Actions actions = new Actions(driver);
                actions.moveToElement(element).perform();
                System.out.println("Element bulundu ve scroll yapıldı: " + elementName);

                // Tıklanabilir olduğunu doğrula ve tıkla
                if (element.isDisplayed() && element.isEnabled()) {
                    isElementClickable = true; // Tıklanabilir, döngüyü bitir
                    element.click();
                    System.out.println("Element tıklanabilir durumda ve tıklandı: " + elementName);
                } else {
                    actions.moveToElement(element).perform();
                    element.click();
                    System.out.println("Element görünür ancak tıklanabilir değil: " + elementName);
                }
            } catch (Exception e) {
                // Eğer element bulunamazsa veya tıklanamazsa, sayfayı manuel olarak kaydır
                try {

                    JavascriptExecutor js = (JavascriptExecutor) driver;
                    js.executeScript("window.scrollBy(0, 250);");
                    scrollCount++;
                    System.out.println("Scroll yapılıyor... Deneme: " + scrollCount);
                } catch (Exception jsException) {
                    System.err.println("Scroll işlemi başarısız: " + jsException.getMessage());
                    break; // Eğer scroll yapılamıyorsa döngüyü durdur
                }
            }
        }

        // Maksimum scroll denemesinden sonra element bulunamazsa hata mesajı
        if (!isElementClickable) {
            System.err.println("Element tıklanabilir hale getirilemedi: " + elementName);
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
                // Elementi bulmaya çalış
                WebElement element = driver.findElement(By.xpath(elementPath));

                // Elementin görünürlüğünü sağla ve scroll yap
                JavascriptExecutor js = (JavascriptExecutor) driver;
                js.executeScript("arguments[0].scrollIntoView(true);", element);
                System.out.println("Element bulundu ve scroll yapıldı: " + elementName);

                // Tıklanabilir olduğunu doğrula
                WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
                wait.until(ExpectedConditions.elementToBeClickable(element));

                if (element.isDisplayed() && element.isEnabled()) {
                    isElementClickable = true; // Tıklanabilir, döngüyü bitir
                    element.click(); // Tıkla
                    System.out.println("Element tıklanabilir durumda ve tıklandı: " + elementName);
                } else {
                    System.out.println("Element görünür ancak henüz tıklanabilir değil: " + elementName);
                }
            } catch (Exception e) {
                // Eğer element bulunamazsa veya tıklanamazsa, sayfayı manuel olarak küçük bir miktar kaydır
                try {
                    JavascriptExecutor js = (JavascriptExecutor) driver;
                    js.executeScript("window.scrollBy(0, 250);");
                    scrollAttempts++;
                    System.out.println("Element bulunamadı veya tıklanamadı, aşağı doğru scroll yapılıyor... Deneme: " + scrollAttempts);
                } catch (Exception jsException) {
                    System.err.println("Scroll işlemi başarısız: " + jsException.getMessage());
                    break; // Eğer scroll yapılamıyorsa döngüyü durdur
                }
            }
        }

        if (!isElementClickable) {
            System.err.println("Maksimum scroll denemesine ulaşıldı. Element tıklanabilir hale getirilemedi: " + elementName);
        }
    }

    public void clickElementWithWait(String pageName, String elementName) {
        String elementPath = JsonReader.getLocator(pageName, elementName);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        try {
            WebElement element = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(elementPath)));
            element.click();
            System.out.println("Element tıklanabilir durumda ve tıklandı: " + elementName);
        } catch (Exception e) {
            System.err.println("Elemente tıklanamadı: " + elementName + ". Hata: " + e.getMessage());
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
            System.out.println("Elemente çift tıklandı: " + elementName);
        } catch (Exception e) {
            System.err.println("Elemente çift tıklanamadı: " + elementName + ". Hata: " + e.getMessage());
        }
    }
    public void hoverAndClickButton(String pageName,String firstElementName, String SecondElementName) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        String cardXPath = JsonReader.getLocator(pageName, firstElementName);
        String buttonXPath = JsonReader.getLocator(pageName, SecondElementName);
        try {
            // Card üzerine gel
            WebElement cardElement = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(cardXPath)));
            Actions actions = new Actions(driver);
            actions.moveToElement(cardElement).perform();
            System.out.println("Hovered the card: " + cardXPath);

            // Butonu görünür hale getir ve tıkla
            WebElement buttonElement = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(buttonXPath)));
            buttonElement.click();
            System.out.println("Click button: " + buttonXPath);
        } catch (Exception e) {
            System.err.println("Failed : " + e.getMessage());
        }
    }
    public void switchToNewTab(String expectedURL) {
        try {
            // Geçerli pencereyi sakla
            String currentWindow = driver.getWindowHandle();
            Set<String> allWindows = driver.getWindowHandles();

            // Yeni pencereye geç
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
            System.err.println("Sekme değiştirme veya URL kontrolü sırasında hata oluştu: " + e.getMessage());

        }
        }
    }