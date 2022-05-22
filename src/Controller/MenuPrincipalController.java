package Controller;

import View.ViewHandler;
import View.ViewMenuPrincipal;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;

public class MenuPrincipalController implements EventHandler<MouseEvent> {

    private final ViewHandler vh;
    private final ViewMenuPrincipal vmp;

    public MenuPrincipalController(ViewHandler vh, ViewMenuPrincipal vmp) {
        this.vh = vh;
        this.vmp = vmp;
    }

    @Override
    public void handle(MouseEvent event) {
        // Affiche
        if (event.getSource().equals(vmp.getEmprunter())) {
                vh.afficherMenuEmprunter();
        }
        if (event.getSource().equals(vmp.getinscriptionuser())) {
                vh.afficherInscriptionUser();
        }
        if (event.getSource().equals(vmp.getQuitter())) {
                vh.afficherMenuEmprunter();
        }
    }
}
