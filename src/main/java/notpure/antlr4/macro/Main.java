package notpure.antlr4.macro;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;

/**
 * Application entry-point.
 */
public final class Main {

    /**
     * Project URL.
     */
    private static final String PROJECT_URL = "https://github.com/PureCS/antlr4-macro";
    private static final String PROJECT_URL_2 = "https://github.com/HappySad-/antlr4-macro";
    
    /**
     * Project version.
     */
    private static final String PROJECT_VERSION = "v1.0.1-beta.2a";

    public static void main(String[] args) {
        try {
            Options options = commandLineOptions();
            
            CommandLine line = new DefaultParser().parse(options, args); //Wrap in try catch

            if (line.hasOption("url")) {
                System.out.println("Original Project url: " + PROJECT_URL);
                System.out.println("Current Project url: " + PROJECT_URL_2);
            }

            if (line.hasOption("version")) {
                System.out.println("Project version: " + PROJECT_VERSION);
            }

            if (line.hasOption("minify")) {
                CommandLineFlags.minify = true;
            }

            if (line.hasOption("recursive")) {
                CommandLineFlags.recursive = true;
            }
            
            if (line.hasOption("t")) {
            	CommandLineFlags.threadLimit = Integer.parseInt(line.getOptionValue("t"));
            }
            
            if (line.hasOption("v")) {
            	//TODO: Implement a verbose output
            }
            
            if (line.hasOption("o")) {
            	String outputDirectory = line.getOptionValue("o");
            	
            	if(outputDirectory == null) {
            		System.out.println("Missing output directory argument for: -o --output <arg>");
            	} else {
            		CommandLineFlags.outputDirectory = outputDirectory;
            	}
            }

            if (line.hasOption("help")) {
                new HelpFormatter().printHelp("antlr4-macro", options);
            }

            if (line.hasOption("i")) {
                String targetInput = line.getOptionValue("i");

                // Process input
                if (targetInput == null) {
                    System.out.println("Missing target input argument for: -i,--input <arg>");
                } else {
                	
                	if (targetInput.endsWith("/")) { // Process directory
                		MacroFileProcessor.processDirectory(System.getProperty("user.dir") + targetInput);
                	} else if (targetInput.equals(".")) { // Process working directory
                        MacroFileProcessor.processDirectory(System.getProperty("user.dir"));
                    } else { // Process single file
                        // Get file name
                        String inFileName = args[1];

                        // Process file
                        MacroFileProcessor.processFile(inFileName);
                    }
                }
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    /**
     * Command-line options.
     */
    private static Options commandLineOptions() {
        Options options = new Options();
        options.addOption("h", "help", false, "prints this message");
        options.addOption("url", false, "prints the project url");
        options.addOption("version", false, "prints the version information");
        options.addOption("m", "minify", false, "minify output grammar");
        options.addOption("i", "input", true, "processes the given file(s)");
        options.addOption("r", "recursive", false, "processes the given directory recursively");
        options.addOption("o", "out", true, "defines the output file name / directory");
        options.addOption("t", true, "defines the thread limit (default is 2)");
        options.addOption("v", "verbose", false, "displays a verbose output");
        return options;
    }


    /**
     * Command-line parsed flags.
     */
    public static final class CommandLineFlags { //Change name? Since flag is now misleading as having a String in here instead of all booleans...

        /**
         * If output grammars should be minified.
         */
        public static boolean minify;
        /**
         * If the target directory is to be processed recursively.
         */
        public static boolean recursive;
        
        /**
         * Defines the output directory of the output file.
         */
        public static String outputDirectory;
        
        public static int threadLimit = 2;
    }
}
