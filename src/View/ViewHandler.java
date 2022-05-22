package View;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;


public class ViewHandler extends Application {

    private ViewMenuPrincipal mp;
    private ViewMenuEmprunter me;
    private ViewMenuInscriptionUser mi;
    private ViewMenuInscriptionAuteur mia;
    private ViewMenuInscriptionBook mib;

    @Override
    public void start(Stage stage) {
        Group root = new Group();
        Scene scene = new Scene(root,800,600);

        me = new ViewMenuEmprunter(root, this);
        mp = new ViewMenuPrincipal(root, this);
        mi = new ViewMenuInscriptionUser(root,this);
        mia = new ViewMenuInscriptionAuteur(root,this);
        mib = new ViewMenuInscriptionBook(root,this);

        stage.setScene(scene);
        stage.setFullScreenExitHint("");
        stage.setResizable(true);
        stage.initStyle(StageStyle.TRANSPARENT);
        stage.setWidth(800);
        stage.setHeight(650);
        stage.setFullScreen(false);
        stage.show();

        afficherMenuPrincipal();
    }

    public void afficherMenuPrincipal() {
        mp.setVueMenu();
    }

    public void afficherMenuEmprunter() {
        me.setVueEmprunter();
    }

    public void afficherInscriptionUser() {mi.setVueInscriptionUser();}

    public void afficherInscriptionAuteur() {mia.setVueInscription();}

    public void afficherInscriptionBook() {mib.setVueInscription();}

    public void afficherAuteurBook() {mib.setAuteurNom();}

    public void afficherSelectBdd(){ mi.setViewSelectUser();    }

    public void afficherInserttBdd(){
        mi.setViewInsertUser();
    }

    public void afficherSelectauteurBdd(){ mia.setViewSelectAuteur();}

    public void afficherInsertauteurtBdd(){mia.setViewInsertAuteur();}

    public void afficherSelectbookBdd(){ mib.setViewSelectBook();}

    public void afficherInsertbooktBdd(){mib.setViewInsertBook();}
}