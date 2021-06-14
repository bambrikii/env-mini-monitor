package org.bambrikii.monitoring.envminidashboard.web.services;

import org.bambrikii.monitoring.envminidashboard.impl.dashboard.DashboardLoader;
import org.bambrikii.monitoring.envminidashboard.impl.loader.LinuxSysProbe;
import org.bambrikii.monitoring.envminidashboard.impl.loader.WinSysProbe;
import org.bambrikii.monitoring.envminidashboard.impl.loader.WindowsAppLogsProbe;
import org.bambrikii.monitoring.envminidashboard.model.Dashboard;
import org.bambrikii.monitoring.envminidashboard.orm.model.DashboardEntity;
import org.bambrikii.monitoring.envminidashboard.orm.model.DashboardRepository;
import org.bambrikii.monitoring.envminidashboard.web.converters.DashboardConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

@Service
public class DashboardService {
    @Autowired
    private DashboardRepository dashboardRepository;

    private final DashboardLoader loader = new DashboardLoader()
            .probe(new LinuxSysProbe())
            .probe(new WinSysProbe())
            .probe(new WindowsAppLogsProbe());

    private Stream<Dashboard> getDashboardStream() {
        return StreamSupport
                .stream(dashboardRepository
                        .findAll()
                        .spliterator(), true)
                .map(DashboardConverter::convert);
    }

    public List<Dashboard> list() {
        return getDashboardStream()
                .map(dashboard1 -> {
                    loader.load(dashboard1);
                    return dashboard1;
                })
                .collect(Collectors.toList());
    }

    public List<Dashboard> collect() {
        return getDashboardStream()
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
}
