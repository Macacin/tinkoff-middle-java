package third_task.second_exercise.metrics;

import lombok.Data;

@Data
public class MethodMetricsStat {
    private String methodName;
    private int invocationsCount;
    private int minTime;
    private int averageTime;
    private int maxTime;
}
