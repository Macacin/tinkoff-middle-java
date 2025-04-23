package third_task.first_exercise;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.core.io.support.ResourcePropertySource;
import third_task.first_exercise.config.AppConfig;
import third_task.first_exercise.config.DateConfig;
import third_task.first_exercise.config.LocaleConfig;
import third_task.first_exercise.service.DateService;

import java.util.Scanner;

public class Application {

    public static void main(String[] args) throws Exception {
        var ctx = new AnnotationConfigApplicationContext();
        ctx.getEnvironment()
                .getPropertySources()
                .addFirst(new ResourcePropertySource("classpath:app.properties"));

        String profile = ctx.getEnvironment().getProperty("profiles.active");
        ctx.getEnvironment().setActiveProfiles(profile);

        ctx.register(AppConfig.class, DateConfig.class, LocaleConfig.class);
        ctx.refresh();

        var svc = ctx.getBean(DateService.class);
        var sc = new Scanner(System.in);

        while (true) {
            System.out.println(svc.prompt());
            String cmd = sc.nextLine().trim();
            if (cmd.equals("today")) {
                System.out.println(svc.formatToday());
            } else if (cmd.equals("today-iso")) {
                System.out.println(svc.formatIso());
            } else if (cmd.equals("exit")) {
                break;
            } else {
                System.out.println(svc.unknown());
            }
        }
    }
}