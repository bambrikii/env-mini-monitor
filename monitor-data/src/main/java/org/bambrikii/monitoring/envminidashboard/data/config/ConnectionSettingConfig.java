package org.bambrikii.monitoring.envminidashboard.data.config;

import lombok.Getter;
import lombok.Setter;
import org.bambrikii.monitoring.envminidashboard.model.ConnectionSettingable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Getter
@Setter
@Entity
@Table
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class ConnectionSettingConfig implements ConnectionSettingable {
    @Id
    @GeneratedValue(generator = "CONN_SETTINGS_CONFIG", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "CONN_SETTINGS_SEQ", sequenceName = "CONN_SETTINGS_SEQ")
    private Long id;

    @Column(nullable = false)
    private String host;

    @Column(nullable = false)
    private Integer port = 22;
}
