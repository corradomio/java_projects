https://www.baeldung.com/java-matrix-multiplication
https://medium.com/swlh/programming-linear-algebra-in-java-vector-operations-6ba08fdd5a1a
https://en.wikipedia.org/wiki/Jblas:_Linear_Algebra_for_Java
https://lessthanoptimal.github.io/Java-Matrix-Benchmark/

http://la4j.org/
http://ejml.org/wiki/index.php?title=Main_Page

https://math.nist.gov/javanumerics/jama/
https://commons.apache.org/proper/commons-math/userguide/linear.html
https://sites.google.com/site/piotrwendykier/software
https://java-matrix.org/


Linear:
    r,u,v  : vectors
    R,A,B,C: matrices

    r = s*u + t*v
    r = s*u + t*C.v)

    R = s*A + t*B
    R = s*A + t*C.B)

oppure, alla BLAS

    r = s*u + v
    r = s*A.u + t*v
    R = s*A.B + t*C

    y = s*x + y
    y = s*A.x + t*y
    T.x = y


BLAS naming conventions
    https://www.netlib.org/lapack/lug/node24.html

Sparse BLAS naming conventions
    https://scc.ustc.edu.cn/zlsc/sugon/intel/mkl/mkl_manual/GUID-68706DC5-8ED9-46F3-9E74-BFBC3E7A06A6.htm

MKL
    https://scc.ustc.edu.cn/zlsc/sugon/intel/mkl/mkl_manual/index.htm#GUID-68706DC5-8ED9-46F3-9E74-BFBC3E7A06A6.htm