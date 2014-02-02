package de.YonasCode.SupportChat;

import org.bukkit.ChatColor;

public class Message {
	
	public static final String TAG 				= ChatColor.GOLD + "[" + ChatColor.AQUA + "SupportChat" + ChatColor.GOLD + "] ";
	public static final String NO_PERMISSIONS 	= ChatColor.RED + "Sorry, but you're not permitted to use this command.";
	public static final String MORE_WORDS		= ChatColor.RED + "Please enter more words.";
	public static final String IN_WAITLIST		= ChatColor.RED + "You're already in the waitlist.";
	public static final String ADDED_WAITLIST 	= ChatColor.GREEN + "You're now in the waitlist.";
	public static final String ALREADY_IN_CHAT 	= ChatColor.RED + "You're already in a support chat.";
	public static final String EMPTY_WAITLIST	= ChatColor.RED + "The waitlist is empty.";
	public static final String LEAVE_CHAT		= ChatColor.GREEN + "The chat was successfully closed.";
	public static final String NEED_SUPPORTER	= ChatColor.RED + "You must be a supporter.";
	public static final String NEED_CHAT		= ChatColor.RED + "You must be in a support chat.";
	public static final String NOT_ON_WAITLIST	= ChatColor.RED + "There is no question from this user on the waitlist.";
	public static final String INQUIRY_CLOSED	= ChatColor.GREEN + "Inquiry closed successfully.";
	public static final String CLOSE_SIMPLE		= ChatColor.RED + "Your support inquiry was closed.";
	public static final String CLOSE_W_TEXT		= ChatColor.RED + "Your support inquiry was closed with the following note or answer:";
	
}
