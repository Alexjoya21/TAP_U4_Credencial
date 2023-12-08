/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
import java.sql.*;
import java.util.ArrayList;
/**
 *
 * @author alejo
 */
public class BaseDatos {
    Connection conexion;
    Statement transaccion;
    ResultSet cursor;
    String cadenaConexion="jdbc:mysql://buh1mfiritjwanx0sa7t-mysql.services.clever-cloud.com:3306/buh1mfiritjwanx0sa7t?zeroDateTimeBehavior=CONVERT_TO_NULL",
            usuario="um6qu4elkzqqxvfc",
            pass="lM8eEXhu6FeRSbqxE9eS";
    
    public BaseDatos(){
        try{
        Class.forName("com.mysql.cj.jdbc.Driver");
        conexion=DriverManager.getConnection(cadenaConexion, usuario, pass);
        transaccion=conexion.createStatement();
        
        }catch(SQLException e){
        
        }catch(ClassNotFoundException ex){
        
        }
    }
    public boolean insertar(Credencial p){
        String SQL_Insertar="INSERT INTO `CREDENCIAL`(`NUMCONTROL`, `NOMBREALUMNO`, `CARRERA`, `FECHAEXPEDICION`, `SEMESTRE`)"
                + " VALUES ('%CON%','%NOM%','%CAR%','%FEC%','%SEM%')";
        SQL_Insertar = SQL_Insertar.replace("%CON%", p.ncontrol);
        SQL_Insertar = SQL_Insertar.replace("%NOM%", p.nombre);
        SQL_Insertar = SQL_Insertar.replace("%CAR%", p.carrera);
        SQL_Insertar = SQL_Insertar.replace("%FEC%", p.fecha);
        SQL_Insertar = SQL_Insertar.replace("%SEM%", ""+p.semestre);
        
        try{
            transaccion.execute(SQL_Insertar);
        }catch(SQLException e){
        return false;
        }
        return true;
    }
    public ArrayList<Credencial> mostrarTodos(){
        ArrayList<Credencial> datos = new ArrayList<Credencial>();
        String SQL_consulta = "SELECT * FROM `CREDENCIAL`  ";
        //RESULSET = variable que maneja las tuplas resultado
        
        try{
            cursor=transaccion.executeQuery(SQL_consulta);
            if(cursor.next()){
                do{
                    
                    Credencial p=new Credencial(cursor.getString(1),
                                            cursor.getString(2),
                                            cursor.getString(3),
                                            cursor.getString(4),
                                            cursor.getInt(5));
                    datos.add(p);
                }while(cursor.next());
            }
        }catch(SQLException e){
        
        }
        
        return datos;
    }
    public Credencial obtnerporID(String ID){
        String SQL_consulta = "SELECT * FROM `Persona` WHERE `ID`="+ID;
        //RESULSET = variable que maneja las tuplas resultado
        
        try{
            cursor=transaccion.executeQuery(SQL_consulta);
            Credencial p=new Credencial(cursor.getString(1),
                                            cursor.getString(2),
                                            cursor.getString(3),
                                            cursor.getString(4),
                                            cursor.getInt(5));
            return p;
        }catch(SQLException e){
        
        }
        
        return new Credencial("","","","",-1);
    }
    
    public boolean Eliminar(String IDaEliminar){
        String SQL_eliminar ="DELETE FROM `CREDENCIAL` WHERE `ID` ="+IDaEliminar;
        
        try {
            transaccion.execute(SQL_eliminar);
        } catch (SQLException ex) {
            return false;
        }
        return true;
    }
    
    public boolean actualizar(Credencial p){
        String SQL_actualizar="UPDATE `CREDENCIAL` SET `NOMBREALUMNO`='%NOM%',"
                + "`CARRERA`='%CAR%',`FECHAEXPEDICION`='%FEC%',`SEMESTRE`='%SEM%' WHERE `NUMCONTROL`="+p.ncontrol;
        SQL_actualizar = SQL_actualizar.replace("%NOM%", p.nombre);
        SQL_actualizar = SQL_actualizar.replace("%CAR%", p.carrera);
        SQL_actualizar = SQL_actualizar.replace("%FEC%", p.fecha);
        SQL_actualizar = SQL_actualizar.replace("%SEM%", ""+p.semestre);
        
        try{
            transaccion.executeUpdate(SQL_actualizar);
        }catch(SQLException e){
        return false;
        }
        return true;
    }
}
