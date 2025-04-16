package first_exercise.third_exercise;

import first_task.third_exercise.Client;
import first_task.third_exercise.ClientService;
import first_task.third_exercise.Phone;
import first_task.third_exercise.PhoneType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ClientServiceTest {

    List<Client> clients;

    @BeforeEach
    void setUp() {
        clients = new ArrayList<>();
        clients.add(new Client(1, "Alice", 30, new ArrayList<>() {{
            add(new Phone("111-1111", PhoneType.MOBILE));
            add(new Phone("222-2222", PhoneType.STATIONARY));
        }}));
        clients.add(new Client(2, "Bob", 25, new ArrayList<>() {{
            add(new Phone("333-3333", PhoneType.MOBILE));
        }}));
        clients.add(new Client(3, "Charlie", 35, Collections.emptyList()));
        clients.add(new Client(4, "Alice", 28, new ArrayList<>() {{
            add(new Phone("444-4444", PhoneType.STATIONARY));
        }}));
        clients.add(new Client(5, "Eve", 40, new ArrayList<>() {{
            add(new Phone("555-5555", PhoneType.STATIONARY));
            add(new Phone("666-6666", PhoneType.MOBILE));
        }}));
    }

    @Test
    void testSumAgeByName() {
        // act
        int sumAge = ClientService.sumAgeByName(clients, "Alice");

        // assert
        assertEquals(58, sumAge);
    }

    @Test
    void testGetOrderedNames() {
        // arrange
        List<String> expectedOrder = List.of("Alice", "Bob", "Charlie", "Eve");

        // act
        Set<String> orderedNames = ClientService.getOrderedNames(clients);

        // assert
        assertEquals(expectedOrder, List.copyOf(orderedNames));
    }

    @Test
    void testExistsClientOlderThan() {
        // act
        boolean exists = ClientService.existsClientOlderThan(clients, 37);

        // assert
        assertTrue(exists);
    }

    @Test
    void testBuildIdNameMap() {
        // act
        Map<Integer, String> idNameMap = ClientService.buildIdNameMap(clients);

        // assert
        assertEquals(5, idNameMap.size());
        assertEquals("Alice", idNameMap.get(1));
    }

    @Test
    void testGroupByAge() {
        // act
        Map<Integer, List<Client>> groupedByAge = ClientService.groupByAge(clients);

        // assert
        assertTrue(groupedByAge.containsKey(30));
        assertEquals(1, groupedByAge.get(30).size());
    }

    @Test
    void testGetAllPhones() {
        // act
        String phones = ClientService.getAllPhones(clients);

        // assert
        assertTrue(phones.contains("111-1111"));
        assertTrue(phones.contains("222-2222"));
        assertTrue(phones.contains("333-3333"));
    }

    @Test
    void testFindOldestClientWithLandline() {
        // act
        Optional<Client> oldest = ClientService.findOldestClientWithStationary(clients);

        // assert
        assertTrue(oldest.isPresent());
        assertEquals("Eve", oldest.get().getName());
    }
}
