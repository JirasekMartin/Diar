import java.util.Scanner;

public class Poznamkovac {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in, "UTF-8");
        Diar diar = new Diar();
        String volba = "";
        // hlavní cyklus
        while (!volba.equals("4")) {
            diar.vypisUvodniObrazovku();
            System.out.println();
            System.out.println("Vyberte si akci:");
            System.out.println("1 - Přidat záznam");
            System.out.println("2 - Vyhledat záznamy");
            System.out.println("3 - Vymazat záznam");
            System.out.println("4 - Konec");
            volba = scanner.nextLine();
            System.out.println();
            // reakce na volbu
            switch (volba) {
                case "1":
                    diar.pridejZaznam();
                    break;
                case "2":
                    diar.vyhledejZaznamy();
                    break;
                case "3":
                    diar.vymazZaznamy();
                    break;
                case "4":
                    // vyskočí z cyklu while a tím ukončí program
                    break;
                default:
                    System.out.println("Neplatná volba, stiskněte libovolnou klávesu a opakujte volbu.");
                    break;
            }
        }
    }
}
