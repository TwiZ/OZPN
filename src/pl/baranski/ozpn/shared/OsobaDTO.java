/**
 * 
 */
package pl.baranski.ozpn.shared;

import com.google.gwt.user.client.rpc.IsSerializable;

/**
 * @author <a href="mailto:bartosz.baranski@o2.pl">Bartosz "TwiZ" Barański</a>
 * 
 */
public class OsobaDTO implements IsSerializable {
    private String nazwisko;
    private String imie;
    private int wzrost;
    private int waga;
    private String plec;

    /**
     * Ustawia wartość pola <code>imie</code>.
     * 
     * @param imie
     *            the imie to set
     */
    public void setImie(String imie) {
        this.imie = imie;
    }

    /**
     * Pobiera wartość pola <code>imie</code>.
     * 
     * @return the imie
     */
    public String getImie() {
        return imie;
    }

    /**
     * Ustawia wartość pola <code>nazwisko</code>.
     * 
     * @param nazwisko
     *            the nazwisko to set
     */
    public void setNazwisko(String nazwisko) {
        this.nazwisko = nazwisko;
    }

    /**
     * Pobiera wartość pola <code>nazwisko</code>.
     * 
     * @return the nazwisko
     */
    public String getNazwisko() {
        return nazwisko;
    }

    /**
     * Ustawia wartość pola <code>waga</code>.
     * 
     * @param waga
     *            the waga to set
     */
    public void setWaga(int waga) {
        this.waga = waga;
    }

    /**
     * Pobiera wartość pola <code>waga</code>.
     * 
     * @return the waga
     */
    public int getWaga() {
        return waga;
    }

    /**
     * Ustawia wartość pola <code>wzrost</code>.
     * 
     * @param wzrost
     *            the wzrost to set
     */
    public void setWzrost(int wzrost) {
        this.wzrost = wzrost;
    }

    /**
     * Pobiera wartość pola <code>wzrost</code>.
     * 
     * @return the wzrost
     */
    public int getWzrost() {
        return wzrost;
    }

    /**
     * Ustawia wartość pola <code>plec</code>.
     * 
     * @param plec
     *            the plec to set
     */
    public void setPlec(String plec) {
        this.plec = plec;
    }

    /**
     * Pobiera wartość pola <code>plec</code>.
     * 
     * @return the plec
     */
    public String getPlec() {
        return plec;
    }
}
