package br.com.puti.core.component.nms.protocol.discord;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import br.com.puti.core.Core;
import br.com.puti.core.component.database.mysql.DataBase;
import br.com.puti.core.component.database.clotted.DataClotted;
import br.com.puti.core.component.nms.protocol.discord.actions.bot.BotEvents;
import br.com.puti.core.component.nms.protocol.discord.actions.bot.Initialize;
import br.com.puti.core.component.nms.protocol.discord.actions.chat.MessageAction;
import br.com.puti.core.component.nms.protocol.discord.actions.chat.ReactAction;
import br.com.puti.core.component.nms.protocol.discord.actions.chat.ReactAction.Type;
import br.com.puti.core.component.nms.protocol.discord.actions.commands.Executor;
import br.com.puti.core.component.nms.protocol.discord.dataExpression.BotEntity;
import br.com.puti.core.component.nms.protocol.discord.dataExpression.eMessage;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.MessageBuilder;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.*;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.events.message.react.MessageReactionAddEvent;
import net.dv8tion.jda.api.events.message.react.MessageReactionRemoveEvent;

@SuppressWarnings("all")
public class Bot extends BotEntity<Bot> implements BotEvents {

    protected Initialize initialize;

    private String prefix = null;
    protected HashMap<String, eMessage> aks = new HashMap<String, eMessage>();
    protected Map<Integer, Object> actions = new HashMap<Integer, Object>();

    private DataBase dataBase;
    private String id;

    public Bot(String id, String token) {
        this.id = id;
        this.dataBase = Core.getPlugin(Core.class).get().getAccountServices().getDataBase();
        this.setStartup(token, this);
        Core.getPlugin(Core.class).get().getDiscordServices().add(this);
    }

    public Bot(String id, String token, Activity activity) {
        this.id = id;
        this.dataBase = Core.getPlugin(Core.class).get().getAccountServices().getDataBase();
        this.setStartup(token, this, activity);
        Core.getPlugin(Core.class).get().getDiscordServices().add(this);
    }

    public DataBase getDataBase() {
        return dataBase;
    }

    public HashMap<String, eMessage> getAks() {
        return aks;
    }

    public Map<Integer, Object> getActions() {
        return actions;
    }

    public String getId() {
        return id;
    }

    @Override
    public Bot getClazz() {
        return super.getClazz();
    }

    @Override
    public Guild getGuild() {
        return super.getGuild();
    }

    @Override
    public JDA getJda() {
        return super.getJda();
    }

    @Override
    public void setPrefix(String arg0) {
        this.prefix = arg0;
    }

    @Override
    public String getPrefix() {
        return this.prefix;
    }

    private String getFirstWord(String text) {

        int index = text.indexOf(' ');

        if (index > -1) {

            return text.substring(0, index).trim();

        } else {

            return text;
        }
    }

    private <E> E callEvent(int valueID) {
        if (!this.actions.containsKey(valueID))
            return null;

        Object object = this.actions.get(valueID);
        try {
            Field f = object.getClass().getFields()[0];
            int objectID = f.getInt(object);

            if (valueID == objectID)
                return (E) object;
        } catch (Exception e) {
        }

        return null;
    }

    @Override
    public void disconnect() {
        try {
            Core.getPlugin(Core.class).get().getDiscordServices().remove(this);
            this.getJda().setAutoReconnect(false);
            this.getJda().shutdownNow();

        } finally {

        }
    }

    @Override
    public void start() {
        this.setStartup(getToken(), this);
    }

    @Override
    public Emote getEmoji(String name) {
        return getGuild().getEmotesByName(name, true).get(0);
    }

    @Override
    public void onInitialize() {
        if (this.initialize != null)
            this.initialize.action();
    }

    @Override
    public boolean hasRole(User user, String id) {
        Member member = getGuild().getMember(user);
        return hasRole(member, id);
    }

    @Override
    public boolean hasRole(Member member, String id) {
        List<Object> values = new ArrayList<Object>();

        member.getRoles().forEach(role -> {
            if (role.getId().equals(id))
                values.add(0);
        });

        return values.size() > 0;
    }

    @Override
    public boolean hasPermissions(User user, Permission permissions) {
        Member member = getGuild().getMember(user);
        return hasPermissions(member, permissions);
    }

    @Override
    public boolean hasPermissions(Member member, Permission permissions) {
        return member.hasPermission(permissions);
    }

    @Override
    public void onInitialize(Initialize action) {
        this.initialize = action;
    }

    @Override
    public eMessage sendMessage(TextChannel channel, MessageEmbed embedBuilder) {
        Message message = null;

        synchronized (this) {
            message = channel.sendMessage(embedBuilder).complete();
        }
        return new eMessage(this).query(message);
    }

    @Override
    public eMessage sendMessage(TextChannel channel, String text) {
        Message message = null;
        synchronized (this) {
            message = channel.sendMessage(text).complete();
        }
        return new eMessage(this).query(message);
    }

    @Override
    public eMessage sendPrivateMessage(User user, MessageEmbed embedBuilder) {
        PrivateChannel channel = user.openPrivateChannel().complete();
        Message message = null;

        synchronized (this) {
            message = channel.sendMessage(embedBuilder).complete();
        }

        return new eMessage(this).query(message);
    }

    @Override
    public eMessage sendPrivateMessage(User user, String text) {
        PrivateChannel channel = user.openPrivateChannel().complete();
        MessageBuilder builder = new MessageBuilder();
        builder.setContent(text);
        Message message = null;

        synchronized (this) {
            message = channel.sendMessage(builder.build()).complete();
        }

        return new eMessage(this).query(message);
    }

    @Override
    public Member getMemberCached(String arg0, boolean force) {
        if (force)
            for (Member member : users.values())
                if (member.getId().equals(arg0))
                    return member;

        List<String> ids = new ArrayList<String>();
        Member member = null;
        DataClotted cache = dataBase.createDataClotted(arg0);

        cache.setAccessibility((db, collun, value) -> {
            ids.add(value.toString());
        }).search("user_id");

        if (ids.size() > 0)
            if (this.users.containsKey(ids.get(0)))
                member = this.users.get(ids.get(0));
            else
                member = null;

        return member;
    }

    @Override
    public eMessage complete(String id) {
        return this.aks.get(id);
    }

    @Override
    public eMessage complete(Message message) {
        return this.complete(message.getId());
    }

    @Override
    public eMessage complete() {
        if (aks.size() == 0)
            return null;

        return this.aks.values().toArray(new eMessage[]{})[0];
    }

    @Override
    public eMessage getEMessage(String messageid) {
        return aks.get(messageid);
    }

    @Override
    public eMessage getEMessage(Message message) {
        return getEMessage(message.getId());
    }

    @Override
    public TextChannel getGuildChannelbyName(String arg0) {
        TextChannel channel = this.getJda().getTextChannelsByName(arg0, true).get(0);

        return channel;
    }

    @Override
    public TextChannel getGuildChannelbyId(String arg0) {
        TextChannel channel = this.getJda().getTextChannelById(arg0);
        return channel;
    }

    @Override
    public void onReactAdd(MessageReactionAddEvent event) {
        eMessage eMessage = getEMessage(event.getMessageId());

        if (eMessage == null)
            return;

        if (eMessage.getAction() != null) {
            try {

                eMessage.getAction().action(event.getMember(), event.getReactionEmote(), eMessage.getMessage(),
                        Type.ADD);
                ReactAction callEvent = callEvent(ReactAction.serialID);
                if (callEvent != null)
                    callEvent.action(event.getMember(), event.getReactionEmote(), eMessage.getMessage(), Type.ADD);
            } catch (Exception e) {
                System.out.println("ERROR! ao avisa o bot " + id);
                e.printStackTrace();
            }
        }
    }

    @Override
    public void onReactRemove(MessageReactionRemoveEvent event) {
        eMessage eMessage = getEMessage(event.getMessageId());

        if (eMessage == null)
            return;

        if (eMessage.getAction() != null) {
            try {

                eMessage.getAction().action(event.getMember(), event.getReactionEmote(), eMessage.getMessage(),
                        Type.REMOVE);
                ReactAction callEvent = callEvent(ReactAction.serialID);
                if (callEvent != null)
                    callEvent.action(event.getMember(), event.getReactionEmote(), eMessage.getMessage(), Type.REMOVE);
            } catch (Exception e) {
                System.out.println("ERROR! ao avisa o bot " + id);
                e.printStackTrace();
            }
        }
    }

    @Override
    public void onMessageReceived(MessageReceivedEvent event) {
        Message clazz = event.getMessage();
        String message = clazz.getContentRaw();
        String command = getFirstWord(message);
        if (prefix != null && command.startsWith(prefix)) {
            command = command.replace(prefix, "").trim();
            String[] args = new String[]{};
            if (message.length() > command.length()) {
                String result = message.substring(command.length() + 1, message.length()).trim();
                if (!result.isEmpty())
                    args = result.indexOf(" ") > 0 ? result.split(" ") : new String[]{result.trim()};
            }

            for (Executor executor : cmds)
                if (executor.has(command)) {
                    executor.onCommand(event.getMember(), clazz, args, event);
                    clazz.delete().queue();
                }
        }

        MessageAction callEvent = callEvent(MessageAction.serialID);
        if (callEvent != null)
            callEvent.action(event.getMessage(), event.getAuthor(), ChannelType.GROUP);

    }

  /*
    @Override
    public void onGuildMemberJoin(GuildMemberJoinEvent event) {
        MemberJoinAction callEvent = callEvent(MemberJoinAction.serialID);
        if (callEvent != null)
            callEvent.action(event.getMember());
    }

    @Override
    public void onGuildMemberQuit(GuildMemberLeaveEvent event) {
        MemberQuitAction callEvent = callEvent(MemberQuitAction.serialID);
        if (callEvent != null)
            callEvent.action(event.getMember());
    }
*/
    @Override
    public <K, V> V on(BotAction<K> action) {
        try {
            K object = action.getBotAction();
            Field f = object.getClass().getFields()[0];
            int objectID = f.getInt(object);

            this.actions.put(objectID, action.getBotAction());

        } finally {
            return (V) action;
        }
    }

}
