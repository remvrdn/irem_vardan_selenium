package utils;

import java.awt.*;
import java.io.File;
import java.io.IOException;

public class TestRunner {


    public static void openReport() {
        try {
            File reportFile = new File("target/allure-report/index.html");
            if (Desktop.isDesktopSupported()) {
                Desktop.getDesktop().browse(reportFile.toURI());
            }
        } catch (IOException e) {
            System.out.println("Error opening the Allure report: " + e.getMessage());
        }
    }

    public static void runTestsWithTestNGXml() {

        String mvnCommand = "mvn clean test allure:report";


        ProcessBuilder processBuilder = new ProcessBuilder(mvnCommand.split(" "));
        processBuilder.inheritIO();

        try {
            // exe cmd
            Process process = processBuilder.start();
            int exitCode = process.waitFor();

            // if success
            if (exitCode == 0) {
                System.out.println("Tests ran successfully and Allure reports are generated.");
                openReport();
            } else {
                System.out.println("Error running tests or generating reports.");
            }
        } catch (IOException | InterruptedException e) {
            System.out.println("Error while executing the Maven command: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        // run test cases
        runTestsWithTestNGXml();
    }
}

