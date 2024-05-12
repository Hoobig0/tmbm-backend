package service.tmbmbackend.bootstrap;

import lombok.RequiredArgsConstructor;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;

@Component
@Profile("local")
@RequiredArgsConstructor
public class LocalDataSetup implements ApplicationRunner {

    @Override
    public void run(ApplicationArguments args) {
        this.CCTVDataLoad();
    }

    private void CCTVDataLoad() {
        try {
            InputStream in = new ClassPathResource("cctv.xlsx").getInputStream();
            Workbook workbook = new XSSFWorkbook(in);
            Sheet sheet = workbook.getSheetAt(0);
            for (Row row : sheet) {
                if(row.getRowNum() == 0){
                    continue;
                }
                System.out.println(row.getCell(0).getStringCellValue()
                        + row.getCell(1).getStringCellValue()
                        + row.getCell(2).getStringCellValue()
                        + row.getCell(11).getStringCellValue()
                        + row.getCell(12).getStringCellValue());
            }
            workbook.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
