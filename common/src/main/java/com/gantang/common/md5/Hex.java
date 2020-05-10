package com.gantang.common.md5;

public class Hex {
	private static final char[] hexDigits = { '0', '1', '2', '3', '4', '5',
			'6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F' };

	public static String toString(byte[] paramArrayOfByte) {
		return toString(paramArrayOfByte, 0, paramArrayOfByte.length);
	}

	public static String toString(byte[] paramArrayOfByte, int paramInt1,
			int paramInt2) {
		char[] arrayOfChar = new char[paramInt2 * 2];
		int i = 0;

		for (int k = paramInt1; k < paramInt1 + paramInt2; k++) {
			int j = paramArrayOfByte[k];
			arrayOfChar[(i++)] = hexDigits[(j >>> 4 & 0xF)];
			arrayOfChar[(i++)] = hexDigits[(j & 0xF)];
		}
		return new String(arrayOfChar);
	}
}
