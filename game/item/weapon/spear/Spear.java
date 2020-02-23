package game.item.weapon.spear;

import game.character.Status;
import game.item.weapon.Weapon;
import game.item.weapon.WeaponType;

public class Spear extends Weapon
{
	public Spear()
	{
		name = "初めの槍";
		description = new String[]{"AP +2 SP +1", "初めてのプレイヤーのための槍。"};
		st.ap = 2;
		st.sp = 1;
		st.avoid = 1;

		type = WeaponType.WEAPON_SPEAR;
	}
}
