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
public class ErrorModule extends Module
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
	public void commandReceived(MessageReceivedEvent e, String invoker, StringTokenizer st)
	{
		e.getChannel().sendMessage("Invalid command").queue();
		// TODO: Print help message here
	}
}