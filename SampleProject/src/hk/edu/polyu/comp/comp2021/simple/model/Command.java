package hk.edu.polyu.comp.comp2021.simple.model;

import java.io.IOException;

public interface Command {
	
	public DataObject execute(Memory m) ;
	public void setLabel(String l);
	public String getLabel();
	public String getCmdString();
	public void setCmdString(String s);

}
