package jext.hashing.provider.digest;

import jext.hashing.provider.config.ConfigurableProvider;
import jext.hashing.provider.util.AlgorithmProvider;

import java.security.MessageDigest;


public class TLSH {

    public static class Mappings extends AlgorithmProvider {
        private static final String PREFIX = TLSH.class.getName();
        @Override
        public void configure(ConfigurableProvider provider) {
            provider.addAlgorithm("MessageDigest.TLSH", PREFIX + "$Digest");
        }
    }

    public static class Digest extends MessageDigest {

        private jext.hashing.provider.digest.tlsh.TLSH tlsh;
        private final byte[] abyte = new byte[1];

        public Digest() {
            super("TLSH");
            this.tlsh = new jext.hashing.provider.digest.tlsh.TLSH();
        }

        @Override
        protected void engineReset() {
            this.tlsh = new jext.hashing.provider.digest.tlsh.TLSH();
        }

        @Override
        protected void engineUpdate(byte input) {
            this.abyte[0] = input;
            this.tlsh.update(abyte);
        }

        @Override
        protected void engineUpdate(byte[] input, int offset, int len) {
            this.tlsh.update(input, offset, len);
        }

        @Override
        protected byte[] engineDigest() {
            this.tlsh.finale();
            return this.tlsh.hash();
        }

    }
}
