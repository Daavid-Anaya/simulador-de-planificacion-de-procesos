package com.mycompany.model;

import com.mycompany.view.gui.VentanaPrincipal;

public class ValidarEntradas {
    public boolean esEnteroValido(String llegada, String rafaga) {
        try {
            Integer.parseInt(llegada);
            Integer.parseInt(rafaga);
            return true; // La cadena es un entero válido
        } catch (NumberFormatException e) {
            return false; // La cadena no es un entero válido
        }
    }

    public boolean esEntradasVacias(String nombre, String rafaga, String llegada) {
        return nombre.isEmpty() || rafaga.isEmpty() || llegada.isEmpty();
    }

    public boolean esPrioridadValida(String prioridad) {
        if (prioridad.isEmpty()) {
            prioridad = "0";
            return true; // La prioridad es opcional, se considera válida si está vacía
        }

        try {
            Integer.parseInt(prioridad);
            return true; // La cadena es un entero válido
        } catch (NumberFormatException e) {
            return false; // La cadena no es un entero válido
        }
    }

    public boolean esQuantumValido(String quantum) {
        if (quantum.isEmpty()) {
            return false; // La cadena esta vacía, se considera inválida
        }
        try {
            int q = Integer.parseInt(quantum);
            if (q > 0) {
                return true; // El entero es positivo
            } else {
                return false; // El entero no es positivo
            }
        } catch (NumberFormatException e) {
            return false; // La cadena no es un entero válido
        }
    }

    public boolean esValorNoNegativo(int llegada, int rafaga, int prioridad) {
        return (llegada >= 0 && rafaga > 0 && prioridad >= 0);
    }

    public boolean nombreRepetido(String nombre) {
        for (Proceso p : VentanaPrincipal.listaProcesos) {
            if (p.getNombre().equals(nombre)) {
                return true; // El nombre ya existe en la lista
            }
        }
        return false; // El nombre no existe en la lista
    }
}