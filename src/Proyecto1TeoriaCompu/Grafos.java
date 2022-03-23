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
    public ArrayList<String> found = new ArrayList<>();
    String tm, siono, imp;
    int tmp;

    public Grafos() {
        tm = "";
        tmp = 0;
        siono = "";
        imp = "";
    }

    public int crearGrafo(String nameGra, boolean dirigido, String vertex, String edges) {
        ArrayList<String> arr = new ArrayList<String>();
        //Verificar que grafo no exista
        for (int x = 0; x < grafos.size(); x++) {
            if (grafos.get(x).get(1).equals(nameGra)) {
                return 3;
            }
        }

        if (dirigido) {
            arr.add("D");
        } else if (!dirigido) {
            arr.add("N");
        }

        arr.add(nameGra);
        arr.add("{");
        tm = "";
        //Ingresar vertices
        for (int x = 0; x < vertex.length(); x++) {
            if (vertex.charAt(x) != '{' && vertex.charAt(x) != '}' && vertex.charAt(x) != ',') {
                tm += vertex.charAt(x);
            } else {
                if (x != 0) {
                    arr.add(tm);
                    tm = "";
                }
            }
        }
        arr.add("}");
        int con = 0;
        //Eliminar duplicados
        for (int x = 0; x < arr.size(); x++) {
            tm = arr.get(x);
            for (int a = 0; a < arr.size(); a++) {
                if (arr.get(a).equals(tm) && tm != ",") {
                    con++;
                }
            }
            if (con > 1) {
                for (int q = 0; q < arr.size(); q++) {
                    if (arr.get(q).equals(tm)) {
                        arr.remove(q);
                        con--;
                    }
                    if (con == 1) {
                        break;
                    }
                }
            }
            con = 0;
        }

        //Antes de ingresar edges, validar si existen en grafo
        tmp = 0;
        siono = "";
        for (int x = 2; x < edges.length() - 1; x++) {
            if (edges.charAt(x) != ',' && edges.charAt(x) != '(' && edges.charAt(x) != ')' && edges.charAt(x) != '{' && edges.charAt(x) != '}') {
                siono += edges.charAt(x);
            } else {
                if (!siono.equals("")) {
                    for (int a = 0; a < arr.size(); a++) {
                        if (siono.equals(arr.get(a))) {
                            tmp = 1;
                        }
                    }
                    if (tmp == 0) {
                        //Nombre de vértice no existe en grafo
                        return 2;
                    }
                    siono = "";
                    tmp = 0;
                }
            }
        }
        arr.add("{");
        tm = "";
        for (int x = 2; x < edges.length() - 1; x++) {
            if (edges.charAt(x) != '{' && edges.charAt(x) != '}' && edges.charAt(x) != ',' && edges.charAt(x) != '(' && edges.charAt(x) != ')') {
                tm += edges.charAt(x);
            } else {
                if (x != 0 && !tm.equals("")) {
                    arr.add(tm);
                    tm = "";
                }
            }
        }
        arr.add("}");
        grafos.add(new ArrayList());
        int cantGra = grafos.size() - 1;
        for (int x = 0; x < arr.size(); x++) {
            grafos.get(cantGra).add(arr.get(x));
        }
        System.out.println(impGrafo(nameGra));
        arr.clear();
        imp = "";
        tmp = 0;
        //retorna 1 si lo agregó exitosamente
        return 1;

    }

    public String impGrafo(String name) {
        imp = name + " = ({";
        tmp = 0;
        boolean done = false;
        for (int x = 0; x < grafos.size(); x++) {
            if (grafos.get(x).get(1).equals(name)) {
                if (!done) {
                    if (grafos.get(x).get(0).equals("N")) {
                        tmp = 1;
                        done = true;
                    } else if (grafos.get(x).get(0).equals("D")) {
                        tmp = 2;
                        done = true;
                    }
                }
                for (int a = 3; a < grafos.get(x).size(); a++) {
                    if (grafos.get(x).get(a) != "}") {
                        imp = imp + grafos.get(x).get(a) + ", ";
                    } else {
                        StringBuilder sb = new StringBuilder(imp);
                        sb.deleteCharAt(imp.length() - 1);
                        imp = sb.toString();
                        sb.deleteCharAt(imp.length() - 1);
                        imp = sb.toString();
                        imp += "},{";
                        for (int b = a + 2; b < grafos.get(x).size() - 1; b++) {
                            if (tmp == 1) {
                                imp = imp + "{" + grafos.get(x).get(b) + ",";
                                b++;
                                imp = imp + grafos.get(x).get(b) + "}, ";
                            } else if (tmp == 2) {
                                imp = imp + "(" + grafos.get(x).get(b) + ",";
                                b++;
                                imp = imp + grafos.get(x).get(b) + "), ";
                            }
                        }
                        StringBuilder sb2 = new StringBuilder(imp);
                        sb2.deleteCharAt(imp.length() - 1);
                        imp = sb2.toString();
                        sb2.deleteCharAt(imp.length() - 1);
                        imp = sb2.toString();
                        imp += "})";
                        break;
                    }
                }
                break;
            }
        }
        return imp;
    }

    public int gradoVertice(String nombre, String vertex) {
        int conteo = 0;
        for (int x = 0; x < grafos.size(); x++) {
            if (grafos.get(x).get(1).equals(nombre)) {
                tmp = x;
                siono = grafos.get(x).get(0);
                break;
                //N si no es dirigido y D si es dirigido
            }
        }
        if (siono.equals("N")) {
            for (int x = 3; x < grafos.get(tmp).size() - 1; x++) {
                if (grafos.get(tmp).get(x).equals("{")) {
                    x++;
                    for (int a = x; a < grafos.get(tmp).size() - 1; a++) {
                        if (grafos.get(tmp).get(a).equals(vertex)) {
                            conteo++;
                        }
                    }
                    break;
                }
            }
            return conteo;
        } else if (siono.equals("D")) {
            for (int x = 3; x < grafos.get(tmp).size() - 1; x++) {
                if (grafos.get(tmp).get(x).equals("{")) {
                    x++;
                    for (int a = x; a < grafos.get(tmp).size() - 1; a++) {
                        if (grafos.get(tmp).get(a).equals(vertex)) {
                            conteo++;
                        }
                        a++;
                    }
                    break;
                }
            }
            System.out.println(conteo);
            return conteo;
        }
        return 1;
    }

    public boolean validarCiclos(String nombre) {
        found.clear();
        int conteo = 0;
        int tm = 0;
        int cantA = 0;
        String V = "";
        siono = "";
        for (int x = 0; x < grafos.size(); x++) {
            if (grafos.get(x).get(1).equals(nombre)) {
                siono = grafos.get(x).get(0);
                tmp = x;
                break;
            }
        }
        for (int x = 3; x < grafos.get(tmp).size() - 1; x++) {
            if (grafos.get(tmp).get(x).equals("{")) {
                x++;
                tm = x;
                cantA = grafos.get(tmp).size() - x - 1;
                break;
            }
        }
        //ciclos propios
        for(int x=tm;tm<grafos.get(tmp).size();x++){
            if(x!=(grafos.get(tmp).size()-2)){
                if(grafos.get(tmp).get(x).equals(grafos.get(tmp).get(x+1))){
                    return true;
                }
            }
        }
        for (int x = tm; x < grafos.get(tmp).size(); x++) {
            V = grafos.get(tmp).get(x);
            for (int a = tm; a < grafos.get(tmp).size() - 1; a++) {
                if (grafos.get(tmp).get(a).equals(V)) {
                    conteo++;
                }
            }
            if (conteo >= 2 && !found.contains(V)) {
                found.add(V);
            }
            conteo = 0;
        }
        
            if (found.size() > 2 && found.size() == (cantA / 2)) {
            tmp = 0;
            return true;
        } else {
            tmp = 0;
            return false;
        }
            
        
     
        
    }

    public boolean validarCamino(String name, String aristas) {
        for (int x = 0; x < grafos.size(); x++) {
            if (grafos.get(x).get(1).equals(name)) {
                siono = grafos.get(x).get(0);
                tmp = x;
                break;
            }
        }
        tm = "";
        int si = 0;
        int ar = 0;//donde empiezan aristas
        int cont = 0;
        int cantAris = 0;
        for (int x = 3; x < grafos.get(tmp).size() - 1; x++) {
            if (grafos.get(tmp).get(x).equals("{")) {
                x++;
                ar = x;
                break;
            }
        }
        String tm1 = "";
        String tm2 = "";
        for (int x = 0; x < aristas.length(); x++) {
            //Ingresar vertices
            if (aristas.charAt(x) != '(' && aristas.charAt(x) != ')' && aristas.charAt(x) != ',') {
                tm += aristas.charAt(x);
            } else {
                if (x != 0) {
                    if (tm1.equals("")) {
                        tm1 = tm;
                        tm = "";
                        cantAris++;
                    } else if (tm2.equals("")) {
                        tm2 = tm;
                        tm = "";
                        cantAris++;
                    }
                }

            }
            si = 0;
            if (!tm1.equals("") && !tm2.equals("")) {
                if (siono.equals("N")) {
                    for (int a = ar; a < grafos.get(tmp).size(); a++) {
                        if (grafos.get(tmp).get(a).equals(tm1)) {
                            if (grafos.get(tmp).get(a + 1).equals(tm2)) {
                                si = 1;
                                cont += 2;
                            }
                        }
                        if (a < grafos.get(tmp).size()-1) {
                            if (grafos.get(tmp).get(a + 1).equals(tm1) && si == 0) {
                                if (grafos.get(tmp).get(a).equals(tm2)) {
                                    cont += 2;
                                }
                            }
                        }

                    }
                } else if (siono.equals("D")) {
                    for (int a = ar; a < grafos.get(tmp).size(); a++) {
                        if (grafos.get(tmp).get(a).equals(tm1)) {
                            if (grafos.get(tmp).get(a + 1).equals(tm2)) {
                                cont++;
                            }
                        }
                    }
                }
                tm1 = tm2;
                tm2 = "";
                si = 0;
            }
        }
        if (siono.equals("N")) {
            if (cont == ((cantAris - 1) * 2)) {
                return true;
            } else {
                return false;
            }
        } else if (siono.equals("D")) {
            if (cont == (cantAris - 1)) {
                return true;
            } else {
                return false;
            }
        }
        return false;
    }
}
