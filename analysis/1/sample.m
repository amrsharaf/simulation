x = [16807, 282475249, ....]

%draw histogram
k = 100;
hist(x,k)
h = findobj(gca, 'Type','patch');
set(h(1), 'FaceColor','k', 'EdgeColor','r')

o = hist(x,k);
disp(o)
%get length of samples
n = length(x);

%calc expected value
e = n/k;

%calc D = segma((o_i -e)^2/e)
D = 0;
for i = 1:k
  D = D + ( ( (o(i) - e)^2 ) / e );
end 
fprintf('D = %d\n',D);

