/**
 * 
 */
package pl.baranski.ozpn.client.uzytkownicy;

import pl.baranski.ozpn.client.OZPN;
import pl.baranski.ozpn.client.helpers.TextHelper;
import pl.baranski.ozpn.client.main.NewsPanel;
import pl.baranski.ozpn.client.widgets.ContentPanel;
import pl.baranski.ozpn.client.widgets.HorizontalPanel;
import pl.baranski.ozpn.client.widgets.InfoBox;
import pl.baranski.ozpn.shared.OsobaDTO;
import pl.baranski.ozpn.shared.UzytkownikDTO;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Widget;
import com.gwtext.client.core.EventObject;
import com.gwtext.client.core.Position;
import com.gwtext.client.data.SimpleStore;
import com.gwtext.client.data.Store;
import com.gwtext.client.widgets.Button;
import com.gwtext.client.widgets.Component;
import com.gwtext.client.widgets.MessageBox;
import com.gwtext.client.widgets.Panel;
import com.gwtext.client.widgets.event.ButtonListenerAdapter;
import com.gwtext.client.widgets.form.ComboBox;
import com.gwtext.client.widgets.form.DateField;
import com.gwtext.client.widgets.form.FieldSet;
import com.gwtext.client.widgets.form.FormPanel;
import com.gwtext.client.widgets.form.TextField;

/**
 * @author <a href="mailto:bartosz.baranski@o2.pl">Bartosz "TwiZ" Barański</a>
 * 
 */
public class RejestracjaUzytkownika extends ContentPanel {
    private TextField tfLogin;
    private TextField tfHaslo;
    private TextField tfPowtorzoneHaslo;
    private TextField tfNazwisko;
    private TextField tfImie;
    private TextField tfUlica;
    private TextField tfNumerDomu;
    private TextField tfNumerLokalu;
    private TextField tfMiasto;
    private TextField tfKodPocztowy;
    private Button bZapisz;
    private FieldSet fsDodatkowe;
    private ComboBox cbPlec;
    private DateField dfDataUrodzenia;

    /**
     * Konstruktor.
     */
    public RejestracjaUzytkownika(String title) {
        super(title);
        setId(TextHelper.REJESTRACJA_ID);

        createUI();
        addListeners();
    }

    private void addListeners() {
        bZapisz.addListener(new ButtonListenerAdapter() {
            @Override
            public void onClick(Button button, EventObject e) {

                if (tfLogin.validate() && tfHaslo.validate() && tfPowtorzoneHaslo.validate()
                        && (tfHaslo.getValueAsString().equals(tfPowtorzoneHaslo.getValueAsString()))) {

                    UzytkownikDTO uzytkownik = new UzytkownikDTO();
                    uzytkownik.setLogin(tfLogin.getValueAsString());
                    uzytkownik.setHaslo(tfHaslo.getValueAsString());
                    uzytkownik.setOsoba(getOsoba());
                    OZPN.getInstance().getOzpnService().rejestrujUzytkownika(uzytkownik, new AsyncCallback<Void>() {

                        public void onSuccess(Void result) {
                            MessageBox
                                    .alert("Rejestracja nowego użytkownika powiodła się.<br>"
                                            + "Będziesz mógł zalogować się do systemu, po autoryzacji przez administratora systemu.");
                        }

                        public void onFailure(Throwable caught) {
                            MessageBox.alert("Rejestracja użytkownika nie powiodła się.<br>" + caught.getMessage());
                        }
                    });
                    OZPN.getInstance().getMainPanel().showPanel(new NewsPanel());
                } else {
                    // MessageBox.alert("Wprowadź poprawne dane.");
                    InfoBox.alert("Błędne dane", "Wprowadź poprawne dane.");
                    // InfoWindow info = new InfoWindow("Wprowadź poprawne dane.");
                    // info.show();
                }

            }
        });
    }

    private void createUI() {

        add(createMainDataPanel());
        add(createAdditionalDataPanel());

        bZapisz = new Button("Zapisz");
        setButtonAlign(Position.CENTER);
        setButtons(new Button[] { bZapisz });
    }

    private FormPanel createMainDataPanel() {
        tfLogin = new TextField("Login");
        tfLogin.setLabelSeparator("<font color=red>*</font>");
        tfLogin.setAllowBlank(false);

        tfHaslo = new TextField("Hasło");
        tfHaslo.setLabelSeparator("<font color=red>*</font>");
        tfHaslo.setAllowBlank(false);
        tfHaslo.setPassword(true);

        tfPowtorzoneHaslo = new TextField("Powtórzone hasło");
        tfPowtorzoneHaslo.setLabelSeparator("<font color=red>*</font>");
        tfPowtorzoneHaslo.setAllowBlank(false);
        tfPowtorzoneHaslo.setPassword(true);

        FormPanel fp = new FormPanel();
        fp.setBorder(false);
        fp.setFrame(false);
        fp.setLabelWidth(110);

        fp.add(tfLogin);
        fp.add(tfHaslo);
        fp.add(tfPowtorzoneHaslo);

        return fp;
    }

    private String getValue(TextField textField) {
        return (textField.getValueAsString().equals("") ? null : textField.getValueAsString());
    }

    private FieldSet createAdditionalDataPanel() {
        fsDodatkowe = new FieldSet("Dodatkowe dane");
        fsDodatkowe.setCollapsible(true);
        fsDodatkowe.setCollapsed(true);

        tfNazwisko = new TextField("Nazwisko");
        tfNazwisko.setWidth(150);

        tfImie = new TextField("Imię");
        tfImie.setWidth(120);

        tfUlica = new TextField("Ulica");
        tfUlica.setWidth(150);

        tfNumerDomu = new TextField("Nr domu");
        tfNumerDomu.setWidth(25);

        tfNumerLokalu = new TextField("Nr lokalu");
        tfNumerLokalu.setWidth(25);

        tfMiasto = new TextField("Miasto");
        tfMiasto.setWidth(150);

        tfKodPocztowy = new TextField("Kod pocztowy");
        tfKodPocztowy.setWidth(50);

        cbPlec = new ComboBox("Płeć");
        Store storek = new SimpleStore(0, new String[] { "plec", "nazwa" }, new String[][] {
                new String[] { "M", "Mężczyzna" }, new String[] { "K", "Kobieta" } });
        cbPlec.setStore(storek);
        cbPlec.setDisplayField("nazwa");
        cbPlec.setValueField("plec");
        cbPlec.setWidth(100);
        cbPlec.setValue("M");
        storek.load();

        dfDataUrodzenia = new DateField("Data urodzenia", "Y-m-d");
        dfDataUrodzenia.setWidth(100);

        fsDodatkowe.add(new HorizontalPanel(new Widget[] { tfNazwisko, tfImie }, 100, false));
        fsDodatkowe.add(new HorizontalPanel(new Widget[] { dfDataUrodzenia, cbPlec }, 100, false));
        fsDodatkowe.add(new HorizontalPanel(new Widget[] { tfUlica, tfNumerDomu, tfNumerLokalu }, 100, false));
        fsDodatkowe.add(new HorizontalPanel(new Widget[] { tfMiasto, tfKodPocztowy }, 100, false));

        return fsDodatkowe;
    }

    private boolean isPustaOsoba() {
        boolean pusta = true;
        for (Component comp : fsDodatkowe.getComponents()) {
            if (comp instanceof HorizontalPanel) {
                for (Component formPanel : ((HorizontalPanel) comp).getComponents()) {
                    if (formPanel instanceof Panel) {
                        if (!((TextField) ((FormPanel) ((Panel) formPanel).getComponent(0)).getComponent(0))
                                .getValueAsString().equals("")) {
                            pusta = false;
                        }
                    }
                }
            }
        }
        return pusta;
    }

    private OsobaDTO getOsoba() {
        OsobaDTO osoba = null;
        if (!fsDodatkowe.isCollapsed()) {
            osoba = new OsobaDTO();
            osoba.setImie(getValue(tfImie));
            osoba.setNazwisko(getValue(tfNazwisko));
        }
        return osoba;
    }

}
