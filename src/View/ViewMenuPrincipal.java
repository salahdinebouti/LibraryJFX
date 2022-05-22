package View;

import Controller.MenuPrincipalController;
import Tools.BackGround;
import Tools.Path;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

public class ViewMenuPrincipal {

    private final Group root;
    private final ViewHandler vh;
    private MenuPrincipalController mpc;

    private Button inscriptionuser, inscriptionbook, inscriptionauteur, emprunter, quitter;
    private Text titre;

    public ViewMenuPrincipal(Group root, ViewHandler vh) {
        this.root = root;
        this.vh = vh;
        this.mpc = new MenuPrincipalController( vh, this);

        initTitre();
        initButtons();
        BackGround.initBackground(Path.fondMenu);
    }

    public Button getinscriptionuser() {
        return inscriptionuser;
    }

    public Button getEmprunter() {
        return emprunter;
    }

    public Button getQuitter() {
        return quitter;
    }

    private void initTitre() {
        titre = new Text("Bibliothèque Issam - Salah & Bros. ");
        titre.setTranslateX(150);
        titre.setTranslateY(65);
        titre.setFill(Color.web("#696969"));
        titre.setFont(javafx.scene.text.Font.font(33));
        titre.setStyle("-fx-background-color: #696969; -fx-font-weight: bold;");
    }

    private void initButtons() {
        inscriptionuser = new Button("Inscription utilisateur");
        inscriptionuser.setTranslateX(250);
        inscriptionuser.setTranslateY(150);
        inscriptionuser.setMinWidth(350);
        inscriptionuser.setStyle("-fx-background-color: #FFFFFF; -fx-border-color: #000000; -fx-border-radius: 3px; -fx-border-width: 1px; -fx-font-size: 22px; -fx-font-weight: bold; ");
        inscriptionuser.setOnMouseClicked(e->vh.afficherInscriptionUser());

        inscriptionbook = new Button("Enregistrement d'un livre");
        inscriptionbook.setTranslateX(250);
        inscriptionbook.setTranslateY(250);
        inscriptionbook.setMinWidth(350);
        inscriptionbook.setStyle("-fx-background-color: #FFFFFF; -fx-border-color: #000000; -fx-border-radius: 3px; -fx-border-width: 1px; -fx-font-size: 22px; -fx-font-weight: bold;");
        inscriptionbook.setOnMouseClicked(e->{vh.afficherInscriptionBook();vh.afficherAuteurBook();});

        inscriptionauteur = new Button("Enregistrement d'auteur");
        inscriptionauteur.setTranslateX(250);
        inscriptionauteur.setTranslateY(350);
        inscriptionauteur.setMinWidth(350);
        inscriptionauteur.setStyle("-fx-background-color: #FFFFFF; -fx-border-color: #000000; -fx-border-radius: 3px; -fx-border-width: 1px; -fx-font-size: 22px; -fx-font-weight: bold;");
        inscriptionauteur.setOnMouseClicked(e->vh.afficherInscriptionAuteur());

        emprunter = new Button("Emprunter/Retourner un livre");
        emprunter.setTranslateX(250);
        emprunter.setTranslateY(450);
        emprunter.setMinWidth(350);
        emprunter.setStyle("-fx-background-color: #FFFFFF; -fx-border-color: #000000; -fx-border-radius: 3px; -fx-border-width: 1px; -fx-font-size: 22px; -fx-font-weight: bold;");
        emprunter.setOnMouseClicked(e->vh.afficherMenuEmprunter());

        quitter = new Button("X");
        quitter.setTranslateX(750);
        quitter.setTranslateY(10);
        quitter.setStyle("-fx-background-color: #808080; -fx-font-weight: bold;");
        quitter.setOnMouseClicked(e->System.exit(0));
    }

    public void setVueMenu() {
        root.getChildren().clear();
        // todo Etape 3) on met ici les éléments du menu à afficher.
        root.getChildren().add(BackGround.getImgBg());
        root.getChildren().add(titre);
        root.getChildren().add(inscriptionuser);
        root.getChildren().add(inscriptionauteur);
        root.getChildren().add(inscriptionbook);
        root.getChildren().add(emprunter);
        root.getChildren().add(quitter);
    }
}

