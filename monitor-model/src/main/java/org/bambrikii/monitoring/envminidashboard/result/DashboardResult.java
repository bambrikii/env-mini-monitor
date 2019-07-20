package org.bambrikii.monitoring.envminidashboard.result;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class DashboardResult {
    private List<EnvironmentResult> environments = new ArrayList<>();

    public EnvironmentResult addEnvironment(String code) {
        EnvironmentResult environmentResult = new EnvironmentResult();
        environmentResult.setCode(code);
        return environmentResult;
    }
}
