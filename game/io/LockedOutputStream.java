package game.io;

import java.io.*;

/**
 * 簡単な暗号化書き込みクラス
 * 単純なので絶対個人情報などに使用しないように!
 *
 * @version 0.0.1p
 */
public class LockedOutputStream extends BufferedOutputStream
{
	/* InputStreamと同じでないといけない */
	private static final byte[] KEY = {(byte)36};

	public LockedOutputStream(OutputStream out)
	{
		super(out);
	}

	@Override public void write(byte[] b, int off, int len) throws IOException
	{
		for(int l = 0; l < b.length; l++){
			for(int m = 0; m < KEY.length; m++){
				b[l] ^= KEY[m];
			}
		}
		super.write(b, off, len);
	}
}
