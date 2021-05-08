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
            byte[] data = new byte[]{input};
            this.nilsimsa.update(data);
        }

        @Override
        protected void engineUpdate(byte[] input, int offset, int len) {
            if (offset == 0 && input.length == len)
                this.nilsimsa.update(input);
            else {
                byte[] data = new byte[len];
                System.arraycopy(input, offset, data, 0, len);
                this.nilsimsa.update(data);
            }
        }

        @Override
        protected byte[] engineDigest() {
            return this.nilsimsa.digest();
        }

    }
}
