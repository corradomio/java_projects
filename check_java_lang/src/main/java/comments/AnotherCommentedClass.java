/**
 * AnotherCommentedClass commented file
 */
package comments;

/**
 * AnotherCommentedClass class
 *
 * @author Corrado Mio
 */
public class AnotherCommentedClass {

    /**
     * CommentedInterface interface (2)
     *
     * @author Nawaf I. Almoosa
     */
    interface CommentedInterface { }

    // a line comment in class
    /*
        a block comment in class
     */

    /** Counter (2) */
    private int counter;

    /** count method (2) */
    void count() {
        // a line comment in method()
        /*
            a block comment in method()
         */

        /**
         * An incorrect Javadoc for a AClassMethod
         * (here is not correct!!!) (2)
         */
        class AClassInMethod {
            // a line comment in AClassInMethod
            /*
                a block comment in AClassInMethod
             */
        }
    }
}


/**
 * AnotherStrangeClass class
 *
 * @author Salwa Alzahmi
 */
class AnotherStrangeClass {

    /**
     * An inner class
     * @author Ahmed Talal (2)
     */
    static class InnerClass {

        /**
         * An inner^2 class
         * @author Ahmed Talal (2)
         */
        static public class InnerInnerClass {

            /**
             * An inner^3 class
             * @author Ahmed Talal (2)
             */
            static public class InnerInnerInnerClass { }
        }
    }
}

