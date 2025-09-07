package com.mycompany.view.manejoDeTablas;

import com.mycompany.view.gui.VentanaPrincipal;

public class LimpiarTabla {
    public Object vector[] = {"", "", "", "", "", "", "", "", "", "", ""};

    public void limpiar() {
        int filas = VentanaPrincipal.modeloTablaInfo.getRowCount();
		for(int i = 0; i < filas; i++) {
			VentanaPrincipal.modeloTablaInfo.removeRow(0);
		}
        if(VentanaPrincipal.cant == 0){
            for(int i = 0; i < 8; i++){  
            VentanaPrincipal.modeloTablaInfo.addRow(vector);
        }
        }
    }
}