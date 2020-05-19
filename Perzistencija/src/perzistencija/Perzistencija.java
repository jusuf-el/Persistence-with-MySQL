package perzistencija;

import java.sql.*;
import java.util.*;

/**
 * @author jusuf
 */
public class Perzistencija {

    public static void main(String[] args) {
        
        try (java.sql.Connection konekcija = DriverManager.getConnection("jdbc:mysql://localhost/perzistencija",
                "root", "");) {
            
            Scanner izbor = new Scanner(System.in);
            System.out.println("Moguće akcije:");
            System.out.println("1 - Prikaz svih zaposlenih iz baze");
            System.out.println("2 - Prikaz određenih zaposlenih iz baze po kriterijumu 'Ime'");
            System.out.println("3 - Izmjena podataka o zaposlenom na osnovu ID-a");
            System.out.println("4 - Brisanje zaposlenog iz baze na osnovu ID-a");
            System.out.println("5 - Unos novog zaposlenog u bazu");
            System.out.println("0 - Izlaz");
            System.out.println("------------------------------------------------------------------------------------------------");            
            System.out.println("Unesite odgovarajući broj za akciju koju želite!");
            
            String akcija = izbor.nextLine();    
            if("1".equals(akcija)) {
                //PRIKAZ SVIH ZAPOSLENIH IZ BAZE
                Zaposleni uposlenik2 = null;
                List<Zaposleni> zaposleni_collection = new ArrayList();
                
                Statement citanje = konekcija.createStatement();
                citanje.executeQuery("SELECT * FROM zaposleni");
                
                ResultSet result_set = citanje.getResultSet();
                while(result_set.next()) {
                    uposlenik2 = new Zaposleni(result_set.getString("ime"), result_set.getInt("godine"), 
                            result_set.getString("adresa"), result_set.getDouble("dohodak"));
                    zaposleni_collection.add(uposlenik2);
                }
                System.out.println(zaposleni_collection);
            }
            //PRIKAZ ODREĐENIH ZAPOSLENIH IZ BAZE NA OSNOVU IMENA (JA SAM IZABRAO TAJ KRITERIJUM)
            else if("2".equals(akcija)) {
                System.out.println("Unesite ime koje tražite u bazi!");
                Scanner trazenoIme = new Scanner(System.in);
                String unesenoIme = izbor.nextLine();
                
                Zaposleni uposlenik2 = null;
                List<Zaposleni> zaposleni_collection = new ArrayList();
                
                Statement citanje = konekcija.createStatement();
                citanje.executeQuery("SELECT * FROM zaposleni WHERE ime = '" + unesenoIme + "'");
                
                ResultSet result_set = citanje.getResultSet();
                while(result_set.next()) {
                    uposlenik2 = new Zaposleni(result_set.getString("ime"), result_set.getInt("godine"), 
                            result_set.getString("adresa"), result_set.getDouble("dohodak"));
                    zaposleni_collection.add(uposlenik2);
                    System.out.println(zaposleni_collection);
                }
            }
            //IZMJENE PODATAKA U BAZI NA OSNOVU ID-A
            else if("3".equals(akcija)) {
                System.out.println("Prije ovog koraka izlistajte sve zapise iz baze koristeći opciju broj 1.");
                System.out.println("Unesite ID zaposlenog kojem želite promijeniti informacije! "
                        + "(ID mora da postoji u bazi, u protivnom nećete dobiti nikakav odgovor iz baze)");
                Scanner trazeniID = new Scanner(System.in);
                String uneseniID = izbor.nextLine();
                
                Zaposleni uposlenik2 = null;
                List<Zaposleni> zaposleni_collection = new ArrayList();
                
                //IZLISTAVANJE PODATAKA ZAPOSLENOG ČIJI ID JE UNESEN 
                Statement citanje = konekcija.createStatement();
                citanje.executeQuery("SELECT * FROM zaposleni WHERE zaposleni_id = '" + uneseniID + "'");
                
                ResultSet result_set = citanje.getResultSet();
                while(result_set.next()) {
                    uposlenik2 = new Zaposleni(result_set.getString("ime"), result_set.getInt("godine"), 
                            result_set.getString("adresa"), result_set.getDouble("dohodak"));
                    zaposleni_collection.add(uposlenik2);
                    System.out.println(zaposleni_collection);
                }
                
                //IZMJENA IMENA
                System.out.println("Unesite ime koje želite da bude u bazi za zaposlenog kojeg ste prethodno izabrali! "
                        + "Ukoliko ne želite mijenjati ime, ostavite prazno (tj. pritisnite ENTER bez ikakvog unosa sa tastature)");
                Scanner novoIme = new Scanner(System.in);
                String unesenoIme = izbor.nextLine();
                
                if(unesenoIme.isEmpty() == false) {
                    Zaposleni uposlenik = null;
                    List<Zaposleni> zaposleni_collection2 = new ArrayList();
                    
                    Statement izmjenaImena = konekcija.createStatement();
                    izmjenaImena.execute("UPDATE zaposleni SET ime = '" + unesenoIme + "' WHERE zaposleni_id = '" + uneseniID + "'");
                }
                else
                    System.out.println("Ime nije promijenjeno!");
                
                //IZMJENA GODINA
                System.out.println("Unesite broj godina koje želite da budu u bazi za zaposlenog kojeg ste prethodno izabrali! "
                        + "Ukoliko ne želite mijenjati godine, ostavite prazno (tj. pritisnite ENTER bez ikakvog unosa sa tastature)");
                Scanner noveGodine = new Scanner(System.in);
                String uneseneGodine = izbor.nextLine();
                
                if(uneseneGodine.isEmpty() == false) {
                    Zaposleni uposlenik = null;
                    List<Zaposleni> zaposleni_collection2 = new ArrayList();
                    
                    Statement izmjenaGodina = konekcija.createStatement();
                    izmjenaGodina.execute("UPDATE zaposleni SET godine = '" + uneseneGodine + "' WHERE zaposleni_id = '" + uneseniID + "'");
                }
                else
                    System.out.println("Godine nisu promijenjene!");
                
                //IZMJENA ADRESE
                System.out.println("Unesite adresu koju želite da bude u bazi za zaposlenog kojeg ste prethodno izabrali! "
                        + "Ukoliko ne želite mijenjati adresu, ostavite prazno (tj. pritisnite ENTER bez ikakvog unosa sa tastature)");
                Scanner novaAdresa = new Scanner(System.in);
                String unesenaAdresa = izbor.nextLine();
                
                if(unesenoIme.isEmpty() == false) {
                    Zaposleni uposlenik = null;
                    List<Zaposleni> zaposleni_collection2 = new ArrayList();
                    
                    Statement izmjenaAdrese = konekcija.createStatement();
                    izmjenaAdrese.execute("UPDATE zaposleni SET adresa = '" + unesenaAdresa + "' WHERE zaposleni_id = '" + uneseniID + "'");
                }
                else
                    System.out.println("Adresa nije promijenjena!");
                
                //IZMJENA DOHOTKA
                System.out.println("Unesite visinu dohotka koji želite da bude u bazi za zaposlenog kojeg ste prethodno izabrali! "
                        + "Ukoliko ne želite mijenjati visinu dohotka, ostavite prazno (tj. pritisnite ENTER bez ikakvog unosa sa tastature)");
                Scanner noviDohodak = new Scanner(System.in);
                String uneseniDohodak = izbor.nextLine();
                
                if(unesenoIme.isEmpty() == false) {
                    Zaposleni uposlenik = null;
                    List<Zaposleni> zaposleni_collection2 = new ArrayList();
                    
                    Statement izmjenaDohotka = konekcija.createStatement();
                    izmjenaDohotka.execute("UPDATE zaposleni SET dohodak = '" + uneseniDohodak + "' WHERE zaposleni_id = '" + uneseniID + "'");
                }
                else
                    System.out.println("Visina dohotka nije promijenjena!");
                System.out.println("Izmjene su izvršene!");
            }
            
            //BRISANJE ZAPOSLENOG IZ BAZE NA OSNOVU ID-A
            else if("4".equals(akcija)) {
                System.out.println("Unesite ID broj zaposlenog kojeg želite obrisati iz baze!"
                        + "(ID mora da postoji u bazi, u protivnom nećete se desiti nikakva promjena u bazi)");
                Scanner trazeniID = new Scanner(System.in);
                String uneseniID = izbor.nextLine();
                
                Zaposleni uposlenik2 = null;
                List<Zaposleni> zaposleni_collection = new ArrayList();
                
                Statement citanje = konekcija.createStatement();
                citanje.execute("DELETE FROM zaposleni WHERE zaposleni_id = '" + uneseniID + "'");
                
                System.out.println("Uposlenik uspješno izbrisan iz baze!");
            }
            
            //UNOS NOVOG ZAPOSLENOG U BAZU
            else if("5".equals(akcija)) {
                System.out.println("Unesite ime novog zaposlenog!");
                Scanner novoIme = new Scanner(System.in);
                String unesenoIme = izbor.nextLine();
                System.out.println("Unesite godine novog zaposlenog!");
                Scanner noveGodine = new Scanner(System.in);
                String uneseneGodine = izbor.nextLine();
                System.out.println("Unesite adresu novog zaposlenog!");
                Scanner novaAdresa = new Scanner(System.in);
                String unesenaAdresa = izbor.nextLine();
                System.out.println("Unesite visinu dohotka novog zaposlenog!");
                Scanner noviDohodak = new Scanner(System.in);
                String uneseniDohodak = izbor.nextLine();
                
                Zaposleni uposlenik = new Zaposleni (unesenoIme, Integer.parseInt(uneseneGodine), unesenaAdresa, Integer.parseInt(uneseniDohodak));
                PreparedStatement unos = konekcija.prepareStatement("INSERT INTO zaposleni (ime, godine, adresa, dohodak)"
                    + "VALUES (?, ?, ?, ?)");
                unos.setString(1, uposlenik.getIme());
                unos.setString(2, String.valueOf(uposlenik.getGodine()));
                unos.setString(3, uposlenik.getAdresa());
                unos.setString(4, String.valueOf(uposlenik.getDohodak()));
                unos.execute();
            }
            else if("0".equals(akcija)) {
                System.exit(0);
            }
            else
                System.out.println("Pogrešan unos!");       
        }
        catch (SQLException ex) {
            System.out.println("Error in database connection: \n" + ex.getMessage());
        }
        
    }
    
}
