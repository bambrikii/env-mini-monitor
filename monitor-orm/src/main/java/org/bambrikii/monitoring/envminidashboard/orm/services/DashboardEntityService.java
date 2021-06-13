package org.bambrikii.monitoring.envminidashboard.orm.services;

import org.bambrikii.monitoring.envminidashboard.orm.model.ConnConfigRepository;
import org.bambrikii.monitoring.envminidashboard.orm.model.DashboardEntity;
import org.bambrikii.monitoring.envminidashboard.orm.model.DashboardRepository;
import org.bambrikii.monitoring.envminidashboard.orm.model.EnvEntity;
import org.bambrikii.monitoring.envminidashboard.orm.model.EnvRepository;
import org.bambrikii.monitoring.envminidashboard.orm.model.MetricLogEntity;
import org.bambrikii.monitoring.envminidashboard.orm.model.MetricLogRepository;
import org.bambrikii.monitoring.envminidashboard.orm.model.PhysicalConnEntity;
import org.bambrikii.monitoring.envminidashboard.orm.model.PhysicalConnRepository;
import org.bambrikii.monitoring.envminidashboard.orm.model.SshConnConfigEntity;
import org.bambrikii.monitoring.envminidashboard.orm.model.SshConnConfigRepository;
import org.bambrikii.monitoring.envminidashboard.orm.model.TagEntity;
import org.bambrikii.monitoring.envminidashboard.orm.model.TagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Calendar;
import java.util.Optional;

@Service
public class DashboardEntityService {
    @Autowired
    private DashboardRepository dashboardRepository;
    @Autowired
    private TagRepository tagRepository;
    @Autowired
    private ConnConfigRepository connConfigRepository;
    @Autowired
    private SshConnConfigRepository sshConnConfigRepository;
    @Autowired
    private PhysicalConnRepository physicalConnRepository;
    @Autowired
    private EnvRepository envRepository;
    @Autowired
    private MetricLogRepository metricLogRepository;

    public Iterable<DashboardEntity> findAll() {
        return dashboardRepository.findAll();
    }

    public DashboardEntity createDashboard(EnvEntity dev) {
        DashboardEntity dashboad = new DashboardEntity();
        dashboad.getEnvs().add(dev);
        return dashboad;
    }

    public EnvEntity createEnv(PhysicalConnEntity conn1, String code) {
        EnvEntity dev = new EnvEntity();
        dev.setCode(code);
        dev.getConnections().add(conn1);
        envRepository.save(dev);
        return dev;
    }

    public PhysicalConnEntity createConn(TagEntity devTag1, SshConnConfigEntity sshConfig1, MetricLogEntity... log1) {
        PhysicalConnEntity conn1 = new PhysicalConnEntity();
        conn1.setConfig(sshConfig1);
        conn1.getTags().add(devTag1);
        conn1.getMetricLogs().addAll(Arrays.asList(log1));
        physicalConnRepository.save(conn1);
        return conn1;
    }

    public MetricLogEntity createLog(String code, String val, Calendar time) {
        MetricLogEntity log1 = new MetricLogEntity();
        log1.setCode(code);
        log1.setValue(val);
        log1.setTime(time);
        metricLogRepository.save(log1);
        return log1;
    }

    public SshConnConfigEntity createSshConnConfig(TagEntity devTag1, String host, int port) {
        SshConnConfigEntity sshConfig1 = new SshConnConfigEntity();
        sshConfig1.setHost(host);
        sshConfig1.setPort(port);
        sshConfig1.getTags().add(devTag1);
        sshConnConfigRepository.save(sshConfig1);
        return sshConfig1;
    }

    public TagEntity createTag(String name) {
        TagEntity devTag1 = new TagEntity();
        devTag1.setName(name);
        tagRepository.save(devTag1);
        return devTag1;
    }

    public void save(DashboardEntity dashboad) {
        dashboardRepository.save(dashboad);
    }

    public Optional<DashboardEntity> findDashboardById(Long id) {
        return dashboardRepository.findById(id);
    }
}
