//
//  ViewController.swift
//  ICUEM
//
//  Created by Andrew Arnopoulos on 2/14/15.
//  Copyright (c) 2015 ICUEM. All rights reserved.
//

import UIKit
import MapKit

class ViewController: UIViewController {
	
	@IBOutlet var mapView:MKMapView!
	
	lazy var searchBar:UISearchBar = {
		var searchBar = UISearchBar()
		searchBar.placeholder = "Search"
		return searchBar
	}()

	override func viewDidLoad() {
		super.viewDidLoad()
		// Do any additional setup after loading the view, typically from a nib.
		navigationItem.titleView = searchBar
		let point = MKMapPointMake(55727057.1484889, 101615460.203782)
		let size = MKMapSizeMake(2201.44149146974, 3463.83047926426)
		mapView.setVisibleMapRect(MKMapRect(origin: point, size: size), animated: false)
	}
	
	override func viewWillAppear(animated: Bool) {
		super.viewWillAppear(animated)
		mapView.showsBuildings = true
	}

	override func didReceiveMemoryWarning() {
		super.didReceiveMemoryWarning()
		// Dispose of any resources that can be recreated.
	}

	@IBAction func mapViewInteraction(tapGesture:UITapGestureRecognizer) {
		if searchBar.canResignFirstResponder() {
			searchBar.resignFirstResponder()
		}
	}
	
	@IBAction func printRect() {
		let mapRect = mapRectForCoordinateRegion(mapView.region)
		mapView.setRegion(mapView.region, animated: false)
		mapView.setVisibleMapRect(mapRect, animated: false)
		println(mapRect.origin.x)
		println(mapRect.origin.y)
		println()
		println(mapRect.size.height)
		println(mapRect.size.width)
	}
	
	func mapRectForCoordinateRegion(region:MKCoordinateRegion) -> MKMapRect {
		let a = MKMapPointForCoordinate(CLLocationCoordinate2DMake(region.center.latitude + region.span.latitudeDelta / 2, region.center.longitude - region.span.longitudeDelta / 2))
		let b = MKMapPointForCoordinate(CLLocationCoordinate2DMake(region.center.latitude - region.span.latitudeDelta / 2, region.center.longitude + region.span.longitudeDelta / 2))
		
		return MKMapRectMake(min(a.x, b.x), min(a.y, b.y), abs(a.x-b.x), abs(a.y-b.y))
	}

}

