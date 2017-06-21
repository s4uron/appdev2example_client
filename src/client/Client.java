/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
// import damit der Client alle methoden aus dem Interface kennt
import server.BenutzerverwaltungInterface;

/**
 *
 * @author Jan
 */
public class Client {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            Scanner scanner = new Scanner(System.in);
            // Verbindung zum Server wird hergestellt
            BenutzerverwaltungInterface benutzerverwaltung = (BenutzerverwaltungInterface) Naming.lookup("rmi://localhost:1099/benutzerverwaltung");
            // die verschiedenen Methoden zum anlegen, anzeigen oder löschen von Benutzern werden
            // vom Client auf dem Server ausgeführt
            String eingabe = "";
            boolean loop = true;
            while(loop) {
                System.out.println("Benutzername oder 'exit' oder 'liste' oder 'clear' eingeben:");
                eingabe = scanner.next();       
                if(eingabe.equals("exit")) {
                    loop = false;
                } else if(eingabe.equals("liste")) {
                    System.out.println("Alle Benutzer die angelegt wurden:");
                    System.out.println("------------------------------------");
                    System.out.println(benutzerverwaltung.alleBenutzer());
                } else if(eingabe.equals("clear")) {
                    benutzerverwaltung.alleBenutzerLoeschen();
                    System.out.println("Alle Benutzer wurden gelöscht.");
                }else {
                    benutzerverwaltung.benutzerErstellen(eingabe);
                    System.out.println("Benutzer "+eingabe+" wurde angelegt.");
                }
            }
        } catch (NotBoundException ex) {
            System.out.println("Fehler: "+ex.getMessage());
        } catch (MalformedURLException ex) {
            System.out.println("Fehler in der rmi Adresse: "+ex.getMessage());
        } catch (RemoteException ex) {
            System.out.println("Ein remote fehler ist aufgetreten: "+ex.getMessage());
        }
    }
    
}
