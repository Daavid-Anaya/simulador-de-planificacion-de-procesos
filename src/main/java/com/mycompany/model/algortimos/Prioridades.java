package com.mycompany.model.algortimos;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import com.mycompany.model.Proceso;
import com.mycompany.view.gui.VentanaPrincipal;
import com.mycompany.view.manejoDeTablas.AgregarADiagrama;
import com.mycompany.view.manejoDeTablas.AgregarATabla;
import com.mycompany.view.manejoDeTablas.Modulo;

public class Prioridades {
    
    public void ejecutar() {
        ArrayList<Modulo> listModulo = new ArrayList<>();
        int cont = 0;
        List<Proceso> listos = new ArrayList<>();
        List<Proceso> terminados = new ArrayList<>();

        VentanaPrincipal.listaProcesos.sort(Comparator.comparingInt(Proceso::getTiempoLlegada));

        int tiempoActual = 0;

        while (!VentanaPrincipal.listaProcesos.isEmpty() || !listos.isEmpty()) {
            // Agregar procesos que han llegado
            while (!VentanaPrincipal.listaProcesos.isEmpty() && VentanaPrincipal.listaProcesos.get(0).getTiempoLlegada() <= tiempoActual) {
                listos.add(VentanaPrincipal.listaProcesos.remove(0));
            }

            if (listos.isEmpty()) {
                tiempoActual++;
                continue;
            }

            // Elegir el de mayor prioridad (menor número)
            listos.sort(Comparator.comparingInt(Proceso::getPrioridad));
            Proceso actual = listos.get(0);

            if (actual.getTiempoInicio() == 0 && tiempoActual >= actual.getTiempoLlegada()) {
                actual.setTiempoInicio(tiempoActual);
            }

            // Ejecutar 1 unidad de tiempo
            actual.setTiempoRestante(actual.getTiempoRestante() - 1);
            tiempoActual++;

            // Si terminó
            if (actual.getTiempoRestante() == 0) {
                actual.setTiempoFin(tiempoActual);
                actual.setTiempoRetorno(actual.getTiempoFin() - actual.getTiempoLlegada());
                actual.setTiempoEspera(actual.getTiempoRetorno() - actual.getTiempoRafaga());
                actual.setTiempoRespuesta(actual.getTiempoEspera()); // Revisar esto
                actual.setTasaDesperdicio((float)actual.getTiempoEspera() / (float)actual.getTiempoRetorno());
                actual.setTasaPenalizacion((float)actual.getTiempoRetorno() / (float)actual.getTiempoRafaga());
                terminados.add(actual);
                listos.remove(actual);

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
        }

        // Mover procesos de la cola de vuelta a la lista principal
        VentanaPrincipal.listaProcesos.addAll(terminados);
        VentanaPrincipal.listaProcesos.sort(Comparator.comparingInt(Proceso::getTiempoLlegada));
        new AgregarATabla();
        new AgregarADiagrama(listModulo, tiempoActual);
    }
}