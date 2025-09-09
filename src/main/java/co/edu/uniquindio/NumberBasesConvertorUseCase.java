package co.edu.uniquindio;

public interface NumberBasesConvertorUseCase {

    String decimalToBinary(String decimal, int size);

    String decimalToHexadecimal(String decimal, int size);

    String binaryToDecimal(String binary);

    String binaryToHexadecimal(String binary, int size);

    String hexadecimalToDecimal(String hex);

    String hexadecimalToBinary(String hex);
}