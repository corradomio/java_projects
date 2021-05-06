package jext.hashing.provider.digest;

import jext.hashing.provider.config.ConfigurableProvider;
import jext.hashing.provider.util.AlgorithmProvider;

import java.security.MessageDigest;


public class Sample {

    public static class Mappings extends AlgorithmProvider {
        private static final String PREFIX = Sample.class.getName();
        @Override
        public void configure(ConfigurableProvider provider) {
            provider.addAlgorithm("MessageDigest.Sample", PREFIX + "$Digest");
        }
    }

    public static class Digest extends MessageDigest {

        /**
         * Creates a message digest with the specified algorithm name.
         *
         * @param algorithm the standard name of the digest algorithm.
         *                  See the MessageDigest section in the <a href=
         *                  "{@docRoot}/../technotes/guides/security/StandardNames.html#MessageDigest">
         *                  Java Cryptography Architecture Standard Algorithm Name Documentation</a>
         *                  for information about standard algorithm names.
         */
        protected Digest(String algorithm) {
            super(algorithm);
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

        @Override
        protected void engineReset() {

        }
    }
}
