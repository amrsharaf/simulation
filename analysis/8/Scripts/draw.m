q = dlmread('q.txt',',');

t = dlmread('t.txt',',');
plot(t,q)
xlabel('Time');
ylabel('Queue Length');
title('Inspection Queue');
