/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Proyecto1TeoriaCompu;

import com.mxgraph.swing.mxGraphComponent;
import com.mxgraph.view.mxGraph;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import org.w3c.dom.css.RGBColor;

/**
 *
 * @author Andrea
 */
public class MainFrame extends javax.swing.JFrame {

    public static Grafos grafos;
    public static Validaciones vals;
    protected static mxGraph graph;
    private mxGraphComponent graphComp;
    boolean dirig;
    Object parent;
    int xx, y;
    String graf, vertices, aristas;
    ArrayList<Object> objs = new ArrayList<Object>();
    ArrayList<String> vertexes = new ArrayList<String>();
    ArrayList<String> aris = new ArrayList<String>();

    public MainFrame() {
        initComponents();
        this.setSize(new Dimension(1000, 600));
        getContentPane().setBackground(new Color(212, 217, 226));
        grafos = new Grafos();
        vals = new Validaciones();
        dirig = false;
        graf = vertices = aristas = "";
        xx = y = 20;
        this.setLocationRelativeTo(null);
        initGUI();
    }

    public void initGUI() {
        graph = new mxGraph();
        graphComp = new mxGraphComponent(graph);
        graphComp.setBounds(500, 100, 450, 400);
        graphComp.getViewport().setBackground(Color.white);
        getContentPane().add(graphComp);
        parent = graph.getDefaultParent();
        /*  graph.getModel().beginUpdate();
        Object v1 = graph.insertVertex(parent, null, "1", 20, 20, 50, 50, "shape=ellipse;strokeColor=black;fillColor=yellow;fontSize=20");
        Object v2 = graph.insertVertex(parent, null, "2", 60, 60, 50, 50, "shape=ellipse;strokeColor=black;fillColor=yellow;fontSize=20");
        graph.insertEdge(parent, null, "", v1, v2, "endArrow=none");
        graph.getModel().endUpdate();*/
        vertexIng.setVisible(false);
        edgesI.setVisible(false);
        regresar.setVisible(false);
        label1.setVisible(false);
        label2.setVisible(false);
        v.setVisible(false);
        e.setVisible(false);
        dirigido.setVisible(false);
        nodir.setVisible(false);
        btnIng.setVisible(false);
        cbGrafos.setVisible(false);
        select2.setVisible(false);
        textArea.setVisible(false);
        nombregra.setVisible(false);
        V1.setVisible(false);
        cbGrafos2.setVisible(false);

    }

    protected void aristasCamino(String aristas, String name) {
        aris.clear();
        int tmp = 0;
        // String siono="";
        for (int x = 0; x < grafos.grafos.size(); x++) {
            if (grafos.grafos.get(x).get(1).equals(name)) {
                //        siono = grafos.grafos.get(x).get(0);
                //graf es siono para saber si dir
                tmp = x;
                break;
            }
        }
        String tm = "";
        int ar = 0;//donde empiezan aristas
        int cont = 0;
        int cantAris = 0;
        for (int x = 3; x < grafos.grafos.get(tmp).size() - 1; x++) {
            if (grafos.grafos.get(tmp).get(x).equals("{")) {
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
            if (!tm1.equals("") && !tm2.equals("")) {

                aris.add(tm1);
                aris.add(tm2);
                tm1 = tm2;
                tm2 = "";
            }

        }
        for (int x = 0; x < aris.size(); x++) {
            System.out.print(aris.get(x) + " - ");
        }
        System.out.println("");
    }

    protected void grafoGrafico(String nombre, boolean sidir, ArrayList<String> aristas) {
        graph.getModel().beginUpdate();
        xx = y = 20;
        //x y y inicializados en 20
        boolean done = false;
        int tmp = 0;
        int cont = 0;
        for (int x = 0; x < grafos.grafos.size(); x++) {
            if (grafos.grafos.get(x).get(1).equals(nombre)) {
                if (!done) {
                    if (grafos.grafos.get(x).get(0).equals("N")) {
                        tmp = 1;
                        done = true;
                    } else if (grafos.grafos.get(x).get(0).equals("D")) {
                        tmp = 2;
                        done = true;
                    }
                }

                for (int a = 3; a < grafos.grafos.get(x).size(); a++) {
                    if (grafos.grafos.get(x).get(a) != "}") {
                        objs.add(graph.insertVertex(parent, grafos.grafos.get(x).get(a), grafos.grafos.get(x).get(a), xx, y, 50, 50, "shape=ellipse;strokeColor=#E680FF;fillColor=00FFFF;fontSize=20"));
                        vertexes.add(grafos.grafos.get(x).get(a));
                        if (xx > 450) {
                            xx = 20;
                            y += 100;
                        }
                        xx += 60;
                        // graphComp.setBounds(500, 100, 450, 400);
                    } else {
                        String c = "";
                        String c2 = "";
                        for (int b = a + 2; b < grafos.grafos.get(x).size() - 1; b++) {
                            cont = 0;
                            c = "";
                            c2 = "";
                            String tmp1 = grafos.grafos.get(x).get(b);
                            b++;
                            String tmp2 = grafos.grafos.get(x).get(b);
                            Object b1 = null;
                            Object b2 = null;
                            for (int q = 0; q < vertexes.size(); q++) {
                                if (tmp1.equals(vertexes.get(q))) {
                                    b1 = objs.get(q);
                                    c = vertexes.get(q);
                                    cont++;
                                }
                                if (tmp2.equals(vertexes.get(q))) {
                                    b2 = objs.get(q);
                                    c2 = vertexes.get(q);
                                    cont++;
                                }
                                if (cont == 2) {
                                    break;
                                }
                            }
                            if (tmp == 1) {
                                if (aris.contains(c) && aris.contains(c2)) {
                                    graph.insertEdge(parent, null, "", b1, b2, "strokeColor=#B90E0A;endArrow=none");
                                } else {
                                    graph.insertEdge(parent, null, "", b1, b2, "endArrow=none");
                                }

                            } else if (tmp == 2) {
                                if (aris.contains(c) && aris.contains(c2)) {
                                    graph.insertEdge(parent, null, "", b1, b2, "strokeColor=#B90E0A");
                                } else {
                                    graph.insertEdge(parent, null, "", b1, b2);
                                }

                            }
                            /*  if (tmp == 1) {
                                graph.insertEdge(parent, null, "", b1, b2, "endArrow=none");
                            } else if (tmp == 2) {
                                graph.insertEdge(parent, null, "", b1, b2);
                            }*/

                        }

                        break;
                    }
                }
                break;
            }
        }
        graph.getModel().endUpdate();
        objs.clear();
        vertexes.clear();

    }

    /*public void deleteSelected(){
    jButton1.addActionListener(new ActionListener(){
    // cell = component.getCellAt(e.getX(), e.getY());

    @Override
    public void actionPerformed(ActionEvent arg0){
        graph.selectChildCell();
        graph.removeCells();
    }
});
}*/
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        vertexIng = new javax.swing.JTextField();
        edgesI = new javax.swing.JTextField();
        label2 = new javax.swing.JLabel();
        btnIng = new javax.swing.JButton();
        v = new javax.swing.JLabel();
        e = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        cbOpc = new javax.swing.JComboBox<>();
        select2 = new javax.swing.JButton();
        nodir = new javax.swing.JRadioButton();
        dirigido = new javax.swing.JRadioButton();
        cbGrafos = new javax.swing.JComboBox<>();
        label1 = new javax.swing.JLabel();
        regresar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        textArea = new javax.swing.JTextArea();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        select = new javax.swing.JButton();
        nombregra = new javax.swing.JTextField();
        V1 = new javax.swing.JLabel();
        cbGrafos2 = new javax.swing.JComboBox<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(212, 217, 226));
        setMaximumSize(new java.awt.Dimension(1000, 600));
        setMinimumSize(new java.awt.Dimension(1000, 600));
        getContentPane().setLayout(null);

        jLabel1.setFont(new java.awt.Font("Yu Gothic UI", 0, 34)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 0, 102));
        jLabel1.setText("Proyecto 1: Grafos");
        getContentPane().add(jLabel1);
        jLabel1.setBounds(10, 10, 430, 50);
        getContentPane().add(vertexIng);
        vertexIng.setBounds(80, 200, 240, 30);
        getContentPane().add(edgesI);
        edgesI.setBounds(80, 240, 240, 30);

        label2.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        label2.setText("Representar Grafo en forma G= ({V},{E}):");
        getContentPane().add(label2);
        label2.setBounds(20, 370, 280, 20);

        btnIng.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        btnIng.setText("Ingresar");
        btnIng.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnIngMouseClicked(evt);
            }
        });
        btnIng.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnIngActionPerformed(evt);
            }
        });
        getContentPane().add(btnIng);
        btnIng.setBounds(120, 320, 160, 30);

        v.setFont(new java.awt.Font("Arial Black", 0, 15)); // NOI18N
        v.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        v.setText("{V}");
        getContentPane().add(v);
        v.setBounds(30, 200, 50, 30);

        e.setFont(new java.awt.Font("Arial Black", 0, 15)); // NOI18N
        e.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        e.setText("{E}");
        getContentPane().add(e);
        e.setBounds(40, 240, 50, 30);

        jLabel6.setFont(new java.awt.Font("Arial", 1, 15)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(0, 102, 102));
        jLabel6.setText("Opciones:");
        jLabel6.setToolTipText("");
        getContentPane().add(jLabel6);
        jLabel6.setBounds(10, 70, 90, 30);

        cbOpc.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Ingresar Grafo", "Representar Grafo en forma G= ({V} {E})", "Mostrar Grafo de manera gr??fica", "Calcular grado de un V??rtice", "Verificar ciclos en Grafos", "Validar camino en Grafo" }));
        getContentPane().add(cbOpc);
        cbOpc.setBounds(90, 70, 240, 30);

        select2.setBackground(new java.awt.Color(0, 153, 153));
        select2.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        select2.setForeground(new java.awt.Color(255, 255, 255));
        select2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Proyecto1TeoriaCompu/cursor.png"))); // NOI18N
        select2.setText("Seleccionar");
        select2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                select2MouseClicked(evt);
            }
        });
        getContentPane().add(select2);
        select2.setBounds(210, 400, 140, 30);

        nodir.setBackground(new java.awt.Color(212, 217, 226));
        nodir.setText("Grafo No Dirigido");
        nodir.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                nodirMouseClicked(evt);
            }
        });
        getContentPane().add(nodir);
        nodir.setBounds(200, 280, 120, 25);

        dirigido.setBackground(new java.awt.Color(212, 217, 226));
        dirigido.setText("Grafo Dirigido");
        dirigido.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                dirigidoMouseClicked(evt);
            }
        });
        getContentPane().add(dirigido);
        dirigido.setBounds(70, 280, 110, 25);

        getContentPane().add(cbGrafos);
        cbGrafos.setBounds(20, 400, 170, 30);

        label1.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        label1.setText("Ingresar grafo:");
        getContentPane().add(label1);
        label1.setBounds(10, 120, 130, 20);

        regresar.setBackground(new java.awt.Color(0, 204, 153));
        regresar.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        regresar.setForeground(new java.awt.Color(255, 255, 255));
        regresar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Proyecto1TeoriaCompu/cursor.png"))); // NOI18N
        regresar.setText("Regresar");
        regresar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                regresarMouseClicked(evt);
            }
        });
        getContentPane().add(regresar);
        regresar.setBounds(340, 110, 140, 30);

        textArea.setEditable(false);
        textArea.setColumns(20);
        textArea.setRows(5);
        jScrollPane1.setViewportView(textArea);

        getContentPane().add(jScrollPane1);
        jScrollPane1.setBounds(20, 440, 330, 50);

        jButton4.setFont(new java.awt.Font("Segoe UI Light", 1, 18)); // NOI18N
        jButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Proyecto1TeoriaCompu/cerrar-sesion.png"))); // NOI18N
        jButton4.setText("SALIR");
        jButton4.setBorder(null);
        jButton4.setBorderPainted(false);
        jButton4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton4MouseClicked(evt);
            }
        });
        getContentPane().add(jButton4);
        jButton4.setBounds(840, 20, 130, 30);

        jButton5.setFont(new java.awt.Font("Microsoft Sans Serif", 0, 12)); // NOI18N
        jButton5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Proyecto1TeoriaCompu/eraser.png"))); // NOI18N
        jButton5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton5MouseClicked(evt);
            }
        });
        getContentPane().add(jButton5);
        jButton5.setBounds(880, 70, 70, 30);

        select.setBackground(new java.awt.Color(0, 153, 153));
        select.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        select.setForeground(new java.awt.Color(255, 255, 255));
        select.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Proyecto1TeoriaCompu/cursor.png"))); // NOI18N
        select.setText("Seleccionar");
        select.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                selectMouseClicked(evt);
            }
        });
        getContentPane().add(select);
        select.setBounds(340, 70, 140, 30);
        getContentPane().add(nombregra);
        nombregra.setBounds(80, 160, 240, 30);

        V1.setFont(new java.awt.Font("Arial Black", 0, 14)); // NOI18N
        V1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        V1.setText("Nombre:");
        getContentPane().add(V1);
        V1.setBounds(10, 160, 70, 30);

        cbGrafos2.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbGrafos2ItemStateChanged(evt);
            }
        });
        cbGrafos2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cbGrafos2MouseClicked(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                cbGrafos2MouseReleased(evt);
            }
        });
        getContentPane().add(cbGrafos2);
        cbGrafos2.setBounds(80, 160, 190, 30);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton4MouseClicked
        System.exit(0);
    }//GEN-LAST:event_jButton4MouseClicked

    private void jButton5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton5MouseClicked
        graph.selectChildCell();
        graph.removeCells();
    }//GEN-LAST:event_jButton5MouseClicked

    private void btnIngActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnIngActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnIngActionPerformed

    private void btnIngMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnIngMouseClicked
        if (btnIng.getText().equals("Ingresar")) {
            if (nombregra.getText().equals("")) {
                JOptionPane.showMessageDialog(null, "Ingres?? valor vacio", "ERROR", JOptionPane.ERROR_MESSAGE);
            } else if (!dirigido.isSelected() && !nodir.isSelected()) {
                JOptionPane.showMessageDialog(null, "Indique si grafo es dirigido o no dirigido", "Warning", JOptionPane.WARNING_MESSAGE);
            } else if (vals.validacionesVertex(vertexIng.getText()) == 0) {
                JOptionPane.showMessageDialog(null, "Ingres?? valor vacio", "ERROR", JOptionPane.ERROR_MESSAGE);
            } else if (vals.validacionesVertex(vertexIng.getText()) == 1) {
                JOptionPane.showMessageDialog(null, "??ltimo car??cter debe ser \"}\" en V??rtices", "ERROR", JOptionPane.ERROR_MESSAGE);
            } else if (vals.validacionesVertex(vertexIng.getText()) == 2) {
                JOptionPane.showMessageDialog(null, "Primer car??cter debe ser \"{\" en V??rtices", "ERROR", JOptionPane.ERROR_MESSAGE);
            } else if (vals.validacionesVertex(vertexIng.getText()) == 3) {
                JOptionPane.showMessageDialog(null, "Elementos en v??rtices vacios", "ERROR", JOptionPane.ERROR_MESSAGE);
            } else if (vals.validacionesVertex(vertexIng.getText()) == 4) {
                JOptionPane.showMessageDialog(null, "Elementos en v??rtices no pueden terminar con coma {num,} -> X", "ERROR", JOptionPane.ERROR_MESSAGE);
            } else if (vals.validacionesVertex(vertexIng.getText()) == 5) {
                JOptionPane.showMessageDialog(null, "No pueden ir espacios vacios en v??rtices", "ERROR", JOptionPane.ERROR_MESSAGE);
            }
            if (dirigido.isSelected()) {
                dirig = true;
            } else if (nodir.isSelected()) {
                dirig = false;
            }

            if (vals.validacionesEdges(edgesI.getText(), dirig) == 0) {
                JOptionPane.showMessageDialog(null, "Ingres?? valor vacio", "ERROR", JOptionPane.ERROR_MESSAGE);
            } else if (vals.validacionesEdges(edgesI.getText(), dirig) == 1) {
                JOptionPane.showMessageDialog(null, "??ltimo car??cter debe ser \"}\" en Aristas", "ERROR", JOptionPane.ERROR_MESSAGE);
            } else if (vals.validacionesEdges(edgesI.getText(), dirig) == 2) {
                JOptionPane.showMessageDialog(null, "Primer car??cter debe ser \"}\" en Aristas", "ERROR", JOptionPane.ERROR_MESSAGE);
            } else if (vals.validacionesEdges(edgesI.getText(), dirig) == 3) {
                JOptionPane.showMessageDialog(null, "Elementos en aristas vacios", "ERROR", JOptionPane.ERROR_MESSAGE);
            } else if (vals.validacionesEdges(edgesI.getText(), dirig) == 4) {
                JOptionPane.showMessageDialog(null, "Elementos deben ser ingresados (num,num) porque son grafos dirigidos", "ERROR", JOptionPane.ERROR_MESSAGE);
            } else if (vals.validacionesEdges(edgesI.getText(), dirig) == 5) {
                JOptionPane.showMessageDialog(null, "Elementos deben ser ingresados {num,num} porque son grafos dirigidos", "ERROR", JOptionPane.ERROR_MESSAGE);
            } else if (vals.validacionesEdges(edgesI.getText(), dirig) == 6) {
                JOptionPane.showMessageDialog(null, "Elementos no pueden terminar en coma -> {(),} | {{},} ---> X", "ERROR", JOptionPane.ERROR_MESSAGE);
            } else if (vals.validacionesEdges(edgesI.getText(), dirig) == 7) {
                JOptionPane.showMessageDialog(null, "??Par??ntesis Incompletos en Aristas!", "ERROR", JOptionPane.ERROR_MESSAGE);
            } else if (vals.validacionesEdges(edgesI.getText(), dirig) == 8) {
                JOptionPane.showMessageDialog(null, "??Llaves Incompletas en Aristas!", "ERROR", JOptionPane.ERROR_MESSAGE);
            } else if (vals.validacionesEdges(edgesI.getText(), dirig) == 9) {
                JOptionPane.showMessageDialog(null, "??No pueden ir m??s de dos elementos dentro de la secuencia! Debe ser-->(num,num) en aristas", "ERROR", JOptionPane.ERROR_MESSAGE);
            } else if (vals.validacionesEdges(edgesI.getText(), dirig) == 10) {
                JOptionPane.showMessageDialog(null, "??No pueden ir m??s de dos elementos dentro de la secuencia! Debe ser-->{num,num} en aristas", "ERROR", JOptionPane.ERROR_MESSAGE);
            } else if (vals.validacionesEdges(edgesI.getText(), dirig) == 11) {
                JOptionPane.showMessageDialog(null, "??No pueden ir espacios vacios en aristas!", "ERROR", JOptionPane.ERROR_MESSAGE);
            } else {
                graf = nombregra.getText();
                vertices = vertexIng.getText();
                aristas = edgesI.getText();
                int x = 0;
                x = grafos.crearGrafo(graf, dirig, vertices, aristas);
                if (x == 3) {
                    JOptionPane.showMessageDialog(null, "??Grafo ya existe!", "ERROR", JOptionPane.ERROR_MESSAGE);
                    graf = "";
                    nombregra.setText("");
                } else if (x == 2) {
                    JOptionPane.showMessageDialog(null, "??Una(s) arista ingresado no existe(n) en v??rtices!", "ERROR", JOptionPane.ERROR_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(null, "??Grafo ingresado correctamente!", "??XITO", JOptionPane.INFORMATION_MESSAGE);
                    nombregra.setText("");
                    vertexIng.setText("");
                    edgesI.setText("");
                    graf = vertices = aristas = "";
                    dirig = false;
                    x = 0;
                    vertexIng.setVisible(false);
                    edgesI.setVisible(false);
                    regresar.setVisible(false);
                    select.setVisible(true);
                    label1.setVisible(false);
                    v.setVisible(false);
                    e.setVisible(false);
                    nodir.setVisible(false);
                    dirigido.setVisible(false);
                    btnIng.setVisible(false);
                    nombregra.setVisible(false);
                    V1.setVisible(false);
                }

            }
        } else if (btnIng.getText().equals("Calcular grado")) {
            JOptionPane.showMessageDialog(null, "El grado de " + vertexIng.getText() + " es: " + grafos.gradoVertice(cbGrafos2.getSelectedItem().toString(), vertexIng.getText()), "Calcular Grado", JOptionPane.INFORMATION_MESSAGE);
            vertexIng.setText("");
            graph.removeCells(graph.getChildVertices(graph.getDefaultParent()));
            for (int x = 0; x < grafos.grafos.size(); x++) {
                if (grafos.grafos.get(x).get(1).equals(cbGrafos2.getSelectedItem().toString())) {
                    graf = grafos.grafos.get(x).get(1);
                    if (grafos.grafos.get(x).get(0).equals("D")) {
                        dirig = true;
                    } else if (grafos.grafos.get(x).get(0).equals("N")) {
                        dirig = false;
                    }
                    break;
                }
            }
            grafoGrafico(graf, dirig, aris);
            dirig = false;
            graf = "";
        } else if (btnIng.getText().equals("Validar ciclos")) {
            if (cbGrafos2.getItemCount() != 0) {
                if (grafos.validarCiclos(cbGrafos2.getSelectedItem().toString())) {
                    JOptionPane.showMessageDialog(null, "Si hay ciclos en grafo", "Validar Ciclos", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(null, "No hay ciclos en grafo", "Validar Ciclos", JOptionPane.INFORMATION_MESSAGE);
                }

            } else {
                JOptionPane.showMessageDialog(null, "??No hay grafo seleccionado!", "ERROR", JOptionPane.ERROR_MESSAGE);
            }

        } else if (btnIng.getText().equals("Validar camino")) {
            if (cbGrafos2.getItemCount() != 0) {
                if (grafos.validarCamino(cbGrafos2.getSelectedItem().toString(), vertexIng.getText())) {
                    for (int x = 0; x < grafos.grafos.size(); x++) {
                        if (grafos.grafos.get(x).get(1).equals(cbGrafos2.getSelectedItem().toString())) {
                            graf = grafos.grafos.get(x).get(1);
                            if (grafos.grafos.get(x).get(0).equals("D")) {
                                dirig = true;
                            } else if (grafos.grafos.get(x).get(0).equals("N")) {
                                dirig = false;
                            }
                            break;
                        }
                    }
                    graph.removeCells(graph.getChildVertices(graph.getDefaultParent()));
                    aristasCamino(vertexIng.getText(), graf);
                    grafoGrafico(cbGrafos2.getSelectedItem().toString(), dirig, aris);
                    JOptionPane.showMessageDialog(null, "Camino V??lido", "Validar Ciclos", JOptionPane.INFORMATION_MESSAGE);
                    dirig = false;
                    graf = "";
                    vertexIng.setText("");
                    aris.clear();
                } else {
                    JOptionPane.showMessageDialog(null, "Camino no v??lido", "Validar Ciclos", JOptionPane.INFORMATION_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(null, "??No hay grafo seleccionado!", "ERROR", JOptionPane.ERROR_MESSAGE);
            }

        }


    }//GEN-LAST:event_btnIngMouseClicked

    private void dirigidoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_dirigidoMouseClicked
        nodir.setSelected(false);
    }//GEN-LAST:event_dirigidoMouseClicked

    private void nodirMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_nodirMouseClicked
        dirigido.setSelected(false);
    }//GEN-LAST:event_nodirMouseClicked

    private void selectMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_selectMouseClicked
        if (cbOpc.getSelectedIndex() == 0) {
            vertexIng.setVisible(true);
            edgesI.setVisible(true);
            regresar.setVisible(true);
            select.setVisible(false);
            label1.setVisible(true);
            v.setVisible(true);
            v.setText("{V}");
            e.setVisible(true);
            dirigido.setVisible(true);
            nodir.setVisible(true);
            btnIng.setVisible(true);
            btnIng.setText("Ingresar");
            nombregra.setVisible(true);
            V1.setVisible(true);
            cbGrafos2.setVisible(false);
        } else if (cbOpc.getSelectedIndex() == 1) {
            nombregra.setText("");
            vertexIng.setText("");
            edgesI.setText("");
            dirigido.setSelected(false);
            nodir.setSelected(false);
            dirig = false;
            select.setVisible(false);
            vertexIng.setVisible(false);
            edgesI.setVisible(false);
            regresar.setVisible(true);
            label1.setVisible(false);
            v.setVisible(false);
            e.setVisible(false);
            dirigido.setVisible(false);
            nodir.setVisible(false);
            btnIng.setVisible(false);
            nombregra.setVisible(false);
            V1.setVisible(false);
            label2.setVisible(true);
            label2.setText("Representar Grafo en forma G= ({V},{E}):");
            cbGrafos.setVisible(true);
            select2.setVisible(true);
            textArea.setVisible(true);
            for (int x = 0; x < grafos.grafos.size(); x++) {
                cbGrafos.addItem(grafos.grafos.get(x).get(1));
            }
            cbGrafos2.setVisible(false);

        } else if (cbOpc.getSelectedIndex() == 2) {
            label2.setText("Seleccione grafo a graficar:");
            vertexIng.setText("");
            vertexIng.setVisible(false);
            dirig = false;
            edgesI.setVisible(false);
            regresar.setVisible(true);
            select.setVisible(false);
            label2.setVisible(true);
            v.setVisible(false);
            e.setVisible(false);
            dirigido.setVisible(false);
            nodir.setVisible(false);
            btnIng.setVisible(false);
            cbGrafos.setVisible(true);
            select2.setVisible(true);
            textArea.setVisible(true);
            nombregra.setVisible(false);
            V1.setVisible(false);
            for (int x = 0; x < grafos.grafos.size(); x++) {
                cbGrafos.addItem(grafos.grafos.get(x).get(1));
            }
            cbGrafos2.setVisible(false);
        } else if (cbOpc.getSelectedIndex() == 3) {
            label1.setVisible(true);
            vertexIng.setVisible(true);
            edgesI.setVisible(false);
            dirig = false;
            regresar.setVisible(true);
            select.setVisible(false);
            label2.setVisible(false);
            v.setText("V");
            v.setVisible(true);
            e.setVisible(false);
            dirigido.setVisible(false);
            nodir.setVisible(false);
            btnIng.setText("Calcular grado");
            btnIng.setVisible(true);
            cbGrafos2.setVisible(true);
            cbGrafos.setVisible(false);
            select2.setVisible(false);
            textArea.setVisible(false);
            nombregra.setVisible(false);
            V1.setVisible(true);
            for (int x = 0; x < grafos.grafos.size(); x++) {
                cbGrafos2.addItem(grafos.grafos.get(x).get(1));
            }

        } else if (cbOpc.getSelectedIndex() == 4) {
            regresar.setVisible(true);
            select.setVisible(false);
            label1.setVisible(true);
            dirig = false;
            V1.setVisible(true);
            cbGrafos2.setVisible(true);
            for (int x = 0; x < grafos.grafos.size(); x++) {
                cbGrafos2.addItem(grafos.grafos.get(x).get(1));
            }
            btnIng.setText("Validar ciclos");
            btnIng.setVisible(true);
        } else if (cbOpc.getSelectedIndex() == 5) {
            regresar.setVisible(true);
            select.setVisible(false);
            label1.setVisible(true);
            dirig = false;
            V1.setVisible(true);
            cbGrafos2.setVisible(true);
            for (int x = 0; x < grafos.grafos.size(); x++) {
                cbGrafos2.addItem(grafos.grafos.get(x).get(1));
            }
            btnIng.setText("Validar camino");
            btnIng.setVisible(true);
            vertexIng.setVisible(true);
        }

    }//GEN-LAST:event_selectMouseClicked

    private void regresarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_regresarMouseClicked
        nombregra.setText("");
        vertexIng.setText("");
        edgesI.setText("");
        dirigido.setSelected(false);
        nodir.setSelected(false);
        dirig = false;
        select.setVisible(true);
        vertexIng.setVisible(false);
        edgesI.setVisible(false);
        regresar.setVisible(false);
        label1.setVisible(false);
        v.setVisible(false);
        e.setVisible(false);
        dirigido.setVisible(false);
        nodir.setVisible(false);
        btnIng.setVisible(false);
        nombregra.setVisible(false);
        V1.setVisible(false);
        graf = vertices = aristas = "";
        textArea.setText("");
        textArea.setVisible(false);
        cbGrafos.removeAllItems();
        cbGrafos2.removeAllItems();
        cbGrafos.setVisible(false);
        select2.setVisible(false);
        label2.setVisible(false);
        label2.setText("");
        graph.removeCells(graph.getChildVertices(graph.getDefaultParent()));
        cbGrafos2.setVisible(false);
    }//GEN-LAST:event_regresarMouseClicked

    private void select2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_select2MouseClicked
        if (cbGrafos.getItemCount() != 0) {
            if (cbOpc.getSelectedIndex() == 1) {
                textArea.setText(grafos.impGrafo(cbGrafos.getSelectedItem().toString()));
            } else if (cbOpc.getSelectedIndex() == 2) {
                graph.removeCells(graph.getChildVertices(graph.getDefaultParent()));
                for (int x = 0; x < grafos.grafos.size(); x++) {
                    if (grafos.grafos.get(x).get(1).equals(cbGrafos.getSelectedItem().toString())) {
                        graf = grafos.grafos.get(x).get(1);
                        if (grafos.grafos.get(x).get(0).equals("D")) {
                            dirig = true;
                        } else if (grafos.grafos.get(x).get(0).equals("N")) {
                            dirig = false;
                        }
                        break;
                    }
                }
                textArea.setText(grafos.impGrafo(cbGrafos.getSelectedItem().toString()));
                grafoGrafico(graf, dirig, aris);
                dirig = false;
                graf = "";
            }
        } else {
            JOptionPane.showMessageDialog(null, "??No hay grafo seleccionado!", "ERROR", JOptionPane.ERROR_MESSAGE);
        }


    }//GEN-LAST:event_select2MouseClicked

    private void cbGrafos2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cbGrafos2MouseClicked

    }//GEN-LAST:event_cbGrafos2MouseClicked

    private void cbGrafos2ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbGrafos2ItemStateChanged


    }//GEN-LAST:event_cbGrafos2ItemStateChanged

    private void cbGrafos2MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cbGrafos2MouseReleased

         graph.removeCells(graph.getChildVertices(graph.getDefaultParent()));
        if (cbGrafos2.getItemCount() != 0) {
            for (int x = 0; x < grafos.grafos.size(); x++) {
                if (grafos.grafos.get(x).get(1).equals(cbGrafos2.getSelectedItem().toString())) {
                    graf = grafos.grafos.get(x).get(1);
                    if (grafos.grafos.get(x).get(0).equals("D")) {
                        dirig = true;
                    } else if (grafos.grafos.get(x).get(0).equals("N")) {
                        dirig = false;
                    }
                    break;
                }
            }
            grafoGrafico(graf, dirig, aris);
            dirig = false;
            graf = "";
        }

    }//GEN-LAST:event_cbGrafos2MouseReleased

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;

                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MainFrame().setVisible(true);

            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel V1;
    private javax.swing.JButton btnIng;
    private javax.swing.JComboBox<String> cbGrafos;
    private javax.swing.JComboBox<String> cbGrafos2;
    private javax.swing.JComboBox<String> cbOpc;
    private javax.swing.JRadioButton dirigido;
    private javax.swing.JLabel e;
    private javax.swing.JTextField edgesI;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel label1;
    private javax.swing.JLabel label2;
    private javax.swing.JRadioButton nodir;
    private javax.swing.JTextField nombregra;
    private javax.swing.JButton regresar;
    private javax.swing.JButton select;
    private javax.swing.JButton select2;
    private javax.swing.JTextArea textArea;
    private javax.swing.JLabel v;
    private javax.swing.JTextField vertexIng;
    // End of variables declaration//GEN-END:variables
}
