package Model;


import javax.swing.*;
import java.awt.*;
import java.sql.*;
import View.ViewMenuInscriptionAuteur;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableCellRenderer;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Auteur {
    private int id_auteur;
    private String nom;
    private String prenom;
    private LocalDate datenaissance;

    public Auteur() {
    }

    public Auteur(int id_auteur, String nom, String prenom, LocalDate datenaissance) {
        this.id_auteur = id_auteur;
        this.nom = nom;
        this.prenom = prenom;
        this.datenaissance = datenaissance;
    }


    public int getId_auteur() {
        return id_auteur;
    }

    public void setId_auteur(int id_auteur) {
        this.id_auteur = id_auteur;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public LocalDate getDatenaissance() {
        return datenaissance;
    }

    public void setDatenaissance(LocalDate datenaissance) {
        this.datenaissance = datenaissance;
    }

    public void insertAuteur(ViewMenuInscriptionAuteur mi){
        DatabaseConnection conn = new DatabaseConnection();
        Connection connectDB = conn.getConnection();
        PreparedStatement statement;
        String fname=mi.getPrenom();
        String lname = mi.getNom();
        LocalDate datenaissance = mi.getDatenaissance();

        if(fname.equals("") || lname.equals("") || datenaissance == null){
            JLabel label = new JLabel("Veuillez renseigner tous les champs svp .");
            label.setFont(new Font("Arial", Font.BOLD, 18));
            JOptionPane.showMessageDialog(null,label,"WARNING",JOptionPane.WARNING_MESSAGE);
        } else {
            try{
                //Statement statement = connectDB.prepareStatement("insert into person(lname,fname,datenissance) values(?,?,?)");
                statement = connectDB.prepareStatement("INSERT INTO auteur (nom, prenom, datenaissance) VALUES (?,?,?)");
                // statement.setString(1,"3");
                statement.setString(1,fname);
                statement.setString(2,lname);
                statement.setString(3,datenaissance.toString());

                int status = statement.executeUpdate();
                if(status==1){
                    JLabel label = new JLabel(fname + " " + lname + " ajouté avec succès!");
                    label.setFont(new Font("Arial", Font.BOLD, 18));
                    JOptionPane.showMessageDialog(null,label,"SUCCESS",JOptionPane.INFORMATION_MESSAGE);
                    mi.setNom("");
                    mi.setPrenom("");
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

    public void selectAuteur(){
        String[][] result ;
        JFrame f;
        f = new JFrame();

        DatabaseConnection conn = new DatabaseConnection();
        Connection connectDB = conn.getConnection();
        String connectQuery = "SELECT nom, prenom, datenaissance, id_auteur FROM auteur";
        String[] column = {"Nom","Prenom","Date de naissance"};

        try{
            Statement statement = connectDB.createStatement();
            ResultSet queryOutput = statement.executeQuery(connectQuery);

            int n = 0;
            while(queryOutput.next()){
                n++;
            }

            result = new String[n][4];

            int i=0;
            queryOutput = statement.executeQuery(connectQuery);

            while(queryOutput.next()){
                result[i][0] = queryOutput.getString("nom");
                result[i][1] = queryOutput.getString("prenom");
                result[i][2] = queryOutput.getString("datenaissance");
                result[i][3] = queryOutput.getString("id_auteur");
                i++;
            }

            this.createTableSelectAuteur(result,column);

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void createTableSelectAuteur(String[][] result, Object[] column){
        Auteur auteur = new Auteur();

        JFrame frame = new JFrame();
        frame.setLayout(new BorderLayout());

        JTable  table = new JTable(result,column);
        table.getTableHeader().setReorderingAllowed(false);

        DefaultTableCellRenderer center = new DefaultTableCellRenderer();
        center.setHorizontalAlignment(JLabel.CENTER);
        for (int i = 0; i < column.length; i++) {
            table.getColumnModel().getColumn(i).setCellRenderer(center);
        }

        table.setFont(new Font("Arial", Font.PLAIN, 18));
        table.setRowHeight(25);

        Dimension dimension = new Dimension(800,600);
        table.setPreferredSize(dimension);
        table.setPreferredScrollableViewportSize(table.getPreferredSize());
        table.setFillsViewportHeight(true);
        table.setFont(new Font("Arial", Font.PLAIN, 18));

        table.getColumnModel().getColumn(0).setPreferredWidth(60);
        table.getColumnModel().getColumn(1).setPreferredWidth(60);
        table.getColumnModel().getColumn(2).setPreferredWidth(40);

        // Création des panneaux
        JPanel btnPnl = new JPanel(new BorderLayout());
        JPanel bottombtnPnl = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JPanel tablePnl = new JPanel();

        JButton modifier =  new JButton("modifier");
        JButton supprimer =  new JButton("Supprimer");

        // Insert bouton dans jpanel
        bottombtnPnl.add(modifier);
        bottombtnPnl.add(supprimer);
        btnPnl.add(bottombtnPnl, BorderLayout.CENTER);

        tablePnl.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(),"Liste des auteurs", TitledBorder.CENTER,TitledBorder.TOP));
        tablePnl.add(new JScrollPane(table));

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
                auteur.setNom(table.getModel().getValueAt(table.getSelectedRow(), 0).toString());
                auteur.setPrenom(table.getModel().getValueAt(table.getSelectedRow(), 1).toString());
                auteur.setDatenaissance(LocalDate.parse(table.getModel().getValueAt(table.getSelectedRow(), 2).toString(),formatter));
                auteur.setId_auteur(Integer.parseInt(table.getModel().getValueAt(table.getSelectedRow(), 3).toString()));

                try {
                    DatabaseConnection dbCon = new DatabaseConnection();
                    Connection con = dbCon.getConnection();
                    PreparedStatement statement = con.prepareStatement("UPDATE auteur SET nom = ?, prenom = ?, datenaissance = ? WHERE id_auteur = ?");

                    statement.setString(1, auteur.getNom());
                    statement.setString(2, auteur.getPrenom());
                    statement.setString(3,String.valueOf(auteur.getDatenaissance()));
                    statement.setString(4,String.valueOf(auteur.getId_auteur()));

                    System.out.println(statement);

                    int status = statement.executeUpdate();
                    if(status==1){
                        JLabel label = new JLabel(auteur.getNom()+" "+auteur.getPrenom() + " modifié avec succès!");
                        label.setFont(new Font("Arial", Font.BOLD, 18));
                        JOptionPane.showMessageDialog(null,label,"SUCCESS",JOptionPane.INFORMATION_MESSAGE);
                        frame.setVisible(false);
                        this.selectAuteur();
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
                String id_auteur = table.getModel().getValueAt(table.getSelectedRow(),3 ).toString();
                auteur.setNom(table.getModel().getValueAt(table.getSelectedRow(), 0).toString());
                auteur.setPrenom(table.getModel().getValueAt(table.getSelectedRow(), 1).toString());

                try {
                    DatabaseConnection dbCon = new DatabaseConnection();
                    Connection con = dbCon.getConnection();
                    PreparedStatement statement = con.prepareStatement("DELETE FROM auteur WHERE id_auteur = ? ");

                    statement.setInt(1, Integer.parseInt(id_auteur));
                    System.out.println(statement);

                    int status = statement.executeUpdate();
                    if(status==1){
                        JLabel label = new JLabel(auteur.getNom()+" "+auteur.getPrenom() + " supprimé avec succès!");
                        label.setFont(new Font("Arial", Font.BOLD, 18));
                        JOptionPane.showMessageDialog(null,label,"SUCCESS",JOptionPane.INFORMATION_MESSAGE);
                        frame.setVisible(false);
                        this.selectAuteur();
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
