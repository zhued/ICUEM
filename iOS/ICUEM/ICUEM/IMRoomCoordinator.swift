//
//  IMRoomCoordinator.swift
//  ICUEM
//
//  Created by Andrew Arnopoulos on 4/22/15.
//  Copyright (c) 2015 ICUEM. All rights reserved.
//

import Foundation



class IMRoomCoordinator: NSObject {
	
	/**
	Singleton instantiation of IMClassCoordinator.
	
	:returns: IMClassCoordinator
	*/
	class var defaultCoordinator:IMRoomCoordinator {
		let url = NSURL(string: "http://localhost:3000/room/all")
		let request = NSURLRequest(URL: url!)
		var response:AutoreleasingUnsafeMutablePointer<NSURLResponse?>=nil
		var error: NSErrorPointer = nil
		let data = NSURLConnection.sendSynchronousRequest(request, returningResponse: response, error: error)!
		let json = NSJSONSerialization.JSONObjectWithData(data, options: .MutableContainers, error: nil) as! NSArray
		var rooms:[String:IMRoom] = [:]
		for (var i = 0; i < json.count; i++) {
			let room = json[i] as! [String:AnyObject]
			rooms[room["RoomID"]! as! String] = IMRoom(json: room)
		}
		return IMRoomCoordinator(rooms: rooms)
	}
	
	private let rooms:[String:IMRoom]
	
	/**
	Creates new IMRoomCoordinator with room ID to IMRoom mapping
	
	:param: rooms: Room ID to IMRoom dictionary.
	:returns: IMRoomCoordinator
	*/
	init(rooms:[String:IMRoom]) {
		self.rooms = rooms
	}
	/**
	Gets room with corrisponding ID.
	
	:param: id: String that corrisponds to room ID.
	:returns: IMRoom of rooms[id].
	*/
	func getRoom(id:String) -> IMRoom {
		return rooms[id]!
	}

}
