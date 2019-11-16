package com.vooydzig.springcounter;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long>, TaskRepositoryCustom {

    @Query("Select t from Task t where t.id = :id")
    Task get(long id);

    @Modifying
    @Transactional(propagation = Propagation.REQUIRES_NEW, isolation = Isolation.READ_COMMITTED)
    @Query(value="UPDATE taskjpa SET counter = counter +1 where id = ?", nativeQuery = true)
    public int updateCount(long id);

}
