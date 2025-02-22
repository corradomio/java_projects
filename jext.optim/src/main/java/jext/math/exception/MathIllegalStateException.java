/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package jext.math.exception;

import jext.math.exception.util.Localizable;
import jext.math.exception.util.LocalizedFormats;

/**
 * Base class for all exceptions that signal that the process
 * throwing the exception is in a state that does not comply with
 * the set of states that it is designed to be in.
 *
 * @since 2.2
 */
public class MathIllegalStateException extends MathRuntimeException {
    /** Serializable version Id. */
    private static final long serialVersionUID = -6024911025449780478L;

    /**
     * Simple constructor.
     *
     * @param pattern Message pattern explaining the cause of the error.
     * @param args Arguments.
     */
    public MathIllegalStateException(Localizable pattern, Object... args) {
        super(pattern, args);
    }

    /**
     * Simple constructor.
     *
     * @param cause Root cause.
     * @param pattern Message pattern explaining the cause of the error.
     * @param args Arguments.
     */
    public MathIllegalStateException(Throwable cause,
                                     Localizable pattern,
                                     Object... args) {
        super(cause, pattern, args);
    }

    /**
     * Default constructor.
     */
    public MathIllegalStateException() {
        this(LocalizedFormats.ILLEGAL_STATE);
    }
}
