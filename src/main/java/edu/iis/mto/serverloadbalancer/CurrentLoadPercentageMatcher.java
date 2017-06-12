package edu.iis.mto.serverloadbalancer;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;

public class CurrentLoadPercentageMatcher extends TypeSafeMatcher<Server> {

	private double expectedPercentageLoad;

	public CurrentLoadPercentageMatcher(double expectedPercentageLoad) {
		this.expectedPercentageLoad = expectedPercentageLoad;
	}

	public void describeTo(Description description) {
		description.appendText("a server with load percentage of ").appendValue(expectedPercentageLoad);
	}

	@Override
	protected boolean matchesSafely(Server server) {
		// TODO Auto-generated method stub
		return expectedPercentageLoad == server.currentLoadPercentage
				|| Math.abs(expectedPercentageLoad - server.currentLoadPercentage) < 0.01d;
	}

}
