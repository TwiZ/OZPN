/**
 * 
 */
package pl.baranski.ozpn.client.main;

import java.util.ArrayList;
import java.util.List;

import pl.baranski.ozpn.client.OZPN;
import pl.baranski.ozpn.client.hyperlinks.CommentHyperLink;
import pl.baranski.ozpn.client.hyperlinks.UserHyperLink;
import pl.baranski.ozpn.shared.NewsDTO;

import com.google.gwt.user.client.History;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.gwtext.client.core.Ext;
import com.gwtext.client.widgets.Panel;

/**
 * @author <a href="mailto:bartosz.baranski@o2.pl">Bartosz "TwiZ" Barański</a>
 * 
 */
public class NewsPanel extends Panel {
    /**
     * Konstruktor.
     */

    List<HTMLPanel> listaHtmli = new ArrayList<HTMLPanel>();

    public NewsPanel() {
        History.newItem("newsy_token");
        setBorder(false);
        // setHeight(400);
        // setWidth(400);
        Ext.getBody().mask("Trwa ładowanie danych.. Prosze Czekać");
        OZPN.getInstance().getOzpnService().pobierzNewsy(20, new AsyncCallback<List<NewsDTO>>() {

            public void onSuccess(List<NewsDTO> result) {
                System.out.println("NewsPanel.NewsPanel().new AsyncCallback<List<NewsDTO>>() {...}.onSuccess()");
                for (final NewsDTO news : result) {
                    System.out.println("temat : " + news.getTytul() + " " + news.getTresc());

                    // CommentHyperLink commentLink = new CommentHyperLink(, news.getIdNewsa());

                    CommentHyperLink commentLink = new CommentHyperLink("Komentarze (" + news.getIlosc_komentarzy()
                            + ")", news.getIdNewsa());

                    UserHyperLink userLink = new UserHyperLink(news.getUzytkownik().getLogin());

                    // HTMLPanel html = new HTMLPanel("<div class='post'>" + "<h1 class='title'>" + news.getTytul()
                    // + "</h1>" + "<div class='entry'>" + news.getTresc() + "</div><hr>"
                    // + "<div><div class='byline'>Dodane " + news.getData_dodania() + " przez <font color=red>"
                    // + news.getAutor() + "</font></div>" + commentLink + "</div></div>");

                    HTMLPanel html = new HTMLPanel("<div class='post'><h3>" + news.getTytul() + "</h3>"
                            + "<ul class='post_info'>" + " <li  class='date'>Napisał/-a</li><li id='author"
                            + news.getIdNewsa() + "'/><li>dnia " + news.getData_dodania()
                            + "</li><li class='comments' id='comments" + news.getIdNewsa() + "'/></ul>"
                            + news.getTresc() + " </p></div><br class='clear' />");

                    html.add(commentLink, "comments" + news.getIdNewsa());
                    html.add(userLink, "author" + news.getIdNewsa());
                    // listaHtmli.add(html);
                    add(html);
                    doLayout();
                    Ext.getBody().unmask();
                }

            }

            public void onFailure(Throwable caught) {
                System.out.println("NewsPanel.NewsPanel().new AsyncCallback<List<NewsDTO>>() {...}.onFailure()");
            }
        });
    }
    // private HTMLPanel getPanel(HTMLPanel html) {
    // HTMLPanel htmlPanel = new HTMLPanel("<div id='newsy'/>");
    // // htmlPanel.add(html, "newsy");
    // htmlPanel.add(html);
    //
    // return htmlPanel;
    // }
}
