package br.com.puti.core.component.npc.api.utilities;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

import org.bukkit.OfflinePlayer;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.mojang.authlib.GameProfile;
import com.mojang.authlib.properties.Property;

public class SkinFetcher {

    public static String[] getFromName(String name) {
        try {
            URL url_0 = new URL("https://api.mojang.com/users/profiles/minecraft/" + name);
            InputStreamReader reader_0 = new InputStreamReader(url_0.openStream());
            String uuid = new JsonParser().parse(reader_0).getAsJsonObject().get("id").getAsString();
     
            URL url_1 = new URL("https://sessionserver.mojang.com/session/minecraft/profile/" + uuid + "?unsigned=false");
            InputStreamReader reader_1 = new InputStreamReader(url_1.openStream());
            JsonObject textureProperty = new JsonParser().parse(reader_1).getAsJsonObject().get("properties").getAsJsonArray().get(0).getAsJsonObject();
            String texture = textureProperty.get("value").getAsString();
            String signature = textureProperty.get("signature").getAsString();
     
            return new String[] {texture, signature};
        } catch (IOException e) {
            System.err.println("Could not get skin data from session servers!");
            e.printStackTrace();
            return null;
        }
    }
	
	public static String[] getSkinFromPlayer(OfflinePlayer player) {
		try {
			Property prop = ((GameProfile) player.getClass().getDeclaredMethod("getProfile").invoke(player)).getProperties().get("textures").iterator().next();
            
			String signature = prop.getSignature();
			String value = prop.getValue();

			return new String[] { value, signature };
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return null;
	}

	public static void main(String[] args) {
		String name = "TalonDev";

		try {
			BufferedReader reader = new BufferedReader(new InputStreamReader(
					new URL("https://api.mojang.com/users/profiles/minecraft/" + name).openStream()));
			String str = "";
			String line;
			while ((line = reader.readLine()) != null) {
				str += line;
			}

			String id = new JsonParser().parse(str).getAsJsonObject().get("id").getAsString();
			System.out.println("UUID >> " + id);

			reader.close();
			reader = new BufferedReader(new InputStreamReader(
					new URL("https://sessionserver.mojang.com/session/minecraft/profile/" + id + "?unsigned=false")
							.openStream()));
			str = "";
			line = null;
			while ((line = reader.readLine()) != null) {
				str += line;
			}
			String prop = "";
			JsonObject profile = new JsonParser().parse(str).getAsJsonObject();
			JsonObject object = profile.get("properties").getAsJsonArray().get(0).getAsJsonObject();
			prop = object.get("value").getAsString() + " : " + object.get("signature").getAsString();
			System.out.println("PROPERTIES:");
			System.out.println("Name>> \"textures\"");
			System.out.println("Value>> \"" + prop.split(" : ")[0] + "\"");
			System.out.println("Signature>> \"" + prop.split(" : ")[1] + "\"");
			System.out.println(" \nCodigo de setar skin:");
			System.out.println("npc.setSkin(\"" + prop.split(" : ")[0] + "\", \"" + prop.split(" : ")[1] + "\");");
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
}
