//
//  IMClassSearchViewController.swift
//  ICUEM
//
//  Created by Andrew Arnopoulos on 3/26/15.
//  Copyright (c) 2015 ICUEM. All rights reserved.
//

import UIKit

class IMClassSearchViewController: UIViewController,UITextFieldDelegate {
	///UITextField that takes in section for the room
	@IBOutlet var sectionField:UITextField!
	///UITextField that takes in room number for the room
	@IBOutlet var roomNumberField:UITextField!
	
	func textFieldShouldReturn(textField: UITextField) -> Bool {
		if textField == sectionField {
			roomNumberField.becomeFirstResponder()
			return true
		} else if textField == roomNumberField {
			displayRelevantFormInformation()
			return true
		}
		return false
	}
	
	@IBAction func displayRelevantFormInformation() {
		if sectionField.text != "" && roomNumberField.text != "" {
			performSegueWithIdentifier("ShowResults", sender: nil)
		} else {
			UIAlertView(title: "Invalid Query", message: "Both fields must be completed.", delegate: nil, cancelButtonTitle: "OK").show()
		}
	}
	
	override func prepareForSegue(segue: UIStoryboardSegue, sender: AnyObject?) {
		if segue.identifier == "ShowResults" {
			let destinationViewController = segue.destinationViewController as! IMClassTableViewController
			destinationViewController.predicate = {
				return ($0.room.wing == self.sectionField.text && $0.room.roomNumber == self.roomNumberField.text) || $0.name == self.sectionField.text
			}
		}
	}

}
