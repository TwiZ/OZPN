/**
 * 
 */
package pl.baranski.ozpn.server.logika;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import pl.baranski.ozpn.shared.OsobaDTO;
import pl.baranski.ozpn.shared.UzytkownikDTO;

/**
 * @author <a href="mailto:bartosz.baranski@o2.pl">Bartosz "TwiZ" Barański</a>
 * 
 */
public class UzytkownicyL {
    Connection polaczenie;

    public UzytkownicyL(Connection polaczenie) throws Exception {
        this.polaczenie = polaczenie;
    }

    public UzytkownikDTO sprawdzLogin(String login, String haslo) throws Exception {
        Integer result = 0;
        UzytkownikDTO uzytkownik = null;
        try {
            polaczenie.setAutoCommit(false);
            String sqlTxt = "select count(login) as ilosc from uzytkownicy where login = ? and haslo = ? and typ > 0 ";
            PreparedStatement stmt = polaczenie.prepareStatement(sqlTxt);

            MessageDigest mdEnc = MessageDigest.getInstance("MD5"); // Encryption algorithm

            mdEnc.update(haslo.getBytes(), 0, haslo.length());

            String md5 = new BigInteger(1, mdEnc.digest()).toString(16); // Encrypted string

            stmt.setString(1, login);
            stmt.setString(2, md5);
            System.out.println(stmt);

            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                result = rs.getInt("ilosc");
            }

            // istnieje użytkownik
            if (result > 0) {
                uzytkownik = new UzytkownikDTO();
                uzytkownik.setLogin(login);
                sqlTxt = "  select login, id_os, haslo, typ, (case when typ = 0 then 'Użytkownik' "
                        + "when typ = 1 then 'Administrator OZPN' when typ = 2 then 'Administrator klubowy' "
                        + "when typ = 3 then 'Arbiter' when typ = 4 then 'Zawodnik' "
                        + "when typ = 5 then 'Użytkownik' when typ = 6 then 'Zbanowany użytkownik' "
                        + "else 'Ktoś inny' end) as typ_pelny  from uzytkownicy where login = ?";

                stmt = polaczenie.prepareStatement(sqlTxt);
                stmt.setString(1, login);
                rs = stmt.executeQuery();
                while (rs.next()) {
                    uzytkownik.setTypUzytkownika(rs.getString("typ_pelny"));
                    uzytkownik.setStatus(rs.getInt("typ"));
                }
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
        return uzytkownik;
    }

    public UzytkownikDTO pobierzSzczegolyUzytkownika(String login) throws Exception {
        UzytkownikDTO uzytkownik = new UzytkownikDTO();
        OsobaDTO osoba = new OsobaDTO();
        uzytkownik.setOsoba(osoba);
        uzytkownik.setLogin(login);
        try {
            polaczenie.setAutoCommit(false);
            String sqlTxt = "select imie, nazwisko, wzrost, waga, plec from pobierz_szczegoly_uzytkownika( ? ) ";
            PreparedStatement stmt = polaczenie.prepareStatement(sqlTxt);

            stmt.setString(1, login);

            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                osoba.setImie(rs.getString("imie"));
                osoba.setNazwisko(rs.getString("nazwisko"));
                osoba.setWzrost(rs.getInt("wzrost"));
                osoba.setWaga(rs.getInt("waga"));
                osoba.setPlec(rs.getString("plec"));
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
        return uzytkownik;
    }

    public void rejestrujUzytkownika(UzytkownikDTO uzytkownik) throws Exception {
        try {
            polaczenie.setAutoCommit(false);
            String sqlTxt = null;
            PreparedStatement stmt = null;
            ResultSet rs = null;
            int idOsoby = 0;
            OsobaDTO osoba = uzytkownik.getOsoba();

            if (osoba != null) {
                sqlTxt = "INSERT INTO osoby( nazwisko, imie) VALUES ( ?, ? ) returning id_os";
                stmt = polaczenie.prepareStatement(sqlTxt);

                stmt.setString(1, osoba.getNazwisko());
                stmt.setString(2, osoba.getImie());

                System.out.println(stmt);
                rs = stmt.executeQuery();
                while (rs.next()) {
                    idOsoby = rs.getInt("id_os");
                }
            }

            if (osoba != null) {
                sqlTxt = "INSERT INTO uzytkownicy(login, haslo, typ, id_os) VALUES (?, ?, ? ,?)";
            } else {
                sqlTxt = "INSERT INTO uzytkownicy(login, haslo, typ) VALUES (?, ?, ? )";
            }
            stmt = polaczenie.prepareStatement(sqlTxt);
            System.out.println(stmt);

            MessageDigest mdEnc = MessageDigest.getInstance("MD5"); // Encryption algorithm

            mdEnc.update(uzytkownik.getHaslo().getBytes(), 0, uzytkownik.getHaslo().length());

            String md5 = new BigInteger(1, mdEnc.digest()).toString(16); // Encrypted string

            stmt.setString(1, uzytkownik.getLogin());
            stmt.setString(2, md5);
            stmt.setInt(3, 0);// użytkownik nieautoryzowany
            if (osoba != null) {
                stmt.setInt(4, idOsoby);
            }

            stmt.execute();

            polaczenie.commit();

        } catch (SQLException e) {
            try {
                polaczenie.rollback();
            } catch (SQLException e1) {
                // Wyjatek.dodajInformacjeOWyjatku(e1);
                e1.printStackTrace();
            }
            // Wyjatek.dodajInformacjeOWyjatku(e);
            e.printStackTrace();
            throw new Exception(e.getLocalizedMessage());
        }
    }
}
