//
//  UIImage+AspectScale.swift
//  ICUEM
//
//  Created by Andrew Arnopoulos on 4/25/15.
//  Copyright (c) 2015 ICUEM. All rights reserved.
//

import UIKit

extension UIImage {
	func scaleWithSize(size:CGSize) -> UIImage {
		//		let image =
		let imageSize = size
		let width = imageSize.width
		let height = imageSize.height
		let targetWidth = size.width
		let targetHeight = size.height
		var scaleFactor = CGFloat(0.0)
		var scaledWidth = targetWidth
		var scaledHeight = targetHeight
		var thunbnailPoint = CGPointZero
		
		if !CGSizeEqualToSize(imageSize, size) {
			let widthFactor = targetWidth / width
			let heightFactor = targetHeight / height
			
			if widthFactor > heightFactor {
				scaleFactor = widthFactor
			} else {
				scaleFactor = heightFactor
			}
			
			scaledWidth = width * scaleFactor
			scaledHeight = height * scaleFactor
			
			if widthFactor > heightFactor {
				thunbnailPoint.y = (targetHeight - scaledHeight) * 0.5
			} else if widthFactor < heightFactor {
				thunbnailPoint.x = (targetWidth - scaledWidth) * 0.5
			}
		}
		
		UIGraphicsBeginImageContext(size)
		
		var thumbnailRect = CGRectZero
		thumbnailRect.origin = thunbnailPoint
		thumbnailRect.size.width = scaledWidth
		thumbnailRect.size.height = scaledHeight
		drawInRect(thumbnailRect)
		let newImage = UIGraphicsGetImageFromCurrentImageContext()
		return newImage
	}
}