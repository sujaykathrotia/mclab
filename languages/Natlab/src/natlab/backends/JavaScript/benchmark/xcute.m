nmTimes = 10;

disp('-----------Bubble------------');

for i=1:nmTimes
	input = rand(800);
	t_1 = clock();
	bubble(input);
	t_2 = clock();
	a(i) = etime(t_2,t_1);
end

disp(min(a));
disp(max(a));
disp(mean(a));


disp('-----------Dirich------------');

for i=1:nmTimes
	input = 3000;;
	t_1 = clock();
	dirich(input);
	t_2 = clock();
	a(i) = etime(t_2,t_1);
end

disp(min(a));
disp(max(a));
disp(mean(a));


disp('-----------Wire_Driver_Sizing------------');

for i=1:nmTimes
	input = 100;;
	t_1 = clock();
	wire_driver_sizing(input);
	t_2 = clock();
	a(i) = etime(t_2,t_1);
end

disp(min(a));
disp(max(a));
disp(mean(a));
