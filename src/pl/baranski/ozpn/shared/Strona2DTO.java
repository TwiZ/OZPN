package pl.baranski.ozpn.shared;

import java.io.Serializable;

public class Strona2DTO implements Serializable {

    private static final long serialVersionUID = 3516510235971135213L;
    String[][] dane;
    Integer liczbaWierszy;

    public String[][] getDane() {
        return dane;
    }

    public void setDane(String[][] dane) {
        this.dane = dane;
    }

    public Integer getLiczbaWierszy() {
        return liczbaWierszy;
    }

    public void setLiczbaWierszy(Integer liczbaWierszy) {
        this.liczbaWierszy = liczbaWierszy;
    }

    public void wyswietl() {
        for (String d[] : this.getDane()) {
            for (String dana : d) {
                System.out.print(dana);
            }
        }

    }
}
