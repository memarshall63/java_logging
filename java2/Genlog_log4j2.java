/* Genlog.java
 */

import org.apache.logging.log4j.*;
import com.splunk.logging.*;

import java.lang.Math;
import java.util.Random;
import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;

/*import org.json.simple.JSONObject;*/

class Genlog
{
	private static final Logger logger = LogManager.getLogger("splunklogger");  
	private static final Level AUDIT = Level.forName("AUDIT",100);

	public static void main(String args[])
		throws InterruptedException 
	{
		String levels[] = {
			"DEBUG",
			"INFO",
			"WARN",
			"ERROR",
			"FATAL",
			"TRACE"
		};
		String messages[] = {
			"Debugging Message",
			"Informational Message",
			"Warning Message",
			"Error Message - Recovering",
			"Fatal Error Message - Action Required",
			"AUDIT: This message must be written to the logs, no exceptions. It has a 100 Level - same level as FATAL"
		};

		/* Build a JSON object 
		JSONObject obj = new JSONObject();
		obj.put("name", "foo");
		obj.put("num", new Integer(100));
		obj.put("balance", new Double(1000.21));
		obj.put("is_vip", new Boolean(true));
		*/

		logger.info("logger level set to: "+logger.getLevel());
		int limit = getRandomInteger(10,25);
		int level = getRandomInteger(0,5);
		int isleep = getRandomInteger(1,5);

		for (int i=1; i<limit; i++)
		{
			String error_msg;
			error_msg = "{\"@ts\":\"2020-10-31T12:15:30.0123456Z\"," + 
				"\"msg\":\"" + messages[level] + "\"}";

			switch(level)
			{
				case 0:
					logger.debug("Invalid JSON Message Sent: " + error_msg);
					break;
				case 1:
					logger.info(error_msg);
					break;
				case 2:
					logger.warn(error_msg);
					break;
				case 3:
					logger.error(error_msg);
					break;
				case 4:
					logger.fatal(error_msg);
					break;
				case 5:
					logger.log(AUDIT, error_msg);
					break;
				default:
					logger.error("DEFAULT: ("+level+") "+error_msg);
			}

			/*System.out.println(error_msg);*/
			Thread.sleep(isleep*1000);
			level = getRandomInteger(0,5);
			isleep = getRandomInteger(1,5);
		}
	}
	public static int getRandomInteger(double min, double max)
	{
		String this_method = Thread.currentThread().getStackTrace()[1].getMethodName();
		logger.traceEntry(this_method);
		double x = (int)(Math.random()*((max-min)+1))+min;
		logger.traceExit(x);
		return (int) x;

	}
}
