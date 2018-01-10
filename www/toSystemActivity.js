var exec = require('cordova/exec');
var toSystemSet = function() {};

toSystemSet.prototype.toActivity = function (activityType, success, error) {
    exec(success, error, 'ToSystemActivity', 'toActivity', [activityType]);
};

var toSystemSet = new toSystemSet();
module.exports = toSystemSet;
