package third_task.first_exercise.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.Scope;

import java.text.SimpleDateFormat;
import java.util.Locale;

@Configuration
@PropertySource("classpath:app.properties")
@ComponentScan("third_task.first_exercise")
public class DateConfig {

    @Bean(name = "todayFormatter")
    @Scope("prototype")
    @Profile("ru")
    public SimpleDateFormat todayFormatterRu() {
        return new SimpleDateFormat("EEEE, d MMMM, yyyy", new Locale("ru"));
    }

    @Bean(name = "todayFormatter")
    @Scope("prototype")
    @Profile("en")
    public SimpleDateFormat todayFormatterEn() {
        return new SimpleDateFormat("EEEE, MMMM, d, yyyy", Locale.ENGLISH);
    }

    @Bean(name = "isoFormatter")
    @Scope("prototype")
    public SimpleDateFormat isoFormatter() {
        return new SimpleDateFormat("yyyy-MM-dd");
    }
}
