import java.util.List;
import java.util.Map;
public class ProcessFile {
    /**
     * Определяет обработчик файла в зависимости от его расширения.
     *
     * @param filePath путь к файлу
     * @return список записей из файла или null, если формат не поддерживается
     */
    public static List<Map<String, String>> processFile(String filePath) {
        if (filePath.endsWith(".csv")) {
            return Pars.loadCsv(filePath);
        } else if (filePath.endsWith(".xml")) {
            return Pars.loadXml(filePath);
        } else {
            System.out.println("Формат файла не поддерживается. Пожалуйста, используйте CSV или XML.");
            return null;
        }
    }
}