package logger;

import java.util.logging.FileHandler;
import java.util.logging.Formatter;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class FileLogger {
	
	private static FileLogger fileLogger;
	private static Logger logger;
	private static FileHandler fileHandler;
	private static Formatter formatter;
	
	public FileLogger() {
		
		if (fileLogger != null) {
			return;
		}
		
		try {
			fileHandler = new FileHandler("C:\\Users\\lvonnied\\eclipse-workspace\\Swing Address book\\FileLog.log");
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		formatter = new SimpleFormatter();
		fileHandler.setFormatter(formatter);
		
		logger = Logger.getLogger("FileLogger");
		logger.addHandler(fileHandler);
		//logger.setUseParentHandlers(false);
		fileLogger = this;
		
	}
	
	public Logger getLogger() {
		
		return FileLogger.logger;
		
	}

}
