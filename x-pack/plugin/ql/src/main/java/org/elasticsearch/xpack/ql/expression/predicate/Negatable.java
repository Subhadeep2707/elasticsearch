/*
 * Copyright Elasticsearch B.V. and/or licensed to Elasticsearch B.V. under one
 * or more contributor license agreements. Licensed under the Elastic License;
 * you may not use this file except in compliance with the Elastic License.
 */
package org.elasticsearch.xpack.ql.expression.predicate;

import org.elasticsearch.xpack.ql.expression.function.scalar.ScalarFunction;

public interface Negatable<T extends ScalarFunction> {

    T negate();

}
