package game;

import java.math.BigInteger;

/**
 * javaとは違う方法で生成する乱数のクラスです。
 * なお、実装についてはwikipediaを参照してください
 * <a href=https://ja.wikipedia.org/wiki/Xorshift target="_blank">Wikipedia:Xorshift</a>
 *
 * @version 0.0.1p
 */
public final class Random
{
	/* 乱数の種 */
	private static BigInteger bigseed = BigInteger.valueOf(2_5252_3364_5462_7188L);

	/**
	 * Long.MIN_VALUEからLong.MAX_VALUEまでの乱数を生成します。
	 *
	 * @return Longで表せるすべての値の中から選ばれた乱数
	 */
	public static long rand()
	{
		return bigrand().longValue();
	}

	/**
	 * 0から64bitで表せる正の数すべての乱数を生成します。
	 *
	 * @return BigIntegerによる0から64bitで表せるすべての値
	 */
	public static BigInteger bigrand()
	{
		/* seedをBigIntegerに変換する */
		bigseed = bigseed.xor(bigseed.shiftLeft(13));
		bigseed = bigseed.xor(bigseed.shiftRight(7));
		/* seed書き換え */
		return bigseed = bigseed.xor(bigseed.shiftLeft(17));
	}

	/**
	 * 0からlimitまでの乱数を生成します。
	 *
	 * @param limit 乱数に制限をかける値
	 *
	 * @return 0以上limit未満の値
	 */
	public static long rand(long limit)
	{
		BigInteger r = bigrand();
		BigInteger s = r.mod(BigInteger.valueOf(limit));
		return s.longValue();
	}

	/**
	 * 1から100までの乱数を生成します。
	 *
	 * @return 1から100までの乱数の値
	 */
	public static long _rand()
	{
		long val = rand(1000000);
		/* 0から99までになる */
		double d = Math.ceil(val / 10000);
		return (long)d + 1;
	}
}
