package org.bambrikii.monitoring.envminidashboard.webapp.converters;

import org.bambrikii.monitoring.envminidashboard.model.Tag;
import org.bambrikii.monitoring.envminidashboard.orm.model.TagEntity;

public class TagConverter {
    public static Tag convert(TagEntity entity) {
        Tag tag = new Tag();
        tag.setName(entity.getName());
        return tag;
    }
}
