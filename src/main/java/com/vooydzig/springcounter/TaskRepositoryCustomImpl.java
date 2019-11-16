package com.vooydzig.springcounter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.*;

public class TaskRepositoryCustomImpl implements TaskRepositoryCustom {

    @Autowired
    private EntityManagerFactory entityManagerFactory;

    @Override
    public Task update(Task task) {
        EntityManager _em = entityManagerFactory.createEntityManager();
        _em.getTransaction().begin();
        Query query = _em.createNativeQuery("UPDATE task t set counter=counter+1 where id=" + task.getId().toString());
        query.executeUpdate();
        _em.getTransaction().commit();
        task = _em.merge(task);
        _em.refresh(task);
        return task;
    }
}
