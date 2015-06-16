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

Eclipse has now done its best to create code that fulfills the requirements of
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

## The Main Command Loop

Our program is going to run in a loop, continuously asking the user for the
next command that they wish to do. We will keep all of the logic for our game
flow inside a new class `PlayerRunThrough`, which will represent a single run
of the game, and keep track of a player's information using a class called
`Player`.

First create a new class called `Player` in the `adventure_game` package, and
once this class is created, inside the curly braces, write the following:

    private final String name;

    public Player(String name){
        this.name = name;
    }

    public String getName(){
        return name;
    }

Let's examine this new code section, by, section.

    private final String name;

The first line of this code says that every instance of `Player` has a property
called `name` that is a string. This property is `private` (meaning that only
code inside the `Player` class can see it), and `final`, which means once it is
set it can't be changed.

    public Player(String name){
        this.name = name;
    }

This code is what is called the constructor, it is a special kind of
**method** that is called only when we create a new instance of a `Person`. You
can tell that it is a constructor because it has **the same name as the
class**. This constructor takes a `String` parameter called `name`, which is
stores as the property `name`.

    public String getName(){
        return name;
    }

This final piece of code is also a **method**. This method fetches the
`private` field `name` and returns it, effectively making it available to code
**outside** of the `Person` class.

Create a new class called `PlayerRunThrough` inside the package
`adventure_game`, and inside this new class write the following:

    public static void performRunThrough(UserInterface iface, Player player){
        iface.sendTextToUser("Starting Game...");
        iface.sendTextToUser("Welcome " + player.getName());


    }

`UserInterface` will be underlined because it is not yet imported. Like before,
hover over it and select the option that begins `Import ...`.

Now we will use these two new classes in the main `Game` class.

Switch back to `Game.java` and **remove** the lines that say:

    iface.sendTextToUser("Welcome... Wait...");
    String name = iface.getStringFromUser("What is your name?");
    iface.sendTextToUser("Welcome " + name + "!");

And instead **write** the following:

    String name = iface.getStringFromUser("What is your name?");

    Player player = new Player(name);
    PlayerRunThrough.performRunThrough(iface, player);

    iface.sendTextToUser("Game finished");

When running your game, you should now get something that looks like this:

    What is your name? Bob
    Starting Game...
    Welcome Bob
    Game finished

Lets go back to `PlayerRunThrough.java`. This class does not do much currently,
so lets make it more interesting and introduce the main command loop.

Under the existing code inside your method `performRunThrough()`, write the
following:

    while(true){
        String line = iface.getStringFromUser("> ");

        if(line.equals("exit")){
            return;
        }

        if(line.equals("help")){
            iface.sendTextToUser("  Commands:");
            iface.sendTextToUser("   - help: show this message");
            iface.sendTextToUser("   - exit: end the game");
            continue;
        }

        iface.sendTextToUser("Unrecognized Command! type: help");
    }

Try running yor program now, when your game starts you should be able to enter
in a number of commands. It might look like so:

    What is your name? Bob
    Starting Game...
    Welcome Bob
    Type help to get started
    >  help
      Commands:
       - help: show this message
       - exit: end the game
    >  walk
    Unrecognized Command! type: help
    >  exit
    Game finished

## Creating a Map

The next stage for our game is building up a map and allowing the player to
move around!

Create a new package `map` inside the package `adventure_game`. And inside this
package, create two new classes, called `Location` and `Map`.

Inside the body of the `Location` class (that's between the curly brackets),
write the following:

    public final String name;
    private String description;

    public Location north;
    public Location east;
    public Location south;
    public Location west;

    public Location(String name){
        this.name = name;
    }

    public void setDescription(String description){
        this.description = description;
    }

    public String getDescription(){
        return this.description;
    }

And inside the body of the `Map` class, write the following:

    private final Location startLocation;

    public Map() {

        // Create Locations

        Location villageSquare = new Location("The Village Square");
        villageSquare.setDescription("I think ball games are banned here?");
        this.startLocation = villageSquare;

        Location waterfall = new Location("The Village Waterfall");
        waterfall.setDescription("I love listening to the sound of the water");

        Location caves = new Location("The Caves");
        caves.setDescription("It's kinda strange that we have caves in such a small village");

        // Join Locations Together
        joinLocationsVertically(waterfall, villageSquare);
        joinLocationsHorizontally(waterfall, caves);
    }

    private void joinLocationsHorizontally(Location west, Location east){
        east.west = west;
        west.east = east;
    }

    private void joinLocationsVertically(Location north, Location south){
        north.south = south;
        south.north = north;
    }

    public Location getStartLocation() {
        return startLocation;
    }

We now have a map with 3 locations within it which we can start to explore with
our game.

## Looking Around and Moving Around the Map

Go back to `PlayerRunThrough.java`, and make the following changes:

* **Above the while loop:** write this:

        Map map = new Map();
        Location currentLocation = map.getStartLocation();

* **Inside the code that writes the help message:** add these extra two lines:

        iface.sendTextToUser("   - look around: inspect the area around you");
        iface.sendTextToUser("   - move: move your character");

  These help messages will be for the two extra commands we are about to add.

* **Inside the while loop, after the last `if` statement**, write the
  following:

        if(line.equals("look around")){
            lookAround(iface, currentLocation);
            continue;
        }

* **After the `performRunThrough()` method**, write this:

        private static void lookAround(UserInterface iface, Location location){
            // Talk about current location
            iface.sendTextToUser("  You Look around...");
            iface.sendTextToUser("  You are at " + location.name);
            String description = location.getDescription();
            if(description != null)
                iface.sendTextToUser("    '" + description + "'");

            // Describe the locations around you.
            if(location.north != null)
                iface.sendTextToUser("  North of you is: " + location.north.name);
            if(location.east != null)
                iface.sendTextToUser("  East of you is: " + location.east.name);
            if(location.south != null)
                iface.sendTextToUser("  South of you is: " + location.south.name);
            if(location.west != null)
                iface.sendTextToUser("  West of you is: " + location.west.name);
        }

### Test The Code So Far

Run the code, and you should now be able to inspect the map around you *(but
not yet move around!)*. For example:

    What is your name? Bob
    Starting Game...
    Welcome Bob
    Type help to get started
    >  help
      Commands:
       - help: show this message
       - exit: end the game
       - look around: inspect the area around you
       - move: move your character
    >  look around
      You Look around...
      You are at The Village Square
        'I think ball games are banned here?'
      North of you is: The Village Waterfall
    >  move
    Unrecognized Command! type: help
    >  exit
    Game finished

### Add Movement

Now to add the ability to move around the map, **inside the while loop, after
the if statement you wrote in the last step**, write the following:

    if(line.equals("moves")){
        String direction =  iface.getStringFromUser("  Enter a Direction (n,e,s,w) > ");

        if(direction.equals("n")){
            if(currentLocation.north != null)
                currentLocation = currentLocation.north;
            else
                iface.sendTextToUser("There is nothing north of you");
            continue;
        }

        if(direction.equals("e")){
            if(currentLocation.east != null)
                currentLocation = currentLocation.east;
            else
                iface.sendTextToUser("There is nothing east of you");
            continue;
        }

        if(direction.equals("s")){
            if(currentLocation.south != null)
                currentLocation = currentLocation.south;
            else
                iface.sendTextToUser("There is nothing south of you");
            continue;
        }

        if(direction.equals("w")){
            if(currentLocation.west != null)
                currentLocation = currentLocation.west;
            else
                iface.sendTextToUser("There is nothing west of you");
            continue;
        }

        iface.sendTextToUser("Unregognized Direction!");
        continue;
    }

### Test Moving Around

You should now be able to move your player around the map, try testing your
code and exploring the map.

    What is your name? Bob
    Starting Game...
    Welcome Bob
    Type help to get started
    >  help
      Commands:
       - help: show this message
       - exit: end the game
       - look around: inspect the area around you
       - move: move your character
    >  look around
      You Look around...
      You are at The Village Square
        'I think ball games are banned here?'
      North of you is: The Village Waterfall
    >  move
      Enter a Direction (n,e,s,w) >  x
    Unregognized Direction!
    >  move
      Enter a Direction (n,e,s,w) >  n
    >  look around
      You Look around...
      You are at The Village Waterfall
        'I love listening to the sound of the water'
      East of you is: The Caves
      South of you is: The Village Square
    >  exit
    Game finished

## Making Your Own Map

Now it's time to get creative!

Use your imagination to create a much bigger map that you can explore using
the commands that we have just created.

You need to edit the file `Map.java` to do this, all the code for creating the
locations of the map is in the **constructor** (the **method** that has the
same name as the class).

## Cleaning Up the Code

In preparation to making your game more advanced, we should tidy up some parts
so that it makes this easier. Luckily, because Java has software like eclipse
to help you write it, this is made much easier as it can quickly point out
problems with your code and makes moving things around simpler.

* In `PlayerRunThrough.java`, **above the method `performRunThrough()`**, write
  the following:

        private final UserInterface iface;
        private final Player player;
        private final Map map;
        private Location currentLocation;

        public PlayerRunThrough(UserInterface iface, Player player){
            this.iface = iface;
            this.player = player;

            map = new Map();
            currentLocation = map.getStartLocation();
        }

* Next delete both the parameters from the method `performRunThrough()`, and
  remove the `static` keyword, this should result in a line that looks like
  this:

        public void performRunThrough(){

* Then, delete the two lines of code shortly afterwards that look like this:

        Map map = new Map();
        Location currentLocation = map.getStartLocation();

* Delete the two parameters for the method `lookAround()` and remove the
  `static` keyword, this should introduce a few errors:

  * For the errors below the line you just changed, replace anywhere it says
    `location` with `currentLocation`.

  * For the error that was introduced above this line, in the method
    `performRunThrough()` (you can see where the errors are by looking for the
    red markers in the scrollbar), change the line from:

        lookAround(iface, currentLocation);

    to:

        lookAround();

### Extracting a Method

We are now going to move and modify code that is inside the
`performRunThrough()` method, and extract it to another method.

**After the method `lookAround()`**, write the following:

**Note:** This code is mostly similar to code already in your method
`performRunThrough()`, so it will be easier to copy and paste the code instead
of writing it from scratch.

    private void move() {
        String direction = iface.getStringFromUser("  Enter a Direction (n,e,s,w) > ");

        if (direction.equals("n")) {
            if (currentLocation.north != null)
                currentLocation = currentLocation.north;
            else
                iface.sendTextToUser("There is nothing north of you");
            return;
        }

        if (direction.equals("e")) {
            if (currentLocation.east != null)
                currentLocation = currentLocation.east;
            else
                iface.sendTextToUser("There is nothing east of you");
            return;
        }

        if (direction.equals("s")) {
            if (currentLocation.south != null)
                currentLocation = currentLocation.south;
            else
                iface.sendTextToUser("There is nothing south of you");
            return;
        }

        if (direction.equals("w")) {
            if (currentLocation.west != null)
                currentLocation = currentLocation.west;
            else
                iface.sendTextToUser("There is nothing west of you");
            return;
        }

        iface.sendTextToUser("Unregognized Direction!");
    }

**In the methos `performRunThrough()`**, find the **if statement** that is used
for when we type in the command `move`, and **replace it** with the following
code:

    if (line.equals("move")) {
        move();
        continue;
    }

### Try To Run Your Code

If you try to run your code, it should not work. It will instead give a message
like "Errors exist in required project(s)", and if you continue regardless, you
may get a message like this:

    Exception in thread "main" java.lang.Error: Unresolved compilation problem:
        The method performRunThrough() in the type PlayerRunThrough is not applicable for the arguments (UserInterface, Player)

        at adventure_game.Game.main(Game.java:14)

This is because we modified the way in which you have to use the class
`PlayerRunThrough`, and didn't update the parts of the code that use it.

Lets fix this...

### Fixing The Compilation Problem

If you look at your list of files **on your left** (under `Package Explorer`),
you should be able to see a red cross on the file `Game.java`, that means there
is a compilation error there.

Open the file and you will see the line where the problem is, lets fix that
line.

Replace the line:

    PlayerRunThrough.performRunThrough(iface, player);

With:

    new PlayerRunThrough(iface, player).performRunThrough();

### Running The Game

After running the game, it should work exactly as before, even though we
changed a lot of code (because we made sure we kept the same functionality).
