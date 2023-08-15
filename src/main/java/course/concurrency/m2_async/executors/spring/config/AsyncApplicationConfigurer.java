package course.concurrency.m2_async.executors.spring.config;

import course.concurrency.m2_async.exception.DefaultAsyncUncaughtExceptionHandler;
import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;

@Configuration
public class AsyncApplicationConfigurer implements AsyncConfigurer {
    @Override
    public Executor getAsyncExecutor() {
        ThreadPoolTaskExecutor taskExecutor = new ThreadPoolTaskExecutor();
        taskExecutor.setThreadNamePrefix("custom-task-");
        taskExecutor.setCorePoolSize(4);
        taskExecutor.setMaxPoolSize(36);
        taskExecutor.setQueueCapacity(60);
        taskExecutor.initialize();
        return taskExecutor;
    }

    @Override
    public AsyncUncaughtExceptionHandler getAsyncUncaughtExceptionHandler() {
        return new DefaultAsyncUncaughtExceptionHandler();
    }
}
