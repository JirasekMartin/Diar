import java.time.LocalDateTime;
import java.util.ArrayList;
public class Databaze {
    /**
     * Záznamy
     */
    private ArrayList<Zaznam> zaznamy;

    /**
     * Konstruktor
     */
    public Databaze() {
        zaznamy = new ArrayList<>();
    }

    /**
     * Přidá záznam do databáze
     *
     * @param datumCas Datum a čas záznamu
     * @param text Text záznamu
     */
    public void pridejZaznam(LocalDateTime datumCas, String text) {
        zaznamy.add(new Zaznam(datumCas, text));
    }

    /**
     * Najde záznamy dle datumu a času
     *
     * @param datumCas Datum a čas hledaných záznamů
     * @param dleCasu True = bude hledat s ohledem na čas, False = bez ohledu na čas
     * @return Kolekce nalezených záznamů
     */
    public ArrayList<Zaznam> najdiZaznamy(LocalDateTime datumCas, boolean dleCasu) {
        ArrayList<Zaznam> nalezene = new ArrayList<>();
        for (Zaznam zaznam : zaznamy) {
            if ((dleCasu && zaznam.getDatumCas().equals(datumCas))
                    || (!dleCasu && zaznam.getDatumCas().toLocalDate().equals(datumCas.toLocalDate()))) {
                nalezene.add(zaznam);
            }
        }
        return nalezene;
    }

    /**
     * Odstraní záznamy v určitém datu a času
     *
     * @param datum Datum a čas záznamů
     */
    public void vymazZaznamy(LocalDateTime datum) {
        ArrayList<Zaznam> nalezeno = najdiZaznamy(datum, true);
        for (Zaznam zaznam : nalezeno) {
            zaznamy.remove(zaznam);
        }
    }
}
