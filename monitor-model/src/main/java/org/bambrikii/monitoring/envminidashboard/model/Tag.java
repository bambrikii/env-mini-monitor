package org.bambrikii.monitoring.envminidashboard.model;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class Tag implements Taggable {
    private String name;
    private List<MetricsFamilible> metricsFamilies = new ArrayList<>();
    private List<ConnectionSettingable> connectionSettings = new ArrayList<>();
}
