/**
 * 
 */
package pl.baranski.ozpn.client.zarzadzanie.newsy;

import com.gwtext.client.core.EventObject;
import com.gwtext.client.widgets.Button;
import com.gwtext.client.widgets.Panel;
import com.gwtext.client.widgets.event.ButtonListenerAdapter;
import com.gwtext.client.widgets.layout.FormLayout;

/**
 * @author <a href="mailto:bartosz.baranski@o2.pl">Bartosz "TwiZ" Barański</a>
 * 
 */
public class NewsyZarzadzanie extends Panel {
    private Button bDodaj;

    /**
     * Konstruktor.
     */
    public NewsyZarzadzanie() {
        setLayout(new FormLayout());
        // bDodaj.setcl
        bDodaj = new Button("Dodaj newsa");
        bDodaj.addListener(new ButtonListenerAdapter() {
            public void onClick(Button button, EventObject e) {
                new NowyNews();
            }
        });
        add(bDodaj);
        add(new NewsyZarzadzanieGridPanel("adsad"));
    }
}
