package agh.ics.oop;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class GrassFieldTest {


    @Test
    public void canMoveToTest() {
        GrassField map = new GrassField(10);
        Assertions.assertTrue(map.canMoveTo(new Vector2d(1, 0)));
        Assertions.assertTrue(map.canMoveTo(new Vector2d(1, 1)));
        Assertions.assertTrue(map.canMoveTo(new Vector2d(1, 2)));
        Assertions.assertTrue(map.canMoveTo(new Vector2d(1, 2)));
        Assertions.assertTrue(map.canMoveTo(new Vector2d(Integer.MAX_VALUE - 1, Integer.MAX_VALUE - 1)));
        map.place(new Animal(map, new Vector2d(3, 3)));
        Assertions.assertFalse(map.canMoveTo(new Vector2d(3, 3)));
    }

    @Test
    public void placeTest() {
        GrassField map = new GrassField(10);
        Assertions.assertTrue(map.place(new Animal(map, new Vector2d(-1, -1))), "Negative coords");
        try{
            map.place(new Animal(map, new Vector2d(Integer.MAX_VALUE, Integer.MAX_VALUE)));
            Assertions.fail("Can't place at borders");
        }catch(IllegalArgumentException ex){
            Assertions.assertTrue(true);
        }

        Assertions.assertTrue(map.place(new Animal(map, new Vector2d(3, 1))), "Can place at 3,1");
        try{
            map.place(new Animal(map, new Vector2d(3, 1)));
            Assertions.fail("Can't place again at 3,1");
        }catch(IllegalArgumentException ex){
            Assertions.assertTrue(true);
        }
    }

    @Test
    public void isOccupiedTest() {
        GrassField map = new GrassField(10);
        map.place(new Animal(map, new Vector2d(1, 1)));
        Assertions.assertTrue(map.isOccupied(new Vector2d(1, 1)));
    }

    @Test
    public void objectAtTest() {
        GrassField map = new GrassField(10);
        Assertions.assertTrue(map.objectAt(new Vector2d(1, 1)) == null || map.objectAt(new Vector2d(1, 1)) instanceof Grass);
        map.place(new Animal(map, new Vector2d(1, 1)));
        Assertions.assertTrue(map.objectAt(new Vector2d(1, 1)) instanceof Animal);
    }

    @Test
    public void spawnGrassTest() {
        GrassField map = new GrassField(0);
        Assertions.assertTrue(map.spawnGrassAt(new Vector2d(3, 3)));
        Assertions.assertTrue(map.objectAt(new Vector2d(3, 3)) instanceof Grass);
    }

}
