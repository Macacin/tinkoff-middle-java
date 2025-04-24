package third_task.second_exercise.service;

import third_task.second_exercise.metrics.MethodMetricsStat;
import third_task.second_exercise.metrics.MetricEvent;

import java.time.LocalDateTime;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class MetricsProvider implements MetricStatProvider {
    private final Map<String, List<MetricEvent>> events = new ConcurrentHashMap<>();

    public void record(String methodName, LocalDateTime ts, long duration) {
        events.computeIfAbsent(methodName, k -> Collections.synchronizedList(new ArrayList<>()))
                .add(new MetricEvent(methodName, ts, duration));
    }

    @Override
    public List<MethodMetricsStat> getTotalStatForPeriod(LocalDateTime from, LocalDateTime to) {
        return events.entrySet().stream()
                .map(e -> createStat(e.getKey(), e.getValue(), from, to))
                .toList();
    }

    @Override
    public MethodMetricsStat getTotalStatByMethodForPeriod(String method, LocalDateTime from, LocalDateTime to) {
        return createStat(method, events.getOrDefault(method, List.of()), from, to);
    }

    private MethodMetricsStat createStat(String methodName, List<MetricEvent> list,
                                         LocalDateTime from, LocalDateTime to) {
        List<MetricEvent> filtered = list.stream()
                .filter(ev -> !ev.timestamp().isBefore(from) && !ev.timestamp().isAfter(to))
                .toList();

        MethodMetricsStat stat = new MethodMetricsStat();
        stat.setMethodName(methodName);
        stat.setInvocationsCount(filtered.size());
        if (!filtered.isEmpty()) {
            long min = filtered.stream().mapToLong(MetricEvent::durationMs).min().orElse(0);
            long max = filtered.stream().mapToLong(MetricEvent::durationMs).max().orElse(0);
            long avg = (long) filtered.stream().mapToLong(MetricEvent::durationMs).average().orElse(0);
            stat.setMinTime((int) min);
            stat.setMaxTime((int) max);
            stat.setAverageTime((int) avg);
        }
        return stat;
    }
}
