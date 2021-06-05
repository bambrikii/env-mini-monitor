package org.bambrikii.monitoring.envminidashboard.webapp.loader;

import org.springframework.web.bind.annotation.RequestMapping;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@RequestMapping("/monitor")
public @interface DashboardAnnotation {
}
