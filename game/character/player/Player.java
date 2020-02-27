package game.character.player;

import java.io.*;

import game.character.*;
import game.io.*;
import game.item.*;
import game.item.weapon.*;

import static game.Console.*;
import static game.Game.*;

/**
 * プレイヤーの分身として、ゲーム内で活動する
 * キャラクターのクラスです。
 *
 * @version 0.0.1p
 */
public class Player extends Mob
{
	/* ステータスとして付与できるポイント */
	private long point = 20;

	/**
	 * 現在のお金
	 */
	private long money;

	/* プレイヤーの装備している武器 装備していない場合null */
	private Weapon weapon = new None();

	/* 配列は{lv, need, up} */
	private long[][] status_lv = {{1, 2, 2}, {1, 1, 1}, {1, 1, 1}, {1, 1, 1}, {1, 1, 1}, {1, 1, 1}, {1, 2, 2}, {1, 6, 1}};

	/* 現在の経験値と次のレベルへの経験値 */
	private long nowexp, nextexp;
	/* 今までに手に入れた合計 */
	private long totalexp;

	/* ステータスの初期化 */
	@Override protected void statusInit()
	{
		status.mhp = 10;
		status.ap = status.bp = status.sp = 2;
		status.map = status.mbp = 2;
		status.mmp = 1;
		status.flee = 0;

		nextexp = 6;
	}

	/**
	 * 新しいPlayerオブジェクトを生成します。
	 *
	 * @param name プレイヤーを識別する名前
	 */
	public Player(String name)
	{
		this.name = name;

		money = 5;
		statusInit();
	}

	/**
	 * Mobの所持金を返します。
	 *
	 * @return 所持金
	 */
	public long getMoney()
	{
		return money;
	}

	/**
	 * プレイヤーの所持金を追加します。
	 *
	 * @param m 追加させる金額
	 */
	public void addMoney(long m)
	{
		money += m;
	}

	/**
	 * プレイヤーの所持金を減らします。
	 *
	 * @param m 減少させる金額
	 */
	public void subMoney(long m)
	{
		money -= m;
	}

	/**
	 * 現在装備している武器を返します。
	 *
	 * @return 現在装備している武器 装備していない場合null
	 */
	public Weapon getWeapon()
	{
		return weapon;
	}

	/**
	 * プレイヤーの武器をセットします。
	 * 自動で武器を外すことはありません。
	 *
	 * @param w 装備する武器 nullの場合無視される
	 */
	public void addWeapon(Weapon w)
	{
		if(weapon != null && !(weapon instanceof None)){
			status.add(w.getStatus());
			weapon = w;
		}
	}

	/**
	 * プレイヤーから武器を外します。
	 */
	public void removeWeapon()
	{
		if(weapon != null && !(weapon instanceof None)){
			status.sub(weapon.getStatus());
			weapon = new None();
		}
	}

	/**
	 * ステータス上昇に使用できるポイントを返します。
	 *
	 * @return ポイント
	 */
	public long getPoint()
	{
		return point;
	}

	/* 次にステータスを上げるときに必要なポイントを計算 */
	private void setNextPoint(int index)
	{
		if(index < status_lv.length){
			if(index == 0 || index == 7){
				/* HP, MP */
				if(status_lv[index][0] == 5) status_lv[index][1] += 2;
				if(status_lv[index][0] % 15 == 0) status_lv[index][1] += 2;
				if(status_lv[index][0] % 30 == 0) status_lv[index][2]++;
			}else if(index == 8){
				/* AVOID */
				if(status_lv[index][0] == 3) status_lv[index][1] += 2;
				if(status_lv[index][0] % 6 == 0) status_lv[index][1] += 1;
			}else{
				/* AP, BP, SP, MAP, MBP */
				if(status_lv[index][0] % 5 == 0) status_lv[index][1] += 1;
			}
			status_lv[index][0]++;
		}
	}

	/**
	 * ポイントを付与する画面を表示
	 */
	public void pointed()
	{
		putfn("現在のポイント:%d", point);
		write("どのステータスを上げる?");
		write("1.HP");
		write("2.AP");
		write("3.BP");
		write("4.SP");
		write("5.MAP");
		write("6.MBP");
		write("7.MP");
		write("8.AVOID");
		write("x.キャンセル");

		char c = input();

		if(c == '1'){pointed(0); pointed();}
		if(c == '2'){pointed(1); pointed();}
		if(c == '3'){pointed(2); pointed();}
		if(c == '4'){pointed(3); pointed();}
		if(c == '5'){pointed(4); pointed();}
		if(c == '6'){pointed(5); pointed();}
		if(c == '7'){pointed(6); pointed();}
		if(c == '8'){pointed(7); pointed();}
		if(c == 'x'){
			try{
				reset();
			}catch(IOException ie){}
		}
		return;
	}

	private void pointed(int index)
	{
		String[] sarray = {"HP", "AP", "BP", "SP", "MAP", "MBP", "MP", "AVOID"};

		try{
			putfn("現在のポイント:%d", point);
			putfn("%sにポイントを振ってレベルアップする?(y/n)", sarray[index]);
			putfn("必要ポイント:%d 上昇値:%d", status_lv[index][1], status_lv[index][2]);
			char i = input();
			if(i == 'y'){
				if(status_lv[index][1] <= point){
					putfn("%sが%d上がった!", sarray[index], status_lv[index][2]);
					if(index == 0) point_hp();
					else if(index == 1) point_ap();
					else if(index == 2) point_bp();
					else if(index == 3) point_sp();
					else if(index == 4) point_map();
					else if(index == 5) point_mbp();
					else if(index == 6) point_mp();
					else if(index == 7) point_avoid();
					point -= status_lv[index][1];
					setNextPoint(index);
				}else write("ポイントが足りませんよ!");
			}else write("やっぱりやめよう。");
			return;
		}catch(NumberFormatException nfe){
			error("数値以外の数が入力されました。");
		}
	}

	private void point_hp()
	{
		putfn("%d %s %d", status.mhp, ARROW, status.mhp + status_lv[0][2]);
		status.mhp += status_lv[0][2];
		status.hp += status_lv[0][2];
	}

	private void point_ap()
	{
		putfn("%d %s %d", status.ap, ARROW, status.ap + status_lv[1][2]);
		status.ap += status_lv[1][2];
	}

	private void point_bp()
	{
		putfn("%d %s %d", status.bp, ARROW, status.bp + status_lv[2][2]);
		status.bp += status_lv[2][2];
	}

	private void point_sp()
	{
		putfn("%d %s %d", status.sp, ARROW, status.sp + status_lv[3][2]);
		status.sp += status_lv[3][2];
	}

	private void point_map()
	{
		putfn("%d %s %d", status.map, ARROW, status.map + status_lv[4][2]);
		status.map += status_lv[4][2];
	}

	private void point_mbp()
	{
		putfn("%d %s %d", status.mbp, ARROW, status.mbp + status_lv[5][2]);
		status.mbp += status_lv[5][2];
	}

	private void point_mp()
	{
		putfn("%d %s %d", status.mmp, ARROW, status.mmp + status_lv[6][2]);
		status.mmp += status_lv[6][2];
		status.mp += status_lv[6][2];
	}

	private void point_avoid()
	{
		putfn("%d %s %d", status.avoid, ARROW, status.avoid + status_lv[7][2]);
		status.avoid += status_lv[7][2];
	}

	/**
	 * 現在溜まっている経験値を返します。
	 *
	 * @return 現在の経験値
	 */
	public long getNowExp()
	{
		return nowexp;
	}

	/**
	 * 次のレベルへ行くのに必要な経験値を返します。
	 *
	 * @return 次のレベルに必要な経験値
	 */
	public long getNextExp()
	{
		return nextexp;
	}

	/**
	 * プレイヤーに経験値を付与します。
	 *
	 * @param exp 付与する経験値
	 */
	public void addExp(long exp)
	{
		nowexp += exp;
		totalexp += exp;

		while(nowexp >= nextexp){
			long p = 3;
			point += p;

			putfn("レベルアップ! %d%s%d", status.lv, ARROW, status.lv + 1);
			putfn("ポイントを%d手に入れた!", p);
			status.lv++;
			pointed();

			nowexp -= nextexp;
			long a = status.lv + status.lv * 3;
			long b = status.lv + 5;
			nextexp = (a + b) / 2;
			maxheal();
		}
	}

	/**
	 * プレイヤーにアイテムを与えます。
	 *
	 * @param i 与えるアイテム
	 * @param num 与えるアイテムの数量
	 */
	public void giveItem(Item i, long num)
	{
		putfn("%sを%d個手に入れた!", i.getName(), num);
		ItemManager.addItem(i, num);
	}

	private void attack(Mob a, Mob[] d)
	{
		if(getWeapon().getFlags()[0]){
			/* 全体攻撃 */
			for(Mob mob : d) powerAttack(this, mob, 1);
		}else{
			putll("どのキャラに攻撃する?");
			for(int l = 1; l < d.length + 1; l++){
				putfn("%d.%s", l, d[l - 1].getName());
			}
			char c = input();
			int selected = Character.digit(c, 10);

			/* 攻撃対象が見つかった */
			if(selected >= 1 && selected <= d.length){
				powerAttack(this, d[selected - 1], 1);
			}else putl("攻撃しようとしていたはずなのに敵が見つからない!");
		}
	}

	@Override public void command(Mob[] m, Mob[] n)
	{
		putll(getName() + "はどうする?");

		putl("1.敵に攻撃");
		putl("2.防御を構える");
		putl("3.魔法を詠唱");
		putl("4.アイテムを使用");
		putl("5.まずいので逃げ出す");
		char c = input();

		if(c == '1') attack(this, n);
		else if(c == '2') defence();
		else if(c == '3'){
			putl("魔法を覚えていない...");
		}else if(c == '4'){
			putl("アイテムを持っていない...");
			putl("そこらの小石でも投げてやった...");
			putl("ダメージは全くなさそうだ... むしろ怒ってる?");
		}else if(c == '5'){
			flee();
			if(isFlee()) putfn("%sは戦闘から逃げ出せた!", getName());
			else putfn("%sは逃げ出せなかった!", getName());
		}
	}

	public void s_write(LockedOutputStream fos) throws IOException
	{
		fos.write(Long2Bytes(status.lv),  0, 8);
		fos.write(Long2Bytes(status.mhp), 0, 8);
		fos.write(Long2Bytes(status.hp),  0, 8);
		fos.write(Long2Bytes(status.ap),  0, 8);
		fos.write(Long2Bytes(status.bp),  0, 8);
		fos.write(Long2Bytes(status.sp),  0, 8);
		fos.write(Long2Bytes(status.map), 0, 8);
		fos.write(Long2Bytes(status.mbp), 0, 8);
		fos.write(Long2Bytes(status.mmp), 0, 8);
		fos.write(Long2Bytes(status.mp),  0, 8);
		fos.write(Long2Bytes(point),      0, 8);
		fos.write(Long2Bytes(money),      0, 8);
		fos.write(Long2Bytes(weapon.getNumber()), 0, 8);
		for(int l = 0; l < status_lv.length; l++){
			for(int m = 0; m < status_lv[l].length; m++){
				fos.write(Long2Bytes(status_lv[l][m]), 0, 8);
			}
		}
		fos.write(Long2Bytes(nowexp),   0, 8);
		fos.write(Long2Bytes(nextexp),  0, 8);
		fos.write(Long2Bytes(totalexp), 0, 8);

		fos.write(Long2Bytes(name.length()), 0, 8);

		Items[] items = ItemManager.getItems();
		if(items != null){
			fos.write(Long2Bytes(items.length), 0, 8);
			for(int l = 0; l < items.length; l++){
				fos.write(Long2Bytes(items[l].getItem().getNumber()), 0, 8);
				fos.write(Long2Bytes(items[l].getNumber()), 0, 8);
			}
		}else fos.write(Long2Bytes(0), 0, 8);
	}

	public void s_writename(LockedWriter bw) throws IOException
	{
		bw.write(name.toCharArray(), 0, name.length());
	}

	public void s_read(LockedInputStream fis) throws IOException
	{
		byte[] bytes = new byte[8];

		fis.read(bytes, 0, 8);
		status.lv = Bytes2Long(bytes);
		fis.read(bytes, 0, 8);
		status.mhp = Bytes2Long(bytes);
		fis.read(bytes, 0, 8);
		status.hp = Bytes2Long(bytes);
		fis.read(bytes, 0, 8);
		status.ap = Bytes2Long(bytes);
		fis.read(bytes, 0, 8);
		status.bp = Bytes2Long(bytes);
		fis.read(bytes, 0, 8);
		status.sp = Bytes2Long(bytes);
		fis.read(bytes, 0, 8);
		status.map = Bytes2Long(bytes);
		fis.read(bytes, 0, 8);
		status.mbp = Bytes2Long(bytes);
		fis.read(bytes, 0, 8);
		status.mmp = Bytes2Long(bytes);
		fis.read(bytes, 0, 8);
		status.mp = Bytes2Long(bytes);
		fis.read(bytes, 0, 8);
		point = Bytes2Long(bytes);
		fis.read(bytes, 0, 8);
		money = Bytes2Long(bytes);
		fis.read(bytes, 0, 8);
		weapon = (Weapon)ItemManager.getItem((int)Bytes2Long(bytes));

		for(int l = 0; l < status_lv.length; l++){
			for(int m = 0; m < status_lv[l].length; m++){
				fis.read(bytes, 0, 8);
				status_lv[l][m] = Bytes2Long(bytes);
			}
		}

		fis.read(bytes, 0, 8);
		nowexp = Bytes2Long(bytes);
		fis.read(bytes, 0, 8);
		nextexp = Bytes2Long(bytes);
		fis.read(bytes, 0, 8);
		totalexp = Bytes2Long(bytes);

		fis.read(bytes, 0, 8);
		int len = (int)Bytes2Long(bytes);
		s_readname(len);

		fis.read(bytes, 0, 8);
		int num = (int)Bytes2Long(bytes);
		int itemnum;
		long itemcount;
		for(int l = 0; l < num; l++){
			fis.read(bytes, 0, 8);
			itemnum = (int)Bytes2Long(bytes);
			fis.read(bytes, 0, 8);
			itemcount = Bytes2Long(bytes);
			ItemManager.addItem(ItemManager.getItem(itemnum), itemcount);
		}
	}

	private void s_readname(int length) throws IOException
	{
		File f = new File("save.dat2");
		LockedReader br = new LockedReader(new FileReader(f));
		char[] str = new char[length];
		br.read(str, 0, length);
		name = new String(str);

		br.close();
	}

	private byte[] Long2Bytes(long l)
	{
		byte[] bytes = new byte[8];
		bytes[0] = (byte)(l >> 56);
		bytes[1] = (byte)(l >> 48);
		bytes[2] = (byte)(l >> 40);
		bytes[3] = (byte)(l >> 32);
		bytes[4] = (byte)(l >> 24);
		bytes[5] = (byte)(l >> 16);
		bytes[6] = (byte)(l >>  8);
		bytes[7] = (byte)(l >>  0);
		return bytes;
	}

	private long Bytes2Long(byte[] b)
	{
		long l = 0;
		l += b[0] << 56;
		l += b[1] << 48;
		l += b[2] << 40;
		l += b[3] << 32;
		l += b[4] << 24;
		l += b[5] << 16;
		l += b[6] <<  8;
		l += b[7] <<  0;
		return l;
	}
}
