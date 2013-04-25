function disp(x) {
	console.log(x);
}


// number of time benchmarks will run
nmTime = 10;



disp("---------------------Bubble---------------------\n");
benchmark_time = [];

for(var times = 0; times < nmTime; times++) {
	input = _McLib.random(800);
	var start = +new Date().getTime();
	bubble(input);
	var end =  +new Date().getTime();

	var diff = end - start;
	benchmark_time.push(diff);
}

disp( _McLib.min(benchmark_time));
disp( _McLib.max(benchmark_time));
disp( _McLib.average(benchmark_time));


disp("---------------------Dirich---------------------\n");
benchmark_time = [];

for(var times = 0; times < nmTime; times++) {
	input = 3000;
	var start = +new Date().getTime();
	dirich(input);
	var end =  +new Date().getTime();

	var diff = end - start;
	benchmark_time.push(diff);
}

disp( _McLib.min(benchmark_time));
disp( _McLib.max(benchmark_time));
disp( _McLib.average(benchmark_time));


disp("----------------Wire_Driver_Sizing--------------\n");
benchmark_time = [];

for(var times = 0; times < nmTime; times++) {
	input = 100;
	var start = +new Date().getTime();
	wire_driver_sizing(input);
	var end =  +new Date().getTime();

	var diff = end - start;
	benchmark_time.push(diff);
}

disp( _McLib.min(benchmark_time));
disp( _McLib.max(benchmark_time));
disp( _McLib.average(benchmark_time));
