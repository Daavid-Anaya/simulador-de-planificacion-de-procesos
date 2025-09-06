package com.mycompany.view.manejoDeTablas;

import java.awt.Color;
import java.awt.Component;

import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

import com.mycompany.view.gui.VentanaPrincipal;

public class FormatoDiagrama extends DefaultTableCellRenderer {
	private static final long serialVersionUID = 1L;

	public FormatoDiagrama() {

    }

    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        this.setBackground((Color)null);
        super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
        String s = VentanaPrincipal.tablaDiagrama[0].getModel().getValueAt(row, column).toString();
        if (s.equals("L") || s.equals("U") || s.equals("E")) {
           this.setBackground(new Color(69, 69, 94));
        }
        return this;
    }
}