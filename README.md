#Java Personal Project by Tong Liu
## Fundraising management App for wildlife conservatories
## Project Proposal:


A wildlife conservatory provides rescue and rehabilitation services to injured animals. A large portion of the funds that supports the facility is raised from the public through donations from animal lovers

To encourage donations from the public, many non-profit organizations created websites that contains the profiles of 
animals they attempt to rescue and encourage the public to make donations to their favorite animals through symbolic adoptions. 
one of the examples can be accessed here (https://support.worldwildlife.org/site/Donation2?df_id=17797&17797.donation=form1&s_src=AWE2308OQ18297A06836RX&s_subsrc=homepage).


In my personal project, I want to design a donation management app for a newly established wildlife conservation facility. 
The app can be used to add and create profiles for wildlife that are rescued and sheltered at the facility and updates their donation records, list of donors, and the total amount of donations (funds). This app will allow the donors to make donations to a certain wildlife they choose. Donors will be able to track his/her donation records using his donor profile ID. 

This project is particularly interesting to me as it can help the conservation facility to systematically track and manage the funding they collected from the public.The data (records) it collected can be later used to for research and provides feedback to the conservation activists to enhance the donor user experience.

The app is designed to be used by two types of users:
- Administration team at the conservation site:
They will use the app to add new wildlife profile, track the donation records and funding progression of each wildlife at the facility. 


- People who wish to make donations to the wildlife in the facility:
They will use the app to navigate the list of wildlife in the site and donate to their chosen wildlife.
They will be able to create donor profiles of their own, and track their donation records by using donor ID



User stories:

## Phase 1

- As an admin user I want to be able to add new wildlife to the conservatory
- As an admin user I want to be able to add important information for the added wildlife such as target funding, species name, conservation status, admission date, and a short description
- As an admin user I want to be able to view the list of wildlife in my conservation site
- As an admin user I want to be able to view the list of donors and their information (email address, donor ID, and profile creation date)

- As a donor user I want to be able to make donation to a wildlife in the conservation site
- As a donor user I want to be able to create a donor profile with a donor ID and an email address
- As a donor user I want to be able to view my donation records  

## Phase 2
- As a user I want to be able to actively save the status of my conservatory to file at the main menu (with the save option)
- As a user, I want to be reminded to save the status of my conservatory to file when I quit the program at the main application menu 
- As a user, when I start the application, I want to be able to load the data of my conservatory from file and restore the status (list of wildlife, funding progression, donors list and donation records) of
- my conservatory from previous usages.


## Phase 3

# Instructions for Grader
- There are 3 actions relating to adding a wildlife (creating wildlife profile) to the conservatory
- You can generate the first required action by clicking the **For Admin** button in the main menu;
- You can generate the second required action by clicking **Add a wildlife** button in the **For Admin** panel
- You can generate the third required action by entering the required information (**Species name**, **Admission date**, **Description**, **Target funding**, **Conservatory status**) and clicking **Submit** button 

- You can save the status of my application by clicking the **Save to file** button in the main menu
- You can reload the state of my application by clicking the **Load from file** button in the main menu

- There are 9 visual components in this GUI
- You can locate my first visual component (logo image of the conservatory) at the top left of the frame
- You can locate my second visual component (the background image) on the main manu of the application
- You can locate the remaining 7 visual components (button logo images) at the 7 buttons (**For Admin**, **To Donate**, **Make a donation**, **Track donation records**, **Track donor info**, **Track wildlife info**, **Track wildlife info**, **Back to the main menu**) of the applications

- You can view the list of wildlife by clicking **Track wildlife info** in the **For Admin** page, and selecting the wildlife in the comboBox to access its information
- You can view the list of fully funded wildlife in their IDs by clicking the **Fully funded wildlife** 
- You can view the list of donors by their IDs by clicking **Track donor info** in the **For Admin** page, and selecting the donor in the comboBox to access its information
- You can view the list of the most generous donors (donors who make the highest amount of total donation) by clicking **Most generous donor** 

- You can make donation to a wildlife by clicking **To Donate** button in the main page and **Make a donation** button, then selecting your chosen wildlife and the amount of donations, and clicking **Submit** button
- You can create a donor profile by clicking **Submit** in **Make a donation** page, A window will pop up asking if you have a donor profile. Select No to proceed to the **Create donor profile** page, enter your information and click **Submit** 
- You can view the donation records of a donor by clicking **Track donation records** button, entering the donor ID in the provided textfield, and clicking the **Track donor info** button

## Phase 4 Task 2
# Representative sample events print-out:
- A wildlife (EN0642) is added to the Conservatory 
- A donor profile (Donor ID: Tong) is created and added into the conservatory 
- A donation ($500.0, wildlife ID: EN0642) is added to Tong's donation list
- A donation ($500.0, wildlife ID: EN0642) is added to EN0642's donation list
- Donor (Tong) is added to EN0642's donor list
- A donation ($500.0, wildlife ID: EN0642) is added to Tong's donation list
- A donation ($500.0, wildlife ID: EN0642) is added to EN0642's donation list
- A wildlife (EN0642) becomes fully funded 
- A wildlife (EN0642) is removed from the list of wildlife for funding
- A wildlife (EN0642) is added to the list of fully funded wildlife 

## Phase 4: Task 3
    

    Refactoring 1: Application of singleton design pattern
- In my project, there exists only one instance of Conservatory, and 0...* instances of Wildlife, Donor, and Donation. 
- Therefore, to reduce the expense of instantiating conservatory class and 
- to have easier and more direct accesses to the Conservatory's methods and fields, we can apply singleton design pattern 
- We can achieve this by simply making the constructor of Conservatory class private and implement a static getInstance method, With this implementation,
- all the ui class can access the Conservatory class by using Conservatory.getInstance() to access its fields and perform functional actions (add wildlife, donor into conservatory).


    Refactoring 2: Application of HashMap 
- In my project, I used Array Lists to hold the collections of the model objects (wildlife, donor, donation).
- Since elements in a list can only be accessed using indexes, searching for a specific element in the list with a certain characteristics (i.e. fully funded wildlife) is not efficient and requires much more codes.
- Since these lists are not designed to hold duplicate objects (for example, every donor object in the List<Donor> of Conservatory object should be unique, with unique donor ID), we can use HashMap to hold these object
- using string of ID as the keys (i.e. HashMap<IDString, wildlife>, HashMap<IDString, Donor>) to search for a wildlife or donor.


    Refactoring 3: To avoid tight coupling between model classes
- The UML diagram reveals that Conservatory has association relationships with both Wildlife and Donor classes, while Wildlife has already been associated with Donor (Wildlife has a field List<Donor>). 
- To reduce the coupling among the model classes, we can remove the association relationship between Donor and Conservatory (ie. remove the field List<Donor> in Conservatory class) 
- Instead, the conservatory can reference its list of donors by referencing its Wildlife's List<Donor> ( conservatory.getListOfWildlife().get(index).getListOfDonors()); 
- Similarly, the relationship among Donation, Wildlife and Donor classes can be improved. Wildlife class is associated with both Donor and Donation, while Donor is associated with Donation.
- We can remove the relationship between Wildlife and Donation class (this can be achieved by removing the field List<Donation> in the Wildlife class). 
- To reference the donations that are associated with a wildlife object, we can reference its donor's list of donations (wildlife.getListOfDonors().getListOfDonation().getDonationOf())
- This trade-off will reduce the coupling among the model classes, but increase the time complexity of the searching as we will need to search by iterating nested loops to find the object(O(n) vs. O(n^2))


    Refactoring 4: Application of Observer Patterns
- In my project, the GUI are composed of one JFrame object and multiple JPanel objects: The FundTrackerAppGUI (subtyping JFrame) object to represent the main menu and other components such as AddWildlife, CreateDonorProfile (subtyping JLabel) to represent the menu pages related to a specific function
- In the original design, the contents of these GUI objects do not update immediately when the status of the conservatory (newly added wildlife, donation making, wildlife becomes fully funded, etc.) changes. For example, when the conservatory adds new wildlife, the list of the JCombobox options for selection
- do not update immediately, the refreshComboBoxOptions() was used to update the combobox options. it is only called when the user selects to go to the Add wildlife page from ForAdmin page again.
- Observer pattern design can be applied to improve the 
- To achieve this, we can create a subject class and have Conservatory extending it, and create an Observer interface and have all the ui classes implementing it
- We can then use addObservers to add the ui classes into Conservatory's list of observers and call notifyObservers() to update each GUI class when the conservatory changes state. Each GUI component class will have its own implemented update method
- For example, the refreshComboBoxOptions() of AddWildlife class will reset the list of wildlife available for donation immediately after a new wildlife is added to the conservatory.


## Main source of reference
Tutoring source: https://youtu.be/Kmgo00avvEw 



## Photos Reference list: 
- wildlife Info.jpg (https://www.freepik.com/premium-vector/animal-conservation-logo-design-wildlife-safari-logo-design-inspiration_29775373.htm)
- donor info.jpg (https://iconscout.com/icons/animal-donation)
- add wildlife .jpg (https://iconscout.com/icons/animal-donation)
- AddWildlifeSuccessful.jpg
- donate logo.png (https://www.pngegg.com/en/png-eedma)
- donor info2 .jpg (https://iconscout.com/icons/animal-donation)
- logoWildlifeConservatory250.jpg (https://scalebranding.com/product/15938/)
- make donation.jpg (https://iconscout.com/icons/animal-donation)
- wildlife info.jpg (https://www.freepik.com/premium-vector/animal-conservation-logo-design-wildlife-safari-logo-design-inspiration_29775373.htm)


