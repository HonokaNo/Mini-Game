package game.item.weapon;

import game.character.Status;
import game.character.player.Player;
import game.item.Item;

import static game.Console.*;

/**
 * 装備可能な武器を示すクラスです。
 *
 * @version 0.0.1p
 */
/* abstractにしているのはインスタンス化して */
/* setWeaponされるのを防ぐ意味がある */
public abstract class Weapon extends Item
{
	/**
	 * Itemクラスの変数と同じです。
	 *
	 * @see game.item.Item#lost
	 */
	protected boolean lost = false;

	/* 武器の能力 */
	protected Status st = new Status();

	/* 武器の能力のフラグ */
	/* 0:全体攻撃可能 */
	/* 1:ガードブレイク */
	/* 2:即死 */
	protected boolean[] flags = new boolean[32];

	/* 武器の種別 */
	protected WeaponType type;

	/**
	 * この武器の能力を返します。
	 *
	 * @return この武器の能力
	 */
	public final Status getStatus()
	{
		return st;
	}

	/**
	 * 武器のステータスフラグを返します。
	 *
	 * @return 武器にセットされたフラグ
	 */
	public final boolean[] getFlags()
	{
		return flags;
	}

	/**
	 * 武器の種別を返します。
	 *
	 * @return 武器のタイプ
	 */
	public final WeaponType getType()
	{
		return type;
	}

	@Override public final void use(Player p)
	{
		if(p.getWeapon() instanceof None){
			/* 付けていないのでつける */
			p.addWeapon(this);
			putf("%sを装備しました!%s", getName(), NEWLINE);
		}else if(p.getWeapon().getName().equals(getName())){
			/* 武器を外す */
			p.removeWeapon();
			putf("%sを外しました!%s", getName(), NEWLINE);
		}else{
			/* 武器を外してつける */
			p.removeWeapon();
			p.addWeapon(this);
			putf("%sを装備しました!%s", getName(), NEWLINE);
		}
	}
}
