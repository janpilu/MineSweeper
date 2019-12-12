package sample;

import javafx.scene.paint.Color;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class ModelTest {

    Model m;

    @Before
    public void setUp() throws Exception {
        m = new Model(10);
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void mapXCoordinateEqualsSize() {
        int arraySizeX = m.setupBoard().length;
        assertEquals(m.getSize(),arraySizeX);
    }

    @Test
    public void mapYCoordinateEqualsSize() {
        int arraySizeY = m.setupBoard()[0].length;
        assertEquals(m.getSize(),arraySizeY);
    }

    @Test
    public void mapYCoordinateEqualsXCoordinate() {
        boolean same = false;
        int arraySizeX = m.setupBoard().length;
        int arraySizeY = m.setupBoard()[0].length;
        if(arraySizeX==arraySizeY)
            same = true;
        assertTrue(same);
    }

    public Field[][] setupMap(){
        Field[][] map = new Field[m.getSize()][m.getSize()];
        for(int i = 0; i< m.getSize();i++){
            for(int j = 0; j< m.getSize();j++){
                map[i][j] = new Field(i,j,false,m);
            }
        }
        return map;
    }

    @Test
    public void noMinesGetNeighbors() {
        Field[][] map = setupMap();
        this.m.setMap(map);
        List<Field> l = this.m.getNeighbors(map[0][0]);
        boolean check = false;
        for (Field f:l) {
            if(f.getHasMine())
                check = true;
        }
        assertFalse(check);
    }

    @Test
    public void minesGetNeighbors() {
        Field[][] map = setupMap();
        map[0][1] = new Field(0,1,true,m);
        this.m.setMap(map);
        List<Field> l = this.m.getNeighbors(map[0][0]);
        boolean check = false;
        for (Field f:l) {
            if(f.getHasMine())
                check = true;
        }
        assertTrue((check));
    }

    @Test
    public void setDiffCorrect() throws Exception {
        m.setDiff("Easy");
        assertEquals(0.1,m.getDiff(),0);
    }

    @Test(expected = Exception.class)
    public void setDiffIncorrect() throws Exception {
        m.setDiff("Pups");
    }
}