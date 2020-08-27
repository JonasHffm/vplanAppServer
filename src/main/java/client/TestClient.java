package client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class TestClient {

    public static void main(String[] args) throws IOException {


        Socket socket = new Socket("127.0.0.1", 1112);

        System.out.println("Client wurde verbunden");

        while(true) {
            BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            String read = reader.readLine();
                System.out.println(read);
        }

    }

}
