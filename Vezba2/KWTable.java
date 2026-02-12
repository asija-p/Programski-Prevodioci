
import java.util.Hashtable;
public class KWTable {

	private Hashtable mTable;
	public KWTable()
	{
		// Inicijalizcaija hash tabele koja pamti kljucne reci
		mTable = new Hashtable();
        mTable.put("main", sym.MAIN);
        mTable.put("exit", sym.EXIT);

        // types
        mTable.put("int", sym.INT);
        mTable.put("float", sym.FLOAT);
        mTable.put("bool", sym.BOOL);

        // apply expression
        mTable.put("for", sym.FOR);
        mTable.put("in", sym.IN);
        mTable.put("apply", sym.APPLY);

        // bool constants
        mTable.put("true", sym.CONST);
        mTable.put("false", sym.CONST);
	}
	
	/**
	 * Vraca ID kljucne reci 
	 */
	public int find(String keyword)
	{
		Object symbol = mTable.get(keyword);
		if (symbol != null)
			return ((Integer)symbol).intValue();
		
		// Ako rec nije pronadjena u tabeli kljucnih reci radi se o identifikatoru
		return sym.ID;
	}
	

}
