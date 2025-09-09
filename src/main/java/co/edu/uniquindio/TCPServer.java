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

    private static final NumberBasesConvertorUseCase numberBasesConvertorUseCase = NumberBasesConvertorService.getInstance();

    public static void main(String[] args) {
        boolean flag = true;

        while (flag) {
            try (ServerSocket listener = new ServerSocket(3400)) {

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
                log.info("Mensaje recibido: " + message);

                Parameters parameters = processMessage(message);
                String response = convertNumber(parameters.mode(), parameters.number(), parameters.size());
                log.info("Respuesta enviada: " + response);

                toNetwork.println(response + "\n");//muy bien compa

            } catch (IOException e) {
                flag = false;
                log.severe(e.getMessage());
            }
        }
    }

    private static Parameters processMessage(String message) {
        String[] parts = message.split(";");
        String mode = parts[0];
        String number = parts[1];
        int size = Integer.parseInt(parts[2]);
        return new Parameters(mode, number, size);
    }

    private static String convertNumber(String mode, String number, int size){
        switch (mode) {
            case "1":
                return numberBasesConvertorUseCase.decimalToBinary(number, size);
            case "2":
                return numberBasesConvertorUseCase.decimalToHexadecimal(number, size).toUpperCase();
            case "3":
                return numberBasesConvertorUseCase.binaryToDecimal(number);
            case "4":
                return numberBasesConvertorUseCase.binaryToHexadecimal(number, size).toUpperCase();
            case "5":
                return numberBasesConvertorUseCase.hexadecimalToDecimal(number);
            case "6":
                return numberBasesConvertorUseCase.hexadecimalToBinary(number);
            default:
                return "Error: Modo inválido";
        }
    }
}