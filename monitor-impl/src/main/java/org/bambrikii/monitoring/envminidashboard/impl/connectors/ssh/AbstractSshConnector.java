package org.bambrikii.monitoring.envminidashboard.impl.connectors.ssh;

import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import org.bambrikii.monitoring.envminidashboard.connectors.AbstractConnector;

import java.util.Properties;
import java.util.logging.Logger;

public abstract class AbstractSshConnector extends AbstractConnector<SshConnConfig> {
    private static Logger log = Logger.getLogger(AbstractSshConnector.class.getName());

    private Session session;

    protected Session getSession() {
        return session;
    }

    @Override
    public void ensureOpen() {
        if (session != null && session.isConnected()) {
            return;
        }
        SshConnConfig config = getConfig();
        String host = config.getHost();
        int port = config.getPort();
        String user = config.getUsername();
        String password = config.getPassword();
        try {
            Properties props = new Properties();
            props.put("StrictHostKeyChecking", "no");
            JSch jsch = new JSch();
            Session session = jsch.getSession(user, host, port);
            session.setPassword(password);
            session.setConfig(props);
            session.connect();
            this.session = session;
            log.fine("Connected");
        } catch (JSchException ex) {
            session = null;
            throw new RuntimeException(ex);
        }
    }

    @Override
    public void close() {
        try {
            session.disconnect();
            session = null;
            log.fine("Disconnected");
        } catch (Exception ex) {
            session = null;
            throw new RuntimeException(ex);
        }
    }
}
