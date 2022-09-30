package Interfaces;

/*

 */

public interface Anonymiser {

    String encrypt(String strToEncrypt, String secret);

    String decrypt(String strToDecrypt, String secret);
}
