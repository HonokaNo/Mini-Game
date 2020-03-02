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
		status.ap = 3;
		status.bp = 3;
		status.map = 3;
		status.mbp = 3;
		status.sp = 3;
		status.mmp = 1;

		sendexp = 1;
		send = 1;
	}

	@Override public void command(Mob[] m, Mob[] n)
	{
		powerAttack(this, m[(int)rand(m.length)], 0);
	}
}
