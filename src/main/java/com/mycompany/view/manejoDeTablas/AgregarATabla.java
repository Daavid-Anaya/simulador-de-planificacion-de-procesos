package com.mycompany.view.manejoDeTablas;

import javax.swing.JOptionPane;

import com.mycompany.model.Proceso;
import com.mycompany.model.ValidarEntradas;
import com.mycompany.view.gui.VentanaPrincipal;

public class AgregarATabla {
    public String Nombre, auxLlegada, auxRafaga, auxPrioridad, auxQuantum;
    public int llegada, rafaga, prioridad, quantum;


    public AgregarATabla(String txtNombre, String txtLlegada, String txtRafaga, String txtPrioridad, String txtQuantum) {
        this.Nombre = txtNombre;
        this.auxLlegada = txtLlegada;
        this.auxRafaga = txtRafaga;
        this.auxPrioridad = txtPrioridad;
        this.auxQuantum = txtQuantum;
        agregarALista();
    }

    public AgregarATabla() {
        agregarAll();
    }

    public final void agregarALista() {
        if (recogerDatos()) {
            Proceso p = new Proceso(Nombre, llegada, rafaga, prioridad);
            VentanaPrincipal.listaProcesos.add(p);

            if(VentanaPrincipal.cant < 8) {
                VentanaPrincipal.modeloTablaInfo.setValueAt(Nombre, VentanaPrincipal.cant, 0);
                VentanaPrincipal.modeloTablaInfo.setValueAt(llegada, VentanaPrincipal.cant, 1);
                VentanaPrincipal.modeloTablaInfo.setValueAt(rafaga, VentanaPrincipal.cant, 2);
                VentanaPrincipal.modeloTablaInfo.setValueAt(prioridad, VentanaPrincipal.cant, 3);
                VentanaPrincipal.cant++;
            } else {
                Object vector[] = {Nombre, llegada, rafaga, prioridad};
                VentanaPrincipal.modeloTablaInfo.addRow(vector);
            }
        }
    }

    public boolean recogerDatos() {
        if (new ValidarEntradas().esEntradasVacias(Nombre, auxRafaga, auxLlegada)) {
            JOptionPane.showMessageDialog(null, "Error: No se permiten entradas vacías para los campos Nombre, Tiempo de llegada y Duración de ráfaga.", "Error", JOptionPane.ERROR_MESSAGE);
            return false;     
        } else if (!new ValidarEntradas().esEnteroValido(auxLlegada, auxRafaga)) {
            JOptionPane.showMessageDialog(null, "Error: La cadena no es un entero válido para los campos Tiempo de llegada o Duración de ráfaga.", "Error", JOptionPane.ERROR_MESSAGE);
            return false; 
        } else if (new ValidarEntradas().esPrioridadValida(auxPrioridad) == false) {
            JOptionPane.showMessageDialog(null, "Error: La cadena no es un entero válido para el campo Prioridad.", "Error", JOptionPane.ERROR_MESSAGE);
            return false; 
        } else if (new ValidarEntradas().esQuantumValido(auxQuantum) == false) {
            JOptionPane.showMessageDialog(null, "Error: La cadena no es un entero válido para el campo Quantum.", "Error", JOptionPane.ERROR_MESSAGE);
            return false;  
        } else if (new ValidarEntradas().nombreRepetido(Nombre)) {
            JOptionPane.showMessageDialog(null, "Error: El nombre del proceso ya existe.", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        } else {
            llegada = Integer.parseInt(auxLlegada);
            rafaga = Integer.parseInt(auxRafaga);

            if (!auxPrioridad.equals("")) {
                prioridad = Integer.parseInt(auxPrioridad);
            } else {
                prioridad = 0;
            }

            if (!auxQuantum.equals("")) {
                quantum = Integer.parseInt(auxQuantum);
            } else {
                quantum = 0;
            }
            return true;
        }
    }

    public final void agregarAll(){
        new LimpiarTabla().limpiar();
        for(int i = 0; i < VentanaPrincipal.listaProcesos.size(); i++) {
            String vector[] = VentanaPrincipal.listaProcesos.get(i).toString().split(" ");
            VentanaPrincipal.modeloTablaInfo.addRow(vector);
        }
    }
}