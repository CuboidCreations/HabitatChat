package com.gmail.Xeiotos.HabitatChat;

import com.gmail.Xeiotos.HabitatAPI.HabitatAPI;
import com.gmail.Xeiotos.HabitatAPI.HabitatPlayer;
import com.gmail.Xeiotos.HabitatAPI.Managers.HabitatPlayerManager;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.plugin.java.JavaPlugin;

/**
 *
 * @author Chris
 */
public class HabitatChat extends JavaPlugin implements Listener {

    @Override
    public void onEnable() {
        HabitatAPI.getHook(this);
        Bukkit.getServer().getPluginManager().registerEvents(this, this);
    }

    @Override
    public void onDisable() {
        HabitatAPI.unHook(this);
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onPlayerChatEvent(AsyncPlayerChatEvent event) {

        Player player = event.getPlayer();
        HabitatPlayer habitatPlayer = HabitatPlayerManager.getManager().getHabitatPlayer(player);
        if (player.getWorld() != Bukkit.getWorld("world")) {
            event.setMessage(ChatColor.GOLD + "@ " + ChatColor.AQUA
                    + "Otherworld "
                    + ChatColor.RESET + event.getMessage());
            event.setFormat(player.getDisplayName() + " " + event.getMessage());
            return;
        }

        if (habitatPlayer.getHabitat() == null) {
            event.setMessage(ChatColor.GOLD + "@ " + ChatColor.AQUA
                    + "Overworld "
                    + ChatColor.RESET + event.getMessage());
            event.setFormat(player.getDisplayName() + " " + event.getMessage());
            return;
        }

        event.setMessage(ChatColor.GOLD + "@ " + ChatColor.AQUA
                + habitatPlayer.getHabitat().getTypeName() + " "
                + habitatPlayer.getRelativeLocation().x + ","
                + habitatPlayer.getRelativeLocation().y + " "
                + ChatColor.RESET + event.getMessage());
        event.setFormat(player.getDisplayName() + " " + event.getMessage());

    }
}
