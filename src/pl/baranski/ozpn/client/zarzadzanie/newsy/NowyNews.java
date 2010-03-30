/**
 * 
 */
package pl.baranski.ozpn.client.zarzadzanie.newsy;

import pl.baranski.ozpn.client.OZPN;
import pl.baranski.ozpn.shared.NewsDTO;
import pl.baranski.ozpn.shared.UzytkownikDTO;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.gwtext.client.core.EventObject;
import com.gwtext.client.widgets.Button;
import com.gwtext.client.widgets.MessageBox;
import com.gwtext.client.widgets.Window;
import com.gwtext.client.widgets.event.ButtonListenerAdapter;
import com.gwtext.client.widgets.form.FormPanel;
import com.gwtext.client.widgets.form.HtmlEditor;
import com.gwtext.client.widgets.form.TextField;

/**
 * @author <a href="mailto:bartosz.baranski@o2.pl">Bartosz "TwiZ" Barański</a>
 * 
 */
public class NowyNews extends Window {
    private TextField tfTytul;
    private HtmlEditor htmlEditor;
    private Button bZapisz;
    private Button bWyjdz;

    // private
    /**
     * Konstruktor.
     */
    public NowyNews() {
        setTitle("NOWY NEWS");
        setCloseAction(CLOSE);
        setWidth(700);
        setHeight("auto");
        setPaddings(10);
        setModal(true);
        createGUI();
        addListeners();
        show();

    }

    /**
     * 
     */
    private void createGUI() {
        tfTytul = new TextField("Tytuł");
        tfTytul.setWidth(200);
        htmlEditor = new HtmlEditor("Treść");
        htmlEditor.setHeight(300);

        bZapisz = new Button("Zapisz");
        bWyjdz = new Button("Wyjdź");

        FormPanel fp = new FormPanel();
        fp.setBorder(false);
        fp.setFrame(true);
        fp.add(tfTytul);
        fp.add(htmlEditor);
        add(fp);

        setButtons(new Button[] { bZapisz, bWyjdz });
    }

    /**
     * 
     */
    private void addListeners() {
        bWyjdz.addListener(new ButtonListenerAdapter() {
            public void onClick(Button button, EventObject e) {
                close();
            }
        });

        bZapisz.addListener(new ButtonListenerAdapter() {
            public void onClick(Button button, EventObject e) {
                NewsDTO news = new NewsDTO();
                UzytkownikDTO uzytkownik = new UzytkownikDTO();
                uzytkownik.setLogin("twiz");
                news.setUzytkownik(uzytkownik);
                news.setTresc(htmlEditor.getValueAsString());
                news.setTytul(tfTytul.getValueAsString());
                OZPN.getInstance().getOzpnService().dodajNews(news, new AsyncCallback<String>() {

                    public void onSuccess(String result) {
                        MessageBox.alert("OZPN", "Dodano nowego newsa.");
                        close();
                    }

                    public void onFailure(Throwable caught) {
                        caught.printStackTrace();
                        System.out.println("Błąd");
                    }
                });
            }
        });
    }
}
