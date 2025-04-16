package first_task.fourth_exercise;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Random;

@Getter
@Setter
public class User {

    private final Long id;
    private final String name;
    private List<User> friends;

    public User(String name) {
        this.name = name;
        this.id = new Random().nextLong();
    }
}
