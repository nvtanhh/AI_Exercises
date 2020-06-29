package chapter2.agent_AB_v2;

import chapter2.agent_AB_v2.model.Agent;
import chapter2.agent_AB_v2.model.AgentProgram;
import chapter2.agent_AB_v2.model.Environment;
import chapter2.agent_AB_v2.model.Position;

public class TestSimpleReflexAgent_v2 {
	public static void main(String[] args) {
		Environment env = new Environment(5,10, 0.2, 0.3);
		Agent agent = new Agent(new AgentProgram());
		env.addAgent(agent, new Position(2, 2));

		env.stepUntilDone();
	}
}
