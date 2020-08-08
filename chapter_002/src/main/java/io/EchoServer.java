package io;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class EchoServer {

    private static final Logger LOG = LoggerFactory.getLogger(EchoServer.class.getName());

    public static void main(String[] args) {
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
                            out.write(str.split("=")[2].replaceFirst(" HTTP/1.1", "").getBytes());
                        }
                    }
                } catch (Exception e) {
                    LOG.debug("Ошибка {}", e.toString());
                }
            }
        } catch (Exception e) {
            LOG.debug("Ошибка {}", e.toString());
        }
    }

}
