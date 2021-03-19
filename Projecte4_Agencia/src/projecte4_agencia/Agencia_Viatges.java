
package projecte4_agencia;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.util.Scanner;

public class Agencia_Viatges {
    
    final static String nom_empresa = "TRAGEN (Travel Agency)";
    final static String mail_empresa = "travel@gmail.com";
    final static String tel_empresa = "973 01 11 50";
    final static String ubi_empresa = "Carrer World Trade Center 11, Nova York";
    
    final static Double IVA = 21.0;

    static Scanner teclat = new Scanner(System.in);

    public static void main(String[] args){
    int opcio;
        boolean sortir = false;

        while (!sortir) {
            System.out.println("********BENVINGUT A TRAVEL AGENCY********");
            System.out.println("*---------------------------------------*");
            System.out.println("*            Tria una opció:            *");
            System.out.println("*---------------------------------------*");
            System.out.println("* 1. Facturar Comanda                   *");
            System.out.println("* 2. Ofertes                            *");
            System.out.println("* 3. Sortir                             *");
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

                        /*COMPROVAR DNI, COMPROVAR MAIL I GENERAR FACTURA SI SON CORRECTES*/
                        if(sql.comprovarFactura(id)){
                            if(sql.pagada(id)){
                                /*AGAFAR DNI I MAIL A LA VARIABLE*/
                                dni = sql.comprovar_dni(id);
                                mail = sql.comprovar_email(id);
                                if (valid.validar_dni(dni)) {
                                    if (valid.validar_mail(mail)) {

                                        dades_factura = sql.select_factura(id);
                                            if(dades_factura==null){
                                                System.out.print("No hi ha dades d'aquesta factura.");
                                                break;
                                            }
                                        productes = sql.select_prod(id);
                                            if(productes==null){
                                                System.out.print("No hi ha productes en aquesta factura.");
                                                break;
                                            }

                                        File impfact = new File ("C:/FacturesClients/factures/facturaclient_" +id+ "_" +LocalDate.now()+".txt");
                                        PrintWriter write = null;

                                        try {
                                            write = new PrintWriter(impfact);

                                        write.println("\n\t\t FACTURA DE LA COMANDA");

                                        write.println("\n\n\t\t DADES EMPRESA:");
                                        write.println("\n\t\t Nom: "+nom_empresa+"\t\t Ubicació: "+ubi_empresa+"");
                                        write.println("\n\t\t E-mail empresa: "+mail_empresa+"\t\t Telèfon empresa: "+tel_empresa+"");

                                        write.println("\n\n\t\t Info. Client:");
                                        write.println("\n\t\t Dni: "+dni+"   Nom: "+dades_factura[0]+"   E-mail: "+mail+"");

                                        write.println("\n\n\t\t ID Factura: "+dades_factura[1]+"");

                                        write.println("\n\n\t\t PRODUCTES");
                                        write.println("\n\t\t ***************************************************************");
                                        write.println("\n\t\t             NOM PRODUCTE    CATEGORIA    PREU");
                                        
                                        /*COMPROVAR SI TE DESCOMPTE AQUESTA FACTURA*/
                                        String [] rebaixa;
                                        
                                        rebaixa = sql.rebaixa_factura(id);
                                        
                                        double Preutotal = 0;
                                        int num = 1;
                                        /*CONTADOR QUE RECORRE ARRAY PRODUCTES I ELS MOSTRA*/
                                        for (int i=0; i<productes.length; i++){
                                            
                                            if(productes[i][0]!=null){
                                                write.println("\n\t\t Prod. "+num+":    "+productes[i][0]+"    "+productes[i][1]+"     "+productes[i][2]+"");

                                                num++;

                                                try{

                                                Preutotal = Preutotal+ Integer.parseInt(productes[i][2]);
                                                } catch (NumberFormatException e) {

                                                }
                                            }
                                        }
                                        double preu_iva = (((Preutotal/100)*21)+Preutotal);
                                        write.println("\n\t\tPreu total S/I: "+Preutotal+"€ + 21% I.V.A. = "+preu_iva+"€");
                                        
                                        if (rebaixa!=null){
                                           write.println("\n\t\tAquesta factura te un descompte del "+rebaixa[0]+"%");
                                           double descompte = (preu_iva*Double.parseDouble(rebaixa[0]))/100;
                                           write.println("\n\t\tPreu final: "+(preu_iva-descompte)+"€");
                                        }
                                        
                                        write.println("\n\t\t****************************************************************");
                                        } catch (FileNotFoundException ex) {
                                            ex.printStackTrace();
                                        }
                                        
                                        write.close();
                                        
                                    }else{
                                        System.out.println("ERROR: Email no vàlid.");
                                        
                                    }
                                }else{
                                     System.out.println("ERROR: DNI no vàlid.");
                                     
                                }
                            }else{
                                System.out.println("ERROR: La factura encara no està pagada.");
                                  
                            }
                        }else{
                           System.out.println("ERROR: No existeix aquesta factura.");
                            
                        }    

                                break;
                            case 2:
                                
                                int resposta;
                                
                                System.out.println("Has triat: Ofertes");
                                System.out.println("***** Llistat Ofertes *****");

                                Sentencies_SQL ofertes = new Sentencies_SQL();
                                ofertes.mostrar_ofertes();

                                System.out.println("*---------------------------------------*");
                                System.out.println("*            Tria una opció:            *");
                                System.out.println("*---------------------------------------*");
                                System.out.println("* 1. Afegir oferta                      *");
                                System.out.println("* 2. Modificar oferta                   *");
                                System.out.println("* 3. Eliminar oferta                    *");
                                System.out.println("* 4. Sortir                             *");
                                System.out.println("*****************************************");


                                if (!teclat.hasNextInt()) {
                                    System.out.println("ERROR! Has d'introduir un número!");
                                    teclat.next();
                                } else {
                                    resposta = teclat.nextInt();

                                    switch (resposta) {
                                        case 1:
                                            if(ofertes.afegir()){
                                                System.out.println("S'ha introduït correctament.");
                                            }else{
                                                System.out.println("Error: No s'ha introduït l'oferta.");
                                            }
                                            break;

                                        case 2:
                                            if(ofertes.modificar()){
                                                System.out.println("S'ha modificat correctament.");
                                            }else{
                                                System.out.println("Error: No s'ha modificat l'oferta.");
                                            }
                                            break;

                                        case 3:
                                            if(ofertes.eliminar()){
                                                System.out.println("S'ha eliminat correctament.");
                                            }else{
                                                System.out.println("Error: No s'ha eliminat l'oferta.");
                                            }
                                            break;

                                        case 4:
                                            System.out.println("Has triat: Sortir");
                                            break;

                                        default:
                                            System.out.println("ERROR: Opció incorrecta");
                                            System.out.println("Torni-ho a provar");
                                            break;
                                    }
                                }

                                break;
                            case 3:
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
   