package br.com.puti.core.loader;

import br.com.puti.core.component.npc.NPCListeners;
import br.com.puti.core.component.nms.protocol.autosave.AutoSaveProvider;
import org.bukkit.Bukkit;
import org.bukkit.Server;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.plugin.PluginManager;

import br.com.puti.core.Core;
import br.com.puti.core.executables.CoreCommnad;
import br.com.puti.core.listeners.PlayerListeners;
import br.com.puti.core.services.accounts.AccountServices;
import br.com.puti.core.services.discord.DiscordServices;
import br.com.puti.core.services.message.MessageServices;
import br.com.puti.core.services.message.MessageServicesEvent;

public class CoreLoader {

    private Core plugin;
    private DataBaseLoader dataBaseLoader = null;
    private MessageServices messageServices = null;
    private AccountServices accountServices = null;
    private DiscordServices services = null;
    private AutoSaveProvider autoSaveProvider = null;

    public CoreLoader(Core plugin) {
        if (plugin.get() == null)
            this.plugin = plugin;
        else
            throw new NullPointerException("Esta classe nao pode ser instanciada.(CoreLoader.class ?:?)");

    }

    public void onCoreLoad() {

        this.dataBaseLoader = new DataBaseLoader(this);
        this.dataBaseLoader.loader();

        this.services = new DiscordServices(this);
        this.messageServices = new MessageServices(this);
        getServer().getMessenger().registerOutgoingPluginChannel(plugin, "Core");
        getServer().getMessenger().registerIncomingPluginChannel(plugin, "Core",
                new MessageServicesEvent(messageServices));

        getServer().getMessenger().registerOutgoingPluginChannel(plugin, "BungeeCord");
        getServer().getMessenger().registerIncomingPluginChannel(plugin, "BungeeCord",
                new MessageServicesEvent(messageServices));

        this.accountServices = new AccountServices(this);
        this.autoSaveProvider = new AutoSaveProvider(this);


        plugin.log("", "&7Serviços carregados.", "",
                "&fBanco de dados registrados:&7 " + this.getDataBaseLoader().getDataBaseServices().size(),
                "&fServiço de mensagens:&7 " + (this.messageServices == null ? "&cDesativado" : "&aAtivado"), "",
                "&7Depedências: [Hikari, Spigot1.8x ou Bukkit 1.8x]", "");

    }

    public void onCoreEnable() {
        this.setup();

        plugin.log("&fPlugin carregado.");

    }

    public void onCoreDisable() {
        this.autoSaveProvider.saveAll();
        this.getDiscordServices().unregisterAll();
        this.getDataBaseLoader().getDataBaseServices().unregisterAll();

        plugin.log("&fPlugin descarregado.");
    }

    public void send(String... messages) {
        ConsoleCommandSender sender = Bukkit.getConsoleSender();
        for (String message : messages)
            sender.sendMessage("§e[Core] §f"+message.replace("&", "§"));
    }

    public Core getPluginLoader() {
        return this.plugin;
    }

    public Server getServer() {
        return plugin.getServer();
    }

    public DataBaseLoader getDataBaseLoader() {
        return this.dataBaseLoader;
    }

    public DiscordServices getDiscordServices() {
        return services;
    }

    public MessageServices getMessageServices() {
        return this.messageServices;
    }

    public AccountServices getAccountServices() {
        return this.accountServices;
    }

    public AutoSaveProvider getAutoSaveProvider() {
        return autoSaveProvider;
    }

    private void setup() {
        PluginManager pm = Bukkit.getPluginManager();

        pm.registerEvents(new PlayerListeners(), plugin);
        pm.registerEvents(new NPCListeners(), plugin);

        plugin.getCommand("core").setExecutor(new CoreCommnad(this));

        this.accountServices.init();
        this.autoSaveProvider.loadAll();
    }

}
