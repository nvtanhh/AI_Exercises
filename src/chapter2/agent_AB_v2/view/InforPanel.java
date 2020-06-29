package chapter2.agent_AB_v2.view;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class InforPanel extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JLabel scoreJl;
	public InforPanel() {
		scoreJl = new JLabel("Score: 0");
		add(scoreJl);
	}
	
	public void setScore(int score) {
		scoreJl.setText("Score: " + score);
	}
}
