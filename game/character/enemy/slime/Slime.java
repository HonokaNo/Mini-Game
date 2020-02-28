package game.character.enemy.slime;

import game.character.Mob;
import game.character.enemy.Enemy;

import static game.Random.*;

public class Slime extends Enemy
{
	@Override protected void statusInit()
	{
		name = "スライム";

		status.lv = 1;
		status.mhp = 8;
		status.ap = 4;
		status.bp = 4;
		status.map = 4;
		status.mbp = 4;
		status.sp = 3;
		status.mmp = 1;

		sendexp = 1;
		send = 1;
	}

	@Override public void command(Mob[] m, Mob[] n)
	{
		int l = (int)rand(m.length);
		powerAttack(this, m[l], 0);
	}
}
