var db        = require('./mongo.js');
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


};