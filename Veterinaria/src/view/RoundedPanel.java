package view;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

public class RoundedPanel extends JPanel {
	private static final long serialVersionUID = 1L;
    private int size;
    private Color color;
	
    public RoundedPanel(int size, Color color) {
    	super();
    	this.size = size;
    	this.color = color;
        setOpaque(false); 
    }
    protected void paintComponent(Graphics g) {
    	super.paintComponent(g);
    	Graphics2D g2 = (Graphics2D) g;
        g2.setStroke(new BasicStroke(2));
        g2.setColor(getBackground());
        g2.fillRoundRect(0, 0, getWidth(), getHeight(), size, size);
        g2.setColor(color);
        g2.drawRoundRect(3, 3, getWidth()-6, getHeight()-6, size, size);
    }
}