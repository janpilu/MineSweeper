package sample;

import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

public class Model {
    private Field[][] map;
    private int fields;
    private int size;
    private double diff;
    private int mines;
    private int actFields;
    private int safeFields;

    public Model(int size) {
        this.size = size;
        this.mines=0;
        this.actFields = 0;
        this.safeFields =0;
        this.fields = 0;

    }

    public Field[][] setupBoard(){
        this.map = new Field[size][size];
        Field currField;
        for(int i = 0; i < size; i++){
            for(int j = 0; j < size; j++){
                boolean tempMine=false;
                if(Math.random()<this.diff){
                    tempMine = true;
                    this.mines++;
                }
                currField = new Field(i,j,tempMine,this);
                this.fields++;
                this.map[i][j] = currField;
            }
        }

        for(int i = 0; i < size; i++){
            for(int j = 0; j < size; j++){
                List<Field> neighbors = getNeighbors(map[i][j]);
                long amount = neighbors.stream().filter(f -> f.getHasMine()).count();
                map[i][j].setCloseMines((int)amount);
                map[i][j].setNeighbors(neighbors);
            }
        }
        this.safeFields = this.fields-this.mines;
        return map;
    }

    public void leftClick(Field f){
        f.activate();
        if(actFields==safeFields)
            end(true);
    }

    public void rightClick(Field f){
        f.mark();
    }

    public void end(boolean win){
        for (Field[] f:map) {
            for (Field temp:f) {
                temp.end(win);
            }
        }
    }

    public List<Field> getNeighbors(Field cur){
        List<Field> neighbors = new ArrayList<>();
        // ttt
        // tXt
        // ttt

        int[] toAdd = {
                -1, 1,
                0, 1,
                1, 1,
                -1, 0,
                1, 0,
                -1,-1,
                0,-1,
                1,-1
        };
        for(int i = 0;i<toAdd.length;i++){
            int toAddX = cur.getX()+toAdd[i];
            int toAddY = cur.getY()+toAdd[++i];
            if(toAddX >=0 && toAddX<this.size&&toAddY>=0&&toAddY<this.size){
                neighbors.add(map[toAddX][toAddY]);
            }
        }
        return neighbors;
    }

    public void setDiff(String diff) throws Exception{
        switch (diff){
            case "Easy":
                this.diff = 0.1;
                break;
            case "Medium":
                this.diff = 0.2;
                break;
            case "Hard":
                this.diff = 0.3;
                break;
            default:
                throw new Exception("Unknown Difficulty");
        }
    }

    public int getActFields() {
        return actFields;
    }

    public void setActFields(int actFields) {
        this.actFields = actFields;
    }

    public double getDiff(){
        return diff;
    }

    public int getSize() {
        return size;
    }

    public void setMap(Field[][] map){
        this.map = map;
    }
}
