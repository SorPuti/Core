package br.com.puti.core.component.nms.protocol.discord.dataExpression;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.security.auth.login.LoginException;

import br.com.puti.core.component.nms.protocol.discord.BotAction;
import br.com.puti.core.component.nms.protocol.discord.events.ChatListerners;
import org.bukkit.Bukkit;

import br.com.puti.core.Core;
import br.com.puti.core.component.nms.protocol.discord.actions.bot.Initialize;
import br.com.puti.core.component.nms.protocol.discord.actions.commands.Executor;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.entities.Emote;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.GenericEvent;
import net.dv8tion.jda.api.events.ReadyEvent;
import net.dv8tion.jda.api.hooks.EventListener;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.managers.GuildManager;
import net.dv8tion.jda.api.utils.Compression;

@SuppressWarnings("all")
public abstract class BotEntity<E> implements EventListener {

	protected HashMap<String, Member> users = new HashMap<String, Member>();
	private Guild guild = null;
	private JDA jda;
	private GuildManager manager;
	private String token;
	private E clazz;
	protected List<Executor> cmds = new ArrayList<Executor>();

	public void setStartup(String token, E paramsE) {
		this.clazz = paramsE;
		this.token = token;
		try {
			this.configure(Activity.playing(""));
		} catch (LoginException e) {
			sendConsole("&c&lERRRO!&c Falha ao iniciar o bot " + this.token);
		}
	}

	public void setStartup(String token, E paramsE, Activity activity) {
		this.clazz = paramsE;
		this.token = token;
		try {
			this.configure(activity);
		} catch (LoginException e) {
			sendConsole("&c&lERRRO!&c Falha ao iniciar o bot " + this.token);
		}
	}
	
	public String getToken() {
		return token;
	}
	
	public Guild getGuild() {
		return guild;
	}
	
	
	public JDA getJda() {
		return jda;
	}
	

	public List<Executor> getCmds() {
		return cmds;
	}
	public abstract void setPrefix(String arg0);

	public abstract String getPrefix();

	@Override
	public void onEvent(GenericEvent event) {
		if (!(event instanceof ReadyEvent))
			return;

		this.guild = event.getJDA().getGuildById("844378515612762122");
		this.manager = guild.getManager();
		this.update();
		this.onInitialize();
	}

	public abstract void onInitialize();

	public abstract void onInitialize(Initialize action);

	public abstract Emote getEmoji(String name);

	public abstract void disconnect();

	public abstract void start();

	public abstract TextChannel getGuildChannelbyName(String arg0);

	public abstract TextChannel getGuildChannelbyId(String arg0);

	public abstract boolean hasRole(User user, String roleId);

	public abstract boolean hasRole(Member user, String roleId);

	public abstract boolean hasPermissions(User user, Permission permissions);

	public abstract boolean hasPermissions(Member user, Permission permissions);

	public abstract <K, V> V on(BotAction<K> action);

	public abstract Member getMemberCached(String arg0, boolean force);

	public abstract eMessage getEMessage(String messageid);

	public abstract eMessage getEMessage(Message messageid);

	public abstract eMessage sendMessage(TextChannel channel, MessageEmbed embedBuilder);

	public abstract eMessage sendMessage(TextChannel channel, String message);

	public abstract eMessage sendPrivateMessage(User user, MessageEmbed embedBuilder);

	public abstract eMessage sendPrivateMessage(User user, String text);

	public abstract eMessage complete(String id);

	public abstract eMessage complete();

	public abstract eMessage complete(Message message);

	public E getClazz() {
		return clazz;
	}

	public final E setActivity(Activity activity) {
		this.jda.getPresence().setActivity(activity);
		return getClazz();
	}

	private final E configure(Activity activity) throws LoginException {

		JDABuilder builder = JDABuilder.createDefault(token).setBulkDeleteSplittingEnabled(false)
				.setCompression(Compression.NONE).setActivity(activity)
				.addEventListeners(this, new ChatListerners(Core.getPlugin(Core.class).get()));
		this.jda = builder.build();

		return getClazz();
	}

	public final E sendConsole(String... message) {
		for (String line : message)
			Bukkit.getConsoleSender().sendMessage(line.replace("&", "�"));

		return getClazz();
	}

	public final E invisibility(boolean arg0) {
		if (arg0)
			jda.getPresence().setStatus(OnlineStatus.INVISIBLE);
		else
			jda.getPresence().setStatus(OnlineStatus.DO_NOT_DISTURB);

		return getClazz();
	}

	public void update() {
		this.users = new HashMap<String, Member>();

		for (Member member : guild.getMembers())
			users.put(member.getId(), member);
	}

	public void registerEvent(ListenerAdapter... listeners) {
		this.jda.addEventListener(listeners);
	}

	public void registerCommand(Executor... executors) {
		for (Executor ex : executors)
			cmds.add(ex);
	}

}
