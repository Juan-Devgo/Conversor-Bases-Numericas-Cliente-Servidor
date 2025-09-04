package co.edu.uniquindio;

public interface NumberBasesConvertorUseCase {

    String decimalToBinary(int decimal, int size);

    String decimalToHexadecimal(int decimal, int size);

    String binaryToDecimal(String binary, int size);

    String binaryToHexadecimal(String binary, int size);

    String hexadecimalToDecimal(String hex, int size);

    String hexadecimalToBinary(String hex, int size);
}