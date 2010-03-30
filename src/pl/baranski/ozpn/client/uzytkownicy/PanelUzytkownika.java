/**
 * 
 */
package pl.baranski.ozpn.client.uzytkownicy;

import pl.baranski.ozpn.client.OZPN;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.RootPanel;
import com.gwtext.client.widgets.Panel;

/**
 * @author <a href="mailto:bartosz.baranski@o2.pl">Bartosz "TwiZ" Barański</a>
 * 
 */
public class PanelUzytkownika extends Panel {
    private HTML htmlTitle = new HTML("<h4><span>Panel</span> użytkownika</h4>");
    private HTML htmlLinkiUzytkownik;
    private int statusUzytkownika;
    public static final int ADMINISTRATOR_OZPN = 1;
    public static final int ADMINISTRATOR_KLUBOWY = 2;

    /**
     * Konstruktor.
     */
    public PanelUzytkownika() {
        setBorder(false);
        RootPanel.get("columnC_uzytkownik").add(this);
    }

    public void ustawWidoczny(boolean isVisible) {
        if (isVisible) {
            removeAll(true);
            add(getUzytkownikHTML());
            // add(htmlLinkiUzytkownik);
            // add(new HTML("</ul>"));
            // }
            // htmlLinkiUzytkownik = new HTML(getUzytkownikHTML());
            doLayout(true);
            setVisible(true);
            System.out.println("Set visible");
        } else {
            System.out.println("Hide visible");
            setVisible(false);
        }
    }

    private HTMLPanel getUzytkownikHTML() {
        String html = "<h4><span>Panel</span> użytkownika</h4><ul class='links'>";

        switch (statusUzytkownika) {
        case ADMINISTRATOR_OZPN:
            return getAdministratorOZPNLinks(html);

        default:
            html += "<li><a href=''>Inny</a></li>";
        }
        html += "</ul>";
        HTMLPanel htmlPanel = new HTMLPanel(html);
        return htmlPanel;
    }

    private HTMLPanel getAdministratorOZPNLinks(String html) {
        html += "<li id='administracja_ozpn_uzytkownicy'/>";
        HTMLPanel htmlPanel = new HTMLPanel(html);
        htmlPanel.add(createAnchor("Zarządzanie użytkownikami", new ZarzadzanieUzytkownikami()),
                "administracja_ozpn_uzytkownicy");
        return htmlPanel;
    }

    /**
     * Ustawia wartość pola <code>statusUzytkownika</code>.
     * 
     * @param statusUzytkownika
     *            the statusUzytkownika to set
     */
    public void setStatusUzytkownika(int statusUzytkownika) {
        this.statusUzytkownika = statusUzytkownika;
    }

    private Anchor createAnchor(String text, final Panel wyswietlonyPanel) {
        Anchor wylogujAnchor = new Anchor(text);
        wylogujAnchor.addClickHandler(new ClickHandler() {
            public void onClick(ClickEvent event) {
                OZPN.getInstance().getMainPanel().showPanel(wyswietlonyPanel);
            }
        });

        return wylogujAnchor;
    }
}
