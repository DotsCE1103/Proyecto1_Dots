package app.communication;

public class JsonQueue {
	
	public int port;
//	public String linesJson;
	
	public JsonQueue() {
		
		this.port = QueueElements.givePort();
		ServerCommunication.queue.enqueue(port);
//		String[] a = new String[161];
//		this.linesJson = Arrays.toString(a);
	}
	

}
