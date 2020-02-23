package game.item.weapon.axe;

import game.character.Status;
import game.item.weapon.Weapon;
import game.item.weapon.WeaponType;

public class Axe extends Weapon
{
	public Axe()
	{
		name = "初めの斧";
		description = new String[]{"AP +3 BP +2 SP -2 ガードブレイク", "初めてのプレイヤーのための斧。"};
		st.ap = 3;
		st.bp = 2;
		st.sp = -2;
		st.avoid = 0;
		flags[1] = true;

		type = WeaponType.WEAPON_AXE;
	}
}
