
import java.util.HashMap;
import java.util.Map;

public class DictTreeSolution {
    public int minimumLengthEncoding(String[] words) {
        Node rootNode = new Node();
        Map<Node, Integer> wordsNode = new HashMap<>();


        for (int i = 0; i < words.length; ++i) {
            String word = words[i];

            Node currentNode = rootNode;

            for (int index = word.length() - 1; index >= 0; --index) {
                currentNode = rootNode.getOrAddNode(word.charAt(index));

            }

            wordsNode.put(currentNode, i);//把单词结束的节点添加进记录用的数组中
        }

        int result = 0;
        for (Node each : wordsNode.keySet()) {
            if (each.isTerminalNode) {
                result += words[wordsNode.get(each)].length() + 1;
            }
        }

        return result;
    }


}

//字典树的字母节点
//基于静态数组实现
class Node {
    //List<Node> children = new ArrayList<>();//因为是确定的26个字母，也可写成 26 长度的 Node[]
    //写成list不太合行，因为有直接查找节点的需求，可以用Map<String,Node>表示childrenMap，也可以直接用Node[]
    Node[] childrenArray = new Node[26];//对象数组，初始值为null
    boolean isTerminalNode = true;//方便后期当前节点是否为末端节点

    public Node getOrAddNode(char letter) {
        if (null == childrenArray[letter - 'a']) {
            isTerminalNode = false;
            childrenArray[letter - 'a'] = new Node();
        }
        return childrenArray[letter - 'a'];
    }


}

class Node_plus {
    Node_plus[] childrenArray = new Node_plus[26];//对象数组，初始值为null
    int distanceFromRoot = 0;//方便后期当前节点是否为末端节点

    public Node_plus getOrAddNode(char letter) {
        if (null == childrenArray[letter - 'a']) {
            childrenArray[letter - 'a'] = new Node_plus();
            childrenArray[letter - 'a'].distanceFromRoot = (++distanceFromRoot);//子节点距离Root的距离，只比父节点+1
        }
        return childrenArray[letter - 'a'];
    }


}

