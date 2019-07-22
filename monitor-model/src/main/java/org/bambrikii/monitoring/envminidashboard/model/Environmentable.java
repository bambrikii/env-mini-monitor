package org.bambrikii.monitoring.envminidashboard.model;

import java.util.List;

public interface Environmentable<T extends Taggable> {
    String getCode();

    List<T> getTags();
}
