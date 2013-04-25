function fibonacci(n) {
  mc_t5 = 1;
  f = _McLib.zeros(n, mc_t5);
  mc_t6 = 1;
  mc_t7 = 1;
  f[mc_t7-1] = mc_t6;
  mc_t8 = 2;
  mc_t9 = 2;
  f[mc_t9-1] = mc_t8;
  mc_t12 = 3;
  for(k = mc_t12; k <= n; k+=1) {
     mc_t10 = 1;
     mc_t4 = _McLib.sub(k, mc_t10);
     mc_t1 = f[mc_t4-1];
     mc_t11 = 2;
     mc_t3 = _McLib.sub(k, mc_t11);
     mc_t2 = f[mc_t3-1];
     mc_t0 = _McLib.add(mc_t1, mc_t2);
     f[k-1] = mc_t0;
  }
  return [f];
}