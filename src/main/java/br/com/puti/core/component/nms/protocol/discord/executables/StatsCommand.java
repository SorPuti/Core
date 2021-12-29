package br.com.puti.core.component.nms.protocol.discord.executables;

import java.awt.Color;

import br.com.puti.core.component.nms.protocol.discord.Bot;
import br.com.puti.core.component.nms.protocol.discord.actions.commands.Executor;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Emote;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

public class StatsCommand extends Executor {

	private final Bot bot;

	public StatsCommand(Bot bot) {
		super("stats");
		this.bot = bot;
	}

	public MessageEmbed TemplatePermission() {
		EmbedBuilder not_permission = new EmbedBuilder();
		not_permission.setTitle("Sem Permisssão!");
		not_permission.setDescription("Você não possui permissão.");
		not_permission.setColor(Color.red);
		not_permission.setFooter("Abstract Corporation");

		return not_permission.build();
	}

	private MessageEmbed TemplateInfo() {
		EmbedBuilder inf = new EmbedBuilder();
		Emote emote = bot.getEmoji("Anotao");
		String tmp = "";
		
		tmp = tmp.replace(emote.getAsMention(), ":" + emote.getName() + ":");

		inf.setTitle(emote.getAsMention() + " Bot info");
		inf.setDescription("O Bot "+bot.getId()+" está sendo excutado na vers�o 1.0.16 � esta ocupado o prefixo '**"+bot.getPrefix()+"**'.");
		inf.setColor(Color.green);
		inf.setFooter("Abstract Corporation");
		return inf.build();
	}

	@Override
	public void onCommand(Member author, Message message,String[] args, MessageReceivedEvent event) {
		if (!(bot.hasPermissions(author, Permission.MANAGE_ROLES))) {
			bot.sendMessage(message.getTextChannel(), TemplatePermission()).close();
			return;
		}

		bot.sendMessage(message.getTextChannel(), TemplateInfo()).close();
	}

}
