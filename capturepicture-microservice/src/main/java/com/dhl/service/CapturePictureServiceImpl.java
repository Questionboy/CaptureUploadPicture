package com.dhl.service;

import ch.ethz.ssh2.Connection;
import ch.ethz.ssh2.Session;
import ch.ethz.ssh2.StreamGobbler;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * CapturePictureServiceImpl class
 *
 * @author daihongliang
 * @date 2017/10/17
 */
@Service
public class CapturePictureServiceImpl implements CapturePictureService{
    @Override
    public Connection getOpenedConnection(String host, String username, String password) throws IOException {
        Connection conn = new Connection(host);
        conn.connect(); // make sure the connection is opened
        boolean isAuthenticated = conn.authenticateWithPassword(username, password);
        if (isAuthenticated == false) {
            throw new IOException("Authentication failed.");
        }
        return conn;
    }

    @Override
    public String execShellScript(String host, String username, String password, String cmd) throws IOException {
        Connection conn = null;
        Session sess = null;
        InputStream stdout = null;
        BufferedReader br = null;
        StringBuffer buffer = new StringBuffer();
        try {
            conn = getOpenedConnection(host, username, password);
            sess = conn.openSession();
            sess.execCommand(cmd);
            stdout = new StreamGobbler(sess.getStdout());
            br = new BufferedReader(new InputStreamReader(stdout));
            while (true) {
                // attention: do not comment this block, or you will hit
                // NullPointerException
                // when you are trying to read exit status
                String line = br.readLine();
                if (line == null) {
                    break;
                }
                buffer.append(line);
            }
        } finally {
            sess.close();
            conn.close();
        }
        return buffer.toString();
    }
}
