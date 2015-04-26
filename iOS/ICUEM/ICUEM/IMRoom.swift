//
//  IMRoom.swift
//  ICUEM
//
//  Created by Andrew Arnopoulos on 4/22/15.
//  Copyright (c) 2015 ICUEM. All rights reserved.
//

import Foundation

class IMRoom: NSObject {
	///Wing the room is located in.
	let wing:String
	///Room Number
	let roomNumber:String
	let mapURL:NSURL
	/**
	Creates new IMRoom
	
	:param: json: A dictionary with a "Wing" and "RoomNum" key-value pair.
	*/
	init(json:[String:AnyObject]) {
		wing = json["Wing"] as! String
		roomNumber = json["RoomNum"] as! String
		mapURL = NSURL(string: json["url"] as! String)!
	}
	
	func toString() -> String {
		return wing + " " + roomNumber
	}
}
