package barBossHouse;

public class QueueNode {
    private QueueNode next;
    private QueueNode prev;
    private Order value;


    public QueueNode() {
    }

    public QueueNode(Order value) {
        this.value = value;
    }

    public QueueNode getPrev() {
        return prev;
    }

    public void setPrev(QueueNode prev) {
        this.prev = prev;
    }

    public QueueNode getNext() {
        return next;

    }

    public void setNext(QueueNode next) {
        this.next = next;
    }

    public Order getValue() {
        return value;
    }

    public void setValue(Order value) {
        this.value = value;
    }

}
