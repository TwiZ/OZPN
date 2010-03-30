/**
 * 
 */
package pl.baranski.ozpn.client.uzytkownicy;

import pl.baranski.ozpn.client.OZPN;
import pl.baranski.ozpn.shared.UzytkownikDTO;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.gwtext.client.core.Ext;
import com.gwtext.client.widgets.MessageBox;
import com.gwtext.client.widgets.Panel;
import com.gwtext.client.widgets.form.FormPanel;
import com.gwtext.client.widgets.form.TextField;

/**
 * @author <a href="mailto:bartosz.baranski@o2.pl">Bartosz "TwiZ" Barański</a>
 * 
 */
public class SzczegolyUzytkownika extends Panel {
    private TextField tfUzytkownik;
    private TextField tfNazwisko;
    private TextField tfImie;
    private TextField tfWzrost;
    private TextField tfWaga;

    /**
     * Konstruktor.
     */
    public SzczegolyUzytkownika() {
        createUI();
    }

    /**
     * 
     */
    private void createUI() {
        setBorder(false);
        setFrame(false);

        tfUzytkownik = new TextField("Użytkownik");
        tfImie = new TextField("Imię");
        tfWzrost = new TextField("Wzrost");
        tfWaga = new TextField("Waga");
        tfNazwisko = new TextField("Nazwisko");

        FormPanel fp = new FormPanel();
        fp.setBorder(false);
        fp.setFrame(false);
        fp.add(tfUzytkownik);
        fp.add(tfNazwisko);
        fp.add(tfImie);
        fp.add(tfWzrost);
        fp.add(tfWaga);

        add(fp);
    }

    public void ustawDane(String login) {
        Ext.getBody().mask("Trwa ładowanie danych.. Prosze Czekać");
        OZPN.getInstance().getOzpnService().pobierzSzczegolyUzytkownika(login, new AsyncCallback<UzytkownikDTO>() {

            public void onSuccess(UzytkownikDTO uzytkownik) {
                tfUzytkownik.setValue(uzytkownik.getLogin());
                tfNazwisko.setValue(uzytkownik.getOsoba().getNazwisko());
                tfImie.setValue(uzytkownik.getOsoba().getImie());
                tfWaga.setValue(uzytkownik.getOsoba().getWaga() + "");
                tfWzrost.setValue(uzytkownik.getOsoba().getWzrost() + "");
                Ext.getBody().unmask();
            }

            public void onFailure(Throwable caught) {
                MessageBox.alert("Niepowodzenie załadowania danych użytkownika.<br>Skontaktuj się z administratorem.");
            }
        });
    }

}
