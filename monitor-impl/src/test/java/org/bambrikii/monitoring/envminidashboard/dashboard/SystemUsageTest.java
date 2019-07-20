package org.bambrikii.monitoring.envminidashboard.dashboard;

import org.junit.Test;

import java.lang.management.ManagementFactory;
import java.lang.management.OperatingSystemMXBean;

public class SystemUsageTest {
    private static void printUsage() {
        OperatingSystemMXBean operatingSystemMXBean = ManagementFactory.getOperatingSystemMXBean();
        System.out.println(operatingSystemMXBean.getSystemLoadAverage());
    }

    @Test
    public void shouldGatherSysUsage() {
        printUsage();
    }
}
