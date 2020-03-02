package game.character;

import static game.Console.*;

/**
 * 一時的効果を管理するクラス
 *
 * @version 0.0.1p
 */
public class Effect
{
	/* 残りターン */
	private int turn;

	/* 作用する効果 */
	private Status status;

	/* 効果名 */
	private String name;

	public Effect(int turn, Status status, String name)
	{
		this.turn = turn;
		this.status = status;
		this.name = name;
	}

	/**
	 * エフェクトのターンを減らす
	 *
	 * @return 効果が切れた場合true
	 */
	public boolean subTurn()
	{
		turn--;
		if(turn == 0){
			putl(name + "の効果が切れた!");
			return true;
		}
		return false;
	}

	public Status getStatus()
	{
		return status;
	}
}
