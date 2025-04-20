package first_task.first_exercise;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Competition {

    public static String findWinner(List<String> competitors) {
        if (competitors.size() == 1) {
            return competitors.get(0);
        }

        Map<String, Integer> totalScores = new HashMap<>();
        for (String competitor : competitors) {
            String name = parseName(competitor);
            Integer score = parseScore(competitor);
            totalScores.merge(name, score, Integer::sum);
        }

        int maxScore = Collections.max(totalScores.values());

        Map<String, Integer> currentScores = new HashMap<>();
        for (String competitor : competitors) {
            String name = parseName(competitor);
            Integer score = parseScore(competitor);
            currentScores.merge(name, score, Integer::sum);

            if ((totalScores.get(name) == maxScore) && (currentScores.get(name) == maxScore)) {
                return name;
            }
        }
        return "";
    }

    private static String parseName(String competitor) {
        String[] parts = competitor.split(" ");
        return parts[0];
    }

    private static Integer parseScore(String competitor) {
        String[] parts = competitor.split(" ");
        return Integer.parseInt(parts[1]);
    }
}
