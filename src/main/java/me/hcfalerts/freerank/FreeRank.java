package me.hcfalerts.freerank;

import me.hcfalerts.freerank.commands.FreeRankCommand;
import me.hcfalerts.freerank.utils.CC;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;

public final class FreeRank extends JavaPlugin {
    private static FreeRank instance;
    public String ConfigRoute;
    public static FreeRank getInstance() {return instance;}

    public void log(String s) {
        Bukkit.getConsoleSender().sendMessage(CC.translate(s));
    }

    @Override
    public void onEnable() {
        instance = this;
        log("&7&m------------------------");
        log("&aFreeRank Plugin &8- &fv" + getDescription().getVersion());
        log("");
        log("&cLicense Info");
        log("&4Status&f: &aActivated");
        log("&4License&f: Open-Source");
        log("");
        log("&cPlugin Info");
        log("&7&oAuthor:&f" + getDescription().getAuthors());
        log("&7&oDiscord:&f " + getDescription().getWebsite());
        log("&7&m------------------------");
        registerConfig();
        new FreeRankCommand();
    }
    public void registerConfig(){
        File config = new File(getDataFolder(), "config.yml");
        ConfigRoute = config.getPath();
        if (!config.exists()){
            this.getConfig().options().copyDefaults(true);
            saveConfig();
        }
    }

    @Override
    public void onDisable() {
          shutdownMessage();
        }

    private void shutdownMessage() {
            log("&7&m------------------------");
            log("&aFreeRank Plugin &8- &fv" + getDescription().getVersion());
            log(" ");
            log("&cDisconnecting plugin...");
            log("&cJoin our discord for any errors.");
            log(" ");
            log("&4▶ &fhttps://dsc.gg/flameclubdevelopment");
            log("&7&m------------------------");
        }
}
