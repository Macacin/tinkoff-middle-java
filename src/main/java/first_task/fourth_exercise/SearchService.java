package first_task.fourth_exercise;

import java.util.List;

public interface SearchService {
    List<User> searchForFriendsInWidth(User me, String name);
    List<User> searchForFriendsInDepth(User me, String name);
}
