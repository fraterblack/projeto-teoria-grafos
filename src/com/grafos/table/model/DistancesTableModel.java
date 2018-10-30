package com.grafos.table.model;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import com.grafos.model.Path;

public class DistancesTableModel  extends AbstractTableModel{
	private static final long serialVersionUID = -1487051977919290609L;
	
	private List<Path> gradeItens;
	private String[] colunas = new String[] { "Código Origem", "Cidade Origem", "Código Destino", "Cidade Destino", "Distância" };
	
	public DistancesTableModel (List<Path> gradeItens) {
		this.gradeItens = gradeItens;
	}
	
	public DistancesTableModel () {
		this.gradeItens = new ArrayList<Path>();
	}
	
	public int getRowCount() {
		return gradeItens.size();
	}
	
	public int getColumnCount() {
		return colunas.length;
	}

	public String getColumnName(int columnIndex) {
		return colunas[columnIndex];
	}

	public Class<?> getColumnClass(int columnIndex) {
		return String.class;
	}
	
	public void setValueAt(Path aValue, int rowIndex) {
/*		Path gradeItem = gradeItens.get(rowIndex);

		gradeItem.setCodDisciplina(aValue.getDestinationCode());*/
		
		fireTableCellUpdated(rowIndex, 0);
		fireTableCellUpdated(rowIndex, 1);
		fireTableCellUpdated(rowIndex, 2);
		fireTableCellUpdated(rowIndex, 3);
	}
	
	public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
		switch (columnIndex) {
		case 0:
		default:
			System.err.println("Índice da coluna inválido");
		}
		
		fireTableCellUpdated(rowIndex, columnIndex);
	}
	
	public Object getValueAt(int rowIndex, int columnIndex) {
		Path gradeItemSelecionado = gradeItens.get(rowIndex);
		String valueObject = null;
		
		switch (columnIndex) {
		case 0:
			valueObject = gradeItemSelecionado.getOriginCode();
			break;
		case 1:
			valueObject = gradeItemSelecionado.getOriginName();
			break;
		case 2:
			valueObject = gradeItemSelecionado.getDestinationCode();
			break;
		case 3:
			valueObject = gradeItemSelecionado.getDestinationName();
			break;
		case 4:
			valueObject = gradeItemSelecionado.getDistance().toString();
			break;
		default:
			System.err.println("Índice inválido para propriedade do bean Grade.class");
		}

		return valueObject;
	}
	
	@Override
	public boolean isCellEditable(int rowIndex, int columnIndex) {
		return false;
	}
	
	public List<Path> getItems() {
		return gradeItens;
	}
	
	public Path getItem(int indiceLinha) {
		return gradeItens.get(indiceLinha);
	}
	
	public void addItem(Path item) {
		gradeItens.add(item);
		
		int ultimoIndice = getRowCount() - 1;

		fireTableRowsInserted(ultimoIndice, ultimoIndice);
	}
	
	public void removeItem(int indiceLinha) {
		gradeItens.remove(indiceLinha);

		fireTableRowsDeleted(indiceLinha, indiceLinha);
	}
	
	public void addItens(List<Path> novosGradeItens) {

		int tamanhoAntigo = getRowCount();
		gradeItens.addAll(novosGradeItens);
		fireTableRowsInserted(tamanhoAntigo, getRowCount() - 1);
	}
	
	public void limpar() {
		gradeItens.clear();
		fireTableDataChanged();
	}
	
	public boolean isEmpty() {
		return gradeItens.isEmpty();
	}
}
