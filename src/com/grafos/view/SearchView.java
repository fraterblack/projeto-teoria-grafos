package com.grafos.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import com.grafos.model.Configuration;

public class SearchView extends JFrame {
	
	/* BUSCAR */
	private JTextField txfBuscar;
	private JLabel lbBuscar;
	private JButton btnBuscar;
	
	/* CIDADE 1 */
	private JTextField txfCod1;
	private JTextField txfCidade1;
	private JLabel lbCod1;
	private JLabel lbCidade1;
	private JLabel lbDescricao1;
	
	
	/* CIDADE 2 */
	private JTextField txfCod2;
	private JTextField txfCidade2;
	private JLabel lbCod2;
	private JLabel lbCidade2;
	private JLabel lbDescricao2;
	
	/* KM */
	private JTextField txfKM;
	private JLabel lbKM;
	
	
	/* ADD */
	private JButton btnAdiciona;
	
	/* GRID */
	private int nextDistancesGridRow = 0;
	private JScrollPane distanceGridScrollpane;
	private DefaultTableModel distancesGridModel = new DefaultTableModel();
	
	/* FINALIZACAO */
	private JButton btnSalvar;
	private JButton btnProcessar;
	private JTable distancesGridTable;
	
	public void setConfigView() {
		setSize(840, 550);
		setTitle("Busca Menor Caminho");
		setLayout(null);
        setResizable(false);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
	}
	
	public SearchView(Configuration config) {
		setConfigView();
		
		createComponents();
		createDistancesGrid();
		
		setVisible(true);
	}
	
	public void createComponents() {
		
		/* BUSCAR */
		lbBuscar = new JLabel();
		lbBuscar.setBounds(20, 20, 60, 30);
		lbBuscar.setText("Buscar:");
    	getContentPane().add(lbBuscar);
    	
    	txfBuscar = new JTextField();
    	txfBuscar.setBounds(70, 20, 500, 30);
    	getContentPane().add(txfBuscar);
    	
    	btnBuscar = new JButton();
    	btnBuscar.setBounds(580, 20, 100, 30);
    	btnBuscar.setText("Buscar");
    	getContentPane().add(btnBuscar);
    	
    	btnBuscar.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				
				//TO DO ACTION
				
			}
			
        });
    	
    	/* Cidade 1 */
    	
    	lbCod1 = new JLabel();
    	lbCod1.setBounds(20,70,45,30);
    	lbCod1.setText("Codigo:");
		getContentPane().add(lbCod1);
		
		txfCod1 = new JTextField();
		txfCod1.setBounds(70, 70, 100, 30);
    	getContentPane().add(txfCod1);
    	
    	lbCidade1 = new JLabel();
    	lbCidade1.setBounds(180,70,45,30);
    	lbCidade1.setText("Cidade:");
		getContentPane().add(lbCidade1);
		
		txfCidade1 = new JTextField();
		txfCidade1.setBounds(230, 70, 100, 30);
    	getContentPane().add(txfCidade1);
    	
    	lbDescricao1 = new JLabel();
    	lbDescricao1.setBounds(350, 70, 100, 30);
    	lbDescricao1.setText("(ORIGEM)");
    	getContentPane().add(lbDescricao1);
    	
    	/* Cidade 2 */
    	
    	lbCod2 = new JLabel();
    	lbCod2.setBounds(20,110,45,30);
    	lbCod2.setText("Codigo:");
		getContentPane().add(lbCod2);
		
		txfCod2 = new JTextField();
		txfCod2.setBounds(70, 110, 100, 30);
    	getContentPane().add(txfCod2);
    	
    	lbCidade2 = new JLabel();
    	lbCidade2.setBounds(180,110,45,30);
    	lbCidade2.setText("Cidade:");
		getContentPane().add(lbCidade2);
		
		txfCidade2 = new JTextField();
		txfCidade2.setBounds(230, 110, 100, 30);
    	getContentPane().add(txfCidade2);
    	
    	lbDescricao2 = new JLabel();
    	lbDescricao2.setBounds(350, 110, 100, 30);
    	lbDescricao2.setText("(DESTINO)");
    	getContentPane().add(lbDescricao2);
		
    	/* KM */
    	
    	lbKM = new JLabel();
    	lbKM.setBounds(20,150,45,30);
    	lbKM.setText("KM:");
    	getContentPane().add(lbKM);
    	
    	txfKM = new JTextField();
    	txfKM.setBounds(70, 150, 100, 30);
    	getContentPane().add(txfKM);
    	
    	/* ADD */
    	
    	btnAdiciona = new JButton();
    	btnAdiciona.setBounds(770, 150, 50, 30);
    	btnAdiciona.setText("+");
    	getContentPane().add(btnAdiciona);
    	
    	btnAdiciona.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				try {
					if(validateData()) {
						insertDistanceRow(txfCod1.getText(), txfCidade1.getText(), txfCod2.getText(), txfCidade2.getText(), txfKM.getText());
						
						resetFields();
					}
				} catch(Exception exception) {
					JOptionPane.showMessageDialog(null, exception.getMessage());
				}
			}
			
        });

    	/* FINALIZACAO */
    	btnSalvar = new JButton();
    	btnSalvar.setBounds(500, 475, 150, 30);
    	btnSalvar.setText("SALVAR");
    	getContentPane().add(btnSalvar);
    	
    	btnProcessar = new JButton();
    	btnProcessar.setBounds(670, 475, 150, 30);
    	btnProcessar.setText("PROCESSAR");
    	getContentPane().add(btnProcessar);
    	
    	btnSalvar.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				
				//TO DO ACTION
				
			}
			
        });
    	
    	btnProcessar.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				
				//TO DO ACTION
				
			}
			
        });
		
	}
	
	private void createDistancesGrid() {
		JPanel panel = new JPanel();
		getContentPane().add(panel);

		String[] colunas = { "Código Origem", "Cidade Origem", "Código Destino", "Cidade Destino", "Distância" };

		distancesGridModel.setColumnIdentifiers(colunas);

		distancesGridTable = new JTable(distancesGridModel);
		distanceGridScrollpane = new JScrollPane(distancesGridTable);
		distanceGridScrollpane.setBounds(20, 190, 800, 260);
		setLayout(null);
		distanceGridScrollpane.setVisible(true);
		add(distanceGridScrollpane);
	}
	
	private void insertDistanceRow(String originCityCode, String originCity, String destinationCityCode, String destinationCity, String distance) {
		//TODO: Receber os parâmetros e montar o array de dados da linha
		String[] rowData = { originCityCode, originCity, destinationCityCode, destinationCity, distance };
		
		distancesGridModel.insertRow(nextDistancesGridRow, rowData);
		
		nextDistancesGridRow++;
	}
	
	private void resetFields() {
		txfCod1.setText("");
		txfCidade1.setText("");
		txfCod2.setText("");
		txfCidade2.setText("");
		txfKM.setText("");
	}
	
	//Validate data
	private boolean validateData() throws Exception {
		
		if(txfCod1.getText().length() < 1) {
			throw new Exception("Codigo Origem não informado.");
		}
		else if(txfCod2.getText().length() < 1) {
			throw new Exception("Codigo Destino não informado.");
		}
		else if(txfCidade1.getText().length() < 1) {
			throw new Exception("Cidade de Origem não informado.");
		}
		else if(txfCidade2.getText().length() < 1) {
			throw new Exception("Cidade de Destino não informado.");
		}
		else if(txfKM.getText().length() < 1) {
			throw new Exception("Distância não informada.");
		}
		
		return true;
	}
	
}
