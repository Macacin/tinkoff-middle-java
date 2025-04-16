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
            if (minHeap.size() < 10) {
                minHeap.offer(post);
            } else if (post.getLikesCount() > minHeap.peek().getLikesCount()) {
                minHeap.poll();
                minHeap.offer(post);
            }
        }

        List<Post> topPosts = new ArrayList<>(minHeap);
        topPosts.sort(Comparator.comparingInt(Post::getLikesCount).reversed());

        return topPosts;
    }
}
