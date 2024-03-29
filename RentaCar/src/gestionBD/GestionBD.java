/*
GNU LICENCE V3.0
EPE3, RentaCar v1.0
    Copyright (C) 2019  Ricardo Lucero
                        Sebastian Quiroga
                        Ernesto Díaz
                        Diego Arancibia
    This program is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.
    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.
    You should have received a copy of the GNU General Public License
    along with this program.  If not, see <https://www.gnu.org/licenses/>.
RentaCar  Copyright (C) 2019  Ricardo Lucero
                        Sebastian Quiroga
                        Ernesto Díaz
                        Diego Arancibia
    This program comes with ABSOLUTELY NO WARRANTY.
    This is free software, and you are welcome to redistribute it
*/
package gestionBD;

import java.awt.TextField;
import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import static java.sql.JDBCType.DATE;
import java.sql.ResultSet;
import java.sql.SQLException;
import static java.sql.Types.DATE;
import static java.util.Calendar.DATE;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;


public class GestionBD {
    Connection conexion = null;
    Statement sentencia = null;
    ResultSet resultados = null;
    String NOMBREBD = "empresarentacar.sqlite";
    String DRIVER = "org.sqlite.JDBC";
    String URL = "jdbc:sqlite:"+NOMBREBD;
    //------crear BD y tablas-------//
    public void crearBD() {
 
        try {
            Class.forName(DRIVER);
            conexion = DriverManager.getConnection(URL);
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("Error:"+ e.getMessage());
        }
     
     System.out.println("BD creada de forma Exitosa!!!");
 }   
        public void crearTablaUsuarios()  {
        try {
            Class.forName(DRIVER);
            conexion = DriverManager.getConnection(URL);
                sentencia = (Statement) conexion.createStatement();
                    String sql = "CREATE TABLE USUARIOS"+
                            "(USUARIO   INT  NOT NULL PRIMARY KEY ,"+ 
                            "PASS BLOB NOT NULL)"; 
                           
                sentencia.executeUpdate(sql);
                sentencia.close();
            conexion.close();
            System.out.println("Tabla USUARIOS creada EXITOSAMENTE");
          
            
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("Error:"+ e.getMessage());
        } 
 } 
        public void insertDatosUsuariosDefault()  {
            try {
                  Class.forName(DRIVER);
                  conexion = DriverManager.getConnection(URL);

                  sentencia = conexion.createStatement();
                  String sql = "INSERT INTO USUARIOS"+
                               "(USUARIO,PASS)"+
                               "VALUES('Admin','Admin')";
                  sentencia.executeUpdate(sql);
                  sentencia.close();
                  conexion.close();
                //              JOptionPane.showMessageDialog (null, "Cliente ingresado", "Atencion", JOptionPane.WARNING_MESSAGE);


              } catch (ClassNotFoundException | SQLException e) {
                  System.out.println("Error:"+ e.getMessage());
              //    JOptionPane.showMessageDialog(null,"Rut ya Existe en la BD vuelve a ingresar un Rut distinto","error",JOptionPane.ERROR_MESSAGE);   comentado para que no muetre el JOption de error
              }

 }    
            public void insertDatosUsuarios(String us, String psw)  {
            try {
                  Class.forName(DRIVER);
                  conexion = DriverManager.getConnection(URL);

                  sentencia = conexion.createStatement();
                  String sql = "INSERT INTO USUARIOS"+
                               "(USUARIO,PASS)"+
                               "VALUES('"+us+"','"+psw+"')";
                  sentencia.executeUpdate(sql);
                  sentencia.close();
                  conexion.close();
                  JOptionPane.showMessageDialog (null, "USUARIO CREADO CON EXITO...!!!", "Atencion", JOptionPane.WARNING_MESSAGE);


              } catch (ClassNotFoundException | SQLException e) {
                  System.out.println("Error:"+ e.getMessage());
              //    JOptionPane.showMessageDialog(null,"Rut ya Existe en la BD vuelve a ingresar un Rut distinto","error",JOptionPane.ERROR_MESSAGE);   comentado para que no muetre el JOption de error
              }

 } 
            public void modificarDatosUsuarios(String us, String psw)  {
            try {
                  Class.forName(DRIVER);
                  conexion = DriverManager.getConnection(URL);

                  sentencia = conexion.createStatement();
                  String sql = "UPDATE USUARIOS SET PASS='"+psw+"' WHERE USUARIO='"+us+"'";
                              
                  sentencia.executeUpdate(sql);
                  sentencia.close();
                  conexion.close();
                  JOptionPane.showMessageDialog (null, "CONTRASEÑA ACTUALIZADA CON EXITO..!!", "Atencion", JOptionPane.WARNING_MESSAGE);


              } catch (ClassNotFoundException | SQLException e) {
                  System.out.println("Error:"+ e.getMessage());
              //    JOptionPane.showMessageDialog(null,"Rut ya Existe en la BD vuelve a ingresar un Rut distinto","error",JOptionPane.ERROR_MESSAGE);   comentado para que no muetre el JOption de error
              }

 } 
            public void eliminarDatosUsuarios(String us)  {
            try {
                  Class.forName(DRIVER);
                  conexion = DriverManager.getConnection(URL);

                  sentencia = conexion.createStatement();
                    String sql = "DELETE FROM USUARIOS WHERE USUARIO='"+us+"'";
                              
                  sentencia.executeUpdate(sql);
                  sentencia.close();
                  conexion.close();
                  JOptionPane.showMessageDialog (null, "USUARIO ELIMINADO CON EXITO..!!", "Atencion", JOptionPane.WARNING_MESSAGE);


              } catch (ClassNotFoundException | SQLException e) {
                  System.out.println("Error:"+ e.getMessage());
              //    JOptionPane.showMessageDialog(null,"Rut ya Existe en la BD vuelve a ingresar un Rut distinto","error",JOptionPane.ERROR_MESSAGE);   comentado para que no muetre el JOption de error
              }

 }
             public void mostraDatoUsuario(JTable jtable)  {
        try {
            Class.forName(DRIVER);
            conexion = DriverManager.getConnection(URL);
            sentencia = conexion.createStatement();
            String sql = "SELECT * FROM USUARIOS";
            resultados = sentencia.executeQuery(sql);
            int fila=0;
            while(resultados.next()){
                jtable.setValueAt(resultados.getString("USUARIO"), fila,0);
                fila++;
            }
           
            sentencia.close();
            conexion.close();
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("Error:"+ e.getMessage());
            JOptionPane.showMessageDialog(
        null, "Error", "Error", JOptionPane.ERROR_MESSAGE);
        }

    }       
        public String LOGUEAR(String nombre){
            String salida="";
            try{
                Class.forName(DRIVER);
                conexion=DriverManager.getConnection(URL);
                sentencia=conexion.createStatement();
                String sql="SELECT * FROM USUARIOS WHERE USUARIO='"+nombre+"';";
                ResultSet resultado= sentencia.executeQuery(sql);
                salida=resultado.getString("USUARIO");
                salida=salida+","+resultado.getString("PASS");
                sentencia.close();
                conexion.close();
            }catch(ClassNotFoundException | SQLException e){
                System.out.println("ERROR:"+e.getMessage());
            }

                System.out.println("consulta realizada!");
                return salida;
            }
            public void crearTablaCliente()  {
                try {
                    Class.forName(DRIVER);
                    conexion = DriverManager.getConnection(URL);
                        sentencia = (Statement) conexion.createStatement();
                            String sql = "CREATE TABLE CLIENTE"+
                                    "(RUT TEXT  PRIMARY KEY NOT NULL,"+ 
                                    "NOMBRE    TEXT NOT NULL,"+  
                                    "APELLIDO  TEXT NOT NULL,"+
                                    "DIRECCION TEXT NOT NULL,"+
                                    "TELEFONO  INT  NOT NULL,"+
                                    "CORREO TEXT NOT NULL)";
                        sentencia.executeUpdate(sql);
                        sentencia.close();
                    conexion.close();
                    System.out.println("Tabla cliente creada");


                } catch (ClassNotFoundException | SQLException e) {
                    System.out.println("Error:"+ e.getMessage());
                } 
         }    
            public void crearTablaDEVOLUCION()  {
                try {
                    Class.forName(DRIVER);
                    conexion = DriverManager.getConnection(URL);
                        sentencia = (Statement) conexion.createStatement();
                            String sql = "CREATE TABLE DEVOLUCION"+
                                        "(FOLIO_DEV TEXT  PRIMARY KEY NOT NULL,"+ 
                                        "PPU       TEXT NOT NULL,"+  
                                        "RUT_CLI  TEXT NOT NULL,"+
                                        "FECHA_DEV TEXT NOT NULL)";
                        sentencia.executeUpdate(sql);
                        sentencia.close();
                    conexion.close();
                    System.out.println("Tabla DEVOLUCION creada");


                } catch (ClassNotFoundException | SQLException e) {
                    System.out.println("Error:"+ e.getMessage());
                } 
         } 
            public void crearTablaCategoriaVehiculo()  {
                try {
                    Class.forName(DRIVER);
                    conexion = DriverManager.getConnection(URL);
                        sentencia = (Statement) conexion.createStatement();
                            String sql = "CREATE TABLE CATEGORIA"+
                                    "(ID_CAT TEXT  PRIMARY KEY NOT NULL,"+ 
                                    "NOMBRE_CAT    TEXT NOT NULL)";
                        sentencia.executeUpdate(sql);
                        sentencia.close();
                    conexion.close();
                    System.out.println("Tabla CATEGORIA creada");


                } catch (ClassNotFoundException | SQLException e) {
                    System.out.println("Error:"+ e.getMessage());
                } 
         }            
            public void crearTablaVehiculo ()  {
                try {
                    Class.forName(DRIVER);
                    conexion = DriverManager.getConnection(URL);
                        sentencia = (Statement) conexion.createStatement();
                            String sql = "CREATE TABLE VEHICULO"+
                                    "(PPU TEXT PRIMARY KEY NOT NULL,"+ 
                                     "MARCA    TEXT NOT NULL,"+  
                                    "MODELO  TEXT NOT NULL,"+
                                    "COLOR TEXT NOT NULL,"+
                                    "AÑO  TEXT  NOT NULL,"+
                                    "VALORDIA INT NOT NULL,"+
                                    "ESTADO TEXT NOT NULL,"+
                                    "CAT TEXT NOT NULL,"+
                                    "FOREIGN KEY(CAT) REFERENCES CATEGORIA(ID_CAT))";
                        sentencia.executeUpdate(sql);
                        sentencia.close();
                    conexion.close();
                    System.out.println("Tabla VEHICULO creada");


                } catch (ClassNotFoundException | SQLException e) {
                    System.out.println("Error:"+ e.getMessage());
                }


         }   
            public void crearTablaContrato ()  {
        try {
            Class.forName(DRIVER);
            conexion = DriverManager.getConnection(URL);
                sentencia = (Statement) conexion.createStatement();
                    String sql = "CREATE TABLE CONTRATO"+
                            "(FOLIO TEXT NOT NULL PRIMARY KEY,"+ 
                             "FECHA_CONTRATO DATE NOT NULL,"+  
                            "RUT_CLI  TEXT NOT NULL,"+
                            "PPU_CLI TEXT NOT NULL,"+
                            "NUM_DIAS INT  NOT NULL,"+
                            "FECH_DESDE DATE NOT NULL,"+
                            "FECH_HASTA DATE NOT NULL,"+
                            "VALOR_FINAL INT  NOT NULL,"+
                            "CONDICION TEXT  NOT NULL,"+
                            "FOREIGN KEY (RUT_CLI)REFERENCES CLIENTE(RUT),"+
                            "FOREIGN KEY (PPU_CLI)REFERENCES VEHICULO(PPU))";
                sentencia.executeUpdate(sql);
                sentencia.close();
            conexion.close();
            System.out.println("Tabla CONTRATO creada");
          
            
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("Error:"+ e.getMessage());
        }
    
     
 }    
    //------insert update delete mostrar tabla cliente-------//
    public void insertDatoCliente(String RUT, String NOMBRE, String APELLIDO, String DIRECCION,int TELEFONO,String CORREO)  {
      try {
            Class.forName(DRIVER);
            conexion = DriverManager.getConnection(URL);
            
            sentencia = conexion.createStatement();
            String sql = "INSERT INTO CLIENTE"+
                         "(RUT,NOMBRE,APELLIDO,DIRECCION,TELEFONO,CORREO)"+
                         "VALUES('"+RUT+"','"+NOMBRE+"','"+APELLIDO+"','"+DIRECCION+"','"+TELEFONO+"','"+CORREO+"')";
            sentencia.executeUpdate(sql);
            sentencia.close();
            conexion.close();
                        JOptionPane.showMessageDialog (null, "Cliente ingresado", "Atencion", JOptionPane.WARNING_MESSAGE);

            
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("Error:"+ e.getMessage());
            JOptionPane.showMessageDialog(null,"Rut ya Existe en la BD vuelve a ingresar un Rut distinto","error",JOptionPane.ERROR_MESSAGE);
        }

 }    
    public void mostraDatoCliente(JTable jtablecliente)  {
        try {
            Class.forName(DRIVER);
            conexion = DriverManager.getConnection(URL);
            sentencia = conexion.createStatement();
            String sql = "SELECT * FROM CLIENTE";
            resultados = sentencia.executeQuery(sql);
            int fila=0;
            while(resultados.next()){
                jtablecliente.setValueAt(resultados.getString("RUT"), fila,0);
                jtablecliente.setValueAt(resultados.getString("NOMBRE"), fila,1);
                jtablecliente.setValueAt(resultados.getString("APELLIDO"), fila,2);
                jtablecliente.setValueAt(resultados.getString("DIRECCION"), fila,3);
                jtablecliente.setValueAt(resultados.getInt("TELEFONO"), fila,4);
                jtablecliente.setValueAt(resultados.getString("CORREO"), fila,5);
                fila++;
            }
           
            sentencia.close();
            conexion.close();
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("Error:"+ e.getMessage());
            JOptionPane.showMessageDialog(
        null, "Error", "Error", JOptionPane.ERROR_MESSAGE);
        }

    }
    public void mostraDatoClienteunico(JTextField nombre,JTextField apellido,JTextField direccion,JTextField telefono,JTextField correo, String RU)  {
        try {
            Class.forName(DRIVER);
            conexion = DriverManager.getConnection(URL);
            sentencia = conexion.createStatement();
            String sql = "SELECT * FROM CLIENTE where RUT = '"+RU+"'";
            resultados = sentencia.executeQuery(sql);
            
               nombre.setText( resultados.getString("NOMBRE"));
               apellido.setText( resultados.getString("APELLIDO"));
               direccion.setText( resultados.getString("DIRECCION"));
               telefono.setText( resultados.getString("TELEFONO"));
               correo.setText( resultados.getString("CORREO"));
               
              sentencia.close();
            conexion.close();
         
            
        } catch (ClassNotFoundException | SQLException e) {
             System.out.println("Error:"+ e.getMessage());
            JOptionPane.showMessageDialog(
        null, "Error", "Error", JOptionPane.ERROR_MESSAGE);
        }

    }
    public void modificarDatoCliente(String NOM,String APE, String DIR,String TEL,String CORR, String RU)  {
      try {
            Class.forName(DRIVER);
            conexion = DriverManager.getConnection(URL);
            
            sentencia = conexion.createStatement();
            String sql = "update CLIENTE set NOMBRE = '"+NOM+"', APELLIDO = '"+APE+"', DIRECCION = '"+DIR+"',TELEFONO = '"+TEL+"',CORREO = '"+CORR+"' where RUT = '"+RU+"'";
         
            sentencia.executeUpdate(sql);
            sentencia.close();
            conexion.close();
            JOptionPane.showMessageDialog(null,"Cliente Modificado con Exito");
            
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("Error:"+ e.getMessage());
            JOptionPane.showMessageDialog(
        null, "error: "+e.getMessage(), "Rut NO  Existe en la BD vuelva a intentarlo nuevamente ", JOptionPane.ERROR_MESSAGE);
            
        }

 }  
    public void eliminarDatoCliente( String RU)  {
      try {
            Class.forName(DRIVER);
            conexion = DriverManager.getConnection(URL);
            
            sentencia = conexion.createStatement();
            String sql = "DELETE FROM CLIENTE  where RUT = '"+RU+"'";
           
            sentencia.executeUpdate(sql);
            sentencia.close();
            conexion.close();
            JOptionPane.showMessageDialog (null, "Cliente eliminado", "Informacion", JOptionPane.WARNING_MESSAGE);
            
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("Error:"+ e.getMessage());
            JOptionPane.showMessageDialog(
        null, "error: "+e.getMessage(), "RUT NO EXISTE EN LA BD, INTENTELO NUEVAMENTE", JOptionPane.ERROR_MESSAGE);
        }

 } 
    //------insert datos tabla CATEGORIA-------//
    public void insertDatoCategoria()  {    //INSERTE CATEGORIAS SIN TENER TABLA EN LA GRAFICA
      try {
            Class.forName(DRIVER);
            conexion = DriverManager.getConnection(URL);
            
            sentencia = conexion.createStatement();
            String sql ="INSERT INTO CATEGORIA"+"(ID_CAT , NOMBRE_CAT)"+"VALUES('100','SEDAN')"; 
                   sentencia.executeUpdate(sql);
            
           
                     sql = "INSERT INTO CATEGORIA"+"(ID_CAT , NOMBRE_CAT)"+"VALUES('200','JEEP')";
                     sentencia.executeUpdate(sql);
            
                    sql ="INSERT INTO CATEGORIA"+"(ID_CAT , NOMBRE_CAT)"+"VALUES('300','CAMIONETA')"; 
                   sentencia.executeUpdate(sql);
            
            sentencia.close();
            conexion.close();
             System.out.println("insertado los datos de categoria correctamente");
            
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("Error:"+ e.getMessage());
          
        }
    /*  
    public void insertDatoVehiculo(String PPU, String MARCA, String MODELO, String COLOR,int VALOR_ARRIENDO, String ESTADO, int ID_CATT)  {
      try {
            Class.forName(DRIVER);
            conexion = DriverManager.getConnection(URL);
            
            sentencia = conexion.createStatement();
            String sql = "INSERT INTO VEHICULO"+
                         "(PPU,MARCA,MODELO,COLOR,VALOR_ARRIENDO,ESTADO,ID_CAT)"+
                         "VALUES('"+PPU+"','"+MARCA+"','"+MODELO+"','"+COLOR+"','"+VALOR_ARRIENDO+"','"+ESTADO+"','"+ID_CATT+"')";
            sentencia.executeUpdate(sql);
            sentencia.close();
            conexion.close();
            JOptionPane.showMessageDialog(null,"Cliente ingresado con Exito");
            
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("Error:"+ e.getMessage());
            JOptionPane.showMessageDialog(null,"PATENTE ya Existe en la BD vuelve a ingresar una PPU distinta"+ e);
        }
*/
 }    
    //------insert update delete mostrar tabla VEHICULO------//
    public void insertDatoVehiculo(String PPU, String MARCA, String MODELO, String COLOR, String AÑO, int VALORDIA, String ESTADO, String CAT)  {
      try {
            Class.forName(DRIVER);
            conexion = DriverManager.getConnection(URL);
            
            sentencia = conexion.createStatement();
            String sql = "INSERT INTO VEHICULO"+
                         "(PPU,MARCA,MODELO,COLOR,AÑO,VALORDIA,ESTADO,CAT)"+
                         "VALUES('"+PPU+"','"+MARCA+"','"+MODELO+"','"+COLOR+"','"+AÑO+"','"+VALORDIA+"','"+ESTADO+"','"+CAT+"')";
            sentencia.executeUpdate(sql);
            sentencia.close();
            conexion.close();
                       JOptionPane.showMessageDialog (null, "Exito", "Vehiculo ingresado con éxito", JOptionPane.INFORMATION_MESSAGE);

            
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("Error:"+ e.getMessage());
            JOptionPane.showMessageDialog(
        null, "Error al ingresar AUTO", "Error", JOptionPane.ERROR_MESSAGE);
        }

 }
    public void mostraDatoVehiculo(JTable jtableautos)  {
        try {
            Class.forName(DRIVER);
            conexion = DriverManager.getConnection(URL);
            sentencia = conexion.createStatement();
            String sql = "SELECT * FROM VEHICULO";
            resultados = sentencia.executeQuery(sql);
            int fila=0;
            while(resultados.next()){
                jtableautos.setValueAt(resultados.getString("PPU"), fila,0);
                jtableautos.setValueAt(resultados.getString("MARCA"), fila,1);
                jtableautos.setValueAt(resultados.getString("MODELO"), fila,2);
                jtableautos.setValueAt(resultados.getString("COLOR"), fila,3);
                jtableautos.setValueAt(resultados.getInt("AÑO"), fila,4);
                jtableautos.setValueAt(resultados.getString("VALORDIA"), fila,5);
                jtableautos.setValueAt(resultados.getString("ESTADO"), fila,6);
                jtableautos.setValueAt(resultados.getString("CAT"), fila,7);
                fila++;
            }
           
            sentencia.close();
            conexion.close();
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("Error:"+ e.getMessage());
            JOptionPane.showMessageDialog(
        null, "Error", "Error", JOptionPane.ERROR_MESSAGE);
        }

    }
    public void mostraDatoVehiculoUnicoDEVO(JTextField marca,JTextField modelo,JTextField color,JTextField año,JTextField valordia,JComboBox comboestado,JTextField folio,
                                            JTextField rutd,JTextField nomd,JTextField aped,JTextField direcd, JTextField teld,JTextField cord,String PU)  {
        try {
            Class.forName(DRIVER);
            conexion = DriverManager.getConnection(URL);
            sentencia = conexion.createStatement();
            String sql = "SELECT * FROM VEHICULO JOIN CONTRATO ON VEHICULO.PPU=CONTRATO.PPU_CLI  JOIN CLIENTE ON CONTRATO.RUT_CLI=CLIENTE.RUT where PPU = '"+PU+"'";
            resultados = sentencia.executeQuery(sql);
            
               marca.setText( resultados.getString("MARCA"));
               modelo.setText( resultados.getString("MODELO"));
               color.setText( resultados.getString("COLOR"));
               año.setText( resultados.getString("AÑO"));
               valordia.setText( resultados.getString("VALORDIA"));
               comboestado.setSelectedItem( resultados.getString("ESTADO"));
               folio.setText( resultados.getString("FOLIO"));
               rutd.setText( resultados.getString("RUT"));
               nomd.setText( resultados.getString("NOMBRE"));
               aped.setText( resultados.getString("APELLIDO"));
               direcd.setText( resultados.getString("DIRECCION"));
               teld.setText( resultados.getString("TELEFONO"));
              cord.setText( resultados.getString("CORREO"));
              
               sentencia.close();
            conexion.close();
         
            
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("Error:"+ e.getMessage());
            JOptionPane.showMessageDialog(
        null, "Error", "Error", JOptionPane.ERROR_MESSAGE);
        }

    }
    public void modificarDatoVehiculo(String PPU, String MARCA, String MODELO, String COLOR, String AÑO, int VALORDIA, String ESTADO, String CAT)  {
      try {
            Class.forName(DRIVER);
            conexion = DriverManager.getConnection(URL);
            
            sentencia = conexion.createStatement();
            String sql = "update VEHICULO set MARCA = '"+MARCA+"', MODELO = '"+MODELO+"', COLOR = '"+COLOR+"', AÑO = '"+AÑO+"',VALORDIA = '"+VALORDIA+"',ESTADO = '"+ESTADO+"',CAT = '"+CAT+"' where PPU = '"+PPU+"'";
         
            sentencia.executeUpdate(sql);
            sentencia.close();
            conexion.close();
            JOptionPane.showMessageDialog(null,"VEHICULO Modificado con Exito","Atención",JOptionPane.WARNING_MESSAGE);
            
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("Error:"+ e.getMessage());
            JOptionPane.showMessageDialog(
        null, "Auto no existen en la BD ", "Error", JOptionPane.ERROR_MESSAGE);
        }

 }  
    public void eliminarDatoVehiculo( String PPU)  {
      try {
            Class.forName(DRIVER);
            conexion = DriverManager.getConnection(URL);
            
            sentencia = conexion.createStatement();
            String sql = "DELETE FROM VEHICULO  where PPU = '"+PPU+"'";
           
            sentencia.executeUpdate(sql);
            sentencia.close();
            conexion.close();
            JOptionPane.showMessageDialog(null,"VEHICULO Eliminadoo con Exito","Atención",JOptionPane.WARNING_MESSAGE);
            
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("Error:"+ e.getMessage());
           JOptionPane.showMessageDialog(
        null, "Vehiculo no existe en la BD intentelo nuevamente" ,"Error", JOptionPane.ERROR_MESSAGE);
        }

 } 
    public void mostraComboEstadoContrato(JComboBox cbox)  {
        try {
            Class.forName(DRIVER);
            conexion = DriverManager.getConnection(URL);
            sentencia = conexion.createStatement();
            String sql = "SELECT  ESTADO  FROM VEHICULO";
            resultados = sentencia.executeQuery(sql);
            
           cbox.addItem("Seleccione una opción");
             while(resultados.next()){
   
                 cbox.addItem(resultados.getString("ESTADO"));
   
                }
           
            sentencia.close();
            conexion.close();
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("Error:"+ e.getMessage());
            JOptionPane.showMessageDialog(
        null, "Error", "Error", JOptionPane.ERROR_MESSAGE);
        }

    }
    public void mostraComboMarcaContrato(JComboBox cbox2, String opanterior)  {
        try {
            Class.forName(DRIVER);
            conexion = DriverManager.getConnection(URL);
            sentencia = conexion.createStatement();
            String sql = "SELECT  MARCA FROM VEHICULO JOIN CATEGORIA ON VEHICULO.CAT=CATEGORIA.ID_CAT WHERE NOMBRE_CAT ='"+opanterior+"'";
            resultados = sentencia.executeQuery(sql);
            
            cbox2.addItem("Seleccione una opción de marca");
             while(resultados.next()){
   
                 cbox2.addItem(resultados.getString("MARCA"));
   
                }
           
            sentencia.close();
            conexion.close();
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("Error:"+ e.getMessage());
        }

    }
    public void mostraDatoVehiculoCongtrato(JTable jtableautos)  {
        try {
            Class.forName(DRIVER);
            conexion = DriverManager.getConnection(URL);
            sentencia = conexion.createStatement();
            String sql = "SELECT * FROM VEHICULO WHERE ESTADO = 'LIBRE'";
            resultados = sentencia.executeQuery(sql);
            int fila=0;
            while(resultados.next()){
                jtableautos.setValueAt(resultados.getString("PPU"), fila,0);
                jtableautos.setValueAt(resultados.getString("MARCA"), fila,1);
                jtableautos.setValueAt(resultados.getString("MODELO"), fila,2);
                jtableautos.setValueAt(resultados.getString("COLOR"), fila,3);
                jtableautos.setValueAt(resultados.getInt("AÑO"), fila,4);
                fila++;
            }
           
            sentencia.close();
            conexion.close();
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("Error:"+ e.getMessage());
            JOptionPane.showMessageDialog(
        null, "Error", "Error", JOptionPane.ERROR_MESSAGE);
        }

    }   
    public void mostraVALORVehiculoDIA(JTextField valordia,String PU)  {
        try {
            Class.forName(DRIVER);
            conexion = DriverManager.getConnection(URL);
            sentencia = conexion.createStatement();
            String sql = "SELECT VALORDIA FROM VEHICULO where PPU = '"+PU+"'";
            resultados = sentencia.executeQuery(sql);
            
               valordia.setText( resultados.getString("VALORDIA"));
               
              sentencia.close();
            conexion.close();
         
            
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("Error:"+ e.getMessage());
            JOptionPane.showMessageDialog(
        null, "Error", "Error", JOptionPane.ERROR_MESSAGE);
        }

    }
    public void insertDatoCONTRATO(String FOLIO, String FECHA_CONTRATO, String RUT_CLI, String PPU_CLI, int NUM_DIAS, String  FECH_DESDE, String FECH_HASTA, int VALOR_FINAL)  {
      try {
            Class.forName(DRIVER);
            conexion = DriverManager.getConnection(URL);
            
            sentencia = conexion.createStatement();
            String sql = "INSERT INTO CONTRATO"+
                         "(FOLIO,FECHA_CONTRATO,RUT_CLI,PPU_CLI,NUM_DIAS,FECH_DESDE,FECH_HASTA,VALOR_FINAL,CONDICION)"+
                         "VALUES('"+FOLIO+"','"+FECHA_CONTRATO+"','"+RUT_CLI+"','"+PPU_CLI+"','"+NUM_DIAS+"','"+FECH_DESDE+"','"+FECH_HASTA+"','"+VALOR_FINAL+"','VIGENTE')";
            sentencia.executeUpdate(sql);
            sentencia.close();
            conexion.close();
            JOptionPane.showMessageDialog(null,"CONTRATO ingresado con CORRECTAMENTE");
            
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("Error:"+ e.getMessage());
            JOptionPane.showMessageDialog(
        null, "Error al ingresar el Contrato", "Error", JOptionPane.ERROR_MESSAGE);
        }

 }
    
    
    public void modificarEstadoContrato(String folio)  {
      try {
            Class.forName(DRIVER);
            conexion = DriverManager.getConnection(URL);
            
            sentencia = conexion.createStatement();
            String sql = " UPDATE CONTRATO SET CONDICION='CERRADO' WHERE FOLIO='"+folio+"' ";
         
            sentencia.executeUpdate(sql);
            sentencia.close();
            conexion.close();
            JOptionPane.showMessageDialog(null,"ESTADO Modificado con Exito","Atención",JOptionPane.WARNING_MESSAGE);
            
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("Error:"+ e.getMessage());
            JOptionPane.showMessageDialog(
        null, "Estado no existe en la BD","Error", JOptionPane.ERROR_MESSAGE);
        }

 }  
    
    public void  obtenerFolio( JTextField txtpr)  {
        try {
            Class.forName(DRIVER);
            conexion = DriverManager.getConnection(URL);
            sentencia = conexion.createStatement();
            String sql = "SELECT FOLIO FROM CONTRATO where rut rut_cli='15778242-8'";
            resultados = sentencia.executeQuery(sql);
            
               txtpr.setText(resultados.getString("FOLIO"));
             
              sentencia.close();
            conexion.close();
         
            
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("Error:"+ e.getMessage());
            JOptionPane.showMessageDialog(
        null, "Error", "Error", JOptionPane.ERROR_MESSAGE);
        }

    }
    public void mostrarTODO(JTable jtableautos)  {
        try {
            Class.forName(DRIVER);
            conexion = DriverManager.getConnection(URL);
            sentencia = conexion.createStatement();
            String sql = "SELECT * FROM CLIENTE JOIN CONTRATO ON CLIENTE.RUT=CONTRATO.RUT_CLI JOIN VEHICULO ON VEHICULO.PPU=CONTRATO.PPU_CLI ";
            resultados = sentencia.executeQuery(sql);
            int fila=0;
            while(resultados.next()){
                    jtableautos.setValueAt(resultados.getString("FOLIO"), fila,0);
                        jtableautos.setValueAt(resultados.getString("FECHA_CONTRATO"), fila,1);
                jtableautos.setValueAt(resultados.getString("RUT"), fila,2);
                jtableautos.setValueAt(resultados.getString("NOMBRE"), fila,3);
                jtableautos.setValueAt(resultados.getString("APELLIDO"), fila,4);
                jtableautos.setValueAt(resultados.getString("DIRECCION"), fila,5);
                jtableautos.setValueAt(resultados.getInt("TELEFONO"), fila,6);
                 jtableautos.setValueAt(resultados.getString("PPU"), fila,7);
                  jtableautos.setValueAt(resultados.getString("MARCA"), fila,8);
                      jtableautos.setValueAt(resultados.getString("MODELO"), fila,9);
                          jtableautos.setValueAt(resultados.getString("AÑO"), fila,10);
                              
                fila++;
            }
           
            sentencia.close();
            conexion.close();
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("Error:"+ e.getMessage());
            JOptionPane.showMessageDialog(
        null, "Error", "Error", JOptionPane.ERROR_MESSAGE);
        }

    }   
    public void modificarEstado(String PPU,  String ESTADO)  {
      try {
            Class.forName(DRIVER);
            conexion = DriverManager.getConnection(URL);
            
            sentencia = conexion.createStatement();
            String sql = "update VEHICULO set ESTADO = '"+ESTADO+"' where PPU = '"+PPU+"'";
         
            sentencia.executeUpdate(sql);
            sentencia.close();
            conexion.close();
            JOptionPane.showMessageDialog(null,"ESTADO Modificado con Exito","Atención",JOptionPane.WARNING_MESSAGE);
            
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("Error:"+ e.getMessage());
            JOptionPane.showMessageDialog(
        null, "Estado no existe en la BD","Error", JOptionPane.ERROR_MESSAGE);
        }

 }  
    
     public void mostraDatoVehiculoUnico(JTextField marca,JTextField modelo,JTextField color,JTextField año,JTextField valordia,JComboBox comboestado,String PU)  {
        try {
            Class.forName(DRIVER);
            conexion = DriverManager.getConnection(URL);
            sentencia = conexion.createStatement();
            String sql = "SELECT * FROM VEHICULO where PPU = '"+PU+"'";
            resultados = sentencia.executeQuery(sql);
            
               marca.setText( resultados.getString("MARCA"));
               modelo.setText( resultados.getString("MODELO"));
               color.setText( resultados.getString("COLOR"));
               año.setText( resultados.getString("AÑO"));
               valordia.setText( resultados.getString("VALORDIA"));
               comboestado.setSelectedItem( resultados.getString("ESTADO"));
               
              sentencia.close();
            conexion.close();
         
            
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("Error:"+ e.getMessage());
            JOptionPane.showMessageDialog(
        null, "Error", "Error", JOptionPane.ERROR_MESSAGE);
        }

    }

    public void insertDEVOLUCION(String FOLIO_DEV, String PPU, String RUT_CLI, String FECHA_DEV)  {
      try {
            Class.forName(DRIVER);
            conexion = DriverManager.getConnection(URL);
            
            sentencia = conexion.createStatement();
            String sql = "INSERT INTO DEVOLUCION"+
                         "(FOLIO_DEV,PPU,RUT_CLI,FECHA_DEV)"+
                         "VALUES('"+FOLIO_DEV+"','"+PPU+"','"+RUT_CLI+"','"+FECHA_DEV+"')";
            sentencia.executeUpdate(sql);
            sentencia.close();
            conexion.close();
            JOptionPane.showMessageDialog(null,"Cliente ingresado con Exito","Atención",JOptionPane.WARNING_MESSAGE);
            
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("Error:"+ e.getMessage());
            JOptionPane.showMessageDialog(
        null, "Rut ya existe en la BD", "ERROR", JOptionPane.ERROR_MESSAGE);
        }

 }   
    
   
public void mostrarContratoVigente(JTable jtable)  {
        try {
            Class.forName(DRIVER);
            conexion = DriverManager.getConnection(URL);
            sentencia = conexion.createStatement();
            String sql = "SELECT * FROM CONTRATO JOIN CLIENTE ON CONTRATO.RUT_CLI=CLIENTE.RUT JOIN VEHICULO ON VEHICULO.PPU=CONTRATO.PPU_CLI  WHERE CONDICION ='VIGENTE'";
            resultados = sentencia.executeQuery(sql);
            int fila=0;
            while(resultados.next()){
                jtable.setValueAt(resultados.getString("FOLIO"), fila,0);
                jtable.setValueAt(resultados.getString("FECHA_CONTRATO"), fila,1);
                jtable.setValueAt(resultados.getString("RUT_CLI"), fila,2);
                jtable.setValueAt(resultados.getString("NOMBRE"), fila,3);
                jtable.setValueAt(resultados.getString("APELLIDO"), fila,4);
                jtable.setValueAt(resultados.getString("MARCA"), fila,5);
                jtable.setValueAt(resultados.getString("MODELO"), fila,6);
                jtable.setValueAt(resultados.getString("PPU_CLI"), fila,7);
                fila++;
            }
           
            sentencia.close();
            conexion.close();
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("Error:"+ e.getMessage());
            JOptionPane.showMessageDialog(
        null, "Error", "Error", JOptionPane.ERROR_MESSAGE);
        }

    } 
public void mostrarAUTOCATEGORIA(JTable jtableautos)  {
        try {
            Class.forName(DRIVER);
            conexion = DriverManager.getConnection(URL);
            sentencia = conexion.createStatement();
            String sql = "SELECT * FROM VEHICULO JOIN CATEGORIA ON VEHICULO.CAT= CATEGORIA.ID_CAT";
            resultados = sentencia.executeQuery(sql);
            int fila=0;
            while(resultados.next()){
                    jtableautos.setValueAt(resultados.getString("PPU"), fila,0);
                        jtableautos.setValueAt(resultados.getString("MARCA"), fila,1);
                jtableautos.setValueAt(resultados.getString("MODELO"), fila,2);
                jtableautos.setValueAt(resultados.getString("AÑO"), fila,3);
                jtableautos.setValueAt(resultados.getString("COLOR"), fila,4);
                jtableautos.setValueAt(resultados.getString("NOMBRE_CAT"), fila,5);
             
                fila++;
            }
           
            sentencia.close();
            conexion.close();
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("Error:"+ e.getMessage());
            JOptionPane.showMessageDialog(
        null, "Error", "Error", JOptionPane.ERROR_MESSAGE);
        }

    } 
public void mostrarDEVOLUCION(JTable jtableautos)  {
        try {
            Class.forName(DRIVER);
            conexion = DriverManager.getConnection(URL);
            sentencia = conexion.createStatement();
            String sql = "SELECT * FROM DEVOLUCION";
            resultados = sentencia.executeQuery(sql);
            int fila=0;
            while(resultados.next()){
                jtableautos.setValueAt(resultados.getString("FOLIO_DEV"), fila,0);
                jtableautos.setValueAt(resultados.getString("PPU"), fila,1);
                jtableautos.setValueAt(resultados.getString("RUT_CLI"), fila,2);
                jtableautos.setValueAt(resultados.getString("FECHA_DEV"), fila,3);
               
             
                fila++;
            }
           
            sentencia.close();
            conexion.close();
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("Error:"+ e.getMessage());
            JOptionPane.showMessageDialog(
        null, "Error", "Error", JOptionPane.ERROR_MESSAGE);
        }

    } 



} 
    
    
  

    

