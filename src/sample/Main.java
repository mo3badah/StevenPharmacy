package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.FlowPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

import java.awt.*;
import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.Scanner;

@SuppressWarnings("ALL")
public class Main extends Application {
    //make my variables
    //making interface
    //start by 4 buttons
    Button add = new Button("Add");
    Button update = new Button("Update");
    Button delete = new Button("Delete");
    Button buy = new Button("Buy Medicine");
    // 2 textboxes
    Text purchasebill = new Text(400, 300, "Your Purchase ");
    Text message = new Text(400, 300, "notice");
    // 8 lbels
    Label Lwelcome = new Label(" WELCOME  \n AT STEVEN PHARMACY ");
    Label Lname = new Label(" Name   :    ");
    Label Lid = new Label("     ID     :   ");
    Label Lrack = new Label(" Rack no  : ");
    Label Lcabient = new Label(" Cabient no : ");
    Label Lsupplier = new Label(" supplier 's name :");
    Label LnoUnit = new Label(" No of Unit : ");
    Label LsalesCost = new Label(" Sales Cost : ");
    // 7 text fields
    TextField name = new TextField();
    TextField id = new TextField();
    TextField rack = new TextField();
    TextField cabient = new TextField();
    TextField supplier = new TextField();
    TextField noUnit = new TextField();
    TextField salesCost = new TextField();
    Font font1=new Font(18);
    String medicine1 = "medicine1.txt";
    String medicine2 = "medicine2.txt";
    static double totalPrice =0 ;



    @Override
    public void start(Stage primaryStage) throws Exception{
        // make new flow panes
        FlowPane Hpane=new FlowPane(Orientation.HORIZONTAL,10,10);
//        FlowPane Vpane=new FlowPane(Orientation.VERTICAL,10,10);
        // panes padding
        Hpane.setPadding(new Insets(10));
//        Vpane.setPadding(new Insets(10));
        // add objects to panes and edit
        Hpane.getChildren().setAll(Lwelcome,Lname,name,Lid,id,Lrack,rack,Lcabient,cabient,Lsupplier,supplier,LnoUnit,noUnit,LsalesCost,salesCost,add,update,delete,buy,message,purchasebill);
        Lwelcome.setMinSize(700,50);
        Lwelcome.setTextAlignment(TextAlignment.CENTER);
        Lwelcome.setFont(font1);
        purchasebill.setFont(font1);
        purchasebill.setFill(Color.BLUE);
        message.setFill(Color.RED);
        message.setFont(font1);








//      my buttons action calls
        add.setOnAction(event -> AddNewMedicine());
        update.setOnAction(event -> ModifyMedcine());
        delete.setOnAction(event -> DeleteMedicine());
        buy.setOnAction(event -> AddPurshase());














//        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("STEVEN PHARMACY");
        primaryStage.setScene(new Scene(Hpane, 700, 700));
        primaryStage.show();
    }
    void AddNewMedicine() {
        // save data from textfields
        String sname = name.getText();
        String ssuplier = supplier.getText();
        long sid =Long.parseLong(id.getText());
        long srack =Long.parseLong(rack.getText());
        long scabient =Long.parseLong(cabient.getText());
        long snoUnit =Long.parseLong(noUnit.getText());
        double ssalesCost =Double.parseDouble(salesCost.getText());
        boolean exist = false;
        //add afile
        File storing = new File(medicine1);
        //write in the file with try and catch
        try {
            PrintWriter pri = new PrintWriter(new FileOutputStream(new File(medicine1), true));//to write in main file
            Scanner scan = new Scanner(new File(medicine1));// to read from our file
            scan.useDelimiter("[,\n]");//to spacific saving data
            while (scan.hasNext()) {//loop to search if exist
                String isname = scan.next();
                long isid =Long.parseLong(scan.next());
                long israck =Long.parseLong(scan.next());
                long iscabient =Long.parseLong(scan.next());
                String issuplier = scan.next();
                long isnoUnit =Long.parseLong(scan.next());
                double issalesCost =Double.parseDouble(scan.next());
                System.out.println("new1");
                if (sname.equals(isname) || sid==isid ){
                    exist = true;
                }

            }
            System.out.println("new1");
            if (sname.equals("") || id==null ) {//to ignore null data
                message.setText("please enter id and name fields at least");
            } else if (exist) {////to ignore exist data
                message.setText("Repeated data enter anew one");
            } else {//to add to main
                pri.println(sname + "," + sid + "," + srack+","+scabient+","+ssuplier+","+snoUnit+","+ssalesCost);
                pri.flush();
                pri.close();
                message.setText("your new medicine has been Added successfully");//message
            }
            ClearFields();////clear fields


        } catch (Exception e) {
            message.setText("there is an error plese enter valid data");
        }


    }
    @SuppressWarnings("ResultOfMethodCallIgnored")
    void ModifyMedcine() {
        // save data from textfields
        // save data from textfields
        String sname = name.getText();
        String ssuplier = supplier.getText();
        long sid =Long.parseLong(id.getText());
        long srack =Long.parseLong(rack.getText());
        long scabient =Long.parseLong(cabient.getText());
        long snoUnit =Long.parseLong(noUnit.getText());
        double ssalesCost =Double.parseDouble(salesCost.getText());
        boolean exist = false;
        //add afile
        File storing = new File(medicine1);
        File temp = new File(medicine2);
        //write in the file with try and catch
        try {
            PrintWriter pri = new PrintWriter(new FileOutputStream(new File(medicine2), true));//to write in alternative file
            Scanner scan = new Scanner(new File(medicine1));// to read from main file
            scan.useDelimiter("[,\n]");//to spacific saving data
            while (scan.hasNext()) {//loop to search
                String isname = scan.next();
                long isid =Long.parseLong(scan.next());
                long israck =Long.parseLong(scan.next());
                long iscabient =Long.parseLong(scan.next());
                String issuplier = scan.next();
                long isnoUnit =Long.parseLong(scan.next());
                double issalesCost =Double.parseDouble(scan.next());
                if (sname.equals(isname) || sid==isid ) {//if is wanted one replace old with new in the alternative file
                    pri.println(sname + "," + sid + "," + srack+","+scabient+","+ssuplier+","+snoUnit+","+ssalesCost);
                    message.setText("medicine has been Modified successfully");//alert message
                    exist=true;

                } else {//if not wanted one save them in the alternative file
                    pri.println(isname + "," + isid + "," + israck+","+iscabient+","+issuplier+","+isnoUnit+","+issalesCost);
                }
            }
            if (!exist) {//if not matched data try again
                message.setText("medicine name or id you want modify has Not Founded   \n ...........Try again.......");
            }
            pri.flush();//save data writed in alternative file
            pri.close();//close writing in alternative file
            scan.close();//close reading from main
            storing.delete();//delete main file(error from ide )
            File flash = new File(medicine1);//new file for renaming
            //noinspection ResultOfMethodCallIgnored
            temp.renameTo(flash);//rename alternative file as the main one for other process

        } catch (Exception e) {
            message.setText("there is an error");
        }
        ClearFields();//clear fields
    }
    void DeleteMedicine() {
        // save data from textfields
        // save data from textfields
        String sname = name.getText();
//        long sid=-1;
        System.out.println("before");// check
//        if (id.getText()!=null){sid =Long.parseLong(id.getText());}// so can we oveeride null value in long variable
        System.out.println("after");//check
        boolean exist = false;//to search existance
        //add afile
        File storing = new File(medicine1);//original
        File temp = new File(medicine2);//temporary
        //write in the file with try and catch
        try {
            PrintWriter pri = new PrintWriter(new FileOutputStream(new File(medicine2), true));//to write in alternative file
            Scanner scan = new Scanner(new File(medicine1));// to read from main file
            scan.useDelimiter("[,\n]");//to spacific saving data
            while (scan.hasNext()) {//loop to search
                // reading values from file
                String isname = scan.next();
                long isid =Long.parseLong(scan.next());
                long israck =Long.parseLong(scan.next());
                long iscabient =Long.parseLong(scan.next());
                String issuplier = scan.next();
                long isnoUnit =Long.parseLong(scan.next());
                double issalesCost =Double.parseDouble(scan.next());
                if (sname.equals(isname) /*|| sid==isid */ ) {//if is wanted one replace old with new in the alternative file
                    message.setText("medicine has been Deleted successfully");//alert message
                    exist=true;//is founded

                } else {//if not wanted one save them in the alternative file
                    pri.println(isname + "," + isid + "," + israck+","+iscabient+","+issuplier+","+isnoUnit+","+issalesCost);

                }

            }
            if (!exist) {//if not matched data try again
                message.setText("medicine name  you want delete has Not Founded   \n ...........Try again.......");
            }
            pri.flush();//save data writed in alternative file
            pri.close();//close writing in alternative file
            scan.close();//close reading from main
            storing.delete();//delete main file(error from ide )
            File flash = new File(medicine1);//new file for renaming
            //noinspection ResultOfMethodCallIgnored
            temp.renameTo(flash);//rename alternative file as the main one for other process

        } catch (Exception e) {
            message.setText("there is an error");
        }
        ClearFields();//clear fields
    }
    void AddPurshase(){
        // save data from textfields
        // save data from textfields
        String sname = name.getText();
        long sid=-1;
        long snoUnit=1;
        boolean close =false;
        String messa=purchasebill.getText();
        System.out.println("before");// check
//        if (id.getText()!=""){sid =Long.parseLong(id.getText());}// so can we oveeride null value in long variable
//        if (noUnit.getText()!=""){snoUnit =Long.parseLong(noUnit.getText());}// so can we oveeride null value in long variable
        System.out.println("after");//check
        boolean exist = false;//to search existance
        //add afile
        File storing = new File(medicine1);//original
        message.setText(" to select your medicine please enter Name or ID and No Of Units \n if you finished press again with null fields to have total price\n .......Thank You......");
        //write in the file with try and catch
        try {
            snoUnit =Long.parseLong(noUnit.getText());
            Scanner scan = new Scanner(new File(medicine1));// to read from main file
            scan.useDelimiter("[,\n]");//to spacific saving data
            while (scan.hasNext()) {//loop to search
                // reading values from file
                String isname = scan.next();
                long isid =Long.parseLong(scan.next());
                long israck =Long.parseLong(scan.next());
                long iscabient =Long.parseLong(scan.next());
                String issuplier = scan.next();
                long isnoUnit =Long.parseLong(scan.next());
                double issalesCost =Double.parseDouble(scan.next());
                if (sname.equals(isname) /*|| sid==isid*/ ) {//if is wanted one replace old with new in the alternative file
                    sid=isid;
                    exist=true;//is founded
                    messa+="\n medicine name: "+isname+"  id: "+isid+"  no of units: "+isnoUnit+"  price: "+issalesCost ; }
                    totalPrice+=isnoUnit*issalesCost;

            }
            if (!exist) {//if not matched data try again
                message.setText("medicine name or id you want buy has Not Founded   \n ...........Try again.......");
            }
            scan.close();//close reading from main


        } catch (Exception e) {
            message.setText("enter field name and NO OF UniTs ");
            messa+="\n  YOUR TOTAL BILL IS :   "+totalPrice;
        }
        if (name.getText()=="" && id.getText()==""){messa+="\n  YOUR TOTAL BILL IS :   "+totalPrice;}//to end process and buying
        purchasebill.setText(messa);

        ClearFields();//clear fields
    }



    void ClearFields() {
        name.setText("");
        id.setText("");
        rack.setText("");
        cabient.setText("");
        supplier.setText("");
        noUnit.setText("");
        salesCost.setText("");

    }



    public static void main(String[] args) {
        launch(args);
    }
}
