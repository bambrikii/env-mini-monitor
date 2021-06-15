package org.bambrikii.monitoring.envminidashboard.web.converters;

import org.bambrikii.monitoring.envminidashboard.model.Tag;
import org.bambrikii.monitoring.envminidashboard.orm.model.TagEntity;

public class TagConverter {
    public static Tag convert(TagEntity entity) {
        Tag pojo = new Tag();
        convert(entity, pojo);
        return pojo;
    }

    public static TagEntity convert(Tag pojo) {
        TagEntity entity = new TagEntity();
        convert(pojo, entity);
        return entity;
    }

    public static void convert(TagEntity entity, Tag pojo) {
        pojo.setId(entity.getId());
        pojo.setName(entity.getName());
    }

    public static void convert(Tag pojo, TagEntity entity) {
        entity.setName(pojo.getName());
    }
}
