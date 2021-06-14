package org.bambrikii.monitoring.envminidashboard.orm.model;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DashboardRepository extends CrudRepository<DashboardEntity, Long> {
    Iterable<DashboardEntity> findByName(String name);
}
