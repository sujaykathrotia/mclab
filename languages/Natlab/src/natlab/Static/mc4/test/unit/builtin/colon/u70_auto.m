function [result] = u70_auto(n)
  x0 = single([0.844421851525]*n);
  x1 = ([0.62290169489]*n > .5);
  x2 = char(32+80*[0.236048089737]*n);
  result = colon(x0,x1,x2);
end