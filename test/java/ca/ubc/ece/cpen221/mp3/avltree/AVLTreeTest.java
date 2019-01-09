package ca.ubc.ece.cpen221.mp3.avltree;

import ca.ubc.ece.cpen221.mp3.avltree.AvlTreeSet;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.TestCase.assertTrue;
import static junit.framework.TestCase.fail;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

public class AVLTreeTest {

    private AvlTreeSet tree1, tree2, tree3, tree4, tree5, tree6, tree7, tree8, tree9;

    /**
     * Creates trees to be used for testing
     */
    @Before
    public void instantiate(){

        tree1 = new AvlTreeSet();
        insertAArray(tree1,new int[] {30,20,25,10,40,15,43});

        tree2 = new AvlTreeSet();
        insertAArray(tree2,new int[] {1, 9, 4});

        tree3 = new AvlTreeSet();

        tree4 = new AvlTreeSet();
        insertAArray(tree4,new int[] {1});

        tree5 = new AvlTreeSet();
        insertAArray(tree5,new int[] {43, 7, 9});

        tree6 = new AvlTreeSet();
        insertAArray(tree6,new int[] {7, 1});

        tree7 = new AvlTreeSet();
        insertAArray(tree7,new int[] {7, 1, 5, 4, 9, 10, 3, 2, 15, -5, -4,0, 20, 80, -80});

        tree8= new AvlTreeSet();
        insertAArray(tree8, new int[] {2, 3, 2});

        tree9= new AvlTreeSet();
        insertAArray(tree9, new int[] {2, 3, 2, 4, -10, 0});

    }

    @Test
    public void getSizeTests() {
        assertEquals(7, tree1.size());
        assertEquals(3, tree2.size());
        assertEquals(0, tree3.size());
    }

    @Test
    public void getHeightTest1(){
        assertEquals(2, tree1.getHeight());
        assertEquals(1, tree2.getHeight());
        assertEquals(-1, tree3.getHeight());
    }
    @Test
    public void getHeightTest2(){
        assertTrue(tree7.getHeight()>=3);
    }
    @Test
    public void getHeightTest3(){
        assertEquals(1, tree8.getHeight());
        assertEquals(2, tree9.getHeight());
    }

    @Test
    public void isEmptyTests(){
        assertFalse(tree1.isEmpty());
        assertFalse(tree2.isEmpty());
        assertTrue(tree3.isEmpty());

        tree4.remove(1);
        assertTrue(tree4.isEmpty());
        assertFalse(tree4.contains(1));
    }

    @Test
    public void getMaxTests(){
        assertEquals(43, tree1.getMax());
        assertEquals(9, tree2.getMax());
    }
    @Test
    public void getMinTests(){
        assertEquals(10, tree1.getMin());
        assertEquals(1, tree2.getMin());
    }
    @Test(expected= IllegalStateException.class)
    public void getMinTestExcep() throws IllegalStateException{
        tree3.getMin();
        fail();
    }
    @Test(expected= IllegalStateException.class)
    public void getMaxTestExcep() throws IllegalStateException{
        tree3.getMax();
        fail();
    }

    @Test
    public void removeInsert() {
        tree7.remove(-4);
        tree7.insert(5);
        tree7.remove(9);
        tree7.insert(5);
        tree7.remove(3);
        tree7.insert(5);
        tree7.remove(-80);
        tree7.insert(5);
        tree7.remove(80);
        tree7.insert(5);
        tree7.insert(5);
        assertTrue(tree7.contains(5));
        tree7.remove(5);
        assertFalse(tree7.contains(5));

    }

    @Test
    public void removeTest1(){
        int size= tree1.size();
        tree1.remove(10);

        assertEquals(size-1, tree1.size());
        assertEquals(15, tree1.getMin());
        assertEquals(2, tree1.getHeight());
    }
    @Test
    public void removeTest2(){
        int size= tree4.size();
        tree4.remove(1);

        assertEquals(size-1, tree4.size());
    }

    @Test (expected= IllegalStateException.class)
    public void removeTest3Excep() throws IllegalStateException{
        tree3.remove(5);
    }
    @Test
    public void removeTest4() {
        tree7.remove(-4);
        tree7.remove(9);
        tree7.remove(3);
        tree7.remove(-80);
        tree7.remove(80);
        assertEquals(10, tree7.size());
    }
    @Test
    public void removeTest5() {
        tree6.remove(7);
        assertEquals(1, tree6.size());
        tree6.remove(1);
        assertEquals(0, tree6.size());
    }
    @Test
    public void insertTest1(){
        int size= tree4.size();

        tree4.insert(10);
        tree4.insert(-5);
        tree4.insert(Integer.MAX_VALUE);

        assertEquals(size+3, tree4.size());
        assertEquals(-5, tree4.getMin());
        assertEquals(Integer.MAX_VALUE, tree4.getMax());
        assertTrue(tree4.contains(Integer.MAX_VALUE));
    }

    @Test
    public void duplicates(){
        assertEquals( 2,tree8.size());
        assertEquals(2, tree8.getMin());
        assertEquals(3, tree8.getMax());
        assertEquals(1, tree8.getHeight());
    }

    @Test
    public void containsTest1(){
        assertTrue(tree4.contains(1));
        assertFalse(tree4.contains(4));
    }
    @Test
    public void containsTest2(){
        assertTrue(tree5.contains(43));
        assertTrue(tree5.contains(7));
        assertFalse(tree5.contains(5));
        tree5.insert(5);
        assertTrue(tree5.contains(5));
    }
    @Test
    public void containsTest3(){
        tree2.remove(9);
        assertFalse(tree2.contains(9));
    }
    @Test
    public void containsTest4(){
        assertFalse(tree3.contains(9));
    }
    @Test
    public void containsTest5(){
        assertTrue(tree7.contains(-4));
        assertTrue(tree7.contains(1));
        assertTrue(tree7.contains(9));
        assertTrue(tree7.contains(3));
        assertFalse(tree7.contains(1000));
        assertFalse(tree7.contains(-1000));
    }





    /**
     * A method to insert a lot of values into the treeSet quickly. Method for Tests.
     * @param tree AVL TREE
     * @param array array of ints. Not null.
     */
    private void insertAArray(AvlTreeSet tree,int[] array){
        for(int num : array){
            tree.insert(num);
        }
    }



}