package me.chisdealhd.customplugin;

import java.awt.Color;
import java.net.URI;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/*import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.nio.charset.Charset;*/

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;
//import me.chisdealhd.customplugin.Hypixel;
//import org.json.simple.JSONObject;

import ca.momoperes.canarywebhooks.DiscordMessage;
import ca.momoperes.canarywebhooks.WebhookClient;
import ca.momoperes.canarywebhooks.WebhookClientBuilder;
import ca.momoperes.canarywebhooks.embed.DiscordEmbed;

public class ChisdealHDPlugin extends JavaPlugin implements Listener {
	  
	  /*public void main(String[] args) throws IOException {
		  String[] array = new String[]{"https://chisdealhd.xyz/radio/np.php","Test"};
		    JSONObject json = Json_Util.Json_Util(array);
		    System.out.println(json.toString());
		    System.out.println(json.get("radioname"));
		  }*/
			  
	/*public void update() {
	    if (UpdateChecker.checkUpdate(53045, 1.2)) {
	    	Bukkit.getServer().getLogger().info(ChatColor.GREEN +"There is a UPDATE! Please Install Newest Version");
    	} else {
        	Bukkit.getServer().getLogger().info(ChatColor.RED +"There is no Update, Update UP TO DATE!");
		}
	}*/
	
	public void onEnable() {
		Bukkit.getServer().getLogger().info("Plugin is Working!");
		//update();
		/*try {
			main(null);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
	}

	public void onDisable() {
		Bukkit.getServer().getLogger().info("Plugin is Not Working!");
	}
	
    public boolean onCommand(CommandSender sender,
            Command command,
            String commandLabel,
            String[] args) {
        	if (command.getName().equalsIgnoreCase("test")) {
        		sender.sendMessage(ChatColor.RED + "You ran /test!");
        		return true;
        	}
       
        	if (command.getName().equalsIgnoreCase("online")) {
            	if (args.length == 0) {
                	sender.sendMessage(ChatColor.GOLD + "/online twitch <username>: Checks for Online on Twitch.");
                	sender.sendMessage(ChatColor.GOLD + "/online mixer <username>: Checks for Online on Mixer.");
                	return true;
            	}
           
            	if (args[0].equalsIgnoreCase("twitch")) {
                	// /motd set &bThis is a test.
                	//       0   1      2  3 4
                	if (args.length == 1) {
                    	sender.sendMessage(ChatColor.DARK_PURPLE+"[Twitch] "+ChatColor.RED + "You must enter a username.");
                    	return true;
                	}
               
                	String message = "";
                	for (int i = 1; i < args.length; i++) {
                    	message += args[i] + " ";
                	}
                	message = message.trim();
               
                	StreamAPI streamarg = new StreamAPI(message, args);
                	if(streamarg.isOnline()) {
            			Bukkit.getServer().getLogger().info("[Twitch] "+ message +" now ONLINE!");
            			sender.sendMessage(ChatColor.DARK_PURPLE+"[Twitch] "+ChatColor.GREEN + message +" now ONLINE!");
            		} else {
                		Bukkit.getServer().getLogger().info("[Twitch] "+ message +" Offline!");
                		sender.sendMessage(ChatColor.DARK_PURPLE+"[Twitch] "+ChatColor.RED + message +" Offline!");
        			}
            	}
            
            	else if (args[0].equalsIgnoreCase("mixer")) {
            		// /motd set &bThis is a test.
            		//       0   1      2  3 4
                	if (args.length == 1) {
                    	sender.sendMessage(ChatColor.DARK_PURPLE+"[Mixer] "+ChatColor.RED + "You must enter a username.");
                    return true;
                	}
               
                	String message = "";
                	for (int i = 1; i < args.length; i++) {
                    	message += args[i] + " ";
                	}
                	message = message.trim();
               
                	Mixer streamarg = new Mixer(message, args);
                	if(streamarg.isOnline()) {
            			Bukkit.getServer().getLogger().info("[Mixer] "+ message +" now ONLINE!");
            			sender.sendMessage(ChatColor.DARK_PURPLE+"[Mixer] "+ChatColor.GREEN + message +" now ONLINE!");
            		} else {
            			Bukkit.getServer().getLogger().info("[Mixer] "+ message +" Offline!");
            			sender.sendMessage(ChatColor.DARK_PURPLE+"[Mixer] "+ChatColor.RED + message +" Offline!");
        			}
            	}
        	}
            if (command.getName().equalsIgnoreCase("discord")) {
                if (args.length == 0) {
                    sender.sendMessage(ChatColor.GOLD + "/discord reportplayer <username> <cheats>: Reports Username.");
                    return true;
                }
               
                if (args[0].equalsIgnoreCase("reportplayer")) {
                    // /motd set &bThis is a test.
                    //       0   1      2  3 4
                	String webhookUrl = "https://discordapp.com/api/webhooks/425210879139905538/004ovvpL5ma8AK_0Z1-AAuUQAGSyJXfBlj0BGO8rNYkDKzBEKofFcfSR7GEU1wOm_jUY";
                	try {
                		if (args.length == 1) {
                			sender.sendMessage(ChatColor.RED+"[WatchDog Report] "+ChatColor.GOLD + "You must enter a username.");
                			return true;
                		}
                		if (args.length == 2) {
                			sender.sendMessage(ChatColor.RED+"[WatchDog Report] "+ChatColor.GOLD + "Examples:");
                			sender.sendMessage(ChatColor.GOLD + "Cheating");
                			sender.sendMessage(ChatColor.GOLD + "Abusing Bugs");
                			sender.sendMessage(ChatColor.GOLD + "Advertising");
                			sender.sendMessage(ChatColor.GOLD + "Disrespect");
                			sender.sendMessage(ChatColor.GOLD + "Spamming");
                			sender.sendMessage(ChatColor.GOLD + "Staff Impersonation");
                			sender.sendMessage(ChatColor.GOLD + "Other");
                			return true;
                		}
                   
                		String message = "";
                		for (int i = 1; i < args.length; i++) {
                			message += args[i] + " ";
                		}
                		message = message.trim();
                    	String reason;
                    	String reported = args[2];
                    	args[0] = null;
                    
                    	reason = Stream.of(args)
                            .filter(s -> s != null && !s.isEmpty())
                            .collect(Collectors.joining(" "));
                    	if (!Bukkit.getOfflinePlayer(reported).hasPlayedBefore() && !(reported.equalsIgnoreCase("other"))) {
                    		sender.sendMessage(ChatColor.RED+"[WatchDog Report] "+ChatColor.GOLD +" Player not found!");
                    	} else {
                    		sender.sendMessage(ChatColor.RED+"[WatchDog Report] "+ChatColor.GOLD +" Report sent!");
                    		for (Player pl : Bukkit.getOnlinePlayers()) {
                    			DiscordEmbed embed = new DiscordEmbed.Builder()
                	                    .withColor(Color.decode("#f42727"))
                	                    .withDescription("[WatchDog Report]\n\nNew report:\n```\n" + reported + "\n reported for " + reason + " by " + sender.getName())
                	                    .withTitle("[WatchDog Report]")
                	                    .build();

                	            DiscordMessage dmessage = new DiscordMessage.Builder("")
                	                    .withEmbed(embed)
                	                    .build();

                	            WebhookClient client = new WebhookClientBuilder()
                	                    .withURI(new URI(webhookUrl))
                	                    .build();
                	            client.sendPayload(dmessage);
                    			if (pl.hasPermission("chisdealhd.reports.view")) {
                    				sender.sendMessage(ChatColor.RED+"[WatchDog Report] "+ChatColor.GOLD +" New report: " + reported + " reported for " + reason + " by " + sender.getName());
                    			}
                    		}
                    	}
                	} catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }
           
            else if (args[0].equalsIgnoreCase("reload")) {
                reloadConfig();
                sender.sendMessage(ChatColor.GREEN + "Reloaded config.");
            }
           
            else {
                 sender.sendMessage(ChatColor.RED + "Invalid operation.");
            }
        }
       
        return true;
    }     
}
