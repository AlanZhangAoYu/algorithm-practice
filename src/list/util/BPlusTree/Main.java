package list.util.BPlusTree;

/**
 * @author ASUS
 */
public class Main {
     public static void main(String[] args) {
         BPlusTree bPlusTree = new BPlusTree(4);
         KeyAndValue keyAndValue = new KeyAndValue(1,"123");
         KeyAndValue keyAndValue1 = new KeyAndValue(2,"123");
         KeyAndValue keyAndValue2 = new KeyAndValue(3,"123");
         KeyAndValue keyAndValue3 = new KeyAndValue(4,"123");
         KeyAndValue keyAndValue4 = new KeyAndValue(5,"123");
         KeyAndValue keyAndValue5 = new KeyAndValue(6,"123");
         KeyAndValue keyAndValue6 = new KeyAndValue(7,"12300");
         KeyAndValue keyAndValue7 = new KeyAndValue(8,"546");
         KeyAndValue keyAndValue8 = new KeyAndValue(9,"123");
         KeyAndValue keyAndValue9 = new KeyAndValue(10,"123");
         KeyAndValue keyAndValue10 = new KeyAndValue(11,"123");
         KeyAndValue keyAndValue11 = new KeyAndValue(12,"123");
         KeyAndValue keyAndValue12 = new KeyAndValue(13,"123");
         KeyAndValue keyAndValue14 = new KeyAndValue(15,"12345");
         KeyAndValue keyAndValue15 = new KeyAndValue(16,"12345");
         KeyAndValue keyAndValue16 = new KeyAndValue(17,"12345");
         KeyAndValue keyAndValue17 = new KeyAndValue(18,"12345");
         KeyAndValue keyAndValue18 = new KeyAndValue(19,"12345");
         KeyAndValue keyAndValue19 = new KeyAndValue(20,"12345");
         KeyAndValue keyAndValue20 = new KeyAndValue(21,"12345");

         bPlusTree.insert(keyAndValue);
         bPlusTree.insert(keyAndValue5);
         bPlusTree.insert(keyAndValue9);
         bPlusTree.insert(keyAndValue1);
         bPlusTree.insert(keyAndValue7);
         bPlusTree.insert(keyAndValue10);
         bPlusTree.insert(keyAndValue17);
         bPlusTree.insert(keyAndValue2);
         bPlusTree.insert(keyAndValue14);
         bPlusTree.insert(keyAndValue16);
         bPlusTree.insert(keyAndValue11);
         bPlusTree.insert(keyAndValue12);
         bPlusTree.insert(keyAndValue3);
         bPlusTree.insert(keyAndValue8);
         bPlusTree.insert(keyAndValue18);
         bPlusTree.insert(keyAndValue15);
         bPlusTree.insert(keyAndValue4);
         bPlusTree.insert(keyAndValue19);
         bPlusTree.insert(keyAndValue6);
         bPlusTree.insert(keyAndValue20);
         
         bPlusTree.printBPlusTree(bPlusTree.getRoot());

         bPlusTree.delete(1);
         bPlusTree.printBPlusTree(bPlusTree.getRoot());

         bPlusTree.delete(0);
         bPlusTree.printBPlusTree(bPlusTree.getRoot());

         bPlusTree.delete(2);
         bPlusTree.printBPlusTree(bPlusTree.getRoot());

         bPlusTree.delete(11);
         bPlusTree.printBPlusTree(bPlusTree.getRoot());

         bPlusTree.delete(3);
         bPlusTree.printBPlusTree(bPlusTree.getRoot());

         bPlusTree.delete(4);
         bPlusTree.printBPlusTree(bPlusTree.getRoot());

         bPlusTree.delete(5);
         bPlusTree.printBPlusTree(bPlusTree.getRoot());

         bPlusTree.delete(9);
         bPlusTree.printBPlusTree(bPlusTree.getRoot());

         bPlusTree.delete(6);
         bPlusTree.printBPlusTree(bPlusTree.getRoot());

         bPlusTree.delete(13);
         bPlusTree.printBPlusTree(bPlusTree.getRoot());

         bPlusTree.delete(7);
         bPlusTree.printBPlusTree(bPlusTree.getRoot());

         bPlusTree.delete(10);
         bPlusTree.printBPlusTree(bPlusTree.getRoot());

         bPlusTree.delete(18);
         bPlusTree.printBPlusTree(bPlusTree.getRoot());

         bPlusTree.delete(8);
         bPlusTree.printBPlusTree(bPlusTree.getRoot());

         bPlusTree.delete(12);
         bPlusTree.printBPlusTree(bPlusTree.getRoot());

         bPlusTree.delete(20);
         bPlusTree.printBPlusTree(bPlusTree.getRoot());

         bPlusTree.delete(19);
         bPlusTree.printBPlusTree(bPlusTree.getRoot());

         bPlusTree.delete(15);
         bPlusTree.printBPlusTree(bPlusTree.getRoot());

         bPlusTree.delete(17);
         bPlusTree.printBPlusTree(bPlusTree.getRoot());
     }
 }
