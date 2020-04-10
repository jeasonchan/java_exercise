package default_package.枚举单实例测试;


public enum Node {
    LEFT("("),
    RIGHT(")");

    Node(String value) {
        this.value = value;
    }

    public String value;
    public String flag="";
    public Node leftNode = null;
    public Node rightNode = null;
}
