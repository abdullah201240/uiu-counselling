package com.example.mylabproject;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.Window;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.io.IOException;
import java.math.BigInteger;
import java.net.URL;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class AdminAddStudentsController implements Initializable {
    public static String dept;

    @FXML
    private Button AddFacultyButt;
    @FXML
    private Button homebt;

    @FXML
    private Button AddStudentButt;

    @FXML
    private Pane PnlStatus;

    @FXML
    private Button ViewFacultyButt;

    @FXML
    private Button ViewStudentButt;

    @FXML
    private Button addCoursebt;

    @FXML
    private Button addbt;

    @FXML
    private Button allCoursesbt;

    @FXML
    private ComboBox deptcmb;

    @FXML
    private TextField emailtf;

    @FXML
    private TextField idtf;

    @FXML
    private Label lblStatus;

    @FXML
    private Label lblStatusMini;

    @FXML
    private TextField nametf;


    @FXML
    void homebt(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("adminProfile.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }



    @FXML
    void AddCourseBt(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("adminAddCourse.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }

    @FXML
    void AddFacultyBt(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("adminAddFaculty.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }

    @FXML
    void ViewFacultyBt(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("adminViewAllFaculty.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }

    @FXML
    void ViewStudentBt(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("adminViewAllStudent.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }

    @FXML
    void addBt(ActionEvent event) throws NoSuchAlgorithmException, InvalidKeySpecException, ClassNotFoundException, SQLException {
        String password = "1234";
        String sa
                = generateStorngPasswordHash(password);
        String name = nametf.getText();
        String id = idtf.getText();
        String email = emailtf.getText();

        String driver = "com.mysql.cj.jdbc.Driver";
        String databaseurl = "jdbc:mysql://localhost:3306/counselling";
        String username = "root";
        String passwords = "";
        Class.forName(driver);
        Connection conn = DriverManager.getConnection(databaseurl, username, passwords);
        String sql="INSERT INTO `student`(`id`, `name`, `email`, `password`,  `department`,photo) VALUES (?, ?, ?, ?,?,?)";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1,id);
        ps.setString(2,name);
        ps.setString(3, email);
        ps.setString(4, sa);
        ps.setString(5, dept);
        ps.setString(6, "");
        ps.executeUpdate();

        conn.close();
        nametf.setText("");
        idtf.setText("");
        emailtf.setText("");


        Window owner = addbt.getScene().getWindow();
        showAlert(Alert.AlertType.CONFIRMATION, owner, "Congratulations!!!", "Added Successfully.");




    }

    @FXML
    void allCoursesbt(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("adminViewAllCourse.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }

    @FXML
    void deptBt(ActionEvent event) {
        dept=deptcmb.getSelectionModel().getSelectedItem().toString();


    }
    private static void showAlert(Alert.AlertType alertType, Window owner, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.initOwner(owner);
        alert.show();
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


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ObservableList<String> list1 = FXCollections.observableArrayList("CSE","EEE","BBA");
        deptcmb.setItems(list1);
    }

}

