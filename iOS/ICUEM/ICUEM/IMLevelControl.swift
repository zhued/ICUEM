//
//  IMLevelControl.swift
//  ICUEM
//
//  Created by Andrew Arnopoulos on 2/14/15.
//  Copyright (c) 2015 ICUEM. All rights reserved.
//

import UIKit

@IBDesignable class IMLevelControl: UIView {
	
	/*
	// Only override drawRect: if you perform custom drawing.
	// An empty implementation adversely affects performance during animation.
	override func drawRect(rect: CGRect) {
	// Drawing code
	}
	*/
	
	@IBInspectable var levels:Int = 0
	@IBInspectable var currentLevel:Int = 0
	@IBInspectable var levelColor:UIColor = UIColor.grayColor()
	
	override func prepareForInterfaceBuilder() {
		super.prepareForInterfaceBuilder()
	}
	
	override func drawRect(rect: CGRect) {
		super.drawRect(rect)
		let context = UIGraphicsGetCurrentContext()
		levelColor.set()
		CGContextSetLineWidth(context, 3)
		CGContextMoveToPoint(context, CGRectGetWidth(frame)-20, 5)
		CGContextAddLineToPoint(context, CGRectGetWidth(frame)-20, CGRectGetHeight(frame)-5)
		CGContextStrokePath(context)
		
		let increment = (CGRectGetHeight(frame)-20) / CGFloat(levels - 1)
		CGContextSetLineWidth(context, 1)
		for i in 0..<levels {
			let y = 10 + CGFloat(i) * increment
			let x0 = CGRectGetWidth(frame) - 20 - 10
			let x1 = CGRectGetWidth(frame) - 20 + 10
			CGContextMoveToPoint(context, x0, y)
			CGContextAddLineToPoint(context, x1, y)
			CGContextStrokePath(context)
		}
	}
	
}
