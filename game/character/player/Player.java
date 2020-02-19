package game.character.player;

import game.character.Status;
import game.item.weapon.Weapon;
import game.item.weapon.None;

/**
 * プレイヤーの分身として、ゲーム内で活動する
 * キャラクターのクラスです。
 *
 * @version 0.0.1p
 */
public class Player
{
	/* プレイヤーの名前 */
	private String name;

	/* プレイヤーの能力 */
	private Status status;

	/* 現在のお金 */
	private long money;

	/* プレイヤーの装備している武器 装備していない場合null */
	private Weapon weapon = new None();

	/* ステータスの初期化 */
	private void statusInit()
	{
		status = new Status();
		status.mhp = 10;
		status.hp = status.mhp;
		status.ap = status.bp = status.sp = 2;
		status.map = status.mbp = 2;
		status.mmp = 1;
		status.mp = status.mmp;
	}

	/**
	 * 新しいPlayerオブジェクトを生成します。
	 *
	 * @param name プレイヤーを識別する名前
	 */
	public Player(String name)
	{
		this.name = name;

		money = 0;
		statusInit();
	}

	/**
	 * プレイヤーの名前を返します。
	 *
	 * @return プレイヤーを識別する名前
	 */
	public String getName()
	{
		return name;
	}

	/**
	 * プレイヤーの所持金を返します。
	 *
	 * @return 所持金
	 */
	public long getMoney()
	{
		return money;
	}

	/**
	 * プレイヤーの所持金を追加します。
	 *
	 * @param m 追加させる金額
	 */
	public void addMoney(long m)
	{
		money += m;
	}

	/**
	 * プレイヤーの所持金を減らします。
	 *
	 * @param m 減少させる金額
	 */
	public void subMOney(long m)
	{
		money -= m;
	}

	/**
	 * プレイヤーの能力を取得します。
	 *
	 * @return プレイヤーの現在のステータス
	 */
	public Status getStatus()
	{
		return status;
	}

	/**
	 * 現在装備している武器を返します。
	 *
	 * @return 現在装備している武器 装備していない場合null
	 */
	public Weapon getWeapon()
	{
		return weapon;
	}

	/**
	 * プレイヤーの武器をセットします。
	 * 自動で武器を外すことはありません。
	 *
	 * @param w 装備する武器 nullの場合無視される
	 */
	public void addWeapon(Weapon w)
	{
		if(w != null){
			status.add(w.getStatus());
			weapon = w;
		}
	}

	/**
	 * プレイヤーから武器を外します。
	 */
	public void removeWeapon()
	{
		if(weapon != null && !(weapon instanceof None)){
			status.sub(weapon.getStatus());
			weapon = new None();
		}
	}
}
