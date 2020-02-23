package game.character.player;

import game.character.*;
import game.item.weapon.*;

import static game.Console.*;
import static game.Game.*;

/**
 * プレイヤーの分身として、ゲーム内で活動する
 * キャラクターのクラスです。
 *
 * @version 0.0.1p
 */
public class Player extends Mob
{
	/* ステータスとして付与できるポイント */
	private long point;

	/**
	 * 現在のお金
	 */
	private long money;

	/* プレイヤーの装備している武器 装備していない場合null */
	private Weapon weapon = new None();

	/* ステータスの初期化 */
	@Override protected void statusInit()
	{
		status.mhp = 10;
		status.ap = status.bp = status.sp = 2;
		status.map = status.mbp = 2;
		status.mmp = 1;
		status.flee = 0;
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
	 * Mobの所持金を返します。
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
	public void subMoney(long m)
	{
		money -= m;
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

	/* 攻撃処理 */
	private void attack(Mob a, Mob[] d)
	{
		/* 全体攻撃 */
		for(Mob mob : d) powerAttack(this, mob, 0);
	}

	@Override public void command(Mob[] m, Mob[] n)
	{
		putll(getName() + "はどうする?");

		putl("1.敵に攻撃");
		putl("2.防御を構える");
		putl("3.魔法を詠唱");
		putl("4.アイテムを使用");
		putl("5.まずいので逃げ出す");
		char c = input();

		if(c == '1') attack(this, n);
		else if(c == '2') defence();
		else if(c == '3'){
			putl("魔法を覚えていない...");
		}else if(c == '4'){
			putl("アイテムを持っていない...");
			putl("そこらの小石でも投げてやった...");
			putl("ダメージは全くなさそうだ... むしろ怒ってる?");
		}else if(c == '5'){
			flee();
			if(isFlee()) putl(getName() + "は戦闘から逃げ出せた!");
		}
	}
}
