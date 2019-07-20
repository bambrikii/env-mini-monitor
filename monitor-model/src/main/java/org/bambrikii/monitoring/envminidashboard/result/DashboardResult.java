package org.bambrikii.monitoring.envminidashboard.result;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@ToString
public class DashboardResult {
    private List<EnvironmentResult> environments = new ArrayList<>();

    public EnvironmentResult addEnvironment(String code) {
        EnvironmentResult environmentResult = new EnvironmentResult();
        environmentResult.setCode(code);
        environments.add(environmentResult);
        return environmentResult;
    }
}
