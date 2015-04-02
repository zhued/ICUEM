var db        = require('./mongo.js');
var data_room = require('../data_room.json')
var data_class_event = require('../data_class_event.json')

var Room = db.roominit();
var Class_Event = db.classeventinit();
var User = db.userinit();

module.exports = function(app){

// ***
// Returns the number of rooms
// ***
  app.get('/room/count', function(req,res){
    Room.count(function(err,count){
      res.status(200).send(count.toString())
    });
  });

// ***
// Outputs everything in ROOM collection
// ***
  app.get('/room/all', function(req,res){
    Room.find(function(err,doc){
      if(err) res.send(err);
      res.status(200).send(doc);
    });
  });

// ***
// Outputs everything in CLASS_EVENT collection
// ***
  app.get('/class/all', function(req,res){
    Class_Event.find(function(err,doc){
      if(err) res.send(err);
      res.status(200).send(doc);
    });
  });

// ***
// Outputs everything in USER collection
// ***
  app.get('/user/all', function(req,res){
    User.find(function(err,doc){
      if(err) res.send(err);
      res.status(200).send(doc);
    });
  });




// ***
// Populate ROOM data from data_room.json
// ***
  app.get('/room/add', function(req, res){
    for(var i = 0; i < data_room.length; i++){
      new Room(data_room[i]).save();
    }
    res.status(200).send("Database ROOM successfully added from data_room.json.");
  });

// ***
// Clear database and populate data from mockdata.json
// ***
  app.get('/room/reset', function(req, res){
    Room.remove({}, function(err){
      if (err) res.send(err);
       for(var i = 0; i < data_room.length; i++){
          new Room(data_room[i]).save();
        }
      res.status(200).send("Database ROOM successfully reset.");
    });
  });

};