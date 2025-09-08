package com.mycompany.model.algortimos;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import com.mycompany.model.Proceso;
import com.mycompany.view.gui.VentanaPrincipal;
import com.mycompany.view.manejoDeTablas.AgregarATabla;

public class RR {
    private int quantum;

    public RR(int quantum) {
        this.quantum = quantum;
    }

    public void ejecutar() {
        List<Proceso> terminados = new ArrayList<>();
        Queue<Proceso> cola = new LinkedList<>();

        // Ordenar por tiempo de llegada
        VentanaPrincipal.listaProcesos.sort(Comparator.comparingInt(Proceso::getTiempoLlegada));

        int tiempoActual = 0;

        while (!VentanaPrincipal.listaProcesos.isEmpty() || !cola.isEmpty()) {
            // Mover procesos que han llegado a la cola
            while (!VentanaPrincipal.listaProcesos.isEmpty() && VentanaPrincipal.listaProcesos.get(0).getTiempoLlegada() <= tiempoActual) {
                cola.add(VentanaPrincipal.listaProcesos.remove(0));
            }

            if (cola.isEmpty()) {
                tiempoActual++;
                continue;
            }

            Proceso actual = cola.poll();

            if (actual.getTiempoInicio() == 0 && tiempoActual >= actual.getTiempoLlegada()) {
                actual.setTiempoInicio(tiempoActual);
            }

            int tiempoEjecucion = Math.min(quantum, actual.getTiempoRestante());
            actual.setTiempoRestante(actual.getTiempoRestante() - tiempoEjecucion);
            tiempoActual += tiempoEjecucion;

            // Agregar procesos que llegaron durante la ejecución
            while (!VentanaPrincipal.listaProcesos.isEmpty() && VentanaPrincipal.listaProcesos.get(0).getTiempoLlegada() <= tiempoActual) {
                cola.add(VentanaPrincipal.listaProcesos.remove(0));
            }

            if (actual.getTiempoRestante() > 0) {
                // No ha terminado, vuelve al final
                cola.add(actual);
            } else {
                // Terminado
                actual.setTiempoFin(tiempoActual);
                // Calcular métricas
                actual.setTiempoRetorno(actual.getTiempoFin() - actual.getTiempoLlegada());
                actual.setTiempoEspera(actual.getTiempoRetorno() - actual.getTiempoRafaga());
                actual.setTiempoRespuesta(actual.getTiempoEspera()); // Revisar esto
                actual.setTasaDesperdicio((float)actual.getTiempoEspera() / (float)actual.getTiempoRetorno());
                actual.setTasaPenalizacion((float)actual.getTiempoRetorno() / (float)actual.getTiempoRafaga());
                terminados.add(actual);
            }
        }

        // Mover procesos de la cola de vuelta a la lista principal
        VentanaPrincipal.listaProcesos.addAll(terminados);
        VentanaPrincipal.listaProcesos.sort(Comparator.comparingInt(Proceso::getTiempoLlegada));
        new AgregarATabla();
    }
}