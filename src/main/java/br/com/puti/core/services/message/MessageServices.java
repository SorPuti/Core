package br.com.puti.core.services.message;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import com.google.common.io.ByteArrayDataInput;
import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;

import br.com.puti.core.Core;
import br.com.puti.core.component.bungeecord.BungeeChannel;
import br.com.puti.core.component.action.callbacks.AkSpigot;
import br.com.puti.core.component.action.callbacks.CallBack;
import br.com.puti.core.loader.CoreLoader;

public class MessageServices extends BungeeChannel {

    protected CoreLoader loader;
    public TreeMap<String, AkSpigot<ByteArrayDataInput>> aks = new TreeMap<String, AkSpigot<ByteArrayDataInput>>();

    public MessageServices(CoreLoader loader) {
        super(loader);
        if (loader.getMessageServices() == null)
            this.loader = loader;
        else
            throw new NullPointerException("Esta classe nao pode ser instanciada.(MessageServices.class ?:?)");

    }

    public void close(String channel) {
        if (aks.containsKey(channel))
            aks.remove(channel);
    }

    public void closeAll() {
        if (aks.size() > 0)
            aks = new TreeMap<String, AkSpigot<ByteArrayDataInput>>();
    }

    public CallBack send(String channel, Player player, String... data) {
        ByteArrayOutputStream array = new ByteArrayOutputStream();
        ByteArrayDataOutput out = ByteStreams.newDataOutput(array);
        out.writeUTF(channel);
        for (String line : data)
            out.writeUTF(line);

        player.sendPluginMessage(Core.getPlugin(Core.class), "Core", out.toByteArray());
        CallBack callBack = new CallBack();
        aks.put(channel, callBack);
        return callBack;
    }

    public void send(String channel, Player player, String data, AkSpigot<ByteArrayDataInput> callBack) {
        ByteArrayOutputStream array = new ByteArrayOutputStream();
        ByteArrayDataOutput out = ByteStreams.newDataOutput(array);
        out.writeUTF(channel);
        out.writeUTF(data);

        player.sendPluginMessage(Core.getPlugin(Core.class), "Core", out.toByteArray());
        aks.put(channel, callBack);
    }

    public String send(String channel, String... data) {
        ByteArrayOutputStream array = new ByteArrayOutputStream();
        ByteArrayDataOutput out = ByteStreams.newDataOutput(array);
        out.writeUTF(channel);
        for (String line : data)
            out.writeUTF(line);

        Bukkit.getServer().sendPluginMessage(Core.getPlugin(Core.class), "Core", out.toByteArray());
        CallBack callBack = new CallBack();

        aks.put(channel, callBack);

        return callBack.onCallBack((in, author) -> {
            author.close(channel);
        }).getData();
    }

    public String send(String channel, Player player, String data) {
        ByteArrayOutputStream array = new ByteArrayOutputStream();
        ByteArrayDataOutput out = ByteStreams.newDataOutput(array);
        out.writeUTF(channel);
        out.writeUTF(data);

        player.sendPluginMessage(Core.getPlugin(Core.class), "Core", out.toByteArray());
        CallBack callBack = new CallBack();
        aks.put(channel, callBack);
        List<String> datas = new ArrayList<>();
        synchronized (this) {
            callBack.onCallBack((in, author) -> {
                try {
                    datas.add(in.readUTF());
                } finally {
                    author.close(channel);
                }
            }).getData();
        }


        return datas.size() > 0 ? datas.get(0) : "";
    }

    public void send(String channel, String data, AkSpigot<ByteArrayDataInput> callBack) {
        ByteArrayOutputStream array = new ByteArrayOutputStream();
        ByteArrayDataOutput out = ByteStreams.newDataOutput(array);
        out.writeUTF(channel);
        out.writeUTF(data);

        Bukkit.getServer().sendPluginMessage(Core.getPlugin(Core.class), "Core", out.toByteArray());
        aks.put(channel, callBack);
    }

    public void send(String playerName, String message) {
        ByteArrayOutputStream array = new ByteArrayOutputStream();
        ByteArrayDataOutput out = ByteStreams.newDataOutput(array);
        out.writeUTF("Message");
        out.writeUTF(playerName);
        out.writeUTF(message.replace("§", "&"));

        Bukkit.getServer().sendPluginMessage(Core.getPlugin(Core.class), "Core", out.toByteArray());
    }

    public void send(Player player, String message) {
        ByteArrayOutputStream array = new ByteArrayOutputStream();
        ByteArrayDataOutput out = ByteStreams.newDataOutput(array);
        out.writeUTF("Message");
        out.writeUTF(player.getName());
        out.writeUTF(message.replace("§", "&"));

        player.sendPluginMessage(Core.getPlugin(Core.class), "Core", out.toByteArray());
    }

}
