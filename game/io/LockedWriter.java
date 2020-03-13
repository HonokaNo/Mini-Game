package game.io;

import java.io.*;

/**
 * 簡単な暗号化書き込みクラス
 * 単純なので絶対個人情報などに使用しないように!
 *
 * @version 0.0.1p
 */
public class LockedWriter extends BufferedWriter
{
	/* Readerと同じでないといけない */
	private static final byte[] KEY = {(byte)43};

	public LockedWriter(Writer out)
	{
		super(out);
	}

	@Override public void write(char[] cbuf, int off, int len) throws IOException
	{
		int data = 0;
		for(int l = 0; l < cbuf.length; l++){
			for(int m = 0; m < KEY.length; m++){
				data = (int)cbuf[l];
				data ^= KEY[m];
				write(data);
			}
		}
//		super.write(cbuf, off, len);
	}
}
