package first_task.first_exercise;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Competition {

    public static String findWinner(List<String> competitors) {
        if (competitors.size() == 1) {
            return competitors.get(0);
        }

        Map<String, Integer> playerScores = new HashMap<>();

        int maxScore = Integer.MIN_VALUE;
        String winner = null;
        int step = 0;
        int winnerStep = 0;

        for (String competitor : competitors) {
            String name = parseName(competitor);
            int score = parseScore(competitor);

            int current = playerScores.merge(name, score, Integer::sum);

            if (current > maxScore) {
                maxScore = current;
                winner = name;
                winnerStep = step;
            } else if (current == maxScore && step < winnerStep) {
                winner = name;
                winnerStep = step;
            }

            step++;
        }

        return winner;
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
