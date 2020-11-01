# ShoppingApp
### 1) High Level Design

  - Developed an Android application, which allows users to search through the eBay items catalog and look at the detailed information about them. <br/> 
  - The App contains 5 screens: Initial Form, Item Catalog, Product Information screen, Seller Information screen and Shipping Information screen.
  - This app has been designed and implemented in a Pixel 2XL emulator by using SDK API 29 (Android Studio)
  - Used ebay Developers API, Node.js backend deployed on GCP, 
  
  ### 2) Implementation Design
  
 ##### App Icon and Splash Screen 
 
 - The app begins with a welcome screen (see Figure below) which displays the icon of the application
 ![image](https://user-images.githubusercontent.com/24832637/97813079-ac571080-1c3a-11eb-9a25-c7fba302427b.png)
 
 ##### Initial Search Form
 - When you open the app, there will be an initial form displayed on the screen as shown in Figure below.
 - The initial form contains the some filters as follows. The fields are: </br>
    • Keyword field – Mandatory </br>
    • Price Range – Minimum and Maximum Price fields </br>
    • Condition – 3 checkboxes, for New, Used and Unspecified</br>
    • Sort By criteria – 4 options, name Best Match, Price: Highest first, Price +
                          Shipping: Highest first, Price + Shipping: Lowest first
