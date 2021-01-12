var mongodb = require('mongodb').MongoClient;
var objectId = require('mongodb').ObjectID;


var task = [
    {
        title: 'demo title1',
        description: 'demo description1'
    }
];

var taskController = function(taskService, nav){
    
    var middleware =  function(req, res, next) {
//        if(!req.user) {
//            //res.redirect('/');
//        }
        next();
    }

    var getIndex = function (req, res) {
        var url = 'mongodb://localhost:27017/libraryApp';
        mongodb.connect(url, function(err, db) {
            var collection = db.collection('tasks');
            collection.find({}).toArray(function(err,results) {
                res.render('taskListView', {
                    title: 'task',
                    nav: nav,
                    tasks: results
                });
            });
        });
    };

    var postTask = function (req, res) {
        var url = 'mongodb://localhost:27017/libraryApp';
        mongodb.connect(url, function(err, db) {
            var collection = db.collection('tasks');
            var task = {
                title: req.body.title,
                description: req.body.description
            };
            collection.insert(task, function (err, results) {
                res.redirect('/Tasks');
            });
        });
    };

    var getAddNew = function (req, res) {
            var url = 'mongodb://localhost:27017/libraryApp';
            mongodb.connect(url, function(err, db) {
                res.render('taskAddNewView', {
                    title: 'task',
                    nav: nav,
                });
            });
        };
    
    return {
        getIndex : getIndex,
        postTask : postTask,
        getAddNew : getAddNew,
        middleware : middleware
    };
};

module.exports = taskController;