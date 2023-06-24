# CS-360-Portfolio

Portfolio containing the project from CS-360 Mobile Architecture &amp; Programming.

## Briefly summarize the requirements and goals of the app you developed. What user needs was this app designed to address?

With the Inventory App project the goals were to develop an interface and code that allows the user to create a local account on the device and log into the application.  The user account information and the inventory information are stored on separate tables in the SQLite database on the device.

Once logged in the user sees a list of all items currently in the inventory and the associated quantity of each item.  From there the user can then add new items to the inventory or edit existing items.  Either action takes the user to the same item detail screen which has fields for the item name and quantity.  If it is a new item then the fields are blank.  If the user is editing an existing item the fields are preloaded with the existing information. On the detail screen the user is given the option to delete an existing item from the inventory.

## What screens and features were necessary to support user needs and produce a user-centered UI for the app? How did your UI designs keep users in mind? Why were your designs successful?

The register/sign in screen will be the entry point for the app. This is where the user will either create their account or authenticate their access by logging in. After logging in the user will be taken to the main inventory screen. This screen lists all the items currently in the warehouse inventory. There will be a floating button in a fixed location on this screen that the user can click to add a new item. Clicking the add button will take the user to the add/edit item screen with fields for them to fill out. Clicking an item from the inventory list will take the user to the add/edit item screen with the fields pre-populated with the values for the item. When adding or editing an item a save button will be shown to save changes. Clicking the back button will cancel any changes. When editing an item an additional remove button will be displayed as well.  This simple design and layout will allow the user to easily navigate the app and perform the necessary actions.  Because of this the design is successful.

![Inventory mobile app screen flow]()

## How did you approach the process of coding your app? What techniques or strategies did you use? How could those be applied in the future?

When it came time to start coding the app I approached the process by following the normal user flow and starting with the login page.  From there I next worked the Sign Up/Register page.  Once I had completed the database setup and the login and register logic I moved on to the main inventory page.  I started with the layout and then added the logic to populate the list of items.  From there I moved on to the add/edit item page.  I started with the layout and then added the logic to add, update, or delete the item from the database.

## How did you test to ensure your code was functional? Why is this process important and what did it reveal?

In order to make sure everything worked as intended in the app I thoroughly tested the app as I completed each screen.  This process is important because it allows you to catch any bugs or issues as you go along and fix them before moving on to the next screen.  This process revealed an issue with the database schema that I was able to fix before moving on to the next screen.

## Considering the full app design and development process, from initial planning to finalization, where did you have to innovate to overcome a challenge?

As I was implementing the UI I ran into a couple situations where I had to rethink the design that I had originally come up with.  I overcame this challenge by taking a lot at what I had and thinking about how it could be changed to work.

## In what specific component from your mobile app were you particularly successful in demonstrating your knowledge, skills, and experience?

For this project I felt particularly successful of the work I completed with the inventory list screen and the corresponding item detail screen.  I was able to implement the logic to populate the list of items and the logic to add, update, or delete an item from the database.  I was also able to implement the logic to populate the item detail screen with the correct information based on the item selected from the list.
