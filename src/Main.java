
import java.io.*;
import java.util.*;
import javax.xml.parsers.*;
import org.xml.sax.*;
import org.xml.sax.helpers.DefaultHandler;
/**
 * Главный класс программы. Содержит метод main, который обрабатывает ввод пользователя
 * и вызывает функции для обработки файлов и отображения статистики.
 */
public class Main {
    /**
     * Главный метод программы. Обрабатывает ввод пользователя,
     * вызывает функции для обработки файлов и отображения статистики.
     *
     * @param args аргументы командной строки (не используются)
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Введите путь до файла-справочника или 'exit' для завершения:");
            String filePath = scanner.nextLine().replaceAll("^\"|\"$", "");

            if (filePath.equalsIgnoreCase("exit")) {
                System.out.println("Завершение программы.");
                break;
            }

            long startTime = System.currentTimeMillis();
            List<Map<String, String>> records = ProcessFile.processFile(filePath);
            long endTime = System.currentTimeMillis();

            if (records != null && !records.isEmpty()) {
                Print.printDuplicates(records);
                Print.printFloorStats(records);
                System.out.println("Время обработки файла: " + (endTime - startTime) + " мс.");
            }
        }
    }


}
