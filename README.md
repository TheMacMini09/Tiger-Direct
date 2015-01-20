# Tiger Direct
ICS3U project

This is an offline version of the webstore [TigerDirect](http://www.tigerdirect.ca). Includes adding items to the shopping cart, manipulating the cart, and an employee interface to add/remove items, change the stock of items, and employee settings, such as adding new employees and changing their permissions, plus changing usernames and passwords.

If you are our teacher, your username is `klaassen` and your password is `teacher`. This is a level-limited account, to demonstrate the abilities of a limited account. If you want a full account, you can go into the `authentication` file and log in with one of our logins (formatted to be: `ID` + `username` + `password` separated by `;;`), or modify the `3` at the end of your line to a 6.

##Setup
To setup this repository, either clone it using `git clone https://github.com/TheMacMini09/Tiger-Direct.git` in the directory you want to save it in, or download the zipball from the right-hand side of the page. Then install [Netbeans](https://netbeans.org/downloads/) (either Java SE, Java EE, or All). Once Netbeans is installed, open the project using File -> Open Project and navigate to where you downloaded the repository. Click Run -> Clean & Build Project to compile the sources. You can then run using the green triangle, or (once navigated to the directory where the project is saved/build/classes) `java -cp build/classes/ tiger.direct.TigerDirect`. Running in a terminal interface will be faster, but running in Netbeans is more convenient.

##Install
If you want, you can compile the .class files into a .jar (more convenient to run). Go to the directory the repository was saved, and run `java cf TigerDirect.jar build/classes/*`. This will create TigerDirect.java in the root of the project, which can be run with `java -jar TigerDirect.jar`.

##Contributions
To determine the contributions made by the different team members, look at the header of each file, or look at the [GitHub commits](https://github.com/TheMacMini09/Tiger-Direct/commits/master). You can see who made what commits (changes to the code), and what the commits actually were. Clicking on the title of the commit will show how many files were changed and how many lines were added/removed. TheMacMini09: Michael Barker, MaximusTheGreat: Max Farfaras, ThisDaySucks: Nigel Young. For example, if you go to the commit where [I changed the README](https://github.com/TheMacMini09/Tiger-Direct/commit/e9328731011e25e4f497fb8387233fd01922fc74) you can see that I changed one file, added 16 lines, and removed 14 lines.