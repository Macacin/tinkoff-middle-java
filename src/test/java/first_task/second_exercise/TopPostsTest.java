package first_task.second_exercise;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TopPostsTest {
    @Test
    public void testGetTop10WithMoreThan10Posts() {
        // ararnge
        List<Post> posts = new ArrayList<>();
        for (int i = 1; i <= 15; i++) {
            posts.add(new Post("Post " + i, i * 5));
        }

        // act
        List<Post> top10 = TopPosts.getTopTen(posts);

        // assert
        assertEquals(10, top10.size());
        for (int i = 0; i < top10.size() - 1; i++) {
            assertTrue(top10.get(i).getLikesCount() >= top10.get(i + 1).getLikesCount());
        }

        assertEquals(30, top10.get(top10.size() - 1).getLikesCount());
    }

    @Test
    public void testGetTop10WithLessThan10Posts() {
        // arrange
        List<Post> posts = new ArrayList<>();
        posts.add(new Post("Post 1", 50));
        posts.add(new Post("Post 2", 10));
        posts.add(new Post("Post 3", 30));

        // act
        List<Post> topPosts = TopPosts.getTopTen(posts);

        // assert
        assertEquals(posts.size(), topPosts.size());
    }

    @Test
    public void testGetTop10WithEqualLikes() {
        // arrange
        List<Post> posts = new ArrayList<>();
        for (int i = 1; i <= 12; i++) {
            posts.add(new Post("Post " + i, 100));
        }

        // act
        List<Post> top10 = TopPosts.getTopTen(posts);

        // assert
        for (Post post : top10) {
            assertEquals(100, post.getLikesCount(), "Каждый пост должен иметь 100 лайков");
        }
    }
}
