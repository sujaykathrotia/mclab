function [result] = u22_auto(n)
  x0 = uint64([0.237964627092]*n);
  x1 = char(32+80*[0.236048089737]*n);
  result = colon(x0,x1);
end