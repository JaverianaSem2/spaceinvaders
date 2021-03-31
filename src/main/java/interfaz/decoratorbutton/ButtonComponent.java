package interfaz.decoratorbutton;

import javax.swing.*;
import java.awt.event.ActionListener;

public class ButtonComponent extends JButton implements IButtonComponent {
	public ButtonComponent( ActionListener actionListener, String label ) {
		super(label);
		super.addActionListener( actionListener );
	}

	@Override public void setup (ActionListener actionListener) {
		// Empty method : SonarQube
	}
}
