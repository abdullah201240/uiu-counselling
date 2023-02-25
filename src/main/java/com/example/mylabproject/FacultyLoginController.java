package com.example.mylabproject;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.Window;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.math.BigInteger;
import java.net.ServerSocket;
import java.net.Socket;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.sql.*;
import java.io.*;
import java.sql.PreparedStatement;
//import java.io.IOException;


public class FacultyLoginController {
    public static boolean matched;
    public static String name;
    public static String id;
    public static String email;
    public static String room;
    public static Blob b;



    public static String haspass="ovi123";

//    static final String DB_URL = "jdbc:mysql://localhost:3306/ovi?serverTimezone=UTC";
//    static final String USER = "root";
//    static final String PASS = "";


    @FXML
    private PasswordField PassTf;

    @FXML
    private TextField UserTf1;

    @FXML
    private Button backbt;

    @FXML
    private Button facultyloginbt;


    @FXML
    void forgotpassBt(ActionEvent event) throws IOException {

        Parent root = FXMLLoader.load(getClass().getResource("facultyForgottenPass.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }

    @FXML
    void LoginBt(ActionEvent event) throws ClassNotFoundException, SQLException, NoSuchAlgorithmException, InvalidKeySpecException, IOException {










//        String user=UserTf1.getText();
//        String pass=PassTf.getText();
//
//        Connection conn = null;
//        PreparedStatement pst;
//        Class.forName("com.mysql.jdbc.Driver");
//        conn = (Connection) DriverManager.getConnection(DB_URL,USER,PASS);
//
//        System.out.println("hello");
//        Statement abc = (Statement) conn.connectedProperty();

//        String query = "Select * from teacher where id=?";
//        PreparedStatement pst=(PreparedStatement) conn.prepareStatement(query);
//        ResultSet ra =pst.executeQuery(query);
//        while(ra.next()){
//            System.out.println("name");
//        }


        String user=UserTf1.getText();
        String pass=PassTf.getText();


        String driver = "com.mysql.cj.jdbc.Driver";
        String databaseurl = "jdbc:mysql://localhost:3306/counselling";
        String username = "root";
        String password = "";
        Class.forName(driver);
        Connection conn = DriverManager.getConnection(databaseurl, username, password);
        String query = "Select * from teacher  where id=?";
        PreparedStatement pst=conn.prepareStatement(query);

        pst.setString(1, user);

        ResultSet rs = pst.executeQuery();
        if(rs.next()){
            b=rs.getBlob(9);


            id=rs.getString(1);
            name=rs.getString(2);
            email=rs.getString(3);
            room=rs.getString(5);


            haspass=rs.getString(4);
            matched = validatePassword(pass, haspass);
            if(matched) {


                Parent root = FXMLLoader.load(getClass().getResource("FacultyHomePage.fxml"));
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();







            }else{
                Window owner = facultyloginbt.getScene().getWindow();
                showAlert(Alert.AlertType.ERROR, owner, "Form Error!",
                        "Please enter correct  Password.");


            }


        }
        else {
            Window owner = facultyloginbt.getScene().getWindow();
            showAlert(Alert.AlertType.ERROR, owner, "Form Error!",
                    "Please enter correct  Username.");
        }


//        if(matched) {
//            Parent root = FXMLLoader.load(getClass().getResource("FacultyHomePage.fxml"));
//            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
//            Scene scene = new Scene(root);
//            stage.setScene(scene);
//            stage.show();
//
//        }



        conn.close();
        pst.close();


    }

    @FXML
    void backBt(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("firstPage.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }

    @FXML
    void forgetpass(ActionEvent event) {


    }
    private static void showAlert(Alert.AlertType alertType, Window owner, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.initOwner(owner);
        alert.show();
    }
    private static boolean validatePassword(String originalPassword, String storedPassword)
            throws NoSuchAlgorithmException, InvalidKeySpecException
    {
        String[] parts = storedPassword.split(":");
        int iterations = Integer.parseInt(parts[0]);

        byte[] salt = fromHex(parts[1]);
        byte[] hash = fromHex(parts[2]);

        PBEKeySpec spec = new PBEKeySpec(originalPassword.toCharArray(),
                salt, iterations, hash.length * 8);
        SecretKeyFactory skf = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
        byte[] testHash = skf.generateSecret(spec).getEncoded();

        int diff = hash.length ^ testHash.length;
        for(int i = 0; i < hash.length && i < testHash.length; i++)
        {
            diff |= hash[i] ^ testHash[i];
        }
        return diff == 0;
    }
    private static byte[] fromHex(String hex) throws NoSuchAlgorithmException
    {
        byte[] bytes = new byte[hex.length() / 2];
        for(int i = 0; i < bytes.length ;i++)
        {
            bytes[i] = (byte)Integer.parseInt(hex.substring(2 * i, 2 * i + 2), 16);
        }
        return bytes;
    }
    private static String generateStorngPasswordHash(String password)
            throws NoSuchAlgorithmException, InvalidKeySpecException
    {
        int iterations = 1000;
        char[] chars = password.toCharArray();
        byte[] salt = getSalt();

        PBEKeySpec spec = new PBEKeySpec(chars, salt, iterations, 64 * 8);
        SecretKeyFactory skf = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");

        byte[] hash = skf.generateSecret(spec).getEncoded();
        return iterations + ":" + toHex(salt) + ":" + toHex(hash);
    }
    private static byte[] getSalt() throws NoSuchAlgorithmException
    {
        SecureRandom sr = SecureRandom.getInstance("SHA1PRNG");
        byte[] salt = new byte[16];
        sr.nextBytes(salt);
        return salt;
    }

    private static String toHex(byte[] array) throws NoSuchAlgorithmException
    {
        BigInteger bi = new BigInteger(1, array);
        String hex = bi.toString(16);

        int paddingLength = (array.length * 2) - hex.length();
        if(paddingLength > 0)
        {
            return String.format("%0"  +paddingLength + "d", 0) + hex;
        }else{
            return hex;
        }
    }

}
