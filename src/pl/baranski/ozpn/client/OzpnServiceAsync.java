package pl.baranski.ozpn.client;

import java.util.List;

import pl.baranski.ozpn.shared.NewsDTO;
import pl.baranski.ozpn.shared.ParametrDTO;
import pl.baranski.ozpn.shared.Strona2DTO;
import pl.baranski.ozpn.shared.UzytkownikDTO;

import com.google.gwt.user.client.rpc.AsyncCallback;

/**
 * 
 * @author <a href="mailto:bartosz.baranski@o2.pl">Bartosz Barański</a>
 * 
 */
public interface OzpnServiceAsync {

    void pobierzNewsy(int iloscNewsow, AsyncCallback<List<NewsDTO>> callback);

    void pobierzDaneDoTabeli3(String sql, String sqlIle, List<ParametrDTO> parametry, AsyncCallback<Strona2DTO> callback);

    void dodajNews(NewsDTO news, AsyncCallback<String> callback);

    void pobierzDaneNewsa(int idNewsa, AsyncCallback<NewsDTO> callback);

    void sprawdzLogin(String login, String haslo, AsyncCallback<UzytkownikDTO> callback);

    void pobierzSzczegolyUzytkownika(String login, AsyncCallback<UzytkownikDTO> callback);

    void rejestrujUzytkownika(UzytkownikDTO uzytkownik, AsyncCallback<Void> callback);

}
