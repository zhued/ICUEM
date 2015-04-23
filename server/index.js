// Main init file for webframework.
// ---------------
// Requires express (light-weight web application framework),
// bodyParser (parse info from MongoDB), routes (import given
// enpoints from routes.js file), and http (inits a http webservice)
var express     = require('express'),
    bodyParser  = require('body-parser'),
    routes      = require('./routes'),
    http        = require('http');

    // Inits an express app onto port 3000
    var app = express();
    app.set('port', process.env.PORT || 3000);
    app.set('env', process.env.NODE_ENV || 'development');

    // Parses through encoded data
    app.use(bodyParser.urlencoded({ extended: false }));
    app.use(bodyParser.json());

    // Exports routes.js to utilize our express framework
    routes(app);

    // Creates our express server on http (localhost) and outputs
    // that it is listening on port 3000
    var server = http.createServer(app);

  server.listen(app.get('port'), function(){
    console.log("Listening on port " + app.get('port') + " in "+ app.get('env') + " mode");
  });


// Export this module as app, so mongo.js and routes.js can use this.
module.exports = app;