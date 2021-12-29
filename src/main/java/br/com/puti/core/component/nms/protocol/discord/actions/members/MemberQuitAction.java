package br.com.puti.core.component.nms.protocol.discord.actions.members;

import net.dv8tion.jda.api.entities.Member;

public interface MemberQuitAction {
	public static final int serialID = 4;

	void action(Member user);
}
