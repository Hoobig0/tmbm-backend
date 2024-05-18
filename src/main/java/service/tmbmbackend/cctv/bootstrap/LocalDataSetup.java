package service.tmbmbackend.cctv.bootstrap;

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
import service.tmbmbackend.cctv.CCTV;
import service.tmbmbackend.cctv.repository.CCTVRepository;

import java.io.IOException;
import java.io.InputStream;

@Component
@Profile("local")
@RequiredArgsConstructor
public class LocalDataSetup implements ApplicationRunner {
    private final CCTVRepository cctvRepository;

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

                Long id = Long.parseLong(row.getCell(0).getStringCellValue());
                double x = Double.parseDouble(row.getCell(11).getStringCellValue());
                double y = Double.parseDouble(row.getCell(12).getStringCellValue());

                CCTV cctv = new CCTV(id, x, y);
                cctvRepository.save(cctv);
            }
            workbook.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
