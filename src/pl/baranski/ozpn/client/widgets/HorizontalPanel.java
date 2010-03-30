/**
 * 
 */
package pl.baranski.ozpn.client.widgets;

import com.google.gwt.user.client.ui.Widget;
import com.gwtext.client.widgets.Panel;
import com.gwtext.client.widgets.form.Field;
import com.gwtext.client.widgets.form.FormPanel;
import com.gwtext.client.widgets.layout.HorizontalLayout;

/**
 * @author <a href="mailto:bartosz.baranski@o2.pl">Bartosz "TwiZ" Barański</a>
 * 
 */
public class HorizontalPanel extends Panel {
    /**
     * Konstruktor.
     */
    public HorizontalPanel(Widget[] widgety, int labelWidth, boolean rowneLabele) {
        setLayout(new HorizontalLayout(20));
        int licznik = 0;
        for (Widget widget : widgety) {
            if (widget instanceof Field) {
                FormPanel fp = new FormPanel();
                if (licznik == 0 || rowneLabele) {
                    fp.setLabelWidth(labelWidth);
                } else if (licznik > 0) {
                    String fieldLabel = ((Field) widget).getFieldLabel();
                    int szerokoscLabela = fieldLabel.length() * 5 + 23;
                    int odjeteWaskie = 0;
                    for (Character c : fieldLabel.toCharArray()) {
                        switch (c) {
                        case 'l':
                            odjeteWaskie += 4;
                            break;
                        case 'r':
                            odjeteWaskie += 3;
                            break;
                        case 'i':
                            odjeteWaskie += 4;
                            break;
                        case 'I':
                            odjeteWaskie += 4;
                            break;
                        case 'ł':
                            odjeteWaskie += 4;
                            break;

                        default:
                            break;
                        }
                    }
                    System.out.println("Szerokosc labela przed " + szerokoscLabela);
                    System.out.println("Licznik waskich liter to " + odjeteWaskie);
                    szerokoscLabela = szerokoscLabela - (odjeteWaskie);
                    System.out.println("Szerokosc labela po " + szerokoscLabela);

                    fp.setLabelWidth(szerokoscLabela);
                }
                fp.setBorder(false);
                fp.add(widget);
                add(fp);
            }
            licznik++;
        }

        doLayout(true);
    }
}
