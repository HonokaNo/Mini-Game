package game.character.enemy;

import game.character.Mob;

public abstract class Enemy extends Mob
{
	/* 倒したときに渡される経験値 */
	protected long sendexp;

	/* 倒したときに渡されるお金 */
	protected long send;

	/**
	 * 倒したときに入手出来る経験値を返します。
	 *
	 * @return 入手可能経験値
	 */
	public long getSendExp()
	{
		return sendexp;
	}

	/**
	 * 倒したときにドロップするお金を返します。
	 *
	 * @return 倒したとき手に入れるお金の量
	 */
	public long getSendMoney()
	{
		return send;
	}
}
