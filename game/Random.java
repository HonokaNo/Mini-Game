package game;

import java.math.BigInteger;

/**
 * javaとは違う方法で生成する乱数のクラスです。
 * なお、実装についてはwikipediaを参照してください
 * <a href=https://ja.wikipedia.org/wiki/Xorshift target="_blank">Wikipedia:Xorshift</a>
 */
public final class Random
{
	/* このシードの設定が肝 */
	private static long seed = 8_8172_6454_6332_5252L;
	/* シードは変えておいてどちらか気分や環境で選んでください */
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
		r = r.mod(BigInteger.valueOf(limit));
		return r.longValue();
	}
}
