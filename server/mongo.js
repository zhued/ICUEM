// Main MongoDB init and connections
// ---------------
// Requires mongoose for MongoDB data pullling
var mongoose    = require('mongoose');
// dotenv is a node module that stores our credentials
require('dotenv').load();

mongoose.connect(process.env.DB);

// Inits a basic 'any' schema with our database
var AnySchema = new mongoose.Schema({ any: mongoose.Schema.Types.Mixed },{ strict: false });

// Next three functions init the 'any' schema on the three collections we have,
// which are Rooms, Class-Events, and User
function roominit(){
  return mongoose.model('ROOM', AnySchema, 'ROOM');
}

function classeventinit(){
  return mongoose.model('CLASS-EVENT', AnySchema, 'CLASS-EVENT');
}

function userinit(){
  return mongoose.model('USER', AnySchema, 'USER');
}

// Export db schemas to other files
exports.roominit = roominit;
exports.classeventinit = classeventinit;
exports.userinit = userinit;