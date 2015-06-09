**Note:** These are the unformatted raw instructions for this lesson. If you
want to present this as a lesson to students, please format it nicely yourself
This lesson has not yet been added to my
[teaching resources](http://samlanning.github.io/teaching-resources/) web
pages.

## Getting Started

Create a new Java Project, and call it something like `Text Adventure Game`.
Then inside the `src` folder that is created, lets make a new package, and call
it `adventure_game`. We are going to store all of our program files inside of
this package.

Create a new **class** inside the package `adventure_game`, and call it `Game`.
Once you have created the class, it should look like this:

    package adventure_game;

    public class Game {
    
    }

Inside the new class (between the curly brackets `{ }`), write the following:

    public static void main(String[] args){
        System.out.println("Welcome to the text adventure game.");
    }

You can now automatically format your code by typing `Ctrl + Shift + F`. You
should now be able to run your code, hit the green play button at the top of
your screen to do so.

## Getting User Input

For now, users will be able to interact with our program using the command line
and entering commands, and seeing text input as response, but in the future, we
may want to extend our program to allow for a nicer way for users to interact
with the program.

To allow for this to be easily changed in the future, we are going to keep all
interaction with the user in a single location so that we can change it later.

Create a new package called `ui` (this stands for user interface) inside the
package `adventure_game`. And inside this new package, create a new `interface`
called `UserInterface`. This new file should look like this:

    package adventure_game.ui;

    public interface UserInterface {
    
    }

**Note:** In particular, the package at the top of the file should say
`adventure_game.ui`. If not, then you may have created the interface in the
wrong place, or created the package `ui` in the wrong place.

Inside this new interface, write the following:

    public String getStringFromUser(String prompt);
    
    public void sendTextToUser(String message);

And make sure you save your changes.

This interface now describes what our program will need from something that
interacts with our user, now lets create an **implementation** that uses the
command line to do this. Also inside the `ui` package, create a new class and
call it `CommandLineUserInterface`.

After the file has been created, on the line which says
`public class CommandLineUserInterface {`, before the opening bracket, write:

    implements UserInterface

If you have done this correctly, you should now have an error in this new file.
If you hover over the **class name** (the bit which is underlined red), it will
give you details on what the error is. Here it should read something like
`The type CommandLineUserInterface must implement the inherited abstract
method...`. What this error means is that this new class we have created does
not do everything it is required to do as an implementation of `UserInterface`.
If you stay hovered over the error, and then click `Add unimplemented methods`,
some code should be generated for you automatically, and your file should now
look like this:

    package adventure_game.ui;

    public class CommandLineUserInterface implements UserInterface {

        @Override
        public String getStringFromUser(String prompt) {
            // TODO Auto-generated method stub
            return null;
        }

        @Override
        public void sendTextToUser(String message) {
            // TODO Auto-generated method stub
        
        }
    
    }

Eclipse has now done its best to create code that fulfill the requirements of
the `UserInterface` interface. These **methods** that it has created, as it
currently stands, do not do much. Lets change this.

Above the two methods that were automatically created, write the following
line:

    private final Scanner scanner = new Scanner(System.in);

Eclipse should mark this line as having errors, and underline both places where
`Scanner` is used, this is because we haven't yet imported `Scanner` into this
file. We can do this automatically, hover over one of these places to see the
error details, and then click `Import 'Scanner' (java.util)`.

In the `getStringFromUser()` method, remove the code that is already there, and
write the following:

    if(prompt != null)
        System.out.print(prompt + " ");
    return scanner.nextLine();

And inside the `sendTextToUser()` method, remove the comment that is already
there and write:

    System.out.println(message);

Make sure you save your changes, this class is now ready for use, it should
look like this:

    package adventure_game.ui;

    import java.util.Scanner;

    public class CommandLineUserInterface implements UserInterface {
        
        private final Scanner scanner = new Scanner(System.in);

        @Override
        public String getStringFromUser(String prompt) {
            if (prompt != null)
                System.out.print(prompt + " ");
            return scanner.nextLine();
        }

        @Override
        public void sendTextToUser(String message) {
            System.out.println(message);
        }
    
    }

**Note:** remember to hit `Ctrl + Shift + F` to reformat your code.

## Testing our new classes

Go back to the `Game.java` file, and inside the `main()` method, remove the
previous code, and write the following:

    UserInterface iface = new CommandLineUserInterface();

Here it underlines parts of the code red again, in this case, it underlies
`UserInterface` and `CommandLineUserInterface`. Because these two files are in
a different package to the current file (the sub-package `ui`), we need to
import them like we imported `Scanner` earlier. So hover over each of these two
errors, and click the quick fix that beginds with **import**. This should no
longer have any errors.

Under this new line, write the following:

    iface.sendTextToUser("Welcome... Wait...");
    String name = iface.getStringFromUser("What is your name?");
    iface.sendTextToUser("Welcome " + name + "!");

Save your files and run the program. You should be able to enter some text when
it asks for your name, like so:

    Welcome... Wait...
    What is your name? Bob Jenkins
    Welcome Bob Jenkins!
