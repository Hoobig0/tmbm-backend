package service.tmbmbackend.bootstrap;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
@Profile("local")
@RequiredArgsConstructor
public class LocalDataSetup implements CommandLineRunner {
    private final JdbcTemplate jdbcTemplate;

    @Override
    public void run(String... args) throws Exception {
        executeInsertSql();
    }

    private void executeInsertSql() throws Exception {
        Path path = Paths.get(new ClassPathResource("insert-statement.sql").getURI());
        try (Stream<String> stream = Files.lines(path)) {
            String sql = stream.collect(Collectors.joining(System.lineSeparator()));
            jdbcTemplate.execute(sql);
        } catch (Exception e) {
            throw new Exception("Failed to load and execute SQL file", e);
        }
    }
}
