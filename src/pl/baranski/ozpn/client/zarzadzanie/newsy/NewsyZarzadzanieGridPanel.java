/**
 * 
 */
package pl.baranski.ozpn.client.zarzadzanie.newsy;

import com.google.gwt.core.client.GWT;
import com.gwtext.client.core.EventObject;
import com.gwtext.client.core.Ext;
import com.gwtext.client.data.ArrayReader;
import com.gwtext.client.data.FieldDef;
import com.gwtext.client.data.Record;
import com.gwtext.client.data.RecordDef;
import com.gwtext.client.data.SimpleStore;
import com.gwtext.client.data.Store;
import com.gwtext.client.data.StringFieldDef;
import com.gwtext.client.data.event.StoreListenerAdapter;
import com.gwtext.client.widgets.Button;
import com.gwtext.client.widgets.PagingToolbar;
import com.gwtext.client.widgets.ToolTip;
import com.gwtext.client.widgets.Toolbar;
import com.gwtext.client.widgets.ToolbarButton;
import com.gwtext.client.widgets.ToolbarTextItem;
import com.gwtext.client.widgets.event.ButtonListenerAdapter;
import com.gwtext.client.widgets.form.ComboBox;
import com.gwtext.client.widgets.form.Field;
import com.gwtext.client.widgets.form.TextField;
import com.gwtext.client.widgets.form.event.FieldListenerAdapter;
import com.gwtext.client.widgets.form.event.TextFieldListenerAdapter;
import com.gwtext.client.widgets.grid.BaseColumnConfig;
import com.gwtext.client.widgets.grid.ColumnConfig;
import com.gwtext.client.widgets.grid.ColumnModel;
import com.gwtext.client.widgets.grid.GridPanel;
import com.gwtext.client.widgets.grid.event.GridCellListenerAdapter;
import com.gwtext.client.widgets.menu.CheckItem;
import com.gwtext.client.widgets.menu.Menu;
import com.gwtext.client.widgets.menu.event.CheckItemListenerAdapter;

/**
 * @author <a href="mailto:bartosz.baranski@o2.pl">Bartosz "TwiZ" Barański</a>
 * 
 */

public class NewsyZarzadzanieGridPanel extends GridPanel {

    protected static String COL_SZCZEGOLY = "szczegoly";
    protected static String COL_AKCJE = "col_akcja";

    protected static String LBL_WSZYSTKIE = "Wszystkie";
    protected static String LBL_DO_ODEBRANIA = "Do odebrania";
    protected static String LBL_ODEBRANE = "Odebrane";
    protected static String LBL_ODRZUCONE = "Odrzucone";

    protected static String ID_WSZYSTKIE = "odb_dok_el_wszystkie";
    protected static String ID_DO_ODEBRANIA = "odb_dok_el_do_odebrania";
    protected static String ID_ODEBRANE = "odb_dok_el_odebrane";
    protected static String ID_ODRZUCONE = "odb_dok_el_odrzucone";

    protected static String MENU_GROUP = "odb_dok_el_filtr";

    private String parentID;
    private NewsyZarzadzanieGWTProxy proxy;
    private PagingToolbar tbStronicowanie;
    private TextField tfWielkoscStrony;
    private Toolbar tbSzukanie;
    private ComboBox cbSzukanePole;
    private TextField tfSzukanyTekst;
    private ToolbarButton tbbSzukaj;
    private ToolbarButton tbbCzysc;
    private ToolbarButton tbbFiltr;

    // private Renderer rendSzczegoly = new Renderer() {
    // public String render(Object value, CellMetadata cellMetadata, Record record, int rowIndex, int colNum,
    // Store store) {
    //
    // return "<img class=\"szczegoly\" style=\"cursor:pointer;\" src=\"images/zoom.png\" />";
    // }
    // };

    // private Renderer rendOdbierz = new Renderer() {
    // public String render(Object value, CellMetadata cellMetadata, Record record, int rowIndex, int colNum,
    // Store store) {
    // int status = record.getAsInteger(NewsyZarzadzanieGWTProxy.STATUS);
    // switch (status) {
    // case NewsyZarzadzanieGWTProxy.STS_DO_ODEBRANIA:
    // return "<font class=\"odbierz\" style=\"color: blue;cursor:pointer;text-decoration:underline;\">Odbierz</font> "
    // + "<font class=\"odrzuc_dok\" style=\"color: blue;cursor:pointer;text-decoration:underline;\">Odrzuć</font> ";
    // case NewsyZarzadzanieGWTProxy.STS_ODRZUCONY:
    // return "<font class=\"odbierz\" style=\"color: blue;cursor:pointer;text-decoration:underline;\">Odbierz</font> ";
    //
    // default:
    // return "";
    // }
    //
    // }
    // };

    private Menu menu;

    {
        proxy = new NewsyZarzadzanieGWTProxy();
    }

    public NewsyZarzadzanieGridPanel(String parentID) {
        this.parentID = parentID;

        createUI();
        initPagingToolbar();
        initSearchToolbar();
        addListeners();
        setBottomToolbar(tbStronicowanie);
        setTopToolbar(tbSzukanie);

        // ((CheckItem) menu.getItem(String.valueOf(NewsyZarzadzanieGWTProxy.STS_DO_ODEBRANIA))).setChecked(true);
    }

    /**
     * Tworzy interfejs użytkownika
     */
    private void createUI() {
        setColumnModel();

        RecordDef recordDef = new RecordDef(new FieldDef[] { new StringFieldDef(proxy.ID_NEWSA),
                new StringFieldDef(proxy.DATA_DODANIA), new StringFieldDef(proxy.TYTUL),
                new StringFieldDef(proxy.AUTOR) });

        ArrayReader reader = new ArrayReader(recordDef);

        setFrame(true);
        setStripeRows(false);
        setAutoExpandColumn(proxy.TYTUL);
        // setHeight(EKancelariaGWT.getWysGrida());
        setHeight(500);
        setWidth("100%");

        Store store = new Store(proxy, reader, true);
        store.addStoreListener(new StoreListenerAdapter() {
            public void onLoad(Store store, Record[] records) {
                if (parentID != null)
                    if (Ext.get(parentID) != null)
                        Ext.get(parentID).unmask();
            }
        });
        setStore(store);
        store.load();

        // initMenu();

    }

    /**
     * Ustawia kolumn model
     */
    private void setColumnModel() {

        BaseColumnConfig[] columns = new BaseColumnConfig[] {
                new ColumnConfig("<b>Data<br>dodania</b>", proxy.DATA_DODANIA, 80, true, null, proxy.DATA_DODANIA),
                new ColumnConfig("<b>Tytuł</b>", proxy.TYTUL, 160, true, null, proxy.TYTUL),
                new ColumnConfig("<b>Autor</b>", proxy.AUTOR, 120, true, null, proxy.AUTOR)
        // ,new ColumnConfig("", COL_AKCJE, 110, false, rendOdbierz, COL_AKCJE)
        };

        ColumnModel columnModel = new ColumnModel(columns);

        setColumnModel(columnModel);
    }

    /**
     * Inicjuje pasek z szukaniem
     */
    private void initSearchToolbar() {

        tbSzukanie = new Toolbar();
        ToolbarTextItem txt = new ToolbarTextItem("Szukaj w: ");

        cbSzukanePole = new ComboBox();

        final Store sSzukanePola = new SimpleStore(0, new String[] { "display", "value" }, new String[][] {
                new String[] { "Data dodania", proxy.DATA_DODANIA }, new String[] { "Tytuł", proxy.TYTUL } });
        sSzukanePola.load();

        cbSzukanePole.setStore(sSzukanePola);
        cbSzukanePole.setDisplayField("display");
        cbSzukanePole.setValueField("value");
        cbSzukanePole.setValue(proxy.DATA_DODANIA);
        cbSzukanePole.setForceSelection(true);
        cbSzukanePole.setMode(ComboBox.LOCAL);
        cbSzukanePole.setTriggerAction(ComboBox.ALL);
        cbSzukanePole.setLoadingText("Szukanie...");
        cbSzukanePole.setTypeAhead(true);
        cbSzukanePole.setSelectOnFocus(true);
        cbSzukanePole.setWidth(200);

        cbSzukanePole.setHideTrigger(false);
        tbSzukanie.addItem(txt);
        tbSzukanie.addSpacer();

        tbSzukanie.addField(cbSzukanePole);
        tbSzukanie.addSpacer();
        tfSzukanyTekst = new TextField();
        tfSzukanyTekst.setWidth(250);
        tfSzukanyTekst.setStyle("text-transform:uppercase");
        tbSzukanie.addField(tfSzukanyTekst);

        tbSzukanie.addSpacer();
        tbbSzukaj = new ToolbarButton("Szukaj");
        tbbCzysc = new ToolbarButton("Czyść");
        tbbFiltr = new ToolbarButton("Filtruj...");

        tbSzukanie.addButton(tbbSzukaj);
        tbSzukanie.addSpacer();
        tbSzukanie.addButton(tbbCzysc);
        tbSzukanie.addSeparator();
        tbSzukanie.addButton(tbbFiltr);
        tbSzukanie.addSeparator();

    }

    /**
     * Szukanie w bazie rekordów ograniczonych przez kryteria szukania podane przez użytkownika
     */
    private void search() {
        proxy.czyscSzukanie();
        proxy.setSzukanieFraza(tfSzukanyTekst.getValueAsString());
        proxy.setSzukanieKolumna(cbSzukanePole.getValueAsString());
        getStore().reload();
    }

    // /**
    // * Inicjuje menu do wyboru filtru
    // */
    // private void initMenu() {
    // menu = new Menu();
    // menu.setShadow(true);
    // menu.setMinWidth(10);
    //
    // addItem(ID_WSZYSTKIE, LBL_WSZYSTKIE, NewsyZarzadzanieGWTProxy.STS_WSZYSTKIE);
    // menu.addSeparator();
    // addItem(ID_DO_ODEBRANIA, LBL_DO_ODEBRANIA, NewsyZarzadzanieGWTProxy.STS_DO_ODEBRANIA);
    // addItem(ID_ODEBRANE, LBL_ODEBRANE, NewsyZarzadzanieGWTProxy.STS_ODEBRANY);
    // addItem(ID_ODRZUCONE, LBL_ODRZUCONE, NewsyZarzadzanieGWTProxy.STS_ODRZUCONY);
    //
    // }

    /**
     * Dodaje item do menu od filtrów
     * 
     * @param label
     *            do wyświetlenia
     * @param status
     *            do sql'a
     */
    private void addItem(String itemID, String label, final String status) {
        CheckItem item = new CheckItem(label);
        item.setId(itemID);
        item.setGroup(MENU_GROUP);
        if (itemID.equals(ID_DO_ODEBRANIA))
            item.setChecked(true);
        item.addListener(new CheckItemListenerAdapter() {
            public void onCheckChange(CheckItem item, boolean checked) {
                if (checked) {
                    setStatus(status);
                }
            }
        });
        menu.addItem(item);
    }

    /**
     * Dodaje item do menu filtrów.
     * 
     * @param label
     *            napisa w menu
     * @param status
     *            wartość do wybierania
     * 
     */
    private void addItem(String itemID, String label, int status) {
        addItem(itemID, label, String.valueOf(status));
    }

    /**
     * Ustawia status w proxy oraz przeładowuje stora grida.
     * 
     * @param item
     * @param status
     */
    private void setStatus(String status) {
        // proxy.setStatus(status);
        // jeśli będzie więcej parametrów to trzeba będzie to zmienić - zrezygnować z czyszczenia całej listy
        // proxy.czyscListeParametrow();
        getStore().reload();
    }

    /**
     * Inicjuje pasek ze stronnicowaniem na dole tabeli.
     */
    private void initPagingToolbar() {
        tbStronicowanie = new PagingToolbar(getStore());
        tbStronicowanie.setPageSize(20);
        tbStronicowanie.setDisplayInfo(true);

        tfWielkoscStrony = new TextField();
        tfWielkoscStrony.setValue(tbStronicowanie.getPageSize() + "");
        tfWielkoscStrony.setWidth(40);
        tfWielkoscStrony.setSelectOnFocus(true);

        tfWielkoscStrony.addListener(new TextFieldListenerAdapter() {
            public void onSpecialKey(Field field, EventObject e) {
                if (e.getKey() == EventObject.ENTER) {
                    int pageSize = 1;
                    if (Integer.parseInt(field.getValueAsString()) <= 0) {
                        tfWielkoscStrony.setValue("1");
                    } else {
                        pageSize = Integer.parseInt(field.getValueAsString());
                    }

                    tbStronicowanie.setPageSize(pageSize);
                    getStore().load(0, tbStronicowanie.getPageSize());
                }
            }
        });

        ToolTip toolTip = new ToolTip("Liczba rekordów na stronie");
        toolTip.applyTo(tfWielkoscStrony);

        tbStronicowanie.addField(tfWielkoscStrony);
    }

    /**
     * Czyści proxy, resetuje comboboxa, usuwa tekst szukany z texfielda, przeładowuje store'a
     */
    public void czysc() {
        proxy.czyscSzukanie();
        // proxy.czyscListeParametrow();
        // setStatus(String.valueOf(NewsyZarzadzanieGWTProxy.STS_DO_ODEBRANIA));
        ((CheckItem) menu.getItem(ID_DO_ODEBRANIA)).setChecked(true);
        cbSzukanePole.reset();
        tfSzukanyTekst.setValue(null);
    }

    /**
     * Dodaje listenery do pól, przycisków etc.
     */
    private void addListeners() {
        tbbSzukaj.addListener(new ButtonListenerAdapter() {
            public void onClick(Button button, EventObject e) {
                search();
            }
        });

        tfSzukanyTekst.addListener(new FieldListenerAdapter() {
            public void onSpecialKey(Field field, EventObject e) {
                if (e.getKey() == EventObject.ENTER) {
                    search();
                }
            }
        });

        tbbCzysc.addListener(new ButtonListenerAdapter() {
            public void onClick(Button button, EventObject e) {
                czysc();
            }
        });

        addGridCellListener(new GridCellListenerAdapter() {
            public void onCellClick(final GridPanel grid, final int rowIndex, int colindex, EventObject e) {
                if (grid.getColumnModel().getDataIndex(colindex).equals("szczegoly")
                        && e.getTarget(".szczegoly", 1) != null) {

                    Record rec = grid.getStore().getAt(rowIndex);
                    // final String nazwaPliku = rec.getAsString(NewsyZarzadzanieGWTProxy.PLIK);
                    final String adres = GWT.getModuleBaseURL();

                }

                else if (grid.getColumnModel().getDataIndex(colindex).equals(COL_AKCJE)
                        && e.getTarget(".odrzuc_dok", 1) != null) {

                } else if (grid.getColumnModel().getDataIndex(colindex).equals(COL_AKCJE)
                        && e.getTarget(".odbierz", 1) != null) {

                    Record rZaznaczony = grid.getStore().getRecordAt(rowIndex);

                }
            }
        });

    }

    /**
     * Zwraca wartość w komórce
     * 
     * @param record
     * @param columnName
     * @return
     */
    private String getCellValue(Record record, String columnName) {
        try {
            return record.getAsString(columnName);
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * Pobiera aktualnego sql'a, którego wyniki są zaprezentowane w tabeli.
     * 
     * @return
     */
    public String getSQL() {
        return proxy.getSQL();
    }

}