//
//  ObservableComsumer.swift
//  BLBase
//
//  Created by 郭春茂 on 2021/5/19.
//

import Foundation

public class ObservableComsumer : NSObject, RXConsumer {
    
    private let comsumer: (Any) -> Void
    
    public init(comsumer: @escaping (Any) -> Void) {
        self.comsumer = comsumer;
    }
    
    public func accept(withId t: Any!) {
        comsumer(t!)
    }
}
