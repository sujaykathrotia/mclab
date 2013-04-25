% args: {max1=(double,[1, 1],{REAL})}
function  [U] = dirich(max1)
  f1 = 20;                            % f1=(double,20.0,[1, 1],{REAL})
  f2 = 180;                           % f2=(double,180.0,[1, 1],{REAL})
  f3 = 80;                            % f3=(double,80.0,[1, 1],{REAL})
  f4 = 0;                             % f4=(double,0.0,[1, 1],{REAL})
  a = 4;                              % a=(double,4.0,[1, 1],{REAL})
  b = 4;                              % b=(double,4.0,[1, 1],{REAL})
  h = 0.1;                            % h=(double,0.1,[1, 1],{REAL})
  tol = 0.0001;                       % tol=(double,1.0E-4,[1, 1],{REAL})
  [mc_t11] = mrdivide(a, h);          % mc_t11=(double,[1, 1],{REAL})
  [mc_t10] = ceil(mc_t11);            % mc_t10=(double,[1, 1],{REAL})
  mc_t81 = 1;                         % mc_t81=(double,1.0,[1, 1],{REAL})
  [n] = plus(mc_t10, mc_t81);         % n=(double,[1, 1],{REAL})
  [mc_t13] = mrdivide(b, h);          % mc_t13=(double,[1, 1],{REAL})
  [mc_t12] = ceil(mc_t13);            % mc_t12=(double,[1, 1],{REAL})
  mc_t82 = 1;                         % mc_t82=(double,1.0,[1, 1],{REAL})
  [m] = plus(mc_t12, mc_t82);         % m=(double,[1, 1],{REAL})
  mc_t22 = a;                         % mc_t22=(double,4.0,[1, 1],{REAL})
  [mc_t23] = plus(f1, f2);            % mc_t23=(double,[1, 1],{REAL})
  [mc_t18] = mtimes(mc_t22, mc_t23);  % mc_t18=(double,[1, 1],{REAL})
  mc_t20 = b;                         % mc_t20=(double,4.0,[1, 1],{REAL})
  [mc_t21] = plus(f3, f4);            % mc_t21=(double,[1, 1],{REAL})
  [mc_t19] = mtimes(mc_t20, mc_t21);  % mc_t19=(double,[1, 1],{REAL})
  [mc_t14] = plus(mc_t18, mc_t19);    % mc_t14=(double,[1, 1],{REAL})
  mc_t83 = 2;                         % mc_t83=(double,2.0,[1, 1],{REAL})
  [mc_t16] = mtimes(mc_t83, a);       % mc_t16=(double,[1, 1],{REAL})
  mc_t84 = 2;                         % mc_t84=(double,2.0,[1, 1],{REAL})
  [mc_t17] = mtimes(mc_t84, b);       % mc_t17=(double,[1, 1],{REAL})
  [mc_t15] = plus(mc_t16, mc_t17);    % mc_t15=(double,[1, 1],{REAL})
  [ave] = mrdivide(mc_t14, mc_t15);   % ave=(double,[1, 1],{REAL})
  mc_t24 = ave;                       % mc_t24=(double,[1, 1],{REAL})
  [mc_t25] = ones(n, m);              % mc_t25=(double,[?, ?],{REAL})
  [U] = mtimes(mc_t24, mc_t25);       % U=(double,[?, ?],{REAL})
  mc_t86 = 1;                         % mc_t86=(double,1.0,[1, 1],{REAL})
  for l = (mc_t86 : m);
    mc_t85 = 1;                         % mc_t85=(double,1.0,[1, 1],{REAL})
    U(mc_t85, l) = f3;                  % U=(double,[?, ?],{REAL})
    U(n, l) = f4;                       % U=(double,[?, ?],{REAL})
  end
  mc_t88 = 1;                         % mc_t88=(double,1.0,[1, 1],{REAL})
  for k = (mc_t88 : n);
    mc_t87 = 1;                         % mc_t87=(double,1.0,[1, 1],{REAL})
    U(k, mc_t87) = f1;                  % U=(double,[?, ?],{REAL})
    U(k, m) = f2;                       % U=(double,[?, ?],{REAL})
  end
  mc_t89 = 1;                         % mc_t89=(double,1.0,[1, 1],{REAL})
  mc_t90 = 2;                         % mc_t90=(double,2.0,[1, 1],{REAL})
  [mc_t27] = U(mc_t89, mc_t90);       % mc_t27=(double,[1, 1],{REAL})
  mc_t91 = 2;                         % mc_t91=(double,2.0,[1, 1],{REAL})
  mc_t92 = 1;                         % mc_t92=(double,1.0,[1, 1],{REAL})
  [mc_t28] = U(mc_t91, mc_t92);       % mc_t28=(double,[1, 1],{REAL})
  [mc_t26] = plus(mc_t27, mc_t28);    % mc_t26=(double,[1, 1],{REAL})
  mc_t93 = 2;                         % mc_t93=(double,2.0,[1, 1],{REAL})
  [mc_t0] = mrdivide(mc_t26, mc_t93); % mc_t0=(double,[1, 1],{REAL})
  mc_t94 = 1;                         % mc_t94=(double,1.0,[1, 1],{REAL})
  mc_t95 = 1;                         % mc_t95=(double,1.0,[1, 1],{REAL})
  U(mc_t94, mc_t95) = mc_t0;          % U=(double,[?, ?],{REAL})
  mc_t96 = 1;                         % mc_t96=(double,1.0,[1, 1],{REAL})
  [mc_t32] = minus(m, mc_t96);        % mc_t32=(double,[1, 1],{REAL})
  mc_t97 = 1;                         % mc_t97=(double,1.0,[1, 1],{REAL})
  [mc_t30] = U(mc_t97, mc_t32);       % mc_t30=(double,[1, 1],{REAL})
  mc_t98 = 2;                         % mc_t98=(double,2.0,[1, 1],{REAL})
  [mc_t31] = U(mc_t98, m);            % mc_t31=(double,[1, 1],{REAL})
  [mc_t29] = plus(mc_t30, mc_t31);    % mc_t29=(double,[1, 1],{REAL})
  mc_t99 = 2;                         % mc_t99=(double,2.0,[1, 1],{REAL})
  [mc_t1] = mrdivide(mc_t29, mc_t99); % mc_t1=(double,[1, 1],{REAL})
  mc_t100 = 1;                        % mc_t100=(double,1.0,[1, 1],{REAL})
  U(mc_t100, m) = mc_t1;              % U=(double,[?, ?],{REAL})
  mc_t101 = 1;                        % mc_t101=(double,1.0,[1, 1],{REAL})
  [mc_t36] = minus(n, mc_t101);       % mc_t36=(double,[1, 1],{REAL})
  mc_t102 = 1;                        % mc_t102=(double,1.0,[1, 1],{REAL})
  [mc_t34] = U(mc_t36, mc_t102);      % mc_t34=(double,[1, 1],{REAL})
  mc_t103 = 2;                        % mc_t103=(double,2.0,[1, 1],{REAL})
  [mc_t35] = U(n, mc_t103);           % mc_t35=(double,[1, 1],{REAL})
  [mc_t33] = plus(mc_t34, mc_t35);    % mc_t33=(double,[1, 1],{REAL})
  mc_t104 = 2;                        % mc_t104=(double,2.0,[1, 1],{REAL})
  [mc_t2] = mrdivide(mc_t33, mc_t104); % mc_t2=(double,[1, 1],{REAL})
  mc_t105 = 1;                        % mc_t105=(double,1.0,[1, 1],{REAL})
  U(n, mc_t105) = mc_t2;              % U=(double,[?, ?],{REAL})
  mc_t106 = 1;                        % mc_t106=(double,1.0,[1, 1],{REAL})
  [mc_t42] = minus(n, mc_t106);       % mc_t42=(double,[1, 1],{REAL})
  mc_t43 = m;                         % mc_t43=(double,[1, 1],{REAL})
  [mc_t38] = U(mc_t42, mc_t43);       % mc_t38=(double,[1, 1],{REAL})
  mc_t40 = n;                         % mc_t40=(double,[1, 1],{REAL})
  mc_t107 = 1;                        % mc_t107=(double,1.0,[1, 1],{REAL})
  [mc_t41] = minus(m, mc_t107);       % mc_t41=(double,[1, 1],{REAL})
  [mc_t39] = U(mc_t40, mc_t41);       % mc_t39=(double,[1, 1],{REAL})
  [mc_t37] = plus(mc_t38, mc_t39);    % mc_t37=(double,[1, 1],{REAL})
  mc_t108 = 2;                        % mc_t108=(double,2.0,[1, 1],{REAL})
  [mc_t3] = mrdivide(mc_t37, mc_t108); % mc_t3=(double,[1, 1],{REAL})
  U(n, m) = mc_t3;                    % U=(double,[?, ?],{REAL})
  [mc_t55] = pi();                    % mc_t55=(double,3.141592653589793,[1, 1],{REAL})
  mc_t109 = 1;                        % mc_t109=(double,1.0,[1, 1],{REAL})
  [mc_t56] = minus(n, mc_t109);       % mc_t56=(double,[1, 1],{REAL})
  [mc_t54] = mrdivide(mc_t55, mc_t56); % mc_t54=(double,[1, 1],{REAL})
  [mc_t49] = cos(mc_t54);             % mc_t49=(double,[1, 1],{REAL})
  [mc_t52] = pi();                    % mc_t52=(double,3.141592653589793,[1, 1],{REAL})
  mc_t110 = 1;                        % mc_t110=(double,1.0,[1, 1],{REAL})
  [mc_t53] = minus(m, mc_t110);       % mc_t53=(double,[1, 1],{REAL})
  [mc_t51] = mrdivide(mc_t52, mc_t53); % mc_t51=(double,[1, 1],{REAL})
  [mc_t50] = cos(mc_t51);             % mc_t50=(double,[1, 1],{REAL})
  [mc_t48] = plus(mc_t49, mc_t50);    % mc_t48=(double,[1, 1],{REAL})
  mc_t111 = 2;                        % mc_t111=(double,2.0,[1, 1],{REAL})
  [mc_t47] = mpower(mc_t48, mc_t111); % mc_t47=(double,[1, 1],{REAL})
  mc_t112 = 4;                        % mc_t112=(double,4.0,[1, 1],{REAL})
  [mc_t46] = minus(mc_t112, mc_t47);  % mc_t46=(double,[1, 1],{REAL})
  [mc_t45] = sqrt(mc_t46);            % mc_t45=(double,[1, 1],{REAL})
  mc_t113 = 2;                        % mc_t113=(double,2.0,[1, 1],{REAL})
  [mc_t44] = plus(mc_t113, mc_t45);   % mc_t44=(double,[1, 1],{REAL})
  mc_t114 = 4;                        % mc_t114=(double,4.0,[1, 1],{REAL})
  [w] = mrdivide(mc_t114, mc_t44);    % w=(double,[1, 1],{REAL})
  err = 1;                            % err=(double,1.0,[1, 1],{REAL})
  cnt = 0;                            % cnt=(double,0.0,[1, 1],{REAL})
  [mc_t6] = gt(err, tol);             % mc_t6=(logical,[1, 1],{REAL})
  if mc_t6
      [mc_t7] = le(cnt, max1);            % mc_t7=(logical,[1, 1],{REAL})
      [mc_t5] = and(mc_t6, mc_t7);        % mc_t5=(logical,[1, 1],{REAL})
  else
      mc_t5 = mc_t6;                      % mc_t5=(logical,[1, 1],{REAL})
  end
  while mc_t5
    err = 0;                            % err=(double,0.0,[1, 1],{REAL})
    mc_t115 = 1;                        % mc_t115=(double,1.0,[1, 1],{REAL})
    [mc_t80] = minus(m, mc_t115);       % mc_t80=(double,[1, 1],{REAL})
    mc_t125 = 2;                        % mc_t125=(double,2.0,[1, 1],{REAL})
    for l = (mc_t125 : mc_t80);
      mc_t116 = 1;                        % mc_t116=(double,1.0,[1, 1],{REAL})
      [mc_t79] = minus(n, mc_t116);       % mc_t79=(double,[1, 1],{REAL})
      mc_t124 = 2;                        % mc_t124=(double,2.0,[1, 1],{REAL})
      for k = (mc_t124 : mc_t79);
        mc_t58 = w;                         % mc_t58=(double,[1, 1],{REAL})
        mc_t75 = k;                         % mc_t75=(double,[1, 1],{REAL})
        mc_t117 = 1;                        % mc_t117=(double,1.0,[1, 1],{REAL})
        [mc_t76] = plus(l, mc_t117);        % mc_t76=(double,[1, 1],{REAL})
        [mc_t71] = U(mc_t75, mc_t76);       % mc_t71=(double,[1, 1],{REAL})
        mc_t73 = k;                         % mc_t73=(double,[1, 1],{REAL})
        mc_t118 = 1;                        % mc_t118=(double,1.0,[1, 1],{REAL})
        [mc_t74] = minus(l, mc_t118);       % mc_t74=(double,[1, 1],{REAL})
        [mc_t72] = U(mc_t73, mc_t74);       % mc_t72=(double,[1, 1],{REAL})
        [mc_t67] = plus(mc_t71, mc_t72);    % mc_t67=(double,[1, 1],{REAL})
        mc_t119 = 1;                        % mc_t119=(double,1.0,[1, 1],{REAL})
        [mc_t69] = plus(k, mc_t119);        % mc_t69=(double,[1, 1],{REAL})
        mc_t70 = l;                         % mc_t70=(double,[1, 1],{REAL})
        [mc_t68] = U(mc_t69, mc_t70);       % mc_t68=(double,[1, 1],{REAL})
        [mc_t63] = plus(mc_t67, mc_t68);    % mc_t63=(double,[1, 1],{REAL})
        mc_t120 = 1;                        % mc_t120=(double,1.0,[1, 1],{REAL})
        [mc_t65] = minus(k, mc_t120);       % mc_t65=(double,[1, 1],{REAL})
        mc_t66 = l;                         % mc_t66=(double,[1, 1],{REAL})
        [mc_t64] = U(mc_t65, mc_t66);       % mc_t64=(double,[1, 1],{REAL})
        [mc_t60] = plus(mc_t63, mc_t64);    % mc_t60=(double,[1, 1],{REAL})
        [mc_t62] = U(k, l);                 % mc_t62=(double,[1, 1],{REAL})
        mc_t121 = 4;                        % mc_t121=(double,4.0,[1, 1],{REAL})
        [mc_t61] = mtimes(mc_t121, mc_t62); % mc_t61=(double,[1, 1],{REAL})
        [mc_t59] = minus(mc_t60, mc_t61);   % mc_t59=(double,[1, 1],{REAL})
        [mc_t57] = mtimes(mc_t58, mc_t59);  % mc_t57=(double,[1, 1],{REAL})
        mc_t122 = 4;                        % mc_t122=(double,4.0,[1, 1],{REAL})
        [relx] = mrdivide(mc_t57, mc_t122); % relx=(double,[1, 1],{REAL})
        [mc_t77] = U(k, l);                 % mc_t77=(double,[1, 1],{REAL})
        mc_t78 = relx;                      % mc_t78=(double,[1, 1],{REAL})
        [mc_t4] = plus(mc_t77, mc_t78);     % mc_t4=(double,[1, 1],{REAL})
        U(k, l) = mc_t4;                    % U=(double,[?, ?],{REAL})
        [mc_t9] = abs(relx);                % mc_t9=(double,[1, 1],{REAL})
        [mc_t123] = le(err, mc_t9);         % mc_t123=(logical,[1, 1],{REAL})
        if mc_t123
          [err] = abs(relx);                  % err=(double,[1, 1],{REAL})
        else
        end
      end
    end
    mc_t126 = 1;                        % mc_t126=(double,1.0,[1, 1],{REAL})
    [cnt] = plus(cnt, mc_t126);         % cnt=(double,[1, 1],{REAL})
    [mc_t6] = gt(err, tol);             % mc_t6=(logical,[1, 1],{REAL})
    if mc_t6
      [mc_t7] = le(cnt, max1);            % mc_t7=(logical,[1, 1],{REAL})
      [mc_t5] = and(mc_t6, mc_t7);        % mc_t5=(logical,[1, 1],{REAL})
    else
      mc_t5 = mc_t6;                      % mc_t5=(logical,[1, 1],{REAL})
    end
  end
end
% results: [(double,[?, ?],{REAL})]