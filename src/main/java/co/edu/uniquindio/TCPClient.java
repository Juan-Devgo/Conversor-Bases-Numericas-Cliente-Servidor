package co.edu.uniquindio;

import lombok.extern.java.Log;

import javax.swing.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Arrays;

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
                    clientSideSocket.getOutputStream(), true //-> Autoflush: Vaciar el buffer cuando se envíe el mensaje
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

            String number = "";
            boolean flag_number = true;
            while(flag_number) {

                number = JOptionPane.showInputDialog("Ingrese el número " + originalBase + " que va a convertir a "
                    + finalBase + ".").toUpperCase();;
                if(isNumberValid(number, originalBase)) {
                    flag_number = false;
                } else {
                    JOptionPane.showMessageDialog( null,"Ingrese correctamente el numero " +
                            originalBase +".\nCaracteres permitidos: binario[0-1], decimal[0-9], hexadecimal[0-9, A-F].",
                            "Error: numero inválido", JOptionPane.ERROR_MESSAGE);
                }
            }

            String size = "0";

            if(mode.equals("1") || mode.equals("2") || mode.equals("4") ) {

                boolean flag_size = true;
                while (flag_size) {
                    size = JOptionPane.showInputDialog("Ingrese el tamaño del número que quiere como respuesta. Mínimo 1, máximo 16.");
                    if (isSizeValid(size)) {
                        flag_size = false;
                    } else {
                        JOptionPane.showMessageDialog(null, "Ingrese correctamente el tamaño " +
                                        "del numero de respuesta.\nDebe ser un numero entero en el rango.",
                                "Error: tamaño inválido", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }

            String parameters = mode + ";" + number + ";" + size + "\n";

            log.info("Mensaje enviado: " + parameters);

            toNetwork.println(parameters); //-> Desbloquea el servidor

            String message = fromNetwork.readLine();

            log.info("Respuesta recibida: " + message);

            JOptionPane.showMessageDialog(null, "El número convertido es: " +
                    Arrays.toString(message.split("\n")));

        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "El servidor no está activo. Por favor, " +
                    "inícielo y vuelva a ejecutar el cliente.", "Error de conexión", JOptionPane.ERROR_MESSAGE);
            log.severe(e.getMessage());
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

    private static boolean isNumberValid(String numberStr, String base) {

        boolean numValid = false;

        switch (base) {
            case "binario":
                numValid = numberStr.matches("[0-1]+");
                break;
            case "decimal":
                numValid = numberStr.matches("[0-9]+");
                break;
            case "hexadecimal":
                numValid = numberStr.matches("[0-9A-F]+");
                break;
            default:
                log.warning("La base original no coincide con alguna opción.");
                break;
        }

        return numValid;
    }

    private static boolean isSizeValid(String sizeStr) {
        boolean sizeValid = false;
        try {
            int size = Integer.parseInt(sizeStr);
            sizeValid = size <= 16 &&  size > 0;
        } catch (NumberFormatException e) {
            log.warning("El tamaño fue ingresado incorrectamente.");
        }

        return sizeValid;
    }
}