package com.mycompany.model.algortimos;

import java.util.Comparator;

import com.mycompany.model.Proceso;
import com.mycompany.view.gui.VentanaPrincipal;
import com.mycompany.view.manejoDeTablas.AgregarATabla;

public class FCFS {

    public void ejecutar() {
        int tiempoActual = 0;

        // Ordenar por tiempo de llegada
        VentanaPrincipal.listaProcesos.sort(Comparator.comparingInt(Proceso::getTiempoLlegada));

        for (Proceso p : VentanaPrincipal.listaProcesos) {
            if (tiempoActual < p.getTiempoLlegada()) {
                tiempoActual = p.getTiempoLlegada(); // CPU
            }

            p.setTiempoInicio(tiempoActual);
            tiempoActual += p.getTiempoRafaga();
            p.setTiempoFin(tiempoActual);

            // Calcular métricas
            p.setTiempoRetorno(p.getTiempoFin() - p.getTiempoLlegada());
            p.setTiempoEspera(p.getTiempoRetorno() - p.getTiempoRafaga());
            p.setTiempoRespuesta(p.getTiempoEspera());
            p.setTasaDesperdicio((float)p.getTiempoEspera() / (float)p.getTiempoRetorno());
            p.setTasaPenalizacion((float)p.getTiempoRetorno() / (float)p.getTiempoRafaga());
        }
        new AgregarATabla();
    }
}
