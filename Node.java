package iart;

public class Node{
    private double x;
    private double y;
    private int flag = 0; // indica se o nodo foi gerado com ou sem rotacao

    /**
     *
     * @param x X
     * @param y Y
     * @param flag1 Acumulador do ângulo
     */
    Node(double x, double y, int flag1){
                    this.setX(x); 
                    this.setY(y);
					this.flag = flag1;
    }
    public String toString(){
                    return "(" + getX() + ", " + getY() + ") ";
    }
	public void setX(double x2) {
		this.x = x2;
	}
	public double getX() {
		return x;
	}
	public void setY(double y2) {
		this.y = y2;
	}
	public double getY() {
		return y;
	}

	public int getFlag() {
		return flag;
	} 
}

