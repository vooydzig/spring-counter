package com.vooydzig.springcounter;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface TaskJpaRepository extends JpaRepository<TaskJpa, Long> {
    @Modifying
    @Query(value="UPDATE taskjpa SET counter = 100 where id = 1", nativeQuery = true)
//    @Query(value="UPDATE taskjpa SET counter = counter +1 where id = ?", nativeQuery = true)
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    int updateCount(long id);

    TaskJpa findById(long id);

    @Query("Select t from TaskJpa t where t.id = 1")
    TaskJpa foo();
}
