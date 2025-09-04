package co.edu.uniquindio;

import lombok.extern.java.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

@Log
public class TCPServer {

    public static void main(String[] args) {
        boolean flag = true;

        while (flag) {
            try(ServerSocket listener = new ServerSocket(3400)) {

            Socket serverSideSocket = listener.accept(); //-> Bloqueo del servidor

            BufferedReader fromNetwork = new BufferedReader(
                    new InputStreamReader(
                            serverSideSocket.getInputStream()
                    )
            );

            PrintWriter toNetwork = new PrintWriter(
                    serverSideSocket.getOutputStream(), true
            );

            String message = fromNetwork.readLine(); //-> Bloqueo del servidor
            System.out.println(message);
            toNetwork.println(message);//muy bien compa

            } catch (IOException e) {
                flag = false;
                log.severe(e.getMessage());
            }
        }
    }
}