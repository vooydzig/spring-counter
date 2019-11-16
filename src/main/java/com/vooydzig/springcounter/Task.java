package com.vooydzig.springcounter;

import lombok.ToString;

import javax.persistence.*;

@Entity
@ToString
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private long counter;

    private Task() { }

    Task(long counter) {
        this.counter = counter;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public long getCounter() {
        return counter;
    }

    public void setCounter(long counter) {
        this.counter = counter;
    }
}
