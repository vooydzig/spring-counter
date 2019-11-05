package com.vooydzig.springcounter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.core.JdbcTemplate;

@SpringBootApplication
public class SpringCounterApplicationJPA implements CommandLineRunner {

    private static final Logger log = LoggerFactory.getLogger(SpringCounterApplicationJPA.class);

    @Autowired
    TaskJpaRepository repository;

    public static void main(String[] args) {
        SpringApplication.run(SpringCounterApplicationJPA.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        log.info("Getting  row...");
        TaskJpa task = repository.findById(1);
        if (task == null) {
            log.info("Inserting row...");
            task = new TaskJpa(0);
            repository.save(task);
        }

        System.out.println(repository.foo());
        log.info("Task at start: " + task.toString() );
//        Thread.sleep(5000);
//        System.out.println(">>>>>>>> " + repository.updateCount(task.getId()));
        System.out.println(">>>>>>>> " + repository.updateCount());
//        task.setCounter(task.getCounter()+1);
        log.info("Task after increment: " + task.toString() );
        repository.save(task);
        log.info("Task after save: " + task.toString() );
        log.info("Task from DB save: " + repository.findById(task.getId()).toString() );
    }
}
