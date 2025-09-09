package com.mycompany.view.manejoDeTablas;

import java.awt.Font;
import java.util.ArrayList;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.mycompany.view.gui.VentanaPrincipal;

public class AgregarADiagrama {
    // Atributos
   ArrayList<Modulo> listModulo = new ArrayList<>();
   int cantDiagramas;
   int heightPanel;
   int tiempoTotal;

   // Constructor
   public AgregarADiagrama(ArrayList<Modulo> lista, int total) {
      this.listModulo = lista;
      this.tiempoTotal = total;
      construirDiagrama();
   }

   // Metodos
    public void construirDiagrama() {
        cantDiagramas = (int)Math.ceil((double)tiempoTotal / 10);
        int heightPanel = cantDiagramas * 75;
        VentanaPrincipal.panelDiagrama.removeAll();
        VentanaPrincipal.panelDiagrama.setPreferredSize(new java.awt.Dimension(1100, heightPanel));
        VentanaPrincipal.panelDiagrama.revalidate();
        VentanaPrincipal.panelDiagrama.repaint();
        
        String[] columnas = new String[]{"", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", ""};
        Object[][] datosT = new Object[][]{{"L", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", ""}, {"U", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", ""}, {"E", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", ""}};
        DefaultTableModel[] contenidoEnDiagrama = new DefaultTableModel[cantDiagramas];
        JTable[] tabla = new JTable[cantDiagramas];
        int altura = 20;

        for(int it = 0; it < cantDiagramas; ++it) {
            contenidoEnDiagrama[it] = new DefaultTableModel(datosT, columnas);
            tabla[it] = new JTable(contenidoEnDiagrama[it]);
            tabla[it].setBounds(10, altura, 1080, 60);
            tabla[it].setDefaultRenderer(Object.class, new FormatoDiagrama());
            tabla[it].setFont(new Font("Consolas", 0, 12));
            tabla[it].setEnabled(false);
            VentanaPrincipal.panelDiagrama.add(tabla[it]);
            altura += 70;
        }

        int cont = 1, numTabla = 0;
        for (Modulo modulo : listModulo) {
            if (cont > 20) {
                cont = 1;
                numTabla++;
            }
            contenidoEnDiagrama[numTabla].setValueAt(modulo.l, 0, cont);
            if (modulo.u != -1) {
                contenidoEnDiagrama[numTabla].setValueAt(modulo.u, 1, cont);
            } else {
                contenidoEnDiagrama[numTabla].setValueAt("", 1, cont);
            }
            contenidoEnDiagrama[numTabla].setValueAt(modulo.e, 2, cont);
            cont++;
      }
   }  
}
