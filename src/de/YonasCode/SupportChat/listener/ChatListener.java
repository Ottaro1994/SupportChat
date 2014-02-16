package de.YonasCode.SupportChat.listener;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import de.YonasCode.SupportChat.Main;

public class ChatListener implements Listener {
	
	@EventHandler
	public void onSupportChatEvent(AsyncPlayerChatEvent event) {
		if(event.isCancelled()) return;
		
		boolean cancel = false;
		String name = event.getPlayer().getName();
		
		if(Main.sp.inChat(name)) 
			cancel = true;
		
		for(Player p : Bukkit.getOnlinePlayers()) {
			if(Main.sp.inChat(p.getName()))
				event.getRecipients().remove(p);
		}
		
		if(Main.sp.inChat(name)) {
			
			//if Supporter
			if(Main.sp.isSupporter(name)) {
				Player client = Main.sp.getClient(name);
				client.getPlayer().sendMessage(ChatColor.RED + event.getPlayer().getName() + ": " + ChatColor.AQUA + event.getMessage());
                event.getPlayer().sendMessage(ChatColor.RED + event.getPlayer().getName() + ": " + ChatColor.AQUA + event.getMessage());
                System.out.println(ChatColor.GOLD + "[SupportChat]" + ChatColor.RED + event.getPlayer().getName() + ": " + ChatColor.AQUA + event.getMessage());
			}
			//or if client
			if(Main.sp.isClient(name)) {
				Player supporter = Main.sp.getSupporter(name);
				supporter.getPlayer().sendMessage(ChatColor.RED + event.getPlayer().getName() + ": " + ChatColor.AQUA + event.getMessage());
                event.getPlayer().sendMessage(ChatColor.RED + event.getPlayer().getName() + ": " + ChatColor.AQUA + event.getMessage());
                System.out.println(ChatColor.GOLD + "[SupportChat]" + ChatColor.RED + event.getPlayer().getName() + ": " + ChatColor.AQUA + event.getMessage());
			}
				
		}
		
		event.setCancelled(cancel);
	}

}
