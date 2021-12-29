package br.com.puti.core.services.accounts;

import java.util.TreeMap;

import br.com.puti.core.component.database.mysql.DataBase;
import br.com.puti.core.component.model.CorePlayer;
import br.com.puti.core.component.database.query.QueryKey;
import br.com.puti.core.component.database.query.QueryResponse;
import br.com.puti.core.loader.CoreLoader;

public class AccountServices {

	protected CoreLoader loader;
	private TreeMap<String, CorePlayer> corePlayers;
	private DataBase dataBase = null;

	public AccountServices(CoreLoader loader) {
		if (loader.getAccountServices() == null)
			this.loader = loader;
		else
			throw new NullPointerException("Esta classe nao pode ser instanciada.(AccountServices.class ?:?)");

		corePlayers = new TreeMap<>();
	}

	public void init() {
		this.dataBase = loader.getDataBaseLoader().getDataBaseServices().get("database_core");

		dataBase.createTable( "economy_account", new QueryKey("coins", QueryResponse.DOUBLE, 0),
				new QueryKey("cash", QueryResponse.DOUBLE, 0));

		dataBase.createTable( "player_propriert", new QueryKey("mac_address", QueryResponse.STRING, 48),
				new QueryKey("google_authenticator", QueryResponse.INTEGER, null),
				new QueryKey("discord_authentication", QueryResponse.INTEGER, null),
				new QueryKey("accept_services", QueryResponse.BOOLEAN, 0),
				new QueryKey("user_id", QueryResponse.STRING, 24), new QueryKey("ip", QueryResponse.INTEGER, 16));

		dataBase.getAll().forEach(playerName -> {

			CorePlayer player = new CorePlayer(playerName.toString());

			corePlayers.put(playerName.toString(), player);
		});

		if (corePlayers.size() > 0)
			loader.getPluginLoader().log("&7" + corePlayers.size() + " "
					+ (corePlayers.size() > 1 ? "&fJogadores carregados" : "&fJogadore carregado."));
		else
			loader.getPluginLoader().log("&7Nenhum jogador carregado.");
	}

	public CorePlayer get(String arg0) {
		if (!(corePlayers.containsKey(arg0)))
			return null;

		return corePlayers.get(arg0);
	}

	public void add(CorePlayer corePlayer) {
		if (!corePlayers.containsKey(corePlayer.getName()))
			corePlayers.put(corePlayer.getName(), corePlayer);
	}

	public void remove(CorePlayer corePlayer) {
		if (corePlayers.containsKey(corePlayer.getName()))
			corePlayers.put(corePlayer.getName(), corePlayer);
	}

	public DataBase getDataBase() {
		return this.dataBase;
	}
	
	public int size() {
		return corePlayers.size();
	}

}
