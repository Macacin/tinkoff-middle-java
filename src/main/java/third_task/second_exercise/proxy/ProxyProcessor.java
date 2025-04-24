package third_task.second_exercise.proxy;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.cglib.proxy.Enhancer;
import third_task.second_exercise.config.Timed;
import third_task.second_exercise.service.MetricsProvider;

import java.lang.reflect.Proxy;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

@RequiredArgsConstructor
public class ProxyProcessor implements BeanPostProcessor {
    private final MetricsProvider provider;

    @Override
    public Object postProcessAfterInitialization(Object bean, @NonNull String beanName) throws BeansException {
        Class<?> cls = bean.getClass();
        boolean classTimed = cls.isAnnotationPresent(Timed.class);
        if (!hasTimedMethods(cls, classTimed)) {
            return bean;
        }
        return createProxy(bean, cls, classTimed);
    }

    private boolean hasTimedMethods(Class<?> cls, boolean classTimed) {
        for (Method m : cls.getMethods()) {
            if (Modifier.isPublic(m.getModifiers())
                    && (classTimed || m.isAnnotationPresent(Timed.class))) {
                return true;
            }
        }
        return false;
    }

    private Object createProxy(Object bean, Class<?> cls, boolean classTimed) {
        TimingInterceptor interceptor = new TimingInterceptor(bean, provider, classTimed);
        Class<?>[] interfaces = cls.getInterfaces();

        if (interfaces.length > 0) {
            return Proxy.newProxyInstance(cls.getClassLoader(), interfaces, interceptor);
        } else {
            Enhancer enhancer = new Enhancer();
            enhancer.setSuperclass(cls);
            enhancer.setCallback(interceptor);
            return enhancer.create();
        }
    }
}
