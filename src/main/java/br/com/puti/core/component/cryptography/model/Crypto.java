package br.com.puti.core.component.cryptography.model;

import java.util.ArrayList;
import java.util.List;

import br.com.puti.core.component.cryptography.utils.StringUtils;
import br.com.puti.core.component.cryptography.values.CryptoValues;

public class Crypto {

	private String value;
	private List<CryptoKey> keys = new ArrayList<CryptoKey>();

	public Crypto(String value) {
		this.value = value;
		this.init();
	}

	private void init() {
		if (value == null)
			return;

		if (!(value.endsWith(":")))
			return;

		try {
			for (String v$l$ue : value.split(":"))
				try {
					boolean UperCase = v$l$ue.endsWith("#");
					if (UperCase)
						v$l$ue = v$l$ue.replaceAll("#", "");

					keys.add(new CryptoKey(v$l$ue, UperCase));
				} catch (Exception e) {
				}

			this.value = decrypt(false);
		} finally {
		}
	}

	public String encrypt() {
		for (int i = 0; i < value.length(); i++) {
			String a = ((Object) value.charAt(i)).toString();
			String v$l$ue = CryptoValues.getSerial(a);
			keys.add(new CryptoKey(v$l$ue, Character.isUpperCase(value.charAt(i))));
		}
		return toString();
	}

	public String decrypt(boolean format) {
		String result = "";
		for (CryptoKey key : keys) {
			result = result.concat(key.newInstance());
		}
		return (format ? StringUtils.capitalize(result, " ") : result);
	}

	@Override
	public String toString() {
		String serial = "";
		for (CryptoKey key : keys)
			serial = serial.concat(key.getSerial()) + ":";

		return serial;
	}

}

class CryptoKey {

	private final String serialId;

	private boolean isUper = false;

	public CryptoKey(String serialId, boolean arg0) {
		this.serialId = serialId;
		this.isUper = arg0;
	}

	public String getSerial() {
		return this.serialId + (isUper ? "#" : "");
	}

	public boolean isSpace() {
		try {
			String c = CryptoValues.getValue(serialId);
			if (c.isEmpty() || c == "")
				return true;
			else
				return false;
		} catch (Exception e) {
			return false;
		}
	}

	public String newInstance() {
		String str = CryptoValues.getValue(serialId);
		return (isUper ? str.toUpperCase() : str.toLowerCase());
	}

}
