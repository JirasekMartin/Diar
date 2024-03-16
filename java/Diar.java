import java.time.LocalDateTime;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;
public class Diar {
    /**
     * Formát pro datum
     */
    public static final DateTimeFormatter FORMAT_DATA = DateTimeFormatter.ofPattern("d.M.y H:mm");

    /**
     * Formát pro datum a čas
     */
    public static final DateTimeFormatter FORMAT_DATA_BEZ_CASU = DateTimeFormatter.ofPattern("d.M.y");

    /**
     * Databáze
     */
    private Databaze databaze;
    /**
     * Scanner
     */
    private Scanner scanner = new Scanner(System.in, "Windows-1250");

    /**
     * Konstruktor
     */
    public Diar() {
        databaze = new Databaze();
    }

    /**
     * Naparsuje vstup od uživatele jako datum a čas
     *
     * @return Naparsované datum a čas ze vstupu
     */
    private LocalDateTime naparsujDatumCas() {
        System.out.println("Zadejte datum a čas ve tvaru [" + LocalDateTime.now().format(FORMAT_DATA) + "]:");
        while (true) {
            try {
                return LocalDateTime.parse(scanner.nextLine(), FORMAT_DATA);
            } catch (Exception ex) {
                System.out.println("Nesprávně zadáno, zadejte prosím znovu.");
            }
        }
    }

    /**
     * Naparsuje vstup od uživatele jako datum
     *
     * @return Naparsované datum ze vstupu
     */
    private LocalDateTime naparsujDatum() {
        System.out.println("Zadejte datum ve tvaru [" + LocalDate.now().format(FORMAT_DATA_BEZ_CASU) + "]:");
        while (true) {
            try {
                return LocalDate.parse(scanner.nextLine(), FORMAT_DATA_BEZ_CASU).atStartOfDay();
            } catch (Exception ex) {
                System.out.println("Nesprávně zadáno, zadejte prosím znovu.");
            }
        }
    }

    /**
     * Vypíše záznamy z konkrétního dne
     *
     * @param den Den k vypsání
     */
    public void vypisZaznamy(LocalDateTime den) {
        ArrayList<Zaznam> zaznamy = databaze.najdiZaznamy(den, false);
        for (Zaznam zaznam : zaznamy) {
            System.out.println(zaznam);
        }
    }

    /**
     * Přidá záznam do databáze ze vstupu
     */
    public void pridejZaznam() {
        LocalDateTime datumCas = naparsujDatumCas();
        System.out.println("Zadejte text záznamu:");
        String text = scanner.nextLine();
        databaze.pridejZaznam(datumCas, text);
    }

    /**
     * Vyhledá záznamy dle vstupu
     */
    public void vyhledejZaznamy() {
        // Zadání data uživatelem
        LocalDateTime datumCas = naparsujDatum();
        // Vyhledání záznamů
        ArrayList<Zaznam> zaznamy = databaze.najdiZaznamy(datumCas, false);
        // Výpis záznamů
        if (zaznamy.size() > 0) {
            System.out.println("Nalezeny tyto záznamy: ");
            for (Zaznam zaznam : zaznamy) {
                System.out.println(zaznam);
            }
        } else {
            System.out.println("Nebyly nalezeny žádné záznamy.");
        }
    }

    /**
     * Odstraní záznanmy dle data ze vstupu
     */
    public void vymazZaznamy() {
        System.out.println("Budou vymazány záznamy v daný den a hodinu");
        LocalDateTime datumCas = naparsujDatumCas();
        databaze.vymazZaznamy(datumCas);
    }

    /**
     * Vypíše úvodní obrazovku
     */
    public void vypisUvodniObrazovku() {
        System.out.println();
        System.out.println();
        System.out.println("Vítejte v diáři!");
        LocalDateTime dnes = LocalDateTime.now();
        System.out.printf("Dnes je: %s%n", FORMAT_DATA.format(dnes));
        System.out.println();
        // výpis hlavní obrazovky
        System.out.println("Dnes:\n-----");
        vypisZaznamy(dnes);
        System.out.println();
        System.out.println("Zítra:\n------");
        LocalDateTime zitra = LocalDateTime.now().plusDays(1);
        vypisZaznamy(zitra);
        System.out.println();
    }
}