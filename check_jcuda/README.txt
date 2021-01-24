https://docs.nvidia.com/cuda/cuda-c-programming-guide/index.html#c-language-extensions

Function Execution Space Specifiers
-----------------------------------
__global__
    The __global__ exection space specifier declares a function as being a kernel. Such a function is:

        Executed on the device,
        Callable from the host,
        Callable from the device for devices of compute capability 3.2 or higher (see CUDA Dynamic Parallelism for more details).

    A __global__ function must have void return type, and cannot be a member of a class.

    Any call to a __global__ function must specify its execution configuration as described in Execution Configuration.

    A call to a __global__ function is asynchronous, meaning it returns before the device has completed its execution.


__device__
    The __device__ execution space specifier declares a function that is:

        Executed on the device,
        Callable from the device only.

    The __global__ and __device__ execution space specifiers cannot be used together.


__noinline__ __forceinline__
    The compiler inlines any __device__ function when deemed appropriate.
    The __noinline__ function qualifier can be used as a hint for the compiler not to inline the function if possible.
    The __forceinline__ function qualifier can be used to force the compiler to inline the function.
    The __noinline__ and __forceinline__ function qualifiers cannot be used together, and neither function qualifier can be applied to an inline function.


Variable Memory Space Specifiers
--------------------------------

__device__
    The __device__ memory space specifier declares a variable that resides on the device.

    At most one of the other memory space specifiers defined in the next three sections may be used together with __device__ to further denote which memory space the variable belongs to. If none of them is present, the variable:

        Resides in global memory space,
        Has the lifetime of the CUDA context in which it is created,
        Has a distinct object per device,
        Is accessible from all the threads within the grid and from the host through the runtime library (cudaGetSymbolAddress() / cudaGetSymbolSize() / cudaMemcpyToSymbol() / cudaMemcpyFromSymbol()).

__constant__
    The __constant__ memory space specifier, optionally used together with __device__, declares a variable that:

        Resides in constant memory space,
        Has the lifetime of the CUDA context in which it is created,
        Has a distinct object per device,
        Is accessible from all the threads within the grid and from the host through the runtime library (cudaGetSymbolAddress() / cudaGetSymbolSize() / cudaMemcpyToSymbol() / cudaMemcpyFromSymbol()).

__shared__
    The __shared__ memory space specifier, optionally used together with __device__, declares a variable that:

        Resides in the shared memory space of a thread block,
        Has the lifetime of the block,
        Has a distinct object per block,
        Is only accessible from all the threads within the block,
        Does not have a constant address.

__managed__
    The __managed__ memory space specifier, optionally used together with __device__, declares a variable that:

        Can be referenced from both device and host code, e.g., its address can be taken or it can be read or written directly from a device or host function.
        Has the lifetime of an application.

__restrict__
    Restricted pointers: different pointers are alias of the same memory location


Built-in Vector Types
---------------------

    <u>?<type><size>:

    <u>    ::= unsigned
    <size> ::= 1,2,3,4.  Components  x,y,z,w       (meglio usare 1,2,4, NON 3)
    <type> ::= char (1), short (2), int (4), long (4|8?), long long (8)
               float (4), double (8)

    dim3 == uint3


Built-in Variables
------------------

    gridDim:    dim3
    blockIdx:   dim3
    blockDim:   dim3
    threadIdx:  dim3
    warpSize:   int


Memory Fence Functions
----------------------

void __threadfence_block();
    ensures that:

        All writes to all memory made by the calling thread before the call to __threadfence_block() are observed by all threads in the block of the calling thread as occurring before all writes to all memory made by the calling thread after the call to __threadfence_block();
        All reads from all memory made by the calling thread before the call to __threadfence_block() are ordered before all reads from all memory made by the calling thread after the call to __threadfence_block().

void __threadfence_system();
    acts as __threadfence_block() for all threads in the block of the calling thread and also ensures that all writes to all memory made by the calling thread before the call to __threadfence_system() are observed by all threads in the device, host threads, and all threads in peer devices as occurring before all writes to all memory made by the calling thread after the call to __threadfence_system().


Synchronization Functions
-------------------------

void __syncthreads();
    waits until all threads in the thread block have reached this point and all global and shared memory accesses made by these threads prior to __syncthreads() are visible to all threads in the block.

int __syncthreads_count(int predicate);
int __syncthreads_and(int predicate);
int __syncthreads_or(int predicate);
void __syncwarp(unsigned mask=0xffffffff);


Other Functions
---------------

T __ldg(const T* address);
    returns the data of type T located at address address, where T is <u><type><size>. The operation is cached in the read-only data cache (see Global Memory).

clock_t clock();
long long int clock64();

int atomic<fun>>(int|float|double* address, int val);

<fun> ::=
    Add Sub Exch Min Max Inc Dec CAS
    And Or Not

unsigned int __isGlobal(const void *ptr);
unsigned int __isShared(const void *ptr);
unsigned int __isConstant(const void *ptr);
unsigned int __isLocal(const void *ptr);


Warp Functions
--------------

int __all_sync(unsigned mask, int predicate);
int __any_sync(unsigned mask, int predicate);
unsigned __ballot_sync(unsigned mask, int predicate);
unsigned __activemask();

unsigned int __match_any_sync(unsigned mask, T value);
unsigned int __match_all_sync(unsigned mask, T value, int *pred);

T __shfl_sync(unsigned mask, T var, int srcLane, int width=warpSize);
T __shfl_up_sync(unsigned mask, T var, unsigned int delta, int width=warpSize);
T __shfl_down_sync(unsigned mask, T var, unsigned int delta, int width=warpSize);
T __shfl_xor_sync(unsigned mask, T var, int laneMask, int width=warpSize);






Texture Functions |  Surface Functions
-----------------
