/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package metodos;

/**
 *
 * @author rls
 */
public class folio {
    public static int id=1;
   int idusuario;
   
   public folio(){
   idusuario=folio.id++;
   }
   
   public String ToString(){
        String folio= "FOLIO "+idusuario;
        return folio;
    
    }
}
