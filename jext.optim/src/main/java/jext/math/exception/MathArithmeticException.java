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
 * Base class for arithmetic exceptions.
 * It is used for all the exceptions that have the semantics of the standard
 * {@link ArithmeticException}, but must also provide a localized
 * message.
 *
 * @since 3.0
 */
public class MathArithmeticException extends MathRuntimeException {
    /** Serializable version Id. */
    private static final long serialVersionUID = -6024911025449780478L;

    /**
     * Default constructor.
     */
    public MathArithmeticException() {
        this(LocalizedFormats.ARITHMETIC_EXCEPTION);
    }

    /**
     * Constructor with a specific message.
     *
     * @param pattern Message pattern providing the specific context of
     * the error.
     * @param args Arguments.
     */
    public MathArithmeticException(Localizable pattern, Object... args) {
        super(pattern, args);
    }
}
