package pl.baranski.ozpn.client;

import pl.baranski.ozpn.client.main.LogPanel;
import pl.baranski.ozpn.client.main.Logowanie;
import pl.baranski.ozpn.client.main.MainPanel;
import pl.baranski.ozpn.client.main.NewsPanel;
import pl.baranski.ozpn.client.uzytkownicy.PanelUzytkownika;
import pl.baranski.ozpn.client.zarzadzanie.newsy.NewsyZarzadzanie;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.RootPanel;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class OZPN implements EntryPoint {

    private final OzpnServiceAsync ozpnService = GWT.create(OzpnService.class);

    private MainPanel mainPanel;

    private static OZPN instance;
    private LogPanel logPanel;
    private PanelUzytkownika panelUzytkownika;

    /**
     * This is the entry point method.
     */
    public void onModuleLoad() {
        instance = this;

        RootPanel.get("form_logowanie").add(logPanel = new LogPanel());

        RootPanel.get("columnA_3columns").add(mainPanel = new MainPanel());
        mainPanel.showPanel(new NewsPanel());
        logPanel.showPanel(new Logowanie());

        final Anchor newsyAnchor = new Anchor("Newsy");
        newsyAnchor.addClickHandler(new ClickHandler() {

            public void onClick(ClickEvent event) {

                mainPanel.showPanel(new NewsPanel());
                newsyAnchor.setStyleName("active");

            }
        });

        Anchor zarzadzanieAnchor = new Anchor("Zarządzanie");
        zarzadzanieAnchor.addClickHandler(new ClickHandler() {

            public void onClick(ClickEvent event) {
                mainPanel.showPanel(new NewsyZarzadzanie());
            }
        });

        RootPanel.get("link1").add(newsyAnchor);
        // RootPanel.get("link2").add(zarzadzanieAnchor);
    }

    /**
     * Pobiera wartość pola <code>ozpnService</code>.
     * 
     * @return the ozpnService
     */
    public OzpnServiceAsync getOzpnService() {
        return ozpnService;
    }

    /**
     * Pobiera wartość pola <code>instance</code>.
     * 
     * @return the instance
     */
    public static OZPN getInstance() {
        return instance;
    }

    /**
     * Pobiera wartość pola <code>contentPanel</code>.
     * 
     * @return the contentPanel
     */
    public MainPanel getMainPanel() {
        return mainPanel;
    }

    /**
     * Pobiera wartość pola <code>logowanie</code>.
     * 
     * @return the logowanie
     */
    public LogPanel getLogowanie() {
        return logPanel;
    }

    /**
     * Pobiera wartość pola <code>panelUzytkownika</code>.
     * 
     * @return the panelUzytkownika
     */
    public PanelUzytkownika getPanelUzytkownika() {
        if (panelUzytkownika == null) {
            panelUzytkownika = new PanelUzytkownika();
        }
        return panelUzytkownika;
    }
}
