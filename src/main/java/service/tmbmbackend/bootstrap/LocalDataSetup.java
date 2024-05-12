package service.tmbmbackend.bootstrap;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile("local")
@RequiredArgsConstructor
public class LocalDataSetup implements ApplicationRunner {

    @Override
    public void run(ApplicationArguments args) {
        System.out.println("안녕하세요!");
    }
}
