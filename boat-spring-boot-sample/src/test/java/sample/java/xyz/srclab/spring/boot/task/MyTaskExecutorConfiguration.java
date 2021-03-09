package sample.java.xyz.srclab.spring.boot.task;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.annotation.EnableAsync;
import xyz.srclab.spring.boot.task.TaskExecutors;
import xyz.srclab.spring.boot.task.ThreadPoolProperties;

@Configuration
@EnableAsync
public class MyTaskExecutorConfiguration {

    @Bean
    public TaskExecutor taskExecutor() {
        ThreadPoolProperties poolProperties = new ThreadPoolProperties();
        poolProperties.setThreadNamePrefix("6666");
        return TaskExecutors.newTaskExecutor(poolProperties);
    }
}