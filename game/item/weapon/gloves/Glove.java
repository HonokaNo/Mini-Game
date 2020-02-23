package game.item.weapon.gloves;

import game.character.Status;
import game.item.weapon.Weapon;
import game.item.weapon.WeaponType;

public class Glove extends Weapon
{
	public Glove()
	{
		name = "初めの籠手(こて)";
		description = new String[]{"AP +2 BP +1", "初めてのプレイヤーのための籠手。"};
		st.ap = 2;
		st.sp = 1;
		st.avoid = 2;

		type = WeaponType.WEAPON_GLOVE;
	}
}
