package third_task.second_exercise.proxy;

import java.lang.reflect.*;
import java.time.LocalDateTime;

import lombok.RequiredArgsConstructor;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;
import third_task.second_exercise.config.Timed;
import third_task.second_exercise.service.MetricsProvider;

@RequiredArgsConstructor
public class TimingInterceptor implements InvocationHandler, MethodInterceptor {
    private final Object target;
    private final MetricsProvider provider;
    private final boolean classTimed;

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        return doInvoke(method, args, null);
    }

    @Override
    public Object intercept(Object obj, Method method, Object[] args, MethodProxy mp) throws Throwable {
        return doInvoke(method, args, mp);
    }

    private Object doInvoke(Method method, Object[] args, MethodProxy mp) throws Throwable {
        boolean shouldTime = Modifier.isPublic(method.getModifiers()) &&
                (classTimed || method.isAnnotationPresent(Timed.class));

        if (!shouldTime) {
            return callOriginal(method, args, mp);
        }

        LocalDateTime ts = LocalDateTime.now();
        long start = System.currentTimeMillis();
        Object result = callOriginal(method, args, mp);
        long duration = System.currentTimeMillis() - start;

        String name = target.getClass().getSimpleName() + "." + method.getName();
        provider.record(name, ts, duration);
        return result;
    }

    private Object callOriginal(Method method, Object[] args, MethodProxy mp) throws Throwable {
        if (mp != null) {
            return mp.invoke(target, args);
        } else {
            return method.invoke(target, args);
        }
    }
}