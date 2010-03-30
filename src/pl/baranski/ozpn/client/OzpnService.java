package pl.baranski.ozpn.client;

import java.util.List;

import pl.baranski.ozpn.shared.NewsDTO;
import pl.baranski.ozpn.shared.ParametrDTO;
import pl.baranski.ozpn.shared.Strona2DTO;
import pl.baranski.ozpn.shared.UzytkownikDTO;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

/**
 * 
 * @author <a href="mailto:bartosz.baranski@o2.pl">Bartosz "TwiZ" Barański</a>
 * 
 */
@RemoteServiceRelativePath("service")
public interface OzpnService extends RemoteService {

    public List<NewsDTO> pobierzNewsy(int iloscNewsow) throws Exception;

    public Strona2DTO pobierzDaneDoTabeli3(String sql, String sqlIle, List<ParametrDTO> parametry) throws Exception;

    public String dodajNews(NewsDTO news) throws Exception;

    public NewsDTO pobierzDaneNewsa(int idNewsa) throws Exception;

    public UzytkownikDTO sprawdzLogin(String login, String haslo) throws Exception;

    public UzytkownikDTO pobierzSzczegolyUzytkownika(String login) throws Exception;

    public void rejestrujUzytkownika(UzytkownikDTO uzytkownik) throws Exception;
}
