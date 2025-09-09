package com.mycompany.model.algortimos;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import com.mycompany.model.Proceso;
import com.mycompany.view.gui.VentanaPrincipal;
import com.mycompany.view.manejoDeTablas.AgregarADiagrama;
import com.mycompany.view.manejoDeTablas.AgregarATabla;
import com.mycompany.view.manejoDeTablas.Modulo;

public class SRTF {
    public void ejecutar() {
        ArrayList<Modulo> listModulo = new ArrayList<>();
        List<Proceso> resultado = new ArrayList<>();
        List<Proceso> terminados = new ArrayList<>();

        // Ordenar inicialmente por tiempo de llegada
        VentanaPrincipal.listaProcesos.sort(Comparator.comparingInt(Proceso::getTiempoLlegada));

        int tiempoActual = 0;

        while (!VentanaPrincipal.listaProcesos.isEmpty() || !resultado.isEmpty()) {
            // Mover procesos que han llegado a la lista de "listos"
            while (!VentanaPrincipal.listaProcesos.isEmpty() && VentanaPrincipal.listaProcesos.get(0).getTiempoLlegada() <= tiempoActual) {
                resultado.add(VentanaPrincipal.listaProcesos.remove(0));
            }

            if (resultado.isEmpty()) {
                tiempoActual++;
                continue;
            }

            // Elegir el proceso con menor tiempo restante
            resultado.sort(Comparator.comparingInt(Proceso::getTiempoRestante));
            Proceso actual = resultado.get(0);

            if (actual.getTiempoInicio() == 0 && tiempoActual >= actual.getTiempoLlegada()) {
                actual.setTiempoInicio(tiempoActual);
            }

            // Ejecutar 1 unidad de tiempo
            actual.setTiempoRestante(actual.getTiempoRestante() - 1);

            // Llenar diagrama de Gantt
            listModulo.add(new Modulo(tiempoActual));
            listModulo.add(new Modulo(actual.getNombre()));

            tiempoActual++;

            // Si terminó, calcular métricas
            if (actual.getTiempoRestante() == 0) {
                actual.setTiempoFin(tiempoActual);
                actual.setTiempoRetorno(actual.getTiempoFin() - actual.getTiempoLlegada());
                actual.setTiempoEspera(actual.getTiempoRetorno() - actual.getTiempoRafaga());
                actual.setTiempoRespuesta(actual.getTiempoEspera());
                actual.setTasaDesperdicio((float)actual.getTiempoEspera() / (float)actual.getTiempoRetorno());
                actual.setTasaPenalizacion((float)actual.getTiempoRetorno() / (float)actual.getTiempoRafaga());

                terminados.add(actual);
                resultado.remove(actual);

                for(Modulo modulo : listModulo) {
                    if(modulo.u == actual.getTiempoLlegada()) {
                        modulo.l = actual.getNombre();
                    }
                }
            }
        }

        // Mover procesos de la cola de vuelta a la lista principal
        VentanaPrincipal.listaProcesos.addAll(terminados);
        VentanaPrincipal.listaProcesos.sort(Comparator.comparingInt(Proceso::getTiempoLlegada));
        new AgregarATabla();
        new AgregarADiagrama(listModulo, tiempoActual);
    }
}