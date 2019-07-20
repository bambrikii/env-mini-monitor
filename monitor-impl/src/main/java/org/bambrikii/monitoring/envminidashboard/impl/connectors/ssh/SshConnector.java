package org.bambrikii.monitoring.envminidashboard.impl.connectors.ssh;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelExec;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import lombok.extern.java.Log;
import org.bambrikii.monitoring.envminidashboard.connectors.AbstractConnector;
import org.bambrikii.monitoring.envminidashboard.connectors.ConnectorCommandable;
import org.bambrikii.monitoring.envminidashboard.result.MetricsResult;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Properties;
import java.util.function.Function;

@Log
public class SshConnector extends AbstractConnector<SshConnectionSetting> {
    @Override
    public List<MetricsResult> apply(SshConnectionSetting connectionSetting, ConnectorCommandable... commands) {
        String host = connectionSetting.getHost();
        int port = connectionSetting.getPort();
        String user = connectionSetting.getUsername();
        String password = connectionSetting.getPassword();
        try {
            Properties config = new Properties();
            config.put("StrictHostKeyChecking", "no");
            JSch jsch = new JSch();
            Session session = jsch.getSession(user, host, port);
            session.setPassword(password);
            session.setConfig(config);
            session.connect();
            log.fine("Connected");

            List<MetricsResult> metricsResult = iterate(command -> command.execute((Function<String, String>) cmd -> {
                        Channel channel;
                        try {
                            channel = session.openChannel("exec");
                        } catch (JSchException e) {
                            return e.getMessage();
                        }
                        ((ChannelExec) channel).setCommand(cmd);
                        channel.setInputStream(null);
                        ((ChannelExec) channel).setErrStream(System.err);

                        StringBuilder result = new StringBuilder();
                        try (InputStream in = channel.getInputStream()) {
                            channel.connect();
                            byte[] tmp = new byte[1024];
                            while (true) {
                                while (in.available() > 0) {
                                    int i = in.read(tmp, 0, 1024);
                                    if (i < 0) break;
                                    result.append(new String(tmp, 0, i));
                                    log.fine(new String(tmp, 0, i));
                                }
                                if (channel.isClosed()) {
                                    log.fine("exit-status: " + channel.getExitStatus());
                                    break;
                                }
                                try {
                                    Thread.sleep(1000);
                                } catch (Exception ex) {
                                }
                            }
                            channel.disconnect();
                        } catch (IOException | JSchException ex) {
                            // TODO:
                            ex.printStackTrace();
                        }
                        return result.toString();
                    }),
                    commands
            );

            session.disconnect();
            log.fine("DONE");
            return metricsResult;
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }
}
