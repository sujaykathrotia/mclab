function [result] = u152_auto(n)
  x0 = uint64([0.237964627092]*n);
  x1 = double([0.134364244112]*n);
  x2 = int8([0.956034271889]*n);
  result = colon(x0,x1,x2);
end