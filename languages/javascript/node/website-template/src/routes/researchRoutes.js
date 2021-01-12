var express = require('express');
var researchRouter = express.Router();
var mongodb = require('mongodb').MongoClient;
var objectId = require('mongodb').ObjectID;

var router = function(nav) {
    var researchController = require('../controllers/researchController')(null, nav);

    researchRouter.use(researchController.middleware);
    
    researchRouter.route('/') // Get List of all Research Topics
        .get(researchController.getIndex);
    researchRouter.route('/') // Post a new Research Topic
        .post(researchController.postResearchTopic);

    researchRouter.route('/addNew')
        .get(researchController.getAddNew);

    return researchRouter;
};
module.exports = router;