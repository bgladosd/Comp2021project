package hk.edu.polyu.comp.comp2021.simple.model;

import java.io.IOException;

public interface Command {
	
	public void execute(Memory m) ;
	public void setLabel(String l);
	public String getLabel();

}