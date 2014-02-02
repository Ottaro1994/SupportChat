package de.YonasCode.SupportChat;

import java.util.logging.Logger;

import org.apache.commons.lang.StringUtils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {

	public static Logger LOG = Logger.getLogger("Minecraft");
	public static Main INSTANCE;
	public static SupportChat sp;
	
	@Override
	public void onEnable() {
		Main.INSTANCE = this;
		sp = new SupportChat();
		sp.setUp();
	}
	
	@Override
	public void onDisable() {
		if(sp != null)
			sp.setDown();
	}
	
	@Override
    public boolean onCommand(CommandSender sender, Command cmd, String commandLable, String[] args){
	
		if(sender instanceof Player) {
			
			Player pl = (Player)sender;
			
			if(cmd.getName().equalsIgnoreCase("support")) {
				if((args.length >= 1) && (args[0].equalsIgnoreCase("list") || args[0].equalsIgnoreCase("accept") || 
					args[0].equalsIgnoreCase("clear") || args[0].equalsIgnoreCase("leave") || 
					args[0].equalsIgnoreCase("close"))) 
				{
					//list
					if(args[0].equalsIgnoreCase("list")) {
						if(pl.hasPermission(Permission.SUPPORTCHAT_LIST)) {
							if(!(sp.isEmpty())) {
								int i = 1;
								for(String s : sp.getWaitlist()) {
									pl.sendMessage(ChatColor.GOLD + "[" + ChatColor.AQUA + i + ChatColor.GOLD + "]" + ChatColor.GRAY + s + ChatColor.AQUA + "||" + ChatColor.GREEN + sp.getQuestion(s));
									i++;
								}
							} else {
								pl.sendMessage(Message.EMPTY_WAITLIST);
							}
							
							return true;
						} else {
							pl.sendMessage(Message.NO_PERMISSIONS);
							return true;
						}
						
					}
					//list end
					
					//accept
					else if(args[0].equalsIgnoreCase("accept")) {
						if(pl.hasPermission(Permission.SUPPORTCHAT_ACCEPT)) {
							
							if(!(sp.isEmpty())) {
								if(!(sp.inChat(pl.getName()))) {
									if (args.length >= 2) {
										Player target = Bukkit.getPlayer(args[1]);
										if (target != null && !target.equals(pl))
											sp.putInChat(pl, target);
										else
											pl.sendMessage(Message.NOT_ON_WAITLIST);
									} else {
										Player target = sp.getNextPlayer();
										sp.putInChat(pl, target);
									}
								} else {
									pl.sendMessage(Message.ALREADY_IN_CHAT);
								}
								
								return true;
							} else {
								pl.sendMessage(Message.EMPTY_WAITLIST);
								return true;
							}
					
						} else {
							pl.sendMessage(Message.NO_PERMISSIONS);
							return true;
						}
					}
					//accept end
					
					//clear
					else if(args[0].equalsIgnoreCase("clear")) {
						if(pl.hasPermission(Permission.SUPPORTCHAT_CLEAR)) {
							sp.clearWaitlist();
							pl.sendMessage(ChatColor.GREEN + "The waitlist is now empty.");
							return true;
						} else {
							pl.sendMessage(Message.NO_PERMISSIONS);
							return true;
						}
					}
					//clear end
					
					//leave
					else if(args[0].equalsIgnoreCase("leave")) {
						if(pl.hasPermission(Permission.SUPPORTCHAT_LEAVE)) {
							if(sp.inChat(pl.getName())) {
								if(sp.isSupporter(pl.getName())) {
									sp.leave(pl);
								} else {
									pl.sendMessage(Message.NEED_SUPPORTER);
								}
							} else {
								pl.sendMessage(Message.NEED_CHAT);
							}
							return true;
						} else {
							pl.sendMessage(Message.NO_PERMISSIONS);
							return true;
						}
					}
					//leave end
					
					//close
					else if(args[0].equalsIgnoreCase("close")) {
						if(pl.hasPermission(Permission.SUPPORTCHAT_CLOSE)) {
							if (args.length < 2)
								return false;
							
							if (sp.removeClientFromWaitlist(args[1])) {
								Player target = Bukkit.getPlayer(args[1]);
								if (args.length >= 3) {
									String txt = StringUtils.join(args, " ", 2, args.length);
									target.sendMessage(Message.CLOSE_W_TEXT);
									target.sendMessage(ChatColor.GOLD + "    " + txt);
								} else {
									target.sendMessage(Message.CLOSE_SIMPLE);
								}
								
								pl.sendMessage(Message.INQUIRY_CLOSED);
							} else {
								pl.sendMessage(Message.NOT_ON_WAITLIST);
							}
							
							return true;
						} else {
							pl.sendMessage(Message.NO_PERMISSIONS);
							return true;
						}
					}
					//close end
					
				} else {
					
					//join
						if(args.length >= Variables.MIN_WORDS) {
							if(pl.hasPermission(Permission.SUPPORTCHAT_JOIN)) {
								if(!(sp.inWaitlist(pl.getName()))) {
									if(!(sp.inChat(pl.getName()))) {
										StringBuilder question = new StringBuilder();
										for(int i = 0; i < args.length; i++) {
											question.append(args[i]).append(" ");
										}
										for(Player p : Bukkit.getOnlinePlayers()) {
											if(Variables.SEND_ALERT_MESSAGE)
												if(p.hasPermission(Permission.SUPPORTCHAT_SEE))
													p.sendMessage(Message.TAG + ChatColor.RED + "! ALERT ! - A new support ticket is available.");
										}
										sp.addWaitlist(pl.getName(), question.toString());
										pl.sendMessage(Message.ADDED_WAITLIST);
									} else {
										pl.sendMessage(Message.ALREADY_IN_CHAT);
									}
								} else {
									pl.sendMessage(Message.IN_WAITLIST);
								}
							} else {
								pl.sendMessage(Message.NO_PERMISSIONS);
							}
							return true;
						} else {
							pl.sendMessage(Message.MORE_WORDS);
							return true;
						}
					//join end
					
				}
				
				
				return true;
			}
			
			return true;	
		}
		
		return false;
	}
	
}
