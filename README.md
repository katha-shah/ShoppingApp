# ShoppingApp 
[Demo Link](https://youtu.be/67x7HLHjczA )
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
                          Shipping: Highest first, Price + Shipping: Lowest first </br>
![image](https://user-images.githubusercontent.com/24832637/97813311-18864400-1c3c-11eb-93e4-33402db944ad.png)

###### Product Catalog
- If the results are available for the search, this page displays them. If no matching result is found, it displays "No resultsfound" instead.
![image](https://user-images.githubusercontent.com/24832637/97813588-f261a380-1c3d-11eb-901b-959f195f47db.png)


###### Detailed Product Screen
- On clicking on the particular item card,a new Activity call displays the detailed information of the product.
- This includes the following tabs on the top - Product Summary Tab, Seller Information Tab, Shipping Information Tab
![image](https://user-images.githubusercontent.com/24832637/97813847-2c32aa00-1c3e-11eb-8c4e-cd6897a92192.png)


###### Third Party Libraries
 - Volley HTTP requests</br>
    • Volley can be helpful with asynchronous http requests to load data. You can also use Volley network ImageView to load photos in Google tab. </br>
    • You can learn more about them here:
      • https://developer.android.com/training/volley/index.html
 -  Picasso </br>
  • Picasso is a powerful image downloading and caching library for Android. </br>• See:
      •http://square.github.io/picasso/
                          
