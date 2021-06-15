package org.bambrikii.monitoring.envminidashboard.orm.model;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("http")
public class HttpConnConfigEntity extends ConnConfigEntity {
}
