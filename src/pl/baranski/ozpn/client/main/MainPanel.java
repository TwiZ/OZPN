/**
 * 
 */
package pl.baranski.ozpn.client.main;

import com.gwtext.client.widgets.Panel;

/**
 * @author <a href="mailto:bartosz.baranski@o2.pl">Bartosz "TwiZ" Barański</a>
 * 
 */
public class MainPanel extends Panel {
    /**
     * Konstruktor.
     */
    public MainPanel() {
        setAutoHeight(true);
        // setHeight(500);
        setAutoWidth(true);
        setBorder(false);
        setFrame(false);
        // History.addValueChangeHandler(new ValueChangeHandler<String>() {

        // @Override
        // public void onValueChange(ValueChangeEvent<String> event) {
        // System.out.println("MainPanel.valuechange " + event.getValue());
        // History.back();
        // if (event.getValue().equals("newsy_token")) {
        // System.out.println("wlaczam newsy");
        // showPanel(new NewsPanel());
        // }
        // }
        // });
    }

    public void showPanel(Panel panel) {
        // System.out.println("TOKEN " + History.getToken());
        removeAll(true);
        add(panel);
        doLayout(true);
    }
}
