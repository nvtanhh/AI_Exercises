package chapter2.agent_AB_v2.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import chapter2.agent_AB_v2.model.Environment.LocationState;
import chapter2.agent_AB_v2.model.EnvironmentState;
import chapter2.agent_AB_v2.model.Position;

public class MainView extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public InputPanel inputPanel;
	public ControllPanel controlPanel;
	public Floor floorErea;
	public InforPanel inforPanel;

	public MainView() {

		setLayout(new BorderLayout());

		createInputPannel();
		createControlPannel();
		createFloor();
		createInforPanel();

		add(inputPanel, BorderLayout.WEST);
		add(controlPanel, BorderLayout.EAST);
		add(floorErea, BorderLayout.CENTER);
		add(inforPanel, BorderLayout.SOUTH);

		setTitle("Cleaning Agent");
		setSize(800, 500);
		setResizable(false); // window size cannot change
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setVisible(true);

	}

	private void createInforPanel() {
		inforPanel = new InforPanel();
	}

	private void createFloor() {
		floorErea = new Floor();

	}

	private void createControlPannel() {
		controlPanel = new ControllPanel();
		controlPanel.setPreferredSize(new Dimension(150, 500));
	}

	private void createInputPannel() {
		inputPanel = new InputPanel();
		inputPanel.setLayout(new GridLayout(2, 1));
		inputPanel.setPreferredSize(new Dimension(150, 500));
	}

	public void showError(String errMessage) {
		JOptionPane.showMessageDialog(this, errMessage);
	}

	public void generateFloor(LocationState[][] locationState) {
		floorErea.drawFloor(locationState);
	}

	public void addClickListenerEnv(ActionListener clickListenerEnv) {
		inputPanel.addClickListenerEnv(clickListenerEnv);
	}

	public void generateAgent(Position agentLocation) {
		floorErea.generateAgent(agentLocation);
	}

	public void addClickListenerAgent(ActionListener clickListenerAgent) {
		inputPanel.addClickListenerAgent(clickListenerAgent);
	}

	public void update(EnvironmentState envState, int score) {
		this.floorErea.updateView(envState);
		inforPanel.setScore(score);
	}

	public void addClickListenerStep(ActionListener clickListenerStep) {
		this.controlPanel.addClickListenerStep(clickListenerStep);
	}

	public void addClickListenerPause(ActionListener clickListenerPause) {
		controlPanel.addClickListenerPause(clickListenerPause);
	}

	public void addClickListenerRun(ActionListener clickListenerRun) {
		controlPanel.addClickListenerRun(clickListenerRun);
	}

}
