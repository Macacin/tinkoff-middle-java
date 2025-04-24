package third_task.second_exercise.service;

import org.springframework.stereotype.Service;
import third_task.second_exercise.config.Timed;

import java.util.Random;

@Service
@Timed
public class DemoService {
    private final Random r = new Random();

    @Timed
    public void fastMethod() throws InterruptedException {
        Thread.sleep(r.nextInt(50, 100));
    }

    public void slowMethod() throws InterruptedException {
        Thread.sleep(r.nextInt(500, 1000));
    }
}
