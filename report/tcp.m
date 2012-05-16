%lan
x = [81.8125 82.2265625 78.1953125 74.734375 75.78125 75.625 77.5390625 75.6015625 74.1484375 75.40625 76.7890625 76.78125 74.8984375 74.8984375 77.65625 77.296875 75.0078125 76.0625 75.8203125 74.03125 76.65625 77.2109375 75.578125 82.078125 76.9140625 75.2734375 76.578125 76.5390625 76.328125 76.0703125 76.640625 75.3125 75.3125 76.5234375 75.9296875 76.3671875 74.1484375 75.484375 74.7578125 73.5];
%wifi
x2=[10.9 11.4 10.7 10.9 10.8 10.8 10.7 10.5 10.6 10.6 10.6 10.5 11.1 12.1 11.7 11.7 11.5 11.7 11.5 10.7 10.4 10.6 10.5 11 10.8 12.1 10.4 12 10.9 10.6 11 11.7 12.2 11.4 12 11.7 12.3 11.9 12.1 12.2 ];
%DSL
x3=[0.123046875 0.094140625 0.1064453125 0.111328125 0.10546875 0.09541015625 0.1103515625 0.1103515625 0.109375 0.1142578125 0.109375 0.1103515625 0.1162109375 0.0830078125 0.111328125 0.1005859375 0.09736328125 0.0939453125 0.1044921875 0.1103515625 0.1142578125 0.1083984375 0.10546875 0.1005859375 0.1240234375 0.1064453125 0.1162109375 0.1083984375 0.1123046875 0.1005859375 0.1083984375 0.0912109375 0.1044921875 0.1083984375 0.103515625 0.099609375 0.1123046875 0.10546875 0.1142578125 0.111328125];
%dailup
x4 = [0.08583984375 0.0849609375 0.09716796875 0.103515625 0.091015625 0.06328125 0.0986328125 0.103515625 0.0986328125 0.09765625 0.103515625 0.0919921875 0.0912109375 0.09765625 0.111328125 0.08779296875 0.0681640625 0.09765625 0.1025390625 0.1015625 0.1025390625 0.1064453125 0.08740234375 0.092578125 0.099609375 0.1025390625 0.06318359375 0.006328125 0.008974609375 0.09609375 0.109375 0.021484375 0.0560546875 0.06533203125 0.069921875 0.10546875 0.0462890625 0.0580078125 0.0408203125 0.03212890625 ];

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
fprintf('s = %d\n',s);

%calc CI
z = 1.96; %from normal table
ciLow = mean - (z * ( s/sqrt(n) ) );
ciHigh = mean + (z * ( s/sqrt(n) ) );
fprintf('CI = [ %d , %d ]\n',ciLow,ciHigh);

%calc number of samples required
r = 1;
nReq = ceil( ( ( 100*z*s )/( r*mean ) )^2 );
fprintf('number of samples = %d\n',nReq);

%drawing Quantile-quantile plot
qqplot(x);

%drawing probability plot
probplot('normal',[rot90(x) rot90(x2) rot90(x3) rot90(x4)]);
legend('LAN','WIFI','ADSL','Dail Up','Location','N')

%drawing histogram plot
hist(x,30);
h = findobj(gca, 'Type','patch');
set(h(1), 'FaceColor','k', 'EdgeColor','r')

%drawing box plot
boxplot([rot90(x) rot90(x2) rot90(x3) rot90(x4)]);