var db        = require('./mongo.js');
var data_room = require('../data_room.json')
var data_class_event = require('../data_class_event.json')

var Room = db.roominit();
var Class_Event = db.classeventinit();
var User = db.userinit();

module.exports = function(app){
// ***
// Returns COUNT of each collection
// ***
  app.get('/room/count', function(req,res){
    Room.count(function(err,count){
      res.status(200).send(count.toString())
    });
  });

// *************************************************************
//               GET SINGLE - RETURN SINGLE FIELD
// *************************************************************
  app.get('/room/get/:id', function(req,res){
    Room.find({RoomID:req.params.id}).find(function(err,doc){
      if(err) res.send(err);
      res.status(200).send(doc);
    });
  });

  app.get('/class/get/:id', function(req,res){
    Class_Event.find({ClassID:req.params.id}).find(function(err,doc){
      if(err) res.send(err);
      res.status(200).send(doc);
    });
  });

// *************************************************************
//               ALL - OUTPUT EVERYTHING
// *************************************************************
  app.get('/room/all', function(req,res){
    Room.find(function(err,doc){
      if(err) res.send(err);
      res.status(200).send(doc);
    });
  });

  app.get('/class/all', function(req,res){
    Class_Event.find(function(err,doc){
      if(err) res.send(err);
      res.status(200).send(doc);
    });
  });

  app.get('/user/all', function(req,res){
    User.find(function(err,doc){
      if(err) res.send(err);
      res.status(200).send(doc);
    });
  });

// // *************************************************************
// //             INSERT - POST TO A DATABASE
// // *************************************************************
  app.post('/room/post', function(req,res){
    Room.count(function(err,count){
      Room.create({RoomID:req.query.RoomID,Wing:req.query.Wing,RoomNum:req.query.RoomNum,loc:req.query.loc}, function(err,doc){
        if(err) res.send(err);
        res.status(200).send(doc);
      });
    });
  });

  app.post('/class/post', function(req,res){
    Class_Event.count(function(err,count){
      Class_Event.create({RoomID:req.query.RoomID,ClassID:req.query.ClassID,Dept:req.query.Dept,ClassNum:req.query.ClassNum,Name:req.query.Name}, function(err,doc){
        if(err) res.send(err);
        res.status(200).send(doc);
      });
    });
  });


// // *************************************************************
// //             DELETE - POST TO A DATABASE
// // *************************************************************
  app.delete('/room/delete/:id', function(req,res){
    Room.remove( {RoomID:req.params.id}, function(err,Room){
      if(err) res.send(err);
      res.status(200).send("removed "+req.params.id);
    });
  });

  app.delete('/class/delete/:id', function(req,res){
    Class_Event.remove( {ClassID:req.params.id}, function(err){
      if(err) res.send(err);
      res.status(200).send("removed "+req.params.id);
    });
  });



// *************************************************************
//             DROP - REMOVES ALL IN COLLECTION
// *************************************************************

  app.get('/room/drop', function(req, res) {
    Room.remove({}, function(err){
      if (err) res.send(err);
      res.status(200).send("Database ROOM successfully dropped.");
    });
  });

  app.get('/class/drop', function(req, res) {
    Class_Event.remove({}, function(err){
      if (err) res.send(err);
      res.status(200).send("Database CLASS successfully dropped.");
    });
  });


// *************************************************************
//                ADD FROM FILE - POPULATE FROM JSON FILE
// *************************************************************

  app.get('/room/add', function(req, res){
    for(var i = 0; i < data_room.length; i++){
      new Room(data_room[i]).save();
    }
    res.status(200).send("Database ROOM successfully added from data_room.json.");
  });

  app.get('/class/add', function(req, res){
    for(var i = 0; i < data_class_event.length; i++){
      new Class_Event(data_class_event[i]).save();
    }
    res.status(200).send("Database CLASS successfully added from data_class_event.json.");
  });

// *************************************************************
//                 RESET - CLEAR THEN POPULATE
// *************************************************************

  app.get('/room/reset', function(req, res){
    Room.remove({}, function(err){
      if (err) res.send(err);
       for(var i = 0; i < data_room.length; i++){
          new Room(data_room[i]).save();
        }
      res.status(200).send("Database ROOM successfully reset.");
    });
  });

  app.get('/class/reset', function(req, res){
    Class_Event.remove({}, function(err){
      if (err) res.send(err);
       for(var i = 0; i < data_class_event.length; i++){
          new Class_Event(data_class_event[i]).save();
        }
      res.status(200).send("Database CLASS successfully reset.");
    });
  });




};

