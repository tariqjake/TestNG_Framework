package excelAutomation;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.testng.annotations.Test;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class ExcelWriteDemo {

    @Test
    public void writeDemo() throws IOException {
        String path = "src/test/resources/Countries.xls";
        FileInputStream inputStream = new FileInputStream(path);
        Workbook workbook = WorkbookFactory.create(inputStream);
        Sheet workSheet = workbook.getSheetAt(0);
        for (int i = 0; i <= workSheet.getLastRowNum(); i++) {
            if (workSheet.getRow(i).getCell(2) == null)
                workSheet.getRow(i).createCell(2);
            if (i == 0)
                workSheet.getRow(i).getCell(2).setCellValue("Lucky Numbers");
            else
                workSheet.getRow(i).getCell(2).setCellValue((int) (Math.random() * 10));
        }

        FileOutputStream outputStream = new FileOutputStream(path);
        workbook.write(outputStream);

        inputStream.close();
        outputStream.close();
        workbook.close();

    }
}

