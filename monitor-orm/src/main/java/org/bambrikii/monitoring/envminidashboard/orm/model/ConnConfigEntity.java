package org.bambrikii.monitoring.envminidashboard.orm.model;

import lombok.ToString;
import org.bambrikii.monitoring.envminidashboard.model.api.ConnConfiggable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;
import java.util.ArrayList;
import java.util.List;

@ToString
@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class ConnConfigEntity implements ConnConfiggable {
    @Id
    @GeneratedValue(generator = "CONN_SETTINGS_SEQ", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "CONN_SETTINGS_SEQ", sequenceName = "CONN_SETTINGS_SEQ")
    private Long id;

    @Column(nullable = false)
    private String host;

    @Column(nullable = false)
    private Integer port = 22;

    @ManyToMany
    private List<TagEntity> tags = new ArrayList<>();

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

    public List<TagEntity> getTags() {
        return tags;
    }

    public void setTags(List<TagEntity> tags) {
        this.tags = tags;
    }
}
