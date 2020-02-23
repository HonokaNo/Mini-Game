package game.item.weapon.longsword;

import game.character.Status;
import game.item.weapon.Weapon;
import game.item.weapon.WeaponType;

public class LongSword extends Weapon
{
	public LongSword()
	{
		name = "初めの大剣";
		description = new String[]{"AP +2 BP +1 SP -2 全体", "初めてのプレイヤーのための大剣。"};
		st.ap = 2;
		st.bp = 1;
		st.avoid = 0;
		flags[0] = true;

		type = WeaponType.WEAPON_LONGSWORD;
	}
}
