package com.company;
import jcuda.*;
import jcuda.driver.CUdeviceptr;
import jcuda.driver.CUfunction;
import jcuda.driver.CUmodule;
import jcuda.runtime.dim3;
import jext.jcuda.JCudaRuntime;
import jext.jcuda.KernelLauncher;

import java.util.Random;

import static jcuda.driver.JCudaDriver.cuCtxSynchronize;
import static jcuda.driver.JCudaDriver.cuLaunchKernel;
import static jcuda.driver.JCudaDriver.cuMemAlloc;
import static jcuda.driver.JCudaDriver.cuMemcpyDtoH;
import static jcuda.driver.JCudaDriver.cuMemcpyHtoD;
import static jcuda.driver.JCudaDriver.cuModuleGetFunction;
import static jcuda.driver.JCudaDriver.cuModuleLoad;

public class Main
{
    public static void main(String args[]) throws Exception
    {
        // JCudaDriver.setExceptionsEnabled(true);
        // cuInit(0);
        // CUdevice device = new CUdevice();
        // cuDeviceGet(device, 0);
        // CUcontext context = new CUcontext();
        // cuCtxCreate(context, 0, device);

        JCudaRuntime.init();

        Random rnd = new Random();
        // Pointer pointer = new Pointer();
        // JCuda.cudaMalloc(pointer, 4);
        // System.out.println("Pointer: "+pointer);
        // JCuda.cudaFree(pointer);
        // Load the ptx file.
        CUmodule module = new CUmodule();
        cuModuleLoad(module, "D:\\Projects\\java\\check_jcuda\\ker\\JCudaVectorAddKernel.ptx");

        // JCudaRuntime.registerModule("addKernel",  "D:\\Projects\\java\\check_jcuda\\ker\\JCudaVectorAddKernel.ptx");

        // Obtain a function pointer to the kernel function.
        CUfunction function = new CUfunction();
        cuModuleGetFunction(function, module, "add");

        int numElements = 100;
        float[] hostInputA = new float[numElements];
        float[] hostInputB = new float[numElements];
        float[] hostOutput = new float[numElements];

        for(int i=0; i<numElements; ++i) {
            hostInputA[i] = rnd.nextFloat();
            hostInputB[i] = rnd.nextFloat();
        }

        // Allocate the device input data, and copy the
        // host input data to the device
        CUdeviceptr deviceInputA = new CUdeviceptr();
        cuMemAlloc(deviceInputA, numElements * Sizeof.FLOAT);
        cuMemcpyHtoD(deviceInputA, Pointer.to(hostInputA), numElements * Sizeof.FLOAT);
        CUdeviceptr deviceInputB = new CUdeviceptr();
        cuMemAlloc(deviceInputB, numElements * Sizeof.FLOAT);
        cuMemcpyHtoD(deviceInputB, Pointer.to(hostInputB), numElements * Sizeof.FLOAT);

        // Allocate device output memory
        CUdeviceptr deviceOutput = new CUdeviceptr();
        cuMemAlloc(deviceOutput, numElements * Sizeof.FLOAT);

        // Set up the kernel parameters: A pointer to an array
        // of pointers which point to the actual values.
        Pointer kernelParameters = Pointer.to(
                Pointer.to(new int[]{numElements}),
                Pointer.to(deviceInputA),
                Pointer.to(deviceInputB),
                Pointer.to(deviceOutput)
        );

        int blockSizeX = 256;
        int gridSizeX = (int)Math.ceil((double)numElements / blockSizeX);

        // Call the kernel function.
        int res = cuLaunchKernel(function,
                gridSizeX,  1, 1,      // Grid dimension
                blockSizeX, 1, 1,      // Block dimension
                0, null,               // Shared memory size and stream
                kernelParameters, null // Kernel- and extra parameters
        );
        cuCtxSynchronize();
        //float hostOutput[] = new float[numElements];
        cuMemcpyDtoH(Pointer.to(hostOutput), deviceOutput, numElements * Sizeof.FLOAT);

        KernelLauncher kl = KernelLauncher.create(
                "D:\\Projects\\java\\check_jcuda\\ker\\JCudaVectorAddKernel.cu",
                "add");

        dim3 gridSize = new dim3(gridSizeX,1,1);
        dim3 blockSize = new dim3(blockSizeX, 1, 1);
        kl.setup(gridSize, blockSize).call(numElements, deviceInputA, deviceInputB, deviceOutput);

        for (int i=0; i<numElements; ++i) {
            float diff = hostInputA[i] + hostInputB[0] - hostOutput[i];
            if (diff != 0)
                System.out.printf("%f\n", diff);
        }

        System.out.println(function);

        JCudaRuntime.shutdown();
    }
}