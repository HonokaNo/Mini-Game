package game.magic.light;

import game.character.Mob;
import game.magic.Magic;

import static java.lang.System.*;

public class Heal extends Magic
{
	public Heal()
	{
		num = 1;
		name = "ヒーリング";
		description = new String[]{"消費:5 光", "光の力で体力を15回復する。"};
		use = 5;
	}

	@Override public void use(Mob m, Mob[] e)
	{
		if(m.getStatus().mp >= use){
			out.println(m.getName() + "は魔法[" + getName() + "]を使った!");
			m.heal(15);
			out.println(m.getName() + "は体力が15回復した!");
			m.getStatus().mp -= use;
		}
	}
}
