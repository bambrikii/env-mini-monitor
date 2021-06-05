package org.bambrikii.monitoring.envminidashboard.impl.metrics;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FileSystemMetricsValue {
    private String path;
    private Long totalSpace;
    private Long freeSpace;
    private Long usableSpace;

    public FileSystemMetricsValue(String path, Long totalSpace, Long freeSpace, Long usableSpace) {
        this.path = path;
        this.totalSpace = totalSpace;
        this.freeSpace = freeSpace;
        this.usableSpace = usableSpace;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public Long getTotalSpace() {
        return totalSpace;
    }

    public void setTotalSpace(Long totalSpace) {
        this.totalSpace = totalSpace;
    }

    public Long getFreeSpace() {
        return freeSpace;
    }

    public void setFreeSpace(Long freeSpace) {
        this.freeSpace = freeSpace;
    }

    public Long getUsableSpace() {
        return usableSpace;
    }

    public void setUsableSpace(Long usableSpace) {
        this.usableSpace = usableSpace;
    }

    @Override
    public String toString() {
        return "FileSystemMetricsValue{" +
                "path='" + path + '\'' +
                ", totalSpace=" + totalSpace +
                ", freeSpace=" + freeSpace +
                ", usableSpace=" + usableSpace +
                '}';
    }
}
