package org.bambrikii.monitoring.envminidashboard.web.services;

import org.bambrikii.monitoring.envminidashboard.impl.connectors.http.HttpConnConfig;
import org.bambrikii.monitoring.envminidashboard.model.ConnConfig;
import org.bambrikii.monitoring.envminidashboard.orm.model.HttpConnConfigEntity;
import org.bambrikii.monitoring.envminidashboard.orm.model.HttpConnConfigRepository;
import org.bambrikii.monitoring.envminidashboard.web.converters.HttpConnConfigConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class HttpConnConfigService {
    @Autowired
    private HttpConnConfigRepository httpConnConfigRepository;

    public HttpConnConfig save(Long id, ConnConfig pojo) {
        Optional<HttpConnConfigEntity> optionalEntity = id == null
                ? Optional.empty()
                : httpConnConfigRepository.findById(id);
        HttpConnConfigEntity entity = optionalEntity.isPresent()
                ? optionalEntity.get()
                : new HttpConnConfigEntity();
        HttpConnConfigConverter.convert(pojo, entity);
        httpConnConfigRepository.save(entity);
        return HttpConnConfigConverter.convert(entity);
    }
}
