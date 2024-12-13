package com.AdobeAssignment;

import com.AdobeAssignment.utilities.CliOptions;

public class Main {
    public static void main(String[] args) {
        CliOptions cliOptions = new CliOptions(args);
        App app = new App(cliOptions);
        app.run();
    }
}