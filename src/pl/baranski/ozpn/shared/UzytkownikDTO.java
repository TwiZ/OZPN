/**
 * 
 */
package pl.baranski.ozpn.shared;

import com.google.gwt.user.client.rpc.IsSerializable;

/**
 * @author <a href="mailto:bartosz.baranski@o2.pl">Bartosz "TwiZ" Barański</a>
 * 
 */
public class UzytkownikDTO implements IsSerializable {
    private String login;
    private String haslo;
    private String typUzytkownika;
    private int status;
    private OsobaDTO osoba;

    /**
     * Ustawia wartość pola <code>status</code>.
     * 
     * @param status
     *            the status to set
     */
    public void setStatus(int status) {
        this.status = status;
    }

    /**
     * Pobiera wartość pola <code>status</code>.
     * 
     * @return the status
     */
    public int getStatus() {
        return status;
    }

    /**
     * Pobiera wartość pola <code>login</code>.
     * 
     * @return the login
     */
    public String getLogin() {
        return login;
    }

    /**
     * Ustawia wartość pola <code>login</code>.
     * 
     * @param login
     *            the login to set
     */
    public void setLogin(String login) {
        this.login = login;
    }

    /**
     * Pobiera wartość pola <code>haslo</code>.
     * 
     * @return the haslo
     */
    public String getHaslo() {
        return haslo;
    }

    /**
     * Ustawia wartość pola <code>haslo</code>.
     * 
     * @param haslo
     *            the haslo to set
     */
    public void setHaslo(String haslo) {
        this.haslo = haslo;
    }

    /**
     * Ustawia wartość pola <code>osoba</code>.
     * 
     * @param osoba
     *            the osoba to set
     */
    public void setOsoba(OsobaDTO osoba) {
        this.osoba = osoba;
    }

    /**
     * Pobiera wartość pola <code>osoba</code>.
     * 
     * @return the osoba
     */
    public OsobaDTO getOsoba() {
        return osoba;
    }

    /**
     * Ustawia wartość pola <code>typUzytkownika</code>.
     * 
     * @param typUzytkownika
     *            the typUzytkownika to set
     */
    public void setTypUzytkownika(String typUzytkownika) {
        this.typUzytkownika = typUzytkownika;
    }

    /**
     * Pobiera wartość pola <code>typUzytkownika</code>.
     * 
     * @return the typUzytkownika
     */
    public String getTypUzytkownika() {
        return typUzytkownika;
    }
}
