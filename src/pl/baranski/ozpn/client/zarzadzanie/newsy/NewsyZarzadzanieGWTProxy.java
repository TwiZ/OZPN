/**
 * 
 */
package pl.baranski.ozpn.client.zarzadzanie.newsy;

import pl.baranski.ozpn.client.OZPN;
import pl.baranski.ozpn.shared.Strona2DTO;

import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.gwtext.client.core.UrlParam;
import com.gwtext.client.widgets.MessageBox;
import com.gwtextux.client.data.GWTProxy;

/**
 * 
 * @author <a href="mailto:b.baranski@zeto.koszalin.pl">Bartosz Barański</a>
 * 
 */
public class NewsyZarzadzanieGWTProxy extends GWTProxy {

    public static String AS = " as ";

    public final String ID_NEWSA = "id_n";

    // news.setAutor(rs.getString("autor"));
    // news.setTresc(rs.getString("tresc"));
    // news.setTytul(rs.getString("tytul"));
    // news.setIdNewsa(rs.getInt("id_n"));

    public final String TRESC = "tresc";
    public final String TYTUL = "tytul";
    public final String AUTOR = "autor";
    public final String DATA_DODANIA = "data_dodania";

    private String szukanieFraza, szukanieKolumna;
    private int szukanieRodzaj = STR_ILIKE;

    public static final int INT_ROWNY = 1;
    public static final int STR_ILIKE = 2;
    public static final int STR_LIKE_UPPER_CASE = 3;
    public static final int PARAMETR_IN = 4;

    private String sqlRaport;

    // List<ParametrDTO> parametry = new ArrayList<ParametrDTO>();

    public void load(int start, int limit, String sort, String dir, final JavaScriptObject o, UrlParam[] baseParams) {

        String sql = "SELECT id_n,data_dodania , temat, login FROM newsy";

        String sqlIle = "select count(*) from newsy";

        if (szukanieKolumna != null && szukanieFraza != null) {

            // if (!sql.contains("where")) {
            // sql += " where ";
            // sqlIle += " where ";
            // } else {
            // sql += " and ";
            // sqlIle += " and ";
            // }
            //
            // switch (szukanieRodzaj) {
            //
            // case STR_ILIKE:
            //
            // sql += " " + szukanieKolumna + " ilike ? || '%'";
            // sqlIle += " " + szukanieKolumna + " ilike ? || '%'";
            // parametry.add(new ParametrDTO(szukanieFraza));
            // break;
            //
            // default:
            // break;
            // }
        }

        if (limit < 0 && start < 0) {
            limit = 20;
            start = 0;
        }
        if (dir != null && sort != null) {
            sql += " order by " + sort + " " + dir;
        }
        sql += " offset " + start + " limit " + limit;

        OZPN.getInstance().getOzpnService().pobierzDaneDoTabeli3(sql, sqlIle, null, new AsyncCallback<Strona2DTO>() {

            public void onFailure(Throwable caught) {
                MessageBox.alert("eKANCELARIA", "Błąd podczas wczytywania danych.");
                loadResponse(o, false, 0, new String[0][0]);
            }

            public void onSuccess(Strona2DTO result) {
                loadResponse(o, true, result.getLiczbaWierszy(), result.getDane());
            }
        });
    }

    // /**
    // * Dodaje parametr do listy parametrów
    // *
    // * @param parametr
    // */
    // public void addParametr(ParametrDTO parametr) {
    // parametry.add(parametr);
    // }
    //
    // /**
    // * Czyści listę parametrów
    // */
    // public void czyscListeParametrow() {
    // parametry.clear();
    // }

    /**
     * Ustawia wartość pola <code>szukanieFraza</code>
     * 
     * @param szukanieFraza
     *            the szukanieFraza to set
     */
    public void setSzukanieFraza(String szukanieFraza) {
        this.szukanieFraza = szukanieFraza;
    }

    /**
     * Ustawia wartość pola <code>szukanieKolumna</code>
     * 
     * @param szukanieKolumna
     *            the szukanieKolumna to set
     */
    public void setSzukanieKolumna(String szukanieKolumna) {
        this.szukanieKolumna = szukanieKolumna;
    }

    public void czyscSzukanie() {
        szukanieKolumna = null;
        szukanieFraza = null;
        // parametry.clear();
    }

    /**
     * @return
     */
    public String getSQL() {
        return sqlRaport;
    }
}
