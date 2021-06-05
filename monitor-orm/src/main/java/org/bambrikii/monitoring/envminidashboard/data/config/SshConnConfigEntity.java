package org.bambrikii.monitoring.envminidashboard.data.config;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Table;

@Getter
@Setter
@Entity
@Table
public class SshConnConfigEntity extends ConnConfigEntity {
    private String username;
    private String password;
}
