//
//  IMClassViewController.swift
//  ICUEM
//
//  Created by Andrew Arnopoulos on 4/7/15.
//  Copyright (c) 2015 ICUEM. All rights reserved.
//

import UIKit



class IMClassViewController: UIViewController, UIScrollViewDelegate {
	
	@IBOutlet var roomLabel:UILabel?
	@IBOutlet var classNameLabel:UILabel?
	@IBOutlet var departmentLabel:UILabel?
	@IBOutlet var mapImageView:UIImageView?
	@IBOutlet var mapScrollView:UIScrollView?
	
	///Model for the view controller.
	var classModel:IMClass? = nil {
		didSet {
			navigationItem.title = classModel?.name
			classNameLabel?.text = classModel?.name
			roomLabel?.text = classModel?.room.toString()
			departmentLabel?.text = classModel?.department
			let mapURL:NSURL = classModel!.room.mapURL
			let mapImage = UIImage(data: NSData(contentsOfURL: mapURL)!)!
			mapImageView?.image = mapImage.scaleWithSize(mapImageView!.bounds.size)
//			let mapHeight = mapImageView!.frame.size.height
//			let mapWidth = mapImageView!.frame.size.width
//			let mapAspectRatio = mapWidth / mapHeight
//			if let mapScrollView = mapScrollView {
//				let width = mapScrollView.frame.size.width
//				let height = mapScrollView.frame.size.height
//				if width < height {
//					let aspectRatio = width / height
//					mapImageView!.frame.size.width = width
//					mapImageView!.frame.size.height = aspectRatio * height
//				} else {
//					let aspectRatio = height / width
//					mapImageView!.frame.size.width = aspectRatio * width
//					mapImageView!.frame.size.height = height
//				}
//			}
			
		}
	}
	
	func viewForZoomingInScrollView(scrollView: UIScrollView) -> UIView? {
		return mapImageView
	}
	
	override func viewDidLoad() {
		super.viewDidLoad()
		classNameLabel?.text = classModel?.name
		roomLabel?.text = classModel?.room.toString()
		departmentLabel?.text = classModel?.department
		let mapURL:NSURL = classModel!.room.mapURL
		let mapImage = UIImage(data: NSData(contentsOfURL: mapURL)!)!
		mapImageView?.image = mapImage.scaleWithSize(mapImageView!.bounds.size)
//		if let mapScrollView = mapScrollView {
//			let width = mapScrollView.frame.size.width
//			let height = mapScrollView.frame.size.height
//			if width < height {
//				let aspectRatio = width / height
//				mapImageView!.frame.size.width = width
//				mapImageView!.frame.size.height = aspectRatio * height
//			} else {
//				let aspectRatio = height / width
//				mapImageView!.frame.size.width = aspectRatio * width
//				mapImageView!.frame.size.height = height
//			}
//		}
		
		mapScrollView?.clipsToBounds = true
		mapScrollView?.minimumZoomScale = 1.0
		mapScrollView?.maximumZoomScale = 4.0
	}

}


