var express = require('express');
var taskRouter = express.Router();
var mongodb = require('mongodb').MongoClient;
var objectId = require('mongodb').ObjectID;

var router = function(nav) {
    var taskController = require('../controllers/taskController')(null, nav);

    taskRouter.use(taskController.middleware);
    
    taskRouter.route('/') // Get List of all task Topics
        .get(taskController.getIndex);
    taskRouter.route('/') // Post a new task Topic
        .post(taskController.postTask);

    return taskRouter;
};
module.exports = router;