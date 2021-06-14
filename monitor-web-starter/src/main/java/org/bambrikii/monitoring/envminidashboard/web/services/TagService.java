package org.bambrikii.monitoring.envminidashboard.web.services;

import org.bambrikii.monitoring.envminidashboard.model.Tag;
import org.bambrikii.monitoring.envminidashboard.orm.model.TagEntity;
import org.bambrikii.monitoring.envminidashboard.orm.model.TagRepository;
import org.bambrikii.monitoring.envminidashboard.web.converters.TagConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TagService {
    @Autowired
    private TagRepository tagRepository;

    public Tag read(Long id) {
        return TagConverter.convert(tagRepository.findById(id).get());
    }

    public Tag update(Long id, Tag pojo) {
        Optional<TagEntity> optionalEntity = id == null
                ? Optional.empty()
                : tagRepository.findById(id);
        TagEntity entity = optionalEntity.isPresent()
                ? optionalEntity.get()
                : new TagEntity();
        TagConverter.convert(pojo, entity);
        tagRepository.save(entity);
        return TagConverter.convert(entity);
    }

    public List<Tag> find(String name) {
        return tagRepository.findByName(name)
                .stream()
                .map(tag -> TagConverter.convert(tag))
                .collect(Collectors.toList());
    }

    public void delete(Long id) {
        tagRepository.deleteById(id);
    }
}
