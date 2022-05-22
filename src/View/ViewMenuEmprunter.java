package View;

import Model.*;
import Tools.BackGround;
import Tools.Path;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javax.swing.*;
import java.awt.*;
import java.sql.*;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;

public class ViewMenuEmprunter {

    private final Group root;
    private final ViewHandler vh;
    private TextField inputSearchTitleBook, inputSearchAuthorBook, inputSearchUser;
    private Label labelSearchBookByTitle, labelSearchBookByAuthor, labelSearchUser;
    private Button retourMenuPrincipal, quitter, validEmprunt, validRetour, listeEmpruntRetour;
    private Text titre;
    private TableView<Book> tableBook;
    private TableView<User> tableUser;

    public ViewMenuEmprunter(Group root, ViewHandler vh) {
        this.root = root;
        this.vh = vh;

        initTitre();
        initButtons();
        initInput();
        initLabel();
        initTableBook();
        initTableUser();
        BackGround.initBackground(Path.fondMenu);
    }

    private void initTitre() {
        titre = new Text("Emprunt et retour");
        titre.setTranslateX(255);
        titre.setTranslateY(55);
        titre.setFont(new javafx.scene.text.Font("Verdana", 32));
        titre.setFill(Color.web("#696969"));
        titre.setStyle("-fx-background-color: #696969; -fx-font-size: 33px; -fx-font-weight: bold;");
    }

    private void initButtons() {
        retourMenuPrincipal = new Button("Retour");
        retourMenuPrincipal.setTranslateX(85);
        retourMenuPrincipal.setTranslateY(560);
        retourMenuPrincipal.setMinWidth(140);
        retourMenuPrincipal.setStyle("-fx-background-color: #FFFFFF; -fx-border-color: #000000; -fx-border-radius: 3px; -fx-border-width: 1px; -fx-font-size: 15px; -fx-font-weight: bold;");
        retourMenuPrincipal.setOnMouseClicked(e -> {
            vh.afficherMenuPrincipal();
        });

        validEmprunt =  new Button("Emprunter livre");
        validEmprunt.setTranslateX(240);
        validEmprunt.setTranslateY(560);
        validEmprunt.setMinWidth(140);
        validEmprunt.setStyle("-fx-background-color: #FFFFFF; -fx-border-color: #000000; -fx-border-radius: 3px; -fx-border-width: 1px; -fx-font-size: 15px; -fx-font-weight: bold;");

        validRetour = new Button("Retourner livre");
        validRetour.setTranslateX(390);
        validRetour.setTranslateY(560);
        validRetour.setMinWidth(140);
        validRetour.setStyle("-fx-background-color: #FFFFFF; -fx-border-color: #000000; -fx-border-radius: 3px; -fx-border-width: 1px; -fx-font-size: 15px; -fx-font-weight: bold;");

        listeEmpruntRetour = new Button("Liste Emprunt/Retour");
        listeEmpruntRetour.setTranslateX(550);
        listeEmpruntRetour.setTranslateY(560);
        listeEmpruntRetour.setMinWidth(140);
        listeEmpruntRetour.setStyle("-fx-background-color: #FFFFFF; -fx-border-color: #000000; -fx-border-radius: 3px; -fx-border-width: 1px; -fx-font-size: 15px; -fx-font-weight: bold;");
        listeEmpruntRetour.setOnMouseClicked(e->{
            this.setViewSelectBorrow();
        });

        quitter = new Button("X");
        quitter.setTranslateX(750);
        quitter.setTranslateY(10);
        quitter.setStyle("-fx-background-color: #808080; -fx-font-weight: bold;");
        quitter.setOnMouseClicked(e->System.exit(0));
    }

    private void initInput(){
        inputSearchTitleBook = new TextField("");
        inputSearchTitleBook.setTranslateX(175);
        inputSearchTitleBook.setTranslateY(85);

        inputSearchAuthorBook = new TextField("");
        inputSearchAuthorBook.setTranslateX(505);
        inputSearchAuthorBook.setTranslateY(85);

        inputSearchUser = new TextField("");
        inputSearchUser.setTranslateX(370);
        inputSearchUser.setTranslateY(310);
    }

    private void initLabel(){
        labelSearchBookByTitle = new Label(" Titre ");
        labelSearchBookByTitle.setTranslateX(120);
        labelSearchBookByTitle.setTranslateY(90);
        labelSearchBookByTitle.setTextFill(Color.web("black"));
        labelSearchBookByTitle.setStyle("-fx-font-weight: bold;");
        labelSearchBookByTitle.setBackground(new Background(new BackgroundFill(Color.rgb(189,189,189, 1), new CornerRadii(5.0), new Insets(-5.0))));

        labelSearchBookByAuthor = new Label(" Auteur ");
        labelSearchBookByAuthor.setTranslateX(430);
        labelSearchBookByAuthor.setTranslateY(90);
        labelSearchBookByAuthor.setTextFill(Color.web("black"));
        labelSearchBookByAuthor.setStyle("-fx-font-weight: bold;");
        labelSearchBookByAuthor.setBackground(new Background(new BackgroundFill(Color.rgb(189,189,189, 1), new CornerRadii(5.0), new Insets(-5.0))));

        labelSearchUser = new Label("Recherche utilisateur");
        labelSearchUser.setTranslateX(200);
        labelSearchUser.setTranslateY(315);
        labelSearchUser.setTextFill(Color.web("black"));
        labelSearchUser.setStyle("-fx-font-weight: bold;");
        labelSearchUser.setBackground(new Background(new BackgroundFill(Color.rgb(189,189,189, 1), new CornerRadii(5.0), new Insets(-5.0))));
    }

    public void setVueEmprunter() {
        root.getChildren().clear();
        root.setAutoSizeChildren(true);
        root.getChildren().add(BackGround.getImgBg());
        root.getChildren().add(titre);
        root.getChildren().add(labelSearchBookByTitle);
        root.getChildren().add(inputSearchTitleBook);
        root.getChildren().add(labelSearchBookByAuthor);
        root.getChildren().add(inputSearchAuthorBook);
        root.getChildren().add(labelSearchUser);
        root.getChildren().add(inputSearchUser);
        root.getChildren().add(retourMenuPrincipal);
        root.getChildren().add(quitter);
        root.getChildren().add(tableBook);
        root.getChildren().add(tableUser);
        root.getChildren().add(validEmprunt);
        root.getChildren().add(validRetour);
        root.getChildren().add(listeEmpruntRetour);
    }

    public void initTableBook(){
        getBookList();
        getUserList();
        tableBook = new TableView<>();

        TableColumn<Book, String> titreCol = new TableColumn<>("Titre");
        TableColumn<Book, String> formatCol = new TableColumn<>("Format");
        TableColumn<Book, Integer> nbexCol = new TableColumn<>("expl. disponible");
        TableColumn<Book, Integer> nbexTotalCol = new TableColumn<>("expl. total");
        TableColumn<Book, String> auteurCol = new TableColumn<>("Auteur");

        tableBook.setTranslateX(115);
        tableBook.setTranslateY(130);
        tableBook.setMaxHeight(150);
        tableBook.setMinWidth(580);

        titreCol.setCellValueFactory(new PropertyValueFactory<>("titre"));
        formatCol.setCellValueFactory(new PropertyValueFactory<>("format"));
        nbexCol.setCellValueFactory(new PropertyValueFactory<>("nbex"));
        nbexTotalCol.setCellValueFactory(new PropertyValueFactory<>("nbexTotal"));
        auteurCol.setCellValueFactory(new PropertyValueFactory<>("fullnameAuteur"));

        titreCol.setStyle("-fx-alignment: CENTER");
        formatCol.setStyle("-fx-alignment: CENTER");
        nbexCol.setStyle("-fx-alignment: CENTER");
        nbexTotalCol.setStyle("-fx-alignment: CENTER");
        auteurCol.setStyle("-fx-alignment: CENTER");

        // Filtrage descendant
        titreCol.setSortType(TableColumn.SortType.DESCENDING);
        formatCol.setSortType(TableColumn.SortType.DESCENDING);
        nbexCol.setSortType(TableColumn.SortType.DESCENDING);
        nbexTotalCol.setSortType(TableColumn.SortType.DESCENDING);
        auteurCol.setSortType(TableColumn.SortType.DESCENDING);

        // Liste des livres observables
        ObservableList<Book> list = getBookList();

        FilteredList<Book> filteredList = new FilteredList<>(list, b->true);

        inputSearchTitleBook.textProperty().addListener((observable, oldValue, newValue)->{
            filteredList.setPredicate(book ->{

                if(newValue == null || newValue.isEmpty()) {
                    return true;
                }

                String lowerCaseFilter = newValue.toLowerCase();

                return book.getTitre().toLowerCase().contains(lowerCaseFilter);
            });
        });

        inputSearchAuthorBook.textProperty().addListener((observable, oldValue, newValue)->{
            filteredList.setPredicate(book ->{

                if(newValue == null || newValue.isEmpty()) {
                    return true;
                }

                String lowerCaseFilter = newValue.toLowerCase();

                return book.getFullnameAuteur().toLowerCase().contains(lowerCaseFilter);
            });
        });

        SortedList<Book> sortedList = new SortedList<>(filteredList);

        sortedList.comparatorProperty().bind(tableBook.comparatorProperty());

        tableBook.setItems(sortedList);
        tableBook.getColumns().addAll(titreCol, formatCol, nbexCol, nbexTotalCol, auteurCol);

        validEmprunt.setOnMouseClicked(el-> {

        User user = tableUser.getSelectionModel().getSelectedItem();
        Book book = tableBook.getSelectionModel().getSelectedItem();
        Borrow borrow = new Borrow();

            LocalDate today = LocalDate.now();
            LocalDate twoWeeksLater = today.plusDays(15);

            Period period = Period.between(today, twoWeeksLater);

            if(book == null || user == null){
                JLabel label = new JLabel("Selectionnez un utilisateur ET un livre pour valider un emprunt !!!");
                label.setFont(new Font("Arial", Font.BOLD, 18));
                JOptionPane.showMessageDialog(null,label,"ERROR",JOptionPane.WARNING_MESSAGE);
            }
            else{
                try {
                    borrow.setId_book(book.getId_book());
                    borrow.setId_user(user.getId_user());
                    borrow.setBorrow_date(today);
                    borrow.setBorrow_return_date(twoWeeksLater);
                    borrow.setDuree(period.getDays());
                    borrow.setRetourOk("En attente");

                    DatabaseConnection conn = new DatabaseConnection();
                    Connection connectDB = conn.getConnection();

                    PreparedStatement statementBook = connectDB.prepareStatement("UPDATE BOOK SET nbex = ? WHERE id_book = ? AND nbex > 0 ");
                    statementBook.setInt(1,book.getNbex() - 1);
                    statementBook.setInt(2, book.getId_book());

                    int statusBook = statementBook.executeUpdate();

                    System.out.println(statementBook);
                    if(statusBook != 1) {
                        JLabel label = new JLabel(book.getTitre() + " n'est pas disponible (nbex : " + book.getNbex() + "/"+ book.getNbexTotal() + ")");
                        label.setFont(new Font("Arial", Font.BOLD, 18));
                        JOptionPane.showMessageDialog(null,label,"ERROR",JOptionPane.WARNING_MESSAGE);
                    }
                    else {
                        PreparedStatement statement = connectDB.prepareStatement("INSERT INTO borrow (id_user, id_book, borrow_date, borrow_return_date, duree, etat_retour) VALUES (?,?,?,?,?,?)");

                        statement.setInt(1, borrow.getId_user());
                        statement.setInt(2, borrow.getId_book());
                        statement.setDate(3, Date.valueOf(borrow.getBorrow_date()));
                        statement.setDate(4, Date.valueOf(borrow.getBorrow_return_date()));
                        statement.setInt(5, borrow.getDuree());
                        statement.setString(6, borrow.getRetourOk());

                        int status = statement.executeUpdate();

                        if (status == 1) {
                            tableBook.getSelectionModel().clearSelection();
                            tableUser.getSelectionModel().clearSelection();
                            JLabel label = new JLabel(book.getTitre() + " emprunté par " + user.getFname() + " " + user.getLname() + " date de retour prévue le : " + borrow.getBorrow_return_date() + ".");
                            label.setFont(new Font("Arial", Font.BOLD, 18));

                            JOptionPane.showMessageDialog(null,label,"SUCCESS",JOptionPane.INFORMATION_MESSAGE);
                            this.initTableBook();
                            this.initTableUser();
                            this.setVueEmprunter();
                        }
                        else {
                            JLabel label = new JLabel("Échec de l'emprunt !!!");
                            label.setFont(new Font("Arial", Font.BOLD, 18));
                            JOptionPane.showMessageDialog(null,label,"ERROR",JOptionPane.WARNING_MESSAGE);
                        }
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        });

        validRetour.setOnMouseClicked(el-> {
            String[][] result ;

            getBookList();
            getUserList();

            User user2 = tableUser.getSelectionModel().getSelectedItem();
            Book book2 = tableBook.getSelectionModel().getSelectedItem();
            Borrow borrow = new Borrow();

            if(book2 == null || user2 == null){
                JLabel label = new JLabel("Selectionnez un utilisateur ET un livre pour valider un emprunt !!!");
                label.setFont(new Font("Arial", Font.BOLD, 18));
                JOptionPane.showMessageDialog(null,label,"ERROR",JOptionPane.WARNING_MESSAGE);
            } else {
                try {
                    DatabaseConnection conn = new DatabaseConnection();
                    Connection connectDB = conn.getConnection();

                    String connectQuery = "SELECT B.id_book, A.id_user, A.id_borrow, A.borrow_date, A.borrow_return_date, A.duree, A.etat_retour  FROM borrow AS A, book AS B WHERE A.id_user = "+ user2.getId_user() +" AND A.id_book = " + book2.getId_book() + " AND B.nbex < B.nbex_total";
                    Statement statement =  connectDB.createStatement();

                    ResultSet queryOutput = statement.executeQuery(connectQuery);

                    int n = 0;
                    while (queryOutput.next()) n++;

                    result = new String[n][7];

                    int i = 0;
                    queryOutput = statement.executeQuery(connectQuery);

                    while (queryOutput.next()) {
                        result[i][0] = queryOutput.getString("id_borrow");
                        result[i][1] = queryOutput.getString("id_user");
                        result[i][2] = queryOutput.getString("id_book");
                        result[i][3] = queryOutput.getString("borrow_date");
                        result[i][4] = queryOutput.getString("borrow_return_date");
                        result[i][5] = queryOutput.getString("duree");
                        result[i][6] = queryOutput.getString("etat_retour");
                        i++;
                    }

                    borrow.setId_user(user2.getId_user());
                    borrow.setId_book(book2.getId_book());
                    borrow.setRetourOk("Retour OK !");

                    PreparedStatement statement2 = connectDB.prepareStatement("UPDATE borrow SET etat_retour = ? WHERE id_user = ? AND id_book = ? AND etat_retour <> \"Retour OK !\" ");

                    statement2.setString(1,borrow.getRetourOk());
                    statement2.setInt(2, borrow.getId_user());
                    statement2.setInt(3, borrow.getId_book());

                    int status = statement2.executeUpdate();

                    if (status > 0) {
                        tableBook.getSelectionModel().clearSelection();
                        tableUser.getSelectionModel().clearSelection();

                        JLabel label1 = new JLabel(book2.getTitre() + " retourné par " + user2.getFname() + " " + user2.getLname() + " avec " + borrow.getDuree() + " jour" + (borrow.getDuree() > 1 ? "s" : "") + " de retard .");
                        label1.setFont(new Font("Arial", Font.BOLD, 18));
                        JOptionPane.showMessageDialog(null,label1,"SUCCESS",JOptionPane.INFORMATION_MESSAGE);

                        this.initTableBook();
                        this.initTableUser();
                        this.setVueEmprunter();

                        PreparedStatement statementBookRetour = connectDB.prepareStatement("UPDATE BOOK SET nbex = ? WHERE id_book = ? AND nbex < nbex_total ");
                        statementBookRetour.setInt(1,book2.getNbex() + 1);
                        statementBookRetour.setInt(2, book2.getId_book());

                        int statusBookRetour = statementBookRetour.executeUpdate();

                        if (statusBookRetour == 1) {
                            tableBook.getSelectionModel().clearSelection();
                            tableUser.getSelectionModel().clearSelection();
                            JLabel label = new JLabel("\"" + book2.getTitre() + "\" exemplaires disponibles "+ book2.getNbex() + "/" + book2.getNbexTotal() + " .");
                            label.setFont(new Font("Arial", Font.BOLD, 18));
                            JOptionPane.showMessageDialog(null,label,"SUCCESS",JOptionPane.INFORMATION_MESSAGE);

                            this.initTableBook();
                            this.initTableUser();
                            this.setVueEmprunter();
                        } else {
                            JLabel label = new JLabel("Retour impossible : " + book2.getTitre() + " : " + book2.getNbex() + "/" + book2.getNbexTotal() + " .");
                            label.setFont(new Font("Arial", Font.BOLD, 18));
                            JOptionPane.showMessageDialog(null,label,"ERROR",JOptionPane.WARNING_MESSAGE);
                        }
                    } else {
                        JLabel label2 = new JLabel("Emprunt inexistant !!!");
                        label2.setFont(new Font("Arial", Font.BOLD, 18));
                        JOptionPane.showMessageDialog(null,label2,"ERROR",JOptionPane.WARNING_MESSAGE);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public void initTableUser(){
        tableUser = new TableView<>();

        TableColumn<User, String> nomCol = new TableColumn<>("Nom");
        TableColumn<User, String> prenomCol = new TableColumn<>("Prénom");
        TableColumn<User, String> emailCol = new TableColumn<>("Email");
        TableColumn<User, String> dateNaissanceCol = new TableColumn<>("Date de naissance");

        tableUser.setTranslateX(85);
        tableUser.setTranslateY(350);
        tableUser.setMaxHeight(160);
        tableUser.setMinWidth(640);

        nomCol.setCellValueFactory(new PropertyValueFactory<>("lname"));
        prenomCol.setCellValueFactory(new PropertyValueFactory<>("fname"));
        emailCol.setCellValueFactory(new PropertyValueFactory<>("email"));
        dateNaissanceCol.setCellValueFactory(new PropertyValueFactory<>("datenaissance"));

        nomCol.setStyle("-fx-alignment: CENTER");
        prenomCol.setStyle("-fx-alignment: CENTER");
        emailCol.setStyle("-fx-alignment: CENTER");
        dateNaissanceCol.setStyle("-fx-alignment: CENTER");

        nomCol.setSortType(TableColumn.SortType.DESCENDING);
        prenomCol.setSortType(TableColumn.SortType.DESCENDING);
        emailCol.setSortType(TableColumn.SortType.DESCENDING);
        dateNaissanceCol.setSortType(TableColumn.SortType.DESCENDING);

        ObservableList<User> listUser = getUserList();

        FilteredList<User> userFilteredList = new FilteredList<>(listUser, b->true);

        inputSearchUser.textProperty().addListener((observable, oldValue, newValue)->{
            userFilteredList.setPredicate(user ->{

                if(newValue == null || newValue.isEmpty()) {
                    return true;
                }

                String lowerCaseFilter = newValue.toLowerCase();

                return user.getLname().toLowerCase().contains(lowerCaseFilter);
            });
        });
        SortedList<User> sortedList = new SortedList<>(userFilteredList);

        sortedList.comparatorProperty().bind(tableUser.comparatorProperty());

        tableUser.setItems(sortedList);
        tableUser.getColumns().addAll(nomCol, prenomCol, emailCol, dateNaissanceCol);
    }

    public void setViewSelectBorrow(){
        Borrow borrow = new Borrow();
        borrow.selectBorrow();
    }

    private ObservableList<Book> getBookList(){
        DatabaseConnection conn = new DatabaseConnection();
        Connection connectDB = conn.getConnection();
        String connectQuery = "SELECT B.titre, B.format, B.nbex, B.nbex_total, A.nom, A.prenom, B.created_at, id_book FROM book B, auteur A WHERE B.id_auteur = A.id_auteur";
        ObservableList<Book> list = FXCollections.observableArrayList();

        try{
            Statement statement = connectDB.createStatement();
            ResultSet queryOutput = statement.executeQuery(connectQuery);

            while(queryOutput.next()){

                Book book = new Book();
                book.setTitre(queryOutput.getString("titre"));
                book.setFormat(queryOutput.getString("format"));
                book.setNbex(Integer.parseInt(queryOutput.getString("nbex")));
                book.setNbexTotal(Integer.parseInt(queryOutput.getString("nbex_total")));
                book.setFullnameAuteur(queryOutput.getString("A.nom"), queryOutput.getString("A.prenom"));
                book.setId_book(Integer.parseInt(queryOutput.getString("id_book")));
                list.add(book);

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    private ObservableList<User> getUserList(){

        DatabaseConnection conn = new DatabaseConnection();
        Connection connectDB = conn.getConnection();
        String connectQuery = "SELECT * FROM user";
        ObservableList<User> listUser = FXCollections.observableArrayList();

        try{
            Statement statement = connectDB.createStatement();
            ResultSet queryOutput = statement.executeQuery(connectQuery);

            while(queryOutput.next()){
                System.out.println(queryOutput.getString("datenaissance"));

                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                LocalDate localDate = LocalDate.parse(queryOutput.getString("datenaissance"),formatter);

                User user = new User();
                user.setLname(queryOutput.getString("lname"));
                user.setFname(queryOutput.getString("fname"));
                user.setEmail(queryOutput.getString("email"));
                user.setId_user(Integer.parseInt(queryOutput.getString("id_user")));
                user.setDatenaissance(localDate);
                listUser.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listUser;
    }
}