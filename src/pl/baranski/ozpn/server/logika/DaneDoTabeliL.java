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

import pl.baranski.ozpn.shared.ParametrDTO;
import pl.baranski.ozpn.shared.Strona2DTO;

/**
 * @author <a href="mailto:bartosz.baranski@o2.pl">Bartosz "TwiZ" Barański</a>
 * 
 */
public class DaneDoTabeliL {
    Connection polaczenie;

    public DaneDoTabeliL(Connection polaczenie) {
        this.polaczenie = polaczenie;
    }

    public Strona2DTO pobierzDaneDoTabeli3(String sql, String sqlIle, List<ParametrDTO> parametry) throws Exception {

        List<String[]> wynik = new ArrayList<String[]>();
        int liczba = 0;
        try {
            PreparedStatement stmtILE = polaczenie.prepareStatement(sqlIle);
            if (parametry != null) {
                for (int i = 0; i < parametry.size(); i++) {
                    if (parametry.get(i).getTyp() == ParametrDTO.TYP_STRING) {
                        stmtILE.setString(i + 1, parametry.get(i).getParamString());
                    } else if (parametry.get(i).getTyp() == ParametrDTO.TYP_TAB_INTEGER) {
                        stmtILE.setArray(i + 1, polaczenie.createArrayOf("integer", parametry.get(i).getParamList()
                                .toArray()));
                    } else if (parametry.get(i).getTyp() == ParametrDTO.TYP_DATA) {
                        if (parametry.get(i).getParamDate() != null) {
                            stmtILE.setDate(i + 1, new java.sql.Date(parametry.get(i).getParamDate().getTime()));
                        } else {
                            stmtILE.setObject(i + 1, null);
                        }
                    }
                }
            }
            stmtILE.executeQuery();
            ResultSet rsILE = stmtILE.getResultSet();
            rsILE.next();
            liczba = rsILE.getInt(1);

            PreparedStatement stmt = polaczenie.prepareStatement(sql);
            if (parametry != null) {
                for (int i = 0; i < parametry.size(); i++) {
                    if (parametry.get(i).getTyp() == ParametrDTO.TYP_STRING) {
                        stmt.setString(i + 1, parametry.get(i).getParamString());
                    } else if (parametry.get(i).getTyp() == ParametrDTO.TYP_TAB_INTEGER) {
                        stmt.setArray(i + 1, polaczenie.createArrayOf("integer", parametry.get(i).getParamList()
                                .toArray()));
                    } else if (parametry.get(i).getTyp() == ParametrDTO.TYP_DATA) {
                        if (parametry.get(i).getParamDate() != null) {
                            stmt.setDate(i + 1, new java.sql.Date(parametry.get(i).getParamDate().getTime()));
                        } else {
                            stmt.setObject(i + 1, null);
                        }
                    }
                }
            }
            stmt.executeQuery();
            ResultSet rs = stmt.getResultSet();

            int ileKol = rs.getMetaData().getColumnCount();
            String[] jeden;

            while (rs.next()) {
                jeden = new String[ileKol];
                for (int i = 0; i < ileKol; i++) {
                    jeden[i] = rs.getString(i + 1);
                }
                wynik.add(jeden);
            }
            polaczenie.commit();
        } catch (SQLException e) {
            // } catch (Exception e) {
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
        Strona2DTO wynikDTO = new Strona2DTO();
        wynikDTO.setLiczbaWierszy(liczba);
        wynikDTO.setDane(wynik.toArray(new String[0][0]));
        return wynikDTO;
    }

}
