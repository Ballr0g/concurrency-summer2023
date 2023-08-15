package course.concurrency.m2_async.exception;

import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;

import java.lang.reflect.Method;

public class DefaultAsyncUncaughtExceptionHandler implements AsyncUncaughtExceptionHandler {
    @Override
    public void handleUncaughtException(Throwable ex, Method method, Object... params) {
        System.out.printf("Exception in %s.%s with message: \"%s\" %n",
                method.getDeclaringClass(), method.getName(), ex.getMessage());
    }
}
