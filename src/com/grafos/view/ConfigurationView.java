package com.grafos.view;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import com.grafos.lib.DatabaseManager;
import com.grafos.model.Configuration;

public class ConfigurationView extends JFrame {
	private static final long serialVersionUID = 6632200882344575857L;

	private static final String DEFAULT_SUCCESS_FOLDER = "sucesso";
	private static final String DEFAULT_ERROR_FOLDER = "erro";

	private JLabel labelRootDirectory;
	private JTextField tfxRootDirectory;
	private JLabel lblSuccessFolder;
	private JTextField txfSuccessFolder;
	private JLabel lblErrorFolder;
	private JTextField txfErrorFolder;
	private JCheckBox ckbAutomaticRoute;
	private JButton buttonSave;
	private JButton buttonSearchDirectory;

	public ConfigurationView() {
		setSize(300, 200);
		setTitle("Configuração");
		setLayout(null);
		setResizable(false);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);

		createComponents();
	}

	private void createComponents() {
		buttonSearchDirectory = new JButton();
		buttonSearchDirectory.setBounds(250, 10, 30, 24);
		buttonSearchDirectory.setText("Buscar");
		buttonSearchDirectory.addActionListener(btnSearchDirectoryAction());
		getContentPane().add(buttonSearchDirectory);

		// Pasta
		labelRootDirectory = new JLabel("Pasta: *");
		labelRootDirectory.setBounds(10, 10, 50, 25);
		getContentPane().add(labelRootDirectory);

		tfxRootDirectory = new JTextField();
		tfxRootDirectory.setBounds(80, 10, 165, 25);
		getContentPane().add(tfxRootDirectory);

		// Sucesso
		lblSuccessFolder = new JLabel("Sucesso:");
		lblSuccessFolder.setBounds(10, 40, 80, 25);
		getContentPane().add(lblSuccessFolder);

		txfSuccessFolder = new JTextField();
		txfSuccessFolder.addFocusListener(new FocusListener() {
			public void focusGained(FocusEvent e) {
				unsetPlaceholder(txfSuccessFolder, DEFAULT_SUCCESS_FOLDER);
			}

			public void focusLost(FocusEvent e) {
				setPlaceholder(txfSuccessFolder, DEFAULT_SUCCESS_FOLDER);
			}
		});
		txfSuccessFolder.setBounds(80, 40, 100, 25);
		getContentPane().add(txfSuccessFolder);

		// Erro
		lblErrorFolder = new JLabel("Erro:");
		lblErrorFolder.setBounds(11, 70, 50, 25);
		getContentPane().add(lblErrorFolder);

		txfErrorFolder = new JTextField();
		txfErrorFolder.addFocusListener(new FocusListener() {
			public void focusGained(FocusEvent e) {
				unsetPlaceholder(txfErrorFolder, DEFAULT_ERROR_FOLDER);
			}

			public void focusLost(FocusEvent e) {
				setPlaceholder(txfErrorFolder, DEFAULT_ERROR_FOLDER);
			}
		});
		txfErrorFolder.setBounds(80, 70, 100, 25);
		getContentPane().add(txfErrorFolder);

		// Rota Automatica
		ckbAutomaticRoute = new JCheckBox("Rota automática");
		ckbAutomaticRoute.setSelected(false);
		ckbAutomaticRoute.setBounds(80, 100, 150, 25);
		getContentPane().add(ckbAutomaticRoute);

		// Salvar
		buttonSave = new JButton("Salvar");
		buttonSave.setBounds(79, 130, 130, 25);
		getContentPane().add(buttonSave);

		DatabaseManager db = new DatabaseManager();

		// seta os valores já existentes da configuração
		if (db.hasConfig()) {
			Configuration config = db.getConfig();
			txfErrorFolder.setText(config.getError());
			tfxRootDirectory.setText(config.getFolder());
			txfSuccessFolder.setText(config.getSucess());
			ckbAutomaticRoute.setSelected(config.getAutomatic());
		} else {
			setPlaceholder(txfSuccessFolder, DEFAULT_SUCCESS_FOLDER);
			setPlaceholder(txfErrorFolder, DEFAULT_ERROR_FOLDER);
		}

		buttonSave.addActionListener(btnSaveAction());
	}

	private ActionListener btnSaveAction() {
		return new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					validateRequiredFields();
					
					//Garante que o valor das pastas de sucesso e erro sejam setadas
					txfSuccessFolder.setText(txfSuccessFolder.getText().isEmpty() ? DEFAULT_SUCCESS_FOLDER : txfSuccessFolder.getText());
					txfErrorFolder.setText(txfErrorFolder.getText().isEmpty() ? DEFAULT_ERROR_FOLDER : txfErrorFolder.getText());
					
					DatabaseManager dm = new DatabaseManager();

					Configuration configuration = new Configuration(tfxRootDirectory.getText(),
							txfSuccessFolder.getText(), txfErrorFolder.getText(), ckbAutomaticRoute.isSelected());
					dm.createConfig(configuration);

					setVisible(false);
					dispose();
				} catch (Exception error) {
					JOptionPane.showMessageDialog(null, error.getMessage(), "", JOptionPane.ERROR_MESSAGE, null);
				}
			}
		};
	}

	private ActionListener btnSearchDirectoryAction() {
		return new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String selectedFile = folderChooser();
				if (selectedFile != null) {
					tfxRootDirectory.setText(selectedFile);
				} else {
					tfxRootDirectory.setText("");
				}
			}
		};
	}

	private void unsetPlaceholder(JTextField textField, String placeholderMessage) {
		if (textField.getText().equals(placeholderMessage)) {
			textField.setText("");
			textField.setForeground(Color.BLACK);
		}
	}

	private void setPlaceholder(JTextField textField, String placeholderMessage) {
		if (textField.getText().isEmpty()) {
			textField.setForeground(Color.GRAY);
			textField.setText(placeholderMessage);
		}
	}

	private boolean validateRequiredFields() throws Exception {
		if (tfxRootDirectory.getText().isEmpty()) {
			throw new Exception("Informe o caminho para o diretório raiz.");
		}

		return true;
	}

	private String folderChooser() {
		JFileChooser chooser = new JFileChooser();
		chooser.setCurrentDirectory(new java.io.File("."));
		chooser.setDialogTitle("Selecione um diretório");
		chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		int returnVal = chooser.showOpenDialog(null);

		if (returnVal == JFileChooser.APPROVE_OPTION) {
			return chooser.getSelectedFile().getPath().toString();
		}

		return null;
	}
}
