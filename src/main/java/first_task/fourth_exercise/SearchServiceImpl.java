package first_task.fourth_exercise;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;

public class SearchServiceImpl implements SearchService {

    @Override
    public List<User> searchForFriendsInWidth(User me, String name) {
        List<User> result = new ArrayList<>();

        List<User> initialFriends = me.getFriends();
        if (initialFriends == null || initialFriends.isEmpty()) {
            return result;
        }

        Set<Long> visited = new HashSet<>();
        Queue<User> queue = new LinkedList<>();

        visited.add(me.getId());
        for (User f : initialFriends) {
            if (f != null) {
                queue.offer(f);
            }
        }

        while (!queue.isEmpty()) {
            User currUser = queue.poll();
            if (currUser == null || !visited.add(currUser.getId())) {
                continue;
            }

            if (name.equals(currUser.getName())) {
                result.add(currUser);
            }

            List<User> friends = currUser.getFriends();
            for (User friend : friends) {
                if (friend != null && !visited.contains(friend.getId())) {
                    queue.offer(friend);
                }
            }
        }

        return result;
    }

    @Override
    public List<User> searchForFriendsInDepth(User me, String name) {
        List<User> result = new ArrayList<>();

        List<User> initialFriends = me.getFriends();
        if (initialFriends == null || initialFriends.isEmpty()) {
            return result;
        }

        Set<Long> visited = new HashSet<>();
        Deque<User> stack = new ArrayDeque<>();

        visited.add(me.getId());
        for (User f : initialFriends) {
            if (f != null) {
                stack.push(f);
            }
        }

        while (!stack.isEmpty()) {
            User current = stack.pop();
            if (!visited.add(current.getId())) {
                continue;
            }

            if (name.equals(current.getName())) {
                result.add(current);
            }

            List<User> friends = current.getFriends();
            for (User friend : friends) {
                if (friend != null && !visited.contains(friend.getId())) {
                    stack.push(friend);
                }
            }
        }

        return result;
    }
}
