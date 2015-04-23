//
//  IMClassViewController.swift
//  ICUEM
//
//  Created by Andrew Arnopoulos on 4/7/15.
//  Copyright (c) 2015 ICUEM. All rights reserved.
//

import UIKit

class IMClassViewController: UIViewController {
	
	///Model for the view controller.
	var classModel:IMClass? = nil {
		didSet {
			navigationItem.title = classModel?.name
		}
	}

}
