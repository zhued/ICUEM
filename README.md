# ICUEM
Interactive CU Engineering Map - CSCI 3308

Vision: An application for people to actually be able to navigate the Engineering Center without too much hassle/confusion.

Team Members: Steven Tang, Edward Zhu, Andrew Arnopoulos, Brian Gaydon

Motivation: Even after being students for a few years here, we still don’t know where things are in the EC. It would drastically reduce the time and stress of finding rooms for all occasions.

To use the embedded MongoLab API:


####Before you start

Make sure you have a file named '.env' that contains the MongoLab API key and MongoLab DB connection verification.

####Init Server (npm)
	
	npm install

	npm start

##To Test!

	npm test


##What You Can Do! (More will be added later)

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
