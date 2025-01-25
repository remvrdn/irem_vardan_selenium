package utils;

import java.awt.*;
import java.io.File;
import java.io.IOException;

public class TestRunner {


    public static void openReport() {
        try {
            File reportFile = new File("target/allure-report/index.html");  // Raporun bulunduğu dizin
            if (Desktop.isDesktopSupported()) {
                Desktop.getDesktop().browse(reportFile.toURI());  // Tarayıcıyı aç
            }
        } catch (IOException e) {
            System.out.println("Error opening the Allure report: " + e.getMessage());
        }
    }

    public static void runTestsWithTestNGXml() {
        // Maven komutunu tanımlıyoruz
        String mvnCommand = "mvn clean test allure:report";  // allure:report komutunu kullanıyoruz

        // ProcessBuilder ile komutu çalıştırıyoruz
        ProcessBuilder processBuilder = new ProcessBuilder(mvnCommand.split(" "));
        processBuilder.inheritIO();  // Terminal çıktılarını görünür yap

        try {
            // Komutu çalıştır
            Process process = processBuilder.start();
            int exitCode = process.waitFor();  // Sürecin bitmesini bekle

            // Eğer işlem başarılıysa
            if (exitCode == 0) {
                System.out.println("Tests ran successfully and Allure reports are generated.");
                openReport();  // Raporu tarayıcıda aç
            } else {
                System.out.println("Error running tests or generating reports.");
            }
        } catch (IOException | InterruptedException e) {
            System.out.println("Error while executing the Maven command: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        // Testleri çalıştır ve raporu oluştur
        runTestsWithTestNGXml();
    }
}

