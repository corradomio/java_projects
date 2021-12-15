// package jext.name;
//
// import java.util.Objects;
//
// public class HashNameObject extends ObjectName implements HashName {
//
//     // ----------------------------------------------------------------------
//     // Fields
//     // ----------------------------------------------------------------------
//
//     protected int hash;
//
//     // ----------------------------------------------------------------------
//     // Constructors
//     // ----------------------------------------------------------------------
//
//     public HashNameObject(Name name) {
//         super(name);
//     }
//
//     public HashNameObject(String name) {
//         super(name);
//     }
//
//     public HashNameObject(String namespace, String name) {
//         super(namespace, name);
//     }
//
//     public HashNameObject(Name parent, String name) {
//         super(parent, name);
//     }
//
//
//     public HashNameObject(String name, int hash) {
//         super(name);
//         this.hash = hash;
//     }
//
//     public HashNameObject(Name name, int hash) {
//         super(name);
//         this.hash = hash;
//     }
//
//
//     // ----------------------------------------------------------------------
//     // Properties
//     // ----------------------------------------------------------------------
//
//     @Override
//     public int getHash() {
//         return hash;
//     }
//
//     public void setHash(Object... values) {
//         this.hash = Objects.hash(values);
//     }
//
//     // ----------------------------------------------------------------------
//     // Overrides
//     // ----------------------------------------------------------------------
//     // WARNING WARNING WARNING WARNING
//     //
//     //      NO!!!
//     //      The hash is an OPTIONAL value. Sometimes it is used in the comparison,
//     //      sometimes no!
//     //
//     // WARNING WARNING WARNING WARNING
//
//     // @Override
//     // public int hashCode() {
//     //     return hash * 31 + super.hashCode();
//     // }
//     //
//     // @Override
//     // public boolean equals(Object obj) {
//     //     HashName that = (HashName) obj;
//     //     return this.getHash() == that.getHash()
//     //         && this.getFullName().equals(that.getFullName());
//     // }
//     //
//     // @Override
//     // public int compareTo(Name that) {
//     //     int cmp = this.getHash() - ((HashName)that).getHash();
//     //     if (cmp != 0) return cmp;
//     //     return getFullName().compareTo(that.getFullName());
//     // }
//     //
//     // @Override
//     // public String toString() {
//     //     return String.format("%s/%d", super.toString(), this.hash);
//     // }
//
//     // ----------------------------------------------------------------------
//     // End
//     // ----------------------------------------------------------------------
//
// }
