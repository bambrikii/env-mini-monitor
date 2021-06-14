package org.bambrikii.monitoring.envminidashboard.web.services;

import org.bambrikii.monitoring.envminidashboard.impl.connectors.ssh.SshConnConfig;
import org.bambrikii.monitoring.envminidashboard.orm.model.SshConnConfigEntity;
import org.bambrikii.monitoring.envminidashboard.orm.model.SshConnConfigRepository;
import org.bambrikii.monitoring.envminidashboard.web.converters.SshConnConfigConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SshConnConfigService {
    @Autowired
    private SshConnConfigRepository sshConnConfigRepository;

    public SshConnConfig read(Long id) {
        return SshConnConfigConverter.convert(sshConnConfigRepository.findById(id).get());
    }

    public SshConnConfig update(Long id, SshConnConfig pojo) {
        Optional<SshConnConfigEntity> optionalEntity = id == null
                ? Optional.empty()
                : sshConnConfigRepository.findById(id);
        SshConnConfigEntity entity = optionalEntity.isPresent()
                ? optionalEntity.get()
                : new SshConnConfigEntity();
        SshConnConfigConverter.convert(pojo, entity);
        sshConnConfigRepository.save(entity);
        return SshConnConfigConverter.convert(entity);
    }
}
