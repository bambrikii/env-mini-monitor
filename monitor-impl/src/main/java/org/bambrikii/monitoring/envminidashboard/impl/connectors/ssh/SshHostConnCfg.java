package org.bambrikii.monitoring.envminidashboard.impl.connectors.ssh;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.bambrikii.monitoring.envminidashboard.model.HostConnConfig;

import java.util.Objects;

@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
public class SshHostConnCfg extends HostConnConfig {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        SshHostConnCfg that = (SshHostConnCfg) o;
        return Objects.equals(username, that.username) && Objects.equals(password, that.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), username, password);
    }
}
