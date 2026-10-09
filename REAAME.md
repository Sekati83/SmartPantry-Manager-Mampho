# Smart Pantry Manager - MAM700 Practical Assignment
**Student:** Mampho H Modikeng 
**Module:** Mobile App Development 700 
**GitHub:** https://github.com/Sekati83/SmartPantry-Manager-Mampho 
**Video Demo:** [Paste your Google Drive video link here - Make it "Anyone with link can view"]

## 1. Project Overview
Smart Pantry Manager is an Android application that helps users manage their pantry inventory, track expiry dates, reduce food waste, and get recipe suggestions based on available ingredients. Built with Java and Android Studio using Room persistence library for offline-first data storage.

## 2. App Functionality & Requirements (20 Marks)
All required screens work and CRUD operations are fully functional:

### Screens Implemented:
- **MainActivity:** Displays all pantry items in RecyclerView, with search and filter (expiring soon)
- **Add/Edit Item:** Form to add new items with name, quantity, category, expiry date (DatePicker with past-date validation)
- **RecipeDetailActivity:** Shows recipes based on pantry items
- **SuggestedActivity:** Suggests recipes using available ingredients to reduce waste
- **SettingsActivity:** Manage categories and app preferences

### CRUD Operations:
- **Create:** Insert new PantryItem into Room database
- **Read:** Retrieve all items via PantryDao.getAllItems() and display in RecyclerView
- **Update:** Edit existing item details
- **Delete:** Swipe-to-delete or delete button with confirmation

App is stable and free of crashes - tested on Pixel 7 Emulator API 34.

## 3. Database Implementation (15 Marks)
**Technology:** Room (SQLite Abstraction) - Offline-first persistence

- **Entities:** `PantryItem.java` (id, name, quantity, expiryDate, category) and `Recipe.java`
- **DAO:** `PantryDao.java` provides queries: getAllItems(), getExpiringItems(), insert(), update(), delete()
- **Database:** `AppDatabase.java` Singleton pattern, version = 2 with migration handling
    - Migration 1 to 2: Added `category` column for better organization
- **Data Persistence:** Data persists between sessions, verified by closing and reopening app
- **Data Model:** Sensible model with primary keys, proper types (Long for dates, String for categories)

Location: `app/src/main/java/com/example/smartpantrymanager/`

## 4. Code Quality & Java Fundamentals (25 Marks)
- **Clean & Commented:** All Java files have header comments explaining purpose and author logic
- **Activities:** MainActivity, RecipeDetailActivity, SettingsActivity, SuggestedActivity - each with lifecycle handling
- **Layouts:** XML layouts use ConstraintLayout and LinearLayout, responsive for mobile screens (activity_main.xml, item_pantry.xml)
- **Intents:** Explicit Intents used for navigation between activities with putExtra for item IDs
- **Adapters:** PantryAdapter extends RecyclerView.Adapter for efficient list display, ViewHolder pattern implemented

## 5. UI/UX Design (10 Marks)
- Interface is usable and consistent - Material Design 3 components
- RecyclerView with CardView for each pantry item showing name, quantity, expiry with color-coded expiry warnings (Red = expired, Orange = expiring in 3 days, Green = fresh)
- DatePickerDialog prevents selection of past dates - improves user experience
- Appropriate layout for mobile screen, tested on multiple screen sizes
- FloatingActionButton for quick add, intuitive icons

## 6. GitHub Version Control (10 Marks)
Regular commits showing incremental development:
- Commit 1: Initial project setup with Room dependencies
- Commit 2: Created PantryItem Entity and PantryDao
- Commit 3: Implemented AppDatabase with migration
- Commit 4: Built MainActivity with RecyclerView and CRUD
- Commit 5: Added expiry validation and UI improvements
- Commit 6: Added documentation comments and README

Complete push history available in Commits tab. This README present.

## 7. How to Run the Project
1. Clone repository: `git clone https://github.com/Sekati83/SmartPantry-Manager-Mampho.git`
2. Open in Android Studio Ladybug or newer
3. Sync Gradle (Room, RecyclerView dependencies will download)
4. Run on Emulator (Pixel 7 API 34 recommended) or physical device
5. Folder location on my PC: `C:\Users\Thabang\AndroidStudioProjects\SmartPantryManager`

## 8. Video Demonstration
5-7 minute video shows:
- GitHub commit history
- App running: Add, Edit, Delete, Filter operations
- Database persistence demo
- Verbal explanation of Room components

Link: [Insert Google Drive Link Here]

## 9. Screenshots
- Screenshot 1: Project Structure showing `app/src/main/java/com/example/smartpantrymanager/` with AppDatabase.java
- Screenshot 2: Emulator running MainActivity with items
- Screenshot 3: Add Item screen with DatePicker
- Screenshot 4: GitHub commit history

## 10. Academic Integrity Declaration
I confirm this is my own original work and I can explain and defend any part of the submitted code if asked to do so.
