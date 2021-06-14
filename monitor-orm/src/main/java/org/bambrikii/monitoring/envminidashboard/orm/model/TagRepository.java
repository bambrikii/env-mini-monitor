package org.bambrikii.monitoring.envminidashboard.orm.model;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TagRepository extends CrudRepository<TagEntity, Long> {
    List<TagEntity> findByName(String name);
}
