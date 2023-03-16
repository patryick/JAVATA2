package pwr.javata.loader;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import pwr.javata.model.FileData;
import pwr.javata.model.State;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Map;
import java.util.WeakHashMap;

public class FileDataLoaderImpl implements FileDataLoader{

    Map<String, FileData> fileDataMap = new WeakHashMap<>();

    public FileData loadFileData(String path) throws IOException {
        FileData result = fileDataMap.get(path);
        if (result == null) {
            File file = new File(path);
            FileInputStream fis = new FileInputStream(file);
            Workbook wb = new XSSFWorkbook(fis);
            Sheet sheet = wb.getSheetAt(0);
            String[] fileLines = new String[5];

            for (int i = 0; i < 5; i++) {
                Row row = sheet.getRow(i);
                fileLines[i] = rowToString(row);
            }

            result = new FileData(fileLines, State.LOADED_FROM_FILE);
            fileDataMap.put(path, result);
        }
        else {
            result.setState(State.LOADED_FROM_MEMORY);
        }
        return result;
    }

    private String rowToString(Row row) {
        return "" + row.getCell(0).getDateCellValue().getHours() + ":" +
                (row.getCell(0).getDateCellValue().getMinutes() == 0 ? "00" : row.getCell(0).getDateCellValue().getMinutes())  +
                " " + row.getCell(1).getNumericCellValue() + " " +
                row.getCell(2).getNumericCellValue() + " " +
                row.getCell(3).getNumericCellValue();
    }
}
