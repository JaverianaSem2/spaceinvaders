package interfaz;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import mundo.NaveJugador;

import static javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS;

public class DialogoSeleccionarJugador extends JDialog implements ListSelectionListener, ActionListener {

	private static final long serialVersionUID = 1L;
	private static final String ORDENAR = "Ordenar";
	private static final String ACEPTAR = "Aceptar";
	private static final String CANCELAR = "Cancelar";

	private InterfazSpaceInvaders interfaz;
	private JButton butOrdenar;

	@SuppressWarnings("rawtypes")
	private JList jugadores;

	private JScrollPane scroll;
	JButton butBotonAceptar;
	JButton butBotonCancelar;

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public DialogoSeleccionarJugador(InterfazSpaceInvaders interfaz) {

		super(interfaz, true);
		setLayout(new BorderLayout());

		this.interfaz = interfaz;
		scroll = new JScrollPane();
		scroll.setVerticalScrollBarPolicy( VERTICAL_SCROLLBAR_ALWAYS);
		scroll.setPreferredSize(new Dimension(240, 200));
		jugadores = new JList();
		jugadores.addListSelectionListener(this);
		jugadores.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		jugadores.setModel(new DefaultListModel());
		scroll.getViewport().add(jugadores);
		jugadores.setBackground(Color.BLACK);
		jugadores.setFont(FuenteInterfazGrafica.get(20));
		jugadores.setForeground(Color.BLUE);
		scroll.setBackground(Color.BLACK);
		add(scroll, BorderLayout.CENTER);

		butBotonAceptar = new JButton(ACEPTAR);
		butBotonAceptar.setActionCommand(ACEPTAR);
		butBotonAceptar.addActionListener(this);
		butBotonAceptar.setBounds(5, 2, 130, 25);
		butBotonAceptar.setBackground(Color.BLACK);
		butBotonAceptar.setFont(FuenteInterfazGrafica.get(20));
		butBotonAceptar.setForeground(Color.YELLOW);

		butBotonCancelar = new JButton(CANCELAR);
		butBotonCancelar.setActionCommand(CANCELAR);
		butBotonCancelar.addActionListener(this);
		butBotonCancelar.setBounds(140, 2, 130, 25);
		butBotonCancelar.setBackground(Color.BLACK);
		butBotonCancelar.setFont(FuenteInterfazGrafica.get(20));
		butBotonCancelar.setForeground(Color.green);

		butOrdenar = new JButton("ORDENAR");
		butOrdenar.addActionListener(this);
		butOrdenar.setActionCommand(ORDENAR);
		butOrdenar.setBounds(275, 2, 130, 25);
		butOrdenar.setBackground(Color.BLACK);
		butOrdenar.setFont(FuenteInterfazGrafica.get(20));
		butOrdenar.setForeground(Color.BLUE);

		this.setBackground(Color.BLACK);
		JPanel auxiliar = new JPanel();
		auxiliar.setLayout(null);
		auxiliar.setBackground(Color.BLACK);
		auxiliar.add(butBotonAceptar);
		auxiliar.add(butBotonCancelar);
		auxiliar.add(butOrdenar);
		auxiliar.setPreferredSize(new Dimension(240, 28));
		add(auxiliar, BorderLayout.SOUTH);

		setUndecorated(true);
		getRootPane().setBorder(BorderFactory.createLineBorder(Color.WHITE));
	}

	// -----------------------------------------------------------------
	// ----------------------Manejador de eventos-----------------------
	// -----------------------------------------------------------------

	@Override
	public void actionPerformed(ActionEvent e) {
		String comando = e.getActionCommand();

		switch ( comando ) {
			case CANCELAR:
				this.dispose();
				break;
			case ACEPTAR:
				if ( !darJugadorSeleccionado().equals( "" ) ) {
					interfaz.actualizarJugadorActual( darJugadorSeleccionado() );
					this.dispose();
				} else {
					JOptionPane.showMessageDialog( this,
						"Por favor cree un jugador",
						"No existen jugadores",
						JOptionPane.INFORMATION_MESSAGE
					);
					this.dispose();
				}
				break;
			case ORDENAR:
				interfaz.ordenarJugadores();
				break;
			default:
				break;
		}

	}

	@Override
	public void valueChanged(ListSelectionEvent arg0) {
		// Empty method for control behavior
	}

	// -----------------------------------------------------------------
	// -----------------------------Métodos-----------------------------
	// -----------------------------------------------------------------

	@SuppressWarnings("unchecked")
	public void cambiarListaJugadores( List<NaveJugador> lista) {

		jugadores.setListData(lista.toArray());

		if (jugadores.getModel().getSize() > 0) {
			jugadores.setSelectedIndex( 0 );
		}
	}

	public String darJugadorSeleccionado() {
		NaveJugador jugador = (NaveJugador) jugadores.getSelectedValue();
		return (jugador != null) ? jugador.getNickname() : "";
	}

	public void mostrar() {
		setSize(400, 400);
		setLocationRelativeTo(null);
		this.setVisible(true);
	}
}