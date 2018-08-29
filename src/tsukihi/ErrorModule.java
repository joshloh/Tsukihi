package tsukihi;

import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

/**
 * Special case of module
 * @author joshuafloh
 *
 */
public class ErrorModule extends HelpModule
{
	ErrorModule(Tsukihi tsukihi)
	{
		super(tsukihi);
	}

	@Override
	public List<String> createInvokers()
	{
		return Arrays.asList("error");
	}
	@Override
	public List<String> createDescriptions()
	{
		return Arrays.asList("displays the generic error");
	}
	@Override
	public void commandReceived(MessageReceivedEvent e, String invoker, StringTokenizer st)
	{
		e.getChannel().sendMessage("Invalid command").queue();
		printHelpMenu(e);
		
	}
}