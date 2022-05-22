package Model;

import View.ViewMenuInscriptionBook;
import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;
import java.sql.*;
import java.time.LocalDate;

public class Book {
    private int id_book;
    private String titre;
    private String format;
    private int nbex;
    private int nbexTotal;
    private String fullnameAuteur;

    public Book() {
    }

    public int getId_book() {
        return id_book;
    }

    public void setId_book(int id_book) {
        this.id_book = id_book;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    public int getNbex() {
        return nbex;
    }

    public void setNbex(int nbex) {
        this.nbex = nbex;
    }


    public String getFullnameAuteur() {
        return fullnameAuteur;
    }

    public void setFullnameAuteur(String nomAuteur, String prenomAuteur) {
        this.fullnameAuteur = nomAuteur + " " + prenomAuteur;
    }

    public int getNbexTotal() {
        return nbexTotal;
    }

    public void setNbexTotal(int nbexTotal) {
        this.nbexTotal = nbexTotal;
    }

    public void selectBook(){
        String[][] result ;

        DatabaseConnection conn = new DatabaseConnection();
        Connection connectDB = conn.getConnection();
        String connectQuery = "select B.titre, B.format, B.nbex, B.nbex_total, CONCAT(A.nom, ' ', A.prenom) AS fullname, B.created_at, B.id_book from book B, auteur A where B.id_auteur = A.id_auteur";
        String[] column = {"titre","format","nb ex","nb. ex. tot.","auteur","created_at"};

        try{
            Statement statement = connectDB.createStatement();
            ResultSet queryOutput = statement.executeQuery(connectQuery);

            int n = 0;
            while(queryOutput.next()){
                n++;
            }

            result = new String[n][7];

            int i=0;
            queryOutput = statement.executeQuery(connectQuery);
            while(queryOutput.next()){
                result[i][0] = queryOutput.getString("titre");
                result[i][1] = queryOutput.getString("format");
                result[i][2] = queryOutput.getString("nbex");
                result[i][3] = queryOutput.getString("nbex_total");
                result[i][4] = queryOutput.getString("fullname");
                result[i][5] = queryOutput.getString("created_at");
                result[i][6] = queryOutput.getString("id_book");
                i++;
            }

            this.createTableSelectBook(result,column);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void createTableSelectBook(String[][] result, Object[] column){
        Book book = new Book();

        JFrame frame = new JFrame("Liste des livres");
        frame.setLayout(new BorderLayout());

        JTable  table = new JTable(result,column);
        table.getTableHeader().setReorderingAllowed(false);

        Dimension dimension = new Dimension(1200,600);
        table.setPreferredSize(dimension);
        table.setPreferredScrollableViewportSize(table.getPreferredSize());
        table.setFillsViewportHeight(true);

        DefaultTableCellRenderer center = new DefaultTableCellRenderer();
        center.setHorizontalAlignment(JLabel.CENTER);
        for (int i = 0; i < column.length; i++) {
            table.getColumnModel().getColumn(i).setCellRenderer(center);
        }

        table.getColumnModel().getColumn(0).setPreferredWidth(160);
        table.getColumnModel().getColumn(1).setPreferredWidth(20);
        table.getColumnModel().getColumn(2).setPreferredWidth(20);
        table.getColumnModel().getColumn(3).setPreferredWidth(20);
        table.getColumnModel().getColumn(4).setPreferredWidth(70);
        table.getColumnModel().getColumn(5).setPreferredWidth(20);

        table.setFont(new Font("Arial", Font.PLAIN, 18));
        table.setRowHeight(25);

        // Création des panneaux
        JPanel btnPnl = new JPanel(new BorderLayout());
        JPanel bottombtnPnl = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JPanel tablePnl = new JPanel();

        // Création boutons
        JButton modifier =  new JButton("modifier");
        JButton supprimer =  new JButton("Supprimer");

        bottombtnPnl.add(modifier);
        bottombtnPnl.add(supprimer);
        btnPnl.add(bottombtnPnl, BorderLayout.CENTER);

        tablePnl.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(),"Liste des livres", TitledBorder.CENTER,TitledBorder.TOP));

        tablePnl.add(new JScrollPane(table));
        frame.setTitle("Bibliothèque Issam - Salah & Bros.");
//        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(btnPnl, BorderLayout.SOUTH);
        frame.add(tablePnl);
        frame.pack();
        frame.setVisible(true);

        modifier.addActionListener(el->{
            boolean isNull = String.valueOf(table.getSelectedRow()).equals("-1");

            if(isNull){
                JLabel label = new JLabel("Aucune ligne sélectionnée ! ");
                label.setFont(new Font("Arial", Font.BOLD, 18));
                JOptionPane.showMessageDialog(null,label,"ERROR",JOptionPane.WARNING_MESSAGE);
            } else {

                book.setTitre(table.getModel().getValueAt(table.getSelectedRow(), 0).toString());
                book.setFormat(table.getModel().getValueAt(table.getSelectedRow(), 1).toString());
                book.setNbex(Integer.parseInt(table.getModel().getValueAt(table.getSelectedRow(), 2).toString()));
                book.setNbexTotal(Integer.parseInt(table.getModel().getValueAt(table.getSelectedRow(), 3).toString()));
                String created_at = table.getModel().getValueAt(table.getSelectedRow(), 5).toString();
                book.setId_book(Integer.parseInt(table.getModel().getValueAt(table.getSelectedRow(), 6).toString()));

                try {
                    DatabaseConnection dbCon = new DatabaseConnection();
                    Connection con = dbCon.getConnection();
                    PreparedStatement statement = con.prepareStatement("UPDATE book SET titre = ?, format = ?, nbex = ?, nbex_total = ?, created_at = ? WHERE id_book = ? ");
                    System.out.println(statement);

                    statement.setString(1, book.getTitre());
                    statement.setString(2, book.getFormat());
                    statement.setInt(3, book.getNbex());
                    statement.setInt(4, book.getNbexTotal());
                    statement.setString(5, created_at);
                    statement.setInt(6, book.getId_book());

                    System.out.println(statement);

                    int status = statement.executeUpdate();
                    if(status==1){
                        JLabel label = new JLabel(book.getTitre()+" modifié avec succès ! ");
                        label.setFont(new Font("Arial", Font.BOLD, 18));
                        JOptionPane.showMessageDialog(null,label,"SUCCESS",JOptionPane.INFORMATION_MESSAGE);
                        frame.setVisible(false);
                        this.selectBook();
                    }else{
                        JLabel label = new JLabel("Erreur, vérifiez les données entrées SVP !!!");
                        label.setFont(new Font("Arial", Font.BOLD, 18));
                        JOptionPane.showMessageDialog(null,label,"ERROR",JOptionPane.WARNING_MESSAGE);
                    }
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        });

        supprimer.addActionListener((el->{
            boolean isNull = String.valueOf(table.getSelectedRow()).equals("-1");

            if(isNull){
                JLabel label = new JLabel("Aucune ligne sélectionnée ! ");
                label.setFont(new Font("Arial", Font.BOLD, 18));
                JOptionPane.showMessageDialog(null,label,"ERROR",JOptionPane.WARNING_MESSAGE);
            } else {

                String id_book = table.getModel().getValueAt(table.getSelectedRow(), 5).toString();

                try {
                    DatabaseConnection dbCon = new DatabaseConnection();
                    Connection con = dbCon.getConnection();
                    PreparedStatement statement = con.prepareStatement("DELETE FROM book WHERE id_book = ? ");

                    statement.setInt(1, Integer.parseInt(id_book));
                    System.out.println(statement);

                    int status = statement.executeUpdate();

                    if(status==1){
                        JLabel label = new JLabel("\" "+book.getTitre()+" \" supprimé avec succès ! ");
                        label.setFont(new Font("Arial", Font.BOLD, 18));
                        JOptionPane.showMessageDialog(null,label,"SUCCESS",JOptionPane.INFORMATION_MESSAGE);
                        frame.setVisible(false);
                        this.selectBook();
                    }else{
                        JLabel label = new JLabel("Erreur lors de la suppression en base de donnée !!!");
                        label.setFont(new Font("Arial", Font.BOLD, 18));
                        JOptionPane.showMessageDialog(null,label,"ERROR",JOptionPane.WARNING_MESSAGE);
                    }
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }));
    }

    public String[] selectauteurBook(ViewMenuInscriptionBook mi){
        String[] result = null  ;
        DatabaseConnection conn = new DatabaseConnection();
        Connection connectDB = conn.getConnection();
        String connectQuery = "SELECT id_auteur, nom, Prenom FROM auteur ";

        try{
            Statement statement = connectDB.createStatement();
            ResultSet queryOutput = statement.executeQuery(connectQuery);

            int n = 0;
            while(queryOutput.next()){
                n++;
            }

            result = new String[n];
            int i=0;

            queryOutput = statement.executeQuery(connectQuery);
            while(queryOutput.next()){
                result[i] = queryOutput.getString("id_auteur") + " - " + queryOutput.getString("nom")+ " " + queryOutput.getString("prenom") ;
                i++;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return result;
    }

    public void insertBook(ViewMenuInscriptionBook mi){

        DatabaseConnection conn = new DatabaseConnection();
        Connection connectDB = conn.getConnection();
        PreparedStatement statement;

        String titre = mi.getTitreb();
        String format = mi.getFormat();
        String nbex = mi.getNbex();
        String nbex_total = mi.getNbex();
        String id_auteur = mi.getIdauteur() != null ? mi.getIdauteur().split(" ")[0] : "";
        LocalDate created_at = mi.getCreated_at();

        if(titre.equals("") || format.equals("") || nbex.equals("") || nbex_total.equals("") || id_auteur == null || created_at.equals("")){
            JLabel label = new JLabel("Veuillez renseigner tous les champs svp .");
            label.setFont(new Font("Arial", Font.BOLD, 18));
            JOptionPane.showMessageDialog(null,label,"WARNING",JOptionPane.WARNING_MESSAGE);
        }else{
            try{

                statement = connectDB.prepareStatement("INSERT INTO book (titre, format, nbex, nbex_total, id_auteur, created_at) VALUES (?,?,?,?,?,?)");
                // statement.setString(1,"3");
                statement.setString(1,titre);
                statement.setString(2,format);
                statement.setString(3,nbex);
                statement.setString(4,nbex_total);
                statement.setString(5,id_auteur);
                statement.setString(6,created_at.toString());

                int status = statement.executeUpdate();

                if(status==1){
                    JLabel label = new JLabel("Livre \" "+titre+" \" ajouté"+ (Integer.parseInt(nbex_total) > 1 ?"s" : "") + " à la bibliothèque avec succès! ");
                    label.setFont(new Font("Arial", Font.BOLD, 18));
                    JOptionPane.showMessageDialog(null,label,"SUCCESS",JOptionPane.INFORMATION_MESSAGE);
                    mi.setTitreb("");
                    mi.setFormat("");
                    mi.setNbex("");
                }else{
                    JLabel label = new JLabel("Erreur lors de l'inscription en bdd!");
                    label.setFont(new Font("Arial", Font.BOLD, 18));
                    JOptionPane.showMessageDialog(null,label,"ERROR",JOptionPane.WARNING_MESSAGE);
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }
}
