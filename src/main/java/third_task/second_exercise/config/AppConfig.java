package third_task.second_exercise.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;

@Import(TimedConfig.class)
@ComponentScan("third_task.second_exercise")
public class AppConfig {
}
