var request = require('supertest'),
    chai = require('chai'),
    express = require('express'),
    app     = require('../server/index.js'),
    db      = require('../server/mongo.js'),
    data_room = require('../data_room.json')
    mongoose = require('mongoose');


    var expect = chai.expect,
        should = chai.should();

    if(process.env.NODE_ENV != 'development'){
        console.log('in development mode');
    }

    var Room = mongoose.model('ROOM'); // this is counting as a test, very good


    describe('POST single class', function(){
      it('responds with a single class object in JSON', function(done){
        request(app).post('/class/post?RoomID=0001&ClassID=50009&Dept=CSCI&ClassNum=4308&Name=SeniorProjects')
            .end(function(err,res){
                if(err){ return err}
                expect(res).to.exist;
                expect(res.status).to.equal(200);
                expect(res.text).to.contain('"RoomID":"0001"');
                expect(res.text).to.contain('"ClassID":"50009"');
                expect(res.text).to.contain('"Dept":"CSCI"');
                done();
        });
      });
    });

    describe('DELETE single room' , function(){
      it('deletes a single room', function(done){
        request(app).del('/room/delete/0002').expect(200, done);
        request(app)
            .get('/room/get/0002')
            .end(function(err,res){
                if(err){ return err }
                expect(res.text).to.equal("[]")
        });
      });
    });
    describe('DELETE single class' , function(){
      it('deletes a single class', function(done){
        request(app).del('/class/delete/50002').expect(200, done);
        request(app)
            .get('/class/get/50002')
            .end(function(err,res){
                if(err){ return err }
                expect(res.text).to.equal("[]")
        });
      });
    });

    describe('GET all rooms', function(){
      it('responds with a list of room objects in JSON', function(done){
        request(app)
        .get('/room/all')
        .set('Accept', 'application/json')
        .expect('Content-Type', /json/)
        .expect(200, done);
      });
    });
    
    describe('DROP ROOM database', function(){
      it('drops the ROOM database..', function(done){
        request(app).get('/room/drop').expect(200, done);
        request(app).get('/room/count')
            .end(function(err,res){
                if(err){ return err }
                expect(res.text).to.equal("0")
            });
      });
    });

    describe('RESET ROOM database', function(){
      it('RESETS the ROOM collection..', function(done){
        request(app).get('/room/reset').expect(200);
        request(app).get('/room/count')
            .end(function(err,res){
                if(err){ return err }
                expect(res.text).to.equal("8")
            });
            done();
      });
    });

    describe('ADD ROOM database', function(){
      it('ADD the ROOM database..', function(done){
        request(app).get('/room/drop').expect(200);
        request(app).get('/room/add').expect(200);
        request(app).get('/room/count')
            .end(function(err,res){
                if(err){ return err }
                expect(res.text).to.equal("8")
            })
        done();
      });
    });
