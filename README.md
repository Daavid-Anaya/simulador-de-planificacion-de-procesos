<h1 align="center">Simulador de Planificación de Procesos en Java</h1>

<p align="center">
  <img src="https://img.shields.io/badge/STATUS-EN%20DESARROLLO-blue">
</p>

Simulador educativo de planificación de procesos que implementa algoritmos **no apropiativos** y **apropiativos**. Permite capturar procesos manualmente o cargarlos desde archivo, seleccionar el algoritmo de planificación, visualizar resultados y guardar los cálculos en un archivo de reporte. Incluye una sección "Acerca de..." con información del equipo.

## 🔍 Características

1. **Captura de procesos**
   - Ingreso manual de procesos (ID, tiempo de ráfaga, prioridad, tiempo de llegada, etc.).
   - Carga desde archivo (formato definido) de una lista de procesos.

2. **Algoritmos de planificación**
   - No apropiativos:
     - FCFS (First Come First Served)
     - SJF (Shortest Job First) — versión no apropiativa
   - Apropiativos:
     - SRTF (Shortest Remaining Time First)
     - Round Robin (con quantum configurable)
     - Planificación por prioridades preemptiva

3. **Ejecución de la planificación**
   - Simulación paso a paso del avance de la CPU.
   - Cálculo de métricas: tiempo de espera, tiempo de retorno, turnaround, utilización de CPU, etc.

4. **Persistencia**
   - Carga de procesos desde archivo.
   - Guardado de la hoja de planificación y métricas en archivo de reporte (texto o CSV).

5. **Interfaz básica**
   - Selección del algoritmo a utilizar.
   - Configuración de parámetros (ej. quantum para Round Robin).
   - Menú “Acerca de…” con información del equipo/desarrolladores.

## 🖥 Tecnologías utilizadas
[![Tecnologías utilizadas](https://skillicons.dev/icons?i=java,vscode,eclipse)](https://skillicons.dev)
