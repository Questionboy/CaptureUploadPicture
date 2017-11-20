package com.dhl.service;

import ch.ethz.ssh2.Connection;

import java.io.IOException;

/**
 * Created by daihl on 2017/10/16.
 */
public interface CapturePictureService {
    public Connection getOpenedConnection(String host, String username, String password) throws IOException;

    public String execShellScript(String host, String username, String password, String cmd) throws IOException;
}
