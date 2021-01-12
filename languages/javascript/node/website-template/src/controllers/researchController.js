var mongodb = require('mongodb').MongoClient;
var objectId = require('mongodb').ObjectID;


var research = [
    {
        title: 'demo title1',
        description: 'demo description1'
    }
];

var researchController = function(researchService, nav){
    
    var middleware =  function(req, res, next) {
//        if(!req.user) {
//            //res.redirect('/');
//        }
        next();
    }

    var getIndex = function (req, res) {
        var url = 'mongodb://localhost:27017/libraryApp';
        mongodb.connect(url, function(err, db) {
            var collection = db.collection('research');
            collection.find({}).toArray(function(err,results) {
                res.render('researchListView', {
                    title: 'research',
                    nav: nav,
                    research: results
                });
            });
        });
    };

    var postResearchTopic = function (req, res) {
        var url = 'mongodb://localhost:27017/libraryApp';
        mongodb.connect(url, function(err, db) {
            var collection = db.collection('research');
            var research = {
                title: req.body.title,
                description: req.body.description
            };
            collection.insert(research, function (err, results) {
                res.redirect('/Research');
            });
        });
    };

    var getAddNew = function (req, res) {
            var url = 'mongodb://localhost:27017/libraryApp';
            mongodb.connect(url, function(err, db) {
                res.render('researchAddNewView', {
                    title: 'research',
                    nav: nav,
                });
            });
        };
    
    return {
        getIndex : getIndex,
        postResearchTopic : postResearchTopic,
        getAddNew : getAddNew,
        middleware : middleware
    };
};

module.exports = researchController;