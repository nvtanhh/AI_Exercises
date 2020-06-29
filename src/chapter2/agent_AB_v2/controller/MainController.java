package chapter2.agent_AB_v2.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import chapter2.agent_AB_v2.model.Agent;
import chapter2.agent_AB_v2.model.AgentProgram;
import chapter2.agent_AB_v2.model.Environment.LocationState;
import chapter2.agent_AB_v2.model.EnvironmentState;
import chapter2.agent_AB_v2.model.GameData;
import chapter2.agent_AB_v2.model.Position;
import chapter2.agent_AB_v2.view.MainView;

public class MainController {
	public static GameData model;
	public static MainView view;
	public boolean running;

	private Thread runningAgent = new Thread(new Runnable() {

		@Override
		public void run() {
			while (true) {
				System.out.print("");
				if (running) {
					try {
						EnvironmentState envState = model.step();
						int score = model.getCore();
						view.update(envState, score);
						Thread.sleep(300);
						if (model.isDone()) {
							running = false;
							view.showError("ALL IS CLEAN");
							break;
						}
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}

			}

		}
	});

	public MainController() {
		model = new GameData();
		view = new MainView();

		runningAgent.start();
		view.addClickListenerEnv(new ClickListenerEnv());
		view.addClickListenerAgent(new ClickListenerAgent());
		view.addClickListenerStep(new ClickListenerStep());
		view.addClickListenerRun(new ClickListenerRun());
		view.addClickListenerPause(new ClickListenerPause());
	}

// Inner class 
	class ClickListenerEnv implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			try {
				int m = view.inputPanel.getM();
				int n = view.inputPanel.getN();
				double d = view.inputPanel.getDirtRate();
				double w = view.inputPanel.getWallRate();
				LocationState[][] locationState = model.generateEnv(m, n, d, w);

				view.generateFloor(locationState);
			} catch (NumberFormatException nfex) {
				view.showError("Bad input: ");
			}
		}
	}// end inner class

	// Inner class
	class ClickListenerAgent implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			try {
				int x = view.inputPanel.getAgent_X();
				int y = view.inputPanel.getAgent_Y();
				Agent agent = new Agent(new AgentProgram());
				Position agentLocation = new Position(x, y);

				model.addAgent(agent, agentLocation);
				view.generateAgent(agentLocation);
			} catch (NumberFormatException nfex) {
				view.showError("Bad input: ");
			}
		}
	}// end inner class

	// Inner class
	class ClickListenerStep implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			try {
				EnvironmentState envState = model.step();
				int score = model.getCore();
				view.update(envState, score);
			} catch (Exception x) {
				view.showError(x.toString());
			}
		}
	}// end inner class

	// Inner class
	class ClickListenerRun implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if (!running)
				running = true;
		}

	}// end inner class

	class ClickListenerPause implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			try {
				if (running)
					running = false;
			} catch (Exception x) {
				view.showError(x.toString());
			}
		}

	}

	public static void main(String[] args) {
		new MainController();

	}
}
