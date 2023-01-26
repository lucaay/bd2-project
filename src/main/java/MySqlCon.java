import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

import io.github.cdimascio.dotenv.Dotenv;
import io.github.cdimascio.dotenv.DotenvException;
import net.minidev.json.JSONObject;

import javax.swing.*;

class MysqlCon{

    Dotenv dotenv = null;

    int currentLoggedInAccountType = 0;

    public MysqlCon(){
        dotenv = Dotenv.configure().load();
    }

    public boolean verifyLogin(String email, String password){
        try{
            Connection con=DriverManager.getConnection(
                    dotenv.get("DATABASE_URL"), dotenv.get("DATABASE_LOGIN"),dotenv.get("DATABASE_PASSWORD"));
            Statement stmt=con.createStatement();
            ResultSet rs=stmt.executeQuery("select * from utilizator where email = '" + email + "' and parola = '" + password + "'");
            if(rs.next()){
                currentLoggedInAccountType = rs.getInt(10);
                return true;
            }
            con.close();
        }catch(Exception e){
            JOptionPane.showMessageDialog(null ,"Eroare la conectarea la baza de date");
        }
        return false;
    }

    public void createClientAccount(String name, String lastName, String phone, String state, String city, String address, String email, String password, String accountType){
        int accountTypeInt = accountType.equals("Client") ? 0 : 1;
        try{
            Connection con=DriverManager.getConnection(
                    dotenv.get("DATABASE_URL"), dotenv.get("DATABASE_LOGIN"),dotenv.get("DATABASE_PASSWORD"));
            Statement stmt=con.createStatement();
            stmt.executeUpdate("insert into utilizator (nume, prenume, telefon, judet, localitate, adresa, email, parola, ID_ROL) values ('" + name + "', '" + lastName + "', '" + phone + "', '" + state + "', '" + city + "', '" + address + "', '" + email + "', '" + password + "', '" + accountTypeInt + "')");
            con.close();
        }catch(Exception e){
            System.out.println(e);
            JOptionPane.showMessageDialog(null ,"Eroare la conectarea la baza de date");
        }
    }
    public void createAdminAccount(String name, String lastName, String phone, String email, String password, String accountType){
        int accountTypeInt = accountType.equals("Administrator") ? 1 : 0;
        try{
            Connection con=DriverManager.getConnection(
                    dotenv.get("DATABASE_URL"), dotenv.get("DATABASE_LOGIN"),dotenv.get("DATABASE_PASSWORD"));
            Statement stmt=con.createStatement();
            stmt.executeUpdate("insert into utilizator (nume, prenume, telefon, email, parola, ID_ROL) values ('" + name + "', '" + lastName + "', '" + phone + "', '" + email + "', '" + password + "', '" + accountTypeInt + "')");
            con.close();
        }catch(Exception e){
            System.out.println(e);
            JOptionPane.showMessageDialog(null ,"Eroare la conectarea la baza de date");
        }
    }


    public Client[] retrieveClients(){
        Client[] clientData = new Client[100];
        try{
            Connection con=DriverManager.getConnection(
                    dotenv.get("DATABASE_URL"), dotenv.get("DATABASE_LOGIN"),dotenv.get("DATABASE_PASSWORD"));
            Statement stmt=con.createStatement();
            ResultSet rs=stmt.executeQuery("select * from utilizator where ID_ROL = 0");

            int i = 0;
            while(rs.next()){
                Client client = new Client(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8));
                clientData[i++] = client;
            }
            con.close();
        }catch(Exception e){
            System.out.println(e);
            JOptionPane.showMessageDialog(null ,"Eroare la conectarea la baza de date");
        }
        ArrayList<Client> list = new ArrayList<>(Arrays.asList(clientData));
        list.removeIf(Objects::isNull);
        return list.toArray(new Client[list.size()]);
    }

    public void updateClient(int id, String name, String lastName, String email, String phone, String state, String city, String address){
        try{
            Connection con=DriverManager.getConnection(
                    dotenv.get("DATABASE_URL"), dotenv.get("DATABASE_LOGIN"),dotenv.get("DATABASE_PASSWORD"));
            Statement stmt=con.createStatement();
            stmt.executeUpdate("update utilizator set nume = '" + name + "', prenume = '" + lastName + "', email = '" + email + "', telefon = '" + phone + "', judet = '" + state + "', localitate = '" + city + "', adresa = '" + address + "' where ID_Utilizator = " + id);
            con.close();
        }catch(Exception e){
            System.out.println(e);
            JOptionPane.showMessageDialog(null ,"Eroare la conectarea la baza de date");
        }
    }

    public Receipt[] retrieveReceipts(){
        Receipt[] receiptData = new Receipt[100];
        try{
            Connection con=DriverManager.getConnection(
                    dotenv.get("DATABASE_URL"), dotenv.get("DATABASE_LOGIN"),dotenv.get("DATABASE_PASSWORD"));
            Statement stmt=con.createStatement();
            ResultSet rs=stmt.executeQuery("select * from factura");

            int i = 0;
            while(rs.next()){
                Receipt receipt = new Receipt(rs.getInt(1), rs.getInt(2), rs.getString(3), rs.getInt(4), rs.getString(5));
                receiptData[i++] = receipt;
            }
            con.close();
        }catch(Exception e){
            System.out.println(e);
            JOptionPane.showMessageDialog(null ,"Eroare la conectarea la baza de date");
        }
        ArrayList<Receipt> list = new ArrayList<>(Arrays.asList(receiptData));
        list.removeIf(Objects::isNull);
        return list.toArray(new Receipt[list.size()]);
    }

    public void updateReceipt(int id, int idClient, String date, int price, String products){
        try{
            Connection con=DriverManager.getConnection(
                    dotenv.get("DATABASE_URL"), dotenv.get("DATABASE_LOGIN"),dotenv.get("DATABASE_PASSWORD"));
            Statement stmt=con.createStatement();
            stmt.executeUpdate("update factura set ID_Utilizator = '" + idClient + "', data = '" + date + "', pret = '" + price + "', produse = '" + products + "' where ID_Factura = " + id);
            con.close();
        }catch(Exception e){
            System.out.println(e);
            JOptionPane.showMessageDialog(null ,"Eroare la conectarea la baza de date");
        }
    }

    public Object[] retrieveProducts(){
        Object[] productsData = new Object[100];
        try{
            Connection con=DriverManager.getConnection(
                    dotenv.get("DATABASE_URL"), dotenv.get("DATABASE_LOGIN"),dotenv.get("DATABASE_PASSWORD"));
            Statement stmt=con.createStatement();
            ResultSet rs=stmt.executeQuery("select * from produs");
            int i = 0;
            while(rs.next()){
                String[] product = new String[20];
                for (int j = 0; j < product.length; j++) {
                    product[j] = rs.getString(j+1);
                }
                productsData[i++] = product;
            }
            con.close();
        }catch(Exception e){
            System.out.println(e);
            JOptionPane.showMessageDialog(null ,"Eroare la conectarea la baza de date");
        }
        ArrayList<Object> list = new ArrayList<>(Arrays.asList(productsData));
        list.removeIf(Objects::isNull);
        return list.toArray(new Object[list.size()]);
    }
    public void updateProduct(String name, String componentType, String chipset, String socket, String memoryType, String memoryMax, String memorySlots, String memoryFrequency, String modulation, String memoryFrequencyEffective, String series, String numberOfCores, String freq, String power, String capacity, String ssdType, String readMax, String writeMax, String memorySize, String coolingSystem){
        try{
            Connection con=DriverManager.getConnection(
                    dotenv.get("DATABASE_URL"), dotenv.get("DATABASE_LOGIN"),dotenv.get("DATABASE_PASSWORD"));
            Statement stmt=con.createStatement();
            stmt.executeUpdate("update produs set denumire = '" + name + "', chipset = '" + chipset + "', socket = '" + socket + "', tip_memorie = '" + memoryType + "', memorie_maxima = '" + memoryMax + "', numar_sloturi = '" + memorySlots + "', frecventa_memorie = '" + memoryFrequency + "', modulara = '" + modulation + "', frecventa_memorie_efectiva = '" + memoryFrequencyEffective + "', numar_nuclee = '" + numberOfCores + "', frecventa = '" + freq + "', putere = '" + power + "', capacitate = '" + capacity + "', tip_ssd = '" + ssdType + "', citire_maxima = '" + readMax + "', scriere_maxima = '" + writeMax + "', dimensiune_memorie = '" + memorySize + "', sistem_racire = '" + coolingSystem + "' where serie = '" + series + "'");
            con.close();
        }catch(Exception e){
            System.out.println(e);
            JOptionPane.showMessageDialog(null ,"Eroare la conectarea la baza de date");
        }
    }

    public int getCurrentLoggedInAccountType(){
        return currentLoggedInAccountType;
    }


}