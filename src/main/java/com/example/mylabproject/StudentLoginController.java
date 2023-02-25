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
import java.io.IOException;
import java.math.BigInteger;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.sql.*;
import java.util.ArrayList;

public class StudentLoginController {
    public static boolean matched;
    public static String sname;
    public static String sid1;

    public static String sdept;

    public static String semail;



    public static String haspass="ovi123";
    @FXML
    private TextField UserTf;
    @FXML
    private Button loginbt;
    @FXML
    private Button backbt;

    @FXML
    private PasswordField passTf;


    public  static ArrayList<User> users = new ArrayList<>();

    @FXML
    void LoginBt(ActionEvent event) throws SQLException, ClassNotFoundException, NoSuchAlgorithmException, InvalidKeySpecException, IOException {
        String sid = UserTf.getText();
        String pass = passTf.getText();
        String driver = "com.mysql.cj.jdbc.Driver";
        String databaseurl = "jdbc:mysql://localhost:3306/counselling";
        String username = "root";
        String password = "";
        Class.forName(driver);
        Connection conn = DriverManager.getConnection(databaseurl, username, password);
        String query = "Select * from student  where id=?";
        PreparedStatement pst=conn.prepareStatement(query);

        pst.setString(1, sid);

        ResultSet rs = pst.executeQuery();
        if(rs.next()){

           sname=rs.getString(1);
            sid1=rs.getString(2);
            semail=rs.getString(3);
            sdept=rs.getString(4);


            haspass=rs.getString(5);
            matched = validatePassword(pass, haspass);

            User newUser = new User();
            newUser.name = sname;
            users.add(newUser);

            if(matched) {


                String q = "INSERT INTO `count`(`sid`) VALUES (?)";
                PreparedStatement p=conn.prepareStatement(q);
                p.setString(1,sid1);
                p.executeUpdate();

                //p.close();
                conn.close();





                Parent root = FXMLLoader.load(getClass().getResource("StudentHomepage.fxml"));
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
            }else{
                Window owner = loginbt.getScene().getWindow();
                showAlert(Alert.AlertType.ERROR, owner, "Form Error!",
                        "Please enter correct  Password.");


            }


        }
        else {
            Window owner = loginbt.getScene().getWindow();
            showAlert(Alert.AlertType.ERROR, owner, "Form Error!",
                    "Please enter correct  Username.");
        }



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

    @FXML
    void forgetpassBt(ActionEvent event) {

    }


}
