package com.grafos.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import com.grafos.lib.DatabaseManager;
import com.grafos.model.Configuration;

public class ConfiguracaoView extends JFrame {
	
	private JLabel lblPasta;
	private JTextField txfPasta;
	private JLabel lblSucessso;
	private JTextField txfSucesso;
	private JLabel lblErro;
	private JTextField txfErro;
	private JCheckBox ckbRotaAutomatica;
	private JButton btnSalvar;
	
	public ConfiguracaoView() {
		setSize(300,200);
		setTitle("Cofiguração");
		setLayout(null);
		setResizable(false);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
		
		CriarComponentes();
	}
	
	private void CriarComponentes() {
		//Pasta
		lblPasta =  new JLabel("Pasta:");
		lblPasta.setBounds(10, 10, 50, 25);
		getContentPane().add(lblPasta);
		
		txfPasta = new JTextField();
		txfPasta.setBounds(80, 10, 210,25);
		getContentPane().add(txfPasta);
		
		//Sucesso
		lblSucessso =  new JLabel("Sucesso:");
		lblSucessso.setBounds(10, 40, 80, 25);
		getContentPane().add(lblSucessso);
		
		txfSucesso = new JTextField();
		txfSucesso.setBounds(80, 40, 100,25);
		getContentPane().add(txfSucesso);
		
		//Erro
		lblErro =  new JLabel("Erro:");
		lblErro.setBounds(11, 70, 50, 25);
		getContentPane().add(lblErro);
		
		txfErro = new JTextField();
		txfErro.setBounds(80, 70, 100,25);
		getContentPane().add(txfErro);
		
        //Rota Automatica
		ckbRotaAutomatica = new JCheckBox("Rota automática");
		ckbRotaAutomatica.setSelected(false);
		ckbRotaAutomatica.setBounds(80, 100, 150, 25);
		getContentPane().add(ckbRotaAutomatica);
		
		//Salvar
		btnSalvar = new JButton("Salvar");
		btnSalvar.setBounds(79, 130, 130, 25);
		getContentPane().add(btnSalvar);
		
		btnSalvar.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				try {
					if(txfConfigVerify()) {
						DatabaseManager dm = new DatabaseManager();
						dm.createConfig(new Configuration(txfPasta.getText(),txfSucesso.getText(),txfErro.getText(),ckbRotaAutomatica.isSelected()));
						
						setVisible(false);
					}
					else {
						throw new Exception("Tentou inserir com campos sem valores.");
					}
				} catch(Exception e1) {
					System.out.println(e1.getMessage());
				}			
			}

				
        });
		
	}
	
	private boolean txfConfigVerify() {
		
		if(txfPasta.getText().length() < 1) {
			//JOptionPane.showMessageDialog(null, "Pasta principal não informada.");
			return false;
		}
		else if(txfSucesso.getText().length() < 1) {
			//JOptionPane.showMessageDialog(null, "Pasta sucesso não informada.");
			return false;
		}
		else if(txfErro.getText().length() < 1) {
			//JOptionPane.showMessageDialog(null, "Pasta erro não informada.");
			return false;
		}
		
		return true;
	}
	
}
