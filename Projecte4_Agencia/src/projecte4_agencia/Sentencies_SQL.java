
package projecte4_agencia;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Sentencies_SQL {
    
    /*COMPROVAR SI EXISTEIX FACTURA*/
    public boolean comprovarFactura(int id){
        
        try {
            /*REALITZA CONEXIÓ BASE DE DADES*/
            ConexioMysql conexion = new ConexioMysql();
            Connection conexio = conexion.getConnection();
            /*CONSULTA*/
            String consulta = "SELECT * FROM factures WHERE id_factura = '"+id+"'";
            
            Statement sentencia = conexio.createStatement();
            /*EXECUTA CONSULTA*/
            ResultSet result = sentencia.executeQuery(consulta);
            /*COMPROVA SI NO ES NULL*/
            while (result.next()){
                String identi = result.getString("id_factura");
                return true;
            }
            
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        
        return false;
    }
    
    /***************************************************************************/
    
    /*COMPROVAR QUE ESTA PAGADA*/   
    public boolean pagada(int id){
        
        try {
            /*REALITZA CONEXIÓ BASE DE DADES*/
            ConexioMysql conexion = new ConexioMysql();
            Connection conexio = conexion.getConnection();
            /*CONSULTA*/
            String consulta = "SELECT * FROM rebuts WHERE id_factura = '"+id+"'";
            
            Statement sentencia = conexio.createStatement();
            /*EXECUTA CONSULTA*/
            ResultSet result = sentencia.executeQuery(consulta);
            
            result.next();
            /*AGAFA EL RESULTAT DE LA COLUMNA PAGADA*/
            String paga = result.getString("pagada");
            /*COMPROVACIÓ*/
            if (paga.equals("si")){
                return true;
            }
            
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return false;
    }
    
    /***************************************************************************/
    
    /*AGAFAR LES DADES DE LA FACTURA I DEL CLIENT DE LA FACTURA*/
    public String[] select_factura(int id){
        /*DECLARACIÓ VARIABLE*/
        String[] dades_factura= new String[6];
        
        try{
            /*REALITZA CONEXIÓ BASE DE DADES*/
            ConexioMysql conexion = new ConexioMysql();
            Connection conexio = conexion.getConnection();
            /*CONSULTES*/
            String consulta = "SELECT * FROM factures WHERE id_factura = '"+id+"'";
            String consultori = "SELECT clients.usuari FROM clients, pack, factures WHERE factures.id_factura = '"+id+"' and factures.id_pack = pack.id_pack and pack.dni = clients.dni";
            
            Statement sentencia = conexio.createStatement();
            /*EXECUTA CONSULTA*/
            ResultSet result = sentencia.executeQuery(consultori);
                
            result.next();
            /*AGAFA EL RESULTAT DE LA COLUMNA USUARI*/
            String nom = result.getString("clients.usuari"); 
            /*EXECUTA CONSULTA*/   
            ResultSet resultat = sentencia.executeQuery(consulta); 
            
            resultat.next();
            /*AGAFA ELS RESULTATS DE LES COLUMNA PERTINENTS*/        
                String factura = resultat.getString("id_factura");
                String pack = resultat.getString("id_pack");
                String data = resultat.getString("data_compra");
                String preui = resultat.getString("preu_inicial");
                String preuf = resultat.getString("preu_final");
                
                dades_factura[0]= nom;
                dades_factura[1]= factura;
                dades_factura[2]= pack;
                dades_factura[3]= data;
                dades_factura[4]= preui;
                dades_factura[5]= preuf;
                
                if(dades_factura==null){
                    return null;
                }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
     
        return dades_factura;    
    }
    
    /***************************************************************************/
    
    /* AGAFAR EN UN ARRAY LES CARACTERISTIQUES DELS PRODUCTES QUE SURTIRAN A LES FACTURES*/
    public String[][] select_prod(int id){
        /*DECLARACIO VARAIBLE*/
        String[][] producte = new String [100][3];
        
        try{
            /*CONNEXIO BASE DE DADES*/
            ConexioMysql conexion = new ConexioMysql();
            Connection conexio = conexion.getConnection();
            /*CONSULTA*/
            String consulta = "SELECT productes.nom, productes.categoria, productes.preu FROM productes, factures, pack, pack_prod WHERE factures.id_factura = '"+id+"' and factures.id_pack = pack.id_pack and pack.id_pack = pack_prod.id_pack and pack_prod.id_prod = productes.id_prod";
            
            Statement sentencia = conexio.createStatement();
            /*EXECUTA CONSULTA*/
            ResultSet resultat = sentencia.executeQuery(consulta);
            
            int i=0;
            /*MIRA NO SIGUI NULL I AGAFA ELS RESULTATS*/
            while(resultat.next()){
                
                String nom = resultat.getString("nom");
                String categoria = resultat.getString("categoria");
                String preu = resultat.getString("preu");
                
                producte[i][0] = nom;
                producte[i][1] = categoria;
                producte[i][2] = preu;
                
                i++;
            }
            
            if(producte==null){
                    return null;
            }
            
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
            
            return producte;             
    }
    
    /***************************************************************************/
    
    /*AGAFAR EN UNA VARIABLE EL MAIL DEL CLIENT*/
    public String comprovar_email(int id){
        /*DECLARACIO VARIABLE*/
        String correu = "";
        
        try{
            /*CONNEXIO BASE DE DADES*/
            ConexioMysql conexion = new ConexioMysql();
            Connection conexio = conexion.getConnection();
            /*CONSULTA*/
            String consulta = "SELECT clients.mail FROM clients, pack, factures WHERE factures.id_factura ='"+id+"' and factures.id_pack = pack.id_pack and pack.dni = clients.dni";
            
            Statement sentencia = conexio.createStatement();
            /*EXECUTA CONSULTA*/
            ResultSet resultat = sentencia.executeQuery(consulta);
            
            resultat.next();
            /*AGAFA RESULTAT*/
            correu = resultat.getString("mail");
            
            if(correu == null){
                return null;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
            
          return correu;      
    }
    
    /***************************************************************************/
    
    /*AGAFAR EN UNA VARIABLE EL DNI DEL CLIENT*/
    public String comprovar_dni(int id){
        
        String denei ="";
        try{
            ConexioMysql conexion = new ConexioMysql();
            Connection conexio = conexion.getConnection();

            String consulta = "SELECT clients.dni FROM clients, pack, factures WHERE factures.id_factura ='"+id+"' and factures.id_pack = pack.id_pack and pack.dni = clients.dni";
            

            Statement sentencia = conexio.createStatement();
            ResultSet resultat = sentencia.executeQuery(consulta);
            
            resultat.next();
            
            denei = resultat.getString("clients.dni");
            
            if(denei == null){
                return null;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return denei;           
    }
    
    /***************************************************************************/
    
    /*LLISTAR TOTES LES OFERTES*/    
    public void mostrar_ofertes() {
        
        try {
        
            ConexioMysql conexion = new ConexioMysql();
            Connection conexio = conexion.getConnection();

            String consulta = "SELECT * FROM ofertes";
            
            Statement sentencia;
            sentencia = conexio.createStatement();
        
            ResultSet resultat = sentencia.executeQuery(consulta);
            
            System.out.println(" --------------------------- ");
            System.out.println("| ID Oferta |   Nom Oferta  |");
            System.out.println(" --------------------------- ");
            
            while(resultat.next()){
                
            String idof = resultat.getString("id_oferta");
            String ofert = resultat.getString("nom_oferta");
            
            System.out.println("| "+idof+"  |   "+ofert+"    |");
            
            }
            System.out.println(" --------------------------- ");
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    
    /***************************************************************************/
    
    /*AFEGIR OFERTA*/    
    public boolean afegir() {
        
        try{
        
        /*CONEXIO BASE DE DADES*/
        ConexioMysql conexion = new ConexioMysql();
        Connection conexio = conexion.getConnection();

        Statement sentencia = conexio.createStatement();
        Statement sentencia1 = conexio.createStatement();
                        
        Scanner dades = new Scanner (System.in);
        
        String txt, id_of, nom, dia;
                                    
        /*AGAFAR DADES NOVA OFERTA*/
        System.out.println("Has triat: Afegir oferta");
        System.out.println("Introduiex les següents dades:");
        System.out.println();

        System.out.print("ID Oferta: ");
        txt = dades.nextLine();
        id_of = txt;

        System.out.print("Nom Oferta: ");
        txt = dades.nextLine();
        nom = txt;

        System.out.print("Dia rebaixa Oferta (YYYY-MM-DD): ");
        txt = dades.nextLine();
        dia = txt;

        /*GRABAR INFO TAULA OFERTES */
        String grabar = "INSERT INTO ofertes VALUES ('"+id_of+"', '"+nom+"')";
        if (sentencia.executeUpdate(grabar)!=0){
            /*GRABAR INFO TAULA DATA*/
            String guardar = "INSERT INTO data VALUES ('"+id_of+"', '"+dia+"')";
            if (sentencia1.executeUpdate(guardar)!=0){
                return true;
            }
        }
        
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        
     return false;       
    }
    
    /***************************************************************************/
    
    /*MODIFICAR OFERTA*/    
    public boolean modificar() {
        
        try{
        
        /*CONEXIO BASE DE DADES*/
        ConexioMysql conexion = new ConexioMysql();
        Connection conexio = conexion.getConnection();

        Statement sentencia = conexio.createStatement();
                        
        Scanner dades = new Scanner (System.in);
        
        String txt, id_of, dia;
                                    
        /*AGAFAR DADES NOVA OFERTA*/
        System.out.println("Has triat: Modificar oferta.");
        System.out.println();
        System.out.println("**Recordatori**");
        System.out.println("Només pot modificar el dia de l'oferta.");
        System.out.println();

        System.out.print("Introdueix l'ID de la oferta a modificar: ");
        txt = dades.nextLine();
        id_of = txt;
        
        System.out.print("Dia rebaixa Oferta: ");
        txt = dades.nextLine();
        dia = txt;

        /*GRABAR INFO TAULA OFERTES */
        String grabar = "UPDATE data SET dia_rebaixa='"+dia+"' WHERE id_oferta='"+id_of+"'";
        
        /*EXECUTAR CONSULTA*/
        sentencia.executeUpdate(grabar);
        
        if(sentencia.executeUpdate(grabar)!=0){
            return true;
        }
        
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        
     return false;       
    }
    
    /***************************************************************************/
    
    /*ELIMINAR OFERTA*/    
    public boolean eliminar() {
        
        try{        
        /*CONEXIO BASE DE DADES*/
        ConexioMysql conexion = new ConexioMysql();
        Connection conexio = conexion.getConnection();
        
        Statement sentencia = conexio.createStatement();
                        
        Scanner dades = new Scanner (System.in);
        
        String txt, id_of;
                                    
        /*AGAFAR DADES NOVA OFERTA*/
        System.out.print("Has triat: Eliminar oferta");
        System.out.println();
        System.out.print("Introduiex l'ID de l'oferta per eliminar: ");
        txt = dades.nextLine();
        id_of = txt;

        /*ELIMINAR INFO TAULA OFERTES, DATA I REBAIXA */        
        String elim1 = "DELETE FROM data WHERE id_oferta='"+id_of+"'";
        sentencia.executeUpdate(elim1);
        
        String elim2 = "DELETE FROM rebaixa WHERE id_oferta='"+id_of+"'";
        sentencia.executeUpdate(elim2);
        
        String elim3 = "DELETE FROM ofertes WHERE id_oferta='"+id_of+"'";
        sentencia.executeUpdate(elim3);
        
        if((sentencia.executeUpdate(elim1)==0) && (sentencia.executeUpdate(elim2)==0 || sentencia.executeUpdate(elim2)!=0) && (sentencia.executeUpdate(elim3)==0)){
            return true;
        }
        
        } catch (SQLException ex) {
            ex.printStackTrace();
        }  
     return false;       
    }

/*******************************************************************************/

/*COMPROVAR SI TE REBAIXA*/
public String[] rebaixa_factura(int id){
        
        
        String[] rebaixa = new String[2];
        
        try{
            ConexioMysql conexion = new ConexioMysql();
            Connection conexio = conexion.getConnection();

            String consulta = "SELECT * FROM rebaixa WHERE id_factura ='"+id+"'";
            
            Statement sentencia = conexio.createStatement();
            ResultSet resultat = sentencia.executeQuery(consulta);
            
            resultat.next();
            
            String descompte = resultat.getString("descompte");
            String oferta = resultat.getString("id_oferta");
            
            rebaixa[0] = descompte;
            rebaixa[1] = oferta;
            
            
            if(rebaixa != null){
                return rebaixa;
            }
            
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;           
    }
}