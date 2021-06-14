package org.bambrikii.monitoring.envminidashboard.web.services;

import org.bambrikii.monitoring.envminidashboard.impl.dashboard.DashboardLoader;
import org.bambrikii.monitoring.envminidashboard.impl.loader.LinuxSysProbe;
import org.bambrikii.monitoring.envminidashboard.impl.loader.WinSysProbe;
import org.bambrikii.monitoring.envminidashboard.impl.loader.WindowsAppLogsProbe;
import org.bambrikii.monitoring.envminidashboard.model.Dashboard;
import org.bambrikii.monitoring.envminidashboard.orm.model.DashboardEntity;
import org.bambrikii.monitoring.envminidashboard.orm.model.DashboardRepository;
import org.bambrikii.monitoring.envminidashboard.orm.model.EnvEntity;
import org.bambrikii.monitoring.envminidashboard.orm.model.EnvRepository;
import org.bambrikii.monitoring.envminidashboard.web.converters.DashboardConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

@Service
public class DashboardService {
    @Autowired
    private DashboardRepository dashboardRepository;
    @Autowired
    private EnvRepository envRepository;

    private final DashboardLoader loader = new DashboardLoader()
            .probe(new LinuxSysProbe())
            .probe(new WinSysProbe())
            .probe(new WindowsAppLogsProbe());

    private Stream<Dashboard> findByName(String name) {
        Iterable<DashboardEntity> byName = StringUtils.isEmpty(name)
                ? dashboardRepository.findAll()
                : dashboardRepository.findByName(name);
        return StreamSupport
                .stream(byName
                        .spliterator(), true)
                .map(DashboardConverter::convert);
    }

    public List<Dashboard> find(String name) {
        return findByName(name)
                .map(dashboard1 -> {
                    loader.load(dashboard1);
                    return dashboard1;
                })
                .collect(Collectors.toList());
    }

    public List<Dashboard> collect(String name) {
        return findByName(name)
                .map(dashboard1 -> {
                    loader.load(dashboard1);
                    return dashboard1;
                })
                .collect(Collectors.toList());
    }

    public Dashboard read(Long id) {
        return DashboardConverter.convert(dashboardRepository.findById(id).get());
    }

    public Dashboard update(Long id, Dashboard pojo) {
        DashboardEntity entity;
        Optional<DashboardEntity> entityOptional = id == null
                ? Optional.empty()
                : dashboardRepository.findById(id);
        entity = entityOptional.isPresent()
                ? entityOptional.get()
                : new DashboardEntity();
        DashboardConverter.convert(pojo, entity);
        dashboardRepository.save(entity);
        return DashboardConverter.convert(entity);
    }

    public void delete(Long id) {
        dashboardRepository.deleteById(id);
    }

    public void addEnv(Long id, Long envId) {
        DashboardEntity dashboard = dashboardRepository.findById(id).get();
        EnvEntity env = envRepository.findById(envId).get();

        dashboard.getEnvs().add(env);
        dashboardRepository.save(dashboard);
    }

    public void deleteEnv(Long id, Long envId) {
        DashboardEntity dashboard = dashboardRepository.findById(id).get();
        EnvEntity env = envRepository.findById(envId).get();

        dashboard.getEnvs().remove(env);
        dashboardRepository.save(dashboard);
    }
}
