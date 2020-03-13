package game.item.weapon.jsword;

import game.character.Status;
import game.item.weapon.Weapon;
import game.item.weapon.WeaponType;

/**
 * 初めの日本刀のクラスです。
 *
 * @version 0.0.1p
 */
public class JSword extends Weapon
{
	public JSword()
	{
		name = "日本刀 \"数珠丸\"";
		description = new String[]{"AP +20 SP +13 AVOID +1%", "岡山で有名な日本刀。"};
		st.ap = 20;
		st.bp = 13;
		st.avoid = 1;

		type = WeaponType.WEAPON_JSWORD;
		num = 8;
	}
}
