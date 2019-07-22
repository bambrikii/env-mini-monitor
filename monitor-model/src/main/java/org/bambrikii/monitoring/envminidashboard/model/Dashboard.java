package org.bambrikii.monitoring.envminidashboard.model;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class Dashboard implements Dashboardable {
    private List<Environmentable> environments = new ArrayList<>();
}
