package br.edu.dombosco.dbcode.accessManagment.adapter;

import lombok.Getter;
import lombok.Setter;

import java.awt.Color;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import javax.swing.JPasswordField;
import javax.swing.text.JTextComponent;


public class GenericFocusAdapter implements FocusListener {
    private final Color focusColor = Color.BLACK;
    private final Color defaultColor = new Color(153, 153, 153);

    @Override
    public void focusGained(FocusEvent e) {
        JTextComponent component = (JTextComponent) e.getComponent();
        String defaultText = (String) component.getClientProperty("defaultText");

        if (component.getText().equals(defaultText)) {
            component.setText("");
            component.setForeground(focusColor);
            if (component instanceof JPasswordField) {
                ((JPasswordField) component).setEchoChar('•');
            }
        }
    }

    @Override
    public void focusLost(FocusEvent e) {

        JTextComponent component = (JTextComponent) e.getComponent();
        String defaultText = (String) component.getClientProperty("defaultText");

        if (component.getText().isEmpty()) {
            component.setForeground(defaultColor);
            component.setText(defaultText);
            if (component instanceof JPasswordField) {
                ((JPasswordField) component).setEchoChar((char) 0);
            }
        }
    }
}
