package io;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class EchoServer {

    private static final Logger LOG = LogManager.getLogger(EchoServer.class.getName());

    public static void main(String[] args) throws IOException {
        try (ServerSocket server = new ServerSocket(9000)) {
            boolean byeAction = false;
            while (!byeAction) {
                Socket socket = server.accept();
                try (OutputStream out = socket.getOutputStream();
                     BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {
                    String str;
                    while (!(str = in.readLine()).isEmpty()) {
                        LOG.debug(str);
                        if (str.contains("msg=Exit")) {
                            out.write("Goodbye!".getBytes());
                            byeAction = true;
                            break;
                        } else if (str.contains("msg=Hello")) {
                            out.write("Hello, dear friend!".getBytes());
                        } else if (str.contains("msg=")) {
                            out.write(str.split("=")[1].replaceFirst(" HTTP/1.1", "").getBytes());
                        }
                    }
                }
            }
        }
    }

}
