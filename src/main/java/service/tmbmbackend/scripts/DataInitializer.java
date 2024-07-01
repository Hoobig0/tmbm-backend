package service.tmbmbackend.scripts;

import service.tmbmbackend.entity.SecurityCamera;
import service.tmbmbackend.repository.SecurityCameraRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class DataInitializer implements CommandLineRunner {

    private final SecurityCameraRepository securityCameraRepository;

    @Autowired
    public DataInitializer(SecurityCameraRepository securityCameraRepository) {
        this.securityCameraRepository = securityCameraRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        loadSecurityCameraData("insert-statement.sql");
    }

    private void loadSecurityCameraData(String filePath) {
        Pattern pattern = Pattern.compile("ST_GeomFromText\\('POINT\\(([^\\s]+)\\s([^\\)]+)\\)'\\)");
        try (BufferedReader br = new BufferedReader(new InputStreamReader(
            new ClassPathResource(filePath).getInputStream(), StandardCharsets.UTF_8))) {
            String line;
            int idCounter = 1;
            while ((line = br.readLine()) != null) {
                if (line.trim().isEmpty() || !line.contains("ST_GeomFromText")) {
                    continue;
                }
                Matcher matcher = pattern.matcher(line);
                while (matcher.find()) {
                    double x = Double.parseDouble(matcher.group(1));
                    double y = Double.parseDouble(matcher.group(2));
                    String id = "camera" + idCounter++;
                    SecurityCamera camera = new SecurityCamera(id, x, y);
                    securityCameraRepository.save(camera);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
