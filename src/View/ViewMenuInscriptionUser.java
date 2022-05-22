package View;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import Tools.Path;
import javafx.geometry.Insets;
import javafx.scene.control.DatePicker;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.util.StringConverter;
import Model.User;
import Tools.BackGround;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

public class ViewMenuInscriptionUser {

    private final Group root;
    private final ViewHandler vh;

    private Button  retourMP, buttonValidInscriptionUser, buttonListInscriptionUser, quitter;
    private TextField nom, prenom, email, adresse;
    private Text titre;
    private Label labnom, labprenom, labdatenaissance, labemail, labadresse;
    private DatePicker datenaissance;

    public ViewMenuInscriptionUser(Group root, ViewHandler vh){
        this.root = root;
        this.vh = vh;

        BackGround.initBackground(Path.fondMenu2);
        initTitre();
        initInput();
        initLabel();
        initButton();
    }

    public String getNom() {
        return nom.getText();
    }
    public String getPrenom() { return prenom.getText(); }
    public String getEmail() {
        return email.getText();
    }
    public String getAdresse() {
        return adresse.getText();
    }
    public LocalDate getDatenaissance(){ return datenaissance.getValue(); }

    public void setNom(String nom) {
        this.nom.setText(nom);
    }
    public void setPrenom(String prenom) {
        this.prenom.setText(prenom);
    }
    public void setEmail(String email) {
        this.email.setText(email);
    }
    public void setAdresse(String adresse) {
        this.adresse.setText(adresse);
    }

    private void initTitre() {
        titre = new Text("Formulaire d'inscription utilisateur");
        titre.setTranslateX(150);
        titre.setTranslateY(65);
        titre.setFill(Color.web("#696969"));
        titre.setFont(javafx.scene.text.Font.font(33));
        titre.setStyle("-fx-background-color: #696969; -fx-font-weight: bold;");
    }

    private void initButton(){
        retourMP = new Button("retour");
        retourMP.setTranslateX(180);
        retourMP.setTranslateY(500);
        retourMP.setMinWidth(140);
        retourMP.setStyle("-fx-background-color: #FFFFFF; -fx-border-color: #000000; -fx-border-radius: 3px; -fx-border-width: 1px; -fx-font-size: 15px; -fx-font-weight: bold;");
        retourMP.setOnMouseClicked(e->vh.afficherMenuPrincipal());

        buttonListInscriptionUser = new Button("Liste inscription");
        buttonListInscriptionUser.setTranslateX(330);
        buttonListInscriptionUser.setTranslateY(500);
        buttonListInscriptionUser.setMinWidth(140);
        buttonListInscriptionUser.setStyle("-fx-background-color: #FFFFFF; -fx-border-color: #000000; -fx-border-radius: 3px; -fx-border-width: 1px; -fx-font-size: 15px; -fx-font-weight: bold;");
        buttonListInscriptionUser.setOnMouseClicked(e->vh.afficherSelectBdd());

        buttonValidInscriptionUser = new Button("Valider inscription");
        buttonValidInscriptionUser.setTranslateX(480);
        buttonValidInscriptionUser.setTranslateY(500);
        buttonValidInscriptionUser.setStyle("-fx-background-color: #FFFFFF; -fx-border-color: #000000; -fx-border-radius: 3px; -fx-border-width: 1px; -fx-font-size: 15px; -fx-font-weight: bold;");
        buttonValidInscriptionUser.setOnMouseClicked(e->vh.afficherInserttBdd());

        quitter = new Button("X");
        quitter.setTranslateX(750);
        quitter.setTranslateY(10);
        quitter.setStyle("-fx-background-color: #808080; -fx-font-weight: bold;");
        quitter.setOnMouseClicked(e->System.exit(0));
    }

    private void initLabel(){
        labnom = new Label("Nom");
        labnom.setTranslateX(220);
        labnom.setTranslateY(181);
        labnom.setMinWidth(150);
        labnom.setTextFill(Color.web("black"));
        labnom.setStyle("-fx-font-weight: bold; -fx-alignment: CENTER;");
        labnom.setBackground(new Background(new BackgroundFill(Color.rgb(189,189,189, 1), new CornerRadii(5.0), new Insets(-5.0))));

        labprenom = new Label("Prénom");
        labprenom.setTranslateX(220);
        labprenom.setTranslateY(231);
        labprenom.setMinWidth(150);
        labprenom.setTextFill(Color.web("black"));
        labprenom.setStyle("-fx-font-weight: bold; -fx-alignment: CENTER;");
        labprenom.setBackground(new Background(new BackgroundFill(Color.rgb(189,189,189, 1), new CornerRadii(5.0), new Insets(-5.0))));

        labdatenaissance = new Label("Date de naissance");
        labdatenaissance.setTranslateX(220);
        labdatenaissance.setTranslateY(281);
        labdatenaissance.setMinWidth(150);
        labdatenaissance.setTextFill(Color.web("black"));
        labdatenaissance.setStyle("-fx-font-weight: bold; -fx-alignment: CENTER;");
        labdatenaissance.setBackground(new Background(new BackgroundFill(Color.rgb(189,189,189, 1), new CornerRadii(5.0), new Insets(-5.0))));

        labemail = new Label("Email");
        labemail.setTranslateX(220);
        labemail.setTranslateY(331);
        labemail.setMinWidth(150);
        labemail.setTextFill(Color.web("black"));
        labemail.setStyle("-fx-font-weight: bold; -fx-alignment: CENTER;");
        labemail.setBackground(new Background(new BackgroundFill(Color.rgb(189,189,189, 1), new CornerRadii(5.0), new Insets(-5.0))));

        labadresse = new Label("Adresse");
        labadresse.setTranslateX(220);
        labadresse.setTranslateY(381);
        labadresse.setMinWidth(150);
        labadresse.setTextFill(Color.web("black"));
        labadresse.setStyle("-fx-font-weight: bold; -fx-alignment: CENTER;");
        labadresse.setBackground(new Background(new BackgroundFill(Color.rgb(189,189,189, 1), new CornerRadii(5.0), new Insets(-5.0))));
    }

    private void initInput(){
        nom = new TextField("");
        prenom = new TextField("");
        email = new TextField("");
        adresse = new TextField("");
        datenaissance = new DatePicker();

        nom.setTranslateX(390);
        nom.setTranslateY(175);

        prenom.setTranslateX(390);
        prenom.setTranslateY(225);

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

        datenaissance.setPromptText("dd-MM-yyyy");
        datenaissance.setValue(LocalDate.now());
        datenaissance.setConverter(converter);
        datenaissance.setShowWeekNumbers(true);
        datenaissance.setTranslateX(390);
        datenaissance.setTranslateY(275);

        email.setTranslateX(390);
        email.setTranslateY(325);

        adresse.setTranslateX(390);
        adresse.setTranslateY(375);
    }

    public void setVueInscriptionUser() {
        root.getChildren().clear();
        root.getChildren().add(BackGround.getImgBg());
        root.getChildren().add(titre);
        root.getChildren().add(labnom);
        root.getChildren().add(labprenom);
        root.getChildren().add(labdatenaissance);
        root.getChildren().add(labemail);
        root.getChildren().add(labadresse);
        root.getChildren().add(nom);
        root.getChildren().add(prenom);
        root.getChildren().add(datenaissance);
        root.getChildren().add(email);
        root.getChildren().add(adresse);
        root.getChildren().add(retourMP);
        root.getChildren().add(buttonListInscriptionUser);
        root.getChildren().add(buttonValidInscriptionUser);
        root.getChildren().add(quitter);
    }

    public void setViewSelectUser(){
        User user = new User();
        user.selectUser();
    }

    public void setViewInsertUser(){
        User user = new User();
        user.insertUser(this);
    }
}
