package first_task.third_exercise;

import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

public class ClientService {

    public static int sumAgeByName(List<Client> clients, String targetName) {
        return clients.stream()
                .filter(client -> client.hasSameName(targetName))
                .mapToInt(Client::getAge)
                .sum();
    }

    public static Set<String> getOrderedNames(List<Client> clients) {
        return clients.stream()
                .map(Client::getName)
                .collect(Collectors.toCollection(LinkedHashSet::new));
    }

    public static boolean existsClientOlderThan(List<Client> clients, int ageThreshold) {
        return clients.stream()
                .anyMatch(client -> client.isOlder(ageThreshold));
    }

    public static Map<Integer, String> buildIdNameMap(List<Client> clients) {
        return clients.stream()
                .collect(Collectors.toMap(
                        Client::getId,
                        Client::getName,
                        (existing, replacement) -> existing,
                        LinkedHashMap::new));
    }

    public static Map<Integer, List<Client>> groupByAge(List<Client> clients) {
        return clients.stream()
                .collect(Collectors.groupingBy(Client::getAge));
    }

    public static String getAllPhones(List<Client> clients) {
        return clients.stream()
                .map(Client::getPhones)
                .filter(phones -> phones != null && !phones.isEmpty())
                .flatMap(List::stream)
                .map(Phone::getNumber)
                .filter(number -> number != null && !number.isBlank())
                .collect(Collectors.joining(", "));
    }

    public static Optional<Client> findOldestClientWithStationary(List<Client> clients) {
        return clients.stream()
                .filter(Client::usesStationary)
                .max(Comparator.comparingInt(Client::getAge));
    }
}
