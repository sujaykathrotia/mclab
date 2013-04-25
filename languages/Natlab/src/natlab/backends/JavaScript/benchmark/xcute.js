var fs = require('fs');
var vm = require('vm');

var includeInThisContext = function(path) {
    var code = fs.readFileSync(path);
    vm.runInThisContext(code, path);
}.bind(this);

includeInThisContext(__dirname + "/mclib.js");
includeInThisContext(__dirname + "/bubble.js");
includeInThisContext(__dirname + "/dirich.js");
includeInThisContext(__dirname + "/fibonacci.js");
includeInThisContext(__dirname + "/nbody1d.js");
includeInThisContext(__dirname + "/wire_driver_sizing.js");

includeInThisContext(__dirname + "/xrun.js");
