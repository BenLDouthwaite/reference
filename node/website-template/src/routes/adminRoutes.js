var express = require('express');
var adminRouter = express.Router();
var mongodb = require('mongodb').MongoClient;
var books = [
    {
        title: 'title1',
        author: 'author1'
    },
    {
        title: 'title2',
        author: 'author2'
    },
    {
        title: 'title3',
        author: 'author3'
    },
    {
        title: 'title4',
        author: 'author4'
    }
];

var research = [
    {
        title: 'title1',
        description: 'description1'
    },
    {
        title: 'title2',
        description: 'description2'
    },
    {
        title: 'title3',
        description: 'description3'
    },
    {
        title: 'title4',
        description: 'description4'
    }
];
var router = function(nav){
    adminRouter.route('/addBooks')
        .get(function(req, res) {
            var url = 'mongodb://localhost:27017/libraryApp';
            mongodb.connect(url, function(err, db) {
                var collection = db.collection('books');
                collection.insertMany(books, function(err, results) {
                    res.send(results);
                    db.close();
                });
            })
        
//            res.send('inserting books');
        })

    adminRouter.route('/addResearch')
        .get(function(req, res) {
            var url = 'mongodb://localhost:27017/libraryApp';
            mongodb.connect(url, function(err, db) {
                var collection = db.collection('research');
                collection.insertMany(research, function(err, results) {
                    res.send(results);
                    db.close();
                });
            })
        
    //            res.send('inserting books');
        })

    return adminRouter;
}

module.exports = router;