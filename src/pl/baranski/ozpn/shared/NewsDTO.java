/**
 * 
 */
package pl.baranski.ozpn.shared;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.google.gwt.user.client.rpc.IsSerializable;
import com.gwtext.client.util.Format;

/**
 * @author <a href="mailto:bartosz.baranski@o2.pl">Bartosz "TwiZ" Barański</a>
 * 
 */
public class NewsDTO implements IsSerializable, Serializable {
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    private String tytul;
    private String tresc;
    private UzytkownikDTO uzytkownik;
    private int idNewsa;
    private String data_dodania;
    private int ilosc_komentarzy;
    private List<PostNewsaDTO> listaPostow;

    /**
     * Pobiera wartość pola <code>uzytkownik</code>.
     * 
     * @return the uzytkownik
     */
    public UzytkownikDTO getUzytkownik() {
        return uzytkownik;
    }

    /**
     * Ustawia wartość pola <code>uzytkownik</code>.
     * 
     * @param uzytkownik
     *            the uzytkownik to set
     */
    public void setUzytkownik(UzytkownikDTO uzytkownik) {
        this.uzytkownik = uzytkownik;
    }

    /**
     * Ustawia wartość pola <code>tresc</code>.
     * 
     * @param tresc
     *            the tresc to set
     */
    public void setTresc(String tresc) {
        this.tresc = tresc;
    }

    /**
     * Pobiera wartość pola <code>tresc</code>.
     * 
     * @return the tresc
     */
    public String getTresc() {
        return tresc;
    }

    /**
     * Ustawia wartość pola <code>tytul</code>.
     * 
     * @param tytul
     *            the tytul to set
     */
    public void setTytul(String tytul) {
        this.tytul = tytul;
    }

    /**
     * Pobiera wartość pola <code>tytul</code>.
     * 
     * @return the tytul
     */
    public String getTytul() {
        return tytul;
    }

    /**
     * Ustawia wartość pola <code>idNewsa</code>.
     * 
     * @param idNewsa
     *            the idNewsa to set
     */
    public void setIdNewsa(int idNewsa) {
        this.idNewsa = idNewsa;
    }

    /**
     * Pobiera wartość pola <code>idNewsa</code>.
     * 
     * @return the idNewsa
     */
    public int getIdNewsa() {
        return idNewsa;
    }

    /**
     * Pobiera wartość pola <code>data_dodania</code>.
     * 
     * @return the data_dodania
     */
    public String getData_dodania() {
        return data_dodania;
    }

    /**
     * Ustawia wartość pola <code>data_dodania</code>.
     * 
     * @param dataDodania
     *            the data_dodania to set
     */
    public void setData_dodania(String dataDodania) {
        data_dodania = dataDodania;
    }

    public String[] toArrayString() {
        return new String[] { idNewsa + "", data_dodania, Format.stripTags(tresc),
                (uzytkownik != null ? uzytkownik.getLogin() : "") };
    }

    /**
     * Ustawia wartość pola <code>ilosc_komentarzy</code>.
     * 
     * @param iloscKomentarzy
     *            the ilosc_komentarzy to set
     */
    public void setIlosc_komentarzy(int iloscKomentarzy) {
        ilosc_komentarzy = iloscKomentarzy;
    }

    /**
     * Pobiera wartość pola <code>ilosc_komentarzy</code>.
     * 
     * @return the ilosc_komentarzy
     */
    public int getIlosc_komentarzy() {
        return ilosc_komentarzy;
    }

    /**
     * Pobiera wartość pola <code>listaPostow</code>.
     * 
     * @return the listaPostow
     */
    public List<PostNewsaDTO> getListaPostow() {
        if (listaPostow == null) {
            listaPostow = new ArrayList<PostNewsaDTO>();
        }
        return listaPostow;
    }

    /**
     * Ustawia wartość pola <code>listaPostow</code>.
     * 
     * @param listaPostow
     *            the listaPostow to set
     */
    public void setListaPostow(List<PostNewsaDTO> listaPostow) {
        this.listaPostow = listaPostow;
    }
}
