
package projecte4_agencia;

public class Validacions {
    
    public boolean validar_dni(String dni){
        
        boolean dniCorrecte=true;
        
        //COMPROVEM SI DNI ES DIFERENT A 9 CARACTERS    
        if ( dni.length() != 9){
            return false;   
        }
            
        //AGAFEM LULTIMA POSICIO PER COMPROVAR LLETRA            
        char ult_pos = dni.charAt(dni.length()-1);

        if ((ult_pos<'a' && ult_pos>'z') || (ult_pos<'A' && ult_pos>'Z')){
            return false;
        }
        
        //COMPROVEM SI ELS 8 PRIMERS DIGITS SON NUMEROS
        for(int i=0; i<8;i++){
            if(dni.charAt(i)<'0' || dni.charAt(i)>'9'){
                return false;
            }
        }
        //SI TOTES LES CONDICIONS SON CORRECTES, ATRIBUIM LA LLETRA CORRESPONENT AL DNI, DEPENENT DEL RESTO
        if (dniCorrecte){
            //AGAFEM NOMES LA PART NUMERICA AMB UNA SUBCADENA
            String numdni = dni.substring(0, dni.length()-1);
            //CONVERTIM EL NUMERO DE L'STRING EN UN INT
            int dniInt = Integer.parseInt(numdni);
            //CALCULEM EL RESTO DE LA DIVISIO
            int resta = dniInt % 23;
            //DECLAREM L'AARAY AMB LES LLETRES DEL DNI
            char[] lletradni = {'T', 'R', 'W', 'A', 'G', 'M', 'Y', 'F', 'P', 'D', 'X', 'B', 'N', 'J', 'Z', 'S', 'Q', 'V', 'H', 'L', 'C', 'K', 'E'};
            //PASSEM LA LLETRA AMAJUSCULA PER COMPROVAR SI CONCORDA AMB LES DE L'ARRAY           
            char ultposdni = dni.charAt(dni.length()-1);
            if ( ultposdni>='a' && ultposdni<='z'){
                ultposdni-=32;
            }
            //IMPRIMIM SI ES CORRECTE O NO SEGONS SI COINCIDEIXEN LES LLETRES DEL DNI
            if (ultposdni != lletradni[resta]){
                return false;
            } 
        }            
    return true;
    }
    
    public boolean validar_mail(String mail){
        
        ///AGAFEM POSICIONS ARROBA
        int posArroba = mail.indexOf('@');
        int posAr = mail.lastIndexOf('@');
        
        //COMPROVM SI ELS ARROBA COMPLEIXEN LES CONDICIONS
        if (posArroba == -1){
            return false;
        }
        
        if(posArroba != posAr){
            return false;
        }
        
        //SEPAREM LA PART LOCAL DEL DOMINI
        String local = mail.substring(0, posArroba);
        String domini = mail.substring(posArroba, mail.length());
        
        //LOGINTUD DE LA PART LOCAL
        if(local.length()==0 || local.length()>=64){
            return false;
        }
        
        //LONGITUD DEL DOMINI
        if(domini.length()==0 || domini.length()>=255){
            return false;
        }
        
        //COMPROVEM ELS CARACTERS DE LA PART LOCAL
        for (int i = 0; i<local.length(); i++){
            
            char x = local.charAt(i);
            
            if (!(x>='a' && x<='z') || (x>='A' && x<='Z') || (x>='0' && x<='9') || (x=='-') || (x=='_')){   
                return false;
            }
        }
        
        //SEPARAR DOMINI DE LA EXTENSIÓ
        int pospunt = domini.indexOf('.');
        int posultpunt = domini.lastIndexOf('.');
        String dominireal = domini.substring(posArroba, pospunt);
        String ext = domini.substring(pospunt+1, domini.length());
        
        // COMPROVAR PUNTS
        if (pospunt == -1){
            return false;
        }
        
        if(pospunt != posultpunt){
            return false;
        }
        
        //COMPROVEM ELS CARACTERS DE LA PART DEL DOMINI
        for(int i=0; i<dominireal.length(); i++){
            
            char x = dominireal.charAt(i);
            
            if (!(x>='a' && x<='z') || (x>='A' && x<='Z') || (x>='0' && x<='9') || (x=='-') || (x=='_')){
                return false;
            }
        }
        
        //COMPROVEM VALORS EXTENSIÓ
        if (ext.length()<2){
            return false;
        }
        
        for(int i=0; i<ext.length(); i++){
            
            char x = ext.charAt(i);
            
            if (!(x>='a' && x<='z')){
                return false;
            }
        }
        return true;
    }
    
}