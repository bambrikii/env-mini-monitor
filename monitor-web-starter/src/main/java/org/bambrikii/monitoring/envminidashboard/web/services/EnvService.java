package org.bambrikii.monitoring.envminidashboard.web.services;

import org.bambrikii.monitoring.envminidashboard.model.Env;
import org.bambrikii.monitoring.envminidashboard.orm.model.EnvEntity;
import org.bambrikii.monitoring.envminidashboard.orm.model.EnvRepository;
import org.bambrikii.monitoring.envminidashboard.web.converters.EnvConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class EnvService {
    @Autowired
    private EnvRepository envRepository;

    public Env update(Long id, Env env) {
        Optional<EnvEntity> entityOptional = id == null
                ? Optional.empty()
                : envRepository.findById(id);
        EnvEntity entity = entityOptional.isPresent()
                ? entityOptional.get()
                : new EnvEntity();
        EnvConverter.convert(env, entity);
        envRepository.save(entity);
        return EnvConverter.convert(entity);
    }

    public Env read(Long id) {
        return EnvConverter.convert(envRepository.findById(id).get());
    }
}
