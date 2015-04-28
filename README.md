# ICUEM
Interactive CU Engineering Map - CSCI 3308

Vision: An application for people to actually be able to navigate the Engineering Center without too much hassle/confusion.

Team Members: Steven Tang, Edward Zhu, Andrew Arnopoulos, Brian Gaydon

# Navigation
This repo contains 3 main parts: iOS project files, Android project files, and a Node.JS local server that connects with our MongoDB.

iOS project files can be found in the iOS/ICUEM folder.

Android project files can be found in the android/MyApplication folder (although not fully implemented).

Node.JS local API server can be found in the server/ folder and testing for this DB connection can be found in the test/ folder.

#Want to see what our MongoDB looks like?

Run(make sure you have basic mongo drivers installed):

	mongo ds039950.mongolab.com:39950/icuem -u readonly -p readonly


#To use the embedded Node.JS MongoLab API:


####Before you start

Make sure you have a file named '.env' that contains the MongoLab API key and MongoLab DB connection verification.


####Init Server (npm)
	
	npm install

	npm start

##To Test the DB connection!

	npm test


##What You Can Do!

#### Get COUNT of database entries
	
	curl http://localhost:3000/room/count

#### Get ALL data & details in database - as JSON

	curl http://localhost:3000/room/all

	curl http://localhost:3000/class/all

#### Get a SINGLE entry of a person

	curl http://localhost:3000/room/get/:id
	
	curl http://localhost:3000/class/get/:id

#### ADD a single entry to a database

	curl -X POST http://localhost:3000/room/post?<data-goes_here>

	curl -X POST http://localhost:3000/class/post?<data-goes_here>

#### DELETE single person entry
	
	curl -X DELETE http://localhost:3000/room/delete/:RoomID

	curl -X DELETE http://localhost:3000/class/delete/:ClassID

#### RESET database

	curl http://localhost:3000/room/reset

	curl http://localhost:3000/class/reset

#### DROP database

	curl http://localhost:3000/room/drop

	curl http://localhost:3000/class/drop

#### ADD database

	curl http://localhost:3000/room/add

	curl http://localhost:3000/class/add


#To compile our app before it hits the App Store:

  - Download XCode from Mac App store.
  - Clone or Download these Project files from the folder iOS/ICUEM
  - On XCode:
    - Control-Click on empty space
    - Choose Add Files to “<Workspace_Name>”
    - Select the .xcodeproj file and click Add.
  - Then start up an emulator and the node DB connection server and watch the app work!

