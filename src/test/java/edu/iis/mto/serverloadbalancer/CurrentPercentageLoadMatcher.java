package edu.iis.mto.serverloadbalancer;

import org.hamcrest.Description;
import org.hamcrest.TypeSafeMatcher;

public class CurrentPercentageLoadMatcher extends TypeSafeMatcher<Server> {

	private double expectedLoad;

	public CurrentPercentageLoadMatcher(double expectedLoad) {
		this.expectedLoad = expectedLoad;
	}

	public void describeTo(Description description) {
		description.appendText("server with current percentage load of").appendValue(expectedLoad);

	}

	@Override
	protected boolean matchesSafely(Server server) {
		return doubleAreEqual(server.currentPercentageLoad, expectedLoad);
	}

	private boolean doubleAreEqual(double d1, double d2) {
		return d2 == d1 || Math.abs(d1 - d2) / 100 < 0.01d;
	}
	public static CurrentPercentageLoadMatcher hasCurrentLoadOf(double expectedLoad) {
		return new CurrentPercentageLoadMatcher(expectedLoad);
	}

}
