package tsukihi;

import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

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
	public void commandReceived(MessageReceivedEvent e, String invoker, StringTokenizer st)
	{
		e.getChannel().sendMessage("help message placeholder\n"
				+ "here are some commands\n"
				+ "hali\n"
				+ "emma\n"
				+ "denton\n"
				+ "help\n"
				+ "i ceebs explaining what they do for now ill finish this module laterrr\n").queue();
	}
}
