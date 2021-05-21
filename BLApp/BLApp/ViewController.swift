//
//  ViewController.swift
//  BLApp
//
//  Created by 郭春茂 on 2021/5/21.
//

import UIKit
import BLBase

class ViewController: UITableViewController {

    
    var dataSource: UITableViewDiffableDataSource<Section, Item>! = nil

    private var imageObjects = [Item]()
    
    // MARK: View
    
    override func viewDidLoad() {
        super.viewDidLoad()
        
        dataSource = UITableViewDiffableDataSource<Section, Item>(tableView: tableView) {
            (tableView: UITableView, indexPath: IndexPath, item: Item) -> UITableViewCell? in
            let cell = tableView.dequeueReusableCell(withIdentifier: "cell", for: indexPath)
            /// - Tag: update
            //var content = cell.defaultContentConfiguration()
            //content.image = item.image
            cell.imageView?.image = item.image
            print(item.url ?? "")
            ImageCache.publicCache.load(url: item.url as NSURL, item: item) { (fetchedItem, image) in
                if let img = image, img != fetchedItem.image {
                    var updatedSnapshot = self.dataSource.snapshot()
                    if let datasourceIndex = updatedSnapshot.indexOfItem(fetchedItem) {
                        let item = self.imageObjects[datasourceIndex]
                        item.image = img
                        updatedSnapshot.reloadItems([item])
                        self.dataSource.apply(updatedSnapshot, animatingDifferences: true)
                    }
                }
            }
            //cell.contentConfiguration = content
            return cell
        }
        
        self.dataSource.defaultRowAnimation = .fade
        
        // Get our image URLs for processing.
//        if imageObjects.isEmpty {
//                for index in 1...100 {
//                    if let url = Bundle.main.url(forResource: "UIImage_\(index)", withExtension: "png") {
//                        self.imageObjects.append(Item(image: ImageCache.publicCache.placeholderImage, url: url))
//                    }
//                }
//                var initialSnapshot = NSDiffableDataSourceSnapshot<Section, Item>()
//                initialSnapshot.appendSections([.main])
//                initialSnapshot.appendItems(self.imageObjects)
//                self.dataSource.apply(initialSnapshot, animatingDifferences: true)
//        }

        let book = BIServices.bookService()
        book?.getEBookInfo(with: "66233", with: "520135")?.subscribe(with: ObservableComsumer {
            if let d = $0 as? BIBookService_EBookData {
                let it = d.getElectronicTeachingMaterialList()?.iterator();
                while true == it?.hasNext() {
                    if let i = it?.next() as? BIBookService_ElectronicTeachingMaterial {
                        if let u = i.getUrl()?.addingPercentEncoding(withAllowedCharacters:     .urlQueryAllowed) {
                            if let l = URL(string: u) {
                                let url:URL = l
                                print(url.absoluteURL)
                                print(url)
                                let it = Item(image: ImageCache.publicCache.placeholderImage, url: url)
                                print(it.url ?? "")
                                self.imageObjects.append(it)
                            }
                        }
                    }
                }
                DispatchQueue.main.async {
                    var initialSnapshot = NSDiffableDataSourceSnapshot<Section, Item>()
                    initialSnapshot.appendSections([.main])
                    initialSnapshot.appendItems(self.imageObjects)
                    self.dataSource.apply(initialSnapshot, animatingDifferences: true)
                }
            }
        });
    
    }
 }

