package org.bambrikii.monitoring.envminidashboard.orm.model;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EnvRepository extends CrudRepository<EnvEntity, Long> {
}
