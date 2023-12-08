/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author alejo
 */
public class Credencial {
    
    String nombre,fecha,carrera,ncontrol;
    int semestre;
    
    public Credencial(String c,String n,String ca,String f, int s){
        ncontrol=c;
        nombre=n;
        carrera=ca;
        semestre=s;
        fecha = f;
        
    }
    
    public String[] toRenglon(){
        String[] cadena = new String[5];
        
        cadena[0]=ncontrol;
        cadena[1]=nombre;
        cadena[2]=carrera;
        cadena[3]=fecha;
        cadena[4]=""+semestre;
        return cadena;
    }
}
