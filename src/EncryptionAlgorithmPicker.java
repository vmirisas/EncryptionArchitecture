import java.util.Scanner;

public class EncryptionAlgorithmPicker {
    Scanner scanner = new Scanner(System.in);
    private String encryptionAlgorith = "";

    public String EncryptionAlgorithmPick(){
        boolean quit = false;
        while (!quit){
            System.out.println("Encryption Methods" + "\n"
                             + "1 : AES" + "\n"
                             + "2 : RSA" + "\n"
                             + "3 : DES" + "\n");


            int pick = Integer.parseInt(scanner.nextLine());
            switch (pick){
                case 1: encryptionAlgorith = "AES";
                break;
                default: encryptionAlgorith = "";
            }


        }
        return encryptionAlgorith;
    }
}
