function [result] = u0_auto(n)
  x0 = double([0.844421851525]*n);
  result = sech(x0);
end