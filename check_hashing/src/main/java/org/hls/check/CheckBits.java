package org.hls.check;

public class CheckBits {

    public static void main(String[] args) {

        System.out.printf("%08X\n", jext.bits.Bits.shift(0xF0F, 4));
        System.out.printf("%08X\n", jext.bits.Bits.shift(0xF0F, -4));
        System.out.printf("%08X\n", jext.bits.Bits.shift(0xFFFFFFFF, 4));
        System.out.printf("%08X\n", jext.bits.Bits.shift(0xFFFFFFFF, -4));

        System.out.printf("%08X\n", jext.bits.Bits.rotate(0x12345678, 8));
        System.out.printf("%08X\n", jext.bits.Bits.rotate(0x12345678, -8));

        System.out.printf("%08X\n", jext.bits.Bits.rotate(0xFEDCBA9876543210L, 8));
        System.out.printf("%08X\n", jext.bits.Bits.rotate(0xFEDCBA9876543210L, -8));

    }
}
