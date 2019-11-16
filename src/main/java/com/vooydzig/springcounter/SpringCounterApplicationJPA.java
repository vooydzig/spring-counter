package com.vooydzig.springcounter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.sql.DataSource;

@SpringBootApplication
public class SpringCounterApplicationJPA implements CommandLineRunner {

    private static final Logger log = LoggerFactory.getLogger(SpringCounterApplicationJPA.class);

    @Autowired
    TaskRepository repository;

    @Autowired
    DataSource dataSource;

    public static void main(String[] args) {
        SpringApplication.run(SpringCounterApplicationJPA.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        log.info("Datasource: " + dataSource.getConnection());

        log.info("Getting  row...");
        Task task = repository.get(1L);
        if (task == null) {
            log.info("Inserting row...");
            task = new Task(0);
            repository.save(task);
        }

        log.info("Task at start: " + task.toString());
        Thread.sleep(5000);
        task = repository.update(task);
        log.info("Task after increment: " + task.toString());
        repository.save(task);
        log.info("Task after save: " + task.toString());
        log.info("Task from DB save: " + repository.findById(task.getId()).toString());
    }
}
