function bubble(A) {
  n = _McLib.length(A);
  mc_t8 = 1;
  mc_t7 = _McLib.sub(n, mc_t8);
  mc_t15 = 1;
  for(j = mc_t15; j <= mc_t7; j+=1) {
      mc_t9 = 1;
     mc_t6 = _McLib.sub(n, mc_t9);
     mc_t14 = 1;
     for(i = mc_t14; i <= mc_t6; i+=1) {
       mc_t1 = A[i-1];
       mc_t10 = 1;
       mc_t4 = _McLib.add(i, mc_t10);
       mc_t2 = A[mc_t4-1];
       mc_t13 = mc_t1 > mc_t2 ? true : false;
       if (mc_t13) {
          temp = A[i-1];
          mc_t11 = 1;
          mc_t5 = _McLib.add(i, mc_t11);
          mc_t0 = A[mc_t5-1];
          A[i-1] = mc_t0;
          mc_t12 = 1;
          mc_t3 = _McLib.add(i, mc_t12);
          A[mc_t3-1] = temp;
      } else {
      }
    }
  }
  x = A;
  return [x];
}