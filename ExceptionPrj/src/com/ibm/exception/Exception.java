package com.ibm.exception;

import com.ibm.broker.plugin.MbElement;
import com.ibm.broker.plugin.MbException;
import com.ibm.broker.plugin.MbInputTerminal;
import com.ibm.broker.plugin.MbMessage;
import com.ibm.broker.plugin.MbMessageAssembly;
import com.ibm.broker.plugin.MbNode;
import com.ibm.broker.plugin.MbNodeInterface;
import com.ibm.broker.plugin.MbOutputTerminal;

public class Exception {

	
	public static class ExceptionPluginNode extends MbNode implements MbNodeInterface {
		
		private static String RETHROW_MSG_NUMBER = "2230"; 
	 
/*		private String _retryCount, _retryInterval;
		private Boolean _retry;
		
	    public String getRetryCount() {
			return _retryCount;
		}

		public void setRetryCount(String _retryCountAttr) {
			_retryCount = _retryCountAttr;
		}

		public String getRetryInterval() {
			return _retryInterval;
		}

		public void setRetryInterval(String _retryIntervalAttr) {
			_retryInterval = _retryIntervalAttr;
		}

		public boolean isRetry() {
			return _retry;
		}

		public void setRetry(Boolean _retryAttr) {
			_retry = _retryAttr;
		}*/

		public ExceptionPluginNode() throws MbException {
	           
	            createInputTerminal("in");
	            createOutputTerminal("out");
	      }
	     
	    public void evaluate(MbMessageAssembly inAssembly, MbInputTerminal inputTerminal)
	      throws MbException {
	 
	            MbOutputTerminal outTerminal = getOutputTerminal("out");
	           
	            MbMessage inMessage = inAssembly.getMessage();
	           // String rCnt = getRetryCount();
	            // create new message
	            MbMessage outMessage = new MbMessage(inMessage);
	            MbMessageAssembly outAssembly = new MbMessageAssembly(inAssembly,
	                        outMessage);
	           
	            try {
	                 
		    			// Add user code below
	    			MbElement globalEnv = outAssembly.getGlobalEnvironment().getRootElement();
	    			MbElement exceptionDetails = globalEnv.createElementAsLastChild(MbElement.TYPE_NAME, "Exception", null);
	    			MbMessage newExceptionList = new MbMessage(inAssembly.getExceptionList()); 
	    			//MbElement exceptionTreeList = newExceptionList.getRootElement().getFirstChild();
	    			MbElement exceptionTreeList = newExceptionList.getRootElement();
//	    			MbElement exceptionChildElement = exceptionTreeList.getFirstChild();
	    			String messageText, messageDetails,messageNumber = "";//, label;
	    			//int i = 0;
	    			
	    			/*if(exceptionTreeList.getFirstElementByPath("Label") !=null){
    					label = exceptionTreeList.getFirstElementByPath("Label").getValueAsString();
    				}
    				else{
    					label = "not avialable";
    				}*/
	    			while(exceptionTreeList!=null){
	    				if(exceptionTreeList.getFirstElementByPath("Number") !=null){
	    					messageText = exceptionTreeList.getFirstElementByPath("Text").getValueAsString();
	    					if (messageText == null){
	    						messageText = "Exception Message Not Available";
	    					}
	    					messageNumber = exceptionTreeList.getFirstElementByPath("Number").getValueAsString();
	    					if(exceptionDetails.getFirstElementByPath("Code") == null){
	    						exceptionDetails.createElementAsLastChild(MbElement.TYPE_NAME_VALUE, "Code",messageNumber);
	    					}else{
	    						exceptionDetails.getFirstElementByPath("Code").setValue(messageNumber);
	    					}
    					
	    					if(exceptionDetails.getFirstElementByPath("Text") == null){
	    						exceptionDetails.createElementAsLastChild(MbElement.TYPE_NAME_VALUE, "Text",messageText);
	    					}else{
	    						exceptionDetails.getFirstElementByPath("Text").setValue(messageText);
	    					}				
	    				}
	    				if(exceptionTreeList.getFirstElementByPath("Text")!=null){
	    					messageDetails = exceptionTreeList.getFirstElementByPath("Text").getValueAsString();
	    					if(exceptionDetails.getFirstElementByPath("Details") == null){
	    						exceptionDetails.createElementAsLastChild(MbElement.TYPE_NAME_VALUE, "Details",messageDetails);
	    					}else{
	    						exceptionDetails.getFirstElementByPath("Details").setValue(messageDetails);
	    					}
	    				}
	    				exceptionTreeList = exceptionTreeList.getLastChild();
	    			}
	    				/*messageNumber = exceptionTreeList.getFirstElementByPath("Number").getValueAsString();
	    				if (messageNumber !=null){
		    				if (!messageNumber.equals(RETHROW_MSG_NUMBER))
		    				{
	    					
		    				}
		    				else{
		    					exceptionTreeList = exceptionTreeList.getLastChild();
		    				}
	    				}
	    				else
	    				{
	    					exceptionTreeList = exceptionTreeList.getLastChild();
	    				}
	    			}*/
	    			/*	
	    			}*/
	    			outTerminal.propagate(outAssembly);
	            } finally {
	           
	                  // clear the outMessage even if there’s an exception
	                  outMessage.clearMessage();
	            }
	           
	            
	           
	      }
	           
	      public void copyMessageHeaders(MbMessage inMessage, MbMessage outMessage)
	                throws MbException {
	            MbElement outRoot = outMessage.getRootElement();
	           
	            // iterate though the headers starting with the first child of the root
	            // element
	            MbElement header = inMessage.getRootElement().getFirstChild();
	            while (header != null && header.getNextSibling() != null) // stop before
	                  // the last
	                  // child
	                  // (body)
	            {
	                  // copy the header and add it to the out message
	                  outRoot.addAsLastChild(header.copy());
	                  // move along to next header
	                  header = header.getNextSibling();
	            }
	           
	      }
	           
	      public static String getNodeName(){
	            return "ExceptionNode";
	      }
	           
	      public void onDelete() {
	            //Do Nothing for now
	      }
	      

	}
}
	 
	

