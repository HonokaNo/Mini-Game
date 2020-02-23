package game.character;

/**
 * キャラクターの体力や魔力などを管理するクラスです。
 *
 * @version 0.0.1p
 */
public class Status implements Cloneable
{
	/**
	 * レベル
	 */
	public long lv;
	/**
	 * 最大体力
	 */
	public long mhp;
	/**
	 * 現在体力
	 */
	public long hp;
	/**
	 * 攻撃力
	 */
	public long ap;
	/**
	 * 防御力
	 */
	public long bp;
	/**
	 * 素早さ
	 */
	public long sp;
	/**
	 * 魔法攻撃力
	 */
	public long map;
	/**
	 * 魔法防御力
	 */
	public long mbp;
	/**
	 * 最大魔力
	 */
	public long mmp;
	/**
	 * 現在魔力
	 */
	public long mp;
	/**
	 * 回避率
	 */
	public long avoid;

	/**
	 * 逃げられる確率
	 */
	public long flee;

	/**
	 * ステータスの初期化をします。
	 * 設定される値は適当です。
	 */
	public Status()
	{
		lv = 1;
		mhp = 0;
		hp = mhp;
		ap = bp = sp = 0;
		map = mbp = 0;
		mmp = 0;
		mp = mmp;
		avoid = flee = 0;
	}

	/**
	 * ステータス同士を足し合わせます。
	 *
	 * @param st 足し合わせられるステータス
	 */
	public void add(Status st)
	{
		mhp   += st.mhp;
		hp    += st.mhp;
		ap    += st.ap;
		bp    += st.bp;
		sp    += st.sp;
		map   += st.map;
		mbp   += st.mbp;
		mmp   += st.mmp;
		mp    += st.mmp;
		avoid += st.avoid;
		flee  += st.flee;
	}

	/**
	 * ステータスを減らします。
	 *
	 * @param st 減らすステータスを示す
	 */
	public void sub(Status st)
	{
		mhp   -= st.mhp;
		hp    -= st.hp;
		ap    -= st.ap;
		bp    -= st.bp;
		sp    -= st.sp;
		map   -= st.map;
		mbp   -= st.mbp;
		mmp   -= st.mmp;
		mp    -= st.mp;
		avoid -= st.avoid;
		flee  -= st.flee;
	}

	@Override public Status clone()
	{
		try{
			return (Status)super.clone();
		}catch(CloneNotSupportedException cnse){
			/* 絶対に発生しないはず */
			throw new InternalError();
		}
	}
}
