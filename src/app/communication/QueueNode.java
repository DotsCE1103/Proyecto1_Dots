package app.communication;

public class QueueNode 
{ 
	public int port; 
	public QueueNode next;
	public Object element; 
	
	public QueueNode(int port) {
		this.port = port;
	} 
} 