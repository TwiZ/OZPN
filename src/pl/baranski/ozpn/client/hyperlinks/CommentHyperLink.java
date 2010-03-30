/**
 * 
 */
package pl.baranski.ozpn.client.hyperlinks;

import pl.baranski.ozpn.client.OZPN;
import pl.baranski.ozpn.client.main.NewsZKomentarzami;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Anchor;

/**
 * @author <a href="mailto:bartosz.baranski@o2.pl">Bartosz "TwiZ" Barański</a>
 * 
 */
public class CommentHyperLink extends Anchor {
    private int idNewsa;

    /**
     * Konstruktor.
     */
    public CommentHyperLink() {
        addListeners();
    }

    /**
     * Konstruktor.
     */
    public CommentHyperLink(String text, int idNewsa) {
        // this();
        super(text);
        this.idNewsa = idNewsa;
        setText(text);
        addListeners();
    }

    /**
     * 
     */
    private void addListeners() {
        System.out.println("CommentHyperLink.addListeners()");
        addClickHandler(new ClickHandler() {
            public void onClick(ClickEvent event) {
                System.out.println("CommentHyperLink.addListeners().new ClickHandler() {...}.onClick()");
                OZPN.getInstance().getMainPanel().showPanel(new NewsZKomentarzami(idNewsa));
            }
        });
    }

    /**
     * Ustawia wartość pola <code>idNewsa</code>
     * 
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
}
