//
//  IMClassViewController.swift
//  ICUEM
//
//  Created by Andrew Arnopoulos on 4/7/15.
//  Copyright (c) 2015 ICUEM. All rights reserved.
//

import UIKit

class IMClassViewController: UIViewController {
	
	var classModel:(String,String)? = nil {
		didSet {
			navigationItem.title = classModel?.0
		}
	}

    override func viewDidLoad() {
        super.viewDidLoad()

        // Do any additional setup after loading the view.
//		navigationItem.title
    }

    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
        // Dispose of any resources that can be recreated.
    }
    

    /*
    // MARK: - Navigation

    // In a storyboard-based application, you will often want to do a little preparation before navigation
    override func prepareForSegue(segue: UIStoryboardSegue, sender: AnyObject?) {
        // Get the new view controller using segue.destinationViewController.
        // Pass the selected object to the new view controller.
    }
    */

}
