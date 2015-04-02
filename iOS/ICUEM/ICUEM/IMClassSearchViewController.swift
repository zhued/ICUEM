//
//  IMClassSearchViewController.swift
//  ICUEM
//
//  Created by Andrew Arnopoulos on 3/26/15.
//  Copyright (c) 2015 ICUEM. All rights reserved.
//

import UIKit

class IMClassSearchViewController: UIViewController,UITextFieldDelegate {
	
	@IBOutlet var sectionField:UITextField!
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

}
