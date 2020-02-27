package game.item.weapon.scythe;

import game.character.Status;
import game.item.weapon.Weapon;
import game.item.weapon.WeaponType;

public class Scythe extends Weapon
{
	public Scythe()
	{
		name = "初めの鎌";
		description = new String[]{"AP +2 SP -2 AVOID +2 即死", "初めてのプレイヤーのための鎌。"};
		st.ap = 2;
		st.sp = -2;
		st.avoid = 2;
		flags[2] = true;

		type = WeaponType.WEAPON_SCYTHE;
		num = 5;
	}
}
