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
    public ArrayList<String> grafosImp = new ArrayList<>();
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

}
