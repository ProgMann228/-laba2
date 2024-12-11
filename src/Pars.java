import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Pars {
    /**
     * Загружает данные из CSV и XML-файла.
     *
     * @param filePath путь к CSV-файлу
     * @return список записей из файла
     */
    public static List<Map<String, String>> loadCsv(String filePath) {
        List<Map<String, String>> data = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            br.readLine(); // Пропускаем заголовок
            String line;

            while ((line = br.readLine()) != null) {
                String[] parts = line.split(";");
                if (parts.length == 4) {
                    Map<String, String> record = new HashMap<>();
                    record.put("city", parts[0].replaceAll("\"", "").trim());
                    record.put("street", parts[1].replaceAll("\"", "").trim());
                    record.put("house", parts[2].trim());
                    record.put("floor", parts[3].trim());
                    data.add(record);
                }
            }
            System.out.println("CSV файл успешно загружен.");
        } catch (IOException e) {
            System.out.println("Ошибка при чтении CSV файла: " + e.getMessage());
        }

        return data;
    }
    /**
     * Загружает данные из XML-файла.
     *
     * @param filePath путь к XML-файлу
     * @return список записей из файла
     */
    public static List<Map<String, String>> loadXml(String filePath) {
        List<Map<String, String>> data = new ArrayList<>();

        try {
            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser parser = factory.newSAXParser();

            parser.parse(new File(filePath), new DefaultHandler() {
                Map<String, String> record;

                @Override
                public void startElement(String uri, String localName, String qName, Attributes attributes) {
                    if ("item".equals(qName)) {
                        record = new HashMap<>();
                        record.put("city", attributes.getValue("city"));
                        record.put("street", attributes.getValue("street"));
                        record.put("house", attributes.getValue("house"));
                        record.put("floor", attributes.getValue("floor"));
                        data.add(record);
                    }
                }
            });

            System.out.println("XML файл успешно загружен.");
        } catch (Exception e) {
            System.out.println("Ошибка при чтении XML файла: " + e.getMessage());
        }

        return data;
    }
}