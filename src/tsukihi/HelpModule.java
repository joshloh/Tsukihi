package tsukihi;

import java.awt.Color;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

public class HelpModule extends Module
{
	public HelpModule(Tsukihi tsukihi)
	{
		super(tsukihi);
	}
	@Override
	public List<String> createInvokers()
	{
		return Arrays.asList("help");
	}
	@Override
	public List<String> createDescriptions()
	{
		return Arrays.asList("displays this help message");
	}
	@Override
	public void commandReceived(MessageReceivedEvent e, String invoker, StringTokenizer st)
	{
		printHelpMenu(e);
	}
	
	protected void printHelpMenu(MessageReceivedEvent e)
	{
		String out = "";
		for (Module m : tsukihi.moduleSet)
		{
			out += m.help();
		}
		
		EmbedBuilder eb = new EmbedBuilder();
		eb.setTitle("help", null);
		eb.setColor(Color.blue);
		eb.setDescription(out + "\n");
		e.getChannel().sendMessage(eb.build()).queue();
	}
}
