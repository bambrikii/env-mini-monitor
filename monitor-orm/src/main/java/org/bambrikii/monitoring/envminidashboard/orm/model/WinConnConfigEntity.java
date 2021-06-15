package org.bambrikii.monitoring.envminidashboard.orm.model;

import lombok.ToString;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@ToString
@Entity
@DiscriminatorValue("win")
public class WinConnConfigEntity extends ConnConfigEntity {
}
