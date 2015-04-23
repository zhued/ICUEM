//
//  IMClassCoordinator.swift
//  ICUEM
//
//  Created by Andrew Arnopoulos on 4/22/15.
//  Copyright (c) 2015 ICUEM. All rights reserved.
//

import Foundation

class IMClassCoordinator: NSObject {

	/**
	Singleton instantiation of IMClassCoordinator.
	
	:returns: IMClassCoordinator
	 */
	class var defaultCoordinator:IMClassCoordinator {
		let url = NSURL(string: "http://localhost:3000/class/all")
		let request = NSURLRequest(URL: url!)
		var response:AutoreleasingUnsafeMutablePointer<NSURLResponse?>=nil
		var error: NSErrorPointer = nil
		let data = NSURLConnection.sendSynchronousRequest(request, returningResponse: response, error: error)!
		let json = NSJSONSerialization.JSONObjectWithData(data, options: .MutableContainers, error: nil) as! NSArray
		var classes:[IMClass] = []
		for (var i = 0; i < json.count; i++) {
			let c = json[i] as! [String:AnyObject]
			classes += [IMClass(json: c)]
		}
		return IMClassCoordinator(classes: classes)
	}
	
	let classes:[IMClass]
	
	init(classes:[IMClass]) {
		self.classes = classes
	}
}
