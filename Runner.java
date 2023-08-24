import java.util.Scanner;
public class Runner {
    public static void main(String[] args) {

        System.out.println("Choose what you would like to do with the file:\n1. Encrypt\n2. Decrypt");
        Scanner scan = new Scanner(System.in);
        int choice = scan.nextInt();

        if (choice == 1) {
            System.out.println("Select key (from 1 to 20)");
            int key = scan.nextInt();
            while (1 > key || key > 20) {
                System.out.println("Please repeat your request. I remind:\nSelect key (from 1 to 20)");
                key = scan.nextInt();
            }
            Encryption.encryption(key);
            System.out.printf("Encryption completed, file named %s", Encryption.pathDestination);
        } else if (choice == 2) {
            System.out.println("If you know the key please write: y or n");
            String passEnter = scan.nextLine();
            String knowKey = scan.nextLine();
            if (knowKey.equals("y")) {
                System.out.println("Enter the key");
                int key = scan.nextInt();
                Decoding.decoding(key);
            } else if (knowKey.equals("n")) {
                Decoding.brutForce();
            }
            else {
                System.out.println("Please repeat your request. I remind:\nIf you know the key please write: y or n");
            }
            System.out.printf("Decoding completed, file named %s", Decoding.pathDestination);
        } else {
            System.out.println("Please repeat your request. I remind:\n1. Encrypt\n2. Decrypt");
        }
    }
}