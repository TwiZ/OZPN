/**
 * 
 */
package pl.baranski.ozpn.shared;

import com.google.gwt.user.client.rpc.IsSerializable;

/**
 * @author <a href="mailto:bartosz.baranski@o2.pl">Bartosz "TwiZ" Barański</a>
 * 
 */
public class PostNewsaDTO implements IsSerializable {
    private int idPostaNewsa;
    private int idNewsa;
    private String autor;
    private String temat;
    private String tresc;
    private String dataDodania;

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
     * Ustawia wartość pola <code>idPostaNewsa</code>.
     * 
     * @param idPostaNewsa
     *            the idPostaNewsa to set
     */
    public void setIdPostaNewsa(int idPostaNewsa) {
        this.idPostaNewsa = idPostaNewsa;
    }

    /**
     * Pobiera wartość pola <code>idPostaNewsa</code>.
     * 
     * @return the idPostaNewsa
     */
    public int getIdPostaNewsa() {
        return idPostaNewsa;
    }

    /**
     * Ustawia wartość pola <code>autor</code>.
     * 
     * @param autor
     *            the autor to set
     */
    public void setAutor(String autor) {
        this.autor = autor;
    }

    /**
     * Pobiera wartość pola <code>autor</code>.
     * 
     * @return the autor
     */
    public String getAutor() {
        return autor;
    }

    /**
     * Ustawia wartość pola <code>dataDodania</code>.
     * 
     * @param dataDodania
     *            the dataDodania to set
     */
    public void setDataDodania(String dataDodania) {
        this.dataDodania = dataDodania;
    }

    /**
     * Pobiera wartość pola <code>dataDodania</code>.
     * 
     * @return the dataDodania
     */
    public String getDataDodania() {
        return dataDodania;
    }

    /**
     * Ustawia wartość pola <code>temat</code>.
     * 
     * @param temat
     *            the temat to set
     */
    public void setTemat(String temat) {
        this.temat = temat;
    }

    /**
     * Pobiera wartość pola <code>temat</code>.
     * 
     * @return the temat
     */
    public String getTemat() {
        return temat;
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

}
