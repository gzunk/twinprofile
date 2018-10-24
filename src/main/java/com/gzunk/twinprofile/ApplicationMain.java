package com.gzunk.twinprofile;

import com.gzunk.twinprofile.config.PropertyFiles;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@Import({PropertyFiles.class})
public class ApplicationMain {

    private static final Logger LOG = LoggerFactory.getLogger(ApplicationMain.class);

    @Value("${queue.manager}")
    private String queueManager;

    @Value("${queue.name}")
    private String queueName;

    @Value("${treadmill.instance}")
    private String treadmillInstance;

    @Bean(name="QueueManager")
    public String queueManager() {
        LOG.info("Queue manager is {}", queueManager);
        return queueManager;
    }

    @Bean(name="QueueName")
    public String queueName() {
        LOG.info("Queue name is {}", queueName);
        return queueName;
    }

    @Bean(name="TreadmillInstance")
    public String treadmillInstance() {
        LOG.info("Treadmill instance is {}", treadmillInstance);
        return treadmillInstance;
    }

    public static void main(String[] args) {
        SpringApplication.run(ApplicationMain.class, args);
    }


}
