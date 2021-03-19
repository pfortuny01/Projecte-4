package projecte4_agencia;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Scanner;

public class Projecte_4 {
    
    final String nom_empresa = "TRAGEN (Travel Agency)";
    final String mail_empresa = "travel@gmail.com";
    final String tel_empresa = "973 01 11 50";
    final String ubi_empresa = "Carrer World Trade Center 11, Nova York";
    
    final Double IVA = 21.0;

    static Scanner teclat = new Scanner(System.in);

    public void main(String[] args) throws SQLException, FileNotFoundException {

        int opcio;
        boolean sortir = false;

        while (!sortir) {
            System.out.println("********BENVINGUT A TRAVEL AGENCY********");
            System.out.println("*---------------------------------------*");
            System.out.println("*            Tria una opció:            *");
            System.out.println("*---------------------------------------*");
            System.out.println("* 1. Facturar Comanda                   *");
            System.out.println("* 2. Sortir                             *");
            System.out.println("*****************************************");

            if (!teclat.hasNextInt()) {
                System.out.println("ERROR! Has d'introduir un número!");
                teclat.next();
            } else {
                opcio = teclat.nextInt();

                switch (opcio) {
                    case 1:
                        /*LLEGIR PER TECLAT*/
                        Scanner read = new Scanner(System.in);
                        
                        /*DECLARACIO VARIABBLES*/
                        int id;
                        String dni;
                        String mail;
                        String[] dades_factura;
                        String[][] productes = null;
                        
                        /*PREGUNTAR ID*/
                        System.out.println("Has triat: Facturar Comanda");
                        System.out.print("Insereixi l'ID de la factura: ");
                        
                        /*AGAFAR TEXT PER TECLAT*/
                        id = read.nextInt();
                        
                        /*CRIDA ALS METODES DELS ALTRES ARXIU JAVA*/
                        Sentencies_SQL sql = new Sentencies_SQL();
                        Validacions valid = new Validacions();
                        
                        /*AGAFAR DNI I MAIL A LA VARIABLE*/
                        dni = sql.comprovar_dni(id);
                        mail = sql.comprovar_email(id);
                        
                        /*COMPROVAR DNI, COMPROVAR MAIL I GENERAR FACTURA SI SON CORRECTES*/
                        if (valid.validar_dni(dni)) {
                            if (valid.validar_mail(mail)) {
                                
                                dades_factura = sql.select_factura(id);
                                productes = sql.select_prod(id);
                                    
                                File fact = new File ("C:/Documentos/Factures/facturaclient_" +id+ "_" +LocalDate.now()+".txt");
                                PrintWriter write = null;
                                
                                write = new PrintWriter(fact);
                                
                                write.println("\n\t\t   FACTURA DE LA COMANDA");
                                
                                write.println("\n\n\t\t DADES EMPRESA:");
                                write.println("\n\t\t Nom: "+nom_empresa+"\t\t Ubicació: "+ubi_empresa+"");
                                write.println("\n\t\t E-mail empresa: "+mail_empresa+"\t\t Telèfon empresa: "+tel_empresa+"");
                                
                                write.println("\n\n\t\t Info. Client:");
                                write.println("\n\t\t Dni: "+dni+"\t\t Nom: "+dades_factura[0]+"E-mail: "+mail+"");
                                
                                write.println("\n\n\t\t ID Factura: "+dades_factura[1]+"");
                                
                                write.println("\n\n\t\t PRODUCTES");
                                write.println("\n\t\t ***************************************************************");
                                
                                int Preutotal = 0;
                                /*CONTADOR QUE RECORRE ARRAY PRODUCTES I ELS MOSTRA*/
                                for (int i=0; i<productes.length; i++){
                                    int num = 1;
                                    
                                    write.println("\n\t\t Prod. "+num+" : Nom: "+productes[i][0]+" Cat: "+productes[i][1]+" Preu: "+productes[i][2]+"");
                                    
                                    Preutotal = Preutotal+ Integer.parseInt(productes[i][2]);
                                    num++;
                                }
                                write.println("\n\t\tPreu total: "+Preutotal+"€");
                                write.println("\n\t\t****************************************************************");
                                
                                write.close();
                                
                            }else{
                                System.out.println("ERROR: Email no vàlid.");
                                break;
                            }
                        }else{
                             System.out.println("ERROR: DNI no vàlid.");
                             break;
                        }

                        break;
                    case 2:
                        System.out.println("Has triat: Sortir");
                        System.out.println("Adeu!");
                        sortir=true;
                        break;

                    default:
                        System.out.println("ERROR: Opció incorrecta");
                        System.out.println("Torni-ho a provar");
                        break;
                }
            }

        }

    }

}
