x = dlmread('0-response.txt','\n');
disp(x)

%clac mean 
n = length(x);
mean = sum(x)/n;
fprintf('mean = %d\n',mean);

%calc sample variance
sTemp = 0;
for i = 1:n
    sTemp = sTemp + (x(i)-mean)^2;
end
s = sqrt( (1 / (n-1) ) * sTemp );
fprintf('standard diviation = %d\n',s);

%calc CI
z = 1.645; %from normal table
ciLow = mean - (z * ( s/sqrt(n) ) );
ciHigh = mean + (z * ( s/sqrt(n) ) );
fprintf('CI = [ %d , %d ]\n',ciLow,ciHigh);

hist(x,15)
h = findobj(gca, 'Type','patch');
set(h(1), 'FaceColor','k', 'EdgeColor','r')

