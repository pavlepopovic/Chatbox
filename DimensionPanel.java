package chatbox;

import java.awt.Dimension;

import javax.swing.JPanel;

public class DimensionPanel extends JPanel {
	public DimensionPanel (int width, int height) {
		Dimension dimensions = new Dimension(width, height);
		setPreferredSize(dimensions);
	}
}
