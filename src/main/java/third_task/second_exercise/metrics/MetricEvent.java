package third_task.second_exercise.metrics;

import java.time.LocalDateTime;

public record MetricEvent(
        String methodName,
        LocalDateTime timestamp,
        long durationMs
) {
}
