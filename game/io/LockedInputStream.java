package game.io;

import java.io.*;

/**
 * 簡単な暗号化読み込みクラス
 * 単純なので絶対個人情報などに使用しないように!
 *
 * @version 0.0.1p
 */
public class LockedInputStream extends BufferedInputStream
{
	/* OutputStreamと同じでないといけない */
				private static final byte[] KEY = {(byte)100, (byte)230, (byte)36};

	public LockedInputStream(InputStream in)
	{
		super(in);
	}

	@Override public int read(byte[] b, int off, int len) throws IOException
	{
		int read = super.read(b, off, len);
		for(int l = 0; l < b.length; l++){
			for(int m = 0; m < KEY.length; m++){
				b[l] ^= KEY[m];
			}
		}
		return read;
	}
}
