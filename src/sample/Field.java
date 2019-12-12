package sample;

import com.sun.org.glassfish.gmbal.GmbalException;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

import java.util.List;

/**
 * The Object Type of a single field
 */
public class Field extends StackPane {
    private int x;
    private int y;
    private int w=30;
    private  int h=30;
    private boolean hasMine;
    private int closeMines;
    private List<Field> neighbors;
    private Rectangle border = new Rectangle(w,h);
    private Text content = new Text();
    private Text mark = new Text();

    private Model m;

    /**
     * Initializes the passed x and y coordinates, whether it contains a Mine
     *
     * @param x
     * @param y
     * @param hasMine
     */
    public Field(int x, int y, boolean hasMine, Model m){
        this.x = x;
        this.y = y;
        this.hasMine = hasMine;
        this.m = m;

        this.border.setStroke(Color.BLACK);
        this.border.setFill(Color.LIGHTGRAY);
        this.content.setText(this.hasMine ? "X":"");
        this.mark.setText("");

        this.content.setStyle("-fx-font-weight: bold");
        this.mark.setStyle("-fx-font-weight: bold");
        this.content.setVisible(false);

        getChildren().addAll(border,content,mark);
    }

    /**
     * Called when the Field is clicked with the left mouse button
     * If the Field is Marked or already activated the method stops
     * Otherwise the Field is colored in White and displays the amount of neighboring mines
     * or calls the end Method should it contain amine itself
     * If the Field doesnt contain a mine and none of its neighoring fields does the neighbors activat
     * methods are called.
     */
    public void activate() {
        if(this.mark.getText().equals("!")||this.mark.getText().equals("?")||this.border.getFill() == Color.WHITE)
            return;
        this.m.setActFields(this.m.getActFields()+1);
        this.border.setFill(Color.WHITE);
        this.content.setVisible(true);
        if(this.content.getText().equals(""))
            for (Field n:neighbors) {
                n.activate();
            }
        if(this.content.getText().equals("X")){
            m.end(false);
        }
    }

    /**
     * Called by right clicking the field
     * If the field is not yet activated it is marked either with a
     * '!', '?' or '' if the field is not yet marked it is markedwith the '!'
     * Further right clicks cycle through the markings
     */
    public void mark() {
        if(!(this.border.getFill() == Color.WHITE)) {
            switch (this.mark.getText()) {
                case "":
                    this.mark.setText("!");
                    //if(hasMine)
                        //callback.setMarkedMines(callback.getMarkedMines()+1);
                    break;
                case "!":
                    this.mark.setText("?");
                    //if(hasMine)
                        //callback.setMarkedMines(callback.getMarkedMines()-1);
                    break;
                case "?":
                    this.mark.setText("");
                    break;
            }
        }
    }

    /**
     * Called when the Game is lost
     * removes all markings and reveals the fields content
     * should the field contain a mine it is colored in red, otherwise it is colored in white
     */
    public void end(boolean win){

        this.mark.setText("");
        this.content.setVisible(true);
        if(hasMine) {
            if(win)
                this.border.setFill(Color.GREEN);
            else
                this.border.setFill(Color.RED);
        }
        else {
            this.border.setFill(Color.WHITE);
        }
    }

    /**
     * changes the fields width and height
     * @param s new size
     */
    public void setSize(double s){
        this.border.setHeight(s);
        this.border.setWidth(s);
    }

    /**
     * Returns the Fields x Coordinate
     * @return
     */
    public int getX(){
        return this.x;
    }

    /**
     * Returns the Fields y Coordinate
     * @return
     */
    public int getY(){
        return this.y;
    }

    /**
     * When a Field doesnt contain a Mine iths Number is colored in
     * depending on the amount of neighboring mines it has
     * @param closeMines
     */
    public void setCloseMines(int closeMines) {
        this.closeMines = closeMines;
        if(!this.hasMine) {
            switch (closeMines) {
                case 1:
                    this.content.setFill(Color.BLUE);
                    break;
                case 2:
                    this.content.setFill(Color.GREEN);
                    break;
                case 3:
                    this.content.setFill(Color.RED);
                    break;
                case 4:
                    this.content.setFill(Color.DARKBLUE);
                    break;
                case 5:
                    this.content.setFill(Color.DARKRED);
                    break;
                case 6:
                    this.content.setFill(Color.TEAL);
                    break;
                case 7:
                    this.content.setFill(Color.PURPLE);
                    break;
                case 8:
                    this.content.setFill(Color.DARKGRAY);
                    break;

            }
        }
        if(!hasMine&&closeMines>0)
            this.content.setText(closeMines+"");
    }

    /**
     * Returns whether the Field contains a mine or not
     * @return
     */
    public boolean getHasMine(){
        return this.hasMine;
    }

    /**
     * Passes a list containing the Fields neighboring Fields
     * @param neighbors
     */
    public void setNeighbors(List<Field> neighbors) {
        this.neighbors = neighbors;
    }

    public Rectangle getRect() {
        return border;
    }
}
