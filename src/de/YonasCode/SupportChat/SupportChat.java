package de.YonasCode.SupportChat;

import java.util.ConcurrentModificationException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import de.YonasCode.SupportChat.listener.ChatListener;
import de.YonasCode.SupportChat.listener.LeaveListener;

public class SupportChat {
	

	private HashMap<String, String> waitlist 		= new HashMap<String, String>();
	private HashMap<String, String> undersupport	= new HashMap<String, String>();
	private int TASK_ID;
	
	public boolean isEmpty() {
		return this.waitlist.isEmpty();
	}
	
	public boolean inWaitlist(String player) {
		return this.waitlist.containsKey(player);
	}
	
	public boolean isSupporter(String player) {
		return this.undersupport.containsKey(player);
	}
	
	public boolean isClient(String player) {
		return this.undersupport.containsValue(player);
	}
	
	public boolean inChat(String player) {
		if(this.undersupport.containsValue(player) || this.undersupport.containsKey(player)) return true; else return false;
	}
	
	public String getQuestion(String player) {
		return this.waitlist.get(player);
	}
	
	public Set<String> getWaitlist() {
		return this.waitlist.keySet();
	}
	
	public void setUp() {
		Main.INSTANCE.getServer().getPluginManager().registerEvents(new ChatListener(), Main.INSTANCE);
		Main.INSTANCE.getServer().getPluginManager().registerEvents(new LeaveListener(), Main.INSTANCE);
		startScheduler();
	}
	
	public void setDown() {
		stopScheduler();
	}
	
	public void removeSupporterFromChat(String player) {
		try {
			for(String p : this.undersupport.keySet()) {
				if(p.equals(player))
					this.undersupport.remove(p);
			}
		} catch(ConcurrentModificationException  ex){}
	}
	
	public void removeClientFromWaitlist(String player) {
		for(String p : this.waitlist.keySet()) {
			if(p.equals(player))
				this.waitlist.remove(p);
		}
	}
	
	public void putInChat(Player supporter, Player client) {
		this.undersupport.put(supporter.getName(), client.getName());
		StringBuilder message = new StringBuilder();
		for(int i = 0; i < 10; i++) {
			message.append(" ");
			supporter.sendMessage(message.toString());
			client.sendMessage(message.toString());
		}
		supporter.sendMessage(Message.TAG + ChatColor.GREEN + "You're now with the player " + ChatColor.GOLD + client.getName() + ChatColor.GREEN + " in a support chat. The player has following question: " + ChatColor.GOLD + getQuestion(client.getName()));
		client.sendMessage(Message.TAG + ChatColor.GREEN + "You're now with the supporter " + ChatColor.GOLD + supporter.getName() + ChatColor.GREEN + " in a support chat.");
		try {
			this.waitlist.remove(client.getName());
		}catch(ConcurrentModificationException ex){}
	}
	
	public void clearWaitlist() {
		this.waitlist.clear();
	}
	
	@SuppressWarnings("deprecation")
	private void startScheduler() {
		this.TASK_ID = Bukkit.getScheduler().scheduleAsyncRepeatingTask(Main.INSTANCE, new Runnable() {

			@Override
			public void run() {
				for(Player p : Bukkit.getOnlinePlayers()) {
					if(p.hasPermission(Permission.SUPPORTCHAT_SEE))
						p.sendMessage(Message.TAG + ChatColor.GREEN + "Open tickets: " + ChatColor.GOLD + Main.sp.count() + ChatColor.GREEN + ".");
				}
			}
			
		}, Variables.CHECK_TICKETS_TIME, Variables.CHECK_TICKETS_TIME);
	}
	
	private void stopScheduler() {
		Bukkit.getScheduler().cancelTask(this.TASK_ID);
	}
	
	public void addWaitlist(String player, String question) {
		this.waitlist.put(player, question);
	}
	
	public void leave(Player player) {
		Player target = getClient(player.getName());
		removeSupporterFromChat(player.getName());
		player.sendMessage(Message.LEAVE_CHAT);
		target.sendMessage(Message.LEAVE_CHAT);
	}
	
	public int count() {
		return this.waitlist.size();
	}
	
	public Player getNextPlayer() {
		return Bukkit.getPlayer(this.waitlist.keySet().toArray()[0].toString());
	}
	
	public Player getClient(String player) {
		return Bukkit.getPlayer(this.undersupport.get(player));
	}
	
	public Player getSupporter(String player) {
		String name = "";
		for(@SuppressWarnings("rawtypes") Map.Entry entry : this.undersupport.entrySet()) {
			if(entry.getValue().equals(player))
				name = entry.getKey().toString();
		}
		return Bukkit.getPlayer(name);
	}
	
}
