package org.bambrikii.monitoring.envminidashboard.orm.model;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DashboardRepository extends CrudRepository<DashboardEntity, Long> {
    Optional<DashboardEntity> findByName(String name);
}
