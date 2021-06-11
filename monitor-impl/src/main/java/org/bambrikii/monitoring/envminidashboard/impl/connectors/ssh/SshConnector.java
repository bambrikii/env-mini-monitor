package org.bambrikii.monitoring.envminidashboard.impl.connectors.ssh;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelExec;
import com.jcraft.jsch.JSchException;
import org.bambrikii.monitoring.envminidashboard.connectors.api.ConnectorAdapter;
import org.bambrikii.monitoring.envminidashboard.connectors.api.ProbeAction;
import org.bambrikii.monitoring.envminidashboard.connectors.api.ProbeActionPerformer;

import java.io.IOException;
import java.io.InputStream;
import java.util.function.Supplier;
import java.util.logging.Logger;

public class SshConnector extends AbstractSshConnector implements ConnectorAdapter {
    private static Logger log = Logger.getLogger(SshConnector.class.getName());

    @Override
    public String perform(ProbeAction<?, ?> action) {
        ProbeActionPerformer performer = new ProbeActionPerformer<String, String>() {
            @Override
            public String cmd(Supplier<String> perform) {
                Channel channel;
                try {
                    channel = getSession().openChannel("exec");
                } catch (JSchException e) {
                    return e.getMessage();
                }
                ((ChannelExec) channel).setCommand(perform.get());
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
            }
        };
        return (String) action.exec(performer);
    }
}
