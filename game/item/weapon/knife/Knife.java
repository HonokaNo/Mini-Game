package game.item.weapon.knife;

import game.character.Status;
import game.item.weapon.Weapon;
import game.item.weapon.WeaponType;

public class Knife extends Weapon
{
	public Knife()
	{
		name = "初めの小刀";
		description = new String[]{"AP +1 SP +2", "初めてのプレイヤーのための小刀。"};
		st.ap = 1;
		st.sp = 2;
		st.avoid = 2;

		type = WeaponType.WEAPON_KNIFE;
	}
}
