package co.edu.uniquindio;

public interface NumberBasesConvertorUseCase {

    String decimalToBinary(String decimal, int size);

    String decimalToHexadecimal(String decimal, int size);

    String binaryToDecimal(String binary, int size);

    String binaryToHexadecimal(String binary, int size);

    String hexadecimalToDecimal(String hex, int size);

    String hexadecimalToBinary(String hex, int size);
}