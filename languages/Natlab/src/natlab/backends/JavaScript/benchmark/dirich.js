function dirich(max1) {
  f1 = 20;
  f2 = 180;
  f3 = 80;
  f4 = 0;
  a = 4;
  b = 4;
  h = 0.1;
  tol = 0.0001;
  mc_t11 = _McLib.div(a, h);
  mc_t10 = _McLib.ceil(mc_t11);
  mc_t81 = 1;
  n = _McLib.add(mc_t10, mc_t81);
  mc_t13 = _McLib.div(b, h);
  mc_t12 = _McLib.ceil(mc_t13);
  mc_t82 = 1;
  m = _McLib.add(mc_t12, mc_t82);
  mc_t22 = a;
  mc_t23 = _McLib.add(f1, f2);
  mc_t18 = _McLib.mul(mc_t22, mc_t23);
  mc_t20 = b;
  mc_t21 = _McLib.add(f3, f4);
  mc_t19 = _McLib.mul(mc_t20, mc_t21);
  mc_t14 = _McLib.add(mc_t18, mc_t19);
  mc_t83 = 2;
  mc_t16 = _McLib.mul(mc_t83, a);
  mc_t84 = 2;
  mc_t17 = _McLib.mul(mc_t84, b);
  mc_t15 = _McLib.add(mc_t16, mc_t17);
  ave = _McLib.div(mc_t14, mc_t15);
  mc_t24 = ave;
  mc_t25 = _McLib.ones(n, m);
  U = _McLib.mul(mc_t24, mc_t25);
  mc_t86 = 1;
  for(l = mc_t86; l <= m; l+=1) {
     mc_t85 = 1;
     U[mc_t85-1][l-1] = f3;
     U[n-1][l-1] = f4;
  }
  mc_t88 = 1;
  for(k = mc_t88; k <= n; k+=1) {
     mc_t87 = 1;
     U[k-1][mc_t87-1] = f1;
     U[k-1][m-1] = f2;
  }
  mc_t89 = 1;
  mc_t90 = 2;
  mc_t27 = U[mc_t89-1][mc_t90-1];
  mc_t91 = 2;
  mc_t92 = 1;
  mc_t28 = U[mc_t91-1][mc_t92-1];
  mc_t26 = _McLib.add(mc_t27, mc_t28);
  mc_t93 = 2;
  mc_t0 = _McLib.div(mc_t26, mc_t93);
  mc_t94 = 1;
  mc_t95 = 1;
  U[mc_t94-1][mc_t95-1] = mc_t0;
  mc_t96 = 1;
  mc_t32 = _McLib.sub(m, mc_t96);
  mc_t97 = 1;
  mc_t30 = U[mc_t97-1][mc_t32-1];
  mc_t98 = 2;
  mc_t31 = U[mc_t98-1][m-1];
  mc_t29 = _McLib.add(mc_t30, mc_t31);
  mc_t99 = 2;
  mc_t1 = _McLib.div(mc_t29, mc_t99);
  mc_t100 = 1;
  U[mc_t100-1][m-1] = mc_t1;
  mc_t101 = 1;
  mc_t36 = _McLib.sub(n, mc_t101);
  mc_t102 = 1;
  mc_t34 = U[mc_t36-1][mc_t102-1];
  mc_t103 = 2;
  mc_t35 = U[n-1][mc_t103-1];
  mc_t33 = _McLib.add(mc_t34, mc_t35);
  mc_t104 = 2;
  mc_t2 = _McLib.div(mc_t33, mc_t104);
  mc_t105 = 1;
  U[n-1][mc_t105-1] = mc_t2;
  mc_t106 = 1;
  mc_t42 = _McLib.sub(n, mc_t106);
  mc_t43 = m;
  mc_t38 = U[mc_t42-1][mc_t43-1];
  mc_t40 = n;
  mc_t107 = 1;
  mc_t41 = _McLib.sub(m, mc_t107);
  mc_t39 = U[mc_t40-1][mc_t41-1];
  mc_t37 = _McLib.add(mc_t38, mc_t39);
  mc_t108 = 2;
  mc_t3 = _McLib.div(mc_t37, mc_t108);
  U[n-1][m-1] = mc_t3;
  mc_t55 = Math.PI;
  mc_t109 = 1;
  mc_t56 = _McLib.sub(n, mc_t109);
  mc_t54 = _McLib.div(mc_t55, mc_t56);
  mc_t49 = _McLib.cos(mc_t54);
  mc_t52 = Math.PI;
  mc_t110 = 1;
  mc_t53 = _McLib.sub(m, mc_t110);
  mc_t51 = _McLib.div(mc_t52, mc_t53);
  mc_t50 = _McLib.cos(mc_t51);
  mc_t48 = _McLib.add(mc_t49, mc_t50);
  mc_t111 = 2;
  mc_t47 = _McLib.pow(mc_t48, mc_t111);
  mc_t112 = 4;
  mc_t46 = _McLib.sub(mc_t112, mc_t47);
  mc_t45 = _McLib.sqrt(mc_t46);
  mc_t113 = 2;
  mc_t44 = _McLib.add(mc_t113, mc_t45);
  mc_t114 = 4;
  w = _McLib.div(mc_t114, mc_t44);
  err = 1;
  cnt = 0;
  mc_t6 = err > tol ? true : false;
  if (mc_t6) {
        mc_t7 = cnt <= max1 ? true : false;
        mc_t5 = mc_t6 && mc_t7 ? true : false;
  } else {
        mc_t5 = mc_t6;
  }
  while (mc_t5) {
     err = 0;
     mc_t115 = 1;
     mc_t80 = _McLib.sub(m, mc_t115);
     mc_t125 = 2;
     for(l = mc_t125; l <= mc_t80; l+=1) {
       mc_t116 = 1;
       mc_t79 = _McLib.sub(n, mc_t116);
       mc_t124 = 2;
       for(k = mc_t124; k <= mc_t79; k+=1) {
         mc_t58 = w;
         mc_t75 = k;
         mc_t117 = 1;
         mc_t76 = _McLib.add(l, mc_t117);
         mc_t71 = U[mc_t75-1][mc_t76-1];
         mc_t73 = k;
         mc_t118 = 1;
         mc_t74 = _McLib.sub(l, mc_t118);
         mc_t72 = U[mc_t73-1][mc_t74-1];
         mc_t67 = _McLib.add(mc_t71, mc_t72);
         mc_t119 = 1;
         mc_t69 = _McLib.add(k, mc_t119);
         mc_t70 = l;
         mc_t68 = U[mc_t69-1][mc_t70-1];
         mc_t63 = _McLib.add(mc_t67, mc_t68);
         mc_t120 = 1;
         mc_t65 = _McLib.sub(k, mc_t120);
         mc_t66 = l;
         mc_t64 = U[mc_t65-1][mc_t66-1];
         mc_t60 = _McLib.add(mc_t63, mc_t64);
         mc_t62 = U[k-1][l-1];
         mc_t121 = 4;
         mc_t61 = _McLib.mul(mc_t121, mc_t62);
         mc_t59 = _McLib.sub(mc_t60, mc_t61);
         mc_t57 = _McLib.mul(mc_t58, mc_t59);
         mc_t122 = 4;
         relx = _McLib.div(mc_t57, mc_t122);
         mc_t77 = U[k-1][l-1];
         mc_t78 = relx;
         mc_t4 = _McLib.add(mc_t77, mc_t78);
         U[k-1][l-1] = mc_t4;
         mc_t9 = _McLib.abs(relx);
         mc_t123 = err <= mc_t9 ? true : false;
         if (mc_t123) {
            err = _McLib.abs(relx);
        } else {
        }
      }
    }
    mc_t126 = 1;
    cnt = _McLib.add(cnt, mc_t126);
    mc_t6 = err > tol ? true : false;
    if (mc_t6) {
        mc_t7 = cnt <= max1 ? true : false;
        mc_t5 = mc_t6 && mc_t7 ? true : false;
    } else {
        mc_t5 = mc_t6;
    }
  }
  return [U];
}