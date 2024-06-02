package me.hcfalerts.freerank.commands;

import me.hcfalerts.freerank.FreeRank;
import me.hcfalerts.freerank.utils.CC;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

/**
 * Re-Work Code by HCFAlerts
 * Project: Simple FreeRank
 */

public class FreeRankCommand implements CommandExecutor {
    public FreeRankCommand() {
        FreeRank.getInstance().getCommand("freerank").setExecutor(this);
    }
    private final FreeRank plugin = FreeRank.getInstance();

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(CC.translate("&aFreeRank Command &cis only for players."));
        } else {
            Player player = (Player) sender;
            SimpleDateFormat loadFormat = new SimpleDateFormat("MM/dd/yy HH:mm:ss");
            loadFormat.setTimeZone(TimeZone.getTimeZone(this.plugin.getConfig().getString("timezone")));
            try {
                long endDate = loadFormat.parse(this.plugin.getConfig().getString("date")).getTime();
                long diff = endDate - new Date().getTime();
                if (diff <= 0) {
                    player.sendMessage(CC.translate("&cFreeRank is currently disabled."));
                } else {
                    long sec = diff / 1000;
                    Bukkit.getServer().dispatchCommand(Bukkit.getServer().getConsoleSender(), "setrank " + player.getName() + this.plugin.getConfig().getString("rank-name") + "3d" + "Global" + "Claimed in /FreeRank");
                    player.sendMessage(CC.translate("&eEnjoy your new free rank! &c❤"));
                }
                return false;
            } catch (ParseException ignored) {
                Bukkit.getConsoleSender().sendMessage(CC.translate("&cFailed to load time. Is it in the correct format?"));
            }
        }
        return false;
    }
}
