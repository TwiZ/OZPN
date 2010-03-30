/**
 * 
 */
package pl.baranski.ozpn.server.logika;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import pl.baranski.ozpn.shared.NewsDTO;
import pl.baranski.ozpn.shared.OsobaDTO;
import pl.baranski.ozpn.shared.PostNewsaDTO;
import pl.baranski.ozpn.shared.UzytkownikDTO;

/**
 * @author <a href="mailto:bartosz.baranski@o2.pl">Bartosz "TwiZ" Barański</a>
 * 
 */
public class NewsyL {
    Connection polaczenie;

    public NewsyL(Connection polaczenie) throws Exception {
        this.polaczenie = polaczenie;
    }

    public List<NewsDTO> pobierzNewsy(int iloscNewsow) throws Exception {
        List<NewsDTO> listaNewsow = new ArrayList<NewsDTO>();
        UzytkownikDTO uzytkownik = new UzytkownikDTO();
        OsobaDTO osoba = new OsobaDTO();
        try {
            polaczenie.setAutoCommit(false);
            String sqlTxt = "select ilosc_komentarzy,id_n,autor,tytul,tresc,data_dodania, plec from wybierz_newsy(?) ";
            PreparedStatement stmt = polaczenie.prepareStatement(sqlTxt);
            stmt.setInt(1, iloscNewsow);
            System.out.println(stmt);

            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                NewsDTO news = new NewsDTO();
                uzytkownik.setLogin(rs.getString("autor"));
                osoba.setPlec(rs.getString("plec"));
                uzytkownik.setOsoba(osoba);
                news.setTresc(rs.getString("tresc"));
                news.setTytul(rs.getString("tytul"));
                news.setIdNewsa(rs.getInt("id_n"));
                news.setData_dodania(rs.getString("data_dodania"));
                news.setIlosc_komentarzy(rs.getInt("ilosc_komentarzy"));
                news.setUzytkownik(uzytkownik);
                listaNewsow.add(news);
            }

            polaczenie.commit();

        } catch (SQLException e) {
            try {
                polaczenie.rollback();
            } catch (SQLException e1) {
                // Wyjatek.dodajInformacjeOWyjatku(e1);
            }
            // Wyjatek.dodajInformacjeOWyjatku(e);
            throw new Exception(e.getLocalizedMessage());
        }
        return listaNewsow;
    }

    public NewsDTO pobierzDaneNewsa(int idNewsa) throws Exception {
        List<PostNewsaDTO> listaPostow = new ArrayList<PostNewsaDTO>();
        NewsDTO news = new NewsDTO();
        UzytkownikDTO uzytkownik = new UzytkownikDTO();
        OsobaDTO osoba = new OsobaDTO();
        try {
            polaczenie.setAutoCommit(false);
            String sqlDanePodstawoweNewsa = "select ilosc_komentarzy,id_n,autor,tytul,tresc,data_dodania "
                    + "from pobierz_szczegoly_newsa(?) ";
            PreparedStatement stmt = polaczenie.prepareStatement(sqlDanePodstawoweNewsa);
            stmt.setInt(1, idNewsa);

            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                uzytkownik.setLogin(rs.getString("autor"));
                news.setTresc(rs.getString("tresc"));
                news.setTytul(rs.getString("tytul"));
                news.setIdNewsa(rs.getInt("id_n"));
                news.setData_dodania(rs.getString("data_dodania"));
                news.setIlosc_komentarzy(rs.getInt("ilosc_komentarzy"));
                news.setUzytkownik(uzytkownik);
            }

            String sqlPosty = "select data_dodania, autor, temat, tresc, id_n, id_np from wybierz_posty_newsa(?)";
            PreparedStatement stmtPosty = polaczenie.prepareStatement(sqlPosty);
            stmtPosty.setInt(1, idNewsa);

            ResultSet rsPosty = stmtPosty.executeQuery();
            while (rsPosty.next()) {
                PostNewsaDTO post = new PostNewsaDTO();
                post.setAutor(rsPosty.getString("autor"));
                post.setDataDodania(rsPosty.getString("data_dodania"));
                post.setIdNewsa(rsPosty.getInt("id_n"));
                post.setIdPostaNewsa(rsPosty.getInt("id_np"));
                post.setTemat(rsPosty.getString("temat"));
                post.setTresc(rsPosty.getString("tresc"));
                listaPostow.add(post);
            }

            news.setListaPostow(listaPostow);

            polaczenie.commit();

        } catch (SQLException e) {
            try {
                polaczenie.rollback();
            } catch (SQLException e1) {
                // Wyjatek.dodajInformacjeOWyjatku(e1);
            }
            // Wyjatek.dodajInformacjeOWyjatku(e);
            throw new Exception(e.getLocalizedMessage());
        }
        return news;
    }

    public String dodajNews(NewsDTO news) throws Exception {
        try {
            polaczenie.setAutoCommit(false);
            String sqlTxt = "INSERT INTO newsy(login, temat, tresc, data_dodania) VALUES ( ? , ? , ? , current_date ) ";
            PreparedStatement stmt = polaczenie.prepareStatement(sqlTxt);
            stmt.setString(1, news.getUzytkownik().getLogin());
            stmt.setString(2, news.getTytul());
            stmt.setString(3, news.getTresc());
            System.out.println(stmt);
            stmt.execute();
            polaczenie.commit();

        } catch (SQLException e) {
            try {
                polaczenie.rollback();
            } catch (SQLException e1) {
                // Wyjatek.dodajInformacjeOWyjatku(e1);
            }
            // Wyjatek.dodajInformacjeOWyjatku(e);
            throw new Exception(e.getLocalizedMessage());
        }
        return "";
    }

}
