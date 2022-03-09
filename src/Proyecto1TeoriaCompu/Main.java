/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Proyecto1TeoriaCompu;

import com.mxgraph.swing.mxGraphComponent;
import com.mxgraph.view.mxGraph;

/**
 *
 * @author Andrea
 */
public class Main {
     public static MainFrame frame;
     public static CaminosFrame frame2;
    public static Grafos grafos;
   
    public static void main(String[] args) {
      frame = new MainFrame();
      frame2 = new CaminosFrame();
      grafos= new Grafos();
      frame.setVisible(true);
      frame.setLocationRelativeTo(null);
    }
}
