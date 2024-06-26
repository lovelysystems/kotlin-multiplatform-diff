/*
 * Copyright 2021 Peter Trifanov.
 * Copyright 2009-2017 java-diff-utils.
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

import kotlin.jvm.JvmField

/**
 * Holds the information about the part of text involved in the diff process.
 *
 * Text is represented as generic class `T` because the diff engine is capable of handling more
 * than plain ASCII. In fact, arrays or lists of any type that implements
 * `hashCode()` and `equals()` correctly can be subject to differencing using this library.
 *
 * @param position The start position of chunk in the text
 * @param lines The affected lines
 * @param changePosition The positions of changed lines of chunk in the text
 * @param T The type of the compared elements in the 'lines'
 */
public data class Chunk<T>(
    @JvmField val position: Int,
    @JvmField val lines: List<T>,
    @JvmField val changePosition: List<Int>? = null,
) {
    /**
     * Verifies that this chunk's saved text matches the corresponding text in the given sequence.
     *
     * @param target The sequence to verify against
     */
    public fun verifyChunk(target: List<T>): VerifyChunk {
        val targetSize = target.size

        if (position > targetSize || last() > targetSize) {
            return VerifyChunk.POSITION_OUT_OF_TARGET
        }

        for (i in 0..<size()) {
            if (target[position + i] != lines[i]) {
                return VerifyChunk.CONTENT_DOES_NOT_MATCH_TARGET
            }
        }

        return VerifyChunk.OK
    }

    public fun size(): Int =
        lines.size

    /**
     * Returns the index of the last line of the chunk.
     */
    public fun last(): Int =
        position + size() - 1

    override fun toString(): String =
        "[position: $position, size: ${size()}, lines: $lines]"
}
