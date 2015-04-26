//
//  IMClassTableViewController.swift
//  ICUEM
//
//  Created by Andrew Arnopoulos on 4/7/15.
//  Copyright (c) 2015 ICUEM. All rights reserved.
//

import UIKit

class IMClassTableViewController: UITableViewController {
	///Predicate to determine what classes to show.
	var predicate:(IMClass) -> Bool = {(c) -> Bool in return true} {
		didSet {
			filteredClasses = classCoordinator.classes.filter(predicate)
		}
	}
	///Local variable for IMClassCoordinator singleton.
	let classCoordinator = IMClassCoordinator.defaultCoordinator
	///Array of classes that are within the domain of the predicate
	var filteredClasses = IMClassCoordinator.defaultCoordinator.classes {
		didSet {
			tableView.reloadData()
		}
	}

    override func numberOfSectionsInTableView(tableView: UITableView) -> Int {
        return 1
    }

    override func tableView(tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
		return filteredClasses.count
    }

    override func tableView(tableView: UITableView, cellForRowAtIndexPath indexPath: NSIndexPath) -> UITableViewCell {
        let cell = tableView.dequeueReusableCellWithIdentifier("ClassCell", forIndexPath: indexPath) as! UITableViewCell

        // Configure the cell...
		cell.textLabel?.text = filteredClasses[indexPath.row].name
		cell.detailTextLabel?.text = "\(filteredClasses[indexPath.row].room.wing) \(filteredClasses[indexPath.row].room.roomNumber)"

        return cell
    }
	
	override func prepareForSegue(segue: UIStoryboardSegue, sender: AnyObject?) {
		if let sender = sender as? UITableViewCell {
			let indexPath = tableView.indexPathForCell(sender)!
			let classModel = filteredClasses[indexPath.row]
			let destinationViewController = segue.destinationViewController as! IMClassViewController
			
			destinationViewController.classModel = classModel
		}
		
	}

}
