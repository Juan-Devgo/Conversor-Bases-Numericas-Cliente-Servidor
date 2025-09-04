package co.edu.uniquindio;

import lombok.extern.java.Log;

import javax.swing.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

@Log
public class TCPClient {

    public static void main(String[] args) throws IOException {

        try(Socket clientSideSocket = new Socket("localhost", 3400)) {

            BufferedReader fromNetwork = new BufferedReader(
                    new InputStreamReader(
                            clientSideSocket.getInputStream()
                    )
            );

            PrintWriter toNetwork = new PrintWriter(
                    clientSideSocket.getOutputStream(), true //->Autoflush: Vacear el buffer cuando se envíe el mensaje
            );

            JOptionPane.showMessageDialog(null, "La aplicación convertirá números enteros " +
                    "entre decimales, binarios y hexadecimales. Presione OK para continuar.");

            boolean flag_mode = true; String mode = "";
            while(flag_mode) {

                mode = JOptionPane.showInputDialog("Escoja la opción (Recuerde que son enteros):\n1. " +
                        "Decimal a Binario\n2. Decimal a Hexadecimal\n3. Binario a Decimal\n4. Binario a Hexadecimal\n5. " +
                        "Hexadecimal a Decimal\n6. Hexadecimal a Binario");
                if(isModeValid(mode)) {
                    flag_mode = false;
                } else {
                    JOptionPane.showMessageDialog( null,"Ingrese sólo el número del inicio " +
                            "de cada opción.\nEjemplo: 3 (si quiere convertir de binario a decimal).",
                            "Error: Modo inválido", JOptionPane.ERROR_MESSAGE);
                }
            }

            String originalBase = ""; String finalBase = "";
            switch (mode) {
                case "1":
                    originalBase = "decimal";
                    finalBase = "binario";
                    break;
                case "2":
                    originalBase = "decimal";
                    finalBase = "hexadecimal";
                    break;
                case "3":
                    originalBase = "binario";
                    finalBase = "decimal";
                    break;
                case "4":
                    originalBase = "binario";
                    finalBase = "hexadecimal";
                    break;
                case "5":
                    originalBase = "hexadecimal";
                    finalBase = "decimal";
                    break;
                case "6":
                    originalBase = "hexadecimal";
                    finalBase = "binario";
                    break;
                default:
                    log.severe("Error Inesperado: modo inválido.");
                    throw new RuntimeException("Error Inesperado: modo inválido.");
            }

            String number = JOptionPane.showInputDialog("Ingrese el número " + originalBase + " que va a convertir a "
                    + finalBase + ".").toUpperCase();;

            String size = JOptionPane.showInputDialog("Ingrese el tamaño del número que quiere como respuesta.");

            String parameters = mode + "%" + number + "%" + size + "%";

            toNetwork.println(parameters); //-> Desbloquea el servidor

            String message = fromNetwork.readLine();
            System.out.println(message);
        }
    }

    private static boolean isModeValid(String modeStr) {
        boolean modeValid = false;
        try {
            int mode = Integer.parseInt(modeStr);
            modeValid = mode <= 6 &&  mode > 0;
        } catch (NumberFormatException e) {
            log.warning("El modo fue ingresado incorrectamente.");
        }

        return modeValid;
    }
}