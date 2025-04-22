package first_task.third_exercise;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Getter
@RequiredArgsConstructor
public class Client {
    private final int id;
    private final String name;
    private final int age;
    private final List<Phone> phones;

    public boolean hasSameName(String targetName) {
        return name.equalsIgnoreCase(targetName);
    }

    public boolean isOlder(int targetAge) {
        return age > targetAge;
    }

    public boolean usesStationary() {
        return phones.stream().anyMatch(phone -> phone.getType() == PhoneType.STATIONARY);
    }
}
