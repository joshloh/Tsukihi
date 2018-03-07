import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import javax.security.auth.login.LoginException;

import net.dv8tion.jda.core.AccountType;
import net.dv8tion.jda.core.JDA;
import net.dv8tion.jda.core.JDABuilder;
import net.dv8tion.jda.core.entities.Game;
import net.dv8tion.jda.core.entities.Guild;
import net.dv8tion.jda.core.entities.Message;
import net.dv8tion.jda.core.entities.MessageChannel;
import net.dv8tion.jda.core.entities.User;
import net.dv8tion.jda.core.events.ReadyEvent;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;

/**
 * 
 * @author joshuafloh
 *
 */
public class Tsukihi extends ListenerAdapter
{
	private String botToken;
	private static boolean verbose = true;
	private JDA jda;
	private Guild guild;
	Tsukihi()
	{
		botToken = getBotToken();
		try
		{
			jda = new JDABuilder(AccountType.BOT).setToken(botToken).addEventListener(this).buildAsync();
			
		} catch (LoginException e)
		{
			e.printStackTrace();
		}
	}

	/**
	 * Return the bot token if one can be found. Credit to Namdrib https://github.com/Namdrib/RoleBot/blob/master/src/App.java
	 * @return
	 */
	public static String getBotToken()
	{
		String botToken = null;
		final String envVar = "DISCORD_ROLEBOT_TOKEN";

		Properties prop = new Properties();
		final String filename = "config.properties"; // make sure this file exists, store token there

		// Read the bot's token from filename or environment variable
		try (InputStream input = new FileInputStream(filename))
		{
			prop.load(input);
			botToken = prop.getProperty("botToken");
		} catch (IOException ex)
		{
			ex.printStackTrace();
		}

		// Retrieve from environment variables (for Heroku)
		if (botToken == null)
		{
			botToken = System.getenv(envVar);
		}
		return botToken;
	}
	
	public void print(String in)
	{
		if (verbose)
		{
			System.out.println(in);
		}
	}
	
	/* 
	 * ==========================================================================================================
	 * OVERRIDED FUNCTIONS
	 * ==========================================================================================================
	 */
	@Override
	public void onMessageReceived(MessageReceivedEvent e)
	{
		Message message = e.getMessage();
		MessageChannel channel = e.getChannel();
		User user = e.getAuthor();
		print(message.getContentRaw());
		jda.getSelfUser();
		if (!user.isBot() && message.getMentionedUsers().contains(jda.getSelfUser()))
		{
			channel.sendMessage("Hi, " + user.getAsMention() + "! I don't do anything yet, but I exist now!").queue();
		}
	}
	
	@Override
	public void onReady(ReadyEvent e)
	{
		jda.getPresence().setGame(Game.playing("Invoke with @" + jda.getSelfUser().getName()));
	}
}
