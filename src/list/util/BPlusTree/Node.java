package list.util.BPlusTree;
import java.util.List;

/**
 * @author ZAY
 * 节点类
 */
public class Node {
    /**
     *节点的子节点
     */
    private List<Node> nodes;
    /**
     * 节点的键值对
     */
    private List<KeyAndValue> keyAndValue;
    /**
     * 节点的后节点
     */
    private Node nextNode;
    /**
     * 节点的前节点
     */
    private Node previousNode;
    /**
     * 节点的父节点
     */
    private Node parentNode;

    public Node( List<Node> nodes, List<KeyAndValue> keyAndValue, Node nextNode,Node previousNode, Node parentNode) {
        this.nodes = nodes;
        this.keyAndValue = keyAndValue;
        this.nextNode = nextNode;
        this.parentNode = parentNode;
        this.previousNode = previousNode;
    }

    boolean isLeaf() {
        return nodes==null;
    }

    boolean isHead() {
        return previousNode == null;
    }

    boolean isTail() {
        return nextNode == null;
    }

    boolean isRoot() {
        return parentNode == null;
    }


    List<Node> getNodes() {
        return nodes;
    }

    void setNodes(List<Node> nodes) {
        this.nodes = nodes;
    }


    List<KeyAndValue> getKeyAndValue() {
        return keyAndValue;
    }

    public void setKeyAndValue(List<KeyAndValue> keyAndValue) {
        this.keyAndValue = keyAndValue;
    }

    Node getNextNode() {
        return nextNode;
    }

    void setNextNode(Node nextNode) {
        this.nextNode = nextNode;
    }

    Node getParentNode() {
        return parentNode;
    }

    void setParentNode(Node parentNode) {
        this.parentNode = parentNode;
    }

    Node getPreviousNode() {
        return previousNode;
    }

    void setPreviousNode(Node previousNode) {
        this.previousNode = previousNode;
    }
}
