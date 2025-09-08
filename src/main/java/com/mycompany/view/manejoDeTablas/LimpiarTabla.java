package com.mycompany.view.manejoDeTablas;

import java.awt.Font;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.mycompany.view.gui.VentanaPrincipal;

public class LimpiarTabla {
    public Object vector[] = {"", "", "", "", "", "", "", "", "", "", ""};

    public void limpiar() {
        int filas = VentanaPrincipal.modeloTablaInfo.getRowCount();
		for (int i = 0; i < filas; i++) {
			VentanaPrincipal.modeloTablaInfo.removeRow(0);
		}
        
        if (VentanaPrincipal.cant == 0){
            for(int i = 0; i < 8; i++){  
                VentanaPrincipal.modeloTablaInfo.addRow(vector);
            }
        }

        VentanaPrincipal.panelDiagrama.removeAll();
        VentanaPrincipal.panelDiagrama.setPreferredSize(new java.awt.Dimension(1100, 235));
        VentanaPrincipal.panelDiagrama.revalidate();
        VentanaPrincipal.panelDiagrama.repaint();

        String[] columnas = new String[]{"", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", ""};
        Object[] objetos = new Object[] {new Object[][]{{"L", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", ""}, {"U", "0", "", "1", "", "2", "", "3", "", "4", "", "5", "", "6", "", "7", "", "8", "", "9", ""}, {"E", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", ""}},
                                        new Object[][]{{"L", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", ""}, {"U", "10", "", "11", "", "12", "", "13", "", "14", "", "15", "", "16", "", "17", "", "18", "", "19", ""}, {"E", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", ""}},
                                        new Object[][]{{"L", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", ""}, {"U", "20", "", "21", "", "22", "", "23", "", "24", "", "25", "", "26", "", "27", "", "28", "", "29", ""}, {"E", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", ""}}};
        DefaultTableModel[] contenidoEnDiagrama = new DefaultTableModel[3];
        JTable[] tabla = new JTable[3];
        int altura = 20;

        for(int it = 0; it < 3; ++it) {
            contenidoEnDiagrama[it] = new DefaultTableModel((Object[][]) objetos[it], columnas);
            tabla[it] = new JTable(contenidoEnDiagrama[it]);
            tabla[it].setBounds(10, altura, 1080, 60);
            tabla[it].setDefaultRenderer(Object.class, new FormatoDiagrama());
            tabla[it].setFont(new Font("Consolas", 0, 12));
            tabla[it].setEnabled(false);
            VentanaPrincipal.panelDiagrama.add(tabla[it]);
            altura += 70;
        }
    }
}