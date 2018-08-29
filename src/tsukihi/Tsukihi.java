package tsukihi;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import java.util.StringTokenizer;

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
	
	private Map<String, Module> modules;
	public Set<Module> moduleSet;
	
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
		modules = new HashMap<String, Module>();
		moduleSet = new LinkedHashSet<Module>();
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
	
	/**
	 * Print a string to Sysout if verbose is active
	 * @param in the string to be printed
	 */
	public void print(String in)
	{
		if (verbose)
		{
			System.out.println(in);
		}
	}
	
	public void addModule(String invoker, Module module)
	{
		modules.put(invoker, module);
		moduleSet.add(module);
	}
	
	public Map<String, Module> getModules()
	{
		return modules;
	}
	/* 
	 * ==========================================================================================================
	 * OVERRIDED FUNCTIONS (EVENTS)
	 * ==========================================================================================================
	 */
	@Override
	public void onMessageReceived(MessageReceivedEvent e)
	{
		StringTokenizer st = new StringTokenizer(e.getMessage().getContentStripped());
		
		// if the bot was mentioned
		if (st.hasMoreTokens() && st.nextToken().equals("@" + jda.getSelfUser().getName()))
		{
			if (st.hasMoreTokens())
			{
				String requestedModuleName = st.nextToken();
				Module target;
				if (modules.containsKey(requestedModuleName))
				{
					target = modules.get(requestedModuleName);
				}
				else
				{
					target = modules.get("error");
				}
				target.commandReceived(e, requestedModuleName, st);
			}
			else	// Tsukihi was mentioned, but no additional arguments provided
			{
				e.getChannel().sendMessage("Hi! Thanks for mentioning me, but you didn't tell me to do anything...").queue();
			}
		}
	}
	
	@Override
	public void onReady(ReadyEvent e)
	{
		jda.getPresence().setGame(Game.playing("Invoke with @" + jda.getSelfUser().getName()));
	}
}
