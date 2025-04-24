package third_task.second_exercise;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import third_task.second_exercise.config.AppConfig;
import third_task.second_exercise.service.MetricStatProvider;
import third_task.second_exercise.metrics.MethodMetricsStat;
import third_task.second_exercise.service.DemoService;

import java.time.LocalDateTime;
import java.util.List;

public class Application {
    public static void main(String[] args) throws Exception {
        try (var ctx = new AnnotationConfigApplicationContext(AppConfig.class)) {
            DemoService demo = ctx.getBean(DemoService.class);

            demo.fastMethod();
            demo.slowMethod();
            demo.fastMethod();
            demo.slowMethod();

            MetricStatProvider provider = ctx.getBean(MetricStatProvider.class);

            LocalDateTime from = LocalDateTime.now().minusMinutes(1);
            LocalDateTime to   = LocalDateTime.now().plusMinutes(1);

            List<MethodMetricsStat> stats = provider.getTotalStatForPeriod(from, to);
            stats.forEach(stat -> System.out.printf(
                    "%s: calls=%d, min=%dms, avg=%dms, max=%dms\n",
                    stat.getMethodName(),
                    stat.getInvocationsCount(),
                    stat.getMinTime(),
                    stat.getAverageTime(),
                    stat.getMaxTime()
            ));
        }
    }
}