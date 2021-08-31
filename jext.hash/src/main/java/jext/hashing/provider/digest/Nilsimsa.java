package jext.hashing.provider.digest;

import jext.hashing.provider.config.ConfigurableProvider;
import jext.hashing.provider.util.AlgorithmProvider;

import java.security.MessageDigest;

public class Nilsimsa {

    public static class Mappings extends AlgorithmProvider {
        private static final String PREFIX = Nilsimsa.class.getName();
        @Override
        public void configure(ConfigurableProvider provider) {
            provider.addAlgorithm("MessageDigest.Nilsimsa", PREFIX + "$Digest");
        }
    }

    public static class Digest extends MessageDigest {

        private jext.hashing.provider.digest.nilsimsa.Nilsimsa nilsimsa;
        private byte[] abyte = new byte[1];

        public Digest() {
            super("Nilsimsa");
            this.nilsimsa = new jext.hashing.provider.digest.nilsimsa.Nilsimsa();
        }

        @Override
        protected void engineReset() {
            this.nilsimsa.reset();
        }

        @Override
        protected void engineUpdate(byte input) {
            this.abyte[0] = input;
            this.nilsimsa.update(abyte, 0, abyte.length);
        }

        @Override
        protected void engineUpdate(byte[] input, int offset, int len) {
            this.nilsimsa.update(input, offset, len);
        }

        @Override
        protected byte[] engineDigest() {
            return this.nilsimsa.digest();
        }

    }
}
