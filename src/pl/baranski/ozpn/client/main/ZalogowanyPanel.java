/**
 * 
 */
package pl.baranski.ozpn.client.main;

import pl.baranski.ozpn.client.OZPN;
import pl.baranski.ozpn.client.hyperlinks.UserHyperLink;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.gwtext.client.widgets.Panel;

/**
 * @author <a href="mailto:bartosz.baranski@o2.pl">Bartosz "TwiZ" Barański</a>
 * 
 */
public class ZalogowanyPanel extends Panel {
    private HTMLPanel htmlLogin;

    /**
     * Konstruktor.
     */
    public ZalogowanyPanel() {
        setBorder(false);
        setFrame(false);
    }

    public void setDane(String login, String typUzytkownika) {
        UserHyperLink userLink = new UserHyperLink(login);
        htmlLogin = new HTMLPanel("<div><b>Witaj <font color=#F16C00 id='login'></font></b></div>" + "<div>(<i>"
                + typUzytkownika + "</i>)</div>");
        htmlLogin.add(userLink, "login");
        add(htmlLogin);

        Anchor wylogujAnchor = new Anchor("Wyloguj");
        wylogujAnchor.addClickHandler(new ClickHandler() {

            public void onClick(ClickEvent event) {
                OZPN.getInstance().getLogowanie().showPanel(new Logowanie());
                OZPN.getInstance().getMainPanel().showPanel(new NewsPanel());
                OZPN.getInstance().getPanelUzytkownika().ustawWidoczny(false);
            }
        });

        HTMLPanel htmlWyloguj = new HTMLPanel("<div style='width:150px;text-align:right;' id='wyloguj'/>");
        htmlWyloguj.add(wylogujAnchor, "wyloguj");

        add(htmlWyloguj);

        doLayout();
    }
}
