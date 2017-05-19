package com.ibm.exception;

import com.ibm.broker.config.appdev.InputTerminal;
import com.ibm.broker.config.appdev.Node;
import com.ibm.broker.config.appdev.NodeProperty;
import com.ibm.broker.config.appdev.OutputTerminal;

/*** 
 * <p>  <I>ExceptionNodeUDN</I> instance</p>
 * <p></p>
 */
public class ExceptionNodeUDN extends Node {

	private static final long serialVersionUID = 1L;

	// Node constants
	protected final static String NODE_TYPE_NAME = "com/ibm/exception/ExceptionNode";
	protected final static String NODE_GRAPHIC_16 = "platform:/plugin/ExceptionJavaPluginPrj/icons/full/obj16/com/ibm/exception/Exception.gif";
	protected final static String NODE_GRAPHIC_32 = "platform:/plugin/ExceptionJavaPluginPrj/icons/full/obj30/com/ibm/exception/Exception.gif";



	public ExceptionNodeUDN() {
	}

	public final InputTerminal INPUT_TERMINAL_IN = new InputTerminal(this,"InTerminal.in");
	@Override
	public InputTerminal[] getInputTerminals() {
		return new InputTerminal[] {
			INPUT_TERMINAL_IN
	};
	}

	public final OutputTerminal OUTPUT_TERMINAL_OUT = new OutputTerminal(this,"OutTerminal.out");
	@Override
	public OutputTerminal[] getOutputTerminals() {
		return new OutputTerminal[] {
			OUTPUT_TERMINAL_OUT
		};
	}

	@Override
	public String getTypeName() {
		return NODE_TYPE_NAME;
	}

	protected String getGraphic16() {
		return NODE_GRAPHIC_16;
	}

	protected String getGraphic32() {
		return NODE_GRAPHIC_32;
	}

	public String getNodeName() {
		String retVal = super.getNodeName();
		if ((retVal==null) || retVal.equals(""))
			retVal = "Exception";
		return retVal;
	};
}
