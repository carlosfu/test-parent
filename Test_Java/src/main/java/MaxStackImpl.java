import java.util.LinkedList;

/**
 * 
 * @author leifu
 * @Date 2015年7月28日
 * @Time 下午6:22:02
 */
public class MaxStackImpl implements MaxStack {
    public LinkedList<Integer> queue = new LinkedList<Integer>();
    public LinkedList<Integer> maxQueue = new LinkedList<Integer>();

    public Integer pop() {
        Integer maxNum = maxQueue.getFirst();
        Integer num = queue.getFirst();
        if (num == maxNum)
            maxQueue.removeFirst();
        return queue.removeFirst();
    }

    public void push(Integer e) {
        if (!maxQueue.isEmpty()) {
            Integer maxNum = maxQueue.getFirst();
            if (e > maxNum)
                maxQueue.addFirst(e);
        } else {
            maxQueue.addFirst(e);
        }
        queue.addFirst(e);
    }

    @Override
    public Integer max() {
        if (maxQueue.isEmpty())
            return 0;
        return maxQueue.getFirst();
    }
}