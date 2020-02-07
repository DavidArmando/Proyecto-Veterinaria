package view;

import java.awt.Graphics;

import javax.swing.JButton;

public class RoundedButton extends JButton{
	private static final long serialVersionUID = 1L;
    private int size;
	
    public RoundedButton(int size) {
    	super();
    	this.size = size;
        setOpaque(false); 
    }
    protected void paintComponent(Graphics g) {
        g.setColor(getBackground());
        g.fillRoundRect(0, 0, getWidth(), getHeight(), size, size);
        super.paintComponent(g);
    }
}