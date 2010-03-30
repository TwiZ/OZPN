/**
 * 
 */
package pl.baranski.ozpn.client.main;

import com.gwtext.client.widgets.Panel;

/**
 * @author <a href="mailto:bartosz.baranski@o2.pl">Bartosz "TwiZ" Barański</a>
 * 
 */
public class LogPanel extends Panel {
    /**
     * Konstruktor.
     */
    public LogPanel() {
        setAutoHeight(true);
        // setHeight(500);
        setAutoWidth(true);
        setBorder(false);
        setFrame(false);
    }

    public void showPanel(Panel panel) {
        removeAll(true);
        add(panel);
        doLayout(true);
    }
}
