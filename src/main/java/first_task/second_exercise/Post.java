package first_task.second_exercise;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@Getter
@RequiredArgsConstructor
@ToString
public class Post {
    private final String text;
    private final Integer likesCount;
}
