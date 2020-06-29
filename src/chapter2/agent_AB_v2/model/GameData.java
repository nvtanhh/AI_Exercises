package chapter2.agent_AB_v2.model;

import chapter2.agent_AB_v2.model.Environment.LocationState;

public class GameData {
	private Environment env;

	public LocationState[][] generateEnv(int m, int n, double d, double w) {
		env = new Environment(m, n, d, w);
		return env.getCurrentState().getFloor();
	}

	public void addAgent(Agent agent, Position position) {
		env.addAgent(agent, position);
	}

	public EnvironmentState step() {
		env.step();
		return env.getCurrentState();
	}

	public boolean isDone() {
		return env.isDone();
	}

	public int getCore() {
		return env.getScore();
	}

}
