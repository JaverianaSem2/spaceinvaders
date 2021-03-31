package interfaz.decoratorbutton;

import javax.swing.*;
import java.awt.event.ActionListener;

public class ButtonDecorator extends JButton implements IButtonComponent {
	private final IButtonComponent iButtonComponent;

	public ButtonDecorator (IButtonComponent iButtonComponent) {
		this.iButtonComponent = iButtonComponent;
	}

	@Override public void setup ( ActionListener actionListener ) {
		iButtonComponent.setup( actionListener );
	}
}
