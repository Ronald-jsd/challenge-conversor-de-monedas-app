package principalGui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;

import consultaApi.ConsultaMoneda;
import generadorDeArchivo.GeneradorDeArchivo;
import modelRecord.Moneda;

import java.awt.Font;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.awt.event.ActionEvent;
import java.awt.SystemColor;
import java.awt.Cursor;
import java.awt.Desktop;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

import javax.swing.JSeparator;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import javax.swing.border.MatteBorder;


public class PrincipalSwing implements ActionListener {

	private JFrame frame;
	private JTextField txtCantidad;
	private JLabel lblConversion;
	private JButton btnCalcular;
	private JComboBox cboTipoConversion;
	private JLabel lblResultado;
	private JButton btnSalir;
	private JButton btnResumen;
	private JTextField txtTasaConversion;


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PrincipalSwing window = new PrincipalSwing();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public PrincipalSwing() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setAutoRequestFocus(false);
		frame.setBounds(100, 100, 821, 485);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setUndecorated(true);
		frame.getContentPane().setLayout(null);
		frame.setLocationRelativeTo(null);

		lblConversion = new JLabel("Escoja una conversión *");
		lblConversion.setBounds(73, 117, 258, 14);
		frame.getContentPane().add(lblConversion);

		txtCantidad = new JTextField();
		txtCantidad.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		txtCantidad.setToolTipText("");
		txtCantidad.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtCantidad.setBounds(73, 218, 258, 35);
		frame.getContentPane().add(txtCantidad);
		txtCantidad.setColumns(10);

		cboTipoConversion = new JComboBox();
		cboTipoConversion.setAutoscrolls(true);
		cboTipoConversion.setModel(new DefaultComboBoxModel(
				new String[] { "Dolar a Peso Argentino", "Peso Argentino a Dolar", "Dolar a Real Brasileño",
						"Real Brasileño a Dolar", "Dolar a Peso Colombiano", "Peso Colombiano a Dolar" }));
		cboTipoConversion.setBackground(SystemColor.text);
		cboTipoConversion.setBounds(73, 144, 258, 28);
		frame.getContentPane().add(cboTipoConversion);

		JLabel lblCantidad = new JLabel("Digite la cantidad *");
		lblCantidad.setBounds(73, 193, 248, 14);
		frame.getContentPane().add(lblCantidad);

		btnCalcular = new JButton("Calcular");
		btnCalcular.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnCalcular.setBackground(Color.BLACK);
		btnCalcular.setForeground(Color.WHITE);
		btnCalcular.addActionListener(this);

		btnCalcular.setBounds(73, 278, 258, 35);
		frame.getContentPane().add(btnCalcular);

		JPanel panel = new JPanel();
		panel.setBackground(new Color(255, 255, 255));
		panel.setBounds(420, 0, 401, 485);
		frame.getContentPane().add(panel);
		panel.setLayout(null);

		JLabel lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setIcon(new ImageIcon(PrincipalSwing.class.getResource("/img/3.png")));
		lblNewLabel_2.setBounds(0, 81, 401, 343);
		panel.add(lblNewLabel_2);
		
		txtTasaConversion = new JTextField();
		txtTasaConversion.setBounds(259, 454, 132, 20);
		panel.add(txtTasaConversion);
		txtTasaConversion.setBackground(new Color(255, 255, 255));
		txtTasaConversion.setEditable(false);
		txtTasaConversion.setBorder(new MatteBorder(0, 0, 1, 0, (Color) new Color(0, 0, 0)));
		txtTasaConversion.setColumns(10);
		
		JLabel lblTasaConversion = new JLabel("Tasa de Cambio");
		lblTasaConversion.setBounds(117, 456, 132, 14);
		panel.add(lblTasaConversion);
		lblTasaConversion.setHorizontalAlignment(SwingConstants.RIGHT);

		lblResultado = new JLabel("");
		lblResultado.setHorizontalAlignment(SwingConstants.LEFT);
		lblResultado.setFont(new Font("Tahoma", Font.BOLD, 17));
		lblResultado.setBounds(83, 349, 248, 35);
		frame.getContentPane().add(lblResultado);

		btnResumen = new JButton("Resúmen");
		btnResumen.addActionListener(this);
		btnResumen.setIcon(new ImageIcon(PrincipalSwing.class.getResource("/img/papel.png")));
		btnResumen.setBounds(204, 435, 129, 35);
		frame.getContentPane().add(btnResumen);

		btnSalir = new JButton("Salir");
		btnSalir.setIcon(new ImageIcon(PrincipalSwing.class.getResource("/img/btnCerrar.png")));
		btnSalir.addActionListener(this);
		btnSalir.setBounds(73, 435, 93, 35);
		frame.getContentPane().add(btnSalir);

		JSeparator separator = new JSeparator();
		separator.setBounds(73, 377, 258, 15);
		frame.getContentPane().add(separator);
		
				JLabel lblTitulo = new JLabel("Conversor de Moneda");
				lblTitulo.setBounds(57, 43, 324, 42);
				frame.getContentPane().add(lblTitulo);
				lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
				lblTitulo.setFont(new Font("Tahoma", Font.BOLD, 23));
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnResumen) {
			btnResumenActionPerformed(e);
		}
		if (e.getSource() == btnSalir) {
			btnSalirActionPerformed(e);
		}
		if (e.getSource() == btnCalcular) {
			btnCalcularActionPerformed(e);
		}

	}
	
	protected void btnCalcularActionPerformed(ActionEvent arg) {
		try {
			int opcion = cboTipoConversion.getSelectedIndex();
			double valor = Double.parseDouble(txtCantidad.getText());

			String simbolo = "";
			double resultado = 0;
			double tasaConversion=0;
			
			switch (opcion) {
			case 0:
				tasaConversion = obtenerValor("USD", "ARS");
				resultado = valor * tasaConversion;
				simbolo = "$";
				break;
			case 1:
				tasaConversion = obtenerValor("ARS", "USD");
				resultado = valor * tasaConversion;
				simbolo = "$";
				break;
			case 2:
				tasaConversion =  obtenerValor("USD", "BRL");
				resultado = valor * tasaConversion;
				simbolo = "R$";
				break;
			case 3:
				tasaConversion = obtenerValor("BRL", "USD");
				resultado = valor * tasaConversion;
				simbolo = "$";
				break;
			case 4:
				tasaConversion = obtenerValor("USD", "COP");
				resultado = valor * tasaConversion;
				simbolo = "$ COP";
				break;
				
			case 5:
				tasaConversion = obtenerValor("COP", "USD");
				resultado = valor * tasaConversion;
				simbolo = "$";
				break;
			}

			GeneradorDeArchivo.guardarJson(cboTipoConversion.getSelectedItem().toString(), resultado);
			lblResultado.setText(String.format("%.2f %s", resultado, simbolo));
			txtTasaConversion.setText(String.format( "%.5f %s",tasaConversion, simbolo ));
			System.out.println(tasaConversion);
			focusATxt();

		} catch (Exception e) {
			System.out.println("Opcion invalida");
			System.out.println("Error: " + e.getMessage());
			JOptionPane.showMessageDialog(null, "¡Escriba un número válido por favor!");
			focusATxt();
		} finally {
			System.out.println("Finalizado");
		}
	}

	//Metodo para consultas el valor de conversión de una moneda base a otra
	public static double obtenerValor(String baseMoneda, String destino) {
		// Consultar las tasas de conversión desde la API
		ConsultaMoneda consulta = new ConsultaMoneda();
		Moneda moneda = consulta.buscaMoneda(baseMoneda);
		//double resultado2 = resultado;
		
		// verificamos conversion
		if (moneda.conversion_rates() != null && !moneda.conversion_rates().isEmpty()) {
			// Obtener el valor de conversión hacia la moneda destino
			// Ejemplo: USD a COP o COP a USD
			double tasaConversion = moneda.conversion_rates().get(destino.toUpperCase());
			return tasaConversion;
		} else {
			System.out.println("No se encontraron tasas de conversión.");
		}
		return 0;
	}


    protected static void btnResumenActionPerformed(ActionEvent e) {
        String rutaProyecto = System.getProperty("user.dir");
        File archivoJson = new File(rutaProyecto + "/data/datos.json");

        if (!archivoJson.exists()) {
            System.out.println("El archivo datos.json no existe aún.");
            return;
        }

        try {
            Desktop.getDesktop().open(archivoJson);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
	
	protected void btnSalirActionPerformed(ActionEvent e) {
		frame.dispose();
	}

	void focusATxt() {
		txtCantidad.setText("");
		txtCantidad.requestFocus();
	}
}
