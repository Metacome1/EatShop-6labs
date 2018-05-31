package barBossHouse;

public class ListNode {
    private ListNode prev;
    private ListNode next;
    private MenuItem value;

    public ListNode() {
    }

    ListNode(MenuItem value) {
        this.value = value;
    }

    public ListNode(ListNode prev, MenuItem value, ListNode next) {
        this.prev = prev;
        this.next = next;
        this.value = value;
    }
    public ListNode getPrev() {
        return prev;
    }

    public void setPrev(ListNode prev) {
        this.prev = prev;
    }

    public ListNode getNext() {
        return next;
    }

    public MenuItem getValue() {
        return value;
    }

    public void setNext(ListNode next) {
        this.next = next;
    }

    public void setValue(MenuItem value) {
        this.value = value;
    }
}
