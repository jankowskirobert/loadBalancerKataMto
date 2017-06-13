package edu.iis.mto.serverloadbalancer;


import static edu.iis.mto.serverloadbalancer.ServerBuilder.server;
import static edu.iis.mto.serverloadbalancer.VmBuilder.vm;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

import org.hamcrest.Matcher;
import org.junit.Test;

public class ServerLoadBalancerTest {
	@Test
	public void itCompiles() {
		assertThat(true, equalTo(true));
	}

	@Test
	public void balancingServerWithNoVms_serversStayEmpty() {
		Server theServer = a(server().withCapacity(1));
		balancing(aServerListWith(theServer), anEmptyListOfVms());
		assertThat(theServer, hasCurrentLoad(0.0d));
	}

	@Test
	public void balanceServerWithOneSlotCapacity_andOneSlotVm_fillServerWithOneVm() {
		Server theServer = a(server().withCapacity(1));
		Vm theVm = a(vm().ofSize(1));
		balancing(aServerListWith(theServer), anEmptyListOfVms(theVm));
		assertThat(theServer, hasCurrentLoad(100.0d));
		assertThat("server should contain one vm", theServer.contain(theVm));
		
	}


	private CurrentLoadPercentageMatcher hasCurrentLoad(double currentLoad) {
		return new CurrentLoadPercentageMatcher(currentLoad);
	}

	private void balancing(Server[] servers, Vm[] vms) {
		new ServerLoadBalancer().balance(servers, vms);
	}

	private Vm[] anEmptyListOfVms(Vm... vms) {
		return vms;
	}

	private Server[] aServerListWith(Server... servers) {
		return servers;
	}

	private <T> T a(Builder<T> builder){
		return builder.build();
	}

}
