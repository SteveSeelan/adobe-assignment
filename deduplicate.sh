#!/bin/bash

# Ensure the Gradle wrapper exists
if [ ! -f "./gradlew" ]; then
  echo "Gradle wrapper not found. Please ensure it is present in the project directory."
  exit 1
fi

# Build the project
echo "Building the project using Gradle..."
./gradlew build

# Check if build was successful
if [ $? -ne 0 ]; then
  echo "Build failed."
  exit 1
fi

echo "Build successful."

# Run the program with arguments
echo "Running the program..."
./gradlew run --args="$@"

# Check if the program ran successfully
if [ $? -ne 0 ]; then
  echo "Program execution failed."
  exit 1
fi

echo "Program executed successfully."
