var mongoose    = require('mongoose');
require('dotenv').load();

mongoose.connect(process.env.DB);

// var classSchema = {
//   id: Number,
//   first_name: String,
//   last_name: String,
//   email: String,
//   country: String
// }
var AnySchema = new mongoose.Schema({ any: mongoose.Schema.Types.Mixed },{ strict: false });

function roominit(){
  return mongoose.model('ROOM', AnySchema, 'ROOM');
}

function classeventinit(){
  return mongoose.model('CLASS-EVENT', AnySchema, 'CLASS-EVENT');
}

function userinit(){
  return mongoose.model('USER', AnySchema, 'USER');
}



exports.roominit = roominit;
exports.classeventinit = classeventinit;
exports.userinit = userinit;