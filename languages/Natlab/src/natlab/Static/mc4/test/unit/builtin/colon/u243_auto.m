function [result] = u243_auto(n)
  x0 = ([0.62290169489]*n > .5);
  x1 = char(32+80*[0.236048089737]*n);
  x2 = uint64([0.237964627092]*n);
  result = colon(x0,x1,x2);
end