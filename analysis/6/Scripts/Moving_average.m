x = dlmread('sim.txt');
disp(x)

%get number of samples and number of replications
sampleSize = size(x);
m = sampleSize(1);
n = sampleSize(2);

%calc mean trajectory ... X_bar 
x_bar = mean(x);

k = 30;  %smoothing factor

%J = 1:n-k;
x_bar_bar = [];  %moving average tarajectory
for j = k+1:n-k
   x_bar_bar(j) = mean(x_bar(j-k:j+k));
end

%smoothing the first part of the array 1 => k
x_bar_bar(1) = x_bar(1);
for i = 2:k
    x_bar_bar(i) = mean(x_bar(1:2*i-1));
end
%fprintf('%d  %d\n',length(J) , length(x_bar_bar));
plot(x_bar_bar);