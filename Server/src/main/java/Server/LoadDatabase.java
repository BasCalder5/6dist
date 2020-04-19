package Server;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LoadDatabase {

    @Bean
    CommandLineRunner initDatabase(AccountRepo repo){
        return args -> {
            repo.save(new Account("Bart", 200));
            repo.save(new Account("Jeff", 7));
            repo.save(new Account("Bas", 900000));
        };
    }
}
