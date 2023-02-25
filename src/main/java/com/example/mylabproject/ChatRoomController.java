package com.example.mylabproject;

import javafx.animation.FadeTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.NodeOrientation;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

//import javax.swing.*;
import java.io.*;
import java.net.Socket;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;
import java.util.concurrent.TimeUnit;

import static com.example.mylabproject.StudentLoginController.*;

//import static com.example.mylabproject.OpenPageController.*;


public class ChatRoomController extends Thread implements Initializable{

    @FXML
    public TextArea msgRoom;
    @FXML
    public Pane chat;
    @FXML
    public TextField msgField;
    @FXML
    public Label clientName;
    @FXML
    public Button profileBtn;
    /* @FXML
     public Label online;
     @FXML
     public Button chatBtn;*/
    @FXML
    public Circle showProPic;
    @FXML
    public ImageView proImage;
    @FXML
    public Label fullName;
    @FXML
    public Label email;
    @FXML
    public Label phoneNo;
    @FXML
    public Label gender;
    @FXML
    public Pane profile;
    @FXML
    public TextField fileChoosePath;
    @FXML
    public Button logOutbtn1;

    private FileChooser fileChooser;
    private File filePath;
    public boolean toggleChat = false, toggleProfile = false;
    public static Blob z;
    public static int za;

    BufferedReader reader;
    PrintWriter writer = null;
    Socket socket;
    String userName;
    public void takename()
    {
        for (User user : users) {
            userName = user.name;
        }

    }


    @FXML
    void homebt(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("StudentHomepage.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }





    @FXML
    private void handleSendEvent(MouseEvent event) throws SQLException, ClassNotFoundException {
        send();
        for (User user : users) {
            System.out.println(user.name);
        }
    }



//
//    int serial;
//    String Name1,Password1,email1,phone1,gender1 = "";










    public void send() throws SQLException, ClassNotFoundException {
        takename();
        String msg = msgField.getText();

        String driver = "com.mysql.cj.jdbc.Driver";
        String databaseurl = "jdbc:mysql://localhost:3306/counselling";
        String username = "root";
        String passwords = "";
        Class.forName(driver);
        Connection conn = DriverManager.getConnection(databaseurl, username, passwords);
        String sql="INSERT INTO `chats`(`message`, `username`) VALUES (?, ?)";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1,msg);
        ps.setString(2,userName);

        ps.executeUpdate();

        conn.close();

        writer.println(userName  +" : " + msg);
        //writer.println(" ");
        msgRoom.setNodeOrientation(NodeOrientation.LEFT_TO_RIGHT);
        //msgRoom.appendText("Me: " + msg + "\n");

        msgField.setText("");
        if (msg.equalsIgnoreCase("BYE") || (msg.equalsIgnoreCase("logout"))) {
            System.exit(0);
        }
    }

    @FXML
    private void sendMessageByKey(KeyEvent event) throws SQLException, ClassNotFoundException {
        if (event.getCode().toString().equals("ENTER")) {
            send();
        }
    }

    @FXML
//    private void handleProfileBtn(ActionEvent event) {
//
//        if (event.getSource().equals(profileBtn) && !toggleProfile) {
//            new FadeTransition().play();
//            profile.toFront();
//            chat.toBack();
//            toggleProfile = true;
//            toggleChat = false;
//            profileBtn.setText("Back");
//            setProfile();
//        } else if (event.getSource().equals(profileBtn) && toggleProfile) {
//            new FadeTransition().play();
//            chat.toFront();
//            toggleProfile = false;
//            toggleChat = false;
//            profileBtn.setText("Profile");
//        }
//
//    }



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        clientName.setText(sname);


        String driver = "com.mysql.cj.jdbc.Driver";
        String databaseurl = "jdbc:mysql://localhost:3306/counselling";
        String username = "root";
        String passwords = "";
        try {
            Class.forName(driver);
            Connection conn = DriverManager.getConnection(databaseurl, username, passwords);
            String  sql="Select photo, LENGTH(photo) as len from student where id = ?";
            PreparedStatement pst=conn.prepareStatement(sql);
            pst.setString(1, sid1);
            ResultSet rs = pst.executeQuery();



            while(rs.next()){
                z=rs.getBlob(1);
                za=rs.getInt(2);








            }
            if(za>0){
                InputStream l = z.getBinaryStream();
                Image images = new Image(l);
                showProPic.setFill(new ImagePattern(images));

            }
            else{

            }

            conn.close();
            rs.close();
        }
        catch(Exception e){


        }







//        //info();
        takename();
//        System.out.println("Chat Room Controller user name : "+userName);
//        // gender1 = "Male";
//        showProPic.setStroke(Color.valueOf("#90a4ae"));
//        Image image;
//        if (gender1.equalsIgnoreCase("Male")) {
//            image = new Image(new File("src/main/resources/com/example/icons/user.png").toURI().toString(), false);//false chilos
//        } else {
//            image = new Image(new File("src/main/resources/com/example/icons/female.png").toURI().toString(), false);
//            proImage.setImage(image);
//        }
//        showProPic.setFill(new ImagePattern(image));
//        clientName.setText(String.valueOf(userName));
        connectSocket();

//        msgRoom.setText("bob\n");



        try {
            Class.forName(driver);
            Connection conn = DriverManager.getConnection(databaseurl, username, passwords);
            String sql = "SELECT * FROM chats ";
            PreparedStatement pst = conn.prepareStatement(sql);
//            pst.setString(1, sid1);

            ResultSet rsz = pst.executeQuery();


            while (rsz.next()) {
        //        list2.add(new studenthomeshow(rsz.getString("tname"), rsz.getString("stime"),rsz.getString("endtime"), rsz.getString("status"), rsz.getString("date")));
                msgRoom.appendText(rsz.getString(2)+" :- "+rsz.getString(1)+"\n");
            }

            conn.close();
            rsz.close();
        } catch (Exception e) {
        }
    }


    private void connectSocket() {
        try {
            socket = new Socket("127.0.0.1", 5555);
            System.out.println("Socket is connected with server!");
            reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            writer = new PrintWriter(socket.getOutputStream(), true);
            this.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public boolean saveControl = false;





    @Override
    public void run() {
        try {
            while (true) {
                String msg = reader.readLine();
                String[] tokens = msg.split(" ");
                String cmd = tokens[0];
                System.out.println(cmd);
                StringBuilder fulmsg = new StringBuilder();
                for(int i = 1; i < tokens.length; i++) {
                    fulmsg.append(tokens[i]);
                }
                System.out.println(fulmsg);
                if (cmd.equalsIgnoreCase(userName + ":")) {
                    continue;
                } else if(fulmsg.toString().equalsIgnoreCase("bye")) {
                    break;
                }
                msgRoom.appendText(msg + "\n");
            }
            reader.close();
            writer.close();
            socket.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
