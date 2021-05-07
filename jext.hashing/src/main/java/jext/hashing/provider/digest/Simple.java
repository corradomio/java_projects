package jext.hashing.provider.digest;

import jext.hashing.provider.config.ConfigurableProvider;
import jext.hashing.provider.util.AlgorithmProvider;

import java.security.MessageDigest;


public class Simple {

    public static class Mappings extends AlgorithmProvider {
        private static final String PREFIX = Simple.class.getName();
        @Override
        public void configure(ConfigurableProvider provider) {
            provider.addAlgorithm("MessageDigest.Simple", PREFIX + "$Digest");
        }
    }

    public static class Digest extends MessageDigest {

        public Digest() {
            super("Simple");
        }

        @Override
        protected void engineReset() {

        }

        @Override
        protected void engineUpdate(byte input) {

        }

        @Override
        protected void engineUpdate(byte[] input, int offset, int len) {

        }

        @Override
        protected byte[] engineDigest() {
            return new byte[0];
        }

    }
}
