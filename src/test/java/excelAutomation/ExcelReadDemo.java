package excelAutomation;

import com.weborders.utilities.Driver;
import org.apache.poi.ss.usermodel.*;
import org.testng.annotations.Test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class ExcelReadDemo {

    @Test
    public void readXLFile() throws IOException {
        String path = "src/test/resources/Countries.xls";

        FileInputStream inputStream = new FileInputStream(path);

        Workbook workbook = WorkbookFactory.create(inputStream);
        Sheet worksheet = workbook.getSheetAt(0);
        Row row = worksheet.getRow(0);
        Cell c1 = row.getCell(0);
        Cell c2 = row.getCell(1);

        System.out.println(c1);
        System.out.println(c2);

        System.out.println(workbook.getSheetAt(0).getRow(1).getCell(0));
        System.out.println(worksheet.getRow(1).getCell(1));

        int rowNums = worksheet.getLastRowNum();
        //Printing out all the country names and capitals in the sheet
        for (int i=1; i < rowNums; i++){
            System.out.println("Country #"+ i + " : " + worksheet.getRow(i).getCell(0)+
                    " --> "+ worksheet.getRow(i).getCell(1));
        }

        // Looping to get it into a map
        Map<String, String> map = new HashMap<>();
        for (int i=1; i < rowNums; i++){
            map.put(worksheet.getRow(i).getCell(0).toString(), worksheet.getRow(i).getCell(1).toString());
        }
        System.out.println(map);

        inputStream.close();
        workbook.close();
    }

}
