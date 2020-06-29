package chapter2.agent_AB_v2.model;

public class Percept {
	private Position agentLocation;
	private Environment.LocationState state;

	public Percept(Position agentLocation, Environment.LocationState state) {
		this.agentLocation = agentLocation;
		this.state = state;
	}

	public Environment.LocationState getLocationState() {
		return this.state;
	}

	public Position getAgentLocation() {
		return this.agentLocation;
	}
}