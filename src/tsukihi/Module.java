package tsukihi;
import java.util.List;
import java.util.StringTokenizer;

import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

/**
 * Abstract class for a Tsukihi Module
 * 
 * Each class that implements this interface should have:
 * - A list of commands that invokes it
 * 
 * Each constructor must
 * - Register itself as one of the modules in Tsukihi using the registerModule() function
 * @author joshuafloh
 *
 */
public abstract class Module
{
	protected Tsukihi tsukihi;
	protected List<String> invokers;
	protected List<String> descriptions;

	/**
	 * Default constructor: should never really be called
	 */
	public Module()
	{
		
	}
	/**
	 * Constructor that registers all the module invokers with Tsukihi
	 */
	public Module(Tsukihi t)
	{
		tsukihi = t;
		if ((invokers = createInvokers()) == null) return;
		for (String s : invokers)
		{
			tsukihi.addModule(s, this);
		}
		descriptions = createDescriptions();
	}
	
	public String help()
	{
		String out = "";
		for (int i = 0; i < invokers.size(); i++)
		{
			out += "**" + invokers.get(i) + "** " + descriptions.get(i) + "\n";
		}
		return out;
	}
	
	/**
	 * MUST be overridden by every module
	 * @return  a list of all the strings that invoke this module
	 */
	public abstract List<String> createInvokers();
	public abstract List<String> createDescriptions();
	/* ===========================================================================================
	 * ABSTRACT METHODS (EVENTS)
	 * ===========================================================================================
	 */
	
	public abstract void commandReceived(MessageReceivedEvent e, String invoker, StringTokenizer st);
}
