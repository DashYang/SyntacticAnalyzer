package Storage;

public class Link {
	private String sympol;
	private int item;
	public Link(String sympol, int item) {
		this.sympol = sympol;
		this.item = item;
	}
	public Link() {
	}
	public String getSympol() {
		return sympol;
	}
	public void setSympol(String sympol) {
		this.sympol = sympol;
	}
	public int getItem() {
		return item;
	}
	public void setItem(int item) {
		this.item = item;
	}
	
}
