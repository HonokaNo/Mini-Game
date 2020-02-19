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

	/**
	 * この武器の能力を返します。
	 *
	 * @return この武器の能力
	 */
	public final Status getStatus()
	{
		return st;
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
