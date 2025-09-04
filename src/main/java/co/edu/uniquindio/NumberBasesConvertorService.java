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
    public String decimalToBinary(int decimal, int size) {
        String binario = "";
        int residuo = 0;
        for (int i = 0; i < size; i++){
            residuo = decimal%2;
            if (residuo==0) {
                binario = binario + "0";
            } else {
                binario = binario + "1";
            }
        }
        return binario;
    }
    /*
    * Un ojo de plata llora estrellas
    * Sobre los cuerpos sublimes
    */
    @Override
    public String decimalToHexadecimal(int decimal, int size) {
        return "";
    }

    @Override
    public String binaryToDecimal(String binary, int size) {
        return "";
    }

    @Override
    public String binaryToHexadecimal(String binary, int size) {
        return "";
    }

    @Override
    public String hexadecimalToDecimal(String hexadecimal, int size) {
        return "";
    }

    @Override
    public String hexadecimalToBinary(String hexadecimal, int size) {
        return "";
    }
}