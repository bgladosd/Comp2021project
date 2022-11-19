package hk.edu.polyu.comp.comp2021.simple.model;

/**
 *  Command interface, a command should be able to execute get set label and get set the String input that build this command
 */
public interface Command {
	/**
	 * @param m access to memory
	 * @return DataObject expression Command with return expression as dataobject
	 */
	public DataObject execute(Memory m);

	/**
	 * @param l set the label
	 */
	public void setLabel(String l);
	/**
	 * @return get the command label
	 */
	public String getLabel();
	/**
	 * @return get the string input that build the command
	 */
	public String getCmdString();
	/** set command cmdString to string that build the command
	 * @param s string that build the command
	 */
	public void setCmdString(String s);

}
