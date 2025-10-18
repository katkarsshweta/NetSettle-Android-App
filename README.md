# Net$ettle  
**Streamlined Money and Asset Management Solution for Comprehensive Financial Control**

---

## Technologies and Tools
- Platform: Android  
- IDE: Android Studio  
- Languages: Java / Kotlin  
- Database: Firebase Realtime Database  
- Storage: Firebase Storage  

---

## Problem Statement
In a complex financial landscape, individuals often struggle to efficiently track diverse assets such as cash, investments, real estate, and possessions.  
There is no centralized, secure platform for recording and managing these assets along with personal money management, including income, savings, and spending.  
Net$ettle addresses this by providing a unified, user-friendly app to consolidate, organize, and monitor all financial assets and transactions securely and comprehensively.

---

## Abstract
The Net$ettle app provides users with a comprehensive and intuitive platform for managing diverse financial assets and personal finances.  
It enables efficient tracking, organization, and secure storage of income, spending, savings, and assets such as real estate, vehicles, and valuables.  
By using modern Android components and Firebase integration, the app empowers individuals to gain better control over their financial portfolio.

---

## Technical Details

### 1. Development Platform
- IDE: Android Studio  
- Languages: Java / Kotlin  

### 2. Database Integration
- Firebase Realtime Database: For managing and storing real-time data related to financial transactions and asset details.  
- Firebase Storage: For securely storing and managing various media files such as receipts and asset-related images.

---

## Keywords
Money, Asset, Ornaments, Real Estate, Budget, Vehicle, Money Management, Loans, Wealth Management, Finance  

---

## Features

### User Authentication
- Registration:  
  - Username must be alphabetical; null value not accepted; first and last name should start with a capital letter.  
  - Password must have at least 8 characters containing alphabets, digits, and special characters.  
  - Contact number must be 10 digits.  
  - Security question and answer saved in Firebase for password recovery.  
- Login: Username, password, and password confirmation.  
- Forgot Password: Validates security question and allows password reset.  
- Sign in with Google.

### Profile Management
- Update username and password.  
- Upload profile image.  

### Personal Money Management
- Net Balance Calculation: `Net Balance = Total In - Total Out`.  
- Total In (Cash In): Enter transaction details; saved and displayed under Total In.  
- Total Out (Cash Out): Enter transaction details; saved and displayed under Total Out.  
- Transaction History: View and manage all transactions.  
- Budgeting: Set budgets and receive alerts when spending exceeds limits.  

### Wealth and Assets
Manage assets under four categories:
1. Ornaments  
2. Real Estate  
3. Vehicles  
4. Miscellaneous  

Each entry includes amount, type, location, date/time, image upload, and description—saved to Firebase and retrievable for review.

---

## Scope and Features

### Personal Money Management
- Budgeting and spending limit setup  
- Income and spending tracking  
- Transaction history management  
- Total balance calculation  
- Notifications for overspending  

### Asset Management
- Four management categories (Ornaments, Real Estate, Vehicles, Miscellaneous)  
- Detailed entries for each asset  
- Document and image attachment  
- Centralized database for all assets  

---

## Target Audience
- Individuals seeking comprehensive financial management  
- Budget-conscious users  
- Individuals with diverse asset portfolios  
- Tech-savvy users interested in data organization  

---

## Android Features Implemented
1. Layouts and Views  
2. Intents and Intent Filters  
3. Toast and RecyclerView  
4. Fragments and Adapters  
5. Image Uploading  
6. Media Player  
7. Broadcasting  
8. Toast Messaging  
9. Graphical Primitives  
10. Spinner Implementation  
11. Alert Dialog Box  
12. Date and Time Picker  
13. Firebase Integration  

---

## Conclusion
The Net$ettle app offers an effective solution for individuals seeking efficient personal finance management and comprehensive asset tracking.  
By providing distinct interfaces for managing personal finances and diverse assets, it delivers a user-friendly platform to streamline financial activities and organize possessions systematically.  
With future updates, Net$ettle aims to evolve into a more sophisticated and inclusive financial and asset management solution, offering advanced tools for achieving better financial control and asset organization.

---

## How to Run the App
1. Clone the repository:  
   ```bash
   git clone https://github.com/your-username/NetSettle.git
