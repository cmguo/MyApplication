/*
See LICENSE folder for this sample’s licensing information.

Abstract:
The ImageURLProtocol of the sample.
*/
import UIKit

class ImageURLProtocol: URLProtocol {

    var cancelledOrComplete: Bool = false
    var block: DispatchWorkItem!
    
    private static let queue = OS_dispatch_queue_serial(label: "com.apple.imageLoaderURLProtocol")
    
    override class func canInit(with request: URLRequest) -> Bool {
        return true
    }
    
    override class func canonicalRequest(for request: URLRequest) -> URLRequest {
        print("canonicalRequest url = ", request.url!)
        print("canonicalRequest path = ", request.url?.path as Any)
        return request
    }
    
    class override func requestIsCacheEquivalent(_ aRequest: URLRequest, to bRequest: URLRequest) -> Bool {
        return false
    }
    
    final override func startLoading() {
        guard let reqURL = request.url, let urlClient = client else {
            return
        }
        
        print("startLoading url = ", reqURL)
        
        block = DispatchWorkItem(block: {
            if self.cancelledOrComplete == false {
                let fileURL = URL(fileURLWithPath: reqURL.path)
                
                    print("fileurl = ", fileURL)
                    let datat = try? Data(contentsOf: fileURL)
                    print(datat ?? "data is nil")
                
                if let data = try? Data(contentsOf: fileURL) {
                    urlClient.urlProtocol(self, didLoad: data)
                    urlClient.urlProtocolDidFinishLoading(self)
                }
            }
            self.cancelledOrComplete = true
        })
        
        ImageURLProtocol.queue.asyncAfter(deadline: DispatchTime(uptimeNanoseconds: 500 * NSEC_PER_MSEC), execute: block)
    }
    
    final override func stopLoading() {
        ImageURLProtocol.queue.async {
            if self.cancelledOrComplete == false, let cancelBlock = self.block {
                cancelBlock.cancel()
                self.cancelledOrComplete = true
            }
        }
    }
    
    static func urlSession() -> URLSession {
        let config = URLSessionConfiguration.ephemeral
        //config.protocolClasses = [ImageURLProtocol.classForCoder()]
        return  URLSession(configuration: config)
    }
    
}
