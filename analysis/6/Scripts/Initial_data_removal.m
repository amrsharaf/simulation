x = dlmread('sim.txt');

%get number of samples and number of replications
sampleSize = size(x);
m = sampleSize(1);
n = sampleSize(2);
fprintf('m = %d , n = %d \n',m,n);

%calc mean trajectory ... X_bar 
x_bar = mean(x);
disp(x_bar)

%calc overall mean ... X_bar_bar
x_bar_bar = mean(x_bar);

%calc relative change
rc = []; %realtive change arraydlmread('myfile.txt')
for l = 1:n-1
    x_bar_bar_l = mean(x_bar(l+1:n));
    rc(l) = (x_bar_bar_l- x_bar_bar)/(x_bar_bar);
end

%draw realtive change graph
%plot(x_bar)
plot(rc)
xlabel('L')
ylabel('Relative Change')

