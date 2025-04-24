package third_task.second_exercise.config;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.annotation.*;
import third_task.second_exercise.service.MetricStatProvider;
import third_task.second_exercise.service.MetricsProvider;
import third_task.second_exercise.proxy.ProxyProcessor;

@Configuration
public class TimedConfig {

    @Bean
    @Role(BeanDefinition.ROLE_INFRASTRUCTURE)
    public static MetricsProvider metricsProvider() {
        return new MetricsProvider();
    }

    @Bean
    @Primary
    public static MetricStatProvider metricStatProvider(MetricsProvider collector) {
        return collector;
    }

    @Bean
    @Role(BeanDefinition.ROLE_INFRASTRUCTURE)
    public static BeanPostProcessor timedAnnotationBeanPostProcessor(MetricsProvider collector) {
        return new ProxyProcessor(collector);
    }
}
