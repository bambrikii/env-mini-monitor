package org.bambrikii.monitoring.envminidashboard.connectors;

import lombok.extern.java.Log;
import org.bambrikii.monitoring.envminidashboard.model.ConnectionSetting;
import org.bambrikii.monitoring.envminidashboard.result.MetricsResult;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Log
public abstract class AbstractConnector<C extends ConnectionSetting> {
    public interface ConnectorPerformer {
        List<MetricsResult> perform(ConnectorCommandable command);
    }

    protected List<MetricsResult> iterate(ConnectorPerformer performer, ConnectorCommandable... commands) {
        if (commands == null || commands.length == 0) {
            log.warning("No commands in connector " + this.getClass().getName() + " for performer " + performer.getClass().getName() + "!");
        }
        return Arrays.asList(commands)
                .stream()
                .flatMap(command -> performer.perform(command).stream())
                .collect(Collectors.toList());
    }

    public abstract List<MetricsResult> apply(C connectionSetting, ConnectorCommandable... commands);
}
