package org.bambrikii.monitoring.envminidashboard.data.config;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.SequenceGenerator;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class ConnConfigEntity {
    @Id
    @GeneratedValue(generator = "CONN_SETTINGS_SEQ", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "CONN_SETTINGS_SEQ", sequenceName = "CONN_SETTINGS_SEQ")
    private Long id;

    @Column(nullable = false)
    private String host;

    @Column(nullable = false)
    private Integer port = 22;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public Integer getPort() {
        return port;
    }

    public void setPort(Integer port) {
        this.port = port;
    }
}