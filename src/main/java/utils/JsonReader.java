package utils;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.FileReader;
import java.io.IOException;

public class JsonReader {
    public static String getLocator(String pageName, String elementName) {
        String filePath = "src/main/resources/pages/" + pageName + ".json";
        try (FileReader reader = new FileReader(filePath)) {
            JsonObject pageObject = JsonParser.parseReader(reader).getAsJsonObject();
            return pageObject.get(elementName).getAsString();
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to read JSON file for page: " + pageName);
        }
    }
}
