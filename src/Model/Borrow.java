package Model;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;
import java.sql.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Borrow {

    private int id_borrow;
    private int id_user;
    private int id_book;
    private LocalDate borrow_date;
    private LocalDate borrow_return_date;
    private int duree;
    private String retourOk;

    public Borrow() {
    }

    public Borrow(int id_borrow, int id_user, int id_book, LocalDate borrow_date, LocalDate borrow_return_date, int duree, String retourOk) {
        this.id_borrow = id_borrow;
        this.id_user = id_user;
        this.id_book = id_book;
        this.borrow_date = borrow_date;
        this.borrow_return_date = borrow_return_date;
        this.duree = duree;
        this.retourOk = retourOk;
    }

    public int getId_user() {
        return id_user;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    public int getId_book() {
        return id_book;
    }

    public void setId_book(int id_book) {
        this.id_book = id_book;
    }

    public int getId_borrow() {
        return id_borrow;
    }

    public void setId_borrow(int id_borrow) {
        this.id_borrow = id_borrow;
    }

    public int getDuree() {
        return duree;
    }

    public void setDuree(int duree) {
        this.duree = duree;
    }

    public LocalDate getBorrow_date() {
        return borrow_date;
    }

    public void setBorrow_date(LocalDate borrow_date) {
        this.borrow_date = borrow_date;
    }

    public LocalDate getBorrow_return_date() {
        return borrow_return_date;
    }

    public void setBorrow_return_date(LocalDate borrow_return_date) {
        this.borrow_return_date = borrow_return_date;
    }

    public String getRetourOk() {
        return retourOk;
    }

    public void setRetourOk(String retourOk) {
        this.retourOk = retourOk;
    }

    public void selectBorrow(){
        String[][] result ;
        DatabaseConnection conn = new DatabaseConnection();
        Connection connectDB = conn.getConnection();
        String connectQuery = "SELECT A.lname AS lname, A.fname AS fname, B.titre AS titre, C.id_borrow AS id_borrow, C.borrow_date AS borrow_date, C.borrow_return_date AS borrow_return_date, C.duree AS duree, C.etat_retour FROM user A, book B, borrow C WHERE A.id_user = C.id_user AND B.id_book = C.id_book";
        Object[] column = {" Nom", "Prénom ", "Titre ", "Date emprunt", "Date retour", " Jour restant ", "État retour"};

        try{
            Statement statement = connectDB.createStatement();
            ResultSet queryOutput = statement.executeQuery(connectQuery);

            int n = 0;
            while(queryOutput.next())  n++;

            result = new String[n][8];

            queryOutput = statement.executeQuery(connectQuery);

            int i=0;
            while(queryOutput.next()){
                result[i][0] = queryOutput.getString("lname");
                result[i][1] = queryOutput.getString("fname");
                result[i][2] = queryOutput.getString("titre");
                result[i][3] = queryOutput.getString("borrow_date");
                result[i][4] = queryOutput.getString("borrow_return_date");
                result[i][5] = queryOutput.getString("duree");
                result[i][6] = queryOutput.getString("etat_retour");
                result[i][7] = queryOutput.getString("id_borrow");
                i++;
            }

            this.createTableSelectBorrow(result, column);

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void createTableSelectBorrow(String[][] result, Object[] column){
        User user = new User();
        Book book = new Book();
        Borrow borrow = new Borrow();

        // Création du cadre
        JFrame frame = new JFrame();
        frame.setLayout(new BorderLayout());

        // Création du tableau d'affichage des datas SQL
        JTable  table = new JTable(result,column);
        table.getTableHeader().setReorderingAllowed(false);
        table.setFont(new Font("Arial", Font.PLAIN, 18));
        table.setRowHeight(25);

        DefaultTableCellRenderer center = new DefaultTableCellRenderer();
        center.setHorizontalAlignment(JLabel.CENTER);
        for (int i = 0; i < column.length; i++) {
            table.getColumnModel().getColumn(i).setCellRenderer(center);
        }
        table.setFillsViewportHeight(true);

        // Création des panneaux
        JPanel btnPnl = new JPanel(new BorderLayout());
        JPanel bottombtnPnl = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JPanel tablePnl = new JPanel();

        // Création boutons
        JButton modifier =  new JButton("modifier");
        JButton supprimer =  new JButton("Supprimer");

        // Insert bouton dans jpanel
        bottombtnPnl.add(modifier);
        bottombtnPnl.add(supprimer);
        btnPnl.add(bottombtnPnl, BorderLayout.CENTER);
        tablePnl.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(),"Liste des emprunts/retour", TitledBorder.CENTER,TitledBorder.TOP));

        JScrollPane jsp =  new JScrollPane(table);
        Dimension dimension = new Dimension(1000,800);
        jsp.setPreferredSize( dimension );
        table.setPreferredSize(dimension);
        table.setPreferredScrollableViewportSize(table.getPreferredSize());

        tablePnl.add(jsp);
        frame.setTitle("Bibliothèque Issam - Salah & Bros.");
        frame.add(btnPnl, BorderLayout.SOUTH);
        frame.add(tablePnl);

        frame.pack();
        frame.setVisible(true);

        modifier.addActionListener(el->{
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

            boolean isNull = String.valueOf(table.getSelectedRow()).equals("-1");

            if(isNull){
                JLabel label = new JLabel("Aucune ligne sélectionnée ! ");
                label.setFont(new Font("Arial", Font.BOLD, 18));
                JOptionPane.showMessageDialog(null,label,"ERROR",JOptionPane.WARNING_MESSAGE);
            } else {

                user.setLname(table.getModel().getValueAt(table.getSelectedRow(), 0).toString());
                user.setFname(table.getModel().getValueAt(table.getSelectedRow(), 1).toString());
                book.setTitre(table.getModel().getValueAt(table.getSelectedRow(), 2).toString());
                borrow.setBorrow_date(LocalDate.parse(table.getModel().getValueAt(table.getSelectedRow(), 3).toString(),formatter));
                borrow.setBorrow_return_date(LocalDate.parse(table.getModel().getValueAt(table.getSelectedRow(), 4).toString(), formatter));
                borrow.setDuree(Integer.parseInt(table.getModel().getValueAt(table.getSelectedRow(), 5).toString()));
                borrow.setRetourOk(table.getModel().getValueAt(table.getSelectedRow(), 6).toString());
                borrow.setId_borrow(Integer.parseInt(table.getModel().getValueAt(table.getSelectedRow(), 7).toString()));


                try {
                    DatabaseConnection dbCon = new DatabaseConnection();
                    Connection con = dbCon.getConnection();
                    PreparedStatement statement = con.prepareStatement("UPDATE borrow SET borrow_date = ?, borrow_return_date = ?, duree = ?, etat_retour = ? WHERE id_borrow = ?");

                    statement.setDate(1, Date.valueOf(borrow.getBorrow_date()));
                    statement.setDate(2, Date.valueOf(borrow.getBorrow_return_date()));
                    statement.setInt(3,borrow.getDuree());
                    statement.setString(4,borrow.getRetourOk());
                    statement.setInt(5,borrow.getId_borrow());

                    System.out.println(statement);

                    int status = statement.executeUpdate();
                    if(status==1){
                        JLabel label = new JLabel("Emprunt/Retour modifié avec succès!");
                        label.setFont(new Font("Arial", Font.BOLD, 18));
                        JOptionPane.showMessageDialog(null,label,"SUCCESS",JOptionPane.INFORMATION_MESSAGE);
                        frame.setVisible(false);
                        this.selectBorrow();
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
                borrow.setId_borrow(Integer.parseInt(table.getModel().getValueAt(table.getSelectedRow(), 7).toString()));

                try {
                    DatabaseConnection dbCon = new DatabaseConnection();
                    Connection con = dbCon.getConnection();
                    PreparedStatement statement = con.prepareStatement("DELETE FROM borrow WHERE id_borrow = ? ");

                    statement.setInt(1,borrow.getId_borrow());
                    System.out.println(statement);
                    int status = statement.executeUpdate();

                    if(status==1){
                        JLabel label = new JLabel("Emprunt/retour, id : " + borrow.getId_borrow() + " supprimé avec succès!");
                        label.setFont(new Font("Arial", Font.BOLD, 18));
                        JOptionPane.showMessageDialog(null,label,"SUCCESS",JOptionPane.INFORMATION_MESSAGE);
                        frame.setVisible(false);
                        this.selectBorrow();

                    }else{
                        JLabel label = new JLabel("Erreur lors de la suppression en bdd!");
                        label.setFont(new Font("Arial", Font.BOLD, 18));
                        JOptionPane.showMessageDialog(null,label,"ERROR",JOptionPane.WARNING_MESSAGE);
                    }
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }));
    }
}
