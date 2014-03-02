package de.YonasCode.SupportChat.listener;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import de.YonasCode.SupportChat.Main;
import de.YonasCode.SupportChat.Message;
import de.YonasCode.SupportChat.Permission;

public class JoinListener implements Listener {

	@EventHandler
	public void onJoin(PlayerJoinEvent event){
	Player p = event.getPlayer();
		if(p.hasPermission(Permission.SUPPORTCHAT_SEE))
			p.sendMessage(Message.TAG + ChatColor.GREEN + "Open tickets: " + ChatColor.GOLD + Main.sp.count() + ChatColor.GREEN + ".");
	}
}
