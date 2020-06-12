package jext.tasks;

/*
    prev state  curr state
    -           CREATED
    CREATED     WAITING
    WAITING     ABORTED
    WAITINF     RUNNING
    RUNNING     ABORTED
    RUNNING     FAILED
    RUNNING     SUCCESS

    ABORTED     DONE
    FAILED      DONE
    SUCCESS     DONE
 */

public enum TaskStatus {
    CREATED,                    // created
    WAITING,                    // waiting in the queue

    RUNNING,                    // running

    SUCCESS,                    // terminated with onSuccess
    FAILED,                     // terminated by exception
    ABORTED,                    // aborted by user

    DONE                        // task completed
}
