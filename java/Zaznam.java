import java.time.LocalDateTime;

public class Zaznam {

    /**
     * Datum a čas
     */
    private LocalDateTime datumCas;
    /**
     * Text záznamu
     */
    private String text;

    /**
     * Konstruktor
     *
     * @param datumCas Datum a čas
     * @param text Text záznamu
     */
    public Zaznam(LocalDateTime datumCas, String text) {
        this.datumCas = datumCas;
        this.text = text;
    }

    /**
     * Vrátí datum a čas
     *
     * @return Datum a čas
     */
    public LocalDateTime getDatumCas() {
        return datumCas;
    }

    /**
     * Nastaví datum a čas
     *
     * @param datumCas Datum a čas
     */
    public void setDatumCas(LocalDateTime datumCas) {
        this.datumCas = datumCas;
    }

    /**
     * Vrátí text záznamu
     *
     * @return Text
     */
    public String getText() {
        return text;
    }

    /**
     * Nastaví text záznamu
     *
     * @param text Text
     */
    public void setText(String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return Diar.FORMAT_DATA.format(datumCas) + " " + text;
    }
}
