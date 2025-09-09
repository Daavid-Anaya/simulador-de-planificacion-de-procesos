package com.mycompany.model.algortimos;

import java.util.ArrayList;
import java.util.Comparator;

import com.mycompany.model.Proceso;
import com.mycompany.view.gui.VentanaPrincipal;
import com.mycompany.view.manejoDeTablas.AgregarADiagrama;
import com.mycompany.view.manejoDeTablas.AgregarATabla;
import com.mycompany.view.manejoDeTablas.Modulo;

public class FCFS {

    public void ejecutar() {
        ArrayList<Modulo> listModulo = new ArrayList<>();
        int cont = 0;
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

            // Llenar diagrama de Gantt
            cont = p.getTiempoInicio();
            for(int i = cont; i < p.getTiempoFin(); i++) {
                listModulo.add(new Modulo(i));
                listModulo.add(new Modulo(p.getNombre()));
            }

            for(Modulo modulo : listModulo) {
                if(modulo.u == p.getTiempoLlegada()) {
                    modulo.l = p.getNombre();
                }
            }
            cont = p.getTiempoFin();
        }
        new AgregarATabla();
        new AgregarADiagrama(listModulo, tiempoActual);
    }
}
