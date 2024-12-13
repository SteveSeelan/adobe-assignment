package com.AdobeAssignment.utilities;

import com.AdobeAssignment.constants.Constants;
import org.apache.commons.cli.*;

public class CliOptions {
    private final CommandLine cmd;

    public CliOptions(String[] args) {
        Options options = getOptions();

        CommandLineParser parser = new DefaultParser();
        HelpFormatter formatter = new HelpFormatter();

        try {
            cmd = parser.parse(options, args);
            if (cmd.hasOption("h")) {
                formatter.printHelp("./deduplicate.sh", options, true);
                System.exit(0);
            }

            System.out.println("Command args provided: " + cmd.toString());
        } catch (ParseException e) {
            System.out.println(e.getMessage());
            formatter.printHelp("./deduplicate.sh", options, true);
            throw new IllegalArgumentException("Invalid arguments provided", e);
        }
    }

    private static Options getOptions() {
        Options options = new Options();
        Option help = new Option("h", Constants.CLIOptions.HELP, false, "display help message");
        Option input = new Option("i", Constants.CLIOptions.INPUT, true, "input file path");
        Option output = new Option("o", Constants.CLIOptions.OUTPUT, true, "output file path");

        input.setRequired(false);
        output.setRequired(false);
        help.setRequired(false);

        options.addOption(help);
        options.addOption(input);
        options.addOption(output);
        return options;
    }

    public String getInputFilePath() {
        return cmd.getOptionValue(Constants.CLIOptions.INPUT, "leads.json");
    }

    public String getOutputFilePath() {
        return cmd.getOptionValue(Constants.CLIOptions.OUTPUT, "deduped.json");
    }
}
