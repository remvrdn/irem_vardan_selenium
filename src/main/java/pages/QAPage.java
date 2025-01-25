package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import utils.JsonReader;

import java.util.List;
import java.util.Set;

public class QAPage extends CommonPage{

    public QAPage(WebDriver driver) {
        super(driver);
    }

    public void ifIsNotSelectedQADepartment(){
        String elementPath = JsonReader.getLocator("qualityAssurancePage", "filterQADepartment");
        try {
            WebElement element = driver.findElement(By.xpath(elementPath));
            if (element.isDisplayed()) {
                System.out.println("Element görünür: " + elementPath);
            } else {
                System.out.println("Not Found Element " + elementPath);
                String dropboxElementPath= JsonReader.getLocator("qualityAssurancePage","filterbyDepartmentDropbox");
                WebElement dropboxElement =driver.findElement(By.xpath(dropboxElementPath));
                String dropboxOptionElementPath= JsonReader.getLocator("qualityAssurancePage","filterbyDepartmentDropbox");
                WebElement dropboxOptionElement =driver.findElement(By.xpath(dropboxElementPath));
                dropboxElement.click();
                dropboxOptionElement.click();
            }
        } catch (Exception e) {
            System.out.println("Not Found Filtered Department " + elementPath);
        }

    }
    public boolean isTextQAorQualityAssurance(String pageName, String elementName) {
        String elementPath = JsonReader.getLocator(pageName, elementName);
        try {
            List<WebElement> elements = driver.findElements(By.xpath(elementPath));
            for (WebElement element : elements) {
                String text = element.getText();
                if (text.equalsIgnoreCase("QA Tester") || text.equalsIgnoreCase("Quality Assurance")) {
                    System.out.println("Found QA Tester or Quality Assurance Title: " + text);
                    return true;
                }
            }
            System.out.println("No element text QA Tester veya Quality Assurance");
            return false;
        } catch (Exception e) {
            System.err.println("Failed while check elements: " + e.getMessage());
            return false;
        }
    }

    public boolean isTextIstanbulTurkey(String pageName, String elementName) {
        String elementPath = JsonReader.getLocator(pageName, elementName);
        try {
            List<WebElement> elements = driver.findElements(By.xpath(elementPath));
            for (WebElement element : elements) {
                String text = element.getText();
                if (text.equalsIgnoreCase("Istanbul, Turkey")) {
                    System.out.println("Elementin metni Istanbul, Turkey: " + text);
                    return true;
                }
            }
            System.out.println("Hiçbir elementin metni Istanbul, Turkey değil.");
            return false;
        } catch (Exception e) {
            System.err.println("Metin kontrolü sırasında hata oluştu: " + e.getMessage());
            return false;
        }
    }

}
