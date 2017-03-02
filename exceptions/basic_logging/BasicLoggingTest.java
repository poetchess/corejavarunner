package basic_logging;

import java.util.logging.*;
import java.io.*;

public class BasicLoggingTest {

    //Create or receive a logger.
    //A logger that is not referenced by any variable can be garbage collected.
    //To prevent this, save the reference with a static variable.
    private static final Logger logger = Logger.getLogger("InfoLogging");
    private static final Logger logger_ganrantee = 
                                        Logger.getLogger("InfoLoggingG");

    //Logger parents and children share certain properties.
    //i.e. If we set the log level on the logger "com.myapp", the child loggers
    //inherit that level.
    //There are 7 logging levels: SEVERE, WARNING, INFO,
    //                            CONFIG, FINE, FINER, FINEST
    //By default, the first three levels are actually logged.
    //Set a different level using logger.setLevel(Level.###);
    public static void main(String[] args) {

        //Like loggers, handlers have a logging level. For a record to be
        //logged, its logging level must be above the threshold of both the
        //logger and the handler.
        logger.setLevel(Level.FINER);

        //By default, a logger sends records both to its own handlers and the
        //handlers of the parent. We don't want to see those records twice, so
        //set the useParentHandlers property to false.
        logger.setUseParentHandlers(false);
        Handler handler = new ConsoleHandler();
        handler.setLevel(Level.FINER);
        logger.addHandler(handler);

        logger.entering("Class: BasicLoggingTest", "Method: main");

        //This will show the name of the class and method that contains the
        //logging call. However, this is not ganranteed since the VM may 
        //optimize execution.
        logger.info("Logging an INFO-level message");

        //'logp' method can give precise location of the calling class and 
        //method.
        logger_ganrantee.logp(Level.INFO, "Class: BasicLoggingTest", 
                    "Method: main", "Logging an INFO-level message");

        logger.exiting("Class: BasicLoggingTest", "Method: main");
    }
}
