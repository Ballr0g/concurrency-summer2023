package course.concurrency.m2_async.executors.spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Component;

import java.util.concurrent.Executor;

@Component
public class AsyncClassTest {

    @Autowired
    public ApplicationContext context;

    @Autowired
    @Qualifier("applicationTaskExecutor")
    private ThreadPoolTaskExecutor executor;

    @Async
    public void runAsyncTask() {
        System.out.println("runAsyncTask: " + Thread.currentThread().getName());

        self().internalTask();
    }

    @Async("applicationTaskExecutor")
    public void internalTask() {
        System.out.println("internalTask: " + Thread.currentThread().getName());

        self().failingVoidTask();
    }

    @Async("applicationTaskExecutor")
    public void failingVoidTask() {
        System.out.println("failingVoidTask: " + Thread.currentThread().getName());
        throw new IllegalStateException("This exception is always thrown");
    }

    private AsyncClassTest self() {
        return context.getBean(AsyncClassTest.class);
    }
}
