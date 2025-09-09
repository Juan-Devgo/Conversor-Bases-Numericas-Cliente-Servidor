package co.edu.uniquindio;

public class NumberBasesConvertorService implements NumberBasesConvertorUseCase{

    private static NumberBasesConvertorService instance = new NumberBasesConvertorService();

    private NumberBasesConvertorService() {}

    public static NumberBasesConvertorService getInstance() {
        if(instance == null) {
            instance = new NumberBasesConvertorService();
        }
        return instance;
    }

    @Override
    public String decimalToBinary(String decimal, int size) {
//        String binario = "";
//        int residuo = 0;
//        for (int i = 0; i < size; i++){
//            residuo = decimal%2;
//            if (residuo==0) {
//                binario = binario + "0";
//            } else {
//                binario = binario + "1";
//            }
//        }
//        return binario;

        String binario = "";
        int auxDecimal = Integer.parseInt(decimal, 10);
        binario = Integer.toBinaryString(auxDecimal);
        while(binario.length() < size){
            binario = ("0" + binario);
        }

        return binario;
    }
    /*
    * Un ojo de plata llora estrellas
    * Sobre los cuerpos sublimes
    */
    @Override
    public String decimalToHexadecimal(String decimal, int size) {
        String hex = "";
        int auxDecimal = Integer.parseInt(decimal, 10);
        hex = Integer.toHexString(auxDecimal);
        while(hex.length() < size){
            hex = ("0" + hex);
        }

        return hex;
    }

    @Override
    public String binaryToDecimal(String binary) {
        String decimal = "";
        decimal += Integer.parseInt(binary, 2);
        return decimal;

    }

    @Override
    public String binaryToHexadecimal(String binary, int size) {
        String hex = "";

        int auxDecimal = Integer.parseInt(binary, 2);
        hex += Integer.toHexString(auxDecimal);

        while(hex.length() < size){
            hex = ("0" + hex);
        }
        return hex;
    }

    @Override
    public String hexadecimalToDecimal(String hexadecimal) {
        String decimal = "";
        decimal += Integer.parseInt(hexadecimal, 16);
        return decimal;
    }

    @Override
    public String hexadecimalToBinary(String hexadecimal) {
        String binario = "";
        int auxDecimal = Integer.parseInt(hexadecimal, 16);
        binario += Integer.toBinaryString(auxDecimal);
        return binario;
    }
}