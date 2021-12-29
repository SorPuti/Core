package br.com.puti.core.component.packet;

import java.io.Serializable;

/**
 * Represents a packed title. 
 */
@SuppressWarnings("serial")
public class PackedTitle implements Serializable {

	private String title = null;
	private String subtitle = null;
	private int fadein = -1;
	private int stay = -1;
	private int fadeout = -1;
	private TitleMode mode = TitleMode.NORMAL;
	
	/**
	 * Instantiates a new packed title.
	 * Use TitleMode.CLEAR to clear the title.
	 * Use TitleMode.RESET to clear the title and reset the timing.
	 *
	 * @throws IllegalArgumentException if mode == TitleMode.NORMAL
	 * @param mode mode
	 */
	public PackedTitle(TitleMode mode) {
		if(mode == TitleMode.NORMAL) throw new IllegalArgumentException("Titlemode can't be TitleMode.NORMAL here!");
		this.mode = mode;
	}
	
	/**
	 * Instantiates a new packed title.
	 *
	 * @param title title
	 * @param subtitle subtitle
	 */
	public PackedTitle(String title, String subtitle) {
		this.title = title;
		this.subtitle = subtitle;
	}
	
	/**
	 * Instantiates a new packed title.
	 *
	 * @param fadein fadeintime
	 * @param stay staytime
	 * @param fadeout fadeouttime
	 */
	public PackedTitle(int fadein, int stay, int fadeout) {
		this.fadein = fadein;
		this.stay = stay;
		this.fadeout = fadeout;
	}
	
	/**
	 * Instantiates a new packed title.
	 *
	 * @param title title
	 * @param subtitle subtitle
	 * @param fadein fadeintime
	 * @param stay staytime
	 * @param fadeout fadeouttime
	 */
	public PackedTitle(String title, String subtitle, int fadein, int stay, int fadeout) {
		this.title = title;
		this.subtitle = subtitle;
		this.fadein = fadein;
		this.stay = stay;
		this.fadeout = fadeout;
	}
	
	/**
	 * Gets the title.
	 *
	 * @return title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * Gets the subtitle.
	 *
	 * @return subtitle
	 */
	public String getSubtitle() {
		return subtitle;
	}

	/**
	 * Gets the fadein.
	 *
	 * @return fadeintime
	 */
	public int getFadein() {
		return fadein;
	}

	/**
	 * Gets the stay.
	 *
	 * @return staytime
	 */
	public int getStay() {
		return stay;
	}

	/**
	 * Gets the fadeout.
	 *
	 * @return fadeouttime
	 */
	public int getFadeout() {
		return fadeout;
	}

	/**
	 * Gets the mode.
	 *
	 * @return mode
	 */
	public TitleMode getMode() {
		return mode;
	}

}
