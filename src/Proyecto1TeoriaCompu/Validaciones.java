/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Proyecto1TeoriaCompu;

/**
 *
 * @author Andrea
 */
public class Validaciones {

    int siz, leftPar, rightPar, cont;

    public Validaciones() {
        siz = leftPar = rightPar = cont = 0;
    }

    public int validacionesVertex(String vertex) {
        siz = vertex.length();
        if(siz==0){
            //vertex vacío
            return 0;
        }
        if (vertex.charAt(siz - 1) != '}') {
            //Último carácter debe ser \"}\"
            return 1;
        } else if (vertex.charAt(0) != '{') {
            //Primer carácter debe ser {
            return 2;
        } else if (vertex.charAt(siz - 1) == '}' && vertex.charAt(0) == '{' && siz == 2) {
            //Elementos vacíos
            return 3;
        } else if (siz > 2 && vertex.charAt(siz - 2) == ',') {
            //"Elementos no pueden terminar en coma ,
            return 4;
        }
        for (int x = 0; x < vertex.length(); x++) {
            if (vertex.charAt(x)==' ') {
                // "No pueden ir espacios vacíos" << endl;
                return 5;
            }
        }

        return 6;
    }

    public int validacionesEdges(String edge, boolean dir) {
        siz = edge.length();
        if(siz==0){
            //vertex vacío
            return 0;
        }
        if (edge.charAt(siz - 1) != '}') {
            //Último carácter debe ser }
            return 1;
        } else if (edge.charAt(0) != '{') {
            //Primer caracter debe ser {
            return 2;
        } else if (edge.charAt(siz - 1) == '}' && edge.charAt(0) == '{' && siz == 2) {
            //elementos vacios
            return 3;
        } else if (edge.charAt(1) != '(' && dir) {
            //elementos deben ser ingresados (num,num) porque es grafo dirigido
            return 4;
        } else if (edge.charAt(1) != '{' && !dir) {
            //elementos deben ser ingresados {num,num} porque es grafo no dirigido
            return 5;
        } else if (edge.charAt(siz - 2) == ',') {
            //elementos no pueden terminar en coma {(),}
            return 6;
        }
        leftPar = rightPar = cont = 0;
        if (dir) {
            for (int x = 0; x < edge.length(); x++) {
                if (edge.charAt(x) == '(') {
                    leftPar++;
                } else if (edge.charAt(x) == ')') {
                    rightPar++;
                }

                if (leftPar == rightPar) {
                    leftPar = rightPar = 0;
                }
            }
            if (leftPar != rightPar) {
                //¡Paréntesis Incompletos!"
                return 7;
            }
        }
        if (!dir) {
            for (int x = 1; x < edge.length() - 1; x++) {
                if (edge.charAt(x) == '{') {
                    leftPar++;
                } else if (edge.charAt(x) == '}') {
                    rightPar++;
                }

                if (leftPar == rightPar) {
                    leftPar = rightPar = 0;
                }
            }
            if (leftPar != rightPar) {
                //¡Llaves Incompletas!"
                return 8;
            }
        }
        //Para verificar si es par ordenado
        if (dir) {
            for (int x = 1; x < edge.length() - 1; x++) {
                if (edge.charAt(x) == '(') {
                    leftPar = 1;
                } else if (edge.charAt(x) == ')') {
                    rightPar = 1;
                } else if (edge.charAt(x) == ',' && leftPar == 1) {
                    cont++;
                }

                if (leftPar == rightPar) {
                    if (cont > 1) {
                        //¡No pueden ir más de dos elementos dentro de la secuencia! Debe ser-->(num,num)
                        return 9;
                    } else {
                        cont = leftPar = rightPar = 0;
                    }
                }
            }
        } else if (!dir) {
            for (int x = 1; x < edge.length() - 1; x++) {
                if (edge.charAt(x) == '{') {
                    leftPar = 1;
                } else if (edge.charAt(x) == '}') {
                    rightPar = 1;
                } else if (edge.charAt(x) == ',' && leftPar == 1) {
                    cont++;
                }

                if (leftPar == rightPar) {
                    if (cont > 1) {
                        //¡No pueden ir más de dos elementos dentro de la secuencia! Debe ser-->{num,num}
                        return 10;
                    } else {
                        cont = leftPar = rightPar = 0;
                    }
                }
            }
        }
        
        for (int x = 0; x < edge.length(); x++) {
            if (edge.charAt(x)==' ') {
                // "No pueden ir espacios vacíos" << endl;
                return 11;
            }
        }

        return 12;
    }
}
