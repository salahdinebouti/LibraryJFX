package Model;

import java.awt.*;
import java.sql.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import View.ViewMenuInscriptionUser;
import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableCellRenderer;

public class User {

    private int id_user;
    private String fname;
    private String lname;
    private LocalDate datenaissance;
    private String adresse;
    private String email;

    public User() {
    }

    public User(int id_user, String fname, String lname, LocalDate datenaissance, String adresse, String email) {
        this.id_user = id_user;
        this.fname = fname;
        this.lname = lname;
        this.datenaissance = datenaissance;
        this.adresse = adresse;
        this.email = email;
    }

    public int getId_user() {
        return id_user;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public LocalDate getDatenaissance() {
        return datenaissance;
    }

    public void setDatenaissance(LocalDate datenaissance) {
        this.datenaissance = datenaissance;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void insertUser(ViewMenuInscriptionUser mi){
        User user = new User();

        DatabaseConnection conn = new DatabaseConnection();
        Connection connectDB = conn.getConnection();
        PreparedStatement statement;

        user.setFname(mi.getPrenom());
        user.setLname(mi.getNom());
        user.setAdresse(mi.getAdresse());
        user.setEmail(mi.getEmail());
        user.setDatenaissance(mi.getDatenaissance());

        if(user.getLname().equals("") || user.getFname().equals("") || String.valueOf(user.getDatenaissance()).equals("") || user.getEmail().equals("") || user.getAdresse().equals("")){
            JLabel label = new JLabel("Veuillez renseigner tous les champs svp .");
            label.setFont(new Font("Arial", Font.BOLD, 18));
            JOptionPane.showMessageDialog(null,label,"WARNING",JOptionPane.WARNING_MESSAGE);
        } else {
            try{
                statement = connectDB.prepareStatement("INSERT INTO user (lname, fname, datenaissance, adresse, email) VALUES (?,?,?,?,?)");
                statement.setString(1, user.getLname());
                statement.setString(2, user.getFname() );
                statement.setString(3, String.valueOf(user.getDatenaissance()));
                statement.setString(4, user.getEmail());
                statement.setString(5, user.getAdresse());

                System.out.println(statement);
                int status = statement.executeUpdate();
                if(status==1){
                    JLabel label = new JLabel(user.getFname()+" "+user.getLname() + " ajouté avec succès!");
                    label.setFont(new Font("Arial", Font.BOLD, 18));
                    JOptionPane.showMessageDialog(null,label,"Inscription validée !",JOptionPane.INFORMATION_MESSAGE);
                    mi.setNom("");
                    mi.setPrenom("");
                    mi.setEmail("");
                    mi.setAdresse("");
                }else{
                    JOptionPane.showMessageDialog(null,"Erreur lors de l'inscription");
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    public void selectUser() {
        String[][] result ;
        DatabaseConnection conn = new DatabaseConnection();
        Connection connectDB = conn.getConnection();
        String connectQuery = "SELECT * FROM user";
        Object[] column = {"Nom", "Prénom", "Date de naissance", "Email", "Adresse"};

        try{
            Statement statement = connectDB.createStatement();
            ResultSet queryOutput = statement.executeQuery(connectQuery);

            int n = 0;
            while(queryOutput.next())  n++;

            result = new String[n][6];

            int i=0;
            queryOutput = statement.executeQuery(connectQuery);

            while(queryOutput.next()){
                result[i][0] = queryOutput.getString("lname");
                result[i][1] = queryOutput.getString("fname");
                result[i][2] = queryOutput.getString("datenaissance");
                result[i][3] = queryOutput.getString("adresse");
                result[i][4] = queryOutput.getString("email");
                result[i][5] = queryOutput.getString("id_user");
                i++;
            }

            this.createTableSelectUser(result, column);

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void createTableSelectUser(String[][] result, Object[] column){
        User user = new User();

        JFrame frame = new JFrame();
        frame.setLayout(new BorderLayout());

        JTable table = new JTable(result,column);
        table.getTableHeader().setReorderingAllowed(false);
        table.setFont(new Font("Arial", Font.PLAIN, 18));
        table.setRowHeight(25);

        Dimension dimension = new Dimension(1600,600);
        table.setPreferredSize(dimension);
        table.setPreferredScrollableViewportSize(table.getPreferredSize());
        table.setFillsViewportHeight(false);

        DefaultTableCellRenderer center = new DefaultTableCellRenderer();
        center.setHorizontalAlignment(JLabel.CENTER);
        for (int i = 0; i < column.length; i++) {
            table.getColumnModel().getColumn(i).setCellRenderer(center);
        }

        table.getColumnModel().getColumn(0).setPreferredWidth(100);
        table.getColumnModel().getColumn(1).setPreferredWidth(100);
        table.getColumnModel().getColumn(2).setPreferredWidth(150);
        table.getColumnModel().getColumn(3).setPreferredWidth(300);
        table.getColumnModel().getColumn(4).setPreferredWidth(500);

        JPanel btnPnl = new JPanel(new BorderLayout());
        JPanel bottombtnPnl = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JPanel tablePnl = new JPanel();

        JButton modifier =  new JButton("modifier");
        JButton supprimer =  new JButton("Supprimer");

        bottombtnPnl.add(modifier);
        bottombtnPnl.add(supprimer);

        btnPnl.add(bottombtnPnl, BorderLayout.CENTER);
        tablePnl.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(),"Liste des inscrits", TitledBorder.CENTER,TitledBorder.TOP));
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
                user.setLname(table.getModel().getValueAt(table.getSelectedRow(), 0).toString());
                user.setFname(table.getModel().getValueAt(table.getSelectedRow(), 1).toString());
                user.setDatenaissance(LocalDate.parse(table.getModel().getValueAt(table.getSelectedRow(), 2).toString(),formatter));
                user.setEmail(table.getModel().getValueAt(table.getSelectedRow(), 3).toString());
                user.setAdresse(table.getModel().getValueAt(table.getSelectedRow(), 4).toString());
                user.setId_user(Integer.parseInt(table.getModel().getValueAt(table.getSelectedRow(), 5).toString()));

                try {
                    DatabaseConnection dbCon = new DatabaseConnection();
                    Connection con = dbCon.getConnection();
                    PreparedStatement statement = con.prepareStatement("UPDATE user SET lname = ?, fname = ?, datenaissance = ?, email = ?, adresse = ? WHERE id_user = ?");

                    statement.setString(1, user.getLname());
                    statement.setString(2, user.getFname());
                    statement.setString(3,String.valueOf(user.getDatenaissance()));
                    statement.setString(4, user.getEmail());
                    statement.setString(5, user.getAdresse());
                    statement.setString(6,String.valueOf(user.getId_user()));

                    int status = statement.executeUpdate();

                    if(status == 1){
                        JLabel label = new JLabel(user.getFname() + " " + user.getLname() + " modifié avec succès!");
                        label.setFont(new Font("Arial", Font.BOLD, 18));
                        JOptionPane.showMessageDialog(null,label,"SUCCESS",JOptionPane.INFORMATION_MESSAGE);

                        frame.setVisible(false);
//                        this.selectUser();
                        this.createTableSelectUser(result,column);
                    } else {
                        JLabel label = new JLabel("Erreur, vérifiez les données entrées SVP !!!");
                        label.setFont(new Font("Arial", Font.BOLD, 18));
                        JOptionPane.showMessageDialog(null,label,"ERROR",JOptionPane.WARNING_MESSAGE);
                    }
                } catch (Exception e){
                    e.printStackTrace();
                }
            }
        });

        supprimer.addActionListener(( el -> {
            boolean isNull = String.valueOf(table.getSelectedRow()).equals("-1");

            if(isNull){
                JLabel label = new JLabel("Aucune ligne sélectionnée ! ");
                label.setFont(new Font("Arial", Font.BOLD, 18));
                JOptionPane.showMessageDialog(null,label,"ERROR",JOptionPane.WARNING_MESSAGE);
            } else {
                String id_user = table.getModel().getValueAt(table.getSelectedRow(), 5).toString();
                user.setLname(table.getModel().getValueAt(table.getSelectedRow(), 0).toString());
                user.setFname(table.getModel().getValueAt(table.getSelectedRow(), 1).toString());

                try {
                    DatabaseConnection dbCon = new DatabaseConnection();
                    Connection con = dbCon.getConnection();
                    PreparedStatement statement = con.prepareStatement("DELETE FROM user WHERE id_user = ? ");

                    statement.setInt(1, Integer.parseInt(id_user));

                    int status = statement.executeUpdate();

                    if(status==1){
                        JLabel label = new JLabel(user.getFname() + " " + user.getLname() + " supprimé avec succès!");
                        label.setFont(new Font("Arial", Font.BOLD, 18));
                        JOptionPane.showMessageDialog(null,label,"SUCCESS",JOptionPane.INFORMATION_MESSAGE);
                        frame.setVisible(false);
                        this.createTableSelectUser(result,column);

//                        this.selectUser();
                    } else {
                        JLabel label = new JLabel("Échec de la suppression");
                        label.setFont(new Font("Arial", Font.BOLD, 18));
                        JOptionPane.showMessageDialog(null,label,"ERROR",JOptionPane.WARNING_MESSAGE);
                    }
                } catch (Exception e){
                    e.printStackTrace();
                }

            }
        }));
    }
}
