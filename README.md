# command-line-driven-text-search-engine

The exercise is to write a command line driven text search engine in the language you feel most
comfortable with.
Examples of the usage would be:
python simple_search.py ​<pathToDirectoryContainingTextFiles>
or
java​ -jar ​<jarName>​ ​<mainClassFile>​ ​<pathToDirectoryContainingTextFiles>
This should read all the text files in the given directory, building an ​in-memory​ representation of the files
and their contents, and then give a command prompt at which interactive searches can be performed.
An example session might look like:
$ java -jar SimpleSearch.jar Searcher /foo/bar
14 files read in directory /foo/bar
search> to be or not to be
file1.txt:100%
file2.txt:90%
search>
search> cats
no matches found
search> :quit
$
The search should take the words given on the prompt and return a list of the top 10 (maximum)
matching filenames in rank order, giving the rank score against each match.
Note:​ ​ ​We would like to see a working console application, please focus your attention on the search
algorithm and the basic console functionality (you can assume that the input strings are sane).
  
## Rank
  
  The rank score must be 100% if a file contains all the words
  
● It must be 0% if it contains none of the words

● It should be between 0 and 100 if it contains only some of the words, but the exact ranking

formula is up to you to choose and implement

## How to

The application was built using Java11 and streams-lambda feataures, also has implemented unit test for the services classes using mockito and junit 5.

## How to run the application

1. In the main directory run the command ```mvn clean install```, with this command you'll generate a jar file in the ```target``` directory.
2. You can copy and paste this .jar file ```(command-line-driven-text-search-engine-0.0.1.jar)``` in any directory you want and you must create a folder called ```resources```.
3. Open a terminal in the folder and execute the command: ```java -jar command-line-driven-text-search-engine-0.0.1.jar resources```.
4. You can start searching the words you want, by writing them in the console. You must see a console with the search> word, and you can start typing the words that you want to look for inside the files.


