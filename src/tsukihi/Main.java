package tsukihi;
import quote.QuoteModule;

/**
 * 
 * @author joshuafloh
 *
 */
public class Main
{
	public static void main(String[] args) throws Exception
	{
		Tsukihi tsukihi = new Tsukihi();
		new QuoteModule(tsukihi);
		new HelpModule(tsukihi);
		new ErrorModule(tsukihi);
	}
}
