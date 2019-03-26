package com.test.pingpong;

public class Player implements Runnable {
	private static String initial = "test";
	private Monitor out;
	private Monitor in;
	private String name;
	private boolean initiator;
	
	public Player(String name, boolean initiator,Monitor out, Monitor in) {
		this.name = name;
		this.out = out;
		this.in = in;
		this.initiator = initiator;
	}
	@Override
	public void run() {
		String currentMessage = null;
		
		if (initiator) {
			sendToOut(currentMessage);
		}

		while(true){
			if (currentMessage!=null) {
				sendToOut(currentMessage);
			}

			currentMessage = getFromIn();
			
			
			if (initiator && getIndex(currentMessage) == 30) {
				break;
			}			
			
		}
	}
	
	private void sendToOut(String message) {
		synchronized (out) {
			if (out.getMessage() == null) {
				int index = getIndex(message);
				index++;
				String messageToSend = initial + index;
				out.setMessage(messageToSend);
				System.out.println(name+" send output message:"+messageToSend);
			}
		}
	}
	
	private int getIndex(String message) {
		if (message == null) {
			return 0;
		}
		String str = message.substring(initial.length());
		if (str == null || str.length() == 0) {
			return 0;
		}
		return Integer.valueOf(str);
	}
	
	private String getFromIn() {
		synchronized (in) {
			String res = in.getMessage();
			in.setMessage(null);
			if (res!=null) {
				System.out.println(name+" received message:"+res); 
			}
			return res;
		}
	}
}
