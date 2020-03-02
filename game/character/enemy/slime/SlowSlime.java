package game.character.enemy.slime;

import game.character.Effect;
import game.character.Mob;
import game.character.Status;
import game.character.enemy.Enemy;

import static game.Console.*;
import static game.Random.*;

public class SlowSlime extends Enemy
{
	@Override protected void statusInit()
	{
		name = "タートルスライム";

		status.lv = 1;
		status.mhp = 10;
		status.ap = 3;
		status.bp = 6;
		status.map = 3;
		status.mbp = 5;
		status.sp = 2;
		status.mmp = 2;

		sendexp = 3;
		send = 2;
	}

	@Override public void command(Mob[] m, Mob[] n)
	{
		long rand = _rand();
		if(rand <= 20 && status.mp >= 2){
			putl(name + "のタートルプロテクト!");
			Status st = new Status();
			st.bp = 3;
			Effect e = new Effect(5, st, "タートルプロテクト");
			addEffect(e);
		}else if(rand <= 80) powerAttack(this, m[(int)rand(m.length)], 0);
	}
}
