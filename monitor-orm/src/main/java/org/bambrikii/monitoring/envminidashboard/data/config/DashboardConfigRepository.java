package org.bambrikii.monitoring.envminidashboard.data.config;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DashboardConfigRepository extends CrudRepository<DashboardConfigEntity, Long> {
}
