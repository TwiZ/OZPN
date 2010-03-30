/**
 * 
 */
package pl.baranski.ozpn.client.hyperlinks;

import pl.baranski.ozpn.client.OZPN;
import pl.baranski.ozpn.client.uzytkownicy.SzczegolyUzytkownika;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Anchor;

/**
 * @author <a href="mailto:bartosz.baranski@o2.pl">Bartosz "TwiZ" Barański</a>
 * 
 */
public class UserHyperLink extends Anchor {
    private String login;

    /**
     * Konstruktor.
     */
    public UserHyperLink() {
        addListeners();
    }

    /**
     * Konstruktor.
     */
    public UserHyperLink(String login) {
        super(login);
        this.login = login;
        setText(login);
        addListeners();
    }

    /**
     * 
     */
    private void addListeners() {
        addClickHandler(new ClickHandler() {
            public void onClick(ClickEvent event) {
                System.out.println("UserHyperLink.addListeners().new ClickHandler() {...}.onClick()");
                OZPN.getInstance().getMainPanel().showPanel(new SzczegolyUzytkownika() {
                    {
                        ustawDane(login);
                    }
                });
            }
        });
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
     * Pobiera wartość pola <code>login</code>.
     * 
     * @return the login
     */
    public String getLogin() {
        return login;
    }
}
