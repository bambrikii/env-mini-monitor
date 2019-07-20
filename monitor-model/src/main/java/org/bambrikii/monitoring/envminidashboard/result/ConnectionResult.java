package org.bambrikii.monitoring.envminidashboard.result;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.bambrikii.monitoring.envminidashboard.model.Tag;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@ToString
public class ConnectionResult {
    private List<Tag> tags = new ArrayList<>();
    private List<MetricsFamilyResult> metrics = new ArrayList<>();
}
