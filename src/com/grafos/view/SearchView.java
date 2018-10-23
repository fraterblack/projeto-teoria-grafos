package com.grafos.view;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;

import com.grafos.algorithm.PathFounder;
import com.grafos.lib.FileDataManager;
import com.grafos.model.Configuration;

public class SearchView extends JFrame {
	private static final long serialVersionUID = -3940554669996360493L;

	/* BUSCAR */
	private JTextField txfSearch;
	private JLabel lblSearch;
	private JButton buttonSearch;

	/* CIDADE 1 */
	private JTextField txfCodOrigin;
	private JTextField txfCityOrigin;
	private JLabel lblCodOrigin;
	private JLabel lblCityOrigin;
	private JLabel lblDescOrigin;

	/* CIDADE 2 */
	private JTextField txfCodDestin;
	private JTextField txfCityDestin;
	private JLabel lblCodDestin;
	private JLabel lblCityDestin;
	private JLabel lblDescDestin;

	/* KM */
	private JTextField txfKM;
	private JLabel lbKM;

	/* ADD */
	private JButton buttonAdd;

	/* GRID */
	private int nextDistancesGridRow = 0;
	private JScrollPane distanceGridScrollpane;
	private DefaultTableModel distancesGridModel = new DefaultTableModel();

	/* FINALIZACAO */
	private JButton buttonSave;
	private JButton buttonProcess;
	private JTable distancesGridTable;
	
	int dialogButton = JOptionPane.YES_NO_OPTION;

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
		lblSearch = new JLabel();
		lblSearch.setBounds(20, 20, 60, 30);
		lblSearch.setText("Buscar:");
		getContentPane().add(lblSearch);

		txfSearch = new JTextField();
		txfSearch.setBounds(70, 20, 500, 30);
		txfSearch.setEditable(false);
		txfSearch.setBackground(Color.WHITE);
		getContentPane().add(txfSearch);

		buttonSearch = new JButton();
		buttonSearch.setBounds(580, 20, 100, 30);
		buttonSearch.setText("Buscar");
		getContentPane().add(buttonSearch);

		buttonSearch.addActionListener(btnSearchFileAction());

		/* Cidade 1 */

		lblCodOrigin = new JLabel();
		lblCodOrigin.setBounds(20, 70, 45, 30);
		lblCodOrigin.setText("Codigo:");
		getContentPane().add(lblCodOrigin);

		txfCodOrigin = new JTextField();
		txfCodOrigin.setBounds(70, 70, 100, 30);
		getContentPane().add(txfCodOrigin);

		lblCityOrigin = new JLabel();
		lblCityOrigin.setBounds(180, 70, 45, 30);
		lblCityOrigin.setText("Cidade:");
		getContentPane().add(lblCityOrigin);

		txfCityOrigin = new JTextField();
		txfCityOrigin.setBounds(230, 70, 100, 30);
		getContentPane().add(txfCityOrigin);

		lblDescOrigin = new JLabel();
		lblDescOrigin.setBounds(350, 70, 100, 30);
		lblDescOrigin.setText("(ORIGEM)");
		getContentPane().add(lblDescOrigin);

		/* Cidade 2 */

		lblCodDestin = new JLabel();
		lblCodDestin.setBounds(20, 110, 45, 30);
		lblCodDestin.setText("Codigo:");
		getContentPane().add(lblCodDestin);

		txfCodDestin = new JTextField();
		txfCodDestin.setBounds(70, 110, 100, 30);
		getContentPane().add(txfCodDestin);

		lblCityDestin = new JLabel();
		lblCityDestin.setBounds(180, 110, 45, 30);
		lblCityDestin.setText("Cidade:");
		getContentPane().add(lblCityDestin);

		txfCityDestin = new JTextField();
		txfCityDestin.setBounds(230, 110, 100, 30);
		getContentPane().add(txfCityDestin);

		lblDescDestin = new JLabel();
		lblDescDestin.setBounds(350, 110, 100, 30);
		lblDescDestin.setText("(DESTINO)");
		getContentPane().add(lblDescDestin);

		/* KM */

		lbKM = new JLabel();
		lbKM.setBounds(20, 150, 45, 30);
		lbKM.setText("KM:");
		getContentPane().add(lbKM);

		txfKM = new JTextField();
		txfKM.setBounds(70, 150, 100, 30);
		getContentPane().add(txfKM);

		/* ADD */

		buttonAdd = new JButton();
		buttonAdd.setBounds(770, 150, 50, 30);
		buttonAdd.setText("+");
		getContentPane().add(buttonAdd);

		buttonAdd.addActionListener(btnAddDistanceGridRow());

		/* FINALIZACAO */
		buttonSave = new JButton();
		buttonSave.setBounds(500, 475, 150, 30);
		buttonSave.setText("SALVAR");
		getContentPane().add(buttonSave);

		buttonProcess = new JButton();
		buttonProcess.setBounds(670, 475, 150, 30);
		buttonProcess.setText("PROCESSAR");
		getContentPane().add(buttonProcess);

		buttonSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//TODO: GERA O ARQUIVO APARTIR DOS DADOS DA GRID
				
				try {
					PathFounder founder = new PathFounder();

					for (Integer i = 0; i < distancesGridTable.getRowCount(); i++) {
						String[] path = new String[5];

						for (Integer j = 0; j < 5; j++) {
							path[j] = (String) distancesGridTable.getValueAt(i, j);
						}

						founder.add(path);
					}

					String result = founder.foundSmallerPath();
					
					//TODO: GRAVA MENSAGEM DE SUCESSO

					//TODO: MOVE ARQUIVO GERADO PARA A PASTA SUCESSO
				} catch (Exception error) {
					//TODO: GRAVA MENSAGEM DE ERRO
					
					//TODO: MOVE ARQUIVO GERADO PARA PASTA DE ERRO
				}
				
				txfSearch.setText("");
				resetDistanceGrid();
			}
		});

		buttonProcess.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					PathFounder founder = new PathFounder();

					for (Integer i = 0; i < distancesGridTable.getRowCount(); i++) {
						String[] path = new String[5];

						for (Integer j = 0; j < 5; j++) {
							path[j] = (String) distancesGridTable.getValueAt(i, j);
						}

						founder.add(path);
					}

					String result = founder.foundSmallerPath();

					JOptionPane.showMessageDialog(null, "Resultado: " + result);
					
					//Quer continuar?
					int dialogResult = JOptionPane.showConfirmDialog (null, "Gostaria limpar os dados da grid?", "Limpar Informações", dialogButton);

					if (dialogResult == JOptionPane.YES_OPTION) {
						txfSearch.setText("");
						resetDistanceGrid();
					}
				} catch (Exception error) {
					JOptionPane.showMessageDialog(null, "Erro ao processar dados: " + error.getMessage(), "", JOptionPane.ERROR_MESSAGE, null);
				}
			}
		});
	}

	private ActionListener btnAddDistanceGridRow() {
		return new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if (validateData()) {
						insertDistanceRow(txfCodOrigin.getText(), txfCityOrigin.getText(), txfCodDestin.getText(),
								txfCityDestin.getText(), txfKM.getText());

						resetDistanceGridFields();
					}
				} catch (Exception exception) {
					JOptionPane.showMessageDialog(null, exception.getMessage(), "", JOptionPane.ERROR_MESSAGE, null);
				}
			}
		};
	}

	private ActionListener btnSearchFileAction() {
		return new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String selectedFile = fileChooser();
				if (selectedFile != null) {
					txfSearch.setText(selectedFile);

					try {
						FileDataManager.extractFileData(selectedFile)
								.forEach((key, data) -> insertDistanceRow(data[0], data[1], data[2], data[3], data[4]));
					} catch (Exception error) {
						JOptionPane.showMessageDialog(null, error.getMessage(), "", JOptionPane.ERROR_MESSAGE, null);
					}
				} else {
					txfSearch.setText("");

					resetDistanceGrid();
				}
			}
		};
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

	private void insertDistanceRow(String originCityCode, String originCity, String destinationCityCode,
			String destinationCity, String distance) {
		String[] rowData = { originCityCode, originCity, destinationCityCode, destinationCity, distance };

		distancesGridModel.insertRow(nextDistancesGridRow, rowData);

		nextDistancesGridRow++;
	}

	private void resetDistanceGrid() {
		for (int i = nextDistancesGridRow - 1; i >= 0; i--) {
			distancesGridModel.removeRow(i);
		}

		nextDistancesGridRow = 0;
	}

	private void resetDistanceGridFields() {
		txfCodOrigin.setText("");
		txfCityOrigin.setText("");
		txfCodDestin.setText("");
		txfCityDestin.setText("");
		txfKM.setText("");
	}

	private boolean validateData() throws Exception {

		if (txfCodOrigin.getText().length() < 1) {
			throw new Exception("Codigo Origem não informado.");
		} else if (txfCodDestin.getText().length() < 1) {
			throw new Exception("Codigo Destino não informado.");
		} else if (txfCityOrigin.getText().length() < 1) {
			throw new Exception("Cidade de Origem não informado.");
		} else if (txfCityDestin.getText().length() < 1) {
			throw new Exception("Cidade de Destino não informado.");
		} else if (txfKM.getText().length() < 1) {
			throw new Exception("Distância não informada.");
		}

		return true;
	}

	private String fileChooser() {
		JFileChooser chooser = new JFileChooser();
		FileNameExtensionFilter filter = new FileNameExtensionFilter("*.txt", "txt");
		chooser.setFileFilter(filter);
		int returnVal = chooser.showOpenDialog(null);

		if (returnVal == JFileChooser.APPROVE_OPTION) {
			return chooser.getSelectedFile().getPath().toString();
		}

		return null;
	}
}
