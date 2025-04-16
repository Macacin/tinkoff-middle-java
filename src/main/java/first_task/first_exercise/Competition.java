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

        Map<String, Integer> scores = new HashMap<>();
        for (String competitor : competitors) {
            String name = parseName(competitor);
            Integer score = parseScore(competitor);
            scores.put(name, scores.getOrDefault(name, 0) + score);
        }

        int maxScore = Collections.max(scores.values());

        Map<String, Integer> winnerScores = new HashMap<>();
        for (String competitor : competitors) {
            String name = parseName(competitor);
            Integer score = parseScore(competitor);
            int cumScore = winnerScores.getOrDefault(name, 0) + score;
            winnerScores.put(name, cumScore);

            if ((scores.get(name) == maxScore) && (cumScore == maxScore)) {
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
