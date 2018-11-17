

public class Cell {

    private int color;
    private boolean isFilled;
    private boolean isVisible;

    public Cell( ){
        color = 0;
        isFilled = false;
        isVisible = false;
    }

    public int getColor(){
        return color;
    }
    public void setColor( int color){
        this.color = color;
    }
    public boolean getFilled(){
        return isFilled;
    }
    public void setFilled( boolean fill){
        this.isFilled = fill;
    }
    public boolean getVisible(){
        return isVisible;
    }
    public void setVisible( boolean visible){
        this.isVisible = visible;
    }
}
