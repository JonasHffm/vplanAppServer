package vplan.server;

import vplan.utils.Data;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.net.InetAddress;
import java.util.Date;

public class Server {

    private ServerSocket server;
    private int port;
    private InetAddress bindAddr;
    private ClientAcception clientAcception;
    static final String newLine = "\r\n";

    public Server(int port) {
        this.port = port;
        this.bindAddr = null;
    }

    public Server(int port, String listenHost) throws UnknownHostException {
        this.port = port;
        this.bindAddr = InetAddress.getByName(listenHost);
    }

    public void boot() {
        try {
            this.server = new ServerSocket(this.port, 0, this.bindAddr);
            System.out.println("Der Server wurde gestartet!");
            System.out.println("Server listens on: " + this.server.getLocalSocketAddress().toString());
            System.out.println("Starte Client Acception");
            System.out.println("Suche nach Clients...");
            System.out.println();
            this.clientAcception = new ClientAcception(this.server);
            this.clientAcception.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void shutdown() {
        if (this.clientAcception != null) {
            this.clientAcception.requestTermination();
        }
        try {
            this.server.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Server shutdown.");
    }

    public void sendVertretungsstunden(Socket _client) throws IOException {

        System.out.println("Sende alle Vertretungsstunden zu Client: " + _client.getInetAddress().toString());

        OutputStream out = new BufferedOutputStream(_client.getOutputStream());

        PrintStream pout = new PrintStream(out);
        BufferedReader in = new BufferedReader(new InputStreamReader(_client.getInputStream()));

        // read first line of request
        String request = in.readLine();
        String response = "";

        // we ignore the rest
        while (true) {
            String ignore = in.readLine();
            if (ignore == null || ignore.length() == 0) break;
        }

        if (!request.startsWith("GET ") ||
                !(request.endsWith(" HTTP/1.0") || request.endsWith(" HTTP/1.1"))) {
            // bad request
            pout.print("HTTP/1.0 400 Bad Request" + newLine + newLine);
        } else {

            for(String stundeTosend : Data.packedToSend) {
                response += stundeTosend + "\n";
            }

            pout.print(
                "HTTP/1.0 200 OK" + newLine +
                "Content-Type: text/plain" + newLine +
                "Date: " + new Date() + newLine + newLine +
                response
            );

        }
        pout.close();
    }

    public void sendNull(Socket _client) {
        PrintWriter writer = null;
        try {
            writer = new PrintWriter(_client.getOutputStream());
            writer.write("\"HTTP/1.0 200 OK\" + newLine + newLine");
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public ServerSocket getServer() {
        return server;
    }
}
