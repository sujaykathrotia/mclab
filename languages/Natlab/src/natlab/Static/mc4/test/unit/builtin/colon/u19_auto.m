function [result] = u19_auto(n)
  x0 = uint64([0.237964627092]*n);
  x1 = double([0.134364244112]*n);
  result = colon(x0,x1);
end