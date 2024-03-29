package org.bambrikii.monitoring.envminidashboard.web.services;

import org.bambrikii.monitoring.envminidashboard.impl.connectors.http.HttpConnConfig;
import org.bambrikii.monitoring.envminidashboard.impl.connectors.ssh.SshConnConfig;
import org.bambrikii.monitoring.envminidashboard.impl.connectors.windows.WinConnConfig;
import org.bambrikii.monitoring.envminidashboard.model.ConnConfig;
import org.bambrikii.monitoring.envminidashboard.model.MetricLog;
import org.bambrikii.monitoring.envminidashboard.model.PhysicalConn;
import org.bambrikii.monitoring.envminidashboard.model.Tag;
import org.bambrikii.monitoring.envminidashboard.orm.model.ConnConfigEntity;
import org.bambrikii.monitoring.envminidashboard.orm.model.HttpConnConfigEntity;
import org.bambrikii.monitoring.envminidashboard.orm.model.HttpConnConfigRepository;
import org.bambrikii.monitoring.envminidashboard.orm.model.MetricLogEntity;
import org.bambrikii.monitoring.envminidashboard.orm.model.MetricLogRepository;
import org.bambrikii.monitoring.envminidashboard.orm.model.PhysicalConnEntity;
import org.bambrikii.monitoring.envminidashboard.orm.model.PhysicalConnRepository;
import org.bambrikii.monitoring.envminidashboard.orm.model.SshConnConfigEntity;
import org.bambrikii.monitoring.envminidashboard.orm.model.SshConnConfigRepository;
import org.bambrikii.monitoring.envminidashboard.orm.model.TagEntity;
import org.bambrikii.monitoring.envminidashboard.orm.model.TagRepository;
import org.bambrikii.monitoring.envminidashboard.orm.model.WinConnConfigEntity;
import org.bambrikii.monitoring.envminidashboard.orm.model.WinConnConfigRepository;
import org.bambrikii.monitoring.envminidashboard.web.converters.PhysicalConnConverter;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.function.Function;

@Service
public class PhysicalConnService {
    private PhysicalConnRepository physicalConnRepository;
    private SshConnConfigRepository sshConnConfigRepository;
    private WinConnConfigRepository winConnConfigRepository;
    private HttpConnConfigRepository httpConnConfigRepository;

    private TagRepository tagRepository;
    private MetricLogRepository metricLogRepository;
    private Function<ConnConfig, ConnConfigEntity> configTypeMapper;
    private Function<Tag, TagEntity> tagMapper;
    private Function<MetricLog, MetricLogEntity> metricLogMapper;

    public PhysicalConnService(
            PhysicalConnRepository physicalConnRepository,
            SshConnConfigRepository sshConnConfigRepository,
            WinConnConfigRepository winConnConfigRepository,
            HttpConnConfigRepository httpConnConfigRepository,
            TagRepository tagRepository, MetricLogRepository metricLogRepository) {
        this.physicalConnRepository = physicalConnRepository;
        this.sshConnConfigRepository = sshConnConfigRepository;
        this.winConnConfigRepository = winConnConfigRepository;
        this.httpConnConfigRepository = httpConnConfigRepository;
        this.tagRepository = tagRepository;
        this.metricLogRepository = metricLogRepository;
        configTypeMapper = connConfig -> {
            if (connConfig instanceof SshConnConfig) {
                return sshConnConfigRepository.findById(connConfig.getId()).get();
            }
            if (connConfig instanceof WinConnConfig) {
                return winConnConfigRepository.findById(connConfig.getId()).get();
            }
            if (connConfig instanceof HttpConnConfig) {
                return httpConnConfigRepository.findById(connConfig.getId()).get();
            }
            throw new IllegalArgumentException("Cannot find config type mapper");
        };
        tagMapper = tag -> tagRepository.findById(tag.getId()).get();
        metricLogMapper = metricLog -> metricLogRepository.findById(metricLog.getId()).get();
    }

    public PhysicalConn update(Long id, PhysicalConn pojo) {
        Optional<PhysicalConnEntity> entityOptional = id == null
                ? Optional.empty()
                : physicalConnRepository.findById(id);
        PhysicalConnEntity entity = entityOptional.isPresent()
                ? entityOptional.get()
                : new PhysicalConnEntity();

        PhysicalConnConverter.convert(pojo, entity, configTypeMapper, tagMapper, metricLogMapper);

        physicalConnRepository.save(entity);
        return PhysicalConnConverter.convert(entity);
    }

    public PhysicalConn read(Long id) {
        return PhysicalConnConverter.convert(physicalConnRepository.findById(id).get());
    }

    public void delete(Long id) {
        physicalConnRepository.deleteById(id);
    }

    public void addSsh(Long id, Long confId) {
        PhysicalConnEntity conn = physicalConnRepository.findById(id).get();
        SshConnConfigEntity conf = sshConnConfigRepository.findById(confId).get();
        conn.setConfig(conf);
        physicalConnRepository.save(conn);
    }

    public void deleteSsh(Long id, Long confId) {
        PhysicalConnEntity conn = physicalConnRepository.findById(id).get();
        SshConnConfigEntity conf = sshConnConfigRepository.findById(confId).get();
        conn.setConfig(null);
        physicalConnRepository.save(conn);
    }

    public void addWin(Long id, Long confId) {
        PhysicalConnEntity conn = physicalConnRepository.findById(id).get();
        WinConnConfigEntity conf = winConnConfigRepository.findById(confId).get();
        conn.setConfig(conf);
        physicalConnRepository.save(conn);
    }

    public void deleteWin(Long id, Long confId) {
        PhysicalConnEntity conn = physicalConnRepository.findById(id).get();
        WinConnConfigEntity conf = winConnConfigRepository.findById(confId).get();
        conn.setConfig(null);
        physicalConnRepository.save(conn);
    }

    public void addHttp(Long id, Long confId) {
        PhysicalConnEntity conn = physicalConnRepository.findById(id).get();
        HttpConnConfigEntity conf = httpConnConfigRepository.findById(confId).get();
        conn.setConfig(conf);
        physicalConnRepository.save(conn);
    }

    public void deleteHttp(Long id, Long confId) {
        PhysicalConnEntity conn = physicalConnRepository.findById(id).get();
        HttpConnConfigEntity conf = httpConnConfigRepository.findById(confId).get();
        conn.setConfig(null);
        physicalConnRepository.save(conn);
    }
}
