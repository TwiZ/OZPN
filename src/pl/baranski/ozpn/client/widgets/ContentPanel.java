/**
 * 
 */
package pl.baranski.ozpn.client.widgets;

import com.google.gwt.user.client.ui.HTMLPanel;
import com.gwtext.client.widgets.Panel;

/**
 * @author <a href="mailto:bartosz.baranski@o2.pl">Bartosz "TwiZ" Barański</a>
 * 
 */
public abstract class ContentPanel extends Panel {
    /**
     * Konstruktor.
     */
    public ContentPanel(String title) {
        add(new HTMLPanel("<h3>" + title + "</h3>"));
        setBorder(false);
        setFrame(false);
    }

}
