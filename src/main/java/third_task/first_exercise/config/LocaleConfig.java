package third_task.first_exercise.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.util.Locale;

@Configuration
public class LocaleConfig {

    @Bean
    @Profile("ru")
    public Locale localeRu() {
        return new Locale("ru");
    }

    @Bean
    @Profile("en")
    public Locale localeEn() {
        return Locale.ENGLISH;
    }
}
