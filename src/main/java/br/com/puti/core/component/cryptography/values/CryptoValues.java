package br.com.puti.core.component.cryptography.values;

public enum CryptoValues {

	A("0", "a"), B("1", "b"), C("2", "c"), D("3", "d"), E("4", "e"), F("5", "f"), G("6", "g"), H("7", "h"), I("8", "i"),
	J("9", "j"), K("10", "k"), L("A11", "l"), M("B12", "m"), N("C13", "n"), O("O25", "o"), P("D14", "p"), Q("E15", "q"),
	R("F16", "r"), S("G17", "s"), T("H18", "t"), U("I19", "u"), V("J20", "v"), W("K21", "w"), X("L22", "x"),
	Y("M23", "y"), Z("N24", "z"), SPACE("n", " "), NONE("P26", "�"), COMMA("Q27", ","), POINT("I28", "."),
	DOUBLE_POINT("I29", ":"), DOUBLE_POINT_2("I30", ";"), INDENT("J31", "-"), BAR("J32", "_"), ASPA_1("H33", "\""),
	ASPA("W34", "'"), DOUBLE_ASPA("W35", "\""), PARENTv1("X36", "}"), PARENTv2("X37", "{"), PARENTv1_2("Z37", "]"),
	PARENTv2_2("Z38", "["), ONE("N1", "1"), ZERO("N2", "0"), TWO("N3", "2"), THREE("N4", "3"), FOUR("N5", "4"),
	FIVE("N6", "5"), SIX("N7", "6"), SEVEN("N8", "7"), EIGHT("N9", "8"), NINE("N10", "9"), TEN("N11", "10"),

	ACENT("O12", "á"), ACENT_1("O13", "é"), ACENT_2("O14", "ú"), ACENT_4("O15", "í"), ACENT_5("O16", "ó"),
	ACENT_6("O17", "à"), ACENT_7("O18", "è"), ACENT_8("O19", "ù"), ACENT_9("O20", "ì"), ACENT_10("O21", "Ò"),
	ACENT_11("O22", "â"), ACENT_12("O23", "ô"), ACENT_13("O12", "û"), ACENT_14("O13", "î"), ACENT_15("O14", "ê"),
	ACENT_16("O15", ""), ACENT_17("O16", ""),

	EX("P17", "!"), _VERTICAL("J18", "|"), THRO("K19", "/"), THRO_2("K19I", "\\"), EX_2("K20", "?"),
	SIMBOLY("L21", "&"), SIMBOLY_2("L23", ""), SIMBOLY_3("L24", "#"), SIMBOLY_4("L25", "*"), SIMBOLY_5("L26", ""),
	SIMBOLY_6("L27", "%"), SIMBOLY_7("L28", "~"), SIMBOLY_8("L29", "^"), SIMBOLY_9("L30", ""), SIMBOLY_10("L31", "@"),
	SIMBOLY_11("L32", "$"), SIMBOLY_12("L33", "\\n");

	private String serialID;
	public String value = null;

	CryptoValues(String serialID, String value) {
		this.serialID = serialID;
		this.value = value;
	}

	public static String getValue(String id) {
		for (CryptoValues value : CryptoValues.values()) {
			if (value.serialID.equalsIgnoreCase(id))
				return value.value;
		}

		return "";
	}

	public String getSerialID() {
		return serialID;
	}

	public String getValue() {
		return value;
	}

	public static String getSerial(String id) {
		for (CryptoValues value : CryptoValues.values()) {
			id = id.length() == 1 && id.equals("\n") ? "\\n" : id;

			if (value.value.equalsIgnoreCase(id))
				return value.serialID;
		}
		return "";
	}

}
