package com.mycompany.model;

public class Proceso {
    // Atributos //
	private String nombre; // Nombre proceso
    private int tiempoLlegada; // Tiempo de llegada (TII)
    private int tiempoRafaga; // Tiempo de ráfaga CPU (t)
    private int prioridad; // Prioridad
    private int tiempoRestante; // Tiempo restante (para SRTF/RR)

	// Resultados de la planificación
    private int tiempoInicio; // Tiempo de arranque (TA)
    private int tiempoFin; // Tiempo de finalización (TF)
    private int tiempoRetorno; // Tiempo de retorno (Tret = TF - TA)
	private int tiempoEspera; // Tiempo de espera (TE = Tret - TII)
	public float tiempoRespuesta; // Tiempo de respuesta T = TF - TII
    public float tasaDesperdicio; // Tasa de desperdicio W = T - t
    public float tasaPenalizacion; // Tasa de penalización p = T/t

	// Constructor con parámetros
	public Proceso(String nombre, int llegada, int rafaga, int prioridad) {
		this.nombre = nombre;
		this.tiempoLlegada = llegada;
		this.tiempoRafaga = rafaga;
		this.prioridad = prioridad;
	}

    // Metododos getters y setters
    public String getNombre() { return nombre; }
    public int getTiempoLlegada() { return tiempoLlegada; }
    public int getTiempoRafaga() { return tiempoRafaga; }
    public int getPrioridad() { return prioridad; }
    public int getTiempoRestante() { return tiempoRestante; }
    public int getTiempoInicio() { return tiempoInicio; }
    public int getTiempoFin() { return tiempoFin; }
    public int getTiempoRetorno() { return tiempoRetorno; }
    public int getTiempoEspera() { return tiempoEspera; }
    public float getTiempoRespuesta() { return tiempoRespuesta; }
    public float getTasaDesperdicio() { return tasaDesperdicio; }
    public float getTasaPenalizacion() { return tasaPenalizacion; }

	public void setNombre(String nombre) { this.nombre = nombre; }
	public void setTiempoLlegada(int tiempoLlegada) { this.tiempoLlegada = tiempoLlegada; }
	public void setTiempoRafaga(int tiempoRafaga) { this.tiempoRafaga = tiempoRafaga; }
	public void setPrioridad(int prioridad) { this.prioridad = prioridad; }
	public void setTiempoRestante(int tiempoRestante) { this.tiempoRestante = tiempoRestante; }
	public void setTiempoInicio(int tiempoInicio) { this.tiempoInicio = tiempoInicio; }
	public void setTiempoFin(int tiempoFin) { this.tiempoFin = tiempoFin; }
	public void setTiempoRetorno(int tiempoRetorno) { this.tiempoRetorno = tiempoRetorno; }
	public void setTiempoEspera(int tiempoEspera) { this.tiempoEspera = tiempoEspera; }
	public void setTiempoRespuesta(float tiempoRespuesta) { this.tiempoRespuesta = tiempoRespuesta; }
	public void setTasaDesperdicio(float tasaDesperdicio) { this.tasaDesperdicio = tasaDesperdicio; }
	public void setTasaPenalizacion(float tasaPenalizacion) { this.tasaPenalizacion = tasaPenalizacion; }
}