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
			"TRACE",
			"FATAL"
		};
		String messages[] = {
			"Debugging Message",
			"Informational Message",
			"{\"@ts\":\"2020-10-31T12:15:30.0123456Z\", \"msg\":\"Warning Message\"}",
			"Error Message - Recovering",
			"Fatal Error Message - Action Required",
			"AUDIT: This message must be written to the logs, no exceptions. It has a 100 Level - same level as FATAL",
			"FATAL: I'm about to blow chunks Unable to invoke factory method in class com.splunk.logging.HttpEventCollectorLog4jAppender for element SplunkHttp: java.lang.NoClassDefFoundError: org/apache/http/HttpEntity java.lang.reflect.InvocationTargetException\n at java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke0(Native Method)\n at java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:62)\n at java.base/jdk.internal.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)"
		};


		logger.info("logger level set to: "+logger.getLevel());
		int limit = getRandomInteger(10,25);
		int level = getRandomInteger(0,6);
		int isleep = getRandomInteger(1,5);

		for (int i=1; i<limit; i++)
		{
			limit=i+5;	/*unlimited */
			String error_msg;
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
					logger.fatal(error_msg);
					break;
				case 5:
					logger.log(AUDIT, error_msg);
					break;
				case 6:
					logger.fatal(error_msg);
					break;
				default:
					logger.error("DEFAULT: ("+level+") "+error_msg);
			}

			/*System.out.println(error_msg);*/
			Thread.sleep(isleep*1000);
			level = getRandomInteger(0,6);
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
