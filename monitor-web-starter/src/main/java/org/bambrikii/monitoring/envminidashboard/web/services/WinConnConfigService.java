package org.bambrikii.monitoring.envminidashboard.web.services;

import org.bambrikii.monitoring.envminidashboard.impl.connectors.windows.WinConnConfig;
import org.bambrikii.monitoring.envminidashboard.orm.model.WinConnConfigEntity;
import org.bambrikii.monitoring.envminidashboard.orm.model.WinConnConfigRepository;
import org.bambrikii.monitoring.envminidashboard.web.converters.WinConnConfigConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class WinConnConfigService {
    @Autowired
    private WinConnConfigRepository winConnConfigRepository;

    public WinConnConfig save(Long id, WinConnConfig pojo) {
        Optional<WinConnConfigEntity> optionalEntity = id == null
                ? Optional.empty()
                : winConnConfigRepository.findById(id);
        WinConnConfigEntity entity;
        if (optionalEntity.isPresent()) {
            entity = optionalEntity.get();
        } else {
            entity = new WinConnConfigEntity();
        }
        winConnConfigRepository.save(entity);
        return WinConnConfigConverter.convert(entity);
    }
}
