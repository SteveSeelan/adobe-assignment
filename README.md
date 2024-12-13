# adobe-assignment

Programming Challenge:
Take a variable number of identically structured json records and de-duplicate the set.


An example file of records is given in the accompanying 'leads.json'.  Output should be same format, with dups reconciled according to the following rules:
1. The data from the newest date should be preferred.
2. Duplicate IDs count as dups. Duplicate emails count as dups. Both must be unique in our dataset. Duplicate values elsewhere do not count as dups.
3. If the dates are identical the data from the record provided last in the list should be preferred.


Simplifying assumption: the program can do everything in memory (don't worry about large files).


The application should also provide a log of changes including some representation of the source record, the output record and the individual field changes (value from and value to) for each field.


Please implement as a command line program.

### Notes:

I would add more logging capabilities usually. Debugging levels like
trace, info, none, etc. I would have these logs outputted into a file. I 
mostly wanted to demonstrate at a high level how I program.

## Requirements
- **Java**: 
  - openjdk 23 2024-09-17
    OpenJDK Runtime Environment Temurin-23+37 (build 23+37)
    OpenJDK 64-Bit Server VM Temurin-23+37 (build 23+37, mixed mode, sharing)
  - (check with `java -version`).
- **Gradle**: Version 8.11 or higher (check with `./gradlew --version` or install using Gradle Wrapper).
- **Command-line Access**: Ability to execute shell scripts and commands.

---

## Setup Instructions

### 1. Clone the Repository
```bash
git clone https://github.com/SteveSeelan/adobe-assignment.git repo-name
cd repo-name
```

## How To Run
Example:

    ./deduplicate.sh -i <pathToJSONFile> -o <outputPathForDeDuplication>

The application runs with parameters:
    
    -h, --help: displays help message and usage
    -o, --output: outputFilePath(default: dededuped.json)
    -i, --input: inputFilePath(default: leads.json)
    ./deduplicate.sh --help

