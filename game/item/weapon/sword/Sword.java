package game.item.weapon.sword;

import game.character.Status;
import game.item.weapon.Weapon;

import static game.Console.NEWLINE;

public class Sword extends Weapon
{
	public Sword()
	{
		name = "初めの剣";
		description = "AP +2 BP +2" + NEWLINE + "初めてのプレイヤーのための剣。";
		st.ap = 2;
		st.bp = 2;
		st.avoid = 2;
	}
}
