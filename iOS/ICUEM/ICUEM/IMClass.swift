//
//  IMClass.swift
//  ICUEM
//
//  Created by Andrew Arnopoulos on 4/22/15.
//  Copyright (c) 2015 ICUEM. All rights reserved.
//

import Foundation

class IMClass: NSObject {
	///Department that the class courisponds to.
	let department:String
	///Course number of the class.
	let classNumber:String
	///Name of the class.
	let name:String
	///IMRoom that coorisponds the class.
	let room:IMRoom
	
	/**
	Creates new IMRoom
	
	:param: json: A dictionary with a "Wing", "RoomNum", "Name" and "RoomID" key-value pair.
	*/
	init(json:[String:AnyObject]) {
		department = json["Dept"] as! String
		classNumber = json["ClassNum"] as! String
		name = json["Name"] as! String
		room = IMRoomCoordinator.defaultCoordinator.getRoom(json["RoomID"] as! String)
	}
}
