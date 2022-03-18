/* Genlog.java
 */

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.json.*;

import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.classic.joran.JoranConfigurator;
import ch.qos.logback.core.joran.spi.JoranException;
import ch.qos.logback.core.util.StatusPrinter;

import java.lang.Math;
import java.util.Random;
import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;
import java.io.PrintWriter;
import java.io.StringWriter;

class Genlog
{
	private static final Logger logger = LoggerFactory.getLogger(Genlog.class);  

	public static void main(String args[])
		throws InterruptedException 
	{
		String levels[] = {
			"DEBUG",
			"INFO",
			"WARN",
			"ERROR",
			"FATAL"
		};
		String messages[] = {
			"Debugging Message",
			"Informational Message",
			"{\"@ts\":\"2020-10-31T12:15:30.0123456Z\", \"msg\":\"Warning Message\"}",
			"Error Message - Recovering",
			"Fatal Error Message - Action Required"
		};


		String message;
		JSONObject json = new JSONObject();
		JSONObject jsonObj = new JSONObject();
		json.put("test1", "value1");

		jsonObj.put("id", String.valueOf(java.util.UUID.randomUUID()));
		jsonObj.put("user_name", "marshall");
		json.put("invoice_id", jsonObj);


		MDC.put("SESSION_ID",json.toString());
		int limit = getRandomInteger(50,100);
		int level;
		int isleep;
		String error_msg;

		for (int i=1; i<limit; i++)
		{
			try {
			level = getRandomInteger(0,5);
			isleep = getRandomInteger(1,5);
			error_msg = messages[level];
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
					logger.error(error_msg);
					MDC.put("SESSION_ID",String.valueOf(java.util.UUID.randomUUID()));
					logger.info("Changed the Context GUID.");
					break;
				default:
					logger.error("DEFAULT: ("+level+") "+error_msg);
				}
			Thread.sleep(isleep*1000);
			}
			catch(Exception ex) {
				StringWriter sw = new StringWriter();
				PrintWriter pw = new PrintWriter(sw);
				ex.printStackTrace(pw);
				logger.error("CAUGHT EXCEPTION:"+sw.toString());
			}

		}
		MDC.remove("SESSION_ID");
		/* Crash at the end!  */
		logger.info("Here comes an uncaught exception.");
		level = 10;
		String crash = messages[level];
	}
	public static int getRandomInteger(double min, double max)
	{
		String this_method = Thread.currentThread().getStackTrace()[1].getMethodName();
		/*logger.traceEntry(this_method);*/
		double x = (int)(Math.random()*((max-min)+1))+min;
		/*logger.traceExit(x);*/
		return (int) x;

	}
}
