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
	private static final byte[] KEY = {(byte)43, (byte)102, (byte)20};

	public LockedReader(Reader in)
	{
		super(in);
	}

	@Override public int read(char[] cbuf, int off, int len) throws IOException
	{
		int read = super.read(cbuf, off, len);
		for(int l = 0; l < cbuf.length; l++){
			for(int m = 0; m < KEY.length; m++){
				cbuf[l] ^= KEY[m];
			}
		}
		return read;
	}
}
