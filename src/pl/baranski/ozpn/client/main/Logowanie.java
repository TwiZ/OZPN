/**
 * 
 */
package pl.baranski.ozpn.client.main;

import pl.baranski.ozpn.client.OZPN;
import pl.baranski.ozpn.client.helpers.TextHelper;
import pl.baranski.ozpn.client.uzytkownicy.PanelUzytkownika;
import pl.baranski.ozpn.client.uzytkownicy.RejestracjaUzytkownika;
import pl.baranski.ozpn.client.widgets.InfoBox;
import pl.baranski.ozpn.shared.UzytkownikDTO;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.gwtext.client.core.EventCallback;
import com.gwtext.client.core.EventObject;
import com.gwtext.client.core.Ext;
import com.gwtext.client.core.Position;
import com.gwtext.client.widgets.Button;
import com.gwtext.client.widgets.Panel;
import com.gwtext.client.widgets.event.ButtonListenerAdapter;
import com.gwtext.client.widgets.form.FormPanel;
import com.gwtext.client.widgets.form.TextField;

/**
 * @author <a href="mailto:bartosz.baranski@o2.pl">Bartosz "TwiZ" Barański</a>
 * 
 */
public class Logowanie extends Panel {
    private Button bLoguj;
    private TextField tfLogin;
    private Button bRejestruj;
    private TextField tfHaslo;

    /**
     * Konstruktor.
     */
    public Logowanie() {
        createUI();
        addListeners();
    }

    private void createUI() {
        setBorder(false);
        setFrame(false);
        setPaddings(1);

        tfLogin = new TextField("Login");
        tfLogin.setWidth(150);
        tfLogin.setAllowBlank(false);
        tfLogin.setFieldMsgTarget("qtip");
        // add(tfLogin);

        tfHaslo = new TextField("Hasło");
        tfHaslo.setWidth(150);
        tfHaslo.setPassword(true);
        tfHaslo.setAllowBlank(false);
        tfLogin.setFieldMsgTarget("qtip");
        // add(tfHaslo);

        FormPanel fp = new FormPanel();
        fp.setLabelAlign(Position.TOP);
        fp.setBorder(false);

        fp.add(tfLogin);
        fp.add(tfHaslo);

        add(fp);

        Anchor rejestrujAnchor = new Anchor("Rejestruj");
        rejestrujAnchor.addClickHandler(new ClickHandler() {

            public void onClick(ClickEvent event) {
                OZPN.getInstance().getMainPanel().showPanel(new RejestracjaUzytkownika("Rejestracja użytkownika"));
            }
        });

        bLoguj = new Button("Loguj");

        HTMLPanel html = new HTMLPanel("<table><tr><td style='width: 75px;' id='anchor'/>"
                + "<td style='width: 75px; padding-left:45px;' id='loguj'/></tr></table>");
        html.add(rejestrujAnchor, "anchor");
        html.add(bLoguj, "loguj");

        add(html);
    }

    /**
     * 
     */
    private void addListeners() {
        bLoguj.addListener(new ButtonListenerAdapter() {
            public void onClick(Button button, EventObject e) {
                loguj();
            }
        });

        tfHaslo.addKeyPressListener(new EventCallback() {

            public void execute(EventObject e) {
                if (e.getKey() == 13) {
                    loguj();
                }
            }
        });
    }

    private void loguj() {
        if (tfLogin.validate() && tfHaslo.validate()) {
            Ext.getBody().mask("Trwa logowanie..");
            OZPN.getInstance().getOzpnService().sprawdzLogin(getLogin(), getHaslo(),
                    new AsyncCallback<UzytkownikDTO>() {

                        @Override
                        public void onSuccess(final UzytkownikDTO uzytkownik) {
                            Ext.getBody().unmask();
                            if (uzytkownik != null) {
                                OZPN.getInstance().getLogowanie().showPanel(new ZalogowanyPanel() {
                                    {
                                        PanelUzytkownika pnlUzytkownika = OZPN.getInstance().getPanelUzytkownika();
                                        pnlUzytkownika.setStatusUzytkownika(uzytkownik.getStatus());
                                        pnlUzytkownika.ustawWidoczny(true);

                                        // .new PanelUzytkownika(uzytkownik.getStatus()).setVisible(true);
                                        setDane(tfLogin.getValueAsString(), uzytkownik.getTypUzytkownika());
                                        // jeśli jest aktualnie na main panelu rejestracja użytkownika, to pokazuje
                                        // newsy
                                        // rejestracja użytkownika nie jest możliwa po zalogowaniu
                                        if (OZPN.getInstance().getMainPanel().getComponent(TextHelper.REJESTRACJA_ID) != null) {
                                            OZPN.getInstance().getMainPanel().showPanel(new NewsPanel());
                                        }
                                    }
                                });
                            } else {
                                InfoBox
                                        .alert("Niepoprawne logowanie",
                                                "Niepoprawny login lub hasło. Spróbuj ponownie.");
                            }
                        }

                        @Override
                        public void onFailure(Throwable caught) {

                            InfoBox.alert("Logowanie nie powiodło się.");

                        }
                    });
        } else {
            InfoBox.alert("Błędne dane", "Pola loginu i hasła muszą być uzupełnione.");
        }
    }

    public String getLogin() {
        return tfLogin.getValueAsString();
    }

    public String getHaslo() {
        return tfHaslo.getValueAsString();
    }
}
