package jext.hashing.provider.digest;

import jext.hashing.provider.config.ConfigurableProvider;
import jext.hashing.provider.util.AlgorithmProvider;

import java.security.MessageDigest;


public class SDHash {

    public static class Mappings extends AlgorithmProvider {
        private static final String PREFIX = SDHash.class.getName();
        @Override
        public void configure(ConfigurableProvider provider) {
            provider.addAlgorithm("MessageDigest.SDHash", PREFIX + "$Digest");
        }
    }

    public static class Digest extends MessageDigest {

        private jext.hashing.provider.digest.tlsh.TLSH tlsh;

        public Digest() {
            super("SDHash");
            this.tlsh = new jext.hashing.provider.digest.tlsh.TLSH();
        }

        @Override
        protected void engineReset() {
            this.tlsh = new jext.hashing.provider.digest.tlsh.TLSH();
        }

        @Override
        protected void engineUpdate(byte input) {
            byte[] data = new byte[]{input};
            this.tlsh.update(data);
        }

        @Override
        protected void engineUpdate(byte[] input, int offset, int len) {
            if (offset == 0 && input.length == len)
                this.tlsh.update(input);
            else {
                byte[] data = new byte[len];
                System.arraycopy(input, offset, data, 0, len);
                this.tlsh.update(data);
            }
        }

        @Override
        protected byte[] engineDigest() {
            this.tlsh.finale();
            return this.tlsh.hash().getBytes();
        }

    }
}
