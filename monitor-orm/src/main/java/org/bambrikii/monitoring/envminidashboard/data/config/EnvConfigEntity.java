package org.bambrikii.monitoring.envminidashboard.data.config;

import lombok.Getter;
import lombok.Setter;
import org.bambrikii.monitoring.envminidashboard.model.Environmentable;

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

@Getter
@Setter
@Entity
@Table
public class EnvConfigEntity implements Environmentable<TagConfigEntity> {
    @Id
    @GeneratedValue(generator = "ENVIRONMENT_SEQ", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "ENVIRONMENT_SEQ", sequenceName = "ENVIRONMENT_SEQ")
    private Long id;
    @Column(nullable = false, unique = true)
    private String code;
    @OneToMany
    private List<TagConfigEntity> tags = new ArrayList<>();
}
