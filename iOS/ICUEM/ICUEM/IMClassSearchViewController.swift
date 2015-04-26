//
//  IMClassSearchViewController.swift
//  ICUEM
//
//  Created by Andrew Arnopoulos on 3/26/15.
//  Copyright (c) 2015 ICUEM. All rights reserved.
//

import UIKit

class IMClassSearchViewController: UIViewController,UITextFieldDelegate {
	
	@IBOutlet var searchField:UITextField!
	
	func textFieldShouldReturn(textField: UITextField) -> Bool {
		if textField == searchField {
			displayRelevantFormInformation()
			return true
		}
		return false
	}
	
	@IBAction func displayRelevantFormInformation() {
		if searchField.text != "" {
			performSegueWithIdentifier("ShowResults", sender: nil)
		} else {
			UIAlertView(title: "Invalid Query", message: "Both fields must be completed.", delegate: nil, cancelButtonTitle: "OK").show()
		}
	}
	
	override func prepareForSegue(segue: UIStoryboardSegue, sender: AnyObject?) {
		if segue.identifier == "ShowResults" {
			let destinationViewController = segue.destinationViewController as! IMClassTableViewController
			destinationViewController.predicate = {
				if ($0.name.lowercaseString.rangeOfString(self.searchField.text.lowercaseString) != nil) {
					return true
				}
				let components = split(self.searchField.text) {$0 == " "}
				if components.count >= 2 {
					return $0.room.wing == components[0] && $0.room.roomNumber == components[1]
				}
				return false
			}
		}
	}

}
