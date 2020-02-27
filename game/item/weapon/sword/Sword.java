package game.item.weapon.sword;

import game.character.Status;
import game.item.weapon.Weapon;
import game.item.weapon.WeaponType;

/**
 * 初めの剣のクラスです。
 *
 * @version 0.0.1p
 */
public class Sword extends Weapon
{
	public Sword()
	{
		name = "初めの剣";
		description = new String[]{"AP +2 BP +1 AVOID +1", "初めてのプレイヤーのための剣。"};
		st.ap = 2;
		st.bp = 1;
		st.avoid = 1;

		type = WeaponType.WEAPON_SWORD;
		num = 7;
	}
}
