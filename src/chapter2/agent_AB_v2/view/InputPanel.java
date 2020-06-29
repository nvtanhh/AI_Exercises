package chapter2.agent_AB_v2.view;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class InputPanel extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public JPanel envIn4, agentIn4;
	private JTextField m, n, dirtRate, wallRate, agent_X, agent_Y;
	private JButton generateEnvBtn, generateAgentBtn;

	public InputPanel() {
		createEnvIn4Panel();
		createAgntIn4Panel();
		add(envIn4);
		add(agentIn4);
	}

	private void createAgntIn4Panel() {
		agentIn4 = new JPanel();
		agentIn4.setBorder(BorderFactory.createTitledBorder("Agents informations"));
		agentIn4.setLayout(new BorderLayout());
		JPanel agentForm = new JPanel();
		agentForm.setLayout(new GridLayout(2, 2));
		agentForm.add(new JLabel("Agent_X"));
		agent_X = new JTextField();
		agentForm.add(agent_X);
		agentForm.add(new JLabel("Agent_Y"));
		agent_Y = new JTextField();
		agentForm.add(agent_Y);

		agentIn4.add(agentForm, BorderLayout.CENTER);
		generateAgentBtn = new JButton("Generate Agent");
		agentIn4.add(generateAgentBtn, BorderLayout.SOUTH);

	}

	private void createEnvIn4Panel() {
		envIn4 = new JPanel();
		envIn4.setBorder(BorderFactory.createTitledBorder("Floor informations"));
		envIn4.setLayout(new BorderLayout());
		JPanel envForm = new JPanel();
		envForm.setLayout(new GridLayout(4, 2));
		envForm.add(new JLabel("m"));
		m = new JTextField();
		envForm.add(m);
		envForm.add(new JLabel("n"));
		n = new JTextField();
		envForm.add(n);
		envForm.add(new JLabel("dirt_rate"));
		dirtRate = new JTextField();
		envForm.add(dirtRate);
		envForm.add(new JLabel("wall_rate"));
		wallRate = new JTextField();
		envForm.add(wallRate);

		envIn4.add(envForm, BorderLayout.CENTER);
		generateEnvBtn = new JButton("Generate Environment");
		envIn4.add(generateEnvBtn, BorderLayout.SOUTH);

	}

	public int getM() {
		return Integer.parseInt(m.getText());
	}

	public int getN() {
		return Integer.parseInt(n.getText());
	}

	public double getDirtRate() {
		return Double.parseDouble(dirtRate.getText());
	}

	public double getWallRate() {
		return Double.parseDouble(wallRate.getText());
	}

	public int getAgent_X() {
		return  Integer.parseInt(agent_X.getText());
	}

	public int getAgent_Y() {
		return  Integer.parseInt(agent_Y.getText());
	}

	public void addClickListenerEnv(ActionListener clickListenerEnv) {
		this.generateEnvBtn.addActionListener(clickListenerEnv);
	}

	public void addClickListenerAgent(ActionListener clickListenerAgent) {
		this.generateAgentBtn.addActionListener(clickListenerAgent);
	}

}
