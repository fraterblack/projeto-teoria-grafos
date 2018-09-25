package view;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import model.Configuration;



public class BuscaView extends JFrame {
	
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
	
	/* FINALIZACAO */
	private JButton btnSalvar;
	private JButton btnProcessar;
	
	public void setConfigView() {
		setSize(1000,650);
		setTitle("Busca Menor Caminho");
		setLayout(null);
        setResizable(false);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
	}
	
	public BuscaView(Configuration config) {
		setConfigView();
		
		createComponents();
		
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
    	btnBuscar.setText("Buscar:");
    	getContentPane().add(btnBuscar);
    	
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
    	btnAdiciona.setBounds(875, 150, 50, 30);
    	btnAdiciona.setText("+");
    	getContentPane().add(btnAdiciona);
    	
    	/* FINALIZACAO */
    	
    	btnSalvar = new JButton();
    	btnSalvar.setBounds(600, 550, 150, 30);
    	btnSalvar.setText("SALVAR");
    	getContentPane().add(btnSalvar);
    	
    	btnProcessar = new JButton();
    	btnProcessar.setBounds(775,550, 150, 30);
    	btnProcessar.setText("PROCESSAR");
    	getContentPane().add(btnProcessar);
		
	}
	
}