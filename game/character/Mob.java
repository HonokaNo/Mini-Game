package game.character;

import game.Random;

import static game.Console.*;

/**
 * バトルさせることができるキャラクターです。
 *
 * @version 0.0.1p
 */
public abstract class Mob
{
	/**
	 * 能力
	 */
	protected Status status;

	/* 逃げ出したか */
	private boolean flee = false;

	/* 防御を構えているか */
	private boolean defence = false;

	/* コンストラクタより先に実行される */
	/* 子クラスのコンストラクタより先に呼び出す */
	{
		status = new Status();
		statusInit();
		/* HPとMPを全回復させる */
		status.hp = status.mhp;
		status.mp = status.mmp;
	}

	/**
	 * バトル時の初期化を行います。
	 */
	public final void battleInit()
	{
		flee = false;
		defence = false;
	}

	/**
	 * Mobの名前
	 */
	protected String name;

	/**
	 * Mobのステータスを初期化します。
	 * この関数はコンストラクタの前に呼び出されます。
	 */
	protected abstract void statusInit();

	/**
	 * Mobの名前を返します。
	 *
	 * @return Mobを識別する名前
	 */
	public String getName()
	{
		return name;
	}

	/**
	 * Mobの能力を取得します。
	 *
	 * @return Mobの現在のステータス
	 */
	public Status getStatus()
	{
		return status;
	}

	/**
	 * Mobの行動を実装します。
	 *
	 * @param m プレイヤー及びその味方キャラ
	 * @oaram n 敵として主に戦うキャラ
	 */
	public abstract void command(Mob[] m, Mob[] n);

	/**
	 * Mobが戦闘から逃げ出したかを取得します。
	 *
	 * @return Mobが逃げ出した場合はtrue
	 */
	public boolean isFlee()
	{
		return flee;
	}

	/**
	 * Mobが逃げられるかチェックします。
	 * 逃げられた場合isFleeの戻り値がtrueになります。
	 */
	public void flee()
	{
		long val = Random._rand();

		if(status.flee >= val) flee = true;
		else flee = false;
	}

	/**
	 * Mobが防御を構えているかを返します。
	 *
	 * @return 防御を構えていたらtrue
	 */
	public boolean isDefence()
	{
		return defence;
	}

	/**
	 * Mobが防御を構えます。
	 * この関数を呼び出した後はisDefenceがtrueになります。
	 */
	public void defence()
	{
		putl(name + "は防御を構えた!");
		defence = true;
	}

	/**
	 * ダメージを受けて体力を減らします。
	 *
	 * @param d 受けるダメージ
	 */
	public void damage(long d)
	{
		getStatus().hp -= d;
	}

	/**
	 * 回復して体力を増やします。
	 *
	 * @param h 回復する量
	 */
	public void heal(long h)
	{
		getStatus().hp += h;
		if(getStatus().hp > getStatus().mhp) getStatus().hp = getStatus().mhp;
	}

	/**
	 * そのMobがすでに倒れているかを返します。
	 *
	 * @return 体力が0以下の場合true
	 */
	public boolean isDeath()
	{
		return status.hp <= 0;
	}

	/**
	 * 物理攻撃のダメージ処理を行う関数です。
	 *
	 * @param a 攻撃するMob
	 * @param b 攻撃を受けるMob
	 * @param limit 受ける最低ダメージ
	 */
	public void powerAttack(Mob a, Mob b, long limit)
	{
		if(b.isDefence()) b.getStatus().bp *= 2;

		double base = (a.getStatus().ap - b.getStatus().bp) + (a.getStatus().lv - (a.getStatus().lv - b.getStatus().lv));
		double r = ((double)(Random.rand(51) + 70) / 100);
		base *= r;
		long damage = (long)base;
		if(damage < limit) damage = limit;

		long avoid = Random._rand();
		if(status.avoid >= avoid) putl(b.getName() + "は攻撃を回避した!");
		else{
			putl(b.getName() + "は" + damage + "ダメージ受けた!");
			b.damage(damage);
		}

		if(b.isDefence()) b.getStatus().bp /= 2;
	}
}