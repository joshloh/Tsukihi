package quote;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.StringTokenizer;

import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import tsukihi.Module;
import tsukihi.Tsukihi;

/**
 * Extension of the Module class
 * @author joshuafloh
 *
 */
public class QuoteModule extends Module
{
	// Maps authors to a list of their quotes
	Map<String, List<String>> quotes;
	
	public QuoteModule(Tsukihi tsukihi)
	{
		super(tsukihi);
		quotes = new HashMap<String, List<String>>();
		createQuotes();
	}
	
	/**
	 * Initialises the quotes map, should be called before the module does anything else
	 */
	private void createQuotes()
	{
		List<String> haliQuotes = Arrays.asList(
				"250g is  half a kilo",
				"I'm supposed to be at work right now",
				"I need to go to work",
				"Take a bowl of steam",
				"cheatah stripes",
				"Why am I just not a lesbian",
				"You can get conjunctivitis from looking at someone's eye",
				"Stop touching me, Emmanuel!",
				"I have so much work to do",
				"*kisses Kelsey on the cheek*",
				"Argh! There's so much to do"
				);
		List<String> emmaQuotes = Arrays.asList(
				"Githib",
				"waht i dont get is how to write the code",
				"i think to solve global warming we just need lots of huge fans, that cool air or maybe put icecubes into oceans?",
				"jumping off a parachute",
				"How is killing people evil",
				"wat are odd numbers again?",
				"How do you get on to Heroku? Is it GitHub?",
				"What if it's some ISIS porn?",
				"WAIT HOW ARE BINARYSEARCH AND QUICK SORT DIFFERENT??",
				"When loop",
				"what religion was jesus?",
				"What the fuck is beef? Does it come from cows?",
				"do we need to know for ISE",
				"The sink that the dishwasher is in",
				"The world revolves around me",
				"Do you practise not killing people",
				"Do I have a spouse? What's a spouse?",
				"I AM THE SUN",
				"You can be a mother but not like... be a mother while being a mother",
				"anyone here knows the msyteries of a toilet?",
				"The ocean isn't blue",
				"I'd be your daddy for MnMs",
				"Can you read with your ears closed?",
				"Does that look circumcised? Or is it like, Jewish?",
				"I should buy some gluten free water for her",
				"I'm young for my age",
				"What is Bendigo Bank, is it a bank?",
				"Do you practice shaving and waxing on a dumpling?",
				"I watch TV shows in my headphones",
				"Genocide is okay as long as it's not the entire race",
				"Which part of a butt cheek is concave and which is convex?",
				"black is just a really dark white right?",
				"Cheese is not savory",
				"You cant get pregnant the first time you have sex",
				"I don't mind being 20 at least it makes me wisdomer than you",
				"What language do they speak in Holland? Hollish?",
				"if the course has a 25% fail rate does that mean that 25% of the class will fail it, or 25% of people in that class don't pass?",
				"Lasagna is a cake"
				);
		List<String> dentonQuotes = Arrays.asList(
				"it has been confirmed that Aiden is in fact a male",
				"I wonder what happen if some official document had something like This product can support three (4) people",
				"HOLY SHIT, YOU CAN LEGIT OPEN CODE WITH EXCEL",
				"Literally everything in the world already exists",
				"Is it acceptable to submit the essay in a format such as plaintext or PowerPoint?",
				"phew, that was clost. I almost lost zero marks",
				"public Vector<Vector<Vector>> Topovots",
				"```java\n"
				+ "if (SeeWhetherASingleSegmentIsAlreadyInTopovots(left, Topovots.get(i).get(j)) || SeeWhetherASingleSegmentIsAlreadyInTopovots(right, Topovots.get(i).get(j)))\r\n" + 
				"if ((left.get(0).GetLine() == Topovots.get(i).get(j).get(0).GetLine() &&    left.get(0).GetFile() == Topovots.get(i).get(j).get(0).GetFile()) ||    (right.get(0).GetLine() == Topovots.get(i).get(j).get(0).GetLine()) && right.get(0).GetFile() == Topovots.get(i).get(j).get(0).GetFile())\r\n" + 
				"{\r\n" + 
				"    return;\r\n" + 
				"}```",
				"When I can almost recite the 15 minute version of Rapper's Delight, but I can't remember what functional requirements are",
				"But is being on fire a disease?");
		quotes.put("hali", haliQuotes);
		quotes.put("emma", emmaQuotes);
		quotes.put("denton", dentonQuotes);
	}
	
	@Override
	public List<String> createInvokers()
	{
		return Arrays.asList("hali", "emma", "denton");
	}
	
	@Override
	public List<String> createDescriptions()
	{
		return Arrays.asList("sends a random quote from Hali","sends a random quote from Emmanuel","sends a random quote from Denton");
	}
	
	@Override
	public void commandReceived(MessageReceivedEvent e, String invoker, StringTokenizer st)
	{
		List<String> messages = quotes.get(invoker);
		if (messages == null)
		{
			tsukihi.print(invoker + " invokes the quotes module, but quotes module has no such quote author");
			return;
		}
		int upper = messages.size();
		Random rand = new Random();
		int n = rand.nextInt(upper);
		e.getChannel().sendMessage("\"" + messages.get(n) + "\"\n      - " + invoker).queue();
	}
}