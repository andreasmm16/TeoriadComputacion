/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Proyecto1TeoriaCompu;

import java.util.ArrayList;

/**
 *
 * @author Andrea
 */
public class Grafos {

    public ArrayList<ArrayList<String>> grafos = new ArrayList<>();
   
    int tmp;

    public Grafos() {
       
        tmp = 0;
    }

    public int crearGrafo(String nameGra, boolean dirigido) {
        ArrayList<String> arr = new ArrayList<String>();
        //Verificar que grafo no exista
        for (int x = 0; x < grafos.size(); x++) {
            if (grafos.get(x).get(0) == nameGra) {
                tmp = 1;
            }
        }
        
        if(tmp==0){
            if(dirigido){
                arr.add("D");
            }else if(!dirigido){
                arr.add("N");
            }
            
            arr.add(nameGra);
            
            
            
            
            return 1;
        }else{
            //Si retorna 0 es que ya existe
            return 0;
        }
        
    }
    
    
}
