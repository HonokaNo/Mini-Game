package game.character;

import java.util.ArrayList;

import game.Random;
import game.character.Effect;
import game.character.player.Player;

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

	/* エフェクトのリスト */
	private ArrayList<Effect> effects = new ArrayList<>(0);

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
	 * @param n 敵として主に戦うキャラ
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
	 * 体力、魔力をすべて回復します。
	 */
	public void maxheal()
	{
		getStatus().hp = getStatus().mhp;
		getStatus().mp = getStatus().mmp;
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
		putfn("%sの攻撃!", a.getName());
		if(b.isDefence()) b.getStatus().bp *= 2;
		/* ガードブレイク */
		if(a instanceof Player && ((Player)a).getWeapon().getFlags()[1]){
			b.defence = false;
			b.getStatus().bp /= 2;
		}

		double base = (a.getStatus().ap - b.getStatus().bp) + (a.getStatus().lv - (a.getStatus().lv - b.getStatus().lv));
		double r = ((double)(Random.rand(51) + 70) / 100);
		base *= r;
		long damage = (long)base;
		if(damage < limit) damage = limit;

		/* 非常に防御が高いときダメージ低減 */
		if(b.getStatus().bp + 15 >= b.getStatus().ap) damage -= Math.ceil(damage / 10);

		if(damage < 0) damage = 0;

		if(status.avoid >= Random._rand()) putf("%sは攻撃を回避した!", b.getName());
		else{
			/* 即死 */
			if(a instanceof Player && ((Player)a).getWeapon().getFlags()[2]){
				if(Random._rand() == 1){
					damage = b.getStatus().mhp;
					putl("死神の力が発動した!");
				}
			}
			putf("%sは%dダメージ受けた!", b.getName(), damage);
			b.damage(damage);
		}

		if(b.isDefence()) b.getStatus().bp /= 2;
	}

	/**
	 * 一時効果を追加
	 *
	 * @param e 追加する効果
	 */
	public void addEffect(Effect e)
	{
		status.add(e.getStatus());
	}

	/**
	 * 一時効果を消去
	 *
	 * @param e 削除する効果
	 */
	public void subEffect(int index)
	{
		status.sub(effects.get(index).getStatus());
		effects.remove(index);
	}

	/**
	 * 一時効果の配列
	 *
	 * @return 全ての現在保有する効果を持つ配列
	 */
	public Effect[] getEffects()
	{
		Effect[] ef = new Effect[effects.size()];
		effects.toArray(ef);
		return ef;
	}
}
