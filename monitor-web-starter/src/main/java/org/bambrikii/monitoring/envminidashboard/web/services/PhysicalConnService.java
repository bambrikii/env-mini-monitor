package org.bambrikii.monitoring.envminidashboard.web.services;

import org.bambrikii.monitoring.envminidashboard.model.PhysicalConn;
import org.bambrikii.monitoring.envminidashboard.orm.model.PhysicalConnEntity;
import org.bambrikii.monitoring.envminidashboard.orm.model.PhysicalConnRepository;
import org.bambrikii.monitoring.envminidashboard.web.converters.PhysicalConnConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PhysicalConnService {
    @Autowired
    private PhysicalConnRepository physicalConnRepository;

    public PhysicalConn update(Long id, PhysicalConn pojo) {
        Optional<PhysicalConnEntity> entityOptional = id == null
                ? Optional.empty()
                : physicalConnRepository.findById(id);
        PhysicalConnEntity entity = entityOptional.isPresent()
                ? entityOptional.get()
                : new PhysicalConnEntity();
        PhysicalConnConverter.convert(pojo, entity);
        physicalConnRepository.save(entity);
        return PhysicalConnConverter.convert(entity);
    }

    public PhysicalConn read(Long id) {
        return PhysicalConnConverter.convert(physicalConnRepository.findById(id).get());
    }
}
