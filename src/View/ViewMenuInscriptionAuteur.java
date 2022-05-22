package View;

import Model.Auteur;
import Tools.BackGround;
import Tools.Path;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.util.StringConverter;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class ViewMenuInscriptionAuteur {

    private final Group root;
    private final ViewHandler vh;

    private Button  retourMP, buttonValidInscriptionAuthor, buttonListAuthor, quitter;
    private TextField nom, prenom ;
    private Text titre;
    private Label labnom, labprenom, labdatenaissance;
    private DatePicker datenaissance;

    public ViewMenuInscriptionAuteur(Group root, ViewHandler vh){
        this.root = root;
        this.vh = vh;

        BackGround.initBackground(Path.fondMenu);
        initTitre();
        initInput();
        initLabel();
        initButton();
    }

    public String getPrenom() { return prenom.getText(); }
    public String getNom() {
        return nom.getText();
    }
    public LocalDate getDatenaissance(){
        return datenaissance.getValue();
    }
    public void setNom(String nom) {
        this.nom.setText(nom);
    }
    public void setPrenom(String prenom) {
        this.prenom.setText(prenom);
    }

    private void initTitre() {
        titre = new Text("Inscription d'auteur");
        titre.setTranslateX(200);
        titre.setTranslateY(70);
        titre.setFont(new javafx.scene.text.Font("Verdana", 32));
        titre.setFill(Color.web("#696969"));
        titre.setStyle("-fx-background-color: #696969; -fx-font-size: 33px; -fx-font-weight: bold;");
    }

    private void initButton(){
        retourMP = new Button("retour");
        retourMP.setTranslateX(180);
        retourMP.setTranslateY(500);
        retourMP.setMinWidth(140);
        retourMP.setStyle("-fx-background-color: #FFFFFF; -fx-border-color: #000000; -fx-border-radius: 3px; -fx-border-width: 1px; -fx-font-size: 15px; -fx-font-weight: bold;");
        retourMP.setOnMouseClicked(e->vh.afficherMenuPrincipal());

        buttonListAuthor = new Button("Liste des auteurs");
        buttonListAuthor.setTranslateX(330);
        buttonListAuthor.setTranslateY(500);
        buttonListAuthor.setMinWidth(140);
        buttonListAuthor.setStyle("-fx-background-color: #FFFFFF; -fx-border-color: #000000; -fx-border-radius: 3px; -fx-border-width: 1px; -fx-font-size: 15px; -fx-font-weight: bold;");
        buttonListAuthor.setOnMouseClicked(e->vh.afficherSelectauteurBdd());

        buttonValidInscriptionAuthor = new Button("Enreg auteur");
        buttonValidInscriptionAuthor.setTranslateX(480);
        buttonValidInscriptionAuthor.setTranslateY(500);
        buttonValidInscriptionAuthor.setMinWidth(140);
        buttonValidInscriptionAuthor.setStyle("-fx-background-color: #FFFFFF; -fx-border-color: #000000; -fx-border-radius: 3px; -fx-border-width: 1px; -fx-font-size: 15px; -fx-font-weight: bold;");
        buttonValidInscriptionAuthor.setOnMouseClicked(e->vh.afficherInsertauteurtBdd());

        quitter = new Button("X");
        quitter.setTranslateX(750);
        quitter.setTranslateY(10);
        quitter.setStyle("-fx-background-color: #808080; -fx-font-weight: bold;");
        quitter.setOnMouseClicked(e->System.exit(0));
    }

    private void initLabel(){
        labnom = new Label("nom");
        labnom.setTranslateX(220);
        labnom.setTranslateY(201);
        labnom.setMinWidth(150);
        labnom.setTextFill(Color.web("black"));
        labnom.setStyle("-fx-font-weight: bold; -fx-alignment: CENTER;");
        labnom.setBackground(new Background(new BackgroundFill(Color.rgb(189,189,189, 1), new CornerRadii(5.0), new Insets(-5.0))));

        labprenom = new Label("prenom");
        labprenom.setTranslateX(220);
        labprenom.setTranslateY(251);
        labprenom.setMinWidth(150);
        labprenom.setTextFill(Color.web("black"));
        labprenom.setStyle("-fx-font-weight: bold; -fx-alignment: CENTER;");
        labprenom.setBackground(new Background(new BackgroundFill(Color.rgb(189,189,189, 1), new CornerRadii(5.0), new Insets(-5.0))));

        labdatenaissance = new Label("date de naissance");
        labdatenaissance.setTranslateX(220);
        labdatenaissance.setTranslateY(301);
        labdatenaissance.setMinWidth(150);
        labdatenaissance.setTextFill(Color.web("black"));
        labdatenaissance.setStyle("-fx-font-weight: bold; -fx-alignment: CENTER;");
        labdatenaissance.setBackground(new Background(new BackgroundFill(Color.rgb(189,189,189, 1), new CornerRadii(5.0), new Insets(-5.0))));
    }

    private void initInput(){
        nom = new TextField("");
        prenom = new TextField("");
        datenaissance = new DatePicker();

        nom.setTranslateX(390);
        nom.setTranslateY(195);

        prenom.setTranslateX(390);
        prenom.setTranslateY(245);

        datenaissance.setValue(LocalDate.now());
        datenaissance.setShowWeekNumbers(true);
        datenaissance.setTranslateX(390);
        datenaissance.setTranslateY(295);

        StringConverter<LocalDate> converter = new StringConverter<LocalDate>() {
            final DateTimeFormatter dateFormatter =
                    DateTimeFormatter.ofPattern("dd-MM-yyyy");

            @Override
            public String toString(LocalDate date) {
                if (date != null) {
                    return dateFormatter.format(date);
                } else {
                    return "";
                }
            }
            @Override
            public LocalDate fromString(String string) {
                if (string != null && !string.isEmpty()) {
                    return LocalDate.parse(string, dateFormatter);
                } else {
                    return null;
                }
            }
        };

        datenaissance.setConverter(converter);
        datenaissance.setPromptText("dd-MM-yyyy");
    }

    public void setVueInscription() {
        root.getChildren().clear();
        root.getChildren().add(BackGround.getImgBg());
        root.getChildren().add(titre);
        root.getChildren().add(labnom);
        root.getChildren().add(labprenom);
        root.getChildren().add(labdatenaissance);
        root.getChildren().add(nom);
        root.getChildren().add(prenom);
        root.getChildren().add(datenaissance);
        root.getChildren().add(retourMP);
        root.getChildren().add(buttonListAuthor);
        root.getChildren().add(buttonValidInscriptionAuthor);
        root.getChildren().add(quitter);
    }

    public void setViewSelectAuteur(){
        Auteur auteur = new Auteur();
        auteur.selectAuteur();
    }

    public void setViewInsertAuteur(){
        Auteur auteur = new Auteur();
        auteur.insertAuteur(this);
    }
}
