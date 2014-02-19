package de.YonasCode.SupportChat;

import org.bukkit.ChatColor;

public class Message {
	
	public static final String TAG 				= ChatColor.GOLD + "[" + ChatColor.AQUA + "SupportChat" + ChatColor.GOLD + "] ";
	public static final String NO_PERMISSIONS 	= ChatColor.RED + "Du hast keine Rechte für diesen Befehl!";
	public static final String MORE_WORDS		= ChatColor.RED + "Bitte gebe mehr Wörter ein.";
	public static final String IN_WAITLIST		= ChatColor.RED + "Du bist bereits in die Warteliste.";
	public static final String ADDED_WAITLIST 	= ChatColor.GREEN + "Du bist nun in der Warteliste. Bitte warte, bis sich ein Supporter bei dir meldet.";
	public static final String ALREADY_IN_CHAT 	= ChatColor.RED + "Du bist bereits in einer Unterhaltung.";
	public static final String EMPTY_WAITLIST	= ChatColor.RED + "Die Wartelist ist leer.";
	public static final String LEAVE_CHAT		= ChatColor.GREEN + "Die Unterhaltung wurde erfolgreich geschlossen.";
	public static final String NEED_SUPPORTER	= ChatColor.RED + "Du musst ein Supporter sein.";
	public static final String NEED_CHAT		= ChatColor.RED + "Du musst dich in einer Unterhaltung befinden.";
	public static final String NOT_ON_WAITLIST	= ChatColor.RED + "There is no question from this user on the waitlist.";
	public static final String INQUIRY_CLOSED	= ChatColor.GREEN + "Inquiry closed successfully.";
	public static final String CLOSE_SIMPLE		= ChatColor.RED + "Your support inquiry was closed.";
	public static final String CLOSE_W_TEXT		= ChatColor.RED + "Your support inquiry was closed with the following note or answer:";
}
