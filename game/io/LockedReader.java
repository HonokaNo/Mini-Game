package game.io;

import java.io.*;

/**
 * 簡単な暗号化読み込みクラス
 * 単純なので絶対個人情報などに使用しないように!
 *
 * @version 0.0.1p
 */
public class LockedReader extends BufferedReader
{
	/* Writerと同じでないといけない */
	private static final byte[] KEY = {(byte)43};

	public LockedReader(Reader in)
	{
		super(in);
	}

	@Override public int read(char[] cbuf, int off, int len) throws IOException
	{
		int read = super.read(cbuf, off, len);
		int[] array = new int[cbuf.length];
		for(int l = 0; l < array.length; l++){
			for(int m = 0; m < KEY.length; m++){
				array[l] = (int)cbuf[l];
				array[l] ^= KEY[m];
				cbuf[l] = (char)array[l];
			}
		}
		return read;
	}
}
