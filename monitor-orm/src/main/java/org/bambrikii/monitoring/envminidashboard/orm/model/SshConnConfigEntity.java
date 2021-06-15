package org.bambrikii.monitoring.envminidashboard.orm.model;

import lombok.ToString;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@ToString(callSuper = true)
@Entity
@DiscriminatorValue("ssh")
public class SshConnConfigEntity extends ConnConfigEntity {
    private String username;
    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
