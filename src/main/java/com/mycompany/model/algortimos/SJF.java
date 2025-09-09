package com.mycompany.model.algortimos;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import com.mycompany.model.Proceso;
import com.mycompany.view.gui.VentanaPrincipal;
import com.mycompany.view.manejoDeTablas.AgregarADiagrama;
import com.mycompany.view.manejoDeTablas.AgregarATabla;
import com.mycompany.view.manejoDeTablas.Modulo;

public class SJF {
    
    public void ejecutar() {
        ArrayList<Modulo> listModulo = new ArrayList<>();
        int cont = 0;
        List<Proceso> resultado = new ArrayList<>();

        // Ordenar por tiempo de llegada inicialmente
        VentanaPrincipal.listaProcesos.sort(Comparator.comparingInt(Proceso::getTiempoLlegada));

        int tiempoActual = 0;
        while (!VentanaPrincipal.listaProcesos.isEmpty()) {
            // Filtrar procesos que ya han llegado
            List<Proceso> disponibles = new ArrayList<>();
            for (Proceso p : VentanaPrincipal.listaProcesos) {
                if (p.getTiempoLlegada() <= tiempoActual) {
                    disponibles.add(p);
                }
            }

            if (disponibles.isEmpty()) {
                // Si no hay procesos disponibles, avanzar tiempo
                tiempoActual = VentanaPrincipal.listaProcesos.get(0).getTiempoLlegada();
                continue;
            }

            // Elegir proceso con menor ráfaga
            Proceso actual = disponibles.stream()
                    .min(Comparator.comparingInt(Proceso::getTiempoRafaga))
                    .orElseThrow();

            VentanaPrincipal.listaProcesos.remove(actual);

            // Calcular tiempos
            actual.setTiempoInicio(tiempoActual);
            tiempoActual += actual.getTiempoRafaga();
            actual.setTiempoFin(tiempoActual);
            actual.setTiempoRetorno(actual.getTiempoFin() - actual.getTiempoLlegada());
            actual.setTiempoEspera(actual.getTiempoRetorno() - actual.getTiempoRafaga());
            actual.setTiempoRespuesta(actual.getTiempoEspera());
            actual.setTasaDesperdicio((float)actual.getTiempoEspera() / (float)actual.getTiempoRetorno());
            actual.setTasaPenalizacion((float)actual.getTiempoRetorno() / (float)actual.getTiempoRafaga());
            resultado.add(actual);

            // Llenar diagrama de Gantt
                cont = actual.getTiempoInicio();
                for(int i = cont; i < actual.getTiempoFin(); i++) {
                    listModulo.add(new Modulo(i));
                    listModulo.add(new Modulo(actual.getNombre()));
                }

                for(Modulo modulo : listModulo) {
                    if(modulo.u == actual.getTiempoLlegada()) {
                        modulo.l = actual.getNombre();
                    }
                }
                cont = actual.getTiempoFin();
        }

        // Mover procesos de la cola de vuelta a la lista principal
        VentanaPrincipal.listaProcesos.addAll(resultado);
        VentanaPrincipal.listaProcesos.sort(Comparator.comparingInt(Proceso::getTiempoLlegada));
        new AgregarATabla();
        new AgregarADiagrama(listModulo, tiempoActual);
    }
}