% args: {n=(double,[1, 1],{REAL})}
function  [f] = fibonacci(n)
  mc_t5 = 1;                          % mc_t5=(double,1.0,[1, 1],{REAL})
  [f] = zeros(n, mc_t5);              % f=(double,[?, 1],{REAL})
  mc_t6 = 1;                          % mc_t6=(double,1.0,[1, 1],{REAL})
  mc_t7 = 1;                          % mc_t7=(double,1.0,[1, 1],{REAL})
  f(mc_t7) = mc_t6;                   % f=(double,[?, 1],{REAL})
  mc_t8 = 2;                          % mc_t8=(double,2.0,[1, 1],{REAL})
  mc_t9 = 2;                          % mc_t9=(double,2.0,[1, 1],{REAL})
  f(mc_t9) = mc_t8;                   % f=(double,[?, 1],{REAL})
  mc_t12 = 3;                         % mc_t12=(double,3.0,[1, 1],{REAL})
  for k = (mc_t12 : n);
    mc_t10 = 1;                         % mc_t10=(double,1.0,[1, 1],{REAL})
    [mc_t4] = minus(k, mc_t10);         % mc_t4=(double,[1, 1],{REAL})
    [mc_t1] = f(mc_t4);                 % mc_t1=(double,[1, 1],{REAL})
    mc_t11 = 2;                         % mc_t11=(double,2.0,[1, 1],{REAL})
    [mc_t3] = minus(k, mc_t11);         % mc_t3=(double,[1, 1],{REAL})
    [mc_t2] = f(mc_t3);                 % mc_t2=(double,[1, 1],{REAL})
    [mc_t0] = plus(mc_t1, mc_t2);       % mc_t0=(double,[1, 1],{REAL})
    f(k) = mc_t0;                       % f=(double,[?, 1],{REAL})
  end
end
% results: [(double,[?, 1],{REAL})]