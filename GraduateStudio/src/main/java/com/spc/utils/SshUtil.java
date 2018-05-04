package com.spc.utils;

import ch.ethz.ssh2.Connection;
import ch.ethz.ssh2.Session;
import ch.ethz.ssh2.StreamGobbler;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public final class SshUtil {

    private SshUtil() {
    }

   public static String[] doSshCommand(String hostname, String username, String password, String command) {
        String[] result = null;
        try {
            Connection conn = new Connection(hostname);
            conn.connect();
            boolean isAuthenticated = conn.authenticateWithPassword(username, password);
            if (isAuthenticated == false) {
                throw new IOException("Authentication failed.");
            }
            Session sess = conn.openSession();
            sess.execCommand(command);
            InputStream stdout = new StreamGobbler(sess.getStdout());
            BufferedReader br = new BufferedReader(new InputStreamReader(stdout));
            while (true) {
                String line = br.readLine();
                if (line == null)
                    break;
                result = line.split("  ");
            }
            sess.close();
            conn.close();
        } catch (IOException e) {
            e.printStackTrace(System.err);
            System.exit(2);
        }
        return result;
    }
}
