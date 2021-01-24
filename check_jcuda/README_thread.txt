Thread hierarchy
---------------

Definition: __global__ void function(T* p1, ...)

Call:   function<<<numBlocks, threadsBlock>>>(a1, ...)          // si capisce meglio!!!
        function<<<gridDim, blockDim>>>(a1, ...)
        function<<<gridDim, blockDim, ?, stream>>>(1, ...)


    grid
        block           (max 1024 threads per block)
            thread      (max 128 running threads in each moment)
                warp


Memory Hierarchy
----------------

    thread: private memory, shared block memory, global memory, constant (read-only), texture (read-only)
    block:  shared memory