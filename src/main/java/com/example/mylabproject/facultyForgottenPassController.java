package com.example.mylabproject;

import java.io.*;
import java.sql.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.Window;

import java.net.URL;

import java.util.ResourceBundle;

import java.io.*;
import java.util.*;
import javax.mail.*;
import javax.activation.*;
import javax.mail.internet.*;


public class facultyForgottenPassController implements Initializable {
    String driver = "com.mysql.cj.jdbc.Driver";
    String databaseurl = "jdbc:mysql://localhost:3306/counselling";
    String username = "root";
    String password = "";
    static String to;
    public static int randomcode;
    public static String from;
    private Button button;

    @FXML
    private TextField text;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    @FXML
    void sendCodeBt(ActionEvent event) throws ClassNotFoundException, SQLException, IOException {

        //Window owner = button.getScene().getWindow();
        System.out.println("trying");
        String host = "smtp.gmail.com";
        String f="uiucounselling@gmail.com";
        String to="sakibdob@gmail.com";
        String subject="Testing";
        String massage="Testing Done";
        Properties p=new Properties();
        p.put("mail.smtp.auth","true");
        p.put("mail.smtp.starttls.enable","true");
        p.put("mail.smtp.host","smtp.gmail.com");
        p.put("mail.smtp.port","587");



//        p.put("mail.smtp.host",host);
//        p.put("mail.smtp.port","587");
//        p.put("mail.smtp.ssl.enable", "true");
//        p.put("mail.smtp.auth", "true");
        Session s= Session.getDefaultInstance(p,new javax.mail.Authenticator(){

            protected PasswordAuthentication getPasswordAuthentication(){
                return new PasswordAuthentication("uiucounselling@gmail.com","aiaodkyqnfoulyak");

            }

        });


//        Session sak=Session.getInstance(p,new javax.mail.Authenticator(){
//
//            protected PasswordAuthentication getPasswordAuthentication() {
//                return new PasswordAuthentication("uiucounselling@gmail.com", "lotgvbqmlydxddqz");
//            }
//
//            }
//
//
//
//
//        );
        s.setDebug(true);
        try{
            MimeMessage m=new MimeMessage(s);
            m.setFrom(new InternetAddress(f));
            m.addRecipient(Message.RecipientType.TO,new InternetAddress(to));
            m.setSubject(subject);
            m.setText(massage);
            System.out.println("sending...");
            Transport.send(m);
            System.out.println("Sent message successfully....");


        }
        catch(Exception e){
            e.printStackTrace();

        }
















//        Address address = new Address() {
//            @Override
//            public boolean equals(Object o) {
//                return false;
//            }
//
//            @Override
//            public String getType() {
//                return null;
//            }
//
//            @Override
//            public String toString() {
//                return null;
//            }
//        };


//        Connection conn = null;
//
//        Class.forName("com.mysql.cj.jdbc.Driver");
//
//        conn = DriverManager.getConnection(databaseurl,username,password);
//
//
//
//        String query = "Select * from save where email=?";
//        PreparedStatement pst = conn.prepareStatement(query);
//        pst.setString(1, text.getText());
//        ResultSet rs = pst.executeQuery();
//        if(rs.next()){
//
//
//
//
//            Parent ro = FXMLLoader.load(getClass().getResource("pincode.fxml"));
//            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
//            Scene scene = new Scene(ro);
//            stage.setScene(scene);
//            stage.show();
//
//        }
//        else {
//            showAlert(Alert.AlertType.ERROR, owner, "Form Error!",
//                    "Please enter Correct email");
//        }
//        conn.close();
//        pst.close();
    }
    private static void showAlert(Alert.AlertType alertType, Window owner, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.initOwner(owner);
        alert.show();
    }


}
