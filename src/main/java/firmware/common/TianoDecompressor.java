/* ###
 * IP: GHIDRA
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
 */

package firmware.common;

import ghidra.util.Msg;
import util.JNILibraryLoader;

/**
 * Handles the decompression of images compressed with the Tiano Compression Algorithm.
 */
public class TianoDecompressor {
	static {
		try {
			JNILibraryLoader.loadLibrary("efidecompress");
		} catch (Throwable t) {
			Msg.showError(TianoDecompressor.class, null, "Tiano Decompresor",
				"Failed to load libefidecompress native library");
		}
	}

	private TianoDecompressor() {}

	/**
	 * Decompresses the specified compressed image. Implemented by the efidecompress native
	 * library.
	 *
	 * @param compressedImage the compressed image
	 */
	private static native byte[] nativeDecompress(byte[] compressedImage);

	/**
	 * Decompress the specified compressed image.
	 *
	 * @param compressedImage the compressed image
	 */
	public static byte[] decompress(byte[] compressedImage) {
		return nativeDecompress(compressedImage);
	}
}
