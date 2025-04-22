package first_task.second_exercise;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

public class TopPosts {

    public static List<Post> getTopTen(List<Post> posts) {
        PriorityQueue<Post> minHeap = new PriorityQueue<>(10,
                Comparator.comparingInt(Post::getLikesCount));

        for (Post post : posts) {
            if (minHeap.size() < 10 || post.getLikesCount() > minHeap.peek().getLikesCount()) {
                minHeap.offer(post);
                if (minHeap.size() > 10) {
                    minHeap.poll();
                }
            }
        }

        List<Post> topPosts = new ArrayList<>(minHeap);
        topPosts.sort(Comparator.comparingInt(Post::getLikesCount).reversed());

        return topPosts;
    }
}
