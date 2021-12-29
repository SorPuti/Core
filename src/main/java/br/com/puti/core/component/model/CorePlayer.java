package br.com.puti.core.component.model;

import java.util.UUID;

import br.com.puti.core.events.AccountCreatedEvent;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

import br.com.puti.core.Core;
import br.com.puti.core.loader.CoreLoader;
import br.com.puti.core.services.accounts.AccountServices;
import br.com.puti.core.services.message.MessageServices;

public class CorePlayer extends AbstractPlayer<CorePlayer> {

	protected double coins = 0d;
	protected double cash = 0d;
	protected String currentServer = "None";

	public CorePlayer(OfflinePlayer player) {
		super(player.getName(), 3);
		super.Super(this);
		this.init();
	}

	public CorePlayer(String playerName) {
		super(playerName, 2);
		super.Super(this);
		this.init();
	}

	public CorePlayer(Player player) {
		super(player.getName(), 1);
		super.Super(this);
		this.init();
	}

	private void init() {
		if (!this.dataBase.getSelectTable().equals("economy_account"))
			this.dataBase.select("economy_account");

		if (!this.dataBase.contains(getName())) {
			AccountCreatedEvent event = new AccountCreatedEvent(this);
			Bukkit.getPluginManager().callEvent(event);
			if (event.isCancelled())
				return;

			this.dataBase.create(getName(), (response, database, data) -> {

			});
		}
		Object[] values = this.dataBase.multGet(getName(), "cash", "coins");
		try {
			this.cash = Double.parseDouble(values[0].toString());
			this.coins = Double.parseDouble(values[1].toString());
		} finally {
		}

	}

	@SuppressWarnings("deprecation")
	public Player getPlayer() {
		return Bukkit.getPlayerExact(getName());
	}

	public UUID getUUID() {
		Player player = getPlayer();
		if (player == null)
			return UUID.fromString("");

		return player.getUniqueId();
	}

	public String getCurrentServer() {
		try {
			Player player = getPlayer();
			MessageServices service = Core.getPlugin(Core.class).get().getMessageServices();

			service.send("ServerOfPlayer", player, player.getName(), (in, author) -> {
				currentServer = in.readUTF();

				author.close("ServerOfPlayer");
			});

			return currentServer;
		} catch (Exception e) {
			return getCurrentServer();
		}
	}

	public void setCash(Double value) {
		if (!this.dataBase.getSelectTable().equals("economy_account"))
			this.dataBase.select("economy_account");

		this.cash = value;
		this.dataBase.setValue(getName(), "cash", value, (a, b, c) -> {
			if (!a.isProcessed())
				a.print();
		});
	}

	public void setCoins(Double value) {
		if (!this.dataBase.getSelectTable().equals("economy_account"))
			this.dataBase.select("economy_account");

		this.coins = value;
		this.dataBase.setValue(getName(), "coins", value, (a, b, c) -> {
			if (!a.isProcessed())
				a.print();
		});
	}

	public Double getCoins() {
		return this.coins;
	}

	public Double getCash() {
		return this.cash;
	}

	public static CorePlayer get(String arg0) {
		CoreLoader loader = Core.getPlugin(Core.class).get();
		AccountServices _$cc0unt = loader.getAccountServices();

		return _$cc0unt.get(arg0);
	}

}
