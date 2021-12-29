package br.com.puti.core;

import br.com.puti.core.component.config.FileManager;
import br.com.puti.core.loader.CoreLoader;
import br.com.puti.core.task.CoreTask;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.text.NumberFormat;

public class Core extends JavaPlugin {

    protected CoreLoader coreLoader;
    private CoreTask coreTask;

    private FileManager file;

    @Override
    public void onLoad() {
        log("", " §7NMS version: 1.8.8-R0.1-SNAPSHOT", " &7Autor: &fSrPuti_", "", " &fCarregando....", "");
        this.coreLoader = new CoreLoader(this);

        coreLoader.onCoreLoad();
    }


    @Override
    public void onEnable() {
        this.coreTask = (new CoreTask(coreLoader));
        this.coreTask.runTaskTimerAsynchronously(this, 0, 20l * 60 * 1);
        coreLoader.onCoreEnable();
    }

    @Override
    public void onDisable() {
        log("", " &7NMS version: 1.8.8-R0.1-SNAPSHOT", " &7Autor: &fSrPuti_", "", " &cDescarregando....", "");
        coreLoader.onCoreDisable();
        if (this.coreTask != null)
            this.coreTask.cancel();

    }

    public void log(String... messages) {
        for (String line : messages)
            Bukkit.getConsoleSender().sendMessage("§e[Core] §f" + line.replace("&", "§"));
    }

    public void info(String... messages) {
        for (String line : messages)
            getLogger().info(line);
    }

    public CoreLoader get() {
        return this.coreLoader;
    }

    public static FileManager getMainFile() {
        return Core.getPlugin(Core.class).get().getDataBaseLoader().geFile();
    }

    public static String format(Double valor) {
        NumberFormat formatter = NumberFormat.getCurrencyInstance();
        String str = formatter.format(valor);
        return str.replace(".", ",").replace("R$ ", "");
    }

    public static CoreLoader getManager(){
        return getPlugin(Core.class).get();
    }

}
