package View;

import Model.Book;
import Tools.BackGround;
import Tools.Path;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.control.*;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.util.StringConverter;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class ViewMenuInscriptionBook {

    private final Group root;
    private final ViewHandler vh;
    private final Book book = new Book();

    private Button  retourMP, buttonValidEnregBook, buttonListBook, quitter;
    private TextField titreb, format, nbex, idauteur1;
    private Text titre;
    private Label ltitre, lformat, lnbex, lidauteur, lcreated_at;
    private DatePicker created_at;
    private ComboBox<String> idauteur;


    public ViewMenuInscriptionBook(Group root, ViewHandler vh){
        this.root = root;
        this.vh = vh;

        BackGround.initBackground(Path.fondMenu);
        initTitre();
        initInput();
        initLabel();
        initButton();
        setAuteurNom();
    }

    private void initTitre() {
        titre = new Text("Enregistrement du livre");
        titre.setTranslateX(200);
        titre.setTranslateY(70);
        titre.setFont(new javafx.scene.text.Font("Verdana", 32));
        titre.setFill(Color.web("#696969"));
        titre.setStyle("-fx-background-color: #696969; -fx-font-size: 33px; -fx-font-weight: bold;");
    }

    private void initButton(){
        retourMP = new Button("retour");
        retourMP.setTranslateX(220);
        retourMP.setTranslateY(500);
        retourMP.setMinWidth(140);
        retourMP.setOnMouseClicked(e->vh.afficherMenuPrincipal());
        retourMP.setStyle("-fx-background-color: #FFFFFF; -fx-border-color: #000000; -fx-border-radius: 3px; -fx-border-width: 1px; -fx-font-size: 15px; -fx-font-weight: bold;");

        buttonListBook = new Button("Liste des livres");
        buttonListBook.setTranslateX(370);
        buttonListBook.setTranslateY(500);
        buttonListBook.setMinWidth(140);
        buttonListBook.setStyle("-fx-background-color: #FFFFFF; -fx-border-color: #000000; -fx-border-radius: 3px; -fx-border-width: 1px; -fx-font-size: 15px; -fx-font-weight: bold;");
        buttonListBook.setOnMouseClicked(e->vh.afficherSelectbookBdd());

        buttonValidEnregBook = new Button("Valider enreg livre");
        buttonValidEnregBook.setTranslateX(520);
        buttonValidEnregBook.setTranslateY(500);
        buttonValidEnregBook.setMinWidth(140);
        buttonValidEnregBook.setStyle("-fx-background-color: #FFFFFF; -fx-border-color: #000000; -fx-border-radius: 3px; -fx-border-width: 1px; -fx-font-size: 15px; -fx-font-weight: bold;");
        buttonValidEnregBook.setOnMouseClicked(e->vh.afficherInsertbooktBdd());

        quitter = new Button("X");
        quitter.setTranslateX(750);
        quitter.setTranslateY(10);
        quitter.setStyle("-fx-background-color: #808080; -fx-font-weight: bold;");
        quitter.setOnMouseClicked(e->System.exit(0));

    }

    private void initLabel(){
        ltitre = new Label("Titre");
        ltitre.setTranslateX(220);
        ltitre.setTranslateY(181);
        ltitre.setMinWidth(150);
        ltitre.setTextFill(Color.web("black"));
        ltitre.setStyle("-fx-font-weight: bold; -fx-alignment: CENTER;");
        ltitre.setBackground(new Background(new BackgroundFill(Color.rgb(189,189,189, 1), new CornerRadii(5.0), new Insets(-5.0))));

        lformat = new Label("Format");
        lformat.setTranslateX(220);
        lformat.setTranslateY(231);
        lformat.setMinWidth(150);
        lformat.setTextFill(Color.web("black"));
        lformat.setStyle("-fx-font-weight: bold; -fx-alignment: CENTER;");
        lformat.setBackground(new Background(new BackgroundFill(Color.rgb(189,189,189, 1), new CornerRadii(5.0), new Insets(-5.0))));

        lnbex = new Label("nombre exemplaire");
        lnbex.setTranslateX(220);
        lnbex.setTranslateY(281);
        lnbex.setMinWidth(150);
        lnbex.setTextFill(Color.web("black"));
        lnbex.setStyle("-fx-font-weight: bold; -fx-alignment: CENTER;");
        lnbex.setBackground(new Background(new BackgroundFill(Color.rgb(189,189,189, 1), new CornerRadii(5.0), new Insets(-5.0))));

        lidauteur = new Label("auteur");
        lidauteur.setTranslateX(220);
        lidauteur.setTranslateY(331);
        lidauteur.setMinWidth(150);
        lidauteur.setTextFill(Color.web("black"));
        lidauteur.setStyle("-fx-font-weight: bold; -fx-alignment: CENTER;");
        lidauteur.setBackground(new Background(new BackgroundFill(Color.rgb(189,189,189, 1), new CornerRadii(5.0), new Insets(-5.0))));

        lcreated_at = new Label("Date de création");
        lcreated_at.setTranslateX(220);
        lcreated_at.setTranslateY(381);
        lcreated_at.setMinWidth(150);
        lcreated_at.setTextFill(Color.web("black"));
        lcreated_at.setStyle("-fx-font-weight: bold; -fx-alignment: CENTER;");
        lcreated_at.setBackground(new Background(new BackgroundFill(Color.rgb(189,189,189, 1), new CornerRadii(5.0), new Insets(-5.0))));
    }

    private void initInput(){
        titreb = new TextField("");
        format = new TextField("");
        nbex = new TextField("");

        idauteur1 = new TextField("");
        created_at = new DatePicker();

        titreb.setTranslateX(390);
        titreb.setTranslateY(175);
        titreb.setPrefWidth(270);

        format.setTranslateX(390);
        format.setTranslateY(225);
        format.setPrefWidth(270);

        nbex.setTranslateX(390);
        nbex.setTranslateY(275);
        nbex.setPrefWidth(270);

        idauteur = new ComboBox();
        idauteur.setPromptText("Auteur");
        idauteur.setTranslateX(390);
        idauteur.setTranslateY(325);
        idauteur.setMinWidth(270);
        idauteur.setStyle("-fx-font-weight: bold; -fx-alignment: CENTER;");

        created_at.setValue(LocalDate.now());
        created_at.setShowWeekNumbers(true);
        created_at.setTranslateX(390);
        created_at.setTranslateY(375);
        created_at.setPrefWidth(270);

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

        created_at.setConverter(converter);
        created_at.setPromptText("dd-MM-yyyy");
    }

    public void setVueInscription() {
        root.getChildren().clear();
        root.getChildren().add(BackGround.getImgBg());
        root.getChildren().add(titre);
        root.getChildren().add(ltitre);
        root.getChildren().add(lformat);
        root.getChildren().add(lnbex);
        root.getChildren().add(lidauteur);
        root.getChildren().add(lcreated_at);
        root.getChildren().add(titreb);
        root.getChildren().add(format);
        root.getChildren().add(nbex);
        root.getChildren().add(idauteur);
        root.getChildren().add(created_at);
        root.getChildren().add(retourMP);
        root.getChildren().add(buttonListBook);
        root.getChildren().add(buttonValidEnregBook);
        root.getChildren().add(quitter);
    }

    public String getTitreb() { return titreb.getText(); }

    public String getFormat() { return format.getText(); }

    public String getNbex() {   return nbex.getText();   }

    public String getIdauteur() {  return idauteur.getValue(); }

    public LocalDate getCreated_at(){ return created_at.getValue();  }

    public void setTitreb(String titreb) {
        this.titreb.setText(titreb);
    }

    public void setFormat(String format) {
        this.format.setText(format);
    }

    public void setNbex(String nbex) {
        this.nbex.setText(nbex);
    }

    public void setViewSelectBook(){
        Book book = new Book();
        book.selectBook();
    }

    public void setViewInsertBook(){
        Book book = new Book();
        book.insertBook(this);
    }

    public void setAuteurNom(){
        ObservableList<String> ob = FXCollections.observableArrayList(book.selectauteurBook(this));
        this.idauteur.setItems(ob);
        idauteur.setStyle("-fx-font-weight: bold; -fx-alignment: CENTER;");
    }
}
