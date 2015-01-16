# Tiger Direct
ICS3U project

This is an offline version of the webstore [TigerDirect](http://www.tigerdirect.ca). Includes adding items to the shopping cart, manipulating the cart, and an employee interface to add/remove items, change the stock of items, and employee settings, such as adding new employees and changing their permissions, plus changing usernames and passwords.

If you are our teacher, your username is `klaassen` and your password is `teacher`.

##Setup
To setup this repository, either clone it using `git clone https://github.com/TheMacMini09/Tiger-Direct.git` in the directory you want to save it in, or download the zipball from the right-hand side of the page. Then install [Netbeans](https://netbeans.org/downloads/) (either Java SE, Java EE, or All). Once Netbeans is installed, open the project using File -> Open Project and navigate to where you downloaded the repository. Click Run -> Clean & Build Project to compile the sources. You can then run using the green triangle, or (once navigated to the directory where the project is saved/build/classes) `java -cp build/classes/ tiger.direct.TigerDirect`. Running in a terminal interface will be faster, but running in Netbeans is more convenient.

##Install
If you want, you can compile the .class files into a .jar (more convenient to run). Go to the directory the repository was saved, and run `java cf TigerDirect.jar build/classes/*`. This will create TigerDirect.java in the root of the project, which can be run with `java -jar TigerDirect.jar`.
