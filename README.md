# CPSC 210 Personal Project by Tong Liu
## Fund Tracking and Donation making App for wildlife conservatories
## Project Proposal:


A wildlife conservatory provides rescue and rehabilitation services to injured wildlife. A large portion of the funds that supports the facility is raised from the public through donations from animal lovers
To encourage donations from the public, many non-profit organizations created websites that contains the profiles of 
individual wildlife they attempt to rescue and encourage the public to make donations to their favorite animals through symbolic adoptions. one of the examples
can be accessed here (https://support.worldwildlife.org/site/Donation2?df_id=17797&17797.donation=form1&s_src=AWE2308OQ18297A06836RX&s_subsrc=homepage)


In my personal project, I want to design a donation management app for a newly established wildlife conservation facility. 
The app can be used to add and create profiles for wildlife that are rescued and sheltered at the facility and updates their donation records, list of donors
and the total amount of donations (funds). This app will allow the donors to make donations to a certain wildlife they choose. The same donor
will be able to track his/her donation records by using his donor profile ID. 

This project is particularly interesting to me as it can help the conservation facility to systematically track and manage the funding they collected from the public.
The data (records) it collected can be later used to  create new strategy to attract public donations.

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
- A wildlife (EN0642) becomes fully funded, it is added to the list of fully funded wildlife

- A donation ($500.0, wildlife ID: CR9619) is added to Tong's donation list
- A donation ($500.0, wildlife ID: CR9619) is added to CR9619's donation list
- Donor (Tong) is added to CR9619's donor list

## Phase 4: Task 3
- There are several refactoring I can perform to improve the cohesion and reduce the coupling of my program
- Firstly, In the Wildlife class and Donor class, there exist a 


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


