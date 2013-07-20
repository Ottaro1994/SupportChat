package de.YonasCode.SupportChat.listener;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

import de.YonasCode.SupportChat.Main;
import de.YonasCode.SupportChat.Message;

public class LeaveListener implements Listener {

	@EventHandler
	public void onLeave(PlayerQuitEvent event) {
		String name = event.getPlayer().getName();
		
		if(Main.sp.inWaitlist(name)) Main.sp.removeClientFromWaitlist(name);
		
		if(Main.sp.inChat(name)) {
			
			if(Main.sp.isSupporter(name)) {
				Player client = Main.sp.getClient(name);
				Main.sp.removeSupporterFromChat(name);
				client.sendMessage(Message.LEAVE_CHAT);
			}
			
			if(Main.sp.isClient(name)) {
				Player supporter = Main.sp.getSupporter(name);
				Main.sp.removeSupporterFromChat(supporter.getName());
				supporter.sendMessage(Message.LEAVE_CHAT);
			}
			
		}
		
	}
	
}
