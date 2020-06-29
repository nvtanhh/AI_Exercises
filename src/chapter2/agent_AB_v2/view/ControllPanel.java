package chapter2.agent_AB_v2.view;

import java.awt.GridLayout;
import java.awt.event.ActionListener;

import javax.swing.JPanel;

public class ControllPanel extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public ActionButton btnRun, btnPause, btnStep;

	public ControllPanel() {
		btnRun = new ActionButton("RUN");
		btnPause = new ActionButton("PAUSE");
		btnStep = new ActionButton("NEXT STEP");
		setLayout(new GridLayout(3, 1));
		add(btnRun);
		add(btnPause);
		add(btnStep);
	}

	public void addClickListenerStep(ActionListener clickListenerStep) {
		this.btnStep.addActionListener(clickListenerStep);
		
	}

	public void addClickListenerPause(ActionListener clickListenerPause) {
		btnPause.addActionListener(clickListenerPause);
	}

	public void addClickListenerRun(ActionListener clickListenerRun) {
		btnRun.addActionListener(clickListenerRun);
		
	}
}
