package SymbolTable;

public class SymbolTable {

	/*language scope*/
	private SymbolNode types;
	
	/*za oblast vazenja programa*/
	private SymbolNode variables;
	
	public SymbolTable()
	{
		types = new Type("unknown", Type.UNKNOWN, null);
		types = new Type("char", Type.CHARACTER, types);
		types = new Type("integer", Type.INTEGER, types);
		variables=null;
	}
	
	public boolean addVar( String name, Type type)
	{
		Variable existing = this.getVar( name );
		if(existing!=null)
			return false;
		variables = new Variable(name, type, variables);
		return true;
	}
	
	public Variable getVar(String name)
	{
	    SymbolNode current = variables;
	    while( current != null && current.name != null && !current.name.equals(name))
	        current = current.next;
	    return (Variable) current;
	}
	
	public Type getType(String typeName)
	{
	    SymbolNode current = types;
	    while( current != null && current.name != null && !current.name.equals(typeName))
	        current = current.next;
	    return ( Type) current;
	}
	
	public SymbolNode getVariables()
	{
		return variables;
	}
}
