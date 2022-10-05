import Interfaces.Encryptor;

import java.util.Scanner;

public class TxtEncryption implements Encryptor {

    Scanner scanner = new Scanner(System.in);
    private String secretKey;

    @Override
    public void setSecretKey() {
        System.out.println("Type the 16bit secret key needed for the encryption");
        this.secretKey = scanner.nextLine();
    }

    @Override
    public String encrypt() {
        return null;
    }
}
