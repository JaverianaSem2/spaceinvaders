package interfaz;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class DialogoCrearPartida extends JDialog implements ActionListener {

	private static final long serialVersionUID = 1L;
	public static final  String ACEPTAR = "Aceptar";
	public static final String CANCELAR = "Cancelar";

	InterfazSpaceInvaders interfaz;

	JPanel auxiliar;

	JLabel nombre;
	JLabel nombre1;

	JTextField txtNombre;

	JButton butBotonAceptar;
	JButton butBotonCancelar;

	// -----------------------------------------------------------------
	// ---------------------------Constructor---------------------------
	// -----------------------------------------------------------------

	public DialogoCrearPartida(InterfazSpaceInvaders interfaz) {

		super(interfaz, false);

		this.interfaz = interfaz;
		setLayout(null);

		auxiliar = new JPanel();
		auxiliar.setLayout(null);

		nombre = new JLabel("Ingrese el nombre    de");
		nombre.setForeground(Color.YELLOW);
		nombre.setFont(FuenteInterfazGrafica.get(33));
		nombre.setBounds(10, 30, 350, 20);

		nombre1 = new JLabel("la    partida");
		nombre1.setForeground(Color.YELLOW);
		nombre1.setFont(FuenteInterfazGrafica.get(33));
		nombre1.setBounds(10, 55, 240, 20);

		txtNombre = new JTextField();
		txtNombre.setBackground(Color.orange);
		txtNombre.setBounds(10, 150, 210, 25);
		txtNombre.setForeground(Color.BLUE);
		txtNombre.setFont(FuenteInterfazGrafica.get(25));

		JLabel imagen = new JLabel();
		ImageIcon icono = new ImageIcon("./src/main/resources/data/imagenes/fondoAP.jpg");
		imagen.setIcon(icono);
		imagen.setBounds(0, 0, icono.getIconWidth(), icono.getIconHeight());

		butBotonAceptar = new JButton(ACEPTAR);
		butBotonAceptar.setActionCommand(ACEPTAR);
		butBotonAceptar.addActionListener(this);
		butBotonAceptar.setBounds(10, 200, 130, 25);
		butBotonAceptar.setBackground(Color.BLACK);
		butBotonAceptar.setFont(FuenteInterfazGrafica.get(20));
		butBotonAceptar.setForeground(Color.YELLOW);

		butBotonCancelar = new JButton(CANCELAR);
		butBotonCancelar.setActionCommand(CANCELAR);
		butBotonCancelar.addActionListener(this);
		butBotonCancelar.setBounds(200, 200, 130, 25);
		butBotonCancelar.setBackground(Color.BLACK);
		butBotonCancelar.setFont(FuenteInterfazGrafica.get(20));
		butBotonCancelar.setForeground(Color.green);

		auxiliar.setSize(icono.getIconWidth(), icono.getIconHeight());
		auxiliar.add(nombre);
		auxiliar.add(nombre1);
		auxiliar.add(txtNombre);
		auxiliar.add(butBotonAceptar);
		auxiliar.add(butBotonCancelar);
		auxiliar.add(imagen);

		setTitle("Crear Partida");
		setUndecorated(true);
		getRootPane().setBorder(BorderFactory.createLineBorder(Color.WHITE));
	}

	// -----------------------------------------------------------------
	// ----------------------Manejador de eventos-----------------------
	// -----------------------------------------------------------------
	@Override
	public void actionPerformed(ActionEvent e) {

		String comando = e.getActionCommand();
		if (comando.equals(CANCELAR))
			this.dispose();
		else if (comando.equals(ACEPTAR)) {
			if ( txtNombre.getText() == null || txtNombre.getText().equals(""))
				JOptionPane.showMessageDialog(this, "Por favor ingrese un nombre válido", "Error al crear el jugador",
						JOptionPane.ERROR_MESSAGE);
			else {
				interfaz.reqCrearPartida(txtNombre.getText());
				this.dispose();
			}
		}

	}

	// -----------------------------------------------------------------
	// -----------------------------Métodos-----------------------------
	// -----------------------------------------------------------------

	public void mostrar() {
		setSize(400, 225);
		add(auxiliar);
		setLocationRelativeTo(null);
		this.setVisible(true);
	}

	public String darNombre() {
		return txtNombre.getText();
	}
}
