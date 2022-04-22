/**
 * CommentedClass commented file
 */
package emenda.comments;

/**
 * CommentedClass class
 *
 * @author Corrado Mio
 */
public class CommentedClass {

    /**
     * CommentedInterface interface
     *
     * @author Nawaf I. Almoosa
     */
    interface CommentedInterface {

    }

    // a line comment in class
    /*
        a block comment in class
     */

    /** CommentedClass.Counter */
    private int counter;

    /** count method */
    void count() {
        // a line comment in method
        /*
            a block comment in method
         */

        /**
         * An incorrect Javadoc for a AClassInMethod
         * (here is not correct!!!)
         */
        class AClassInMethod {

            /** CommentedClass.AClassInMethod.Counter */
            private int counter;

        }

    }
}


/**
 * Another class
 *
 * @author Salwa Alzahmi
 */
class AnotherClass {

    /** AnotherClass.Counter */
    private int counter;

    /**
     * An inner class
     * @author Ahmed Talal
     */
    static class InnerClass {

    }
}
