package de.rares.visnet.api.math.calculator;


public class ParserManager {

	/** The deegre. */
	private boolean deegre = false;

	// ..... Other configuration values //


	private static ParserManager instance = null;


	protected ParserManager() {

	}

	public static ParserManager getInstance() {
		if (instance == null) {
			instance = new ParserManager();
		}
		return instance;
	}


	public boolean isDeegre() {
		return deegre;
	}

	public void setDeegre(final boolean deegre) {
		this.deegre = deegre;
	}

}
