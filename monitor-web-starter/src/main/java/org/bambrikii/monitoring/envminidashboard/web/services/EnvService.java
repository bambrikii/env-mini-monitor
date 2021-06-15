package org.bambrikii.monitoring.envminidashboard.web.services;

import org.bambrikii.monitoring.envminidashboard.model.Env;
import org.bambrikii.monitoring.envminidashboard.orm.model.EnvEntity;
import org.bambrikii.monitoring.envminidashboard.orm.model.EnvRepository;
import org.bambrikii.monitoring.envminidashboard.orm.model.PhysicalConnEntity;
import org.bambrikii.monitoring.envminidashboard.orm.model.PhysicalConnRepository;
import org.bambrikii.monitoring.envminidashboard.web.converters.EnvConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class EnvService {
    @Autowired
    private EnvRepository envRepository;
    @Autowired
    private PhysicalConnRepository physicalConnRepository;

    public Env update(Env env) {
        Long id = env.getId();
        Optional<EnvEntity> entityOptional = id == null
                ? Optional.empty()
                : envRepository.findById(id);
        EnvEntity entity = entityOptional.isPresent()
                ? entityOptional.get()
                : new EnvEntity();
        EnvConverter.convert(env, entity, conn -> physicalConnRepository.findById(conn.getId()).get());
        envRepository.save(entity);
        return EnvConverter.convert(entity);
    }

    public Env read(Long id) {
        return EnvConverter.convert(envRepository.findById(id).get());
    }

    public void delete(Long id) {
        envRepository.deleteById(id);
    }

    public void addConn(Long id, Long connId) {
        EnvEntity env = envRepository.findById(id).get();
        PhysicalConnEntity conn = physicalConnRepository.findById(connId).get();

        env.getConnections().add(conn);
        envRepository.save(env);
    }

    public void deleteConn(Long id, Long connId) {
        EnvEntity env = envRepository.findById(id).get();
        PhysicalConnEntity conn = physicalConnRepository.findById(connId).get();

        env.getConnections().remove(conn);
        envRepository.save(env);
    }
}
