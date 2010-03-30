/**
 * 
 */
package pl.baranski.ozpn.client.widgets;

import com.google.gwt.user.client.ui.RootPanel;
import com.gwtext.client.core.EventObject;
import com.gwtext.client.widgets.Button;
import com.gwtext.client.widgets.Component;
import com.gwtext.client.widgets.Panel;
import com.gwtext.client.widgets.event.ButtonListenerAdapter;
import com.gwtext.client.widgets.event.PanelListenerAdapter;

/**
 * @author <a href="mailto:bartosz.baranski@o2.pl">Bartosz "TwiZ" Barański</a>
 * 
 */
public class InfoWindow extends Panel {
    private Button bWyjdz;

    /**
     * Konstruktor.
     */
    public InfoWindow() {
        createUI();
        addListeners();
    }

    public InfoWindow(String title) {
        this();
    }

    /**
     * 
     */
    private void addListeners() {
        addListener(new PanelListenerAdapter() {
            public boolean doBeforeShow(Component component) {
                System.out.println("DO BEFORE SHOW");
                // Ext.getBody().mask();
                return super.doBeforeShow(component);
            }

            public void onHide(Component component) {
                System.out.println("ON HIDE");
                // Ext.getBody().unmask();
                super.onHide(component);
            }
        });

        bWyjdz.addListener(new ButtonListenerAdapter() {
            public void onClick(Button button, EventObject e) {
                hide();
            };
        });

    }

    /**
     * 
     */
    private void createUI() {

        setWidth(200);
        setHeight(200);
        setFloating(true);

        setPosition(300, 300);
        bWyjdz = new Button("Wyjdź");
        // setButtons(new Button[] { bWyjdz });
        // setButtonAlign(Position.CENTER);
        add(bWyjdz);
        doLayout();
        RootPanel.get().add(this);

    }

}
