x = dlmread('sim.txt');
disp(x)
%get number of samples and number of replications
sampleSize = size(x);
m = sampleSize(1);
n = sampleSize(2);
fprintf('m = %d  n = %d\n',m,n);
%calc mean for each replication
x_bar = mean(x,2);

%calc the overall mean 
x_bar_bar = mean(x_bar);
fprintf('Average = %f\n',x_bar_bar);

%calc variance of replicate mean
var = 0;
for i = 1:m
    var = var+(x_bar(i)-x_bar_bar)^2;
end
var = (var/(m-1));
fprintf('Standard Diviation = %f\n',sqrt(var));

%calc CI
z = 0.006; %from normal table %1-alpha = 0.005
ciLow = x_bar_bar - (z *  sqrt(var/m) );
ciHigh = x_bar_bar + (z *  sqrt(var/m) ); 
fprintf('CI = [ %d , %d ]\n',ciLow,ciHigh);

%draw histogram
hist(x_bar,15)
h = findobj(gca, 'Type','patch');
set(h(1), 'FaceColor','k', 'EdgeColor','r')

plot(x_bar)
xlabel('Time(Hour)');
ylabel('Throughput');
title('Hourly Throughput');

