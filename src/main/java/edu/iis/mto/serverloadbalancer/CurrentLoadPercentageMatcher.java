package edu.iis.mto.serverloadbalancer;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;

public class CurrentLoadPercentageMatcher extends TypeSafeMatcher<Server> {

	private static final double EPSILON = 0.01d;
	private double expectedPercentageLoad;

	public CurrentLoadPercentageMatcher(double expectedPercentageLoad) {
		this.expectedPercentageLoad = expectedPercentageLoad;
	}

	public void describeTo(Description description) {
		description.appendText("a server with load percentage of ").appendValue(expectedPercentageLoad);
	}

	@Override
	protected void describeMismatchSafely(Server item, Description description) {
		// TODO Auto-generated method stub
		description.appendText("a server with load percentage of ").appendValue(item.currentLoadPercentage);
	}
	
	@Override
	protected boolean matchesSafely(Server server) {
		return doubleAreEqual(expectedPercentageLoad, server.currentLoadPercentage);
	}

	private boolean doubleAreEqual(double d1, double d2) {
		return d1 == d2 || Math.abs(d1 - d2) < EPSILON;
	}

	public static CurrentLoadPercentageMatcher hasCurrentLoadOf(double expectedPercentageLoad) {
		return new CurrentLoadPercentageMatcher(expectedPercentageLoad);
	}
}
