package org.bambrikii.monitoring.envminidashboard.model;

import java.util.List;

public interface Environmentable {
    String getCode();

    List<Taggable> getTags();
}
