//
//  IMClassTableViewController.swift
//  ICUEM
//
//  Created by Andrew Arnopoulos on 4/7/15.
//  Copyright (c) 2015 ICUEM. All rights reserved.
//

import UIKit

class IMClassTableViewController: UITableViewController {

    override func numberOfSectionsInTableView(tableView: UITableView) -> Int {
        // #warning Potentially incomplete method implementation.
        // Return the number of sections.
        return 1
    }

	let classes = [("Cognitive Science","ECCR 121"),("Concurrent Programming","ECCR 121"),("Algorithms","ECCR 121"),("Digital Logic","ECCR 121"),("Compiler Construction","ECCR 121")]
    override func tableView(tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        // #warning Incomplete method implementation.
        // Return the number of rows in the section.
        return classes.count
    }

    override func tableView(tableView: UITableView, cellForRowAtIndexPath indexPath: NSIndexPath) -> UITableViewCell {
        let cell = tableView.dequeueReusableCellWithIdentifier("ClassCell", forIndexPath: indexPath) as UITableViewCell

        // Configure the cell...
		cell.textLabel?.text = classes[indexPath.row].0
		cell.detailTextLabel?.text = classes[indexPath.row].1
		

        return cell
    }
	
	override func prepareForSegue(segue: UIStoryboardSegue, sender: AnyObject?) {
		if let sender = sender as? UITableViewCell {
			println(sender)
			let indexPath = tableView.indexPathForCell(sender)!
			let classModel = classes[indexPath.row]
			let destinationViewController = segue.destinationViewController as IMClassViewController
			
			destinationViewController.classModel = classModel
		}
		
	}

}
