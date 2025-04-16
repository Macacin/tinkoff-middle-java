package first_task.third_exercise;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class Phone {
    private final String number;
    private final PhoneType type;
}
