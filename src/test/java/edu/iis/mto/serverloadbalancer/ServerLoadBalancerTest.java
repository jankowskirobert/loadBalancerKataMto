package edu.iis.mto.serverloadbalancer;


import static edu.iis.mto.serverloadbalancer.CurrentPercentageLoadMatcher.hasCurrentLoadOf;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

import org.hamcrest.Matcher;
import org.hamcrest.MatcherAssert;
import org.junit.Test;

public class ServerLoadBalancerTest {
	@Test
	public void itCompiles() {
		assertThat(true, equalTo(true));
	}

	@Test
	public void balanceServerWithNoVMs() {
		Server theServer = a(server().withCapacity(1));
		balance(serverListWith(theServer), emptyListOfVMs());
		MatcherAssert.assertThat("server has no vms", theServer, hasCurrentLoadOf(0.0d));
	}

	private Server a(ServerBuilder builder) {
		return builder.build();
	}


	private void balance(Server[] servers, Vm[] vms) {
		new ServerLoadBalancer().balance(servers, vms);
	}

	private Vm[] emptyListOfVMs() {
		return new Vm[]{};
	}

	private Server[] serverListWith(Server... servers) {
		return servers;
	}

	private ServerBuilder server() {
		return new ServerBuilder();
	}

}
