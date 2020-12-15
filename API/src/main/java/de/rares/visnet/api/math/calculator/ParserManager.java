package de.rares.visnet.api.math.calculator;


public class ParserManager {


	private boolean deegre = false;



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
