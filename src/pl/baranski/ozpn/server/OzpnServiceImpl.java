package pl.baranski.ozpn.server;

import java.sql.Connection;
import java.util.List;

import javax.servlet.http.HttpSession;

import pl.baranski.ozpn.client.OzpnService;
import pl.baranski.ozpn.server.logika.DaneDoTabeliL;
import pl.baranski.ozpn.server.logika.NewsyL;
import pl.baranski.ozpn.server.logika.UzytkownicyL;
import pl.baranski.ozpn.shared.NewsDTO;
import pl.baranski.ozpn.shared.ParametrDTO;
import pl.baranski.ozpn.shared.Strona2DTO;
import pl.baranski.ozpn.shared.UzytkownikDTO;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

/**
 * 
 * @author <a href="mailto:bartosz.baranski@o2.pl">Bartosz "TwiZ" Barański</a>
 * 
 */
@SuppressWarnings("serial")
public class OzpnServiceImpl extends RemoteServiceServlet implements OzpnService {

    public Connection pobierzPolaczenie() throws Exception {
        HttpSession sesja = getThreadLocalRequest().getSession(true);
        Connection polaczenie = (Connection) sesja.getAttribute("polaczenie_z_baza");
        if (polaczenie == null) {
            polaczenie = PolaczenieZBaza.utworzPolaczenie();
            sesja.setAttribute("polaczenie_z_baza", polaczenie);
        } else if (polaczenie.isClosed()) {
            polaczenie = PolaczenieZBaza.utworzPolaczenie();
            sesja.setAttribute("polaczenie_z_baza", polaczenie);
        }
        return polaczenie;
    }

    public List<NewsDTO> pobierzNewsy(int iloscNewsow) throws Exception {
        NewsyL newsyL = new NewsyL(pobierzPolaczenie());
        return newsyL.pobierzNewsy(iloscNewsow);
    }

    public Strona2DTO pobierzDaneDoTabeli3(String sql, String sqlIle, List<ParametrDTO> parametry) throws Exception {
        DaneDoTabeliL daneL = new DaneDoTabeliL(pobierzPolaczenie());
        return daneL.pobierzDaneDoTabeli3(sql, sqlIle, parametry);
    }

    public String dodajNews(NewsDTO news) throws Exception {
        NewsyL newsyL = new NewsyL(pobierzPolaczenie());
        return newsyL.dodajNews(news);
    }

    public NewsDTO pobierzDaneNewsa(int idNewsa) throws Exception {
        NewsyL newsyL = new NewsyL(pobierzPolaczenie());
        return newsyL.pobierzDaneNewsa(idNewsa);
    }

    public UzytkownikDTO sprawdzLogin(String login, String haslo) throws Exception {
        UzytkownicyL uzytkownicyL = new UzytkownicyL(pobierzPolaczenie());
        return uzytkownicyL.sprawdzLogin(login, haslo);
    }

    public UzytkownikDTO pobierzSzczegolyUzytkownika(String login) throws Exception {
        UzytkownicyL uzytkownicyL = new UzytkownicyL(pobierzPolaczenie());
        return uzytkownicyL.pobierzSzczegolyUzytkownika(login);
    }

    public void rejestrujUzytkownika(UzytkownikDTO uzytkownik) throws Exception {
        UzytkownicyL uzytkownicyL = new UzytkownicyL(pobierzPolaczenie());
        uzytkownicyL.rejestrujUzytkownika(uzytkownik);
    }
}
