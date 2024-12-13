#!/bin/bash

# Ensure the Gradle wrapper exists
if [ ! -f "./gradlew" ]; then
  echo "Gradle wrapper not found. Please ensure it is present in the project directory."
  exit 1
fi

# Build the project if not built
BUILD_DIR="build"
if [ -d "$BUILD_DIR" ]; then
    echo "Build directory found. Skipping build."
else
    echo "Build directory not found. Running Gradle build..."
    ./gradlew build
fi

# Check if build was successful
if [ $? -ne 0 ]; then
  echo "Build failed."
  exit 1
fi

echo "Build successful."

# Run the program with arguments
echo "Running the program..."
if [ $# -eq 0 ]; then
    # If no arguments, pass an empty string
    ./gradlew run --args=" "
else
    # Otherwise, pass all arguments
    ./gradlew run --args="$*"
fi

# Check if the program ran successfully
if [ $? -ne 0 ]; then
  echo "Program execution failed."
  exit 1
fi

echo "Program executed successfully."
