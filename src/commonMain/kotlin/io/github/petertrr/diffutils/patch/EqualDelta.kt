/*
 * Copyright 2021 Peter Trifanov.
 * Copyright 2018 java-diff-utils.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 * This file has been modified by Peter Trifanov when porting from Java to Kotlin.
 */
package io.github.petertrr.diffutils.patch

public data class EqualDelta<T>(
    override val source: Chunk<T>,
    override val target: Chunk<T>,
) : Delta<T>(DeltaType.EQUAL) {
    override fun applyTo(target: MutableList<T>) {
        // Noop
    }

    override fun restore(target: MutableList<T>) {
        // Noop
    }

    override fun withChunks(original: Chunk<T>, revised: Chunk<T>): Delta<T> =
        EqualDelta(original, revised)

    override fun toString(): String =
        "[EqualDelta, position: ${source.position}, lines: ${source.lines}]"
}
