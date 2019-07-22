package org.bambrikii.monitoring.envminidashboard.data.config;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Table;

@Getter
@Setter
@Entity
@Table
public class SshConnectionSettingConfig extends ConnectionSettingConfig {
    private String username;
    private String password;
}