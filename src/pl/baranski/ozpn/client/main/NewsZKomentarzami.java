/**
 * 
 */
package pl.baranski.ozpn.client.main;

import pl.baranski.ozpn.client.OZPN;
import pl.baranski.ozpn.shared.NewsDTO;
import pl.baranski.ozpn.shared.PostNewsaDTO;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.HTMLPanel;

/**
 * @author <a href="mailto:bartosz.baranski@o2.pl">Bartosz "TwiZ" Barański</a>
 * 
 */
public class NewsZKomentarzami extends MainPanel {
    /**
     * Konstruktor.
     */
    public NewsZKomentarzami(int idNewsa) {
        // setId("news_panel_z_komentarzami");
        setBorder(false);
        System.out.println("News z komentarzami");
        OZPN.getInstance().getOzpnService().pobierzDaneNewsa(idNewsa, new AsyncCallback<NewsDTO>() {

            public void onSuccess(NewsDTO news) {
                System.out.println("NewsPanel.NewsPanel().new AsyncCallback<List<NewsDTO>>() {...}.onSuccess()");

                System.out.println("temat : " + news.getTytul() + " " + news.getTresc());

                HTMLPanel htmlNews = new HTMLPanel("<div class='post'>" + "<h1 class='title'>" + news.getTytul()
                        + "</h1>" + "<div class='entry'>" + news.getTresc() + "</div><hr>"
                        + "<span class='byline'><small>Dodane " + news.getData_dodania() + " przez <font color=red>"
                        + news.getUzytkownik().getLogin() + "</font></span></small></div>" + "<hr>Komentarze:<br><br>");
                // html.add(new Label(news.getAutor()), "autor");
                // listaHtmli.add(html);
                add(htmlNews);

                for (PostNewsaDTO post : news.getListaPostow()) {
                    HTMLPanel htmlPosty = new HTMLPanel("<div class='komentarz'>" + "<div class='title'>"
                            + post.getTemat() + "</div>" + "<div class='tresc'>" + post.getTresc() + "</div><hr>"
                            + "<span class='byline'><small>Dodane " + post.getDataDodania() + " przez <font color=red>"
                            + post.getAutor() + "</font></span></div>");

                    add(htmlPosty);

                }
                doLayout();

            }

            public void onFailure(Throwable caught) {
                System.out.println("NewsPanel.NewsPanel().new AsyncCallback<List<NewsDTO>>() {...}.onFailure()");
                caught.printStackTrace();
            }
        });
    }
}
