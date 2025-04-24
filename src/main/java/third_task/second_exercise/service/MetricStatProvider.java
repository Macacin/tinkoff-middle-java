package third_task.second_exercise.service;

import third_task.second_exercise.metrics.MethodMetricsStat;

import java.time.LocalDateTime;
import java.util.List;

public interface MetricStatProvider {
    List<MethodMetricsStat> getTotalStatForPeriod(LocalDateTime from, LocalDateTime to);

    MethodMetricsStat getTotalStatByMethodForPeriod(
            String methodName,
            LocalDateTime from,
            LocalDateTime to
    );
}
