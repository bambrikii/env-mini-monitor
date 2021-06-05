package org.bambrikii.monitoring.envminidashboard.data.config;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table
public class EnvConfigEntity {
    @Id
    @GeneratedValue(generator = "ENVIRONMENT_SEQ", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "ENVIRONMENT_SEQ", sequenceName = "ENVIRONMENT_SEQ")
    private Long id;
    @Column(nullable = false, unique = true)
    private String code;
    @OneToMany
    private List<TagConfigEntity> tags = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public List<TagConfigEntity> getTags() {
        return tags;
    }

    public void setTags(List<TagConfigEntity> tags) {
        this.tags = tags;
    }
}
