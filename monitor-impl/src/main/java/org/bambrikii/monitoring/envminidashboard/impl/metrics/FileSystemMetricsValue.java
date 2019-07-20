package org.bambrikii.monitoring.envminidashboard.impl.metrics;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class FileSystemMetricsValue {
    private String path;
    private Long totalSpace;
    private Long freeSpace;
    private Long usableSpace;
}
