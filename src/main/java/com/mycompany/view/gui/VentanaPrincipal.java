package com.mycompany.view.gui;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.formdev.flatlaf.intellijthemes.FlatDarkPurpleIJTheme;
import com.mycompany.model.Proceso;
import com.mycompany.model.ValidarEntradas;
import com.mycompany.model.algortimos.FCFS;
import com.mycompany.model.algortimos.RR;
import com.mycompany.model.algortimos.SRTF;
import com.mycompany.view.manejoDeTablas.AgregarATabla;
import com.mycompany.view.manejoDeTablas.FormatoDiagrama;


public class VentanaPrincipal extends JFrame{
	private static final long serialVersionUID = 1L;

	public static ArrayList<Proceso> listaProcesos = new ArrayList<>();
	public static int cant = 0;

	private JPanel panelContenido, panelAcciones, panelInfo, panelDiagrama;
	private JMenuBar menuBar;
	private JMenu Menu;
	private JMenuItem menuItemAyuda, menuItemAtajos, menuItemAcerdaDe;
	private JLabel lblNombre, lblTiempoLlegada, lblDuracionRafaga, lblPrioridad, lblQuantum, lblTipoAlgoritmo;
	private JTextField txtNombre, txtTiempoLlegada, tctDuracionRafaga, txtPrioridad, txtQuantum;
	private JComboBox<String> comboBoxAlgoritmos;
	private JButton btnIniciar, btnAgregar, btnLimpiar;
	public static DefaultTableModel modeloTablaInfo, modeloTablaDiagrama[];
	public static JTable[] tablaDiagrama;
	private static JTable tableInfo;
	private JScrollPane scrollTablaInfo, jsPanelDiagrama;
	public static String[] columnTabla, columnDiagrama;
	public static Object[][] dataTabla, dataDiagrama1, dataDiagrama2, dataDiagrama3;

	public VentanaPrincipal() {
		// Configuración de la ventana //
        setTitle("Menu Principal");
        //setIconImage();
		setSize(1146, 640);	
		setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setLayout(null);
		iniciarComponentes();
		setVisible(true);
	}
	
	public void iniciarComponentes() {
		try {
			FlatDarkPurpleIJTheme.setup();
			
			 /**
		     * Barra de menu 
		     * creamos una barra de menu, con un menu el cual contiene 3 items.
		     */
			menuBar = new JMenuBar();
	        menuBar.setBounds(0, 0, 1120, 25);
	        getContentPane().add(menuBar);
	        
	        Menu = new JMenu("Menu");
	        Menu.setFont(new Font("Consolas", Font.BOLD, 14));
	        menuBar.add(Menu);
	        
	        menuItemAyuda = new JMenuItem("Ayuda");
	        menuItemAyuda.setFont(new Font("Consolas", Font.BOLD, 13));
	        Menu.add(menuItemAyuda);
	        
	        menuItemAtajos = new JMenuItem("Atajos");
	        menuItemAtajos.setFont(new Font("Consolas", Font.BOLD, 13));
	        Menu.add(menuItemAtajos);
	        
	        menuItemAcerdaDe = new JMenuItem("Acerca de...");
	        menuItemAcerdaDe.setFont(new Font("Consolas", Font.BOLD, 13));
	        Menu.add(menuItemAcerdaDe);
	        
	        // Panel principal//
	        panelContenido = new JPanel();
	        panelContenido.setBackground(new Color(44, 44, 59));
	        panelContenido.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
	        panelContenido.setBounds(0, 23, 1130, 578);
	        panelContenido.setLayout(null);
	        getContentPane().add(panelContenido);
	        
	        //// Panel de Acciones ////
	        panelAcciones = new JPanel();
	        panelAcciones.setBackground(new Color(44, 44, 59));
	        panelAcciones.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Acciones", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(255, 255, 255)));
	        panelAcciones.setBounds(10, 10, 1110, 100);
	        panelAcciones.setLayout(null);
	        panelContenido.add(panelAcciones);
	        
	        // Etiquetas //
	        lblNombre = new JLabel("Nombre");
	        lblNombre.setHorizontalAlignment(SwingConstants.CENTER);
	        lblNombre.setFont(new Font("Consolas", Font.PLAIN, 15));
	        lblNombre.setBounds(25, 30, 50, 15);
	        panelAcciones.add(lblNombre);
	        
	        lblTiempoLlegada = new JLabel("Tiempo de llegada");
	        lblTiempoLlegada.setHorizontalAlignment(SwingConstants.CENTER);
	        lblTiempoLlegada.setFont(new Font("Consolas", Font.PLAIN, 15));
	        lblTiempoLlegada.setBounds(171, 30, 140, 15);
	        panelAcciones.add(lblTiempoLlegada);
	        
	        lblDuracionRafaga = new JLabel("Duración rafaga");
	        lblDuracionRafaga.setHorizontalAlignment(SwingConstants.CENTER);
	        lblDuracionRafaga.setFont(new Font("Consolas", Font.PLAIN, 15));
	        lblDuracionRafaga.setBounds(336, 30, 120, 15);
	        panelAcciones.add(lblDuracionRafaga);
	        
	        lblPrioridad = new JLabel("Prioridad");
	        lblPrioridad.setHorizontalAlignment(SwingConstants.CENTER);
	        lblPrioridad.setFont(new Font("Consolas", Font.PLAIN, 15));
	        lblPrioridad.setBounds(490, 30, 80, 15);
	        panelAcciones.add(lblPrioridad);
	        
	        lblQuantum = new JLabel("Quantum");
	        lblQuantum.setHorizontalAlignment(SwingConstants.CENTER);
	        lblQuantum.setFont(new Font("Consolas", Font.PLAIN, 15));
	        lblQuantum.setBounds(620, 30, 70, 14);
	        panelAcciones.add(lblQuantum);
	        
	        lblTipoAlgoritmo = new JLabel("Tipo de algoritmo");
	        lblTipoAlgoritmo.setFont(new Font("Consolas", Font.PLAIN, 15));
	        lblTipoAlgoritmo.setHorizontalAlignment(SwingConstants.CENTER);
	        lblTipoAlgoritmo.setBounds(730, 30, 140, 14);
	        panelAcciones.add(lblTipoAlgoritmo);
	        
	        // Campos de texto //
	        txtNombre = new JTextField();
	        txtNombre.setFont(new Font("Consolas", Font.BOLD, 12));
	        txtNombre.setBounds(25, 58, 120, 22);
	        txtNombre.requestFocusInWindow();
	        txtNombre.setColumns(10);
	        panelAcciones.add(txtNombre);
	        
	        txtTiempoLlegada = new JTextField();
	        txtTiempoLlegada.setFont(new Font("Consolas", Font.BOLD, 12));
	        txtTiempoLlegada.setColumns(10);
	        txtTiempoLlegada.setBounds(170, 58, 140, 22);
	        panelAcciones.add(txtTiempoLlegada);
	        
	        tctDuracionRafaga = new JTextField();
	        tctDuracionRafaga.setFont(new Font("Consolas", Font.BOLD, 12));
	        tctDuracionRafaga.setColumns(10);
	        tctDuracionRafaga.setBounds(335, 58, 120, 22);
	        panelAcciones.add(tctDuracionRafaga);
	        
	        txtPrioridad = new JTextField();
	        txtPrioridad.setFont(new Font("Consolas", Font.BOLD, 12));
	        txtPrioridad.setColumns(10);
	        txtPrioridad.setBounds(480, 58, 100, 22);
	        panelAcciones.add(txtPrioridad);
	        
	        txtQuantum = new JTextField();
	        txtQuantum.setFont(new Font("Consolas", Font.BOLD, 12));
	        txtQuantum.setColumns(10);
	        txtQuantum.setBounds(605, 58, 100, 22);
	        panelAcciones.add(txtQuantum);
	        
	        // Cuadro combinado //
	        comboBoxAlgoritmos = new JComboBox<String>(new DefaultComboBoxModel<String>(new String[] {"FIFO", "SJF", "SRT", "PRIORIDAD", "ROUND ROBIN"}));
	        comboBoxAlgoritmos.setFont(new Font("Consolas", Font.BOLD, 12));
	        comboBoxAlgoritmos.setBounds(730, 58, 140, 22);
	        panelAcciones.add(comboBoxAlgoritmos);
	        
	        btnAgregar = new JButton("Agregar");
	        btnAgregar.setFont(new Font("Consolas", Font.BOLD, 12));
	        btnAgregar.setBounds(894, 57, 90, 22);
			btnAgregar.addActionListener(new manejadorBotonAgregar());
	        panelAcciones.add(btnAgregar);
	        
	        btnIniciar = new JButton("Iniciar");
	        btnIniciar.setFont(new Font("Consolas", Font.BOLD, 12));
	        btnIniciar.setBounds(894, 27, 190, 22);
			btnIniciar.addActionListener(new manejadorBotonIniciar());
	        panelAcciones.add(btnIniciar);
	        
	        btnLimpiar = new JButton("Limpiar");
	        btnLimpiar.setFont(new Font("Consolas", Font.BOLD, 12));
	        btnLimpiar.setBounds(994, 57, 90, 22);
	        panelAcciones.add(btnLimpiar);
	        
	        // Panel información de los procesos //
	        panelInfo = new JPanel();
	        panelInfo.setBackground(new Color(44, 44, 59));
	    	panelInfo.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Informacion Procesos", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(255, 255, 255)));
	    	panelInfo.setBounds(10, 121, 1110, 188);
	    	panelInfo.setLayout(null);
	    	panelContenido.add(panelInfo);
	        
	        // Tablas //
	        columnTabla = new String[] {"Proceso", "Tiempo llegada", "Duración Rafaga", "Prioridad", "Tiempo Arranque", "Tiempo Finalización", "Tiempo Retorno", "Tiempo Respuesta", "Taza desperdicio", "Taza penalización", "Tiempo Espera"};
	    	dataTabla = new Object[][] {{"", "", "", "", "", "", "", "", "", "", ""}, {"", "", "", "", "", "", "", "", "", "", ""},
	    								{"", "", "", "", "", "", "", "", "", "", ""}, {"", "", "", "", "", "", "", "", "", "", ""},
	    								{"", "", "", "", "", "", "", "", "", "", ""}, {"", "", "", "", "", "", "", "", "", "", ""},
	    								{"", "", "", "", "", "", "", "", "", "", ""}, {"", "", "", "", "", "", "", "", "", "", ""}};
	    	
	        modeloTablaInfo = new DefaultTableModel(dataTabla, columnTabla);
	        
	        tableInfo = new JTable(modeloTablaInfo);
	        tableInfo.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
	        tableInfo.setFont(new Font("Consolas", Font.PLAIN, 10));
			tableInfo.setEnabled(false);
	        tableInfo.getTableHeader().setReorderingAllowed(false);
	        
	        // Scroll //
	        scrollTablaInfo = new JScrollPane(tableInfo);
	        scrollTablaInfo.setBounds(10, 21, 1090, 153);
	        panelInfo.add(scrollTablaInfo);
	        
	        // Panel tabla del Diagrama //
	        panelDiagrama = new JPanel();
	        panelDiagrama.setBackground(new Color(44, 44, 59));
	    	panelDiagrama.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Diagrama", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(255, 255, 255)));
	    	panelDiagrama.setLayout(null);
	        
	        // Tabla de diagrama //
	        columnDiagrama = new String[] {"", "", "", "", "" ,"" ,"", "", "", "", "", "", "", "", "", "", "", "", "", "", ""};
	    	dataDiagrama1 = new Object[][]{{"L", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", ""}, {"U", "0", "", "1", "", "2", "", "3", "", "4", "", "5", "", "6", "", "7", "", "8", "", "9", ""}, {"E", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", ""}};
	        dataDiagrama2 = new Object[][]{{"L", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", ""}, {"U", "10", "", "11", "", "12", "", "13", "", "14", "", "15", "", "16", "", "17", "", "18", "", "19", ""}, {"E", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", ""}};
	        dataDiagrama3 = new Object[][]{{"L", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", ""}, {"U", "20", "", "21", "", "22", "", "23", "", "24", "", "25", "", "26", "", "27", "", "28", "", "29", ""}, {"E", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", ""}};
	    	
	    	modeloTablaDiagrama = new DefaultTableModel[3];
	    	modeloTablaDiagrama[0] = new DefaultTableModel(dataDiagrama1, columnDiagrama);
	    	modeloTablaDiagrama[1] = new DefaultTableModel(dataDiagrama2, columnDiagrama);
	    	modeloTablaDiagrama[2] = new DefaultTableModel(dataDiagrama3, columnDiagrama);

	    	tablaDiagrama = new JTable[3];
	    	tablaDiagrama[0] = new JTable(modeloTablaDiagrama[0]);
	    	tablaDiagrama[1] = new JTable(modeloTablaDiagrama[1]);
	    	tablaDiagrama[2] = new JTable(modeloTablaDiagrama[2]);

	    	tablaDiagrama[0].setFont(new Font("Consolas", Font.PLAIN, 12));
	    	tablaDiagrama[1].setFont(new Font("Consolas", Font.PLAIN, 12));
	    	tablaDiagrama[2].setFont(new Font("Consolas", Font.PLAIN, 12));

	    	tablaDiagrama[0].setBounds(10, 20, 1080, 60);
	    	tablaDiagrama[1].setBounds(10, 90, 1080, 60);
	    	tablaDiagrama[2].setBounds(10, 160, 1080, 60);

            tablaDiagrama[0].setEnabled(false);
	    	tablaDiagrama[1].setEnabled(false);
	    	tablaDiagrama[2].setEnabled(false);

	    	tablaDiagrama[0].setDefaultRenderer(Object.class, new FormatoDiagrama());
	    	tablaDiagrama[1].setDefaultRenderer(Object.class, new FormatoDiagrama());
	    	tablaDiagrama[2].setDefaultRenderer(Object.class, new FormatoDiagrama());

	    	panelDiagrama.add(tablaDiagrama[0]);
	    	panelDiagrama.add(tablaDiagrama[1]);
	    	panelDiagrama.add(tablaDiagrama[2]);
	    	
	    	// Scroll //
	    	jsPanelDiagrama = new JScrollPane(panelDiagrama);
	    	jsPanelDiagrama.setBounds(10, 320, 1110, 245);
	    	panelContenido.add(jsPanelDiagrama);
		} catch (Exception e) {
	        JOptionPane.showMessageDialog(this, "Error al iniciar la interfaz: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
	    }
	}

	public void limpiarCamposEntrada() {
		txtNombre.setText("");
		txtTiempoLlegada.setText("");
		tctDuracionRafaga.setText("");
		txtPrioridad.setText("");
		txtQuantum.setText("");
		comboBoxAlgoritmos.setSelectedIndex(0);
		txtNombre.requestFocusInWindow();
	}

	// Clases internas que implementa ActionListener
	// Agregar proceso
	private class manejadorBotonAgregar implements ActionListener {
	    @Override
	    public void actionPerformed(ActionEvent e) {
				new AgregarATabla(txtNombre.getText(), txtTiempoLlegada.getText().trim(), tctDuracionRafaga.getText().trim(), txtPrioridad.getText().trim());
				limpiarCamposEntrada();
	    }
	}

	// Iniciar simulación
	private class manejadorBotonIniciar implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			if (listaProcesos.size() > 0) {
				String algoritmoSeleccionado = (String) comboBoxAlgoritmos.getSelectedItem();
				switch (algoritmoSeleccionado) {
					case "FIFO":
						// Llamar al método para ejecutar el algoritmo FIFO
						new FCFS().ejecutar();
						break;
					case "SJF":
						// Llamar al método para ejecutar el algoritmo SJF
						
						break;
					case "SRT":
						// Llamar al método para ejecutar el algoritmo SRT
						new SRTF().ejecutar();
						break;
					case "PRIORIDAD":
						// Llamar al método para ejecutar el algoritmo PRIORIDAD
						
						break;
					case "ROUND ROBIN":
						// Llamar al método para ejecutar el algoritmo ROUND ROBIN
						String quantum = txtQuantum.getText().trim();
						if (new ValidarEntradas().esQuantumValido(quantum)) {
							new RR(Integer.parseInt(quantum)).ejecutar();
						} else {
							JOptionPane.showMessageDialog(null, "El valor del quantum no es válido. Por favor, ingrese un entero positivo.", "Error", JOptionPane.ERROR_MESSAGE);
						}
						break;
					default:
						
						break;
				}
			} else {
				JOptionPane.showMessageDialog(null, "No hay procesos agregados. Por favor, agregue al menos un proceso antes de iniciar la simulación.", "Información", JOptionPane.INFORMATION_MESSAGE);
			}
		}
	}
}