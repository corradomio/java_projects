package org.hls.check;

import org.deeplearning4j.nn.conf.MultiLayerConfiguration;
import org.deeplearning4j.nn.conf.NeuralNetConfiguration;
import org.deeplearning4j.nn.conf.inputs.InputType;
import org.deeplearning4j.nn.conf.layers.ConvolutionLayer;
import org.deeplearning4j.nn.conf.layers.DenseLayer;
import org.deeplearning4j.nn.conf.layers.OutputLayer;
import org.deeplearning4j.nn.conf.layers.PoolingType;
import org.deeplearning4j.nn.conf.layers.SubsamplingLayer;
import org.deeplearning4j.nn.weights.WeightInit;
import org.nd4j.linalg.activations.Activation;
import org.nd4j.linalg.learning.config.Adam;
import org.nd4j.linalg.lossfunctions.LossFunctions;

public class App {

    public static void main(String[] args) {
        int i;

        for(i=0; i<10; ++i)
            ;
        System.out.println(i);
    }

    public static void main1(String[] args) throws Exception {
        long seed = 1;
        int outputNum = 4;
        MultiLayerConfiguration conf = new NeuralNetConfiguration.Builder()
            .seed(seed)
            .l2(0.0005)
            .weightInit(WeightInit.XAVIER)
            .updater(new Adam(1e-3))
            .list()
            .layer(new ConvolutionLayer.Builder(5, 5)
                .stride(1,1)
                .nOut(20)
                .activation(Activation.IDENTITY)
                .build())
            .layer(new SubsamplingLayer.Builder(PoolingType.MAX)
                .kernelSize(2,2)
                .stride(2,2)
                .build())
            .layer(new ConvolutionLayer.Builder(5, 5)
                .stride(1,1)
                .nOut(50)
                .activation(Activation.IDENTITY)
                .build())
            .layer(new SubsamplingLayer.Builder(PoolingType.MAX)
                .kernelSize(2,2)
                .stride(2,2)
                .build())
            .layer(new DenseLayer.Builder().activation(Activation.RELU)
                .nOut(500).build())
            .layer(new OutputLayer.Builder(LossFunctions.LossFunction.NEGATIVELOGLIKELIHOOD)
                .nOut(outputNum)
                .activation(Activation.SOFTMAX)
                .build())
            .setInputType(InputType.convolutionalFlat(28,28,1))
            .build();
    }
}
