# File Deleter Application

## Purpose Of This Application

Sometimes we need to delete files of similar extension. These files may stay at different directory. Then it becomes annoying to go to each
directory and find if targeted file found and then delete it. So this application needs to show the top most directory the required file
type may stay, and also needs the file type you want to delete. The file will delete each and every file it finds recursively from the directory
you give as input.

## Getting Started

A jar has been pushed in the repository. You need this jar. Then you need to run this jar.

## How To Run

Java needs to be installed on your machine. Go to the directory the jar file at, then run this command:

>  java -jar FileDeleterApp.jar /Top/Most/Directory File_type

#### Example

>  java -jar FileDeleterApp.jar /usr/local .txt

So if you run like this, it will search all the child directory of /usr/local and will delete all the .txt file if it finds.
